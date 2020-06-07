package com.wq.andoidlearning.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import static com.wq.andoidlearning.event.MyLinearLayout.getType;
import static com.wq.andoidlearning.event.SimpleRSA.rsa;

public class EventMainActivity extends AppCompatActivity {
    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);
        tvContent = findViewById(R.id.tvContent);
        EventBus.getDefault().register(this);
        findViewById(R.id.btnSurface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventMainActivity.this, TestActivity.class));
            }
        });
//        gcTest();
    }

    ThreadLocal<Integer> threadLocal;

    public void gcTest() {
        // all these objects have a strong reference
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread() {
            @Override
            public void run() {
                threadLocal = new ThreadLocal<>();
                threadLocal.set(100);
                showText("ThreadLocal===" + threadLocal.get());
//                System.gc();
            }
        }.start();


        //基数
        int baseNum = 3 * 11;
        //公钥
        int keyE = 3;
        //密钥
        int keyD = 7;
        //未加密的数据
        long msg = 24L;
        //加密后的数据
        long encodeMsg = rsa(baseNum, keyE, msg);
        //解密后的数据
        long decodeMsg = rsa(baseNum, keyD, encodeMsg);

        showText("加密前：" + msg);
        showText("加密后：" + encodeMsg);
        showText("解密后：" + decodeMsg);


        Object strongA = a;
        SoftReference<Object> softB = new SoftReference<>(b);
        WeakReference<Object> weakC = new WeakReference<>(c);
        a = null;
        b = null;
        c = null;
        showText("Before gc...");
        showText(String.format("strongA = %s, softB = %s, weakC = %s", strongA, softB.get(), weakC.get()));
        showText("Run GC...");
        showText("ThreadLocal===" + threadLocal.get());
//        threadLocal.remove();

        System.gc();

        // object with only soft reference will be cleaned only if memory is not enough: 用来做缓存很不错
        // object with only weak reference will be cleaned after a gc operation:
        showText("After gc...");
        showText(String.format("strongA = %s, softB = %s, weakC = %s", strongA, softB.get(), weakC.get()));
        showText("ThreadLocal===" + threadLocal.get());

    }


    public void showText(String string) {
        stringBuilder.append(string + "\n");
        tvContent.setText(stringBuilder);
    }

    @Subscriber
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg() + "\n");
        tvContent.setText(stringBuilder);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = false;
        EventBus.getDefault().post(new ServiceBean("EventMainActivity--onTouchEvent--" + b + "===" + getType(event)));
        super.onTouchEvent(event);
        return b;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean b = true;
//        EventBus.getDefault().post(new ServiceBean("EventMainActivity--dispatchTouchEvent--" + b + "===" + getType(ev)));
//        super.dispatchTouchEvent(ev);
//
//        return b;
//    }
}
