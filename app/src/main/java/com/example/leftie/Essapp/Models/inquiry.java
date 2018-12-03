package com.example.leftie.Essapp.Models;

import com.google.firebase.database.Exclude;

public class inquiry {

    private String mTo,mSubject,mMessage,mKey;

    public inquiry(){

    }

    public inquiry(String to, String subject, String message) {
        this.mTo = to;
        this.mSubject = subject;
        this.mMessage = message;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        this.mTo = to;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
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
