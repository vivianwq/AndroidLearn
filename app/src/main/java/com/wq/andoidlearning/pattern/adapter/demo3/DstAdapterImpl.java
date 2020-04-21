package com.wq.andoidlearning.pattern.adapter.demo3;

import android.util.Log;

/**
 * DstAdapterImpl只关系1和2方法,因此它只重写了这两个
 */
public class DstAdapterImpl extends DstAdapter {
    @Override
    public void dstMethod1() {
        Log.d("DstAdapter", "DstAdapterImpl.dstMethod1");
    }

    @Override
    public void dstMethod2() {
        Log.d("DstAdapter", "DstAdapterImpl.dstMethod2");
    }
}
