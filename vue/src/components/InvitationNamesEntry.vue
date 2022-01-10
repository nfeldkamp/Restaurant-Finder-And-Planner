<template>
  <div id="main">
    <h1 class="info">Please enter your guests names and emails:</h1>
    <invitation-name-form
      class="guestinput"
      v-for="guest in parseInt(this.$store.state.invitation.eventGuestnum)"
      v-bind:key="guest.id"
    />
    <!-- <router-link to="../invitations/user"> -->
    <button id="proceedbtn" v-on:click="sendTheInvite()">
      Proceed To Invite Page
    </button>
    <!--</router-link>-->
  </div>
</template>

<script>
import InvitationNameForm from "./InvitationNameForm.vue";
import invitationsService from "../services/InvitationsService.js";
export default {
  components: { InvitationNameForm },
  data() {
    return {
      name: "invitation-detail-entry",
      disabledbtn: true,
      invitation: {
        restaurants: [],
        guests: [],
      },
    };
  },
  methods: {
    sendTheInvite() {
      invitationsService
        .buildTheMonster(this.$store.state.invitation)
        .then((response) => {
          this.invitation = response.data;
          this.$store.commit("UPDATE_INVITATION", this.invitation);
          if (response.status == 200) {
            this.$router.push("/invitations/user");
          }
        });
    },
    updateFromGuestEntry() {
      this.$store.commit("UPDATE_FROM_GUEST_ENTRY");
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
  align-items: center;
  height: 87vh;
  flex-grow: 1;
}

#proceedbtn {
  width: 20vw;
}

.info {
  color: white;
  text-align: center;
}

input {
  width: 14vw;
  height: 8vh;
  border-radius: 8px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}

.guestinput {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
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