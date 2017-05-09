package com.project.coffee.shop.entity;

public class OrderElement {

    private Coffee coffee;

    private int amount;

    private int total;

    public OrderElement(Coffee coffee, int amount) {
        this.coffee = coffee;
        this.amount = amount;
        this.total = coffee.getCost() * amount - (amount / 3 * coffee.getCost());
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotal() {
        return total;
    }

}
