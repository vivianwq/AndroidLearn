package com.wq.andoidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.materialdesign.BehaviorActivity;
import com.wq.andoidlearning.status.StatusBarActivity1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarActivity1.setStatusBarTransparent(MainActivity.this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStatusBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatusBarActivity1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnBehavior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BehaviorActivity.class);
                startActivity(intent);
            }
        });



//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"file\"; filename=\"3.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
//        Request request = new Request.Builder()
//                .url("http://ges.sstir.cn")
//                .get()
//                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .addHeader("User-Agent", "PostmanRuntime/7.19.0")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "b5180665-8d9d-4d5a-b0ab-3728988d032b,506f7c6b-26ac-475f-884d-afe10ad2fe1a")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Referer", "http://cas.sstir.cn/cas/?service=http%3A%2F%2Fges.sstir.cn%2Fj_spring_cas_security_check%3Bjsessionid%3D06E18F82FE33EC656B06358D4970DF53")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
//                .build();
//
//        Response response = client.newCall(request).execute();
    }
}
