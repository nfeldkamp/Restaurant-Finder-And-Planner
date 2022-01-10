<template>
  <div class="invitations-list">
    <div>
      <invitation-display
        v-for="invitation in this.$store.state.invitations"
        v-bind:key="invitation.id"
        v-bind:invitation="invitation"
      />
    </div>
  </div>
</template>

<script>
//import restaurantListService from "@/services/RestaurantListService.js";
import InvitationDisplay from "./InvitationDisplay";

export default {
  name: "invitations-list",
  data() {
    return {
      invitations: [],
    };
  },
  components: {
    InvitationDisplay,
  },
};
</script>
