<template>
  <b-container>
    <b-card class="m-4">
      <!-- 메인 -->
      <b-aspect aspect="3.5:2" class="text-left p-2">
        <h3>
          <b-row align-h="between">
            <b-col> MBTI 테스트</b-col>
            <b-col class="text-right">
              <b-badge>Q. {{ index + 1 }}</b-badge>
            </b-col>
          </b-row>
        </h3>
        <h5>
          <b-jumbotron>{{ q[index].text }}</b-jumbotron>
          <b-form-group :label="index + 1 + '. ' + q[index].q">
            <b-form-radio v-model="ans[index]" value="1" size="lg">
              {{ q[index].a }}
            </b-form-radio>
            <b-form-radio v-model="ans[index]" value="-1" size="lg">
              {{ q[index].b }}
            </b-form-radio>
          </b-form-group>
        </h5>
        <br />
      </b-aspect>

      <!-- 하단부 -->
      <template v-slot:footer>
        <b-row align-v="end" class="text-center">
          <b-col cols="12">
            <b-button
              variant="primary"
              size="lg"
              @click="prev"
              class="mb-2 mr-2"
              :disabled="index == 0"
            >
              이전
            </b-button>
            <b-button
              variant="primary"
              size="lg"
              @click="next"
              class="mb-2"
              :disabled="ans[index] == 0"
            >
              다음
            </b-button>
          </b-col>
          <b-col cols="12">
            <b-progress :max="12" height="2px">
              <b-progress-bar :value="index + 1"></b-progress-bar>
            </b-progress>
          </b-col>
        </b-row>
      </template>
    </b-card>

    <!-- 결과 모달 -->
    <b-modal
      ref="result"
      title="내 MBTI 결과"
      size="lg"
      centered
      hide-footer
      no-close-on-backdrop
    >
      <b-row class="text-center">
        <b-col cols="12">
          <h4>{{ user.userNickname }}님의 MBTI는</h4>
          <br />
          <h4>{{ result }} 입니다!</h4>
          <br />
        </b-col>
        <b-col cols="12" class="mt-3">
          <b-button size="lg" block href="/mbti">
            내 MBTI와 잘 맞는 챔피언 추천받기
          </b-button>
        </b-col>
        <b-col cols="12" class="mt-3">
          <b-button size="lg" block href="/myprofile">
            내 프로필 페이지로
          </b-button>
        </b-col>
        <b-col cols="12" class="mt-3">
          <b-button size="lg" block href="/mbtisurvey"> 다시 보기 </b-button>
        </b-col>
      </b-row>
    </b-modal>
  </b-container>
</template>

<script>
import axios from "axios";
export default {
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
  },

  methods: {
    prev() {
      if (this.index != 0) this.index--;
    },
    next() {
      if (this.index == 11) {
        // 셋 씩 나눠서 더함
        let arr = [
          this.ans.slice(0, 3).reduce((a, b) => a + b),
          this.ans.slice(3, 6).reduce((a, b) => a + b),
          this.ans.slice(6, 9).reduce((a, b) => a + b),
          this.ans.slice(9, 12).reduce((a, b) => a + b),
        ];
        // 합계값이 양수면 왼쪽 음수면 오른쪽
        this.result =
          (arr[0] > 0 ? "E" : "I") +
          (arr[1] > 0 ? "N" : "S") +
          (arr[2] > 0 ? "T" : "F") +
          (arr[3] > 0 ? "J" : "P");

        // 업데이트
        axios
          .post(process.env.VUE_APP_DAPI_URL + "/mbti/update/", {
            userno: this.user.userNo,
            mbti: this.result,
          })
          .then(() => {
            axios
              .get(process.env.VUE_APP_API_URL + "/account/user", {
                params: {
                  userNo: this.user.userNo,
                },
              })
              .then(({ data }) => {
                // 최신화
                sessionStorage.removeItem("user");
                sessionStorage.setItem("user", JSON.stringify(data.object));
              });
          });

        // 결과 모달창 팝업
        this.$refs["result"].show();
      } else this.index++;
    },
  },

  data() {
    return {
      result: "",
      user: {},
      index: 0,
      ans: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      q: [
        {
          text:
            "오늘은 배우자의 고교 동창회 부부 동반 모임일이 1년에 한 번 정도 있는 정기 모임을 당신이 참석하는 건 처음이다. 처음에는 갈까 말까 망설였으나 배우자의 간곡한 부탁으로 참석하게 되었다. 모임에는 안면 있는 사람들도 몇몇 있지만, 대부분은 처음 만나는 사람들이다. 모임은 저녁 6시에 시작돼, 지금은 저녁 7시 정도 되었다.",
          q: "당신은 사람들과 어떤 방식으로 어울리는 것이 더 편합니까?",
          a: "일단 모임이 시작되면 스스로 나를 소개하는 편이다.",
          b:
            "일단 모임에 참석하면 다른 사람들이 나를 소개해줄 때까지 기다리는 편이다.",
        },
        {
          text:
            "파티가 계속되면서 이제는 분위기가 많이 무르익었다. 사람들끼리 서로 자유롭게 대화를 나누고 있다. 당신도 자유롭게 이동하면서 다름 사람들과 대화를 나눈다.",
          q: "당신은 주로 누구와 대화를 나눕니까?",
          a:
            "이전부터 알던 사람은 물론 낯선 사람들과 새로운 주제에 대해 이야기 하는데 거리낌이 없다.",
          b:
            "주로 전부터 알던 사람들과 이야기를 나누며, 낯선 사람에게는 내가 먼저 이야기를 걸지 않는 편이다.",
        },
        {
          text:
            "이제 시간이 흘러 모임이 끝날 때가 되었다. 새로운 사람도 많이 알게 되었으며, 이미 많은 대화를 나눈 상태이다. 다들 아쉬움을 남기면서 작별 인사를 하고 있다.",
          q: "당신에게 누군가 2차를 가자고 권유한다면 어떻게 하시겠습니까?",
          a:
            "파티의 여흥이 남아 있던 차에 흔쾌히 응하며, 다름 사람들도 함께 가자고 권유하면서 분위기를 주도한다.",
          b:
            "익숙한 사람들과의 조용한 2차는 가겠지만, 낯선 사람들과의 떠들썩한 2차는 부담스럽다.",
        },
        {
          text:
            "당신은 H대학교 신입생이다. H대학교는 누구나 자신이 원하는 대로, 관심 가는 대로 자유롭게 자신의 수강 과목을 선택할 수 있는 학교이다. 이제 신학기가 시작되면서 수강 신청을 할 때가 왔다. 이제 당신은 스스로 선택한 과목들을 들으면서 새롭게 대학생활을 시작해야 한다.",
          q: "당신은 새로운 대학생활을 앞두고, 어떤 기대를 하고 있습니까?",
          a:
            "대학생으로서 다양한 경험을 하며, 나의 목표에 부합되는 구체적이고 실질적인 정보와 지식을 학습할 것이다.",
          b:
            "대학생활은 나의 삶의 목표와 비전을 실천하기 위한 과정으로, 나의 진정한 의미를 탐색하는 시간이 될 것이다.",
        },
        {
          text:
            "자, 이제 수강 신청일이다. 당신은 여러 가지 과목들 중에서 자유롭게 선택해 수강 신청을 해야 한다.",
          q: "당신은 주로 어떤 과목에 더 호감을 느낍니까?",
          a: "실제적이며 현실적인 적용이 가능한 과목",
          b: "이론적이며 관념적인 면을 중요시하는 과목",
        },
        {
          text:
            "학교 수업이 끝나고 집에 가는 길에 어떤 사람이 도서관에 가는 길을 물었다. 당신은 그 사람에게 길을 가르쳐준다.",
          q: "당신은 어떤 방식으로 길을 가르쳐줍니까?",
          a:
            "여기서 왼쪽으로 돌아가시면 중앙 광장이 보이고요, 중앙 분수대를 왼쪽으로 끼고 돌아서 500미터 쯤 가시면 조그마한 돌담길이 나오는데, 그 길로 100미터 정도 가시면 오른쪽에 있는 웅장한 건물이 도서관입니다.",
          b:
            "일단 중앙 광장을 찾아서 그 뒤쪽으로 난 오솔길을 통해 가시면 바로 도서관이 나옵니다. 여기랑 대각선 반대쪽이라고 보시면 돼요.",
        },
        {
          text:
            '지금 당신은 며칠 후에 내야 할 리포트를 작성하기 위해 도서관으로 바삐 가고 있다. 그런데 그리 친하지 않은 과 친구가 어두운 표정으로 당신 앞에 나타났다. 그 친구는 머뭇거리더니 "혹시 시간 있니?"라고 조심스럽게 물었다.',
          q:
            "이때 당신이 시간적 여유가 별로 많지 않은 상황이라면, 어떻게 반응합니까?",
          a:
            '일단 어떤 일 때문인지 알아야 하기 때문에 "왜, 무슨 일인데?"라고 묻고, 중요한 일이 아니라면 "지금은 시간이 없는데, 다음에 이야기하자"라고 말한다.',
          b:
            '그 친구의 어두운 표정을 보고 "무슨 안 좋은 일 있니?"라고 걱정스러운 태도로 물은 후, 설사 중요한 일이 아니더라도 그 친구가 상처받거나 서운해 할까봐 부탁을 거절하는 것이 어렵다.',
        },
        {
          text:
            "친구가 안 좋은 일이 있다며 이야기를 좀 하자고 해서 그 부탁을 들어주었다. 학교 내 휴게실에 앉아 이야기를 나누는데, 그 친구가 두서없이 감정 섞인 말투로 자꾸 오락가락 이야기를 늘어놓고 있다.",
          q: "당신은 이런 친구에 대해 어떻게 반응합니까?",
          a:
            "구체적인 내용이나 안 좋은 일의 원인과 결과, 과정을 이해하기 위한 질문을 한다.",
          b:
            '일단 이해가 되지 않더라도 그 친구의 힘들었던 점에 대해 "저런, 힘들었겠다." 등과 같은 표현을 통해 공감하고 이해해 주려고 노력한다.',
        },
        {
          text:
            "한참을 이야기 한 후, 친구는 시간을 내주어 고맙다며 자리에서 일어났다. 당신은 친구에게 도움을 준 거 같아 마음이 뿌듯하다.",
          q: "당신은 어떤 순간에 친구에게 도움이 된다고 느낍니까?",
          a: "안 좋은 일의 원인을 밝혀주고 적절한 해결책을 제시해주었을 때",
          b: "그 친구의 어두운 표정이 환해지며 내게 고맙다고 이야기할 때",
        },
        {
          text:
            "당신은 친한 친구들과 3박 4일 일정의 여행을 가기로 했다. 설 시간을 맞추기가 어려워 힘들게 잡은 여행이라 모두들 설레는 마음이다.",
          q: "여행을 가기 전에 계획을 세운다면 당신은 어느 쪽에 가깝습니까.",
          a:
            "여행지의 특성과 예산 등을 고려해 가능한 한 꼼꼼하게 세부적인 일정을 체계적으로 계획한다.",
          b:
            "여행지와 일정 정도만 확정짓고 나머지는 상황에 따라 움직일 수 있도록 충분한 여유를 둔다.",
        },
        {
          text:
            "여행을 가기 전에 답사가 필요하지 않겠느냐는 제안이 나왔다. 그리고 답사를 갈 사람으로 당신이 선정되었다.",
          q: "답사를 간다면 당신은 어떤 태도로 가겠습니까?",
          a:
            "답사를 통해 여행 계획을 보충하고 현실적인 문제들(의료, 교통, 숙박시설 등)에 대한 정보를 확인한다. ",
          b:
            "답사라는 것은 불필요하다고 생각하지만, 일단 가야 한다면 답사도 또 하나의 여행이라는 생각에 즐기는 마음으로 갈 것이다.",
        },
        {
          text:
            "이제 친구들과 만나기로 한 날이 돌아왔다. 9시까지 기차역에서 모두 모이기로 했다.",
          q: "당신은 어떤 쪽에 가깝습니까?",
          a:
            "가능한 준비물은 전날 밤에 미리 챙겨놓고 자명종과 알람을 맞추고 잠들며, 여러 사람이 모이는 것이므로 좀 여유를 가지고 일찍 도착해야겠다고 생각한다.",
          b:
            "여행이란 일상으로부터의 탈출이므로 전날 밤은 새로운 여행에 대한 기대와 설레임을 가지고, 뭘 하며 즐겁게 보낼까를 상상하며 잠든다.",
        },
      ],
    };
  },
};
</script>

<style>
</style>
