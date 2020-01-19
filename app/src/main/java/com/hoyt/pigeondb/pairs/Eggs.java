package com.hoyt.pigeondb.pairs;;

public class Eggs {
    private String laying;
    private String hatching;
    private String status;
    private String key;

    public Eggs() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Eggs(String laying, String hatching, String status) {
        this.hatching = hatching;
        this.status = status;
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
