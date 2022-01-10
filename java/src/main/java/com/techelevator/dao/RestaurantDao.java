package com.techelevator.dao;

import com.techelevator.model.Restaurant;
import org.springframework.stereotype.Component;


public interface RestaurantDao {

     boolean saveRestaurant(Restaurant restaurant);

     Restaurant getRestaurantById(Long restaurantId);

     Restaurant[] getRestaurantByInvitationId(long invitationId);

     Restaurant[] saveRestaurantArray(Restaurant[] restaurants);

}
