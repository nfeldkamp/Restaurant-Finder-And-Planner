<template>
  <div id="register">
    <div>
      <div id="bg-img">
        <register />
      </div>
    </div>
  </div>
</template>

<script>
import Register from "@/components/Register";

export default {
  components: {
    Register,
  },
  data() {
    return {
      name: "register",
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
