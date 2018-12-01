package com.example.leftie.Essapp.Models;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mTitle;
    private String mSubtitle;
    private String mMessage;
    private String mKey;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String title, String subtitle,String message) {
        if (title.trim().equals("")) {
            title = "No Title";
        }

        mTitle = title;
        mSubtitle = subtitle;
        mMessage = message;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}