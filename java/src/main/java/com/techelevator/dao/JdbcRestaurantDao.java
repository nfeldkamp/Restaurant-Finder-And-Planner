package com.techelevator.dao;


import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRestaurantDao implements RestaurantDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean saveRestaurant(Restaurant restaurant) {
        String insertRestaurant = "INSERT INTO restaurants (restaurant_tindr_number, restaurant_id, restaurant_name, " +
                "restaurant_image_url, restaurant_address_as_string, restaurant_phone, restaurant_website_url, " +
                "restaurant_rating, restaurant_price, restaurant_cat_number) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(insertRestaurant,
                restaurant.getRestaurantId(),
                restaurant.getRestaurantName(),
                restaurant.getRestaurantImageUrl(),
                restaurant.getRestaurantAddressAsString(),
                restaurant.getRestaurantPhone(),
                restaurant.getRestaurantWebsiteUrl(),
                restaurant.getRestaurantRating(),
                restaurant.getRestaurantPrice(),
                restaurant.getRestaurantCatNumber()) == 1;
    }

    public Restaurant[] saveRestaurantArray(Restaurant[] restaurants) {
        Restaurant[] returnArray = new Restaurant[3];
        for (int i = 0; i < 3; i++) {
            String sql = "INSERT INTO restaurants (restaurant_id, restaurant_name, " +
                    "restaurant_image_url, restaurant_address_as_string, restaurant_phone, restaurant_website_url, " +
                    "restaurant_rating, restaurant_price, restaurant_cat_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING restaurant_tindr_number";
            Long newId = jdbcTemplate.queryForObject(sql, Long.class,
                    restaurants[i].getRestaurantId(),
                    restaurants[i].getRestaurantName(),
                    restaurants[i].getRestaurantImageUrl(),
                    restaurants[i].getRestaurantAddressAsString(),
                    restaurants[i].getRestaurantPhone(),
                    restaurants[i].getRestaurantWebsiteUrl(),
                    restaurants[i].getRestaurantRating(),
                    restaurants[i].getRestaurantPrice(),
                    restaurants[i].getRestaurantCatNumber());

            returnArray[i] = getRestaurantById(newId);
        }

        return returnArray;
    }


    public Restaurant getRestaurantById(Long restaurantId) {
        Restaurant restaurant = null;
        String sql = "SELECT restaurant_tindr_number, restaurant_id, restaurant_name, " +
                "restaurant_image_url, restaurant_address_as_string, restaurant_phone, restaurant_website_url, " +
                "restaurant_rating, restaurant_price, restaurant_cat_number FROM restaurants WHERE restaurant_tindr_number = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);

        if(results.next()) {
            restaurant = mapRowToRestaurant(results);
        } else {
            throw new RestaurantNotFoundException();
        }
        return restaurant;
    }

    //TODO invitationId good? use path var?
    //TODO array of restaurants?
    public Restaurant[] getRestaurantByInvitationId(long invitationId) {
        List<Restaurant> listOfRestaurants = new ArrayList<>();
        String sql = "SELECT r.restaurant_tindr_number, r.restaurant_id, r.restaurant_name, " +
                "r.restaurant_image_url, r.restaurant_address_as_string, r.restaurant_phone, " +
                "r.restaurant_website_url, r.restaurant_rating, r.restaurant_price, r.restaurant_cat_number " +
                "FROM invitation i " +
                "JOIN invitation_restaurant ir " +
                "ON i.invitation_id = ir.invitation_id " +
                "JOIN restaurants r " +
                "ON r.restaurant_tindr_number = ir.restaurant_tindr_number " +
                "WHERE i.invitation_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, invitationId);
        while(results.next()) {
            Restaurant restaurant = mapRowToRestaurant(results);
            listOfRestaurants.add(restaurant);
        }
        Restaurant[] arrayOfRestaurants = new Restaurant[listOfRestaurants.size()];
        return listOfRestaurants.toArray(arrayOfRestaurants);
    }
// todo ?? update delete - but dont think we would use?


    private Restaurant mapRowToRestaurant(SqlRowSet rt) {
        Restaurant res = new Restaurant();
        res.setRestaurantTindrNumber(rt.getLong("restaurant_tindr_number"));
        res.setRestaurantId(rt.getString("restaurant_id"));
        res.setRestaurantName(rt.getString("restaurant_name"));
        res.setRestaurantImageUrl(rt.getString("restaurant_image_url"));
        res.setRestaurantAddressAsString(rt.getString("restaurant_address_as_string"));
        res.setRestaurantPhone(rt.getString("restaurant_phone"));
        res.setRestaurantWebsiteUrl(rt.getString("restaurant_website_url"));
        res.setRestaurantRating(rt.getDouble("restaurant_rating"));
        res.setRestaurantPrice(rt.getString("restaurant_price"));
        res.setRestaurantCatNumber(rt.getLong("restaurant_cat_number"));

        return res;
    };
}
