package com.wq.andoidlearning.window;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class ToastViewActivity extends AppCompatActivity {
    private Button viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_view);

        findViewById(R.id.btnException).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToastViewActivity.this,"吐司",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
         viewById = findViewById(R.id.btnException);
        Log.i("ToastViewActivity",Thread.currentThread().getName());
        new Thread(){
            @Override
            public void run() {
                super.run();
//                Looper.prepare();
                Log.i("ToastViewActivity",Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viewById.setText("改变文字");
//                Toast.makeText(ToastViewActivity.this,"吐司",Toast.LENGTH_SHORT).show();

//                Looper.loop();
            }
        }.start();


    }
}
