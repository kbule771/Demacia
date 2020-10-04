<template>
  <div id="a" style="font-family:Tmon">
    <nav
      class="navbar navbar-expand-lg navbar-dark fixed-top"
      id="mainNav"
      style="opacity:0.8; background: linear-gradient(180deg, rgba(32,17,95,1) 0%, rgba(14,36,56,1) 100%);"
    >
      <div class="container">
        <a
          class="navbar-brand mx-0"
          href="/"
          style="padding-top:5px; font-size:2em;"
        >
          <img src="../../assets/img/damacia.png" alt="" style="width:200px" />
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target=".dual-collapse2"
          style="margin-left:50px; border:none;"
        >
          <b-icon icon="three-dots" animation="cylon" font-scale="2.5"></b-icon>
        </button>
        <div
          class="navbar-collapse collapse w-100 order-3 dual-collapse2 text-right"
        >
          <ul class="navbar-nav ml-auto" style="font-size:2.5hv" v-if="isLogin">
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                data-toggle="dropdown"
                href="#"
                >추천</a
              >
              <div
                class="dropdown-menu text-right"
                style="background-color: black;"
              >
                <router-link
                  v-bind:to="{ name: constants.URL_TYPE.RECOMMEND.CHAMPION }"
                  class="dropdown-item"
                  >챔피언 추천</router-link
                >
                <router-link
                  v-bind:to="{ name: constants.URL_TYPE.RECOMMEND.COMBINATION }"
                  class="dropdown-item"
                  >조합 추천</router-link
                >
              </div>
            </li>
            <li class="nav-item">
              <router-link
                :to="{ name: constants.URL_TYPE.POST.MAIN }"
                class="nav-link"
                >투표</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                :to="{ name: constants.URL_TYPE.RECOMMEND.DJANGOTEST }"
                class="nav-link"
                >장고테스트</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                :to="{ name: constants.URL_TYPE.VIDEO.MAIN }"
                class="nav-link"
                >영상분석</router-link
              >
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                data-toggle="dropdown"
                href="#"
              >
                <b-avatar
                  variant="secondary"
                  :src="userImage"
                  size="1.8em"
                  class="mr-2"
                ></b-avatar>
                {{ user.userNickname }}
              </a>
              <div
                class="dropdown-menu text-right"
                style="background-color: black;"
              >
                <router-link
                  v-bind:to="{ name: constants.URL_TYPE.USER.MYPROFILE }"
                  class="dropdown-item"
                  >내 프로필</router-link
                >
                <a @click="logout" class="dropdown-item">로그아웃</a>
              </div>
            </li>
          </ul>
          <ul class="navbar-nav ml-auto" style="font-size:2.5hv" v-else>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                data-toggle="dropdown"
                href="#"
                >추천</a
              >
              <div
                class="dropdown-menu text-right"
                style="background-color: black;"
              >
                <router-link
                  v-bind:to="{ name: constants.URL_TYPE.RECOMMEND.CHAMPION }"
                  class="dropdown-item"
                  >챔피언 추천</router-link
                >
                <router-link
                  v-bind:to="{ name: constants.URL_TYPE.RECOMMEND.COMBINATION }"
                  class="dropdown-item"
                  >조합 추천</router-link
                >
              </div>
            </li>
            <li class="nav-item">
              <router-link
                :to="{ name: constants.URL_TYPE.POST.MAIN }"
                class="nav-link"
                >투표</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                :to="{ name: constants.URL_TYPE.VIDEO.MAIN }"
                class="nav-link"
                >영상분석</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                v-bind:to="{ name: constants.URL_TYPE.USER.LOGIN }"
                class="nav-link"
                >로그인</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                v-bind:to="{ name: constants.URL_TYPE.USER.JOIN }"
                class="nav-link"
                >회원가입</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div style="height:74px">.</div>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import axios from "axios";

export default {
  name: "Header",
  data() {
    return {
      constants,
      user: {},
    };
  },
  computed: {
    userImage() {
      return process.env.VUE_APP_IMGUP_URL + this.user.userImage;
    },
    isLogin() {
      return this.user;
    },
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
  },
  methods: {
    logout() {
      if (!this.user.accessToken) {
        console.log("not accesstoken");
        sessionStorage.removeItem("user");
        location.href = "/";
      } else if (this.user.providerName == "GOOGLE") {
        axios
          .get(process.env.VUE_APP_API_URL + "/logout/google", {
            params: {
              token: this.user.accessToken,
            },
          })
          .then(() => {
            sessionStorage.removeItem("user");
            location.href = "/";
          })
          .catch((err) => {
            location.href =
              "/error/로그아웃 중 서버 오류가 발생했습니다. " + err;
          });
      } else {
        axios
          .get(process.env.VUE_APP_API_URL + "/logout/oauth", {
            params: {
              accesstoken: this.user.accessToken,
            },
          })
          .then(() => {
            sessionStorage.removeItem("user");
            location.href = "/";
          })
          .catch((err) => {
            location.href =
              "/error/로그아웃 중 서버 오류가 발생했습니다. " + err;
          });
      }
    },
  },
};
</script>
<style scoped>
.dropdown-item {
  color: #e3d19e !important;
}
.dropdown-item:hover {
  color: inherit !important;
}
@font-face {
  font-family: "Tmon";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "digital";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/LAB디지털.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
