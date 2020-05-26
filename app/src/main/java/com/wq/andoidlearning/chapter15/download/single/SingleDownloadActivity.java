package com.wq.andoidlearning.chapter15.download.single;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.chapter15.download.single.entities.FileInfo;
import com.wq.andoidlearning.chapter15.download.single.loadview.ProgressBarView;
import com.wq.andoidlearning.chapter15.download.single.permission.PermissionHelper;
import com.wq.andoidlearning.chapter15.download.single.permission.PermissionSuccess;

public class SingleDownloadActivity extends AppCompatActivity {
    private ProgressBarView progressBarView;
    private FileInfo fileInfo;
    public static final int WRITE_EXTERNAL_REQUEST_DODE = 1000;
    private TextView tvFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_download);
        tvFileName = findViewById(R.id.tvFileName);
        progressBarView = findViewById(R.id.progressBar);
        progressBarView.setMax(100);
        String downLoadFileUrl = "http://music.163.com/song/media/outer/url?id=281951.mp3";
        final int indexOf = downLoadFileUrl.lastIndexOf("/");
        String charSequence = downLoadFileUrl.substring(indexOf + 1, downLoadFileUrl.length());

        fileInfo = new FileInfo();
        fileInfo.id = 0;
        fileInfo.url = downLoadFileUrl;
        fileInfo.fileName = charSequence;
        fileInfo.length = 0;
        fileInfo.finished = 0;
        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownLoadService.ACTION_UPDATE);
        registerReceiver(broadcastReceiver, filter);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //权限申请
                PermissionHelper.with(SingleDownloadActivity.this).
                        requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}).
                        requestCode(WRITE_EXTERNAL_REQUEST_DODE).
                        request();
            }
        });
        findViewById(R.id.btnPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过intent传递参数给service
                Intent intent = new Intent(SingleDownloadActivity.this, DownLoadService.class);
                intent.setAction(DownLoadService.ACTION_STOP);
                intent.putExtra("fileInfo", fileInfo);
                startService(intent);
            }
        });
        new Thread(
        ){
            @Override
            public void run() {
                super.run();
                showName("dd");
                setFloat(222);
            }
        }.start();
    }



    private void showName(@NonNull String name){
        tvFileName.setText(name);

    }
    public void setFloat(@FloatRange(from=0.0,to=1.0) float f){
        tvFileName.setText(f+"");
    }

    @PermissionSuccess(requestCode =WRITE_EXTERNAL_REQUEST_DODE)
    private void doSuccess(){
        //通过intent传递参数给service
        Intent intent = new Intent(SingleDownloadActivity.this, DownLoadService.class);
        intent.setAction(DownLoadService.ACTION_START);
        intent.putExtra("fileInfo", fileInfo);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.requestPermissionsResult(this,requestCode,permissions,grantResults);
    }


    @Override
    protected void onDestroy() {
//        super.onDestroy();
        //注销广播
        unregisterReceiver(broadcastReceiver);
    }

    BroadcastReceiver broadcastReceiver
            = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == DownLoadService.ACTION_UPDATE) {
                int finished = intent.getIntExtra("finished", 0);
                //设置下载进度
                progressBarView.setProgress(finished);
                int progress = progressBarView.getProgress();
                if (progress == progressBarView.getMax()) {
                    Toast.makeText(SingleDownloadActivity.this, "下载完毕", Toast.LENGTH_LONG).show();

                }

            }
        }
    };
}
