<template>
  <div class="block1 d-flex">
    <div class="main" style="width:100%;">
      <div class="col-xl-4 col-lg-6 col-md-8 col-sm-12 m-auto">
        <div class="login-form shadow1" style="background-color:white;padding:50px">
          <div @click="imgUp" style="cursor:pointer;">
            <b-avatar
              badge-variant="dark"
              badge-offset="-10px"
              variant="dark"
              :src="imgURL"
              size="10rem"
              class="mb-4"
            >
              <template v-slot:badge>
                <b-icon font-scale="0.8" icon="upload"></b-icon>
              </template>
            </b-avatar>
          </div>
          <input
            type="file"
            class="form-control"
            placeholder="프로필 등록"
            id="profil"
            ref="uploadImageFile"
            @change="onFileSelected"
            accept="image/*"
            style="display:none"
          />
          <form>
            <h4>이메일</h4>
            <div class="form-group" style="margin-bottom:4%;">
              <input type="text" class="form-control" id="email" :value="email" disabled />
            </div>
            <h4>비밀번호</h4>
            <b-button
              :class="(visible ? null : 'collapsed') + ' mb-2'"
              :aria-expanded="pwUpdate ? 'true' : 'false'"
              aria-controls="collapse-4"
              @click="pwUpdate = !pwUpdate"
            >비밀번호 변경</b-button>
            <b-collapse id="collapse-4" v-model="pwUpdate" class="mt-2 mb-2">
              <b-card>
                <div class="form-group mb-2">
                  <input
                    type="password"
                    class="form-control"
                    placeholder="비밀번호 : 영어, 숫자 혼용 8자 이상"
                    id="password"
                    v-model="password"
                  />
                </div>

                <div class="form-group mb-2">
                  <input
                    type="password"
                    class="form-control"
                    placeholder="비밀번호 확인"
                    id="passwordconfirm"
                    v-model="passwordconfirm"
                  />
                </div>
              </b-card>
            </b-collapse>

            <h3>랭크 인증</h3>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                placeholder="닉네임을 입력하세요"
                id="nickname"
                ref="uploadnickname"
              />
            </div>
            <div class="d-flex justify-content-between" style="margin-top:10%">
              <div @click="delt" class="btn btn-secondary" style="width:30%;margin-left:5%;">탈퇴</div>
              <div @click="edit" class="btn btn-primary" style="width:30%;margin-right:5%">수정</div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/user.css";
import axios from "axios";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
export default {
  name: "MypageView",
  data: () => {
    return {
      no: JSON.parse(sessionStorage.getItem("user")).userNo,
      email: "",
      nickname: "",
      password: "",
      passwordconfirm: "",
      profil: "",
      passwordSchema: new PV(),
      userImage: "",
      userCreateDate: "",
      userGameRank: "",
      userSolRank: "",
      pwUpdate: false,
    };
  },
  created() {
    window.scrollTo(0, 0);
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();

    axios
      .get(process.env.VUE_APP_API_URL + "/account/user", {
        params: {
          userNo: this.no,
        },
      })
      .then(({ data }) => {
        this.email = data.object.userEmail;
        this.nickname = data.object.userNickname;
        this.userImage = data.object.userImage;
        this.userCreateDate = data.object.userCreateDate;
        this.userGameRank = data.object.userGameRank;
        this.userSolRank = data.object.userSolRank;
      })
      .catch(() => {
        location.href = "/error/회원정보 조회 중 오류가 발생했습니다.";
      });
  },

  methods: {
    onFileSelected() {
      this.uploadImageFile = this.$refs.uploadImageFile.files[0];
    },

    onSave() {
      const fd = new FormData();
      fd.append("upLoadImage", this.uploadImageFile, this.no + ".jpg");

      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/upload", fd, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          console.log("succes");
          this.userImage = "/images/" + this.no + ".jpg";
        })
        .catch(() => {
          console.log("fail");
        });
    },

    edit() {
      let err = false;
      let msg = "";
      !this.email && ((msg = "이메일 입력해주세요"), (err = true));
      !err &&
        !EmailValidator.validate(this.email) &&
        ((msg = "메일 형식을 맞춰주세요"), (err = true));
      !err && !this.nickname && ((msg = "닉네임을 입력해주세요"), (err = true));
      !err &&
        !this.password &&
        ((msg = "비밀번호를 입력해주세요"), (err = true));
      !err &&
        !this.passwordconfirm &&
        ((msg = "비밀번호를 확인해주세요"), (err = true));
      !err &&
        !this.passwordSchema.validate(this.password) &&
        ((msg = "비밀번호 형식을 숫자+문자 8자리로 맞춰주세요"), (err = true));
      !err &&
        this.password != this.passwordconfirm &&
        ((msg = "비밀번호와 비밀번호 확인이 다릅니다."), (err = true));
      if (err) alert(msg);
      else this.dataSend();
    },

    dataSend() {
      this.onSave();
      axios
        .put(process.env.VUE_APP_API_URL + "/account/", {
          userNo: this.no,
          userEmail: this.email,
          userNickname: this.nickname,
          userPw: this.password,
          userImage: this.userImage,
          userCreateDate: this.userCreateDate,
          userGameRank: this.userGameRank,
          userSolRank: this.userSolRank,
        })
        .then((response) => {
          if (response.data.data == "email") {
            alert("이메일 중복입니다");
          } else if (response.data.data == "nickname") {
            alert("닉네임 중복입니다");
          } else {
            alert("수정 성공");
            sessionStorage.removeItem("user");
            location.href = "/";
          }
        })
        .catch((error) => {
          if (error.message == "Request failed with status code 404") {
            location.href = "#/error/PageNotFound";
          } else {
            location.href = "#/error/Error";
          }
        });
    },

    delt() {
      if (confirm("회원탈퇴 하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/account/", {
            params: {
              userNo: this.no,
            },
          })
          .then(() => {
            alert("탈퇴완료");
            // 세션삭제
            sessionStorage.removeItem("user");
            location.href = "/";
          })
          .catch(() => {
            alert("error!");
          });
      }
    },
    imgUp() {
      document.querySelector("#profil").click();
    },
  },
  computed: {
    imgURL() {
      return process.env.VUE_APP_IMGUP_URL + this.userImage;
    },
  },
};
</script>
<style>
.shadow1 {
  box-shadow: 7px 7px 7px;
}
</style>