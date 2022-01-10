<template>
  <div class="invitation" v-bind:key="invitation.invitationId">
    <div id="invitation-info">
      <!-- <router-link to="/restaurant/{restaurant.id}"> something like this for mobile version, hidden if browser-->
      <a
        href=""
        id="invitation-location"
        v-on:click.prevent="updateDisplay(invitation)"
        >{{ invitation.eventLocation }}</a
      ><br />
      <div id="invitation-date">
        {{ myDate }}
      </div>
      <!--<div id="timedate">
        <div id="invitation-date">
          {{ invitation.date }}
        </div>
        <div id="invitation-time">
          {{ invitation.time }}
        </div> 
      </div>-->
      <div id="invitation-numguests">
        Number of guests: {{ invitation.eventGuestnum }}
      </div>
    </div>
  </div>
</template>

<script>
//src="../assets/mock-up-rest-img.jpg"
//v-bind:src="restaurant.image"
export default {
  name: "invitation-display",
  props: ["invitation"],
  methods: {
    updateDisplay(invitation) {
      this.$store.commit("UPDATE_DISPLAY_INVITATION", invitation);
      this.$store.commit("UPDATE_RESTAURANTS", invitation.restaurants);
    },
  },
  created() {
    this.myDate = new Date(
      this.invitation.eventUnixTimestamp * 1000
    ).toLocaleString();
    //document.write(myDate.toGMTString()+"<br>"+myDate.toLocaleString());
  },
};
</script>

<style scoped>
.invitation {
  width: 32.5vw;
  height: 10vw;
  margin-top: 5px;
  display: flex;
  border-radius: 8px;
  border: 4px ridge darkgrey;
  background-color: lightgrey;
  /*border-style: ridge;*/
}

#timedate {
  display: flex;
}

#rest-info {
  display: flex;
  flex-direction: column;
  flex-grow: 3;
}

#invitation-date {
  margin-bottom: 0.5vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 2vw;
  line-height: 3vw;
}

#invitation-location {
  margin-bottom: 1vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 3vw;
  line-height: 3vw;
}

#invitation-time {
  margin-bottom: 1vw;
  margin-left: 1vw;
  margin-top: 0px;
  font-size: 2vw;
  line-height: 3vw;
}

#invitation-numguests {
  margin-bottom: 1vw;
  margin-left: 1vw;
  margin-top: 1vw;
  font-size: 2vw;
  line-height: 0.3vw;
}
</style>
