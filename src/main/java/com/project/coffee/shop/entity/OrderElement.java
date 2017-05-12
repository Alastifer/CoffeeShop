package com.project.coffee.shop.entity;

public class OrderElement {

    private int id;

    private int orderId;

    private Coffee coffee;

    private int amountOfCoffee;

    private int totalCost;

    public OrderElement() {
    }

    public OrderElement(int orderId, Coffee coffee, int amountOfCoffee) {
        this.orderId = orderId;
        this.coffee = coffee;
        this.amountOfCoffee = amountOfCoffee;
        this.totalCost = coffee.getCost() * amountOfCoffee - (amountOfCoffee / 3 * coffee.getCost());
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public int getAmountOfCoffee() {
        return amountOfCoffee;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public void setAmountOfCoffee(int amountOfCoffee) {
        this.amountOfCoffee = amountOfCoffee;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
