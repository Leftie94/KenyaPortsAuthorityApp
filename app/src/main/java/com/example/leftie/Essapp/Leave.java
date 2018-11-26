package com.example.leftie.Essapp;

public class Leave {

    String leaverequestID;
    String leavetype;
    String dtefrom;
    String dteto;
    String leavereason;



    public Leave(String leaverequestID, String leavetype, String dtefrom, String dteto, String leavereason) {
        this.leaverequestID = leaverequestID;
        this.leavetype = leavetype;
        this.dtefrom = dtefrom;
        this.dteto = dteto;
        this.leavereason = leavereason;
    }

    public String getLeaverequestID() {
        return leaverequestID;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public String getDtefrom() {
        return dtefrom;
    }

    public String getDteto() {
        return dteto;
    }

    public String getLeavereason() {
        return leavereason;
    }
}
