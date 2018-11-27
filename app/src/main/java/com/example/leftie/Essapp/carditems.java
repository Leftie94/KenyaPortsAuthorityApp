package com.example.leftie.Essapp;

public class carditems {

    private String mtext1;
    private String mtext2;
    private String mMessage;
    private String key;

    public carditems(String text1, String text2,String message){

        mtext1 = text1;
        mtext2 = text2;
        mMessage = message;

    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setMtext1(String mtext1) {
        this.mtext1 = mtext1;
    }

    public void setMtext2(String mtext2) {

    }

    public String getMtext1() {
        return mtext1;
    }

    public String getMtext2(){
        return mtext2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
