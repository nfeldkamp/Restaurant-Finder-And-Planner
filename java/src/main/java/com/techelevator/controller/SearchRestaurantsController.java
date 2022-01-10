package com.techelevator.controller;


import com.techelevator.dao.InvitationDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Invitation;
import com.techelevator.model.Restaurant;
import com.techelevator.services.SearchRestaurantsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchRestaurantsController {

    private SearchRestaurantsService searchRestaurantsService;
    private RestaurantDao restaurantDao;

    // dependency injection
    public SearchRestaurantsController(SearchRestaurantsService searchRestaurantsService, RestaurantDao restaurantDao) {
        this.searchRestaurantsService = searchRestaurantsService;
this.restaurantDao = restaurantDao;
    }

//    @RequestMapping(path = "restaurants/{searchString}", method = RequestMethod.GET)
//    public RestaurantList getRestaurantList(@PathVariable String searchString) {
//        RestaurantList newRestaurantList = searchRestaurantsService.getArrayOfRestaurants(searchString);
//        return newRestaurantList;
//    }

    @RequestMapping(path = "restaurants/{searchString}", method = RequestMethod.GET)
    public Restaurant[] getRestaurantArray(@PathVariable String searchString) {
        Restaurant[] newRestaurantList = searchRestaurantsService.getRestaurantSearchList(searchString);
        return newRestaurantList;
    }

    @RequestMapping(value = "/invitations/restaurants/", method = RequestMethod.POST)
    public Restaurant[] getRestaurantsByInvitationId(@RequestBody Invitation invitation) {
        return restaurantDao.getRestaurantByInvitationId(invitation.getInvitationId());
    }

//    @RequestMapping(value = "/invitations/restaurants/", method = RequestMethod.POST)
//    public Restaurant[] saveRestaurantArray(@RequestBody Restaurant[] restaurants) {
//        return restaurantDao.getRestaurantByInvitationId(invitation.getInvitationId());
//    }
}
