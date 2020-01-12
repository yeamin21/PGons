package com.hoyt.pigeondb.pairs;

public class Pairs {


    private String fatherPGN, motherPGN, AssigningDate;

    public Pairs() {
    }

    public Pairs(String fatherPGN, String motherPGN, String assigningDate) {
        this.fatherPGN = fatherPGN;
        this.motherPGN = motherPGN;
        this.AssigningDate = assigningDate;
    }

    public String getFatherPGN() {
        return fatherPGN;
    }

    public void setFatherPGN(String fatherPGN) {
        this.fatherPGN = fatherPGN;
    }

    public String getMotherPGN() {
        return motherPGN;
    }

    public void setMotherPGN(String motherPGN) {
        this.motherPGN = motherPGN;
    }

    public String getAssigningDate() {
        return AssigningDate;
    }

    public void setAssigningDate(String assigningDate) {
        this.AssigningDate = assigningDate;

    }
}
