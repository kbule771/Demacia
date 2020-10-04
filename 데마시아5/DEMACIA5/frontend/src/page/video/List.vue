<template>
  <div class="col-12 col-xl-10 m-auto" id="body" style="font-family:Tmon">
    <div class="row">
      <div class="col-12" style="padding:2em;">
        <b-button pill size="lg" variant="dark" class="p-3" href="/vwrite">
          <b-icon icon="camera-video-fill" />&nbsp;영상 의뢰
        </b-button>
      </div>
      <div class="col-0 col-lg-1 col-xl-1 mt-4"></div>
      <div class="col-12 col-lg-10 col-xl-10 mt-4">
        <b-row align-h="between">
          <b-col cols="12" sm="6" lg="5" xl="4">
            <b-input-group>
              <template v-slot:prepend style="width:100px; ">
                <b-form-select v-model="searchby" :options="searchOptions" class="shadow1"></b-form-select>
              </template>
              <b-form-input
                v-model="searchText"
                placeholder="검색"
                @keyup.enter="search"
                class="shadow1"
                style="padding-right:20px; border-radius:4px; margin-right:-7px;"
              ></b-form-input>
              <b-input-group-append>
                <b-button variant="dark" class="shadow1" @click="search">
                  <b-icon-search />
                </b-button>
              </b-input-group-append>
            </b-input-group>
          </b-col>
          <b-col cols="6" sm="3" lg="4" xl="6"></b-col>
          <!-- 정렬 드롭다운 -->
          <b-col cols="4" sm="3" lg="3" xl="2" style="margin-top:20px; margin-bottom:20px;">
            <b-form-select v-model="orderby" :options="orderOptions" class="shadow1"></b-form-select>
          </b-col>
        </b-row>

        <!-- 리스트 시작 -->
        <b-card
          no-body
          class="overflow-hidden box-shadow text-left shadow1"
          style="margin-top:10px; margin-bottom:5%;"
          v-for="(item, $index) in list"
          :key="$index"
        >
          <b-row style>
            <b-col xl="4" class="p-2 pl-4">
              <b-card-img
                id="sizepadding"
                :src="thumbURL(item)"
                :alt="item.videoNo + '번째영상썸네일'"
                class="rounded w-100 h-100"
                style=" border-width:2px; max-height:255px; padding-right:17px;"
                @error="noimage"
              ></b-card-img>
            </b-col>
            <b-col style="padding-left:0">
              <div class="m-3">
                <div class="row" style="padding-right:7px;">
                  <div class="col-6 col-sm-5 col-md-5 col-lg-4 d-inline-block">
                    <b-avatar variant="secondary" :src="uImgURL(item)" class="ml-3 mr-3"></b-avatar>
                    <strong>{{ item.userNickname }}</strong>
                  </div>

                  <div
                    class="col-6 col-sm-7 col-md-7 col-lg-8 d-flex row justify-content-end"
                    style="margin-right:0; padding-right:0px; "
                  >
                    <div class="col-0 col-md-2 col-lg-2 col-xl-2"></div>
                    <div class="d-flex justify-content-end col-5 col-md-3 col-lg-3 col-xl-3">
                      <div class="d-flex justify-content-end">
                        <b-icon-hand-thumbs-up
                          class="h4"
                          style="margin-right:5px; padding-top:3%; padding:0;"
                        />
                        <p style=" font-size:2.5vh ">{{ item.totalLikes }}</p>
                      </div>
                    </div>
                    <div
                      class="d-flex justify-content-end col-5 col-md-3 col-lg-3 col-xl-3"
                      style="margin-right:0px; padding:0;"
                    >
                      <div class="d-flex" style>
                        <b-icon-caret-right-square-fill class="h4" style=" margin-right:10px;" />
                        <p style=" font-size:2.5vh ">{{ item.view }}</p>
                      </div>
                    </div>
                    <div
                      class="d-flex justify-content-end col-5 col-md-3 col-lg-3 col-xl-3"
                      style="margin-right:0px; padding:0;"
                    >
                      <p style="font-size:2.5vh" v-if="item.isPrivate">
                        <b-icon icon="lock-fill" />
                      </p>
                    </div>
                  </div>
                </div>
              </div>
              <b-card-body style="padding-top:0;">
                <b-row>
                  <b-col md="12">
                    <b-card-title>
                      <div class="row">
                        <div class="col-0 col-sm-12 col-lg-12 col-xl-12">
                          <h3
                            style="display: -webkit-box; font-weight: bold; padding-right:15px; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 2em; height: 2em;margin-bottom:0;padding-left:10px;"
                          >{{ item.title }}</h3>
                        </div>
                      </div>
                    </b-card-title>
                  </b-col>

                  <b-card-text style="width:90%; margin-left:5%">
                    <div style="width:100%"></div>
                  </b-card-text>
                </b-row>
                <div class="row mr-1" style="margin-top:5%;">
                  <div class="d-flex col-5 col-sm-4 col-lg-4 col-xl-4" style="padding-left:25px;">
                    <p
                      id="hide1"
                      style="font-weight: font-size:2.2vh; bold; margin-bottom:0px; padding-top:10px; padding-right:5px;"
                    >의뢰번호</p>
                    <p
                      style="font-weight:font-size:2.2vh; bold; margin-bottom:0px; padding-top:10px; "
                    >No. {{ item.videoNo }}</p>
                  </div>
                  <div
                    class="col-2 col-sm-4 col-lg-4 col-xl-5"
                    style="text-align:center; font-size:2em"
                  ></div>
                  <div
                    class="btn btn-success col-5 col-sm-4 col-lg-3 col-xl-3"
                    style="width:100%; max-width:200px;height:40px; cursor:Pointer; "
                    no-gutters
                    @click="toResult(item.videoNo)"
                  >영상 분석 결과</div>
                </div>
              </b-card-body>
            </b-col>
          </b-row>
        </b-card>
        <infinite-loading @infinite="infiniteHandler"></infinite-loading>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      offset: 0,
      limit: 3,
      list: [],
      isSearch: false,
      searchby: "title",
      searchOptions: [
        { value: "title", text: "제목" },
        { value: "userNickname", text: "닉네임" },
      ],
      searchText: "",
      orderby: "date",
      orderOptions: [
        { value: "date", text: "최신순" },
        { value: "view", text: "조회수순" },
      ],
      state: "",
    };
  },

  created() {
    window.scrollTo(0, 0);
  },

  computed: {},

  methods: {
    infiniteHandler($state) {
      this.state = $state;
      this.list = [
        {
          videoNo: 1,
          view: 30,
          thumbnail: "/images/4066v0qe1urlfof.jpg",
          title: "이런식으로 보입니다",
          userNickname: "관리자",
          userImage: "/images/3.jpg",
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
          totalLikes: 10,
          isPrivate: true,
        },
      ];
      axios
        .get(process.env.VUE_APP_API_URL + "/video/search", {
          params: {
            searchby: this.isSearch ? this.searchby : "all",
            searchText: this.searchText,
            orderby: this.orderby,
            limit: this.limit,
            offset: this.offset,
          },
        })
        .then(({ data }) => {
          if (data.object) {
            this.offset += this.limit;
            this.list.push(...data.object);
            $state.loaded();
          } else {
            $state.complete();
          }
        });
      $state.complete();
    },
    search() {
      if (!this.searchText) window.location.reload();
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
    },
    noimage(event) {
      event.target.src = process.env.VUE_APP_IMGUP_URL + "/images/noimage.png";
    },
    uImgURL(item) {
      return process.env.VUE_APP_IMGUP_URL + item.userImage;
    },
    thumbURL(item) {
      return process.env.VUE_APP_IMGUP_URL + item.thumbnail;
    },

    toResult(videoNo) {
      this.$router.push("/vdetail/" + videoNo);
    },
  }, //end of method
  watch: {
    orderby(option) {
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
      return option;
    },
  }, // end watch
};
</script>

<style>
.shadow1 {
  box-shadow: 5px 5px 5px;
}
.shadow2 {
  box-shadow: 7px 7px 7px;
}
.carousel-caption {
  top: 0;
  bottom: auto;
}
@media screen and (max-width: 580px) {
  #hide1 {
    display: none;
  }
}
@media screen and (min-width: 1400px) {
  #sizepadding {
    padding: 0 !important;
  }
}
.blue-color {
  background-color: aqua;
}

.card-header {
  border-bottom: 3px solid black;
}
.nav-link.active {
  border-left: 3px solid;
  border-right: 3px solid;
  border-top: 3px solid;

  border-bottom: 3px solid;
  margin-bottom: -3px;
  border-radius: 10px 10px 3px 3px;
}
.nav-link {
  color: black;
  font-weight: bold;
}
.nav-tabs .nav-item.show .nav-link,
.nav-tabs .nav-link.active {
  color: white;
  border-top-color: black;
  border-left-color: black;
  border-right-color: black;
  border-bottom-color: black;
  background-color: black;
  font-weight: bold;
}
.tab-pane {
  padding: 0;
}
</style>
