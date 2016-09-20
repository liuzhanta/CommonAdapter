package com.tata.commonadapter;

/**
 * Desc:
 * Author: Terry
 * Date:2016-09-20
 */
public class SimpleModel {
    private String title;
    private int resId;

    public SimpleModel(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public SimpleModel(String title) {

        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
