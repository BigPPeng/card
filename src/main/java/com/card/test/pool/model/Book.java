package com.card.test.pool.model;

import java.util.List;

public class Book {
    private long bookId;
    private String name;
    private long writerId;
    private String writerName;
    private long publishId;
    private List<Price> price;

    public Book() {
    }

    public Book(long bookId, String name, long writerId, String writerName, long publishId, List<Price> price) {
        this.bookId = bookId;
        this.name = name;
        this.writerId = writerId;
        this.writerName = writerName;
        this.publishId = publishId;
        this.price = price;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWriterId() {
        return writerId;
    }

    public void setWriterId(long writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public long getPublishId() {
        return publishId;
    }

    public void setPublishId(long publishId) {
        this.publishId = publishId;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }
}
