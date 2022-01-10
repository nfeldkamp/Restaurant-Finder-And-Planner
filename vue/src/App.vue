<template>
  <div id="app">
    <page-header id="page-header" />
    <router-view id="router-view" />
    <!--
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    -->
  </div>
</template>
<script>
import PageHeader from "@/components/PageHeader.vue";
export default {
  components: {
    PageHeader,
  },
};
</script>
<style>
/* div {
  display: inline-block;
} */

#router-view {
  max-height: 88vh;
}

#app {
  background-color: rgb(128, 198, 221);
  width: 100%;
  height: 88vh;
}

button {
  width: 14vw;
  height: 8vh;
  border-radius: 8px !important;
  border: none;
  background-color: green;
  padding: 20px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}
</style>