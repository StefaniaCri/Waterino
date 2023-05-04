package com.example.waterreminder;

public class Drink {
    private String title;
    private String description;
    private int quantity;

    private String userRef;
    public Drink() {

    }

    public Drink(String title, String description, int quantity,String userRef) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.userRef = userRef;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String  getUserRef() {return userRef;}

}
