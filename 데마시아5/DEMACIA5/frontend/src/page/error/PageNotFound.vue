<template>
  <div class="jumbotron">
    <h2 class="display-3">
      😥
      <br />찾으시는 페이지가 없어요...
    </h2>
    <p class="lead">뒤로 가서 다시 시도해보세요.</p>
    <hr class="m-4" />
    <p>다시 시도해도 여기로 오면 관리자에게 문의해보세요.</p>
    <p class="lead">
      <router-link class="btn btn-primary btn-lg" to="/" role="button">메인 페이지</router-link>
    </p>
  </div>
</template>
<script>
export default {
  created() {
    window.scrollTo(0, 0);
  },
};
</script>
<style scoped>
.jumbotron {
  background-color: rgba(255, 255, 255, 0.4);
}
</style>