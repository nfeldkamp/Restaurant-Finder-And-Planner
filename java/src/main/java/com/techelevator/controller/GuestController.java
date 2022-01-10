package com.techelevator.controller;

import com.techelevator.dao.GuestDao;
import com.techelevator.dao.InvitationDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Guest;
import com.techelevator.model.Invitation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GuestController {
    private GuestDao guestDao;
    private UserDao userDao;


    public GuestController(GuestDao guestDao, UserDao userDao) {
        this.guestDao = guestDao;
        this.userDao = userDao;

    }

    @RequestMapping(value = "/invitations/guests/", method = RequestMethod.POST)
    public Guest[] getGuestsByInvitationId(@RequestBody Invitation invitation) {
        return guestDao.getGuestsByInviteId(invitation.getInvitationId());
    }
}
