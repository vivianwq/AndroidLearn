package com.wq.andoidlearning.pattern.adapter.demo4;

public interface EventUploader {
    //点击后上报
    void onItemClick(NewsItem newsItem);

    //依附到视图后上报
    void onItemAttach(NewsItem newsItem);

    //脱离视图后上报
    void onItemDetach(NewsItem newsItem);

    //调用convert()方法后上报
    void onItemConvert(NewsItem newsItem);
}
