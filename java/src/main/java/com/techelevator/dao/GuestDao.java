package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.util.List;


public interface GuestDao {

//    List<User> findAll();
//
//    User getUserById(Long userId);
//
//    User findByUsername(String username);
//
//    int findIdByUsername(String username);
//
//    boolean create(String username, String password, String role);

    boolean createGuest(Guest guest);

    Guest[] getGuestsByInviteId(long invitationId);

    Guest getGuestById(Long guestId);

    Guest[] createGuestArray(Guest[] guests);
}
