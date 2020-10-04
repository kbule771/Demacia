<template>
  <div id="app">
    <Header msg="this is header" />
    <router-view />
  </div>
</template>

<script>
import Header from "./components/common/Header.vue";
import constants from "./lib/constants";

export default {
  name: "App",
  components: {
    Header,
  },
  data: function() {
    return {
      constants,
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#background {
  z-index: -1;
  position: fixed;
  width: 100%;
  height: 100%;
  background-image: url("../src/assets/img/bg4.jpg");
  background-position: top;

  background-size: cover;
}
</style>
