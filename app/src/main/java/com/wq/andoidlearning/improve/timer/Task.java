package com.wq.andoidlearning.improve.timer;

import com.wq.andoidlearning.improve.loop.LogUtils;

import java.util.TimerTask;

public class Task extends TimerTask {


    @Override
    public void run() {

        LogUtils.i("开始执行timer定时任务...");


    }
}
