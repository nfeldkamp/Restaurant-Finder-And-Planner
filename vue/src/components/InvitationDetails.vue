<template>
  <div id="main">
    <div
      id="sizechange"
      class="invitationdetails"
      v-if="$route.name == 'guest-view'"
    >
      <div id="invitationdetails" v-bind:key="invitation.invitationId">
        <div id="invitation-info">
          <div id="location-time-date-guestview">
            Event Location: {{ invitation.eventLocation }}
            <!-- need method to parse date adn time from unixtimestamp -->
            <!-- <div id="invitation-date">
              {{ invitation.date }}
            </div>
            <div id="invitation-time">
              {{ invitation.time }}
            </div> -->
          </div>
          <div id="invitation-numguests-guestview">
            Guests:
            <div id="guest-name-flex">
              <div
                v-for="guest in invitation.guests"
                v-bind:key="guest.id"
                v-bind:guest="guest"
              >
                {{ guest.guestName }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="list">
        <restaurant-list-guest-view />
      </div>
    </div>
    <div
      id="sizechange"
      class="invitationdetailsuser"
      v-if="$route.path == '/invitations/user'"
    >
      <div id="invitation-details" v-bind:key="invitation.invitationId">
        <div id="invitation-info">
          <div id="location-time-date">
            <a
              href=""
              id="invitation-location"
              v-bind:invitation="invitation"
              v-on:click.prevent="updateDisplay(invitation)"
              >{{ invitation.eventLocation }}</a
            >
            <div id="invitation-date">
              {{ myDate }}
            </div>
            <!-- method to parse dat and time -->
            <!--<div id="invitation-date">
              {{ invitation.date }}
            </div>
            <div id="invitation-time">
              {{ invitation.time }}
            </div> -->
          </div>
          <div id="invitation-numguests">
            Guests:
            <div id="guest-name-flex">
              <div
                id="guest-names"
                v-for="guest in invitation.guests"
                v-bind:key="guest.id"
                v-bind:guest="guest"
              >
                {{ guest.guestName }}&nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="listuser">
        <restaurant-list />
      </div>
    </div>
  </div>
</template>
<script>
import RestaurantList from "../components/RestaurantList.vue";
import RestaurantListGuestView from "../components/RestaurantListGuestView.vue";
//src="../assets/mock-up-rest-img.jpg"
//v-bind:src="restaurant.image"
export default {
  name: "invitation-details",
  props: ["invitation"],
  data() {
    return {
      myDate: "",
    };
  },
  components: {
    RestaurantList,
    RestaurantListGuestView,
  },
  created() {
    this.myDate = new Date(
      this.invitation.eventUnixTimestamp * 1000
    ).toLocaleString();
    //document.write(myDate.toGMTString()+"<br>"+myDate.toLocaleString());
  },
  // created: {
  //   changeDisplaySize() {
  //     let thisElement = document.getElementById("div#invitationdetails");
  //     if (this.$store.state.fromguestentry) {
  //       thisElement.classList.add("invitationdetailsuser");
  //     } else {
  //       thisElement.classList.add("invitationdetails");
  //     }
  //   },
  //},
};
</script>

<style scoped>
/* manage size based on where it came from user or guestview*/
.invitationdetails {
  width: 32.5vw;
  height: 80vh;
  margin-top: 5px;
  display: flex;
  flex-direction: column;
  border-radius: 8px;
  border: 4px ridge darkgrey;
  background-color: lightgrey;
  /*border-style: ridge;*/
}

#guest-name-flex {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}
.invitationdetailsuser {
  width: 64vw;
  height: 85vh;
  margin-top: 5px;
  display: flex;
  flex-direction: column;
  border-radius: 8px;
  border: 4px ridge darkgrey;
  background-color: lightgrey;
  /*border-style: ridge;*/
}

#location-time-date,
#location-time-date-guestview {
  display: flex;
  align-items: center;
}

#location-time-date-guestview {
  font-size: 2vw;
  line-height: 2.4vw;
}

#invitation-location {
  margin-bottom: 1vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 3vw;
  line-height: 3vw;
}

#invitation-time {
  margin-bottom: 0.5vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 2vw;
}

#invitation-date {
  margin-bottom: 0.5vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 2vw;
}

#invitation-numguests {
  margin-bottom: 1vw;
  margin-left: 1vw;
  margin-top: 1vw;
  font-size: 3vw;
  line-height: 1vw;
}
/*
#listuser{
  display: flex;
  justify-content: center;
}*/

#invitation-numguests-guestview {
  font-size: 2vw;
  line-height: 2vw;
  margin-top: 1vw;
}

#guest-names {
  margin-top: 2vw;
  font-size: 2vw;
}

#detail-img {
  height: 40vh;
  width: 40vh;
  margin-left: 3px;
  margin-top: 8px;
  margin-right: 10px;
  flex-grow: 1;
}

img.detail-star {
  margin-bottom: 1vh;
  height: auto;
  width: 3vw;
}

.detail-rating {
  margin-top: 3vh;
}

#detail-price {
  margin-top: 1vh;
}

#detail-rest-type {
  margin-top: 0px;
  font-size: 3vh;
  line-height: 4vh;
  margin-left: 2px;
  margin-bottom: 1vh;
}

#detail-rest-add {
  margin-bottom: 0px;
  margin-top: 1vh;
}

#detail-rest-info {
  display: flex;
  flex-direction: column;
  flex-grow: 3;
}

#detail-rest-name {
  margin-bottom: 1vh;
  margin-top: 4vh;
  flex-direction: start;
  font-size: 10vh;
  line-height: 1.6vh;
}

#detail-phonehours {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-end;
  flex-grow: 1;
}

#detail-phonehours button {
  width: 8vw;
  border-radius: 8px;
  border: none;
  padding: 5px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}

button.detail-openbtn,
.detail-phonebtn,
.detail-closedbtn {
  height: 5vh;
}

button.detail-openbtn {
  background-color: lightblue;
}

button.detail-closedbtn {
  background-color: red;
}

#detail-phonehours img {
  margin-right: 2px;
  height: 2vh;
  width: 2vw;
}

#detail-phonehours p {
  margin-top: 4px;
  margin-bottom: 0px;
  margin-right: 10px;
}

#detail-hours {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin-top: 3vh;
  margin-bottom: 0px;
  line-height: 0px;
}

#hours-data {
  margin-top: 1vh;
}

#hours-data p {
  margin-top: 2.75vh;
  margin-bottom: 1vh;
}

#detail-pricerating {
  display: flex;
  flex-direction: column;
}
</style>
