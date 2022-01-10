<template>
  <div id="header">
    <div id="headstyle">
      <router-link router-link to="/">
        <img src="../assets/logo.png" />
      </router-link>
      <h1 @click="$router.push('/')">Food With Friends</h1>
      <router-link v-bind:to="{ name: 'login' }">
        <button id="loginButton" v-if="$store.state.token == ''">Login</button>
      </router-link>
      <router-link
        v-if="$store.state.token != ''"
        v-bind:to="{ name: 'logout' }"
        ><button id="loginButton" v-if="$store.state.token != ''">
          Logout
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "page-header",
    };
  },
};
</script>

<style scoped>
div#headstyle {
  display: flex;
  height: 14vh;
  justify-content: space-between;
  flex-grow: 1;
  background-color: lightgray;
  align-items: center;
  width: auto;
}

div#headstyle img {
  margin-left: 0.5vw;
  max-height: 6.5vw;
  max-width: 6.5vw;
}

div#headstyle h1 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: #251879;
  font-size: 4vw;
  align-items: center;
  padding-left: 9vw;
}

@media only screen and (max-width: 600px) {
  div#headstyle {
    display: none;
  }
}
</style>