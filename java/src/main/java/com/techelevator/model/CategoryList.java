package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryList {

    private Category[] categories;

    public CategoryList() {
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
}
