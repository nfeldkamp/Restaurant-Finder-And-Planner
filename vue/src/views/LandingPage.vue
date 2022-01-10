<template>
  <div id="landingPage">
    <div>
      <div id="bg-img">
        <landing-restaurant-search />
      </div>
    </div>
  </div>
</template>

<script>
import LandingRestaurantSearch from "../components/LandingRestaurantSearch";
export default {
  components: {
    LandingRestaurantSearch,
  },
  data() {
    return {
      name: "landing-page",
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