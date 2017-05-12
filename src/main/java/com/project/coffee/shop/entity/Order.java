package com.project.coffee.shop.entity;

public class Order {

    private int id;

    private String customerFullName;

    private String customerAddress;

    private int totalCost;

    public Order() {
    }

    public Order(int id,
                 String customerFullName,
                 String customerAddress,
                 int totalCost) {
        this.id = id;
        this.customerFullName = customerFullName;
        this.customerAddress = customerAddress;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
