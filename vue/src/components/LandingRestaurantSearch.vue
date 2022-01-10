<template>
  <div id="main">
    <h1 class="welcome">Discover Your New Favorite Restaurant</h1>
    <h2 class="info">
      Let us help you plan your next night out. Short list your choices. Send a
      link to your friends and they'll let you know their favorites.
    </h2>
    <div id="search">
      <input
        id="searcharea"
        type="text"
        placeholder="Please enter a zipcode or City and State"
      />
      <router-link to="/restaurants/">
        <button
          v-on:click="getRestaurants"
          v-bind:restaurants="this.restaurants"
        >
          Search
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
      name: "landing-restaurant-search",
      data() {
        return {
          restaurants: [],
          invitation: {},
        };
      },
    };
  },
  methods: {
    getRestaurants() {
      //format searchArea before sending? - like this?
      let search = document.getElementById("searcharea");
      let thisSearch = `term=restaurants&location=${search.value}`;
      restaurantService.getRestaurants(thisSearch).then((response) => {
        this.restaurants = response.data;
        this.$store.commit("UPDATE_RESTAURANTS", this.restaurants);
        this.$store.commit("UPDATE_DISPLAY", this.$store.state.restaurants[0]);
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

h1 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: white;
  text-align: center;
  font-size: 60px;
}

h2 {
  font-family: "Courier New", Courier, monospace;
  font-size: 40px;
}

#search {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.info {
  color: white;
  text-align: center;
}

input {
  width: 28vw;
  height: 8vh;
  border-radius: 8px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}
</style>