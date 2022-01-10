<template>
  <div class="restaurant-list">
    <restaurant-display
      v-for="restaurant in this.$store.state.invitation.restaurants"
      v-bind:key="restaurant.id"
      v-bind:restaurant="restaurant"
    />
  </div>
</template>

<script>
//import restaurantListService from "@/services/RestaurantListService.js";
import RestaurantDisplay from "./RestaurantDisplay";

export default {
  name: "restaurant-list-guest-view",
  data() {
    return {
      userInvitation: {},
      invitation: { restaurants: [], guests: [] },
      restaurants: [],
    };
  },
  methods: {
    updateFromInvitation() {
      this.$store.commit("UPDATE_FROM_INVITATION");
    },
  },
  components: {
    RestaurantDisplay,
  },
};
</script>

<style>
.invitebuttons {
  display: flex;
  justify-content: space-evenly;
}
</style>
