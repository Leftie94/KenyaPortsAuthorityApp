package com.example.leftie.Essapp.Models;

public class PersonInfo {

    private String id,  mFirst_name,  mSecond_name,  mDob,  mGender, mTel,  mMail,  mCity,  mEmail;

    public PersonInfo(String first_name, String second_name, String dob,
                      String gender, String tel, String mail, String city, String email) {

        this.mFirst_name = first_name;
        this.mSecond_name = second_name;
        this.mDob = dob;
        this.mGender = gender;
        this.mTel = tel;
        this.mMail = mail;
        this.mCity = city;
        this.mEmail = email;
    }


    public String getFirst_name() {
        return mFirst_name;
    }

    public void setFirst_name(String first_name) {
        this.mFirst_name = first_name;
    }

    public String getSecond_name() {
        return mSecond_name;
    }

    public void setSecond_name(String second_name) {
        this.mSecond_name = second_name;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        this.mDob = dob;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String tel) {
        this.mTel = tel;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        this.mMail = mail;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }
}
