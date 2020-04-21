package com.wq.andoidlearning.pattern.adapter.demo4;

import android.util.Log;

public class TcEventUploader extends AbsUploader {


    @Override
    public void onItemAttach(NewsItem newsItem) {
        super.onItemAttach(newsItem);
        Log.d("EventUploader", "tc.onItemAttach");
    }

    @Override
    public void onItemDetach(NewsItem newsItem) {
        super.onItemDetach(newsItem);
        Log.d("EventUploader", "tc.onItemDetach");
    }

    @Override
    public void onItemConvert(NewsItem newsItem) {
        super.onItemConvert(newsItem);
        Log.d("EventUploader", "tc.onItemConvert");
    }
}
