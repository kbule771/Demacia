<template>
  <div class="col-12 col-xl-10 m-auto" id="body" style="font-family:Tmon">
    <div class="row">
      <div class="col-12" style="padding:2em;">
        <b-button pill size="lg" variant="dark" class="p-3" href="/write">
          <b-icon icon="archive-fill" />&nbsp;투표 의뢰
        </b-button>
      </div>
      <div class="col-0 col-lg-1 col-xl-1 mt-4"></div>
      <div class="col-12 col-lg-10 col-xl-10 mt-4">
        <b-row align-h="between">
          <b-col cols="12" sm="6" lg="5" xl="4">
            <b-input-group>
              <template v-slot:prepend style="width:100px; ">
                <b-form-select
                  v-model="searchby"
                  :options="searchOptions"
                  class="shadow1"
                ></b-form-select>
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
          <b-col
            cols="4"
            sm="3"
            lg="3"
            xl="2"
            style="margin-top:20px; margin-bottom:20px;"
          >
            <b-form-select
              v-model="orderby"
              :options="orderOptions"
              class="shadow1"
            ></b-form-select>
          </b-col>
        </b-row>
        <b-tabs
          card
          v-model="tabIndex"
          noody
          style="color:white; font-size:2.5vh;"
          content-class="mt-3"
          fill
        >
          <b-tab title="전체" />
          <b-tab title="진행중" />
          <b-tab title="마감" />
        </b-tabs>

        <!-- 리스트 시작 -->
        <b-card
          no-body
          class="overflow-hidden box-shadow text-left shadow1"
          style="margin-top:10px; margin-bottom:5%;"
          v-for="(item, $index) in list"
          :key="$index"
        >
          <b-row style="">
            <b-col xl="4" class="p-2 pl-4">
              <b-card-img
                id="sizepadding"
                :src="thumbURL(item)"
                :alt="item.postNo + '번째영상썸네일'"
                class="rounded w-100 h-100"
                style=" border-width:2px; max-height:255px; padding-right:17px;"
                @error="noimage"
              ></b-card-img>
            </b-col>
            <b-col style="padding-left:0">
              <div class="m-3">
                <div class="row" style="padding-right:7px;">
                  <div class="col-6 col-sm-5 col-md-5 col-lg-4 d-inline-block">
                    <b-avatar
                      variant="secondary"
                      :src="uImgURL(item)"
                      class="ml-3 mr-3"
                    ></b-avatar>
                    <strong>{{ item.userNickname }}</strong>
                  </div>

                  <div
                    class="col-6 col-sm-7 col-md-7 col-lg-8 d-flex row justify-content-end"
                    style="margin-right:0; padding-right:0px; "
                  >
                    <div class="col-0 col-md-2 col-lg-2 col-xl-2"></div>
                    <div
                      class="d-flex justify-content-end col-5 col-md-3 col-lg-3 col-xl-3"
                    >
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
                        <b-icon-caret-right-square-fill
                          class="h4"
                          style=" margin-right:10px;"
                        />
                        <p style=" font-size:2.5vh ">{{ item.view }}</p>
                      </div>
                    </div>
                    <div
                      class="d-flex justify-content-end col-5 col-md-3 col-lg-3 col-xl-3"
                      style="margin-right:0px; padding:0;"
                    >
                      <div style="text-align:center; font-family:digital">
                        <p
                          style="color:red;font-weight:bold;display:inline; margin-right:3px; font-size:20px;"
                        >
                          {{ item.mileage }}
                        </p>
                        <p
                          style="font-weight:bold;display:inline;font-size:20px;"
                        >
                          SOL
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <b-card-body style="padding-top:0;">
                <img
                  class="max-small"
                  v-if="isDeadLine(item)"
                  src="http://j3a304.p.ssafy.io/imgup/images/votenow1.jpg"
                  style=" position:absolute;
                  width: auto; 
                  height: auto;
                  max-width: 60%;
                  max-height: 60%; 
                  margin-left:20%; 
                  opacity:0.5;"
                />
                <img
                  class="max-small"
                  v-else
                  src="http://j3a304.p.ssafy.io/imgup/images/closed.jpg"
                  style=" position:absolute;
                  width: auto; 
                  height: auto;
                  transform: rotate(-3deg);
                  max-width: 70%;
                  max-height: 90%; 
                  margin-left:20%;
                  margin-bottom:4%;
                  margin-top:-5%;
                  opacity:0.5;"
                />
                <b-row>
                  <b-col md="12">
                    <b-card-title>
                      <div class="row">
                        <div class="col-0 col-sm-8 col-lg-8 col-xl-8">
                          <h3
                            style="display: -webkit-box; font-weight: bold; padding-right:15px; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 2em; height: 2em;margin-bottom:0;padding-left:10px;"
                          >
                            {{ item.title }}
                          </h3>
                        </div>
                        <div
                          class="col-12 col-sm-4 col-lg-4 col-xl-4 d-flex justify-content-end"
                        >
                          <div
                            style="display: -webkit-box; font-weight: bold; padding-right:15px; overflow: hidden;width:100%; text-overflow: ellipsis;
                          -webkit-line-clamp: 1;-webkit-box-orient: vertical;word-wrap:break-word; line-height: 2em; height: 2em;padding-right:5px;text-align:right;"
                          >
                            <h4 style="display:inline-block;font-weight: bold;">
                              {{ item.option1 }}
                            </h4>
                            <h3
                              style="display:inline-block;color:red;font-weight: bold; margin-left:5px;margin-right:5px;"
                            >
                              VS
                            </h3>
                            <h4 style="display:inline-block;font-weight: bold;">
                              {{ item.option2 }}
                            </h4>
                          </div>
                        </div>
                      </div>
                    </b-card-title>
                  </b-col>

                  <b-card-text style="width:90%; margin-left:5%">
                    <div style="width:100%"></div>
                  </b-card-text>
                </b-row>
                <div class="row mr-1" style="margin-top:5%;">
                  <div
                    class="d-flex col-5 col-sm-4 col-lg-4 col-xl-4"
                    style="padding-left:25px;"
                  >
                    <p
                      id="hide1"
                      style="font-weight: font-size:2.2vh; bold; margin-bottom:0px; padding-top:10px; padding-right:5px;"
                    >
                      의뢰번호
                    </p>
                    <p
                      style="font-weight:font-size:2.2vh; bold; margin-bottom:0px; padding-top:10px; "
                    >
                      No. {{ item.postNo }}
                    </p>
                  </div>
                  <div
                    class="col-2 col-sm-4 col-lg-4 col-xl-5"
                    style="text-align:center; font-size:2em"
                  ></div>
                  <div
                    class="btn btn-primary col-5 col-sm-4 col-lg-3 col-xl-3"
                    style="width:100%; max-width:200px;height:40px; cursor:Pointer; "
                    no-gutters
                    @click="toDetail(item.postNo)"
                    v-if="isDeadLine(item)"
                  >
                    투표하러가기
                  </div>
                  <div
                    v-else
                    class="btn btn-danger col-5 col-sm-4 col-lg-3 col-xl-3"
                    style="width:100%; max-width:200px;height:40px; cursor:Pointer; "
                    no-gutters
                    @click="toResult(item.postNo)"
                  >
                    결과보러가기
                  </div>
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

const limit = 3;

export default {
  data() {
    return {
      offset: 0,
      list: [],
      main: [],
      isSearch: false,
      searchby: "title",
      searchOptions: [
        { value: "title", text: "제목" },
        { value: "userNickname", text: "닉네임" },
        { value: "option", text: "투표대상" },
      ],
      searchText: "",
      orderby: "postdate",
      orderOptions: [
        { value: "postdate", text: "최신순" },
        { value: "view", text: "조회수순" },
        { value: "mileage", text: "SOL순" },
      ],
      state: "",
      tabIndex: 0,
      tabOption: "1=1",
    };
  },

  created() {
    window.scrollTo(0, 0);
    this.dlTime = "00:00:00";
    axios
      .get(process.env.VUE_APP_API_URL + "/post/search", {
        params: {
          searchby: "all",
          searchText: "",
          orderby: "postdate",
          limit: limit,
          offset: this.offset,
          tabOption: this.tabOption,
        },
      })
      .then(({ data }) => {
        if (data.object) {
          this.main.push(...data.object);
        }
      });
  },

  computed: {},

  methods: {
    infiniteHandler($state) {
      this.state = $state;
      axios
        .get(process.env.VUE_APP_API_URL + "/post/search", {
          params: {
            searchby: this.isSearch ? this.searchby : "all",
            searchText: this.searchText,
            orderby: this.orderby,
            limit: limit,
            offset: this.offset,
            tabOption: this.tabOption,
          },
        })
        .then(({ data }) => {
          if (data.object) {
            this.offset += limit;
            this.list.push(...data.object);
            $state.loaded();
          } else {
            $state.complete();
          }
        });
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

    toDetail(postNo) {
      // if (sessionStorage.getItem("user") == null) {
      //   this.$router.push("/login");
      // } else {
      this.$router.push("/detail/" + postNo);
      // }
    },

    toResult(postNo) {
      this.$router.push("/result/" + postNo);
    },

    isDeadLine(item) {
      let today = new Date();
      let dd = new Date(item.deadLine);

      if (today.getTime() > dd.getTime()) {
        if (item.isFinished == 0) {
          axios.get(process.env.VUE_APP_API_URL + "/update/mileage/", {
            params: {
              postNo: item.postNo,
            },
          });
        }
        return false;
      } else {
        return true;
      }
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
    tabIndex(i) {
      switch (i) {
        case 0:
          this.tabOption = "1=1";
          break;
        case 1:
          this.tabOption = "NOW()<deadline";
          break;
        case 2:
          this.tabOption = "NOW()>deadline";
          break;
      }
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
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
