package com.example.waterreminder;

public class Drink {
    private String title;
    private String description;
    private int quantity;

    public Drink() {

    }

    public Drink(String title, String description, int quantity) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
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
}
