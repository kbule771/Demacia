<template>
  <div class="block1 d-flex">
    <div class="main" style="width:100%;">
      <div class="col-xl-4 col-lg-4 col-md-8 col-sm-12 m-auto">
        <div class="login-form" style="padding:50px; opacacy:0.9;">
          <img
            src="../../assets/img/damacia.png"
            alt="logo2"
            style="width:90%; margin-bottom:3em; margin-left:15px;"
            draggable="false"
          />

          <b-input-group class="form-group">
            <b-form-input
              type="text"
              placeholder="이메일을 입력해주세요"
              id="email"
              v-model="email"
              :state="emailValid"
              aria-describedby="emailFeedback"
              style="
              border-style:ridge;
              border: #fcd000  3px ridge;
              background-color:#1e2328;
              color:#cdbe91;"
            />
            <b-input-group-append>
              <b-button
                size="sm"
                @click="emailCheck"
                class="hoverbtn"
                style="background: linear-gradient(180deg, #1b2831 0%, #12384b 100%); border-style:ridge; border:#95ede7 3px ridge;"
                >중복확인</b-button
              >
            </b-input-group-append>
            <b-form-invalid-feedback id="emailFeedback">{{
              emailMsg
            }}</b-form-invalid-feedback>
          </b-input-group>

          <b-input-group class="form-group">
            <b-form-input
              type="text"
              placeholder="닉네임을 입력해주세요"
              id="nickname"
              v-model="nickname"
              :state="nicknameValid"
              aria-describedby="nicknameFeedback"
              style="
              border-style:ridge;
              border: #fcd000  3px ridge;
              background-color:#1e2328;
              color:#cdbe91;"
            />
            <b-input-group-append>
              <b-button
                size="sm"
                variant="secondary"
                class="hoverbtn"
                @click="nicknameCheck"
                style="background: linear-gradient(180deg, #1b2831 0%, #12384b 100%); border-style:ridge; border:#95ede7 3px ridge;"
                >중복확인</b-button
              >
            </b-input-group-append>
            <b-form-invalid-feedback id="nicknameFeedback">{{
              nicknameMsg
            }}</b-form-invalid-feedback>
          </b-input-group>
          <div class="form-group">
            <b-form-input
              type="password"
              placeholder="비밀번호 : 영어, 숫자 혼용 8자 이상"
              id="password"
              v-model="password"
              :state="pwValid"
              aria-describedby="pwFeedback"
              style="
              border-style:ridge;
              border: #fcd000  3px ridge;
              background-color:#1e2328;
              color:#cdbe91;"
            />
            <b-form-invalid-feedback id="pwFeedback">{{
              pwMsg
            }}</b-form-invalid-feedback>
          </div>
          <div class="form-group">
            <b-form-input
              type="password"
              placeholder="비밀번호 확인"
              id="passwordconfirm"
              v-model="passwordconfirm"
              :state="pwconValid"
              aria-describedby="pwconFeedback"
              style="
              border-style:ridge;
              border: #fcd000  3px ridge;
              background-color:#1e2328;
              color:#cdbe91;"
            />
            <b-form-invalid-feedback id="pwconFeedback">{{
              pwconMsg
            }}</b-form-invalid-feedback>
          </div>
          <div @click="agree">
            <b-form-checkbox
              id="checkTerm"
              v-model="checkTerm"
              :value="true"
              :unchecked-value="false"
              :disabled="checkTerm"
              style="color:#cdbe91;"
              >이용약관에 동의합니다.[필수]</b-form-checkbox
            >
          </div>
          <b-modal size="xl" ref="modalTerms" scrollable title="이용약관">
            제 1 장 총칙
            <ul>
              <li>제 1 조 (목적)</li>
              <ol>
                <li>
                  본 약관은 Demacia 사이트가 제공하는 모든 서비스(이하
                  "서비스")의 이용조건 및 절차, 이용자와 Demacia 사이트의 권리,
                  의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.
                </li>
              </ol>
              <li>제 2 조 (약관의 효력과 변경)</li>
              <ol>
                <li>
                  Demacia 사이트는 귀하가 본 약관 내용에 동의하는 경우 Demacia
                  사이트의 서비스 제공 행위 및 귀하의 서비스 사용 행위에 본
                  약관이 우선적으로 적용됩니다.
                </li>
                <li>
                  Demacia 사이트는 본 약관을 사전 고지 없이 변경할 수 있고
                  변경된 약관은 Demacia 사이트 내에 공지하거나 e-mail을 통해
                  회원에게 공지하며, 공지와 동시에 그 효력이 발생됩니다.
                  이용자가 변경된 약관에 동의하지 않는 경우, 이용자는 본인의
                  회원등록을 취소 (회원탈락)할 수 있으며 계속 사용의 경우는 약관
                  변경에 대한 동의로 간주 됩니다.
                </li>
              </ol>
              <li>제 3 조 (약관 외 준칙)</li>
              <ol>
                <li>
                  본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법,
                  정보통신윤리위원회심의규정, 정보통신 윤리강령, 프로그램보호법
                  및 기타 관련 법령의 규정에 의합니다.
                </li>
              </ol>
              <li>제 4 조 (용어의 정의)</li>
              본 약관에서 사용하는 용어의 정의는 다음과 같습니다.
              <ol>
                <li>
                  이용자 : 본 약관에 따라 Demacia 사이트가 제공하는 서비스를
                  받는 자.
                </li>
                <li>
                  가입 : Demacia 사이트가 제공하는 신청서 양식에 해당 정보를
                  기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는
                  행위.
                </li>
                <li>
                  회원 : Demacia 사이트에 개인 정보를 제공하여 회원 등록을 한
                  자로서 Demacia 사이트가 제공하는 서비스를 이용할 수 있는 자.
                </li>
                <li>
                  비밀번호 : 이용자와 회원ID가 일치하는지를 확인하고 통신상의
                  자신의 비밀보호를 위하여 이용자 자신이 선정한 문자와 숫자의
                  조합.
                </li>
                <li>탈퇴 : 회원이 이용계약을 종료시키는 행위.</li>
              </ol>
            </ul>
            제 2 장 서비스 제공 및 이용
            <ul>
              <li>제 5 조 (이용계약의 성립)</li>
              <ol>
                <li>
                  이용계약은 신청자가 온라인으로 Demacia 사이트에서 제공하는
                  소정의 가입신청 양식에서 요구하는 사항을 기록하여 가입을
                  완료하는 것으로 성립됩니다.
                </li>
                <li>
                  Demacia 사이트는 다음 각 호에 해당하는 이용계약에 대하여는
                  가입을 취소할 수 있습니다.
                </li>
                <ol>
                  <li>다른 사람의 명의를 사용하여 신청하였을 때</li>
                  <li>
                    이용계약 신청서의 내용을 허위로 기재하였거나 신청하였을 때
                  </li>
                  <li>
                    다른 사람의 Demacia 사이트 서비스 이용을 방해하거나 그
                    정보를 도용하는 등의 행위를 하였을 때
                  </li>
                  <li>
                    Demacia 사이트를 이용하여 법령과 본 약관이 금지하는 행위를
                    하는 경우
                  </li>
                  <li>
                    기타 Demacia 사이트가 정한 이용신청요건이 미비 되었을 때
                  </li>
                </ol>
              </ol>
              <li>제 6 조 (회원정보 사용에 대한 동의)</li>
              <ol>
                <li>
                  회원의 개인정보는 공공기관의 개인정보보호에 관한 법률에 의해
                  보호됩니다.
                </li>
                <li>
                  Demacia 사이트의 회원 정보는 다음과 같이 사용, 관리,
                  보호됩니다.
                </li>
                <ol>
                  <li>
                    개인정보의 사용 : Demacia 사이트는 서비스 제공과 관련해서
                    수집된 회원의 신상정보를 본인의 승낙 없이 제3자에게 누설,
                    배포하지 않습니다. 단, 전기통신기본법 등 법률의 규정에 의해
                    국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이
                    있거나 정보통신윤리 위원회의 요청이 있는 경우 또는 기타
                    관계법령에서 정한 절차에 따른 요청이 있는 경우, 귀하가
                    Demacia 사이트에 제공한 개인정보를 스스로 공개한 경우에는
                    그러하지 않습니다.
                  </li>
                  <li>
                    개인정보의 관리 : 귀하는 개인정보의 보호 및 관리를 위하여
                    서비스의 개인정보관리에서 수시로 귀하의 개인정보를
                    수정/삭제할 수 있습니다.
                  </li>
                  <li>
                    개인정보의 보호 : 귀하의 개인정보는 오직 귀하만이
                    열람/수정/삭제 할 수 있으며, 이는 전적으로 귀하의 ID와
                    비밀번호에 의해 관리되고 있습니다. 따라서 타인에게 본인의
                    ID와 비밀번호를 알려주어서는 안되며, 작업 종료 시에는 반드시
                    로그아웃 해 주시기 바랍니다.
                  </li>
                </ol>
                <li>
                  회원이 본 약관에 따라 이용신청을 하는 것은, Demacia 사이트가
                  신청서에 기재된 회원정보를 수집, 이용하는 것에 동의하는 것으로
                  간주됩니다.
                </li>
              </ol>
              <li>제 7 조 (사용자의 정보 보안)</li>
              <ol>
                <li>
                  가입 신청자가 Demacia 사이트 서비스 가입 절차를 완료하는
                  순간부터 귀하는 입력한 정보의 비밀을 유지할 책임이 있으며,
                  회원의 ID와 비밀번호를 사용하여 발생하는 모든 결과에 대한
                  책임은 회원 본인에게 있습니다.
                </li>
                <li>
                  ID와 비밀번호에 관한 모든 관리의 책임은 회원에게 있으며,
                  회원의 ID나 비밀번호가 부정하게 사용 되었다는 사실을 발견한
                  경우에는 즉시 Demacia 사이트에 신고하여야 합니다. 신고를 하지
                  않음으로 인한 모든 책임은 회원 본인에게 있습니다. 종료하지
                  아니함으로써 제3자가 귀하에 관한 정보를 이용하게 되는 등의
                  결과로 인해 발생하는 손해 및 손실에 대하여 Demacia 사이트는
                  책임을 부담하지 아니합니다.
                </li>
              </ol>
              <li>
                제 8 조 (서비스의 중지)
                <ol>
                  <li>
                    Demacia 사이트는 이용자가 본 약관의 내용에 위배되는 행동을
                    한 경우, 임의로 서비스 사용을 제한 및 중지할 수 있습니다.
                  </li>
                </ol>
              </li>
              <li>제 9 조 (서비스의 변경 및 해지)</li>
              <ol>
                <li>
                  Demacia 사이트는 귀하가 서비스를 이용하여 기대하는 손익이나
                  서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지
                  않으며, 회원이 본 서비스에 게재한 정보, 자료, 사실의 신뢰도,
                  정확성 등 내용에 관하여는 책임을 지지 않습니다. 의한 손해에
                  대하여 책임을 부담하지 아니합니다.
                </li>
              </ol>
              <li>제 10 조 (게시물의 저작권)</li>
              <ol>
                <li>
                  귀하가 게시한 게시물의 내용에 대한 권리는 귀하에게 있습니다.
                </li>
                <li>
                  Demacia 사이트는 게시된 내용을 사전 통지 없이 편집, 이동할 수
                  있는 권리를 보유하며, 게시판운영 원칙에 따라 사전 통지 없이
                  삭제할 수 있습니다.
                </li>
                <li>
                  귀하의 게시물이 타인의 저작권을 침해함으로써 발생하는 민,
                  형사상의 책임은 전적으로 귀하가 부담하여야 합니다.
                </li>
              </ol>
            </ul>
            제 3 장 의무 및 책임
            <ul>
              <li>제 11 조 (Demacia 사이트의 의무)</li>
              <ol>
                <li>
                  Demacia 사이트는 회원의 개인 신상 정보를 본인의 승낙없이
                  타인에게 누설, 배포하지 않습니다. 다만, 전기통신관련법령 등
                  관계법령에 의하여 관계 국가기관 등의 요구가 있는 경우에는
                  그러하지 아니합니다.
                </li>
              </ol>
              <li>제 12 조 (회원의 의무)</li>
              <ol>
                <li>
                  회원 가입 시에 요구되는 정보는 정확하게 기입하여야 합니다.
                  또한 이미 제공된 귀하에 대한 정보가 정확한 정보가 되도록 유지,
                  갱신하여야 하며, 회원은 자신의 ID 및 비밀번호를 제3자에게
                  이용하게 해서는 안됩니다.
                </li>
                <li>
                  회원은 Demacia 사이트의 사전 승낙 없이 서비스를 이용하여
                  어떠한 영리행위도 할 수 없습니다.
                </li>
              </ol>
            </ul>
            제 4 장 기타
            <ul>
              <li>제 13 조 (양도금지)</li>
              <ol>
                <li>
                  회원이 서비스의 이용권한, 기타 이용계약 상 지위를 타인에게
                  양도, 증여할 수 없습니다.
                </li>
              </ol>
              <li>제 14 조 (손해배상)</li>
              <ol>
                <li>
                  Demacia 사이트는 무료로 제공되는 서비스와 관련하여 회원에게
                  어떠한 손해가 발생하더라도 Demacia 사이트가 고의로 행한
                  범죄행위를 제외하고 이에 대하여 책임을 부담하지 아니합니다.
                </li>
              </ol>
              <li>제 15 조 (면책조항)</li>
              <ol>
                <li>
                  Demacia 사이트는 회원이나 제3자에 의해 표출된 의견을
                  승인하거나 반대하거나 수정하지 않습니다. Demacia 사이트는
                  어떠한 경우라도 회원이 서비스에 담긴 정보에 의존해 얻은
                  이득이나 입은 손해에 대해 책임이 없습니다. 금전적 거래등과
                  관련하여 어떠한 책임도 부담하지 아니하고, 회원이 서비스의
                  이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지
                  않습니다.
                </li>
              </ol>
              <li>제 16 조 (재판관할)</li>
              <ol>
                <li>
                  Demacia 사이트와 이용자 간에 발생한 서비스 이용에 관한 분쟁에
                  대하여는 대한민국 법을 적용하며, 본 분쟁으로 인한 소는
                  대한민국의 법원에 제기합니다.
                </li>
              </ol>
            </ul>
            <template v-slot:modal-footer="{ ok, close }">
              <!-- Emulate built in modal footer ok and cancel button actions -->
              <b-button size="sm" variant="primary" @click="ok"
                >약관에 동의합니다.</b-button
              >
              <b-button size="sm" variant="secondary" @click="close"
                >동의하고 말고요.</b-button
              >
            </template>
          </b-modal>
          <div
            @click="signup"
            class="btn mt-3 hoverbtn"
            style="width:100%; color:white;background: linear-gradient(180deg, #1b2831 0%, #12384b 100%); border-style:ridge; border:#95ede7 3px ridge;"
          >
            회원가입
          </div>
        </div>
        <b-modal
          size="sm"
          ref="wait"
          centered
          hide-footer
          hide-header
          no-close-on-backdrop
          no-close-on-esc
        >
          <div class="text-center">
            <b-spinner
              v-if="joinMsg == '회원가입 진행중입니다.'"
              variant="primary"
              label="Spinning"
            ></b-spinner>
            <br />
            <strong>{{ joinMsg }}</strong>
            <br v-if="joinMsg !== '회원가입 진행중입니다.'" />
          </div>
        </b-modal>
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
  name: "JoinView",

  data() {
    return {
      email: "",
      nickname: "",
      password: "",
      passwordconfirm: "",
      passwordSchema: new PV(),
      logo: process.env.VUE_APP_IMGUP_URL + "/images/login/loginlogo.PNG",
      emailValid: null,
      emailMsg: "",
      nicknameValid: null,
      nicknameMsg: "",
      pwValid: null,
      pwMsg: "",
      pwconValid: null,
      pwconMsg: "",
      checkTerm: false,
      joinMsg: "회원가입 진행중입니다.",
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
  },

  methods: {
    emailCheck() {
      let err = false;
      this.emailValid = !(
        (!this.email &&
          (this.errToast(
            "내용 입력",
            (this.emailMsg = "이메일을 입력해주세요.")
          ),
          (err = true))) ||
        (this.email &&
          !EmailValidator.validate(this.email) &&
          (this.errToast(
            "이메일 형식 오류",
            (this.emailMsg = "이메일 형식을 맞춰주세요.")
          ),
          (err = true)))
      );
      if (!err) {
        axios
          .get(process.env.VUE_APP_API_URL + "/account/checkEmail", {
            params: {
              userEmail: this.email,
            },
          })
          .then((response) => {
            if (response.data.status) {
              this.errToast("중복확인", "사용 가능한 이메일입니다.");
              this.emailValid = true;
              document.querySelector("#email").setAttribute("disabled", true);
            } else {
              this.errToast(
                "이메일 중복",
                (this.emailMsg = "이미 가입한 이메일입니다.")
              );
              this.emailValid = false;
            }
          });
      }
    },
    nicknameCheck() {
      let err = false;
      this.nicknameValid = !(
        !this.nickname &&
        (this.errToast(
          "내용 입력",
          (this.nicknameMsg = "닉네임을 입력해주세요.")
        ),
        (err = true))
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
              this.errToast("중복확인", "사용 가능한 닉네임 입니다.");
              this.nicknameValid = true;
              document
                .querySelector("#nickname")
                .setAttribute("disabled", true);
            } else {
              this.errToast(
                "닉네임 중복",
                (this.nicknameMsg = "이미 등록된 닉네임입니다.")
              );
              this.nicknameValid = false;
            }
          });
      }
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
    signup() {
      let err = false;
      this.emailValid = !(
        !this.emailValid &&
        (this.errToast(
          "이메일 중복확인",
          (this.emailMsg = "이메일 중복 확인해주세요")
        ),
        (err = true))
      );
      this.nicknameValid = !(
        !this.nicknameValid &&
        (this.errToast(
          "닉네임 중복확인",
          (this.nicknameMsg = "닉네임 중복 확인해주세요.")
        ),
        (err = true))
      );
      this.pwValid = !(
        (!this.password &&
          (this.errToast(
            "내용 입력",
            (this.pwMsg = "비밀번호를 입력해주세요.")
          ),
          (err = true))) ||
        (this.password &&
          !this.passwordSchema.validate(this.password) &&
          (this.errToast(
            "비밀번호 형식 오류",
            (this.pwMsg = "영어, 숫자 혼용 8자 이상으로 입력해주세요.")
          ),
          (err = true)))
      );
      this.pwconValid = !(
        (!this.passwordconfirm &&
          (this.errToast(
            "내용 입력",
            (this.pwconMsg = "비밀번호를 확인해주세요.")
          ),
          (err = true))) ||
        (this.passwordconfirm &&
          this.password != this.passwordconfirm &&
          (this.errToast(
            "내용 입력",
            (this.pwconMsg = "비밀번호와 비밀번호 확인이 다릅니다.")
          ),
          (err = true)))
      );
      !this.checkTerm &&
        this.errToast("약관 동의", "약관에 동의해 주세요.", (err = true));
      if (!err) this.dataSend();
    },
    agree() {
      this.checkTerm ? "" : this.$refs["modalTerms"].show();
    },
    dataSend() {
      this.$refs["wait"].show();
      axios
        .post(process.env.VUE_APP_API_URL + "/account/signup", {
          userEmail: this.email,
          userNickname: this.nickname,
          userPw: this.password,
        })
        .then((response) => {
          if (response.data.status) {
            this.joinMsg = this.nickname + "님, 회원가입을 축하합니다.";
            this.login();
          } else {
            location.href = "/error/회원가입 중 문제가 발생했습니다.";
          }
        });
    },
    login() {
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
            // 세션에 로그인 정보 추가
            sessionStorage.setItem("user", JSON.stringify(res.data.object));
            setTimeout(function() {
              location.href = "/";
            }, 2000);
          }
        })
        .catch(() => {
          console.log("err");
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. ";
        });
    },
  },
};
</script>
<style>
.hoverbtn:hover {
  opacity: 0.8;
}
</style>
