package com.techelevator.services;

import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantList;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface SearchRestaurantsService {

     Restaurant[] getRestaurantSearchList(String searchString);
     RestaurantList getArrayOfRestaurants(String searchString);

}
