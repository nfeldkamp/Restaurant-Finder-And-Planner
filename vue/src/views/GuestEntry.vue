<template>
  <div id="invitation-guests">
    <div>
      <div id="bg-img">
        <invitation-names-entry />
      </div>
    </div>
  </div>
</template>

<script>
import InvitationNamesEntry from "../components/InvitationNamesEntry";
export default {
  components: {
    InvitationNamesEntry,
  },
  data() {
    return { name: "guest-entry" };
  },
};
</script>


<style scoped>
#bg-img {
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