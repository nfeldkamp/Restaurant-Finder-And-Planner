package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;

public class Restaurant {

    private Long restaurantTindrNumber;
    @JsonProperty("id")
    private String restaurantId;
    @JsonProperty("name")
    private String restaurantName;
    @JsonProperty("image_url")
    private String restaurantImageUrl;
    @JsonProperty("location")
    private Object location;
    private String[] restaurantDisplayAddress;
    private String restaurantAddressAsString;
    @JsonProperty("display_phone")
    private String restaurantPhone;
    @JsonProperty("url")
    private String restaurantWebsiteUrl;
    @JsonProperty("rating")
    private Double restaurantRating;
    @JsonProperty("price")
    private String restaurantPrice;
    private Long restaurantCatNumber;
    private CategoryList[] categories;
    @JsonProperty ("hours")
    private Object[] hours;

    public Restaurant() {
    }

//    public Restaurant(Long restaurantTindrNumber, String restaurantId, String restaurantName, String restaurantImageUrl,
//                      String restaurantAddressAsString, String restaurantPhone, String restaurantWebsiteUrl,
//                      Double restaurantRating, String restaurantPrice, Long restaurantCatNumber, CategoryList[] categories, Object[] hours) {
//        this.restaurantTindrNumber = restaurantTindrNumber;
//        this.restaurantId = restaurantId;
//        this.restaurantName = restaurantName;
//        this.restaurantImageUrl = restaurantImageUrl;
//        this.restaurantAddressAsString = makeRestaurantAddress();
//        this.restaurantPhone = restaurantPhone;
//        this.restaurantWebsiteUrl = restaurantWebsiteUrl;
//        this.restaurantRating = restaurantRating;
//        this.restaurantPrice = restaurantPrice;
//        this.restaurantCatNumber = restaurantCatNumber;
//        this.categories = categories;
//        this.hours = hours;
//    }

    public CategoryList[] getCategories() {
        return categories;
    }

    public void setCategories(CategoryList[] categories) {
        this.categories = categories;
    }

    public String makeRestaurantAddress() {
        return restaurantDisplayAddress[0] + ", " + restaurantDisplayAddress[1];
    }

    public String getRestaurantAddressAsString() {
        return restaurantAddressAsString;
    }

    public void setRestaurantAddressAsString(String restaurantAddressAsString) {
        this.restaurantAddressAsString = restaurantAddressAsString;
    }

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }

    public Long getRestaurantTindrNumber() {
        return restaurantTindrNumber;
    }

    public void setRestaurantTindrNumber(Long restaurantTindrNumber) {
        this.restaurantTindrNumber = restaurantTindrNumber;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String[] getRestaurantDisplayAddress() {
        return restaurantDisplayAddress;
    }

    public void setRestaurantDisplayAddress(String[] restaurantDisplayAddress) {
        this.restaurantDisplayAddress = restaurantDisplayAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantWebsiteUrl() {
        return restaurantWebsiteUrl;
    }

    public void setRestaurantWebsiteUrl(String restaurantWebsiteUrl) {
        this.restaurantWebsiteUrl = restaurantWebsiteUrl;
    }

    public Double getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(Double restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    public String getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(String restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public Long getRestaurantCatNumber() {
        return restaurantCatNumber;
    }

    public void setRestaurantCatNumber(Long restaurantCatNumber) {
        this.restaurantCatNumber = restaurantCatNumber;
    }

    public Object[] getHours() {
        return hours;
    }

    public void setHours(Object[] hours) {
        this.hours = hours;
    }
}
