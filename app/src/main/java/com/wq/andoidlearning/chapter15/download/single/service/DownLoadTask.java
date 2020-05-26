package com.wq.andoidlearning.chapter15.download.single.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wq.andoidlearning.chapter15.download.single.DownLoadService;
import com.wq.andoidlearning.chapter15.download.single.db.ThreadDAOImpl;
import com.wq.andoidlearning.chapter15.download.single.entities.FileInfo;
import com.wq.andoidlearning.chapter15.download.single.entities.ThreadInfo;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownLoadTask {
    private Context context;
    private FileInfo fileInfo;
    private ThreadDAOImpl threadDAO;
    private int finished = 0;
    public boolean isPause = false;

    public DownLoadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
        //创建数据库实例
        threadDAO = new ThreadDAOImpl(context);
    }

    /**
     * 开始文件的下载
     */
    public void downLoad() {
        //读取数据库的线程信息
        List<ThreadInfo> threadInfoList = threadDAO.queryThread(fileInfo.url);
        ThreadInfo threadInfo = null;
        if (threadInfoList.size() == 0) {
            //如果数据库中没有就根据下载文件信息创建一个新的下载信息实例对象
            threadInfo = new ThreadInfo();
            threadInfo.id = 0;
            threadInfo.url = fileInfo.url;
            threadInfo.thread_start = 0;
            threadInfo.thread_end = fileInfo.length;
            threadInfo.finished = 0;
        } else {
            //获取数据库返回的下载信息实例对象
            threadInfo = threadInfoList.get(0);
        }
        //创建子线程下载
        DownLoadThread downLoadThread = new DownLoadThread(threadInfo);
        downLoadThread.start();
    }

    /**
     * 下载线程
     */
    class DownLoadThread extends Thread {
        private ThreadInfo threadInfo;

        public DownLoadThread(ThreadInfo threadInfo) {
            this.threadInfo = threadInfo;
        }

        @Override
        public void run() {
            super.run();
            //向数据库中插入一条信息
            if (!threadDAO.isExists(threadInfo.url, threadInfo.id)) {
                //插入一条新的下载记录信息
                threadDAO.insertThread(threadInfo);
            }
            HttpURLConnection connection = null;
            RandomAccessFile raf = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(threadInfo.url);
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(6000);
                connection.setRequestMethod("GET");
                //设置下载位置
                int start = threadInfo.thread_start + threadInfo.finished;
                connection.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.thread_end);
                //设置一个文件写入位置
                File file = new File(DownLoadService.DOWNLOAD_PATH, fileInfo.fileName);
                raf = new RandomAccessFile(file, "rwd");
                //设置文件写入位置
                raf.seek(start);
                Intent intent = new Intent(DownLoadService.ACTION_UPDATE);
                finished += threadInfo.finished;
                //开始下载了
                if (connection.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                    //读取数据
                    inputStream = connection.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        //写入文件
                        raf.write(buffer, 0, len);
                        //下载进度发送广播给activity
                        finished += len;
                        Log.e("DownLoadService", finished + "");
                        intent.putExtra("finished", finished * 100 / fileInfo.length);

                        context.sendBroadcast(intent);
                        //下载暂停是要把进度保存在数据库中
                        if (isPause) {
                            //暂停时更新数据库中的下载信息
                            threadDAO.updateThread(threadInfo.url, threadInfo.id, finished);
                            return;
                        }
                    }
                    //删除线程信息
                    threadDAO.deleteThread(threadInfo.url, threadInfo.id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (raf != null)
                        raf.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
