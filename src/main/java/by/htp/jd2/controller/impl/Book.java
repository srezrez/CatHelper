package by.htp.jd2.controller.impl;

import java.util.Date;

public class Book {
    String title;
    int price;
    Date date;

    public Book(String title, int price, Date date) {
        this.title = title;
        this.price = price;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
