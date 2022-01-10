package com.techelevator.services;

import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantList;
import com.techelevator.model.RestaurantNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestSearchRestaurantsService implements SearchRestaurantsService {

    private static final String YELP_API_KEY = "";
    private static final String API_BASE_URL = "https://api.yelp.com/v3/businesses/";
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public Restaurant[] getRestaurantSearchList(String searchString) {

        Restaurant[] searchRestaurantsList = getArrayOfRestaurants(searchString).getBusinesses();
        Restaurant[] restaurantList = new Restaurant[searchRestaurantsList.length];

        for (int i = 0; i < restaurantList.length; i++) {
            try {

                ResponseEntity<Restaurant> response = restTemplate.exchange(API_BASE_URL
                                + searchRestaurantsList[i].getRestaurantId(), HttpMethod.GET,
                        makeAuthEntity(), Restaurant.class);
                restaurantList[i] = response.getBody();

            } catch (RestClientResponseException | ResourceAccessException e) {
                throw new RestaurantNotFoundException();
            }
        }
       return restaurantList;
    }

    public RestaurantList getArrayOfRestaurants(String searchString) {
        RestaurantList searchRestaurantsList = null;
        try {
            ResponseEntity<RestaurantList> response = restTemplate.exchange(API_BASE_URL + "search?" + searchString, HttpMethod.GET,
                    makeAuthEntity(), RestaurantList.class);
            searchRestaurantsList = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            throw new RestaurantNotFoundException();
        }
        return searchRestaurantsList;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(YELP_API_KEY);
        return new HttpEntity<>(headers);
    }

}
