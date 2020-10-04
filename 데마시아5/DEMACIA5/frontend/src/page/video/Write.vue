<template>
  <div style="height:100%;font-family:Tmon">
    <div class="container" style="margin-top:5%;">
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
          <div class="card">
            <!---->
            <div class="card-header text-center">
              <h5 class="title text-center" style="font-weight:bold;">영상 등록</h5>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-12 text-right">
                  <b-form-checkbox
                    id="checkbox-private"
                    v-model="isPrivate"
                    name="checkbox-private"
                    :value="true"
                    :unchecked-value="false"
                  >나만 보기</b-form-checkbox>
                </div>
                <div class="col-md-12 text-left">
                  <div class="input-group-lg mb-3">
                    <label class="control-label" for="title">영상 제목</label>
                    <!---->
                    <input placeholder="제목" id="title" v-model="title" class="form-control" />
                    <!---->
                  </div>
                </div>

                <div class="col-md-12 mb-3">
                  내 Sol 포인트 :
                  <Strong>{{userMileage}}</Strong>
                  <p style="color:red;">※ 영상 등록은 50 Sol이 필요합니다.</p>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group">
                    <label class="control-label" for="imgURL">썸네일 이미지</label>
                    <!---->
                    <input
                      type="file"
                      id="imgURL"
                      ref="uploadimgURL"
                      @change="onImgSelected"
                      accept="image/*"
                      aria-describedby="addon-right addon-left"
                      placeholder="썸네일 이미지"
                      class="form-control"
                    />
                    <!---->
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group">
                    <label class="control-label">영상 업로드</label>
                    <!---->
                    <input
                      type="file"
                      id="videoURL"
                      ref="uploadvideoURL"
                      @change="onVideoSelected"
                      accept="video/*"
                      aria-describedby="addon-right addon-left"
                      placeholder="영상 업로드"
                      class="form-control"
                    />
                    <!---->
                  </div>
                </div>
              </div>
            </div>
            <!---->
            <div>
              <div class="card-footer d-flex justify-content-between">
                <router-link to="/vlist">
                  <div type="button" class="btn btn-primary">목록</div>
                </router-link>
                {{uploadMSG}}
                <div type="button" class="btn btn-primary" @click="dataCheck">등록</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "Write",

  data: function () {
    return {
      no: JSON.parse(sessionStorage.getItem("user")).userNo,
      title: "",
      mileage: 50,
      thumbnail: "",
      video: "",
      userMileage: "",
      uploadMSG: "",
      isPrivate: false,
    };
  }, //data
  created() {
    window.scrollTo(0, 0);
    this.getUserMileage();
  },
  methods: {
    getUserMileage() {
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileage", {
          params: {
            userNo: this.no,
          },
        })
        .then((res) => {
          console.log(res.data.object);
          this.userMileage = res.data.object;
        });
    },
    generateRandom(min, max) {
      var ranNum = Math.floor(Math.random() * (max - min + 1)) + min;
      return ranNum;
    },

    onImgSelected() {
      this.uploadimgURL = this.$refs.uploadimgURL.files[0];
      this.onSaveImg();
    },

    onVideoSelected() {
      this.uploadvideoURL = this.$refs.uploadvideoURL.files[0];
      this.onSaveVideo();
    },

    onSaveImg() {
      const fdi = new FormData();
      var fnimg =
        this.generateRandom(0, 9999) +
        Math.random().toString(36).substr(2, 11) +
        ".jpg";
      fdi.append("upLoadThum", this.uploadimgURL, fnimg);

      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/uploadthum", fdi, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("success");
          this.thumbnail = "/images/" + fnimg;
        })
        .catch(() => {
          console.log("fail");
        });
    },

    onSaveVideo() {
      const fdv = new FormData();
      var fnvid =
        this.generateRandom(0, 9999) +
        Math.random().toString(36).substr(2, 11) +
        ".mp4";
      fdv.append("upLoadVideo", this.uploadvideoURL, fnvid);
      this.uploadMSG = "업로드 중...";
      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/uploadvideo", fdv, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("success");
          this.video = "/images/" + fnvid;
          this.uploadMSG = "";
        })
        .catch(() => {
          console.log("fail");
        });
    },

    dataCheck() {
      let err = false;
      let msg = "";
      !this.title && ((msg = "제목 입력해주세요"), (err = true));
      !err && !this.video && ((msg = "영상을 업로드 해주세요"), (err = true));
      !err &&
        this.userMileage - this.mileage < 0 &&
        ((msg = "마일리지가 부족합니다."), (err = true));
      if (err) alert(msg);
      else this.dataSend();
    }, //dataCheck

    dataSend() {
      axios
        .post(process.env.VUE_APP_API_URL + "/video/", {
          userNo: this.no,
          title: this.title,
          thumbnail: this.thumbnail,
          video: this.video,
        })
        .then(() => {
          alert("등록이 완료되었습니다.");
          location.href = "/vlist";
        })
        .catch(() => {
          location.href = "/error/영상 업로드에 실패했습니다.";
        });
    },
  },
};
</script>

<style>
.button1 {
  display: inline-block;
  outline: none;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  font: 14px/100% Arial, Helvetica, sans-serif;
  padding: 0.5em 2em 0.55em;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
  -webkit-border-radius: 0.5em;
  -moz-border-radius: 0.5em;
  border-radius: 0.5em;
  -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}
.button1:hover {
  text-decoration: none;
}
.button1:active {
  position: relative;
  top: 1px;
}
.blue {
  color: #d9eef7;
  border: solid 1px #0076a3;
  background: #0095cd;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#00adee),
    to(#0078a5)
  );
}
.blue:hover {
  background: #8bc1ee;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#9acaf1),
    to(#6bafe7)
  );
}
.blue:active {
  color: #8bc1ee;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#9acaf1),
    to(#6bafe7)
  );
}
</style>
