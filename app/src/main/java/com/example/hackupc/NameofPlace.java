package com.example.hackupc;

public class NameofPlace {

    private int number;
    private String placename;

    public NameofPlace(int number, String placename) {
        this.number = number;
        this.placename = placename;
    }

    public NameofPlace() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }
}
