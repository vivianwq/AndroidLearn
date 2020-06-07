package com.wq.andoidlearning;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.WalleChannelReader;
import com.wq.andoidlearning.anr.AnrActivity;
import com.wq.andoidlearning.apt.AptMainActivity;
import com.wq.andoidlearning.arouter.ARouterMainActivity;
import com.wq.andoidlearning.bitmap.BitmapMainActivity;
import com.wq.andoidlearning.chapter15.Chapter15Activity;
import com.wq.andoidlearning.chapter16.MainMainActivity;
import com.wq.andoidlearning.chapter9.AnimMainActivity;
import com.wq.andoidlearning.component.ComponentMainActivity;
import com.wq.andoidlearning.component.service.ServiceBean;
import com.wq.andoidlearning.dagger.DaggerMainActivity;
import com.wq.andoidlearning.event.EventMainActivity;
import com.wq.andoidlearning.event.RemoteService;
import com.wq.andoidlearning.hotfix.FixDexUtil;
import com.wq.andoidlearning.hotfix.HotFixUtil;
import com.wq.andoidlearning.improve.ImproveMainActivity;
import com.wq.andoidlearning.improve.loop.LogUtils;
import com.wq.andoidlearning.jvm.JVMCopyActivity;
import com.wq.andoidlearning.materialdesign.MainMaterialActivity;
import com.wq.andoidlearning.msg.ConstantKeys;
import com.wq.andoidlearning.msg.MsgMainActivity;
import com.wq.andoidlearning.pattern.PatternMainActivity;
import com.wq.andoidlearning.pull.PullXmlActivity;
import com.wq.andoidlearning.rxjava.RxJavaDemoMainActivity;
import com.wq.andoidlearning.sort.SortActivity;
import com.wq.andoidlearning.status.StatusBarActivity1;
import com.wq.andoidlearning.trace.TraceViewMainActivity;
import com.wq.andoidlearning.view.TestString;
import com.wq.andoidlearning.view.ViewMainActivity;
import com.wq.andoidlearning.window.WindowMainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import dalvik.system.DexClassLoader;
import protocol.ProtocolBuffActivity;


public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();
    private View rlContent;
    private Looper looper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarActivity1.setStatusBarTransparent(MainActivity.this);
//        setUp();
        setContentView(R.layout.activity_main);
        loadTest();

        rlContent = findViewById(R.id.text);
        tvContent = findViewById(R.id.tvContent);
//        if (savedInstanceState != null) {
//            stringBuilder.append("onCreate取出的数据==>" + savedInstanceState.getString("save") + "\n");
//            tvContent.setText(stringBuilder);
//        }


        rlContent.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable1", String.valueOf(rlContent.getHeight()));
                Log.i("Runnable2", String.valueOf(rlContent.getMeasuredHeight()));
                int height = 0;
                ViewGroup viewGroup = (ViewGroup) rlContent.getParent();
                if (rlContent.getBottom() > viewGroup.getBottom()) {
                    height = viewGroup.getBottom() - rlContent.getTop();
                } else {
                    height = rlContent.getHeight();
                }
                Log.e("Runnable3", " " + height);


            }
        });
        ServiceBean serviceBean = ConstantKeys.IjkPlayerType.bean;
        int i = ConstantKeys.IjkPlayerType.TYPE_IJK;
        int j = ConstantKeys.IjkPlayerType.TYPE_NATIVE;
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            System.out.println("classLoader: " + classLoader);
            classLoader = classLoader.getParent();
        }

        Toast.makeText(MainActivity.this, "测试消息" + i + "---" + j
                + serviceBean.getMsg(), Toast.LENGTH_LONG).show();

        findViewById(R.id.btnWay).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (looper != null) {
                    looper.quit();
                    Log.i("MainActivity", "已经结束了");
                }
            }
        }, 3000);
        findViewById(R.id.btnLast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SortActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnTouch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBuff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProtocolBuffActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnAnr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnrActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnJvm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JVMCopyActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnWay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String channel = WalleChannelReader.getChannel(MainActivity.this);
                StringBuilder stringBuilder = new StringBuilder();
                ChannelInfo channelInfo = WalleChannelReader.getChannelInfo(MainActivity.this);
                if (channelInfo != null) {
                    String channel = channelInfo.getChannel();
                    Map<String, String> extraInfo = channelInfo.getExtraInfo();
                    stringBuilder.append(channel + "---");
                    stringBuilder.append(extraInfo.get("buildtime") + "---");
                    stringBuilder.append(extraInfo.get("hash") + "---");
                }
                // 或者也可以直接根据key获取
                String value = WalleChannelReader.get(MainActivity.this, "alias");
                stringBuilder.append(value + "---");
                tvContent.setText(stringBuilder);
                Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });
        findViewById(R.id.btnStatusBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatusBarActivity1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnChapter9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnChapter15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Chapter15Activity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.btnImprove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImproveMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnLoadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HotFixUtil.patch(getApplicationContext());
                try {
//                    String dexPath = Environment.getExternalStorageDirectory() + "/TestString.dex";
//                    HotFixUtil.fixDexFile(MainActivity.this, dexPath);

                    FixDexUtil.loadFixedDex(getApplicationContext());
                    Toast.makeText(MainActivity.this, "修复成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "修复失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.btnPullXml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PullXmlActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MsgMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnHotFix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, TestString.test(), Toast.LENGTH_LONG).show();
                tvContent.setText(TestString.test());
//                Intent intent = new Intent(MainActivity.this, LoadApkActivity.class);
//                startActivity(intent);
            }
        });


        findViewById(R.id.btnApt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AptMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnComponent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComponentMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnBehavior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMaterialActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDagger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaggerMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnProcess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myServiceIntent = new Intent(MainActivity.this, RemoteService.class);
                MainActivity.this.startService(myServiceIntent);
            }
        });

        findViewById(R.id.btnRxJava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxJavaDemoMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnPattern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatternMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnARouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ARouterMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnSmall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Small.openUri("small", MainActivity.this);
                Intent intent = new Intent(MainActivity.this, DebugActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnTrace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TraceViewMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BitmapMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WindowMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUp() {
//        Small.setUp(this, new Small.OnCompleteListener() {
//            @Override
//            public void onComplete() {
//                Log.d("MainActivity", "onComplete");
//            }
//        });
    }

    public void loadTest() {
        // 1. 先把assets中的classes.dex文件复制到一个本地目录中
        LogUtils.i("Context的类加载器：" + Context.class.getClassLoader());
        LogUtils.i("TextView的类加载器： " + TextView.class.getClassLoader());
        LogUtils.i("String的类加载器： " + String.class.getClassLoader());
        LogUtils.i("自定义类的加载器： " + TestString.class.getClassLoader());
        LogUtils.i("系统的内置类加载器" + ClassLoader.getSystemClassLoader());
        File originDex = null;
        try {
            InputStream open = getAssets().open("Test.dex");
            File dexOutputDir = getCacheDir();
            originDex = new File(dexOutputDir, "Test.dex");
            FileOutputStream fileOutputStream = new FileOutputStream(originDex);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = open.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.close();
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

// 2. 创建DexClassLoader加载dex文件中的类
        if (originDex != null) {
            File dexOptimizeDir = getDir("dex", Context.MODE_PRIVATE);
            String dexOutputPath = dexOptimizeDir.getAbsolutePath();
            DexClassLoader dexClassLoader = new DexClassLoader(originDex.getAbsolutePath(), dexOutputPath, null, getClassLoader());

            try {
                Class<?> clazz = dexClassLoader.loadClass("Test");
                System.out.println("loaded class: " + clazz);
                System.out.println("class loader: " + clazz.getClassLoader());
                System.out.println("class loader parent: " + clazz.getClassLoader().getParent());
                Constructor constructor = clazz.getConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                Method print = clazz.getDeclaredMethod("print");
                print.setAccessible(true);
                print.invoke(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "onConfigurationChanged", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stringBuilder.append("onRestoreInstanceState取出的数据==>" + savedInstanceState.getString("save") + "\n");
        tvContent.setText(stringBuilder);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save", "保存的数据");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "销毁", Toast.LENGTH_LONG).show();
    }
}
