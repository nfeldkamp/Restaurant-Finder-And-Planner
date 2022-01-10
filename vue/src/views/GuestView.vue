<template>
  <div class="guest-view">
    <div id="guestflex">
      <div id="list">
        <invitation-details v-bind:invitation="this.$store.state.invitation" />
      </div>
      <div id="details1" v-if="this.$store.state.displayedRestaurant">
        <restaurant-details
          v-bind:restaurant="this.$store.state.displayedRestaurant"
        />
      </div>
      <div id="details2" v-else>
        <restaurant-details
          v-bind:restaurant="this.$store.state.invitation.restaurants[0]"
        />
      </div>
    </div>
  </div>
</template>


<script>
import InvitationDetails from "../components/InvitationDetails.vue";
import RestaurantDetails from "../components/RestaurantDetails.vue";
import guestService from "../services/GuestService";
export default {
  name: "guest-view",
  components: {
    InvitationDetails,
    RestaurantDetails,
  },
  data() {
    return {
      invitation: {},
    };
  },
  created() {
    guestService
      .getInvitationByGuestEmail(this.$route.params.guestEmail)
      .then((response) => {
        this.invitation = response.data;
        this.$store.commit("UPDATE_INVITATION", this.invitation);
      });
  },
};
</script>

<style scoped>
#list {
  flex-grow: 1;
  width: 30vw;
}

#details1,
#details2 {
  flex-grow: 2;
  width: 59vw;
}

#guestflex {
  display: flex;
  align-content: flex-start;
}

#guestflex {
  background-image: linear-gradient(
      90deg,
      rgba(47, 45, 65, 0.7),
      rgba(47, 45, 65, 0.7)
    ),
    url("../assets/bgimagelanding.jpg");
  height: 86vh;
  width: 97vw;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
</style>