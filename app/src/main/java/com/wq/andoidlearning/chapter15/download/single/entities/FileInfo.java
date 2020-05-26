package com.wq.andoidlearning.chapter15.download.single.entities;

import java.io.Serializable;

public class FileInfo implements Serializable {
    public int id;
    public String url;
    public String fileName;
    public int length;
    public int finished;
}
