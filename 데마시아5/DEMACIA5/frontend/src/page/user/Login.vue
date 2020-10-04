<template>
  <div class="block1 d-flex">
    <div class="main" style="width: 100%;">
      <div class="col-xl-4 col-lg-4 col-md-8 col-sm-12 m-auto">
        <div class="login-form " style="padding: 50px; opacity:0.9; ">
          <img
            src="../../assets/img/damacia.png"
            alt="logo2"
            style="width: 90%; margin-bottom: 3em; margin-left: 15px"
            draggable="false"
          />
          <div class="form-group">
            <b-form-input
              type="text"
              placeholder="이메일"
              id="email"
              size="lg"
              style="
                width:80%;
                margin-right: auto;
                margin-left: auto;
                margin-bottom: 5%;
                border-style:ridge;
                border: #fcd000  4px ridge;
                background-color:#1e2328;
                color:#cdbe91;

              "
              v-model="email"
              :state="emailValid"
            />
          </div>
          <div class="form-group">
            <b-form-input
              type="password"
              placeholder="비밀번호"
              id="password"
              v-model="password"
              size="lg"
              style="
                width: 80%;
                margin-right: auto;
                margin-left: auto;
                margin-bottom: 5%;
                border-style:ridge;
                border: #fcd000  4px ridge;
                background-color:#1e2328;
                color:#cdbe91;
              "
              :state="pwValid"
              @keydown.enter="loginCheck"
            />
          </div>
          <div
            @click="loginCheck"
            class="btn btn-lg hoverbtn"
            style="width: 80%; margin-bottom: 5%; color:white;background: linear-gradient(180deg, #1b2831 0%, #12384b 100%); border-style:ridge; border:#95ede7 3px ridge;"
          >
            로그인
          </div>
          <div
            @click="signuppage"
            class="btn btn-lg  hoverbtn"
            style="width: 80%; margin-bottom: 2%; color:white;background: linear-gradient(180deg, #1b2831 0%, #12384b 100%); border-style:ridge; border:#95ede7 3px ridge;"
          >
            회원가입
          </div>
          <img
            src="../../assets/img/kakao1.png"
            class="btn image"
            style="width: 87%;border-radius: 20px;"
            alt="kakaologo"
            draggable="false"
            @click="kakao"
          />
          <img
            src="../../assets/img/google1.png"
            class="btn image"
            style="width: 87%;border-radius: 20px;"
            alt="naverlogo"
            @click="google"
            draggable="false"
          />
        </div>
      </div>
    </div>
    <img id="profile" :src="newImg" />
  </div>
</template>

<script>
import "../../assets/css/user.css";
import axios from "axios";
import * as EmailValidator from "email-validator";

export default {
  name: "LoginView",

  data() {
    return {
      email: "",
      password: "",
      emailValid: null,
      pwValid: null,
      kakaologo: process.env.VUE_APP_IMGUP_URL + "/images/login/kakaologo.PNG",
      naverlogo: process.env.VUE_APP_IMGUP_URL + "/images/login/googlelogo.png",

      newImg: "",
    };
  },

  created() {
    window.scrollTo(0, 0);
    if (this.$route.query.scope) {
      console.log("goolelogin:" + this.$route.query.code);
      axios
        .get(process.env.VUE_APP_API_URL + "/login/google", {
          params: {
            code: this.$route.query.code,
            redirectUrl: process.env.VUE_APP_BASE_URL + "/login",
          },
        })
        .then((res) => {
          if (res.data.status) {
            res.data.object.userPw = "";
            this.$store.commit("addUserInfo", res.data.object);
            // 세션에 로그인 정보 추가
            sessionStorage.setItem("user", JSON.stringify(res.data.object));
            location.href = "/";
          } else {
            this.emailValid = this.pwValid = false;
            this.errToast("구글 아이디 또는 비밀번호를 확인해주세요");
          }
        })
        .catch((err) => {
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
        });
    } else if (this.$route.query.code) {
      console.log(this.$route.query.code);
      axios
        .get(process.env.VUE_APP_API_URL + "/login/oauth", {
          params: {
            code: this.$route.query.code,
            redirectUrl: process.env.VUE_APP_BASE_URL,
          },
        })
        .then((res) => {
          if (res.data.status) {
            res.data.object.userPw = "";
            if (res.data.object.userImage) {
              var canvas = document.createElement("CANVAS");
              canvas.getContext("2d");
              const fd = new FormData();
              fd.append(
                "upLoadImage",
                canvas.toBlob(function(blob) {
                  var newImg = document.getElementById("profile");
                  var url = URL.createObjectURL(blob);
                  newImg.onload = function() {
                    URL.revokeObjectURL(url);
                  };
                  newImg.src = url;
                  document.body.appendChild(newImg);
                }),
                res.data.object.userNo + ".jpg"
              );
              axios
                .post(process.env.VUE_APP_IMGUP_URL + "/upload", fd, {
                  headers: {
                    "Content-Type": "multipart/form-data",
                  },
                })
                .then(() => {
                  console.log("succes");
                  res.data.object.userImage =
                    "/images/" + res.data.object.userNo + ".jpg";
                });
            }
            this.$store.commit("addUserInfo", res.data.object);
            // 세션에 로그인 정보 추가
            sessionStorage.setItem("user", JSON.stringify(res.data.object));
            location.href = "/";
          } else {
            this.emailValid = this.pwValid = false;
            this.errToast("아이디 또는 비밀번호를 확인해주세요");
          }
        })
        .catch((err) => {
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
        });
    }
  },

  methods: {
    kakao() {
      location.href =
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
        "121c6ba38cce9dba0c6843a59450b6fd" +
        "&redirect_uri=" +
        process.env.VUE_APP_BASE_URL +
        "/login&response_type=code";
    },

    google() {
      location.href =
        "https://accounts.google.com/o/oauth2/v2/auth?" +
        "scope=https://www.googleapis.com/auth/userinfo.email&https://www.googleapis.com/auth/userinfo.profile&openid" +
        "include_granted_scopes=true&" +
        "response_type=code&" +
        "state=state_parameter_passthrough_value&" +
        "redirect_uri=" +
        process.env.VUE_APP_BASE_URL +
        "/login&" +
        "client_id=1023963510057-bijvog3gfp162178b7iqu978ruruqkq2.apps.googleusercontent.com";
    },

    loginCheck() {
      let err = false;
      this.emailValid = !(
        (!this.email &&
          (this.errToast("이메일을 입력해주세요"), (err = true))) ||
        (this.email &&
          !EmailValidator.validate(this.email) &&
          (this.errToast("메일 형식을 맞춰주세요"), (err = true)))
      );
      this.pwValid = !(
        !this.password &&
        (this.errToast("비밀번호를 입력해주세요"), (err = true))
      );
      if (!err) this.dataSend();
    },

    signuppage() {
      location.href = "/join";
    },
    errToast(msg) {
      this.$bvToast.toast(msg, {
        title: "로그인 오류",
        toaster: "b-toaster-top-center",
        solid: true,
        appendToast: false,
        variant: "primary",
      });
    },
    dataSend() {
      axios
        .get(process.env.VUE_APP_API_URL + "/account/login", {
          params: {
            userEmail: this.email,
            userPw: this.password,
          },
        })
        .then((res) => {
          if (res.data.status) {
            res.data.object.userPw = "";
            this.$store.commit("addUserInfo", res.data.object);
            console.log(this.$store.state.userInfo);
            // 세션에 로그인 정보 추가
            sessionStorage.setItem("user", JSON.stringify(res.data.object));
            location.href = "/";
          } else {
            this.emailValid = this.pwValid = false;
            this.errToast("아이디 또는 비밀번호를 확인해주세요");
          }
        })
        .catch((err) => {
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
          window.location.reload();
        });
    },
  },
};
</script>

<style>
.hoverbtn:hover {
  opacity: 0.8;
}
.image:hover {
  opacity: 0.9;
}
</style>
