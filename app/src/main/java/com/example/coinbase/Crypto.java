package com.example.coinbase;

public class Crypto {

    private String name;
    private int photo;

    public Crypto() {
    }

    public Crypto(String Name,int Photo)
    {
        name=Name;
        photo=Photo;
    }

    //Getter


    public String getName() {
        return name;
    }


    public int getPhoto() {
        return photo;
    }

    //Setter


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
