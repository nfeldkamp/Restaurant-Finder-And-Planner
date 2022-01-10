package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.Invitation;
import com.techelevator.model.Restaurant;
import org.springframework.stereotype.Component;


public interface InvitationDao {
    Invitation createInvitation(Invitation invitationFromClient);
    Invitation getInvitationById(long invitationId);
    Long getRestaurantTindrNumber(Restaurant restaurant);
    //check
    Long getGuestIdByGuestEmail(Guest guest);
    //check
    Invitation[] getInvitationByUserId(Long userId);
    void createTheMonster(Invitation invitationFromClient);

    Invitation getInvitationByGuestEmailAddress(String guestEmailAddress);
}
