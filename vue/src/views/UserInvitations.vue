<template>
  <div class="user-invites">
    <div id="inviteflex">
      <div id="list">
        <invitations-list />
      </div>
      <div id="details1" v-if="this.$store.state.displayedInvitation">
        <invitation-details
          v-bind:invitation="this.$store.state.displayedInvitation"
        />
      </div>
      <div id="details2" v-else>
        <invitation-details
          v-bind:invitation="this.$store.state.invitations[0]"
        />

        <!-- need v-else for not invitations-->
      </div>
    </div>
  </div>
</template>


<script>
import invitationsService from "../services/InvitationsService.js";
import InvitationsList from "../components/InvitationsList.vue";
import InvitationDetails from "../components/InvitationDetails.vue";
export default {
  name: "user-invitations",
  data() {
    return {
      invitations: [],
    };
  },
  components: {
    InvitationsList,
    InvitationDetails,
  },
  created() {
    invitationsService.getInvitations().then((response) => {
      this.invitations = response.data;
      this.$store.commit("UPDATE_INVITATIONS", this.invitations);
    });
  },
};
</script>

<style scoped>
#inviteflex {
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
  width: 60vw;
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