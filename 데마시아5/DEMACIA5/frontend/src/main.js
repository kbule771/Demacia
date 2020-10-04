import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from './vuex/store'

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import InfiniteLoading from "vue-infinite-loading";

Vue.use(InfiniteLoading, {
  slots: {
    noResults: "하나도 없어요. 추가해주세요!",
    noMore: "끝!"
  }
});

// Install BootstrapVue
Vue.use(BootstrapVue);

// Install BootstrapVue icon
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
