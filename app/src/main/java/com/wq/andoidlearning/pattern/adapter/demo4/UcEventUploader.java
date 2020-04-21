package com.wq.andoidlearning.pattern.adapter.demo4;

import android.util.Log;

public class UcEventUploader extends AbsUploader {
    @Override
    public void onItemClick(NewsItem newsItem) {
        super.onItemClick(newsItem);
        Log.d("EventUploader", "uc.onItemClick");
    }
}
