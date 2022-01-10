<template>
  <div class="restaurant-list">
    <div v-if="$route.path == '/invitations/restaurants'">
      <!-- date - whatever 1971 + time in miliseconds?-->
      <div class="invitebuttons">
        <router-link router-link to="/invitations/guestentry">
          <div id="approvebtn">
            <button
              v-bind:invitation="this.invitation"
              v-on:click="updateFromInvitation()"
            >
              Approve choices
            </button>
          </div>
        </router-link>
        <router-link router-link to="/">
          <div id="cancelbtn">
            <button v-on:click="updateFromInvitation()">Cancel</button>
          </div>
        </router-link>
      </div>
      <restaurant-display
        v-for="restaurant in this.$store.state.invitation.restaurants"
        v-bind:key="restaurant.id"
        v-bind:restaurant="restaurant"
      />
    </div>
    <div v-else-if="$route.name == 'user-invitations'">
      <restaurant-display
        v-for="restaurant in this.$store.state.displayedInvitation.restaurants"
        v-bind:key="restaurant.id"
        v-bind:restaurant="restaurant"
      />
    </div>

    <div v-else>
      <restaurant-display
        v-for="restaurant in this.$store.state.restaurants"
        v-bind:key="restaurant.id"
        v-bind:restaurant="restaurant"
      />
    </div>
  </div>
</template>

<script>
//import restaurantListService from "@/services/RestaurantListService.js";
import RestaurantDisplay from "./RestaurantDisplay";

export default {
  name: "restaurant-list",
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
