<template>
  <div class="jumbotron">
    <h2 class="display-3">
      🤨
      <br />잠시만요...
    </h2>
    <p class="lead">뭔가 잘못됐어요.</p>
    <hr class="my-4" />
    <p>{{this.$route.params.msg}}</p>
    <p class="lead">
      <span class="btn btn-primary btn-lg" @click="back" role="button">뒤로</span>
    </p>
  </div>
</template>
<script>
import router from "../../router";
export default {
  created() {
    window.scrollTo(0, 0);
  },
  methods: {
    back() {
      router.go(-1);
    },
  },
  data() {
    return {};
  },
};
</script>
<style scoped>
.jumbotron {
  background-color: rgba(255, 255, 255, 0.4);
}
</style>