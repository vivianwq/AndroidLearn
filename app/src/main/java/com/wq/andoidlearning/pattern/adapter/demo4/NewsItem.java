package com.wq.andoidlearning.pattern.adapter.demo4;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NewsItem {

    @IntDef({SrcType.SRC_TYPE_UC, SrcType.SRC_TYPE_TC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SrcType {
        int SRC_TYPE_UC = 1;
        int SRC_TYPE_TC = 2;
    }

    private @SrcType
    int srcType;
    private UcBean ucBean;
    private TcBean tcBean;

    public static NewsItem newInstance(Object object) {
        if (object instanceof UcBean) {
            return new NewsItem((UcBean) object);
        } else if (object instanceof TcBean) {
            return new NewsItem((TcBean) object);
        }
        return null;
    }

    private EventUploader eventUploader;

    /**
     * 返回上报的策略
     *
     * @return
     */
    public EventUploader getUploader() {
        if (eventUploader != null) {
            return eventUploader;
        }
        if (srcType == SrcType.SRC_TYPE_TC) {
            eventUploader = new TcEventUploader();
        } else if (srcType == SrcType.SRC_TYPE_UC) {
            eventUploader = new UcEventUploader();
        } else {
            eventUploader = new AbsUploader();
        }
        return eventUploader;
    }

    private NewsItem(UcBean ucBean) {
        this.ucBean = ucBean;
        srcType = SrcType.SRC_TYPE_TC;
    }

    private NewsItem(TcBean tcBean) {
        this.tcBean = tcBean;
        srcType = SrcType.SRC_TYPE_TC;
    }

    /**
     * 获取其唯一标识值
     *
     * @return
     */
    public String getIdentify() {
        if (srcType == SrcType.SRC_TYPE_TC) {
            return ucBean.getId();
        } else if (srcType == SrcType.SRC_TYPE_UC) {
            return tcBean.getId();
        }
        return "";
    }

    /**
     * 获取标题
     *
     * @return
     */
    public String getTitle() {
        if (srcType == SrcType.SRC_TYPE_TC) {
            return ucBean.getUcTitle();
        } else if (srcType == SrcType.SRC_TYPE_UC) {
            return tcBean.getTcTitle();
        }
        return "";
    }
}
