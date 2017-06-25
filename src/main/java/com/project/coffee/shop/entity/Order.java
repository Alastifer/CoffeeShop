package com.project.coffee.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "customer_full_name", nullable = false)
    private String customerFullName;

    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @Column(name = "total_cost", nullable = false)
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
