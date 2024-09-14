package com.example.enums;

public enum Laptop {
    Macbook(2000), XPS(2200), Surface(2500), Thinkpad(1500);

    private int price;

    
    private Laptop(int price) {
        this.price = price;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    
}
