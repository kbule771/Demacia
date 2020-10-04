<template>
  <div style="height:100%">
    <div class="container" style="margin-top:5%;">
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
          <div class="card">
            <!---->
            <div class="card-header text-center">
              <h5 class="title" style="font-weight:bold;">영상 수정</h5>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-8 pr-md-1 text-left">
                  <div class="input-group">
                    제목 :
                    <input placeholder="제목" id="title" v-model="title" class="form-control" />
                  </div>
                </div>
                <div class="col-md-1 px-md-1 text-left">
                  <div class="form-group"></div>
                </div>
              </div>
              <div class="row">
                <div class="col-6 col-md-3 pr-md-1 text-left">
                  <div class="form-group"></div>
                </div>
                <div class="col-6 col-md-3 pl-md-1 text-left">
                  <div class="form-group"></div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group">썸네일 : {{ thumbnail }}</div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group">
                    비디오 : {{ videolink }}
                    <!---->
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group"></div>
                </div>
              </div>
            </div>
            <!---->
            <div>
              <div class="card-footer d-flex justify-content-between">
                <router-link to="/vlist">
                  <div type="button" class="btn btn-primary">목록</div>
                </router-link>
                <div type="button" class="btn btn-primary" @click="edit">수정</div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-2"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      videoNo: this.$route.params.videoNo,
      userNo: "",
      title: "",
      thumbnail: "",
      videolink: "",
      isPrivate: false,
    };
  },

  created() {
    console.log(this.postNo);
    axios
      .get(process.env.VUE_APP_API_URL + "/post/detail", {
        params: {
          postNo: this.videoNo,
        },
      })
      .then(({ data }) => {
        this.userNo = data.object.userNo;
        this.title = data.object.title;
        this.content = data.object.content;
        this.thumbnail = data.object.thumbnail;
        this.option1 = data.object.option1;
        this.option2 = data.object.option2;
        this.videolink = process.env.VUE_APP_IMGUP_URL + data.object.video;
        this.mileage = data.object.mileage;
        this.selected = data.object.deadLineFront;
      });
    // .catch(() => {
    //   location.href = "/error/글 내용을 불러오다 실수해버렸어요";
    // });
  },

  methods: {
    edit() {
      axios
        .put(process.env.VUE_APP_API_URL + "/video/", {
          postNo: this.postNo,
          userNo: this.userNo,
          title: this.title,
          content: this.content,
        })
        .then(() => {
          alert("수정 완료");
          location.href = "/vdetail/" + this.videoNo;
        });
      // .catch(() => {
      //   location.href = "#/error/Error";
      //   window.location.reload();
      // });
      location.href = "/vdetail/" + this.videoNo;
    },
  },
};
</script>

<style>
.border1 {
  border-left-width: 50px !important;
}
</style>