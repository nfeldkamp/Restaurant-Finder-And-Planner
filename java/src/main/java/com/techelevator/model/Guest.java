package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Guest {

    private Long guestId;
    private String guestName;
    private String guestEmailAddress;
    private String guestInvitationLinkURL;
    private boolean isGuestInvitationLinkLive;

    public Guest() { }

    public Guest(Long guestId, String guestName, String guestEmailAddress, String guestInvitationLinkURL, boolean isGuestInvitationLinkLive) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEmailAddress = guestEmailAddress;
        this.guestInvitationLinkURL = guestInvitationLinkURL;
        this.isGuestInvitationLinkLive = isGuestInvitationLinkLive;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmailAddress() {
        return guestEmailAddress;
    }

    public void setGuestEmailAddress(String guestEmailAddress) {
        this.guestEmailAddress = guestEmailAddress;
    }

    public String getGuestInvitationLinkURL() {
        return guestInvitationLinkURL;
    }

    public void setGuestInvitationLinkURL(String guestInvitationLinkURL) {
        this.guestInvitationLinkURL = guestInvitationLinkURL;
    }

    public boolean isGuestInvitationLinkLive() {
        return isGuestInvitationLinkLive;
    }

    public void setGuestInvitationLinkLive(boolean guestInvitationLinkLive) {
        isGuestInvitationLinkLive = guestInvitationLinkLive;
    }

//   @Override
//   public String toString() {
//      return "Guest{" +
//              "id=" + guestId +
//              ", username='" + username + '\'' +
//              ", activated=" + activated +
//              ", authorities=" + authorities +
//              '}';
//   }
}
