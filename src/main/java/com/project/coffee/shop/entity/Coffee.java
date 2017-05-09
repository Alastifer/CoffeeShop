package com.project.coffee.shop.entity;

public class Coffee {

    private int id;

    private String title;

    private int cost;

    public Coffee() {
    }

    public Coffee(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
