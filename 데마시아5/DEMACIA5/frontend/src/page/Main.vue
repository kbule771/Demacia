<template>
  <div class="container p-4">
    <b-row class="maintitle text-center">
      <img src="../assets/img/damacia.png" alt="" style="display:block; margin:100px auto 0px">
      <b-col cols="12" class="sub pb-4 mb-4">
        <img src="../assets/img/subtitle.png" alt="" style="display:block; margin:0px auto 50px">
      </b-col>
    </b-row>

    <img src="../assets/img/recommtitle.png" alt="" style="margin:50px 0px 0px">
    <b-row class="part">
      <b-col cols="12" lg="6">
        <div class="box" style="border-style:ridge; border: #fcd000 4px ridge; 
        opacity:0.8; background: linear-gradient(180deg, rgba(6,17,27,1) 0%, rgba(28,83,73,1) 100%);">
          <b-row>
            <b-col cols="12" class="m-2 text-left">
              <router-link :to="{name:constants.URL_TYPE.RECOMMEND.COMBINATION}">
                <kbd>조합 추천받기</kbd>
              </router-link>누르면 로그인 창으로 갑니다.
            </b-col>
            <b-col>
              <p style="color: #e3d19e">
                Use this area to describe your project. Lorem ipsum dolor
                sit amet, consectetur adipisicing elit. Est blanditiis
                dolorem culpa incidunt minus dignissimos deserunt repellat
                aperiam quasi sunt officia expedita beatae cupiditate,
                maiores repudiandae, nostrum, reiciendis facere nemo!
              </p>
            </b-col>
          </b-row>
        </div>
      </b-col>
      <b-col cols="12" sm="6">
        <div class="box" style="border-style:ridge; border: #fcd000 4px ridge; 
        opacity:0.8; background: linear-gradient(180deg, rgba(6,17,27,1) 0%, rgba(28,83,73,1) 100%);">
          <b-row>
            <b-col cols="12" class="m-2 text-left">
              <router-link :to="{name:constants.URL_TYPE.RECOMMEND.CHAMPION}">
                <kbd>챔피언 추천받기</kbd>
              </router-link>누르면 로그인 창으로 갑니다.
            </b-col>
            <b-col>
              <p style="color: #e3d19e">
                Use this area to describe your project. Lorem ipsum dolor
                sit amet, consectetur adipisicing elit. Est blanditiis
                dolorem culpa incidunt minus dignissimos deserunt repellat
                aperiam quasi sunt officia expedita beatae cupiditate,
                maiores repudiandae, nostrum, reiciendis facere nemo!
              </p>
            </b-col>
          </b-row>
        </div>
      </b-col>
      <b-col cols="12">
        <router-link :to="{name:constants.URL_TYPE.POST.MAIN}">
          <img src="../assets/img/votetitle.png" alt="" style="margin:50px 0px 0px">
        </router-link>
        <div class="box" style="border-style:ridge; border: #fcd000 4px ridge; 
        opacity:0.8; background: linear-gradient(180deg, rgba(6,17,27,1) 0%, rgba(28,83,73,1) 100%);">
          <b-row>
            <b-col cols="12">
              <b-table striped hover :items="lastPost" :fields="lastPostFields" v-if="lastPost" style="color: #e3d19e">
                <template v-slot:cell(postDate)="data">{{ postDT(data.value) }}</template>
                <template v-slot:cell(to)="data">
                  <router-link :to="'/detail/'+data.item.postNo">
                    <b-icon icon="arrow-right-circle" font-scale="2" style="color: #e3d19e"></b-icon>
                  </router-link>
                </template>
              </b-table>
              <div v-else>진행중인 투표글이 없습니다.</div>
            </b-col>
          </b-row>
        </div>
      </b-col>
      <b-col cols="12">
        <router-link :to="{name:constants.URL_TYPE.VIDEO.MAIN}">
          <img src="../assets/img/videotittle.png" alt="" style="margin:50px 0px 0px">
        </router-link>
        <div class="box" style="border-style:ridge; border: #fcd000 4px ridge; 
        opacity:0.8; background: linear-gradient(180deg, rgba(6,17,27,1) 0%, rgba(28,83,73,1) 100%);">
          <b-row>
            <b-col cols="12">
              <b-table striped hover :items="lastVideo" :fields="lastVideoFields" v-if="lastVideo" style="color: #e3d19e">
                <template v-slot:cell(date)="data">{{ postDT(data.value) }}</template>
                <template v-slot:cell(to)="data">
                  <router-link :to="'/vdetail/'+data.item.videoNo">
                    <b-icon icon="arrow-right-circle" font-scale="2" style="color: #e3d19e"></b-icon>
                  </router-link>
                </template>
              </b-table>
              <div v-else>진행중인 투표글이 없습니다.</div>
            </b-col>
          </b-row>
        </div>
      </b-col>
      <b-col cols="12">
        <a href="/welcome" >
          <img src="../assets/img/aboutus.png" alt="" style="display:block; margin:100px auto 50px">
        </a>
      </b-col>
    </b-row>
  </div>
</template>
<script>
import constants from "../lib/constants";
import axios from "axios";

export default {
  data() {
    return {
      constants,
      user: {},
      recommendChampion: [],
      recommendCombination: [],
      lastPost: [],
      lastPostFields: [
        { key: "postNo", label: "의뢰번호" },
        { key: "title", label: "글제목" },
        { key: "option1", label: "VS" },
        { key: "option2", label: "VS" },
        { key: "postDate", label: "작성일" },
        { key: "userNickname", label: "의뢰자" },
        { key: "to", label: "바로가기" },
      ],
      lastVideo: [],
      lastVideoFields: [
        { key: "videoNo", label: "의뢰번호" },
        { key: "title", label: "영상제목" },
        { key: "date", label: "작성일" },
        { key: "to", label: "바로가기" },
      ],
    };
  }, // data end
  created() {
    axios
      .get(process.env.VUE_APP_API_URL + "/post/search", {
        params: {
          searchby: "all",
          searchText: "",
          orderby: "postdate",
          limit: 5,
          offset: 0,
          tabOption: "NOW()>deadline",
        },
      })
      .then(({ data }) => {
        this.lastPost = data.object;
      });
    this.lastVideo = [
      {
        videoNo: 1,
        view: 30,
        thumbnail: "/images/4066v0qe1urlfof.jpg",
        title: "이런식으로 보입니다",
        userNickname: "관리자",
        userImage: "/images/3.jpg",
        date: "2020-08-21 11:40:30",
        totalLikes: 10,
        isPrivate: false,
      },
      {
        videoNo: 2,
        view: 22,
        thumbnail: "",
        title: "나만 보는 글은 자물쇠가 생겨요",
        userNickname: "가렌",
        userImage: "/images/2.jpg",
        date: "2020-08-24 21:22:11",
        totalLikes: 10,
        isPrivate: true,
      },
    ];
  }, //created end
  methods: {
    postDT(item) {
      let date = new Date(item);
      return date.toLocaleDateString();
    },
  }, //methods end
};
</script>
<style scoped>
@font-face {
  font-family: "Tmon";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.maintitle {
  color: aliceblue;
  font-size: 6rem;
  font-family: "Tmon";
  text-shadow: 4px 4px 4px black;
}
.sub {
  font-size: 3rem;
  font-family: "Droid Serif", "Segoe UI", Roboto, "Helvetica Neue", Arial,
    sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol",
    "Noto Color Emoji";
}
.part .box {
  margin: 1rem;
  width: 100%;
  background-color: white;
  box-shadow: 5px 5px 5px;
}
</style>