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
            <p style="font-weight: bold; font-size:3.5vh; padding-left:5px; ">
              NO.{{ postNo }}
            </p>
          </b-col>
          <b-col
            cols="9"
            lg="5"
            xl="6"
            class="d-flex justify-content-center"
            style="padding-top:3%;"
          >
            <p
              style="font-weight: bold;font-size:3.5vh; max-width:100%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 4vh; "
            >
              {{ writePost.title }}
            </p>
          </b-col>

          <b-col id="hide2" cols="4" lg="4" xl="3" style=" ">
            <div class="row" style="margin-top:4%;">
              <div class="col-4" style="padding-right:2%;">
                <b-icon
                  icon="clock"
                  animation="spin"
                  font-scale="3"
                  shift-v="0.7"
                ></b-icon>
              </div>
              <div class="col-8" style="padding-left:0; text-align:center;">
                <h4 style="margin-right:10px;font-weight: bold;">
                  투표 종료시간
                </h4>
                <h3 style="font-size:2vh;">{{ deadLineTime }}</h3>
              </div>
            </div>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <div class="container" style="width:100%; padding:0; margin-top:2%;">
      <video
        style="width:100%; padding:0;max-width:900px;"
        controls
        autoplay="true"
        name="media"
        id="vid"
      >
        <source :src="videolink" type="video/mp4" />
      </video>
    </div>

    <div
      id="mainBody"
      class="container"
      style="padding-left:5px;padding-right:5px;padding-bottom:5px; margin-top:-7%; padding-top:6%; margin-bottom:5%;"
    >
      <hr style="margin-left:2%;margin-right:2%;" />
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
              <div v-show="likest" @click="likesup">
                <b-icon-heart
                  shift-v="5"
                  class="btn"
                  font-scale="3"
                  style="cursor: pointer;"
                />
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

              <p style="font-size:2.5vh">{{ likescnt }}</p>
            </div>
          </div>
        </div>
      </div>
      <hr style="margin:2%;" />
      <div class="row">
        <div class="col-12 d-flex justify-content-center">
          <h1
            v-show="votest"
            @click="vote1"
            class="btn"
            style="background-color: #5e72e4;font-weight: bold;color:white;font-size:3.5vh; max-width:35%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 5vh; "
          >
            {{ writePost.option1 }} 탓
          </h1>
          <h2
            v-show="votest"
            style="color:red;font-weight: bold; margin-left:5%;margin-right:5%; font-size:3.5vh;"
          >
            VS
          </h2>
          <h1
            v-show="votest"
            @click="vote2"
            class="btn btn-danger"
            style=" font-weight: bold;font-size:3.5vh; max-width:35%;display: -webkit-box; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 4vh; height: 5vh;"
          >
            {{ writePost.option2 }} 탓
          </h1>
          <div class="d-flex justify-content-center">
            <div @click="votedelt" class="btn btn-primary" v-show="!votest">
              다시 투표하기
            </div>
          </div>
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

      <!-- 로그인 확인하는 모달 -->
      <b-modal ref="loginChkModal" centered title="로그인 필요">
        로그인이 필요한 서비스입니다.
        <br />로그인 하시겠습니까?
        <template v-slot:modal-footer="{ cancel }">
          <b-button size="sm" variant="primary" @click="toLogin">확인</b-button>
          <b-button size="sm" variant="danger" @click="cancel()">취소</b-button>
        </template>
      </b-modal>
      <!-- 댓글 시작  -->
      <div style="width:96%; margin-left:2%; margin-top:3%;">
        <!-- 댓글 입력 박스 -->
        <div class="CommentWriter" v-if="sessionUserNo">
          <div class="comment_inbox">
            <div class="d-flex mb-4" style="color:black;">
              <b-avatar
                variant="secondary"
                :src="sessionUser.userImage"
                class="ml-1 mr-3"
              ></b-avatar>
              <em class="comment_inbox_name mt-2" style="font-size:2vh;">{{
                sessionUser.userNickname
              }}</em>
            </div>
            <textarea
              placeholder="댓글을 남겨보세요"
              rows="1"
              class="comment_inbox_text"
              v-model="replyOption.comment"
              style="overflow: hidden; overflow-wrap: break-word;font-size:3vh;"
            ></textarea>
          </div>
          <!-- 댓글 등록 버튼 -->
          <div class="register_box row ml-4 mr-4 mb-3">
            <div
              class="col-5 btn btn-primary"
              style="background-color: #5e72e4;"
            >
              <a
                role="button"
                @click="comment1"
                class="button btn_register"
                style="font-size:2vh;color:white;"
                >{{ writePost.option1 }} 탓(으)로 댓글</a
              >
            </div>
            <div class="col-2" />
            <div class="col-5 btn btn-danger">
              <a
                role="button"
                @click="comment2"
                class="button btn_register"
                style="font-size:2vh;color:white;"
                >{{ writePost.option2 }} 탓(으)로 댓글</a
              >
            </div>
          </div>
        </div>
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
                      style="z-index: 0;"
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
      <div style="margin-top:5%;">
        <div
          @click="edit"
          class="btn btn-primary"
          style="width:15%;margin-right:5%; position:relative; bottom:5%;"
          v-if="checkaccess"
        >
          수정
        </div>

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
</template>

<script>
import axios from "axios";
import "../../assets/css/comment.css";

export default {
  data() {
    return {
      sessionUserNo: "",
      sessionUser: {},
      postNo: this.$route.params.postNo,
      writePost: {},
      writeUser: {
        userNickname: "",
        userImage: "",
        userTier: "",
      },
      deadLineTime: "",
      videolink: "",
      votest: true,
      likescnt: "",
      likest: true,
      replyOption: {
        comment: "",
        option: "",
      },
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
        let time = Date.parse(data.object.deadLine) - Date.now();
        if (time < 0) {
          location.href = "/result/" + this.postNo;
        }
        let day = parseInt(time / 1000 / 60 / 60 / 24);
        let hour = parseInt((time / 1000 / 60 / 60) % 24);
        let min = parseInt((time / 1000 / 60) % 60);
        this.deadLineTime =
          (day ? day + "일 " : "") +
          (hour ? hour + "시간 " : "") +
          (min ? min + "분 " : "1분 미만 ") +
          "남음";
        document.querySelector("#vid").load();
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
            this.likest = false;
          } else {
            this.likest = true;
          }
        })
        .catch(() => {
          location.href =
            "/error/좋아요를 눌렀는지 알아보다 에러가 나버렸지 뭐야..";
        });
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
      this.votecheck();
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
    vote1() {
      this.loginCheck() &&
        axios
          .post(process.env.VUE_APP_API_URL + "/vote", {
            userGameRankNum: this.sessionUser.userGameRankNum,
            userSolRankNum: this.sessionUser.userSolRankNum,
            postNo: this.postNo,
            userNo: this.sessionUserNo,
            value: 1,
          })
          .then(() => {
            this.votecheck();
          })
          .catch(() => {
            location.href = "/error/투표 중 오류가 났어요";
          });
    },

    vote2() {
      this.loginCheck() &&
        axios
          .post(process.env.VUE_APP_API_URL + "/vote", {
            userGameRankNum: this.sessionUser.userGameRankNum,
            userSolRankNum: this.sessionUser.userSolRankNum,
            postNo: this.postNo,
            userNo: this.sessionUserNo,
            value: 2,
          })
          .then(() => {
            this.votecheck();
          })
          .catch(() => {
            location.href = "/error/투표 중 오류가 났어요";
          });
    },
    votedelt() {
      axios
        .delete(process.env.VUE_APP_API_URL + "/vote", {
          params: {
            postNo: this.postNo,
            userNo: this.sessionUserNo,
          },
        })
        .then(() => {
          this.votecheck();
        })
        .catch(() => {
          location.href = "/error/투표 취소 중 문제 발생";
        });
    },

    votecheck() {
      //투표여부 불러오기
      this.sessionUserNo &&
        axios
          .get(process.env.VUE_APP_API_URL + "/vote", {
            params: {
              postNo: this.postNo,
              userNo: this.sessionUserNo,
            },
          })
          .then(({ data }) => {
            this.votest = data.data != "success";
          });
    },

    toLogin() {
      this.$router.push("/login/");
    },
    edit() {
      this.$router.push("/edit/" + this.postNo);
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

    likesup() {
      this.loginCheck() &&
        axios
          .post(process.env.VUE_APP_API_URL + "/likes/", {
            postNo: this.postNo,
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
              postNo: this.postNo,
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
            postNo: this.postNo,
          },
        })
        .then(({ data }) => {
          this.likescnt = data.object;
        });
    },

    //option1 댓글
    comment1() {
      let err = false;
      let msg = "";

      !this.replyOption.comment &&
        ((msg = "댓글을 입력해주세요"), (err = true));

      if (err) alert(msg);
      else {
        axios
          .post(process.env.VUE_APP_API_URL + "/reply/", {
            postNo: this.postNo,
            userNo: this.sessionUserNo,
            content: this.replyOption.comment,
            replyOpt: 1,
          })
          .then(() => {
            this.replyOption.comment = "";
            this.listAll = [];
            this.offset = 0;
            this.callReplyListAll();
          })
          .catch(() => {
            location.href = "/error/댓글 등록 중 오류가 발생했습니다.";
          });
      }
    },

    //option2 댓글
    comment2() {
      let err = false;
      let msg = "";

      !this.replyOption.comment &&
        ((msg = "댓글을 입력해주세요"), (err = true));

      if (err) alert(msg);
      else {
        axios
          .post(process.env.VUE_APP_API_URL + "/reply/", {
            postNo: this.postNo,
            userNo: this.sessionUserNo,
            content: this.replyOption.comment,
            replyOpt: 2,
          })
          .then(() => {
            this.replyOption.comment = "";
            this.listAll = [];
            this.offset = 0;
            this.callReplyListAll();
          })
          .catch(() => {
            location.href = "/error/댓글 등록 중 오류가 발생했습니다.";
          });
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
