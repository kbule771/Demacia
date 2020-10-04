<template>
  <div style="heigth: 100%; font-family: Tmon">
    <div class="d-flex row" style>
      <div class="col-1 col-sm-8 col-md-3 col-lg-3 col-xl-4"></div>
      <div
        id="pad2"
        class="col-10 col-sm-8 col-md-6 col-lg-6 col-xl-5"
        style="
          padding-right: 0;
          padding-left: 0;
          margin-top: 5%;
          max-width: 400px;
        "
      >
        <div id="pad1" class="shadow1">
          <div style="background-color: black; height: 200px; width: 100%">
            <div class="container" style="padding-top: 20px">
              <!-- 프로필 이미지 -->
              <div v-b-modal.modalProfileImage style="cursor: pointer">
                <h2>
                  <b-avatar
                    badge-variant="dark"
                    badge-offset="-10px"
                    variant="secondary"
                    :src="imgURL"
                    size="10rem"
                    class="md-4"
                  >
                    <template v-slot:badge>
                      <b-icon icon="upload"></b-icon>
                    </template>
                  </b-avatar>
                </h2>
                <!-- 이미지 변경 모달 -->
                <b-modal
                  id="modalProfileImage"
                  size="sm"
                  title="프로필 사진 변경"
                  centered
                  hide-footer
                >
                  <b-list-group>
                    <b-list-group-item @click="imgUp" href="#" variant="primary"
                      >내 기기에서 파일 탐색</b-list-group-item
                    >
                    <b-list-group-item
                      @click="imgDel"
                      href="#"
                      variant="primary"
                      >프로필 사진 삭제</b-list-group-item
                    >
                    <b-list-group-item
                      href="#"
                      variant="secondary-outline"
                      v-b-modal.imageRule
                      >사진 업로드 이용규칙</b-list-group-item
                    >
                    <b-modal
                      id="imageRule"
                      title="프로필 사진 업로드 이용규칙"
                      ok-only
                      centered
                    >
                      프로필 사진은 회원 여러분의 개성을 드러낼 수 있는
                      서비스입니다.
                      <br />다만 원활한 서비스 제공에 문제를 일으키는 프로필
                      사진은 규제될 수 있음을 알려드리오니, 업로드 시 유의하여
                      주시기 바랍니다. <br />[규제 대상]
                      <ol>
                        <li>음란, 선정성 사진</li>
                        <li>욕설 등 타인에게 불쾌감을 주는 이미지</li>
                        <li>홍보 목적으로 URL이나 연락처가 기재된 이미지</li>
                        <li>폭력 묘사, 신체훼손 등 혐오감을 주는 이미지</li>
                        <li>
                          명예훼손, 저작권 침해 등 타인의 권리를 침해하는 이미지
                        </li>
                        <li>그 외 법령 및 약관에 위배되는 사진 및 이미지</li>
                      </ol>
                      규제 대상으로 확인된 이미지는 발견 즉시 무통보 삭제되며,
                      정도와 빈도에 따라 서비스 이용이 제한 될 수 있습니다.
                      <br />이의 사항 발생 시 관리자에게 문의바랍니다.
                      <br />
                      <br />건전하고 멋진 프로필 사진 사용으로 여러분의 개성을
                      뽐내주세요. 감사합니다.
                    </b-modal>
                  </b-list-group>
                </b-modal>
                <input
                  type="file"
                  class="form-control"
                  placeholder="프로필 등록"
                  id="profil"
                  ref="uploadImageFile"
                  @change="onFileSelected"
                  accept="image/*"
                  style="display: none"
                />
              </div>
            </div>
          </div>
          <!-- 유저정보 박스 -->
          <div style="background-color: white; height: 170px">
            <b-row style="padding-left: 30%">
              <b-col
                cols="2"
                class="ml-1"
                style="font-size: 20px; color: darkgray"
                >{{ user.userEmail }}</b-col
              >
            </b-row>
            <b-row class="m-3" style="height: 3rem">
              <b-col>
                <!-- 닉네임 -->
                <h2 v-show="nicknameCheck">{{ user.userNickname }}</h2>
                <!-- 닉네임 수정시 입력박스 -->
                <b-input-group v-show="!nicknameCheck">
                  <b-form-input
                    type="text"
                    placeholder="닉네임 입력"
                    id="nickname"
                    v-model="nickname"
                    :state="nicknameValid"
                    aria-describedby="nicknameFeedback"
                  />
                  <b-input-group-append>
                    <b-button variant="light" @click="nicknameUpdate"
                      >수정</b-button
                    >
                  </b-input-group-append>
                  <b-form-invalid-feedback id="nicknameFeedback">{{
                    nicknameMsg
                  }}</b-form-invalid-feedback>
                </b-input-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <!-- 비밀번호 재입력 모달 -->
                <b-modal
                  ref="passwordChkModal"
                  centered
                  title="비밀번호 재입력"
                >
                  <b-input-group>
                    <b-form-input
                      type="password"
                      class="form-control"
                      placeholder="기존 비밀번호를 입력해주세요"
                      id="passwordChk"
                      v-model="passwordChk"
                      :state="passwordChkValid"
                      aria-describedby="passwordChkFeedback"
                      @keyup.enter="passwordCheck"
                    />
                    <b-form-invalid-feedback id="passwordChkFeedback">{{
                      passwordChkMsg
                    }}</b-form-invalid-feedback>
                  </b-input-group>
                  <template v-slot:modal-footer>
                    <b-button size="sm" variant="light" @click="passwordCheck"
                      >확인</b-button
                    >
                  </template>
                </b-modal>
                <!-- 비밀번호 변경 모달 -->
                <b-modal
                  ref="passwordUpdateModal"
                  centered
                  title="비밀번호 변경"
                >
                  <div class="form-group">
                    <b-form-input
                      type="password"
                      placeholder="비밀번호 : 영어, 숫자 혼용 8자 이상"
                      id="password"
                      v-model="password"
                      :state="passwordValid"
                      aria-describedby="pwFeedback"
                    />
                    <b-form-invalid-feedback id="pwFeedback">{{
                      passwordMsg
                    }}</b-form-invalid-feedback>
                  </div>
                  <div class="form-group">
                    <b-form-input
                      type="password"
                      placeholder="비밀번호 확인"
                      id="passwordconfirm"
                      v-model="passwordConfirm"
                      :state="passwordConfirmValid"
                      aria-describedby="pwconFeedback"
                      @keyup.enter="passwordUpdate"
                    />
                    <b-form-invalid-feedback id="pwconFeedback">{{
                      passwordConfirmMsg
                    }}</b-form-invalid-feedback>
                  </div>
                  <template v-slot:modal-footer>
                    <b-button size="sm" variant="light" @click="passwordUpdate"
                      >비밀번호 변경</b-button
                    >
                  </template>
                </b-modal>
                <!-- 마일리지랭킹 조회 -->
                <h6 class="mil">마일리지 랭킹 :</h6>
                <h6 class="mil" style="color: blue">{{ usermilrank }}</h6>
                <h6 class="mil">위</h6>
                <h6 class="mil">(상위 {{ usermilrankper }}%)</h6>
              </b-col>
              <b-col>
                <!-- \닉네임, 비밀번호, 회원탈퇴 드랍다운 -->
                <b-dropdown variant="light" text="더 보기" class="m-2">
                  <b-dropdown-item @click="nicknameCheck = !nicknameCheck"
                    >닉네임 수정</b-dropdown-item
                  >
                  <b-dropdown-item @click="showPasswordChkModal"
                    >비밀번호 변경</b-dropdown-item
                  >
                  <b-dropdown-divider></b-dropdown-divider>
                  <b-dropdown-item @click="deleteUser"
                    >회원 탈퇴</b-dropdown-item
                  >
                </b-dropdown>
              </b-col>
            </b-row>
          </div>
        </div>
      </div>

      <!-- 마일리지 박스 -->
      <div
        class="d-flex align-items-center justify-content-center col-12 col-sm-8 col-md-12 col-lg-4 col-xl-4"
        style="padding-left: 3%; padding-top: 5%"
      >
        <div style="height: 120px">
          <div
            class="shadow1 d-flex justify-content-center"
            style="
              width: 300px;
              height: 100px;
              background-color: white;
              margin: auto;
              top: 50%;
              font-family: digital;
            "
          >
            <p
              style="
                font-size: 3vh;
                margin-top: 10%;
                color: red;
                font-weight: bold;
                padding-right: 4px;
              "
            >
              {{ user.userMileage }}
            </p>
            <p style="font-size: 3vh; margin-top: 10%">SOL</p>
          </div>
        </div>
      </div>
    </div>
    <!-- 하단부 -->
    <div style="background-color: rgb(222, 226, 228); margin: 5%">
      <!-- 랭크 박스 -->
      <div class="row d-flex justify-content-center">
        <div
          class="shadow1 col-10 col-xl-5 m-4"
          style="background-color: white"
        >
          <!-- 솔 랭 -->
          <b-row>
            <b-col>
              <b-card no-body style="border: none">
                <b-row no-gutters>
                  <b-col cols="6" md="4">
                    <b-card-img
                      :src="solRankImg"
                      width="100%"
                      alt="게임랭크"
                      class="rounded-0"
                    ></b-card-img>
                  </b-col>
                  <b-col cols="6" md="8">
                    <b-card-body>
                      <b-card-text>
                        <b-row cols="1" style="margin-top: 10%">
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              SOLOMON RANK
                            </p>
                          </b-col>
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              {{ solRank }}
                            </p>
                          </b-col>
                        </b-row>
                      </b-card-text>
                    </b-card-body>
                  </b-col>
                </b-row>
              </b-card>
            </b-col>
          </b-row>
          <hr />
          <!-- 게임 랭-->
          <b-row>
            <b-col>
              <b-card no-body class="m-2" style="border: none">
                <b-row no-gutters>
                  <b-col cols="6" md="4">
                    <b-card-img
                      :src="gameRankImg"
                      width="100%"
                      alt="게임랭크"
                      class="rounded-0"
                    ></b-card-img>
                  </b-col>
                  <b-col cols="6" md="8">
                    <b-card-body>
                      <b-card-text>
                        <b-row
                          cols="1"
                          align-v="stretch"
                          v-show="user.summonerName"
                        >
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              GAME RANK
                            </p>
                          </b-col>
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              {{ gameRank }}
                            </p>
                          </b-col>
                          <b-col class="d-flex justify-content-center">
                            <b-button
                              style="max-width: 200px"
                              variant="light"
                              block
                              @click="summonerReset"
                              >초기화</b-button
                            >
                          </b-col>
                        </b-row>
                        <b-row
                          align-v="center"
                          cols="1"
                          v-show="!user.summonerName"
                        >
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              GAME RANK
                            </p>
                          </b-col>
                          <b-col>
                            <p
                              id="font1"
                              style="font-size: 3vh; font-weight: bold"
                            >
                              소환사 인증이 필요합니다!
                            </p>
                          </b-col>
                          <b-col>
                            <!-- 소환사 인증 폼 -->
                            <b-input-group>
                              <b-form-input
                                type="text"
                                placeholder="소환사명 입력"
                                id="summoner"
                                v-model="summoner"
                                :state="summonerValid"
                                aria-describedby="summonerFeedback"
                              />
                              <b-input-group-append>
                                <b-button variant="light" @click="summonerAuth"
                                  >인증</b-button
                                >
                              </b-input-group-append>
                              <b-form-invalid-feedback id="summonerFeedback">{{
                                summonerMsg
                              }}</b-form-invalid-feedback>
                            </b-input-group>
                          </b-col>
                        </b-row>
                      </b-card-text>
                    </b-card-body>
                  </b-col>
                </b-row>
              </b-card>
            </b-col>
          </b-row>
        </div>
        <div
          class="shadow1 col-10 col-xl-5 m-4 pt-4"
          style="background-color: white"
        >
          <!-- 솔로몬 활동 리스트 박스-->
          <h4 style="display: inline-block">솔로몬 활동</h4>
          <h6 style="display: inline-block; float: right">
            적중률 : {{ userhitrate }}%
          </h6>
          <ul class="solomonul">
            <li v-for="(item, $index) in listsol" :key="$index">
              <div class="solomon">
                <div class="title">{{ item.postTitle }}</div>
                <div class="result">{{ item.voteResult }}</div>
                <div class="point1" v-if="item.voteResult == 'Win'">
                  +{{ item.point }}SOL
                </div>
                <div class="point2" v-else-if="item.voteResult == 'Proceeding'">
                  {{ item.point }}SOL
                </div>
                <div class="point3" v-else>-{{ item.point }}SOL</div>
                <div class="icon" v-if="item.voteResult == 'Proceeding'">
                  <b-icon
                    class="icon"
                    icon="arrow-right-circle"
                    font-scale="2"
                    style="cursor: pointer"
                    @click="toDetail(item.postNo)"
                  ></b-icon>
                </div>
                <div class="icon" v-else>
                  <b-icon
                    class="icon"
                    icon="arrow-right-circle"
                    font-scale="2"
                    style="cursor: pointer"
                    @click="toResult(item.postNo)"
                  ></b-icon>
                </div>
              </div>
            </li>
          </ul>
        </div>

        <!-- MBTI -->
        <div
          class="shadow1 col-10 col-xl-5 m-4 p-4"
          style="background-color: white"
        >
          <b-row>
            <b-col cols="12">
              <h4>내 MBTI</h4>
            </b-col>
            <b-col cols="12" v-if="!user.mbti">
              아직 데마시아 전용 MBTI 측정을 하지 않았습니다.
              <br />
              <b-button size="lg" variant="secondary" href="/mbtisurvey"
                >MBTI 설문하기</b-button
              >
            </b-col>
            <b-col cols="12" v-else>
              <h4>{{ user.mbti }}</h4>
              <br />
              <b-button size="lg" variant="primary" href="/mbti" class="mr-2">
                챔피언 추천!
              </b-button>
              <b-button size="lg" variant="secondary" href="/mbtisurvey">
                다시 설문하기
              </b-button>
            </b-col>
          </b-row>
        </div>
        <!-- 추천 -->
        <div
          class="shadow1 col-10 col-xl-5 m-4 pt-4"
          style="background-color: white"
        >
          <h4 style="display: inline-block">추천 챔피언과 조합</h4>
          <br />코그모 티모 모모
        </div>
        <!-- 활동 목록 -->
        <div class="col-11 p-2">
          <div class="shadow1 m-4" style="background-color: white">
            <h2 class="p-4">투표의뢰목록</h2>
            <b-table
              hover
              :items="myPostList"
              :fields="myPostFields"
              table-variant="primary"
            >
              <template v-slot:cell(postDate)="data">{{
                postDT(data.value)
              }}</template>
              <template v-slot:cell(to)="data">
                <b-icon
                  class="icon"
                  icon="arrow-right-circle"
                  font-scale="2"
                  style="cursor: pointer"
                  @click="toDetail(data.item.postNo)"
                ></b-icon>
              </template>
            </b-table>
          </div>
        </div>
        <!-- 영상분석 -->
        <div class="col-11 p-2">
          <div class="shadow1 m-4" style="background-color: white">
            <h2 class="p-4">영상분석 의뢰목록</h2>
            <b-table
              hover
              :items="myPostList"
              :fields="myPostFields"
              table-variant="primary"
            >
              <template v-slot:cell(postDate)="data">{{
                postDT(data.value)
              }}</template>
              <template v-slot:cell(to)="data">
                <b-icon
                  class="icon"
                  icon="arrow-right-circle"
                  font-scale="2"
                  style="cursor: pointer"
                  @click="toDetail(data.item.postNo)"
                ></b-icon>
              </template>
            </b-table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/profile.css";
import axios from "axios";
import PV from "password-validator";

export default {
  data() {
    return {
      userNo: "",
      user: {},
      passwordSchema: "",
      pwUpdate: false,
      nickname: "",
      nicknameCheck: true,
      nicknameValid: null,
      nicknameMsg: "",
      passwordChk: "",
      passwordChkValid: null,
      passwordChkMsg: "",
      password: "",
      passwordValid: null,
      passwordMsg: "",
      passwordConfirm: "",
      passwordConfirmValid: null,
      passwordConfirmMsg: "",
      summoner: "",
      summonerValid: null,
      summonerMsg: "",
      myPostList: [],
      myPostFields: [
        { key: "postNo", label: "의뢰번호" },
        { key: "title", label: "글제목" },
        { key: "option1", label: "VS" },
        { key: "option2", label: "VS" },
        { key: "postDate", label: "작성일" },
        { key: "to", label: "바로가기" },
      ],
      listsol_tmp: [],
      listsol: [],
      userhitrate: "",
      usermilrank: "",
      usermilrankper: "",
    };
  },
  created() {
    window.scrollTo(0, 0);
    this.passwordSchema = new PV()
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
    this.userNo = sessionStorage.getItem("user")
      ? JSON.parse(sessionStorage.getItem("user")).userNo
      : false;
    if (!this.userNo && (alert("사용자정보가 없습니다. 로그인 하세요.") || 1)) {
      location.href = "/login";
    }
    axios
      .get(process.env.VUE_APP_API_URL + "/update/solrank", {
        params: {
          userNo: this.userNo,
        },
      })
      .then(() => {
        axios
          .get(process.env.VUE_APP_API_URL + "/account/user", {
            params: {
              userNo: this.userNo,
            },
          })
          .then(({ data }) => {
            sessionStorage.removeItem("user");
            sessionStorage.setItem("user", JSON.stringify(data.object));
            this.user = data.object;
            axios
              .get(process.env.VUE_APP_API_URL + "/post/search", {
                params: {
                  searchby: "userNickname",
                  searchText: this.user.userNickname,
                  orderby: "postdate",
                  limit: 5,
                  offset: 0,
                  tabOption: "1=1",
                },
              })
              .then(({ data }) => {
                this.myPostList = data.object;
                this.solomonhire();
              });
          });
      })
      .catch(() => {
        location.href = "/error/회원정보 조회 중 오류가 발생했습니다.";
      });
  },
  methods: {
    postDT(item) {
      let date = new Date(item);
      return date.toLocaleDateString() + " " + date.toLocaleTimeString();
    },
    solomon() {
      //솔로몬활동
      axios
        .get(process.env.VUE_APP_API_URL + "/account/uservote", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          this.listsol_tmp = data.object;
          this.listsol = this.listsol_tmp.reverse();
          this.listsol.splice("5");
          for (var i in this.listsol) {
            if (this.listsol[i].postTitle.length > 8) {
              this.listsol[i].postTitle =
                this.listsol[i].postTitle.substr(0, 8) + "...";
            }
          }
          this.milerankper();
        })
        .catch(() => {
          location.href = "/error/솔로몬 활동 에러";
        });
    },

    solomonhire() {
      //솔로몬 활동 적중률
      axios
        .get(process.env.VUE_APP_API_URL + "/account/userhitrate", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          var tmp = data.object;
          console.log(tmp);
          if (tmp == null) {
            tmp = 0;
          }
          this.userhitrate = tmp.toFixed(2) * 100;
          this.solomon();
        })
        .catch(() => {
          location.href = "/error/솔로몬 활동 적중률 에러";
        });
    },

    milerank() {
      //유저 마일리지 랭킹
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileagerank", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          this.usermilrank = data.object;
        })
        .catch(() => {
          location.href = "/error/유저 마일리지 랭킹 에러";
        });
    },

    milerankper() {
      //유저 마일리지 랭킹 퍼센트
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileagerankper", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          var tmp = data.object;
          this.usermilrankper = (tmp * 100).toFixed(2);
          this.milerank();
        })
        .catch(() => {
          location.href = "/error/유저 마일리지 랭킹 퍼센트";
        });
    },

    editmove() {
      location.href = "/mypage";
    },
    summonerAuth() {
      let err = false;
      this.summonerValid = !(
        !this.summoner &&
        ((this.summonerMsg = "소환사명을 입력해주세요."), (err = true))
      );
      if (!err) {
        axios
          .get(process.env.VUE_APP_API_URL + "/rank", {
            params: {
              summonerName: this.summoner,
              userNo: this.user.userNo,
            },
          })
          .then(() => {
            location.reload();
          });
      }
    },
    summonerReset() {
      axios
        .delete(process.env.VUE_APP_API_URL + "/rank", {
          params: { userNo: this.user.userNo },
        })
        .then(() => {
          location.reload();
        });
      this.user.summonerName = this.user.userGameTierStr = this.user.userGameRankStr = null;
      this.dataUpdate();
    },
    nicknameUpdate() {
      let err = false;
      this.nicknameValid = !(
        !this.nickname &&
        ((this.nicknameMsg = "닉네임을 입력해주세요."), (err = true))
      );
      if (!err) {
        axios
          .get(process.env.VUE_APP_API_URL + "/account/checkNickname", {
            params: {
              userNickname: this.nickname,
            },
          })
          .then((response) => {
            if (response.data.status) {
              this.nicknameCheck = false;
              this.nicknameValid = null;
              this.user.userNickname = this.nickname;
              this.dataUpdate();
            } else {
              this.nicknameMsg = "이미 등록된 닉네임입니다.";
              this.nicknameValid = false;
            }
          });
      }
    },
    showPasswordChkModal() {
      if (this.user.providerName) {
        this.errToast("소셜 회원입니다.", "비밀번호를 변경할 수 없습니다.");
      } else {
        this.$refs["passwordChkModal"].show();
      }
    },
    passwordCheck() {
      this.user.userPw == this.passwordChk
        ? (this.$refs["passwordChkModal"].hide(),
          this.$refs["passwordUpdateModal"].show())
        : ((this.passwordChkValid = false),
          (this.passwordChkMsg = "비밀번호가 일치하지 않습니다."));
    },
    passwordUpdate() {
      let err = false;
      this.passwordValid = !(
        (!this.password &&
          ((this.passwordMsg = "비밀번호를 입력해주세요."), (err = true))) ||
        (this.password &&
          !this.passwordSchema.validate(this.password) &&
          ((this.passwordMsg = "영어, 숫자 혼용 8자 이상으로 입력해주세요."),
          (err = true)))
      );
      this.passwordConfirmValid = !(
        (!this.passwordConfirm &&
          ((this.passwordConfirmMsg = "비밀번호를 확인해주세요."),
          (err = true))) ||
        (this.passwordConfirm &&
          this.password != this.passwordConfirm &&
          ((this.passwordConfirmMsg = "비밀번호와 비밀번호 확인이 다릅니다."),
          (err = true)))
      );
      if (!err) {
        this.user.userPw = this.password;
        this.dataUpdate();
      }
    },
    imgUp() {
      document.querySelector("#profil").click();
    },
    imgDel() {
      this.user.userImage = null;
      this.dataUpdate();
    },
    onFileSelected() {
      this.uploadImageFile = this.$refs.uploadImageFile.files[0];
      const fd = new FormData();
      fd.append("upLoadImage", this.uploadImageFile, this.user.userNo + ".jpg");
      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/upload", fd, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("succes");
          this.user.userImage = "/images/" + this.user.userNo + ".jpg";
          this.dataUpdate();
        })
        .catch(() => {
          console.log("fail");
        });
    },
    dataUpdate() {
      axios
        .put(process.env.VUE_APP_API_URL + "/account/", this.user)
        .then(() => {
          location.reload();
        })
        .catch(() => {
          console.log("fail");
        });
    },
    errToast(title, msg) {
      this.$bvToast.toast(msg, {
        title: title,
        toaster: "b-toaster-top-center",
        solid: true,
        appendToast: false,
        variant: "primary",
      });
    },
    deleteUser() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/account/", {
            params: {
              userNo: this.userNo,
            },
          })
          .then(() => {
            alert("탈퇴되었습니다. 이용해 주셔서 감사합니다.");
            let tk = JSON.parse(sessionStorage.getItem("user")).accesstoken;
            if (!tk) {
              sessionStorage.removeItem("user");
              location.href = "/";
            } else {
              axios
                .get(process.env.VUE_APP_API_URL + "/logout/oauth", {
                  params: {
                    accesstoken: tk,
                  },
                })
                .then(() => {
                  sessionStorage.removeItem("user");
                  location.href = "/";
                });
            }
          })
          .catch(() => {
            location.href = "/error/회원 탈퇴 중 문제가 발생했습니다.";
          });
      }
    },

    toDetail(postNo) {
      this.$router.push("/detail/" + postNo);
    },

    toResult(postNo) {
      this.$router.push("/result/" + postNo);
    },
  },
  computed: {
    imgURL() {
      return this.user.userImage
        ? process.env.VUE_APP_IMGUP_URL + this.user.userImage
        : "";
    },
    gongsaing() {
      return process.env.VUE_APP_IMGUP_URL + "/images/gongsaing.jpg";
    },
    gameRankImg() {
      return (
        process.env.VUE_APP_IMGUP_URL +
        "/images/emblems/" +
        (this.user.userGameTierStr
          ? "Emblem_" + this.user.userGameTierStr.toLowerCase() + ".png"
          : "default.png")
      );
    },
    gameRank() {
      return this.user.userGameTierStr
        ? this.user.userGameTierStr + " - " + this.user.userGameRankStr
        : "Unranked";
    },
    solRankImg() {
      return (
        process.env.VUE_APP_IMGUP_URL +
        "/images/emblems/" +
        (this.user.userSolTierStr
          ? "Emblem_" + this.user.userSolTierStr.toLowerCase() + ".png"
          : "default.png")
      );
    },
    solRank() {
      return this.user.userSolRankStr
        ? this.user.userSolTierStr + " - " + this.user.userSolRankStr
        : "Unranked";
    },
  },
};
</script>

<style>
@media screen and (min-width: 1024px) {
  #pad1 {
    margin-left: 15%;
  }
}
@media screen and (min-width: 1650px) {
  #pad2 {
    margin-left: 5%;
  }
}

@media screen and (max-width: 430px) {
  #font1 {
    font-size: 15px !important;
  }
}

.da {
  background-color: rgb(222, 226, 228);
}
.shadow1 {
  box-shadow: 5px 5px 5px;
}
</style>
