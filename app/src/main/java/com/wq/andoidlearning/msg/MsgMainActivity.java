package com.wq.andoidlearning.msg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class MsgMainActivity extends AppCompatActivity {
    private TextView tvContent;

    static class MyHandler extends Handler {
        private TextView tvContent;

        public MyHandler(TextView tvContent) {
            this.tvContent = tvContent;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            tvContent.setText(msg.what + "我已经被子线程更改了");
        }
    }

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_main);
        tvContent = findViewById(R.id.tvContent);
        myHandler = new MyHandler(tvContent);


        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        myHandler.sendEmptyMessageDelayed(0, 5000);
                    }
                }.start();
                tvContent.setText("线程启动,5秒后会通过子线程更新UI");
            }
        });

        findViewById(R.id.btnHandlerThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MsgMainActivity.this, HandlerThreadActivity.class));
            }
        });

    }
}
