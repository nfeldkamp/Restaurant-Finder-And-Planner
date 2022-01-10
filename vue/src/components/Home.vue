<template>
  <div id="main">
    <div class="home">
      <h1>Home</h1>
      <h2>Welcome, Member!</h2>
      <button class="btn-night-out" @click="$router.push('invitations')">
        Plan Your Night Out With Friends
      </button>
      <button class="btn-invite" @click="$router.push('invitations/user')">
        See Your Current Invitations List
      </button>
      <div id="search">
        <input
          id="searcharea"
          type="text"
          placeholder="Please enter a zipcode or City and State"
        />
        <router-link to="/restaurants/">
          <button
            id="restaurantbtn"
            v-on:click="getRestaurants"
            v-bind:restaurants="this.restaurants"
          >
            Search
          </button>
        </router-link>
      </div>
      <router-link id="preference" :to="{ name: 'preferences' }"
        >Have a Favorite Type of Food? Add It Here.</router-link
      >
    </div>
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService";
export default {
  name: "home",
  data() {
    return {
      restaurants: [],
    };
  },
  methods: {
    getRestaurants() {
      //format searchArea before sending? - like this?
      let search = document.getElementById("searcharea");
      let thisSearch = `location=${search.value}&term=restaurants`;
      restaurantService.getRestaurants(thisSearch).then((response) => {
        this.restaurants = response.data;
        this.$store.commit("UPDATE_RESTAURANTS", this.restaurants);
      });
    },
  },
};
</script>

<style scoped>
div.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 60vh;
  flex-grow: 1;
}

h1 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: white;
  text-align: center;
  font-size: 55px;
}

h2 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 35px;
  color: white;
  text-align: center;
}

#search {
  display: flex;
  flex-direction: row;
  justify-content: center;
  height: 40vh;
  margin-top: 2%;
}

#search input,
#restaurantbtn {
  height: 4vw;
}

button.btn-night-out {
  margin-top: 0.5vw;
  margin-left: auto;
  margin-right: auto;
  height: 6vw;
}

button.btn-invite {
  margin-top: 2vw;
  margin-left: auto;
  margin-right: auto;
  height: 6vw;
}

#preference {
  color: white;
  text-align: center;
  font-size: 30px;
  text-decoration: none;
}
</style>