<template>
<div id='preferences'>
  <div>
    <div id="bg-img">
  <preferences />
    </div>
  </div>
</div>
</template>

<script>

import Preferences from '../components/Preferences.vue';

export default {
  components: {
    
    Preferences
  },
  data() {
    return {
      name: "preferences",
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
  height: 105vh;
  width: auto;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
</style>