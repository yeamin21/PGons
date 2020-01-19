package com.hoyt.pigeondb.pairs;;

public class Eggs {
    private String laying;

    public Eggs(String hatching, String status) {
        this.hatching = hatching;
        this.status = status;
    }

    private String hatching;
    private String status;

    public Eggs() {
    }
    public  Eggs(String laying)
    {
        this.laying=laying;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLaying() {
        return laying;
    }

    public void setLaying(String laying) {
        this.laying = laying;
    }

    public String getHatching() {
        return hatching;
    }

    public void setHatching(String hatching) {
        this.hatching = hatching;
    }
}
