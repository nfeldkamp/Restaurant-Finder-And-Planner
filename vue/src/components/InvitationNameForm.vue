<template>
  <div>
    <input
      v-if="this.disabledbtn"
      type="text"
      placeholder="Please enter a Name"
      v-model="guest.guestName"
    />
    <input
      v-if="disabledbtn"
      type="text"
      v-model="guest.guestEmailAddress"
      placeholder="Please enter an email address"
    />
    <input
      class="displaylink"
      id="inputforlink"
      v-if="!disabledbtn"
      v-model="inputLink"
      type="text"
      readonly
    />
    <button
      v-if="disabledbtn"
      v-on:click="
        disabledbtn = !disabledbtn;
        makeGuestArray();
      "
    >
      Submit
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "invitation-name-form",
      disabledbtn: true,
      inputLink: "",
      guest: {},
    };
  },
  methods: {
    makeGuestArray() {
      //let thisInput = document.getElementById("inputforlink");
      this.inputLink = `http://localhost:8081/invitations/guestview/${this.guest.guestEmailAddress}`;
      //
      this.$store.commit("ADD_GUEST", this.guest);
    },
  },
};
</script>

<style scoped>
input {
  width: 14vw;
  height: 6vh;
  border-radius: 8px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 1vw;
  margin: 4px 2px;
}
button {
  padding-top: 2vh;
  height: 6vh;
}
.displaylink {
  width: 32vw;
}
</style>