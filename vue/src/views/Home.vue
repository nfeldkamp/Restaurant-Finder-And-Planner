<template>
  <div id="home">
    <div>
      <div id="bg-img">
        <home />
      </div>
    </div>
  </div>
</template>

<script>
import Home from "../components/Home";
export default {
  components: {
    Home,
  },
  data() {
    return {
      name: "home",
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
