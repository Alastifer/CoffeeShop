package com.project.coffee.shop.entity;

import javax.persistence.*;

/**
 * Represent of coffee. Contains name and price of coffee.
 * Each grade of coffee contains unique identifier.
 */
@Entity
@Table(name = "CoffeeGrades")
public class Coffee {

    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of coffee.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Price of coffee.
     */
    @Column(name = "cost", nullable = false)
    private int cost;

    /**
     * Constructs of empty instance.
     */
    public Coffee() {
    }

    /**
     * Constructs of coffee with name and price.
     *
     * @param title name of coffee
     * @param cost price of coffee
     */
    public Coffee(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    /**
     * Returns coffee's identifier.
     *
     * @return identifier of coffee
     */
    public int getId() {
        return id;
    }

    /**
     * Returns coffee's name.
     *
     * @return name of coffee
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns coffee's price.
     *
     * @return price of coffee
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set coffee's identifier.
     *
     * @param id identifier of coffee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set coffee's name.
     *
     * @param title name of coffee
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set coffee's price.
     *
     * @param cost price of coffee
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

}
