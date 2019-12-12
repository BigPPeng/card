package com.card.test.pool.model;

/**
 * Created by cuihp on 2019/11/17.
 */
public class Price {
    private long bookId;
    private long date;
    private double cost;
    private double sale;

    public Price() {
    }

    public Price(long bookId, long date, double cost, double sale) {
        this.bookId = bookId;
        this.date = date;
        this.cost = cost;
        this.sale = sale;
    }

    public Price(long date, double cost, double sale) {
        this.date = date;
        this.cost = cost;
        this.sale = sale;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }
}
