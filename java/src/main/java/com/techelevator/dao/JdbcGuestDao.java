package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.Restaurant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGuestDao implements GuestDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGuestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public List findGuestName(String guestName) {
//        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);

    @Override
    public boolean createGuest(Guest guest) {
        boolean guestCreated = false;
        // create guest
//        private Long guestId;
//        private String guestName;
//        private String guestEmailAddress;
//        private String guestInvitationLinkURL;
//        private boolean isGuestInvitationLinkLive;

        String insertGuest = "INSERT INTO guests (guest_email_address, guest_name, invitation_link_url, is_ivitation_link_url_live) " +
                "VALUES (?,?, true) RETURNING guest_id;";

        Long newGuestId = jdbcTemplate.queryForObject(insertGuest, Long.class,
                guest.getGuestName(), guest.getGuestEmailAddress(), guest.getGuestInvitationLinkURL(), guest.isGuestInvitationLinkLive());
        if (newGuestId > 0) {
            guestCreated = true;
        }
        return guestCreated;
    }

    public Guest[] createGuestArray(Guest[] guests) {
        Guest[] returnArray = new Guest[guests.length];
        for (int i = 0; i < guests.length; i++) {
            String sql = "INSERT INTO guests (guest_email_address, guest_name, invitation_link_url, is_invitation_link_url_live) " +
                    "VALUES (?, ?, ?, ?) RETURNING guest_id;";
            Long newId = jdbcTemplate.queryForObject(sql, Long.class,
                    guests[i].getGuestEmailAddress(), guests[i].getGuestName(), guests[i].getGuestInvitationLinkURL(), true);
            returnArray[i] = getGuestById(newId);
        }
        return returnArray;
    }

    //TODO getguestinvitelinkfromuserid invitation/guestentry/{guestemailaddress} - invitation controller as well

    public Guest getGuestById(Long guestId) {

        Guest guest = null;
        String sql = "SELECT guest_id, guest_name, guest_email_address, invitation_link_url, is_invitation_link_url_live FROM guests WHERE guest_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, guestId);
        if (results.next()) {
            guest = mapRowToGuest(results);
        }
        return guest;
    }

    //Get guests by invite Id
    public Guest[] getGuestsByInviteId(long invitationId) {
        List<Guest> listOfGuests = new ArrayList<>();
        String sql = "SELECT g.guest_id, g.guest_email_address, g.guest_name, g.invitation_link_url, " +
                "g.is_invitation_link_url_live " +
                "FROM guests g " +
                "JOIN invitation_guests ig " +
                "ON g.guest_id = ig.guest_id " +
                "JOIN invitation i " +
                "ON i.invitation_id = ig.invitation_id " +
                "WHERE i.invitation_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, invitationId);
        while (results.next()) {
            Guest guest = mapRowToGuest(results);
            listOfGuests.add(guest);
        }
        Guest[] arrayOfGuests = new Guest[listOfGuests.size()];
        return listOfGuests.toArray(arrayOfGuests);
    }

    private Guest mapRowToGuest(SqlRowSet rowSet) {
        Guest guest = new Guest();
        guest.setGuestId(rowSet.getLong("guest_id"));
        guest.setGuestName(rowSet.getString("guest_name"));
        guest.setGuestEmailAddress(rowSet.getString("guest_email_address"));
        guest.setGuestInvitationLinkURL(rowSet.getString("invitation_link_url"));
        guest.setGuestInvitationLinkLive(rowSet.getBoolean("is_invitation_link_url_live"));
        return guest;
    }
}

