package com.chiului.android_mvvm_architecture.dummy;

/**
 * 假 item 数据实体类$
 *
 * @author 神经大条蕾弟
 * @date 2020/09/07 15:54
 */
public class DummyItemBean {

    public String id;
    public String content;
    public String details;

    public DummyItemBean(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
