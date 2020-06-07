package com.wq.andoidlearning.chapter16;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main3Activity.class));
            }
        });
        tvContent = findViewById(R.id.tvContent);
        getTopActivity(this);

    }

    /**
     * 获得栈中最顶层的Activity
     *
     * @param context
     * @return
     */
    public void getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            for (int i = 0; i < runningTaskInfos.size(); i++) {
                stringBuilder.append(runningTaskInfos.get(i).topActivity.toString() + "\n");
            }
            tvContent.setText(stringBuilder.toString());
        }
    }
}
