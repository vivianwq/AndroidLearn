package com.wq.andoidlearning.pattern.adapter.demo3;

import android.util.Log;

/**
 * DstAdapterImpl2只关系1和3方法,因此它只重写了这两个
 */
public class DstAdapterImpl2 extends DstAdapter {
    @Override
    public void dstMethod1() {
        Log.d("DstAdapter", "DstAdapterImpl2.dstMethod1");
    }

    @Override
    public void dstMethod3() {
        Log.d("DstAdapter", "DstAdapterImpl2.dstMethod3");
    }
}
