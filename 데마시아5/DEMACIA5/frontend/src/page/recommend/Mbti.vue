<template>
  <b-container class="bv-example-row bv-example-row-flex-cols">
    <b-card class="m-2">
      <b-row align-v="start">
        <b-col><h1>MBTI 추천</h1></b-col>
      </b-row>

      <b-row align-v="start">
        <b-col>
          <h2>{{ user.userNickname }}님의 MBTI는 '{{ user.mbti }}' 입니다.</h2>
        </b-col>
      </b-row>
      <h4 class="text-center"><br />[여기에 {{ user.mbti }}에 대한 설명 입력]</h4>
      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>{{ user.mbti }}에 어울리는 챔피언</h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.mbti_chams"
              :key="$index"
              class="text-left"
            >
              <b-img
                thumbnail
                width="100%"
                :src="
                  item.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>

      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>1. 극강의 조합 : {{ chams.good_chams[0].mbti }}</h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.good_chams"
              :key="$index"
              class="text-left"
            >
              <b-img
                thumbnail
                width="100%"
                :src="
                  item.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>

      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>2. 최악의 조합 : {{ chams.bad_chams[0].mbti }}</h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.bad_chams"
              :key="$index"
              class="text-left"
            >
              <b-img
                thumbnail
                width="100%"
                :src="
                  item.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>
    </b-card>
  </b-container>
</template>

<script>
import "../../assets/css/mbti.css";
import axios from "axios";

export default {
  data() {
    return {
      user: {},
      chams: {},
    };
  },

  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    axios
      .get(
        process.env.VUE_APP_DAPI_URL +
          "/mbti/" +
          this.user.mbti +
          // 중요
          "/"
        // 중요
      )
      .then((res) => {
        this.chams = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {},
  methods: {},
};
</script>

<style>
</style>
