package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.Invitation;
import com.techelevator.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcInvitationDao implements InvitationDao {

    private JdbcTemplate jdbcTemplate;
    private GuestDao guestDao;
    private RestaurantDao restaurantDao;

    @Autowired
    public JdbcInvitationDao(JdbcTemplate jdbcTemplate, GuestDao guestDao, RestaurantDao restaurantDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.guestDao = guestDao;
        this.restaurantDao = restaurantDao;
    }

    @Override
    public Invitation getInvitationById(long invitationId){
        String sql = "SELECT invitation_id, user_id, event_location, event_guestnum, event_unix_timestamp, restaurant_1_thumbup, " +
                "restaurant_1_thumbdown, restaurant_2_thumbup, restaurant_2_thumbdown, restaurant_3_thumbup, restaurant_3_thumbdown " +
                "FROM invitation " +
                "WHERE invitation_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, invitationId);
        if(rowSet.next()){
            return mapRowToInvitation(rowSet);
        }
        return null;
    }
    @Override
    public Invitation createInvitation(Invitation invitationFromClient) {
        Invitation returnInvitation = null;
        String sql = "INSERT INTO invitation (user_id, event_location, event_guestnum, event_unix_timestamp)"+
                "VALUES (?,?,?,?) RETURNING invitation_id;";
        Long newId = jdbcTemplate.queryForObject(
                sql, Long.class, invitationFromClient.getUserId(), invitationFromClient.getEventLocation(),
                invitationFromClient.getEventGuestnum(), invitationFromClient.getEventUnixTimestamp()
                );
        returnInvitation = getInvitationById(newId);
        return returnInvitation;
    }

@Override
    public void createTheMonster(Invitation invitationFromClient){
        Invitation newInvitation = createInvitation(invitationFromClient);
        Restaurant[] newRestaurants = restaurantDao.saveRestaurantArray(invitationFromClient.getRestaurants());
        Guest[] newGuests = guestDao.createGuestArray(invitationFromClient.getGuests());

        for(int i = 0; i < newRestaurants.length; i++) {
            String sql = "INSERT INTO invitation_restaurant (invitation_id, restaurant_tindr_number)" +
                    "VALUES (?,?);";
            jdbcTemplate.update(sql, newInvitation.getInvitationId(), newRestaurants[i].getRestaurantTindrNumber());
        }

        for(int i = 0; i < newGuests.length; i++){
            String sql = "INSERT INTO invitation_guests (invitation_id, guest_id)" +
                    "VALUES (?,?);";
            jdbcTemplate.update(sql, newInvitation.getInvitationId(), newGuests[i].getGuestId());
        }
    }

//    //@Override
//    public boolean createInvitation(Invitation invitation) {
//        boolean invitationCreated = false;
//
//        String insertInvitation = "INSERT INTO invitation (invitation_id, user_id, event_unix_timestamp, event_location, event_guestnum) " +
//                "VALUES (?,?) RETURNING invitation_id;";
//        //FIXME I think we have to set this ID to the invitation we made
//        Long newInvitationId = jdbcTemplate.queryForObject(insertInvitation, Long.class,
//                invitation.getUserId(), invitation.getEventUnixTimestamp(), invitation.getEventLocation(), invitation.getEventGuestnum());
//
//        //FIXME Not sure what this is doing
//        Long[] tindrNumbers = new Long[3];
//        int counter = 0;
//        for (Restaurant restaurant : invitation.getRestaurants()) {
//            tindrNumbers[counter] = getRestaurantTindrNumber(restaurant);
//            counter++;
//        }
//
//        Long[] invitedGuests = new Long[6];
//        int counter2 = 0;
//        for (Guest guest : invitation.getGuests()) {
//            invitedGuests[counter2] = getGuestIdByGuestEmail(guest);
//            counter2++;
//        }
//
//        String insertInvitationRestaurant = "INSERT INTO invitation_restaurant (invitation_id, restaurant_tindr_number); " +
//                "VALUES (?,?);";
//        for (int i = 0; i < tindrNumbers.length; i++) {
//            jdbcTemplate.update(insertInvitationRestaurant, newInvitationId, tindrNumbers[i]);
//        }
//
//        String insertInvitationGuest = "INSERT INTO invitation_guests (invitation_id, guest_id); " +
//                "VALUES (?,?);";
//        for (int i = 0; i < invitedGuests.length; i++) {
//            jdbcTemplate.update(insertInvitationGuest, newInvitationId, invitedGuests[i]);
//        }
//        invitationCreated = true;
//        return invitationCreated;
//    }

    //HELPER METHOD FOR MAKING INVITATIONS
    @Override
    public Long getGuestIdByGuestEmail(Guest guest) {
        String sql = "SELECT guest_id FROM guests WHERE guest_email_address = ?";
        Long guestIdNumber;
        Long results = jdbcTemplate.queryForObject(sql, Long.class, guest.getGuestEmailAddress());
        if (results != null) {
            guestIdNumber = results;
        } else {
            JdbcGuestDao guestDao = new JdbcGuestDao(jdbcTemplate);
            guestDao.createGuest(guest);
            String select = "SELECT guest_id FROM guests WHERE guest_email_address = ?";
            //FIXME I think we have to set this ID to the guest we made
            guestIdNumber = jdbcTemplate.queryForObject(select, Long.class, guest.getGuestEmailAddress());
        }
        return guestIdNumber;
    }

    //HELPER METHOD FOR MAKING INVITATIONS
    @Override
    public Long getRestaurantTindrNumber(Restaurant restaurant) {
        String sql = "SELECT restaurant_tindr_number FROM restaurants WHERE restaurant_id = ?";
        Long tindrNumber;
        Long results = jdbcTemplate.queryForObject(sql, Long.class, restaurant.getRestaurantId());
        if (results != null) {
            tindrNumber = results;
        } else {
            JdbcRestaurantDao res = new JdbcRestaurantDao(jdbcTemplate);
            res.saveRestaurant(restaurant);
            String select = "SELECT restaurant_tindr_number FROM restaurants WHERE restaurant_id = ?";
            tindrNumber = jdbcTemplate.queryForObject(select, Long.class, restaurant.getRestaurantId());
        }
        return tindrNumber;
    }

    //Get invites by user Id
    @Override
    public Invitation[] getInvitationByUserId(Long userId) {
        List<Invitation> listOfInvitations = new ArrayList<>();
        String sql = "SELECT invitation_id, user_id, event_location, event_guestnum, event_unix_timestamp, restaurant_1_thumbup, restaurant_1_thumbdown, restaurant_2_thumbup, restaurant_2_thumbdown,restaurant_3_thumbup, restaurant_3_thumbdown " +
                "FROM invitation " +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Invitation invitation = mapRowToInvitation(results);
            invitation.setGuests(guestDao.getGuestsByInviteId(invitation.getInvitationId()));
            invitation.setRestaurants(restaurantDao.getRestaurantByInvitationId(invitation.getInvitationId()));
            listOfInvitations.add(invitation);
        }
        Invitation[] arrayOfInvitations = new Invitation[listOfInvitations.size()];
        return listOfInvitations.toArray(arrayOfInvitations);
    }

    public Invitation getInvitationByGuestEmailAddress(String guestEmailAddress) {
        Invitation invitation = new Invitation();
        String sql = "SELECT invitation.invitation_id, invitation.user_id, invitation.event_location, invitation.event_guestnum, invitation.event_unix_timestamp, " +
                "invitation.restaurant_1_thumbup, invitation.restaurant_1_thumbdown, invitation.restaurant_2_thumbup, invitation.restaurant_2_thumbdown," +
                "invitation.restaurant_3_thumbup, invitation.restaurant_3_thumbdown " +
                "FROM invitation " +
                "JOIN invitation_guests ON invitation.invitation_id = invitation_guests.invitation_id " +
                "JOIN guests ON invitation_guests.guest_id = guests.guest_id " +
                "WHERE guest_email_address = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, guestEmailAddress);
        if (results.next()) {
            invitation = mapRowToInvitation(results);
            invitation.setGuests(guestDao.getGuestsByInviteId(invitation.getInvitationId()));
            invitation.setRestaurants(restaurantDao.getRestaurantByInvitationId(invitation.getInvitationId()));
        }
//        invitation = jdbcTemplate.queryForObject(sql, Invitation.class, invitation.getInvitationId(), invitation.getUserId(),
//                invitation.getEventLocation(), invitation.getEventGuestnum(), invitation.getEventUnixTimestamp(),
//                invitation.getRestaurant_1_thumbsup(), invitation.getRestaurant_1_thumbsdown(), invitation.getRestaurant_2_thumbsup(),
//                invitation.getRestaurant_2_thumbsdown(),invitation.getRestaurant_3_thumbsup(), invitation.getRestaurant_3_thumbsdown());
//        invitation.setGuests(guestDao.getGuestsByInviteId(invitation.getInvitationId()));
//        invitation.setRestaurants(restaurantDao.getRestaurantByInvitationId(invitation.getInvitationId()));
        return invitation;
    }



    private Invitation mapRowToInvitation(SqlRowSet results) {
        Invitation invite = new Invitation();
        invite.setInvitationId(results.getLong("invitation_id"));
        invite.setUserId(results.getLong("user_id"));
        invite.setEventLocation(results.getString("event_location"));
        invite.setEventGuestnum(results.getInt("event_guestnum"));
        invite.setEventUnixTimestamp(results.getLong("event_unix_timestamp"));
        invite.setRestaurant_1_thumbsup(results.getInt("restaurant_1_thumbup"));
        invite.setRestaurant_1_thumbsdown(results.getInt("restaurant_1_thumbdown"));
        invite.setRestaurant_2_thumbsup(results.getInt("restaurant_2_thumbup"));
        invite.setRestaurant_2_thumbsdown(results.getInt("restaurant_2_thumbdown"));
        invite.setRestaurant_3_thumbsup(results.getInt("restaurant_3_thumbup"));
        invite.setRestaurant_3_thumbsdown(results.getInt("restaurant_3_thumbdown"));

        return invite;
    };

}

