package com.wq.andoidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.WalleChannelReader;
import com.wq.andoidlearning.apt.AptMainActivity;
import com.wq.andoidlearning.arouter.ARouterMainActivity;
import com.wq.andoidlearning.bitmap.BitmapMainActivity;
import com.wq.andoidlearning.chapter15.Chapter15Activity;
import com.wq.andoidlearning.chapter9.AnimMainActivity;
import com.wq.andoidlearning.component.ComponentMainActivity;
import com.wq.andoidlearning.dagger.DaggerMainActivity;
import com.wq.andoidlearning.improve.ImproveMainActivity;
import com.wq.andoidlearning.materialdesign.MainMaterialActivity;
import com.wq.andoidlearning.msg.MsgMainActivity;
import com.wq.andoidlearning.pattern.PatternMainActivity;
import com.wq.andoidlearning.pull.PullXmlActivity;
import com.wq.andoidlearning.rxjava.RxJavaDemoMainActivity;
import com.wq.andoidlearning.status.StatusBarActivity1;
import com.wq.andoidlearning.trace.TraceViewMainActivity;
import com.wq.andoidlearning.window.WindowMainActivity;

import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarActivity1.setStatusBarTransparent(MainActivity.this);
//        setUp();
        setContentView(R.layout.activity_main);
        tvContent=findViewById(R.id.tvContent);
        findViewById(R.id.btnWay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String channel = WalleChannelReader.getChannel(MainActivity.this);
                StringBuilder stringBuilder=new StringBuilder();
                ChannelInfo channelInfo= WalleChannelReader.getChannelInfo(MainActivity.this);
                if (channelInfo != null) {
                    String channel = channelInfo.getChannel();
                    Map<String, String> extraInfo = channelInfo.getExtraInfo();
                    stringBuilder.append(channel+"---");
                    stringBuilder.append(extraInfo.get("buildtime")+"---");
                    stringBuilder.append(extraInfo.get("hash")+"---");
                }
                // 或者也可以直接根据key获取
                String value = WalleChannelReader.get(MainActivity.this, "alias");
                stringBuilder.append(value+"---");
                tvContent.setText(stringBuilder);
                Toast.makeText(MainActivity.this,stringBuilder.toString(), Toast.LENGTH_LONG)
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
}
