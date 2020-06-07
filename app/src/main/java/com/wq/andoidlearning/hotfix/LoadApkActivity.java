package com.wq.andoidlearning.hotfix;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.loop.LogUtils;
import com.wq.mylibrary.IPay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dalvik.system.DexClassLoader;

public class LoadApkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_apk);
        //创建apk加载路径
        String src = this.getFilesDir().getAbsolutePath() + File.separator + "a.apk";
        //作为odex的释放路径
        String des = this.getFilesDir().getAbsolutePath() + File.separator + "a" + File.separator;
        try {
            copyPlugin(src);//将assets下的插件apk拷贝到src路径下
            //创建DexClassLoader,parent指定为应用默认的PathClassLoader
            LogUtils.i("src===" + src);
            LogUtils.i("des===" + des);
            DexClassLoader classLoader = new DexClassLoader(src, des, null, getClassLoader());
            Class class1 = classLoader.loadClass("com.wq.pluginpro.PayServiceImpl");
            Object instance = class1.newInstance();
            IPay iPay = (IPay) instance;
            String userName = iPay.getUserName();
            String order = iPay.getOrder("ss");
            iPay.pay(10);
            LogUtils.i("userName" + userName);
            LogUtils.i("order" + order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyPlugin(String path) throws IOException {
        InputStream in = getAssets().open("a.apk");
        OutputStream os = new FileOutputStream(path);
        byte[] temp = new byte[1024];
        int len = -1;
        while ((len = in.read(temp)) != -1) {
            os.write(temp, 0, len);
        }
        in.close();
        os.flush();
        os.close();
    }


}