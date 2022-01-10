package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    public Category() {
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
