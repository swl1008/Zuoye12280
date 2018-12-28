package com.example.zuoye.bean;

public class MessageBean {
    private String title;
    private String price;

    public MessageBean(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
