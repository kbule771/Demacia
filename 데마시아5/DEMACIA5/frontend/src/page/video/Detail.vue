<template>
  <div
    class="pb-2"
    style="background-color:white; width:90%; margin-left:auto; margin-right:auto;font-family:Tmon"
  >
    <div
      class="d-flex justify-content-between"
      style="margin-right:1%;margin-left:1%; margin-top:1%;"
    >
      <b-container class="bv-example-row" style="padding:0;margin-top:3%;">
        <b-row
          style="max-height:475px; width:100%; border-top:solid; border-bottom:solid; margin:0; "
        >
          <b-col
            cols="3"
            lg="3"
            xl="3"
            style="text-align:left; padding-top:2%; padding-left:1%;"
            class="d-flex"
          >
            <p id="hide3" style="font-size:3vh;  padding-left:7%;">의뢰번호</p>
            <p style="font-weight: bold; font-size:3.5vh; padding-left:5px; ">NO.{{ videoNo }}</p>
          </b-col>
          <b-col
            cols="9"
            lg="5"
            xl="6"
            class="d-flex color1 justify-content-center"
            style="padding-top:3%;"
          >
            <p
              style="font-weight: bold;font-size:3.5vh; max-width:100%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 4vh; "
            >{{ writePost.title }}</p>
          </b-col>
          <b-col>
            <b-button pill size="lg" variant="dark" class="p-3" @click="analize">
              <b-icon icon="exclude" />&nbsp;영상 분석
            </b-button>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div class="container" style="width:100%; padding:0; margin-top:2%;">
      <video style="width:100%; padding:0;" controls autoplay="true" name="media" id="vid">
        <source :src="videolink" type="video/mp4" />
      </video>
    </div>

    <div
      id="mainBody"
      class="container"
      style="border-left:solid; border-right:solid;border-bottom:solid; padding-left:5px;padding-right:5px;padding-bottom:5px; margin-top:-7%; padding-top:6%; margin-bottom:5%;"
    >
      <hr style="margin-left:2%;margin-right:2%;" />
      <div class="row">
        <div class="col-7 col-xl-8" style="padding:0;">
          <!-- 글쓴이 -->
          <div class="d-flex justify-content-between">
            <div class="d-flex" style=" margin-left:2%;">
              <b-avatar variant="secondary" :src="writeUser.userImage" class="ml-3 mr-3"></b-avatar>
              <div>
                <h4
                  style="margin-bottom:0; text-align:center; font-size:2.5vh; margin-top:12%;"
                >{{ writeUser.userNickname }}</h4>
                <h4
                  style="margin-bottom:0; text-align:center;  font-size:1.2vh;"
                >{{ writeUser.userTier }}</h4>
              </div>
            </div>
          </div>
        </div>
        <div class="col-5 col-xl-4">
          <!-- 조회수 -->
          <div class="d-flex justify-content-end" style="margin-right:5%;">
            <div class="d-flex">
              <b-icon-caret-right-square-fill
                class="h4"
                style=" margin-right:10px; padding-top:3%;"
              />
              <p style=" font-size:2.5vh ">{{ writePost.view }}</p>
            </div>
            <!-- 좋아요 -->
            <div class="d-flex justify-content-end">
              <div v-show="likest" @click="likesup">
                <b-icon-heart shift-v="5" class="btn" font-scale="3" style="cursor: pointer;" />
              </div>

              <div v-show="!likest" @click="likesdown">
                <b-icon-heart-fill
                  shift-v="5"
                  variant="danger"
                  font-scale="3"
                  class="btn"
                  style="cursor: pointer;"
                />
              </div>

              <p style="font-size:2.5vh">{{likescnt}}</p>
            </div>
          </div>
        </div>
      </div>
      <hr style="margin:2%;" />
      <div
        class="d-flex justify-content-start"
        style="margin-top:2%; margin-left:2%; margin-right:2%; text-align:center; border:solid 5px gray; border-radius:10px;"
      >
        <p style="font-size:2vh; text-align:left; margin:3%;">
          분석결과
          <br />
        </p>
        <hr />
        <p style="font-size:2vh; text-align:left; margin:3%;">
          여기에 여러가지 그래프들이 들어갈 겁니다.
          <br />
          <img src="http://j3a304.p.ssafy.io/imgup/images/gongsaing.jpg" />
          <br />
        </p>
      </div>
      <br />

      <!-- 로그인 확인하는 모달 -->
      <b-modal ref="loginChkModal" centered title="로그인 필요">
        로그인이 필요한 서비스입니다.
        <br />로그인 하시겠습니까?
        <template v-slot:modal-footer="{ cancel }">
          <b-button size="sm" variant="primary" @click="toLogin">확인</b-button>
          <b-button size="sm" variant="danger" @click="cancel()">취소</b-button>
        </template>
      </b-modal>
      <!-- 빙글빙글 모달 -->
      <b-modal ref="binggleModal" centered title="분석중..">
        <b-icon icon="arrow-clockwise" animation="spin" font-scale="4"></b-icon>
      </b-modal>
      <!-- 수정 삭제 -->
      <div style="margin-top:5%;">
        <div
          @click="edit"
          class="btn btn-primary"
          style="width:15%;margin-right:5%; position:relative; bottom:5%;"
          v-if="checkaccess"
        >수정</div>

        <div
          @click="delt"
          class="btn btn-secondary"
          style="width:15%;margin-left:5%;"
          v-if="checkaccess"
        >삭제</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import "../../assets/css/comment.css";

export default {
  data() {
    return {
      sessionUserNo: "",
      sessionUser: {},
      videoNo: this.$route.params.videoNo,
      writePost: {},
      writeUser: {
        userNickname: "",
        userImage: "",
        userTier: "",
      },
      videolink: "",
      votest: true,
      likescnt: "",
      likest: true,
      state: "",
    };
  },

  created() {
    window.scrollTo(0, 0);
    // 세션 유저정보 저장. 없으면 ""
    this.sessionUserNo = JSON.parse(sessionStorage.getItem("user"))
      ? JSON.parse(sessionStorage.getItem("user")).userNo
      : "";
    //처음 데이터 불러오기
    axios
      .get(process.env.VUE_APP_API_URL + "/post/detail", {
        params: {
          postNo: this.videoNo,
        },
      })
      .then(({ data }) => {
        this.writePost = data.object;
        this.videolink = process.env.VUE_APP_IMGUP_URL + this.writePost.video;
        let vd = document.querySelector("#vid");
        vd.load();
        // 글쓴이 정보 불러오기
        axios
          .get(process.env.VUE_APP_API_URL + "/account/user", {
            params: {
              userNo: this.writePost.userNo,
            },
          })
          .then(({ data }) => {
            this.writeUser = data.object;
            this.writeUser.userImage =
              process.env.VUE_APP_IMGUP_URL + this.writeUser.userImage;
            this.writeUser.userTier =
              this.writeUser.userGameTierStr +
              " " +
              this.writeUser.userGameRankStr;
          });
      });
    this.likecount();

    // 유저가 있을때만 도는 애들
    if (this.sessionUserNo) {
      //좋아요 여부 불러오기
      // axios
      //   .get(process.env.VUE_APP_API_URL + "/likes/status", {
      //     params: {
      //       videoNo: this.videoNo,
      //       userNo: this.sessionUserNo,
      //     },
      //   })
      //   .then(({ data }) => {
      //     if (data.object == 1) {
      //       this.likest = false;
      //     } else {
      //       this.likest = true;
      //     }
      //   })
      //   .catch(() => {
      //     location.href =
      //       "/error/좋아요를 눌렀는지 알아보다 에러가 나버렸지 뭐야..";
      //   });
      // 유저 정보불러오기
      axios
        .get(process.env.VUE_APP_API_URL + "/account/user", {
          params: {
            userNo: this.sessionUserNo,
          },
        })
        .then(({ data }) => {
          this.sessionUser = data.object;
          this.sessionUser.userImage =
            process.env.VUE_APP_IMGUP_URL + this.sessionUser.userImage;
        });
    }
  },

  computed: {
    checkadmin() {
      return this.sessionUserNo == 3;
    },

    checkaccess() {
      return (
        this.sessionUserNo == this.writeUser.userNo || this.sessionUserNo == 3
      );
    },
  },

  methods: {
    toLogin() {
      this.$router.push("/login/");
    },
    edit() {
      this.$router.push("/vedit/" + this.videoNo);
    },
    delt() {
      if (confirm("글을 삭제하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/video/", {
            params: {
              videoNo: this.videoNo,
            },
          })
          .then(() => {
            alert("삭제 완료");
            location.href = "/";
          })
          .catch(() => {
            location.href = "/error/글 삭제 중 서버에서 문제가 발생했습니다.";
          });
      }
    },

    likesup() {
      this.loginCheck() &&
        axios
          .post(process.env.VUE_APP_API_URL + "/likes/", {
            videoNo: this.videoNo,
            userNo: this.sessionUserNo,
          })
          .then(() => {
            this.likest = !this.likest;
            this.likescnt++;
          });
    },

    likesdown() {
      this.loginCheck() &&
        axios
          .delete(process.env.VUE_APP_API_URL + "/likes/", {
            params: {
              videoNo: this.videoNo,
              userNo: this.sessionUserNo,
            },
          })
          .then(() => {
            this.likest = !this.likest;
            this.likescnt--;
          });
    },

    likecount() {
      //좋아요 갯수 불러오기
      axios
        .get(process.env.VUE_APP_API_URL + "/likes/count", {
          params: {
            videoNo: this.videoNo,
          },
        })
        .then(({ data }) => {
          this.likescnt = data.object;
        });
    },
    analize() {
      this.$refs["binggleModal"].show();
    },

    loginCheck() {
      return this.sessionUserNo
        ? true
        : this.$refs["loginChkModal"].show() && false;
    },
  },
};
</script>

<style>
.border1 {
  border-left-width: 50px !important;
}
@media screen and (max-width: 580px) {
  #hide1 {
    display: none;
  }
}
@media screen and (max-width: 990px) {
  #hide2 {
    display: none;
  }
}
@media screen and (max-width: 760px) {
  #hide3 {
    display: none;
  }
}
.s {
  background-color: rgb(76, 202, 240);
}
.text_comment {
  font-size: 2vh;
}
.popover {
  position: relative !important;
  max-width: 90% !important;
  width: 90% !important;
}
</style>
