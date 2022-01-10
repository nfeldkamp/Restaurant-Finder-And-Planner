<template>
  <div id="invitation-page">
    <div>
      <div id="bg-img">
        <invitation-detail-entry />
      </div>
    </div>
  </div>
</template>

<script>
import InvitationDetailEntry from "../components/InvitationDetailEntry";
export default {
  components: {
    InvitationDetailEntry,
  },
  data() {
    return {
      name: "invitations",
    };
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