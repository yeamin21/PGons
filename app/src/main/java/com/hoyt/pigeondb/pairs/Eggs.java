package com.hoyt.pigeondb.pairs;

public class Eggs {
    private String laying, hatching;

    public Eggs() {
    }

    public Eggs(String laying) {
        this.laying = laying;
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
