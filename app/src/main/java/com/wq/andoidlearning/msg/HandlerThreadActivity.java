package com.wq.andoidlearning.msg;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class HandlerThreadActivity extends AppCompatActivity {


    private TextView tvContent;

    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String description = msg.getData().getString("description");
            tvContent.setText(description);
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        tvContent = findViewById(R.id.tvContent);

        HandlerThread handlerThread = new HandlerThread("handler-thread");
        handlerThread.start();

        Handler childHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //运行在子线程
                String description = msg.getData().getString("description");
                Message message = mainHandler.obtainMessage();
                Bundle data = message.getData();
                data.putString("description", description);
                //通过主线程的handler发送消息
                //调用主线程的handler发送消息
                mainHandler.sendMessage(message);
            }
        };

        //主线程sendMsg
        for (int i = 0; i < 10; i++) {
            //子线程childHandler发送消息
            Message message = childHandler.obtainMessage();
            Bundle data = new Bundle();
            data.putString("description", "hello world  " + i);
            message.setData(data);
            childHandler.sendMessageDelayed(message, 1000 * i);
        }
    }
}
