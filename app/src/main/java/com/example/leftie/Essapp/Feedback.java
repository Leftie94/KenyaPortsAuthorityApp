package com.example.leftie.Essapp;

public class Feedback {

    String feedbackid;
    String feedbackto;
    String feedbacksubject;
    String feedbackmessage;


    public Feedback(){


    }



    public Feedback(String feedbackid, String feedbackto, String feedbacksubject, String feedbackmessage) {
        this.feedbackid = feedbackid;
        this.feedbackto = feedbackto;
        this.feedbacksubject = feedbacksubject;
        this.feedbackmessage = feedbackmessage;
    }

    public String getFeedbackid() {
        return feedbackid;
    }

    public String getFeedbackto() {
        return feedbackto;
    }

    public String getFeedbacksubject() {
        return feedbacksubject;
    }

    public String getFeedbackmessage() {
        return feedbackmessage;
    }
}
