<template>
  <div style="height:100%;font-family:Tmon">
    <div class="container" style="margin-top:5%;">
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
          <div class="card">
            <!---->
            <div class="card-header text-center">
              <h5 class="title text-center" style="font-weight:bold;">게시글 작성</h5>
            </div>
            <div class="card-body">
              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="input-group-lg mb-3">
                    <label class="control-label" for="title">글 제목</label>
                    <!---->
                    <input placeholder="제목" id="title" v-model="title" class="form-control" />
                    <!---->
                  </div>
                </div>
                <div class="col-md-12 mb-3">
                  내 Sol 포인트 :
                  <Strong>{{userMileage}}</Strong>
                </div>
              </div>
              <div class="row">
                <div class="col-6 col-sm-3">
                  <div class="form-group">
                    <select v-model="selected" style="width:100px; height:40px;">
                      <option disabled value>투표 시간</option>
                      <option>1</option>
                      <option>4</option>
                      <option>8</option>
                      <option>24</option>
                    </select>

                    <!---->
                  </div>
                </div>
                <div class="col-6 col-sm-3">
                  <div class="form-group">
                    <select v-model="mileage" style="width:100px; height:40px;">
                      <option disabled value>Sol포인트</option>
                      <option>50</option>
                      <option>100</option>
                      <option>150</option>
                      <option>200</option>
                    </select>
                  </div>
                </div>
                <div class="col-6 col-sm-3">
                  <div class="form-group">
                    <select v-model="subject1" style="width:100px; height:40px; ">
                      <option disabled value>대상1</option>
                      <option>탑</option>
                      <option>미드</option>
                      <option>정글</option>
                      <option>원딜</option>
                      <option>서폿</option>
                    </select>
                  </div>
                </div>
                <div class="col-6 col-sm-3">
                  <div class="form-group">
                    <select v-model="subject2" style="width:100px; height:40px; ">
                      <option disabled value>대상2</option>
                      <option>탑</option>
                      <option>미드</option>
                      <option>정글</option>
                      <option>원딜</option>
                      <option>서폿</option>
                    </select>
                  </div>
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

              <div class="row">
                <div class="col-md-12 text-left">
                  <div class="form-group">
                    <!---->
                    <!---->
                    <label for="content">내용</label>
                    <textarea
                      rows="4"
                      cols="80"
                      placeholder="상대방에게 불쾌감을 주거나 주제에 맞지 않는 영상 및 글 게재 시 경고 없이 삭제 될 수 있습니다. 타인에 대한 따뜻한 배려로 아름다운 공간을 만들어 나갑시다."
                      class="form-control"
                      id="content"
                      v-model="content"
                    ></textarea>
                    <!---->
                  </div>
                </div>
              </div>
            </div>
            <!---->
            <div>
              <div class="card-footer d-flex justify-content-between">
                <router-link to="/list">
                  <div type="button" class="btn btn-primary">목록</div>
                </router-link>
                {{uploadMSG}}
                <div type="button" class="btn btn-primary" @click="dataCheck">등록</div>
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
  name: "Write",

  data: function () {
    return {
      no: JSON.parse(sessionStorage.getItem("user")).userNo,
      title: "",
      subject1: "",
      subject2: "",
      content: "",
      thumbnail: "",
      video: "",
      selected: "",
      mileage: "",
      userMileage: "",
      uploadMSG: "",
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
        .then((res) => {
          console.log(res);
          console.log("succes");
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
        .then((res) => {
          console.log(res);
          console.log("succes");
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
      !err && !this.subject1 && ((msg = "대상1을 입력해 주세요"), (err = true));
      !err && !this.subject2 && ((msg = "대상2을 입력해 주세요"), (err = true));
      !err &&
        this.subject1 == this.subject2 &&
        ((msg = "대상1과 대상2를 다르게 입력해 주세요"), (err = true));
      !err &&
        !this.selected &&
        ((msg = "투표시간을 설정해 주세요"), (err = true));
      !err &&
        !this.mileage &&
        ((msg = "Sol 포인트를 설정해 주세요"), (err = true));
      !err && !this.video && ((msg = "영상을 업로드 해주세요"), (err = true));
      !err && !this.content && ((msg = "내용을 입력해 주세요"), (err = true));
      !err &&
        this.userMileage - this.mileage < 0 &&
        ((msg = "마일리지가 부족합니다."), (err = true));
      if (err) alert(msg);
      else this.dataSend();
    }, //dataCheck

    dataSend() {
      axios
        .post(process.env.VUE_APP_API_URL + "/post/", {
          userNo: this.no,
          title: this.title,
          option1: this.subject1,
          option2: this.subject2,
          content: this.content,
          thumbnail: this.thumbnail,
          video: this.video,
          deadLineFront: this.selected,
          mileage: this.mileage,
        })
        .then(() => {
          alert("등록이 완료되었습니다.");
          location.href = "/list";
        })
        .catch(() => {
          location.href = "#/error/Error";
          window.location.reload();
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
