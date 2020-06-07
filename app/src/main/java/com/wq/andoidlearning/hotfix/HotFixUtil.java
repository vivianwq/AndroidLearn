package com.wq.andoidlearning.hotfix;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.wq.andoidlearning.MainActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class HotFixUtil {
    public static void patch(Context base) {
        try {
            //获取PathClassLoader加载器
            ClassLoader classLoader = MainActivity.class.getClassLoader();
            //反射获得BaseDexClassLoader中的pathList成员变量
            Field dexPathListField = classLoader.getClass().getSuperclass().getDeclaredField("pathList");
            //设为可访问
            dexPathListField.setAccessible(true);
            //获得PathClassLoader中的pathList对象
            Object pathList = dexPathListField.get(classLoader);
            //反射获得DexPathList中的dexElements成员变量
            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            //设为可访问
            dexElementsField.setAccessible(true);
            //获得pathList中的dexElements数组对象
            Object dexElements[] = (Object[]) dexElementsField.get(pathList);
            //反射获得pathList中的makeDexElements方法
//            Method method= pathList.getClass().getDeclaredMethod("makeDexElements", ArrayList.class, File.class,
//                    ArrayList.class);
            Method method = Build.VERSION.SDK_INT >= 23 ?
                    pathList.getClass().getDeclaredMethod("makePathElements", List.class, File.class, List.class) :
                    pathList.getClass().getDeclaredMethod("makeDexElements", ArrayList.class, File.class, ArrayList.class);
            //设为可访问
            method.setAccessible(true);
            List<File> files = new ArrayList<>();
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            //获得我们的patch补丁包
            File patchFile = new File(copyAssetsFile("patch.jar", base));
            files.add(patchFile);
            //指定解压目录
            File optimizedDirectory = new File(base.getFilesDir().getAbsolutePath() + File.separator + "patch");
            if (!optimizedDirectory.exists()) {
                optimizedDirectory.mkdirs();
            }
            //执行makeDexElements方法，解析我们的补丁包获得dexElements数组
            Object dexElementsResult[] = (Object[]) method.invoke(pathList, files, optimizedDirectory, suppressedExceptions);
            //创建一个新的数组
            Object finalResult[] = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + dexElementsResult.length);
            //先把我们的补丁包的dexElements数组放入刚创建的数组
            System.arraycopy(dexElementsResult, 0, finalResult, 0, dexElementsResult.length);
            //再把原来的dexElements数组放入
            System.arraycopy(dexElements, 0, finalResult, dexElementsResult.length, dexElements.length);
            //将新的数组设置回去
            dexElementsField.set(pathList, finalResult);
            for (Object o : finalResult) {
                Log.d("HotFixUtilDebug", o.toString());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String copyAssetsFile(String assetsFileName, Context context) {
        String src = context.getFilesDir().getAbsolutePath() + File.separator + assetsFileName;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = context.getAssets().open(assetsFileName);
            outputStream = new BufferedOutputStream(new FileOutputStream(src));
            byte[] temp = new byte[1024];
            int len;
            while ((len = (inputStream.read(temp))) != -1) {
                outputStream.write(temp, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return src;
    }
    /**
     * 修复指定的类
     *
     * @param context        上下文对象
     * @param fixDexFilePath   修复的dex文件路径
     */
    public static void fixDexFile(Context context, String fixDexFilePath) {
        if (fixDexFilePath != null && new File(fixDexFilePath).exists()) {
            try {
                injectDexToClassLoader(context, fixDexFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param context
     * @param fixDexFilePath 修复文件的路径
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void injectDexToClassLoader(Context context, String fixDexFilePath)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //读取 baseElements
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object basePathList = getPathList(pathClassLoader);
        Object baseElements = getDexElements(basePathList);

        //读取 fixElements
        String baseDexAbsolutePath = context.getDir("dex", 0).getAbsolutePath();
        DexClassLoader fixDexClassLoader = new DexClassLoader(
                fixDexFilePath, baseDexAbsolutePath, fixDexFilePath, context.getClassLoader());
        Object fixPathList = getPathList(fixDexClassLoader);
        Object fixElements = getDexElements(fixPathList);

        //合并两份Elements
        Object newElements = combineArray(baseElements, fixElements);

        //一定要重新获取，不要用basePathList，会报错
        Object basePathList2 = getPathList(pathClassLoader);

        //新的dexElements对象重新设置回去
        setField(basePathList2, basePathList2.getClass(), "dexElements", newElements);
    }

    /**
     * 通过反射先获取到pathList对象
     *
     * @param obj
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getPathList(Object obj) throws ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        return getField(obj, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    /**
     * 从上面获取到的PathList对象中，进一步反射获得dexElements对象
     *
     * @param obj
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getDexElements(Object obj) throws NoSuchFieldException, IllegalAccessException {
        return getField(obj, obj.getClass(), "dexElements");
    }

    private static Object getField(Object obj, Class cls, String str)
            throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);//设置为可访问
        return declaredField.get(obj);
    }

    private static void setField(Object obj, Class cls, String str, Object obj2)
            throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);//设置为可访问
        declaredField.set(obj, obj2);
    }

    /**
     * 合拼dexElements ,并确保 fixElements 在 baseElements 之前
     *
     * @param baseElements
     * @param fixElements
     * @return
     */
    private static Object combineArray(Object baseElements, Object fixElements) {
        Class componentType = fixElements.getClass().getComponentType();
        int length = Array.getLength(fixElements);
        int length2 = Array.getLength(baseElements) + length;
        Object newInstance = Array.newInstance(componentType, length2);
        for (int i = 0; i < length2; i++) {
            if (i < length) {
                Array.set(newInstance, i, Array.get(fixElements, i));
            } else {
                Array.set(newInstance, i, Array.get(baseElements, i - length));
            }
        }
        return newInstance;
    }
}
