<template>
  <div
    class="pb-2"
    style="background: rgba( 0, 0, 0, 0.5 );width:96%; margin-left:auto; margin-right:auto;font-family:Tmon;color: white;"
  >
    <div
      class="d-flex justify-content-between"
      style="margin-right:1%;margin-left:1%; margin-top:1%;"
    >
      <b-container class="bv-example-row" style="padding:0;margin-top:3%;">
        <b-row
          style="width:100%; border-top:solid; border-bottom:solid; margin:0; "
        >
          <b-col
            cols="3"
            lg="3"
            xl="3"
            style="text-align:left; padding-top:2%; padding-left:1%;"
            class="d-flex"
          >
            <p id="hide3" style="font-size:3vh; padding-left:7%;">의뢰번호</p>
            <p style="font-weight: bold; font-size:3vh; padding-left:5px; ">
              NO.{{ postNo }}
            </p>
          </b-col>
          <b-col cols="9" lg="6" xl="6" class="d-flex" style="padding-top:3%;">
            <p
              style="font-weight: bold;font-size:3.5vh; max-width:100%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 4vh; "
            >
              {{ writePost.title }}
            </p>
          </b-col>
          <b-col id="hide2" cols="4" lg="3" xl="3" style=" ">
            <h4
              style="font-size:5vh;font-weight: bold; color:red;margin-top:10%;"
            >
              투표 종료
            </h4>
          </b-col>
        </b-row>
      </b-container>
    </div>

    <div class="container" style="width:100%; padding:0; margin-top:2%;">
      <video
        style="width:100%; padding:0; max-width:900px;"
        controls
        autoplay="true"
        name="media"
        id="vid"
      >
        <source :src="videolink" type="video/mp4" />
      </video>
    </div>

    <div
      class="container"
      style="  padding-left:5px;padding-right:5px;padding-bottom:5px; margin-top:-7%; padding-top:6%"
    >
      <hr style="margin-left:2%;margin-right:2%;" />
      <div>
        <div class="row">
          <div class="col-7 col-xl-8" style="padding:0;">
            <!-- 글쓴이 -->
            <div class="d-flex justify-content-between">
              <div class="d-flex" style=" margin-left:2%;">
                <b-avatar
                  variant="secondary"
                  :src="writeUser.userImage"
                  class="ml-3 mr-3"
                ></b-avatar>
                <div>
                  <h4
                    style="margin-bottom:0; text-align:center; font-size:2.5vh; margin-top:12%;"
                  >
                    {{ writeUser.userNickname }}
                  </h4>
                  <h4
                    style="margin-bottom:0; text-align:center;  font-size:1.2vh;"
                  >
                    {{ writeUser.userTier }}
                  </h4>
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
                <div v-show="likeStatus" @click="likesup">
                  <b-icon-heart
                    shift-v="5"
                    class="btn"
                    font-scale="3"
                    style="cursor: pointer; "
                  />
                </div>

                <div v-show="!likeStatus" @click="likesdown">
                  <b-icon-heart-fill
                    shift-v="5"
                    variant="danger"
                    font-scale="3"
                    class="btn"
                    style="cursor: pointer; "
                  />
                </div>

                <p style="font-size:2.5vh">{{ likescnt }}</p>
              </div>
            </div>
          </div>
        </div>
        <hr style="margin:2%;" />
        <div class="row">
          <div class="col-12 d-flex justify-content-center">
            <h1
              class="btn disabled"
              style="background-color: #5e72e4;font-weight: bold;color:white;font-size:3.2vh; max-width:35%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 5vh; "
            >
              {{ this.writePost.option1 }} 탓
            </h1>
            <h2
              style="color:red;font-weight: bold; margin-left:5%;margin-right:5%; font-size:3.5vh;"
            >
              VS
            </h2>
            <h1
              class="btn btn-danger disabled"
              style=" font-weight: bold;font-size:3.2vh; max-width:35%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 5vh;"
            >
              {{ this.writePost.option2 }} 탓
            </h1>
          </div>
        </div>
        <div
          class="d-flex justify-content-start"
          style="margin-top:2%; margin-left:2%; margin-right:2%; text-align:center; border:solid 5px gray; border-radius:10px;"
        >
          <p style="font-size:2vh; text-align:left; margin:3%;">
            {{ writePost.content }}
          </p>
        </div>
        <br />

        <div class="d-flex justify-content-between">
          <p
            style="color:blue; font-weight: bold;font-size:3vh; margin-left:3%;"
          >
            {{ this.writePost.option1 }} 탓 {{ resOp1 }} %
          </p>

          <p style="color:red;font-weight: bold;font-size:3vh;margin-right:3%;">
            {{ this.writePost.option2 }} 탓 {{ resOp2 }} %
          </p>
        </div>

        <div>
          <b-progress
            height="30px"
            show-value
            striped
            :max="max"
            class="mb-3"
            style="margin-left:1%; margin-right:1%"
          >
            <b-progress-bar
              variant="primary"
              :value="values[0]"
              animated
              style="font-size:2.7vh"
            ></b-progress-bar>
            <b-progress-bar
              variant="danger"
              :value="values[1]"
              animated
              style="font-size:2.7vh"
            ></b-progress-bar>
          </b-progress>
        </div>
        <div class="d-flex" style="padding-top:2%; margin-left:3%;">
          <div v-if="op1bigger" style="color:blue; font-size:2.8vh;">
            {{ resultOp }}
          </div>
          <div v-else style="color:red; font-size:2.8vh;">{{ resultOp }}</div>
          <p style="font-size:2.8vh; ">{{ resultMsg }}</p>
        </div>

        <!-- 로그인 확인하는 모달 -->
        <b-modal ref="loginChkModal" centered title="로그인 필요">
          로그인이 필요한 서비스입니다.
          <br />로그인 하시겠습니까?
          <template v-slot:modal-footer="{ cancel }">
            <b-button size="sm" variant="primary" @click="toLogin"
              >확인</b-button
            >
            <b-button size="sm" variant="danger" @click="cancel()"
              >취소</b-button
            >
          </template>
        </b-modal>
      </div>
      <!-- 댓글 시작  -->
      <div style="width:96%; margin-left:2%; margin-top:3%;">
        <!-- 댓글 리스트 시작 -->
        <div class="row">
          <div class="col-12">
            <ul class="p-1 m-1">
              <li
                class="CommentItem mb-2"
                v-for="(item, $index) in listPart"
                :key="$index"
                style="display:block;"
              >
                <!-- 왼쪽 -->
                <div class="row">
                  <div class="col-12 col-md-5">
                    <div
                      v-if="item.replyOpt == 1"
                      role="tooltip"
                      tabindex="-1"
                      class="popover b-popover b-popover-primary bs-popover-left"
                      x-placement="left"
                      style="float:right;z-index: 0;"
                    >
                      <div class="arrow" style="top: 5px;"></div>
                      <h3 class="popover-header">
                        <b-avatar
                          variant="secondary"
                          :src="profile(item.userImage)"
                          class="ml-3 mr-3"
                        ></b-avatar>
                        {{ item.userNickname }}
                        <span style="font-size:1vh;">{{
                          item.userSolTierStr + " " + item.userSolRankStr
                        }}</span>
                      </h3>
                      <div class="popover-body">
                        <div class="comment_box">
                          <div class="d-flex"></div>
                          <div class="comment_text_box">
                            <p class="comment_text_view ml-2 mt-3">
                              <span class="text_comment">{{
                                item.content
                              }}</span>
                            </p>
                          </div>
                          <div class="comment_info_box row">
                            <span class="comment_info_date col-10">{{
                              replyTime(item.replyDate)
                            }}</span>
                            <span
                              class="col-1 ml-3"
                              style="cursor:pointer"
                              v-if="
                                sessionUserNo == item.userNo ||
                                  sessionUserNo == 3
                              "
                              @click="deleteReply(item)"
                            >
                              <b-icon icon="x-square-fill" variant="danger" />
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- 가운데 -->
                  <div id="hide3" class="col-0 col-md-2">
                    <a :id="'replyCentre' + $index" :tabindex="$index">{{
                      replyTime(item.replyDate)
                    }}</a>
                  </div>
                  <!-- 오른쪽 -->
                  <div class="col-12 col-md-5">
                    <div
                      v-if="item.replyOpt == 2"
                      role="tooltip"
                      tabindex="-1"
                      class="popover b-popover b-popover-danger bs-popover-right"
                      x-placement="right"
                      style="z-index: 0;s"
                    >
                      <div class="arrow" style="top: 5px;"></div>
                      <h3 class="popover-header">
                        <b-avatar
                          variant="secondary"
                          :src="profile(item.userImage)"
                          class="ml-3 mr-3"
                        ></b-avatar>
                        {{ item.userNickname }}
                        <span style="font-size:1vh;">{{
                          item.userSolTierStr + " " + item.userSolRankStr
                        }}</span>
                      </h3>
                      <div class="popover-body">
                        <div class="comment_box">
                          <div class="d-flex"></div>
                          <div class="comment_text_box">
                            <p class="comment_text_view ml-2 mt-3">
                              <span class="text_comment">{{
                                item.content
                              }}</span>
                            </p>
                          </div>
                          <div class="comment_info_box row">
                            <span class="comment_info_date col-10">{{
                              replyTime(item.replyDate)
                            }}</span>
                            <span
                              class="col-1 ml-3"
                              style="cursor:pointer"
                              v-if="
                                sessionUserNo == item.userNo ||
                                  sessionUserNo == 3
                              "
                              @click="deleteReply(item)"
                            >
                              <b-icon icon="x-square-fill" variant="danger" />
                            </span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <infinite-loading @infinite="infiniteHandler"></infinite-loading>
      <div
        style="background-color:rgb(222, 226, 228);width:96%; margin-left:2%; margin-top:3%;"
      >
        <div>
          <div
            @click="delt"
            class="btn btn-secondary"
            style="width:15%;margin-left:5%;"
            v-if="checkaccess"
          >
            삭제
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      sessionUserNo: "",
      postNo: this.$route.params.postNo,
      writePost: {},
      writeUser: {
        userNickname: "",
        userImage: "",
        userTier: "",
      },
      videolink: "",
      likescnt: "",
      likeStatus: true,
      max: "",
      values: "",
      resOp1: "",
      resOp2: "",
      resultOp: "",
      resultMsg: "",
      listAll: [],
      listPart: [],
      offset: 0,
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
          postNo: this.postNo,
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
          })
          .catch(() => {
            console.log("유저 정보 ㅗ류");
          });
        // 결과 내용
        axios
          .get(process.env.VUE_APP_API_URL + "/vote/count", {
            params: {
              postNo: this.postNo,
            },
          })
          .then(({ data }) => {
            this.values = data.object;
            this.max = this.values[0] + this.values[1];
            if (!this.values[0] && !this.values[1]) {
              this.resultMsg = "투표내역이 없습니다";
            } else {
              let r = this.values[0] / this.max;
              this.resultOp =
                r < 0.5 ? this.writePost.option2 : this.writePost.option1;
              this.resOp1 = parseInt(r * 100);
              this.resOp2 = 100 - this.resOp1;
              this.resultMsg =
                r < 0.25 || r > 0.75
                  ? " 탓이 압도적으로 우세합니다."
                  : r < 0.47 || r > 0.53
                  ? " 탓이 더 우세합니다."
                  : (this.resultOp = "") + "오 마이갓 우열을 가리기 힘듭니다";
            }
          })
          .catch(() => {
            console.log("투표 정보 정보 ㅗ류");
          });
      })
      .catch(() => {
        location.href = "/error/글 정보를 불러오다 오류가 났어요";
      });

    //좋아요 갯수 불러오기
    axios
      .get(process.env.VUE_APP_API_URL + "/likes/count", {
        params: {
          postNo: this.postNo,
        },
      })
      .then(({ data }) => {
        this.likescnt = data.object;
      })
      .catch(() => {
        location.href = "/error/좋아요 갯수를 불러오다 오류가 나버렸어요";
      });

    // 댓글 불러오기
    this.callReplyListAll();
    // 유저가 있을때만 도는 애들
    if (this.sessionUserNo) {
      //좋아요 여부 불러오기
      axios
        .get(process.env.VUE_APP_API_URL + "/likes/status", {
          params: {
            postNo: this.postNo,
            userNo: this.sessionUserNo,
          },
        })
        .then(({ data }) => {
          if (data.object == 1) {
            this.likeStatus = false;
          } else {
            this.likeStatus = true;
          }
        })
        .catch(() => {
          location.href =
            "/error/좋아요를 눌렀는지 알아보다 에러가 나버렸지 뭐야..";
        });
    }
  },

  computed: {
    checkaccess() {
      return this.sessionUserNo == this.userNo || this.sessionUserNo == 3;
    },

    op1bigger() {
      return this.option1 > this.option2;
    },
  },

  methods: {
    toLogin() {
      this.$router.push("/login/");
    },
    delt() {
      if (confirm("글을 삭제하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/post/", {
            params: {
              postNo: this.postNo,
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
    loginCheck() {
      return this.sessionUserNo
        ? true
        : this.$refs["loginChkModal"].show() && false;
    },
    likesup() {
      this.loginCheck() &&
        axios
          .post(process.env.VUE_APP_API_URL + "/likes/", {
            postNo: this.postNo,
            userNo: this.sessionUserNo,
          })
          .then(() => {
            this.likeStatus = !this.likeStatus;
            this.likescnt++;
          });
    },

    likesdown() {
      this.loginCheck() &&
        axios
          .delete(process.env.VUE_APP_API_URL + "/likes/", {
            params: {
              postNo: this.postNo,
              userNo: this.sessionUserNo,
            },
          })
          .then(() => {
            this.likeStatus = !this.likeStatus;
            this.likescnt--;
          });
    },

    // 전체 댓글 불러오기
    callReplyListAll() {
      axios
        .get(process.env.VUE_APP_API_URL + "/reply/ListAll", {
          params: {
            postNo: this.postNo,
          },
        })
        .then(({ data }) => {
          this.listAll = data.object;
          if (this.state) {
            this.state.reset();
            this.state.loaded();
          }
        })
        .catch(() => {
          location.href = "/error/댓글 조회 중 오류 발생";
        });
    },
    infiniteHandler($state) {
      this.state = $state;
      if (this.listAll) {
        this.offset += 2;
        this.offset > this.listAll.length &&
          ((this.offset = this.listAll.length), $state.complete());
        this.listPart = this.listAll.slice(0, this.offset);
        $state.loaded();
      } else {
        $state.complete();
      }
    },

    //댓글 프로필사진 불러오기
    profile(prof) {
      if (prof) {
        return process.env.VUE_APP_IMGUP_URL + prof;
      } else {
        return "";
      }
    },
    replyTime(item) {
      let date = new Date(item);
      return (
        (date.getMonth() < 10 ? "0" : "") +
        date.getMonth() +
        "/" +
        (date.getDate() < 10 ? "0" : "") +
        date.getDate() +
        " " +
        (date.getHours() < 10 ? "0" : "") +
        date.getHours() +
        ":" +
        (date.getMinutes() < 10 ? "0" : "") +
        date.getMinutes()
      );
    },
    deleteReply(item) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/reply/", {
            params: {
              replyNo: item.replyNo,
            },
          })
          .then(() => {
            this.listAll = [];
            this.offset = 0;
            this.callReplyListAll();
          })
          .catch(() => {
            location.href = "/error/글 삭제 중 서버에서 문제가 발생했습니다.";
          });
      }
    },
  },
};
</script>

<style>
.color1 {
  background-color: rgb(243, 241, 239);
}
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
@media screen and (min-width: 581px) {
  #show1 {
    display: none;
  }
}
@media screen and (min-width: 761px) {
  #show2 {
    display: none;
  }
}
.progress-bar {
  font-size: 10px;
}
</style>
