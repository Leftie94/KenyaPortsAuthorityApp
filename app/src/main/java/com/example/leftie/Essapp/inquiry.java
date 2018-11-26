package com.example.leftie.Essapp;

public class inquiry {

    String inquiryid;
    String inquiryto;
    String inquirysubject;
    String inquirymessage;


    public inquiry(){


    }



    public inquiry(String inquiryid, String inquiryto, String inquirysubject, String inquirymessage) {
        this.inquiryid = inquiryid;
        this.inquiryto = inquiryto;
        this.inquirysubject = inquirysubject;
        this.inquirymessage = inquirymessage;
    }

    public String getInquiryid() {
        return inquiryid;
    }

    public String getInquiryto() {
        return inquiryto;
    }

    public String getInquirysubject() {
        return inquirysubject;
    }

    public String getInquirymessage() {
        return inquirymessage;
    }
}
