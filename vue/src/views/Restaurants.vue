<template>
  <div class="restaurants">
    <div id="restaurantflex">
      <div id="list">
        <restaurant-list />
      </div>
      <div id="details1" v-if="this.$store.state.displayedRestaurant">
        <restaurant-details
          v-bind:restaurant="this.$store.state.displayedRestaurant"
        />
      </div>
      <div id="details2" v-else>
        <restaurant-details
          v-bind:restaurant="this.$store.state.restaurants[0]"
        />
      </div>
    </div>
  </div>
</template>

<script>
import RestaurantList from "../components/RestaurantList.vue";
import RestaurantDetails from "../components/RestaurantDetails.vue";
export default {
  name: "restaurants",
  components: {
    RestaurantList,
    RestaurantDetails,
  },
};
</script>

<style scoped>
#restaurantflex {
  display: flex;
  width: 100vw;
}

#list {
  flex-grow: 1;
  width: 30vw;
}

#details1,
#details2 {
  flex-grow: 2;
  width: 63vw;
}

#restaurantflex {
  background-image: linear-gradient(
      90deg,
      rgba(47, 45, 65, 0.7),
      rgba(47, 45, 65, 0.7)
    ),
    url("../assets/bgimagelanding.jpg");
  height: 86vh;
  width: auto;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
</style>