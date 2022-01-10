package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Guest;
import com.techelevator.model.Invitation;
import com.techelevator.model.Restaurant;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class InvitationController {

    private UserDao userDao;
    private InvitationDao invitationDao;

    public InvitationController(UserDao userDao, InvitationDao invitationDao) {
        this.userDao = userDao;
        this.invitationDao = invitationDao;
    }

        @RequestMapping(value = "/invitations/guestview/{guestEmailAddress}", method = RequestMethod.GET)
        public Invitation getInvitationByGuestEmail(@PathVariable String guestEmailAddress ) {
            Invitation guestInvitation = invitationDao.getInvitationByGuestEmailAddress(guestEmailAddress);
            return guestInvitation;
        }



//     getguestinvitelinkfromuserid invitations/guestentry/{guestemailaddress}


    @RequestMapping(value = "/invitations/user", method = RequestMethod.GET)
    public Invitation[] getInvitationsByUserId(Principal principal) {
        return invitationDao.getInvitationByUserId(userDao.findIdByUsername(principal.getName()));
    }

//    @RequestMapping(value = "/invitations/", method = RequestMethod.POST)
//    public Invitation createInvitation(@RequestBody Invitation invitation) {
//        return invitationDao.createInvitation(invitation);
//    }

    @RequestMapping(value = "/invitations", method = RequestMethod.POST)
    public void createMonster(@RequestBody Invitation invitation, Principal principal) {
        invitation.setUserId(userDao.findIdByUsername(principal.getName()));
        invitationDao.createTheMonster(invitation);
    }

    @RequestMapping(value = "/invitations/create/", method = RequestMethod.POST)
    public void createInvitation(@RequestBody Invitation invitation) {
        invitationDao.createInvitation(invitation);
    }



}
