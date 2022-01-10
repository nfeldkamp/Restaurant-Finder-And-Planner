<template>
<div id='login'>
  <div>
    <div id="bg-img">
  <login-register />
    </div>
  </div>
</div>
</template>

<script>
import LoginRegister from '@/components/LoginRegister';

export default {
  components: {
    LoginRegister
  },
  data() {
    return {
      name: "login",
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
