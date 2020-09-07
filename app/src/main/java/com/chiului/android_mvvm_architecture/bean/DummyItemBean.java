package com.chiului.android_mvvm_architecture.bean;

/**
 * 假 item 数据实体类$
 *
 * @author 神经大条蕾弟
 * @date 2020/09/07 15:54
 */
public class DummyItemBean {

    public final String id;
    public final String content;
    public final String details;

    public DummyItemBean(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }

}
