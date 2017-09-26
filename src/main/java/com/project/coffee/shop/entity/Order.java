package com.project.coffee.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Represent of order. Contains name of the person who made the order, his address and price of order.
 * Each order contain unique identifier.
 */
@Entity
@Table(name = "Orders")
public class Order {

    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of person who made the order.
     */
    @Column(name = "customer_full_name", nullable = false)
    private String customerFullName;

    /**
     * Address of person who made the order.
     */
    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    /**
     * Price of order.
     */
    @Column(name = "total_cost", nullable = false)
    private int totalCost;

    /**
     * Constructs empty instance.
     */
    public Order() {
    }

    /**
     * Constructs order.
     *
     * @param customerFullName name of person who made the order
     * @param customerAddress address of person who made the order
     * @param totalCost price of order
     */
    public Order(String customerFullName,
                 String customerAddress,
                 int totalCost) {
        this.customerFullName = customerFullName;
        this.customerAddress = customerAddress;
        this.totalCost = totalCost;
    }

    /**
     * Returns order's identifier.
     *
     * @return identifier of order
     */
    public int getId() {
        return id;
    }

    /**
     * Returns customer's name.
     *
     * @return name of customer
     */
    public String getCustomerFullName() {
        return customerFullName;
    }

    /**
     * Returns customer's address.
     *
     * @return address of customer
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Returns price of order.
     *
     * @return price of order
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Sets order's identifier.
     *
     * @param id identifier of order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets customer's name.
     *
     * @param customerFullName name of customer
     */
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    /**
     * Sets customer's address.
     *
     * @param customerAddress address of customer
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Sets order's price.
     *
     * @param totalCost price of order
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
