package com.project.coffee.shop.entity;

import javax.persistence.*;

/**
 * Represent of one order element.
 * Contains identifier of order, information about coffee,
 * number of ordered coffee and price of this element.
 */
@Entity
@Table(name = "OrderElements")
public class OrderElement {

    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Order identifier to which the order element is bound.
     */
    @Column(name = "order_id", nullable = false)
    private int orderId;

    /**
     * Information about grade of coffee.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    /**
     * Number of coffee.
     */
    @Column(name = "amount_of_coffee", nullable = false)
    private int amountOfCoffee;

    /**
     * Price of order element.
     */
    @Column(name = "total_cost", nullable = false)
    private int totalCost;

    /**
     * Constructs empty instance.
     */
    public OrderElement() {
    }

    /**
     * Constructs order element.
     *
     * @param orderId order identifier to which the element is bound
     * @param coffee information about grade of coffee
     * @param amountOfCoffee number of coffee
     */
    public OrderElement(int orderId, Coffee coffee, int amountOfCoffee) {
        this.orderId = orderId;
        this.coffee = coffee;
        this.amountOfCoffee = amountOfCoffee;
        this.totalCost = coffee.getCost() * amountOfCoffee - (amountOfCoffee / 3 * coffee.getCost());
    }

    /**
     * Returns order element's identifier.
     *
     * @return identifier of order element
     */
    public int getId() {
        return id;
    }

    /**
     * Returns order's identifier.
     *
     * @return identifier of order
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns coffee.
     *
     * @return coffee
     */
    public Coffee getCoffee() {
        return coffee;
    }

    /**
     * Returns number of coffee.
     *
     * @return number of coffee
     */
    public int getAmountOfCoffee() {
        return amountOfCoffee;
    }

    /**
     * Returns order element's price.
     *
     * @return price of order element
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Sets order element's identifier.
     *
     * @param id identifier of order element
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets order's identifier.
     *
     * @param orderId identifier of order
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Sets coffee.
     *
     * @param coffee grade of coffee
     */
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     * Sets number of coffee.
     *
     * @param amountOfCoffee number of coffee
     */
    public void setAmountOfCoffee(int amountOfCoffee) {
        this.amountOfCoffee = amountOfCoffee;
    }

    /**
     * Sets order element's price.
     *
     * @param totalCost price of order element
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

}
