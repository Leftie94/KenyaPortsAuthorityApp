package com.example.leftie.Essapp.Models;

import com.google.firebase.database.Exclude;

public class Leave {

    private String mType,mFrom,mTo,mReason,mKey, mReply;

    public Leave(){

    }
    public Leave(String type, String from, String to, String reason, String reply) {
        this.mType = type;
        this.mFrom = from;
        this.mTo = to;
        this.mReason = reason;
        this.mReply = reply;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        this.mFrom = from;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        this.mTo = to;
    }

    public String getReason() {
        return mReason;
    }

    public void setReason(String reason) {
        this.mReason = reason;
    }
    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String mKey) {
        this.mKey = mKey;
    }

    public String getReply() {
        return mReply;
    }

    public void setReply(String mReply) {
        this.mReply = mReply;
    }
}
