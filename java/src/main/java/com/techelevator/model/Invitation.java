package com.techelevator.model;

public class Invitation {

    private Long invitationId;
    private Long userId;
    private Long eventUnixTimestamp;
    private Restaurant[] restaurants = new Restaurant[3];
    private Guest[] guests;
    private String eventLocation;
    private int eventGuestnum;
    private int restaurant_1_thumbsup;
    private int restaurant_2_thumbsup;
    private int restaurant_3_thumbsup;
    private int restaurant_1_thumbsdown;
    private int restaurant_2_thumbsdown;
    private int restaurant_3_thumbsdown;

    public Invitation() {
    }

    public Invitation(Long invitationId, Long userId, Long eventUnixTimestamp, Restaurant[] restaurants, Guest[] guests, String eventLocation, int eventGuestnum, int restaurant_1_thumbsup, int restaurant_2_thumbsup, int restaurant_3_thumbsup, int restaurant_1_thumbsdown, int restaurant_2_thumbsdown, int restaurant_3_thumbsdown) {
        this.invitationId = invitationId;
        this.userId = userId;
        this.eventUnixTimestamp = eventUnixTimestamp;
        this.restaurants = restaurants;
        this.guests = guests;
        this.eventLocation = eventLocation;
        this.eventGuestnum = eventGuestnum;
        this.restaurant_1_thumbsup = restaurant_1_thumbsup;
        this.restaurant_2_thumbsup = restaurant_2_thumbsup;
        this.restaurant_3_thumbsup = restaurant_3_thumbsup;
        this.restaurant_1_thumbsdown = restaurant_1_thumbsdown;
        this.restaurant_2_thumbsdown = restaurant_2_thumbsdown;
        this.restaurant_3_thumbsdown = restaurant_3_thumbsdown;
    }

    public int getRestaurant_1_thumbsup() {
        return restaurant_1_thumbsup;
    }

    public void setRestaurant_1_thumbsup(int restaurant_1_thumbsup) {
        this.restaurant_1_thumbsup = restaurant_1_thumbsup;
    }

    public int getRestaurant_2_thumbsup() {
        return restaurant_2_thumbsup;
    }

    public void setRestaurant_2_thumbsup(int restaurant_2_thumbsup) {
        this.restaurant_2_thumbsup = restaurant_2_thumbsup;
    }

    public int getRestaurant_3_thumbsup() {
        return restaurant_3_thumbsup;
    }

    public void setRestaurant_3_thumbsup(int restaurant_3_thumbsup) {
        this.restaurant_3_thumbsup = restaurant_3_thumbsup;
    }

    public int getRestaurant_1_thumbsdown() {
        return restaurant_1_thumbsdown;
    }

    public void setRestaurant_1_thumbsdown(int restaurant_1_thumbsdown) {
        this.restaurant_1_thumbsdown = restaurant_1_thumbsdown;
    }

    public int getRestaurant_2_thumbsdown() {
        return restaurant_2_thumbsdown;
    }

    public void setRestaurant_2_thumbsdown(int restaurant_2_thumbsdown) {
        this.restaurant_2_thumbsdown = restaurant_2_thumbsdown;
    }

    public int getRestaurant_3_thumbsdown() {
        return restaurant_3_thumbsdown;
    }

    public void setRestaurant_3_thumbsdown(int restaurant_3_thumbsdown) {
        this.restaurant_3_thumbsdown = restaurant_3_thumbsdown;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public int getEventGuestnum() {
        return eventGuestnum;
    }

    public void setEventGuestnum(int eventGuestnum) {
        this.eventGuestnum = eventGuestnum;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventUnixTimestamp() {
        return eventUnixTimestamp;
    }

    public void setEventUnixTimestamp(Long eventUnixTimestamp) {
        this.eventUnixTimestamp = eventUnixTimestamp;
    }

    public Restaurant[] getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    public Guest[] getGuests() {
        return guests;
    }

    public void setGuests(Guest[] guests) {
        this.guests = guests;
    }
}

