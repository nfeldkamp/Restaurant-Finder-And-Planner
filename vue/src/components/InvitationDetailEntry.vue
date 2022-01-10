<template>
  <div id="main">
    <h1 class="info">Plan your night out. Please enter your choices:</h1>
    <div id="search">
      <input
        v-model="invitation.eventLocation"
        type="text"
        id="locationinput"
        placeholder="Please enter a zipcode or City and State"
      />
      <div id="inputdate">
        <div class="inputtext">Choose your day:</div>
        <input id="datefrominput" type="date" v-model="invitation.date" />
      </div>
      <div id="inputtime">
        <div class="inputtext">Choose your time:</div>
        <input type="time" v-model="invitation.time" />
      </div>
      <div id="inputnumguests">
        <div class="inputtext">Number of guests:</div>
        <select
          name="numofguests"
          id="numguests"
          v-model="invitation.eventGuestnum"
        >
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
        </select>
      </div>
      <router-link
        v-bind:to="{ name: 'invitations-restaurants', params: { invitation } }"
      >
        <button
          v-bind:invitation="invitation"
          v-on:click="
            updateInvitation();
            getRestaurants();
          "
        >
          Plan your night out
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService";
export default {
  data() {
    return {
      name: "invitation-detail-entry",
      invitation: { restaurants: [], guests: [] },
    };
  },
  methods: {
    updateInvitation() {
      let thisDate = new Date(
        `${this.invitation.date} ${this.invitation.time}`
      );
      let otherEpoch = thisDate.getTime() / 1000.0;
      this.invitation.eventUnixTimestamp = otherEpoch;
      this.$store.commit("UPDATE_INVITATION", this.invitation);
    },
    getRestaurants() {
      //format searchArea before sending? - like this?
      let thisDate = new Date(
        `${this.invitation.date} ${this.invitation.time}`
      );
      let myEpoch = thisDate.getTime() / 1000.0;
      let thisSearch = `location=${this.invitation.eventLocation}&open_at=${myEpoch}&term=restaurant`;
      restaurantService.getRestaurants(thisSearch).then((response) => {
        this.invitation.restaurants = response.data;
        this.$store.commit(
          "UPDATE_RESTAURANTS_INVITATION",
          this.invitation.restaurants
        );
      });
    },
  },
};

// <div v-if="!isMobile()">
//   <desktop>
//   </desktop>
// </div>
// <div v-else>
//   <mobile>
//   </mobile>
// </div>
// And methods:
// methods: {
//  isMobile() {
//    if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
//      return true
//    } else {
//      return false
//    }
//  }
// }
</script>

<style scoped>
div #main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 87vh;
  flex-grow: 1;
}

#search {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.info {
  color: white;
  text-align: center;
}

input {
  width: 14vw;
  height: 6vh;
  border-radius: 8px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}

#locationinput {
  width: 28vw;
  height: 8vh;
  border-radius: 8px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}

.inputtext {
  margin-top: 1vh;
  color: white;
}

#inputnumguests {
  display: flex;
  margin-top: 1vh;
  margin-bottom: 1vh;
}

#numguests {
  margin-left: 0.5vw;
  margin-top: 0.5vh;
}
</style>