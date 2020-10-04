<template>
  <b-container class="bv-example-row bv-example-row-flex-cols">
    <div><img src="../../assets/img/picktitle.png" alt="" class="mt-5 mb-3"></div>
    <div><img src="../../assets/img/chamtitle.png" alt="" class="mb-5"></div>

  <!-- 1위 챔피언 -->
    <div class="box px-3 pb-3 chambox mb-5">
      <div>
        <div class="chamtitle pl-4"><p>FIRST PICK</p></div>
      </div>
      <b-row align-v="stretch" class="article mb-0">
        <b-col cols="4" class="d-flex justify-content-center">
          <div class="mt-5">
            <img
            class="chamimg"
            thumbnail
            width="300px"
            :src="cham_no1.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
            /> 
            <b-badge pill variant="warning" class="pickname">{{cham_no1.chamname}} | {{cham_no1.korname}}</b-badge>
            <b-progress class="mt-3">
              <b-progress-bar :value="cham_no1_rate" variant="info" striped :animated="animate">
                <span>승률: <strong>{{ cham_no1_rate }}%</strong></span>
              </b-progress-bar>
            </b-progress> 
          </div>
        </b-col>
        <b-col cols="8" class="sub">
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{user.userNickname}}</span>님이 가장 잘하는 챔피언은 <span class="text-warning">{{cham_no1.korname}}</span></h3></b-col>
          </b-row>
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{cham_no1.korname}}</span>의 승률은 {{cham_no1_rate}}%</h3></b-col>
          </b-row>
          <div class="simchams mt-5">
            <div class="simtitle mb-5"><p>비슷한 성향을 가진 챔피언들</p></div>
            <b-row align-v="center">
              <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[0].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[1].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[2].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            </b-row>
            <b-row align-v="center">
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[0].chamname}} | {{sim_info[0].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[1].chamname}} | {{sim_info[1].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[2].chamname}} | {{sim_info[2].korname}}</b-badge>
              </b-col>
            </b-row>
          </div>

        </b-col>
      </b-row>
    </div>

  <!-- 2위 챔피언 -->
    <div class="box px-3 pb-3 chambox mb-5">
      <div>
        <div class="chamtitle pl-3"><p>SECOND PICK</p></div>
      </div>
      <b-row align-v="stretch" class="article mb-0">
        <b-col cols="4" class="d-flex justify-content-center">
          <div class="mt-5">
            <img
            class="chamimg"
            thumbnail
            width="300px"
            :src="cham_no2.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
            /> 
            <b-badge pill variant="warning" class="pickname">{{cham_no2.chamname}} | {{cham_no2.korname}}</b-badge>
            <b-progress class="mt-3">
              <b-progress-bar :value="cham_no2_rate" variant="info" striped :animated="animate">
                <span>승률: <strong>{{ cham_no2_rate }}%</strong></span>
              </b-progress-bar>
            </b-progress> 
          </div>
        </b-col>
        <b-col cols="8" class="sub">
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{user.userNickname}}</span>님이 가장 잘하는 챔피언은 <span class="text-warning">{{cham_no2.korname}}</span></h3></b-col>
          </b-row>
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{cham_no2.korname}}</span>의 승률은 {{cham_no2_rate}}%</h3></b-col>
          </b-row>
          <div class="simchams mt-5">
            <div class="simtitle mb-5"><p>비슷한 성향을 가진 챔피언들</p></div>
            <b-row align-v="center">
              <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[3].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[4].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[5].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            </b-row>
            <b-row align-v="center">
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[3].chamname}} | {{sim_info[3].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[4].chamname}} | {{sim_info[4].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[5].chamname}} | {{sim_info[5].korname}}</b-badge>
              </b-col>
            </b-row>
          </div>

        </b-col>
      </b-row>
    </div>

  <!-- 3위 챔피언 -->
    <div class="box px-3 pb-3 chambox mb-5">
      <div>
        <div class="chamtitle pl-4"><p>THIRD PICK</p></div>
      </div>
      <b-row align-v="stretch" class="article mb-0">
        <b-col cols="4" class="d-flex justify-content-center">
          <div class="mt-5">
            <img
            class="chamimg"
            thumbnail
            width="300px"
            :src="cham_no3.imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
            /> 
            <b-badge pill variant="warning" class="pickname">{{cham_no3.chamname}} | {{cham_no3.korname}}</b-badge>
            <b-progress class="mt-3">
              <b-progress-bar :value="cham_no3_rate" variant="info" striped :animated="animate">
                <span>승률: <strong>{{ cham_no3_rate }}%</strong></span>
              </b-progress-bar>
            </b-progress> 
          </div>
        </b-col>
        <b-col cols="8" class="sub">
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{user.userNickname}}</span>님이 가장 잘하는 챔피언은 <span class="text-warning">{{cham_no3.korname}}</span></h3></b-col>
          </b-row>
          <b-row align-v="center" class="text-white">
              <b-col><h3><span class="text-warning">{{cham_no3.korname}}</span>의 승률은 {{cham_no3_rate}}%</h3></b-col>
          </b-row>
          <div class="simchams mt-5">
            <div class="simtitle mb-5"><p>비슷한 성향을 가진 챔피언들</p></div>
            <b-row align-v="center">
              <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[6].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[7].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            <b-col>
              <b-img
                style="border-style:ridge; border: #fcd000 3px ridge;"
                width="100%"
                :src="sim_info[8].imgsrc.replace('@/assets/img', '').replace('_1.', '_0.')"
              /> 
            </b-col>
            </b-row>
            <b-row align-v="center">
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[6].chamname}} | {{sim_info[6].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[7].chamname}} | {{sim_info[7].korname}}</b-badge>
              </b-col>
              <b-col>
                <b-badge pill variant="warning" class="pickname">{{sim_info[8].chamname}} | {{sim_info[8].korname}}</b-badge>
              </b-col>
            </b-row>
          </div>

        </b-col>
      </b-row>
    </div>
  </b-container>
</template>
<style>
  @import "../../assets/css/champion.css";
</style>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      user: {},
      recommand_cham_info: {},
      similar_cham_recommand: {},
      champions: {},
      cham_no1_rate:"",
      cham_no2_rate:"",
      cham_no3_rate:"",
      cham_no1_id:"",
      cham_no2_id:"",
      cham_no3_id:"",
      cham_no1: {},
      cham_no2: {},
      cham_no3: {},
      sim_no1: {},
      sim_no2: {},
      sim_no3: {},
      sim_info: [],
      max: 100
    };
  },

  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    console.log(this.user);
    axios
      .get(
        process.env.VUE_APP_DAPI_URL +
         "/recommand/" + 
         this.user.userNo + 
         "/champion"
         )
        .then((res) => {
          this.recommand_cham_info = res.data.recommand_cham_info;
          this.similar_cham_recommand = res.data.similar_cham_recommand[0];
          let temp = JSON.parse(this.recommand_cham_info[0].recommand_champion.replaceAll("'", '"'));
          this.champions = Object.entries(temp).sort((a, b) => b[1].localeCompare(a[1]));
          this.cham_no1_id = this.champions[0][0];
          this.cham_no2_id = this.champions[1][0];
          this.cham_no3_id = this.champions[2][0];
          this.cham_no1_rate = this.champions[0][1]*100;
          this.cham_no2_rate = this.champions[1][1]*100;
          this.cham_no3_rate = this.champions[2][1]*100;
          this.sim_no1 = this.similar_cham_recommand[this.cham_no1_id];
          this.sim_no2 = this.similar_cham_recommand[this.cham_no2_id];
          this.sim_no3 = this.similar_cham_recommand[this.cham_no3_id];
          this.callsim(this.sim_no1);
          this.callsim(this.sim_no2);
          this.callsim(this.sim_no3);
          this.callchampion();
        })
        .catch((err) => {
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
        });
  },

  computed: {

  },

  methods: {
    callchampion(){
      axios
      .get(
        process.env.VUE_APP_DAPI_URL +
         "/champions/"
         )
        .then((res) => {
          this.cham_no1 = res.data.find(o => o.id == this.cham_no1_id);
          this.cham_no2 = res.data.find(o => o.id == this.cham_no2_id);
          this.cham_no3 = res.data.find(o => o.id == this.cham_no3_id);
        })
        .catch((err) => {
          location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
        });
    },

    callsim(sim){
      sim.forEach(element => {
        axios
          .get(
            process.env.VUE_APP_DAPI_URL +
            "/champions/"
            )
            .then((res) => {
              this.sim_info.push(res.data.find(o => o.id == element));
            })
            .catch((err) => {
              location.href = "/error/로그인 중 서버 오류가 발생했습니다. " + err;
            });
          });
    },

  }
};
</script>

<style>
.chambox {
  border-style:ridge; 
  border: #e3d19e 1px ridge; 
  opacity:0.8; 
  background: linear-gradient(180deg, rgba(6,17,27,1) 0%, rgba(28,83,73,1) 100%)
}

.chamimg {
  border-style:ridge; 
  border: #fcd000 5px ridge; 
  border-radius:50%;
}

.chamtitle {
  position: relative;
  width: 200px;
  border-left: 30px solid transparent;
  border-right: 30px solid transparent;
  border-top: 30px solid #e3d19e;
  margin: 0px auto;
}

.chamtitle > p {
  position: absolute;
  top: -30px;
}

.simchams {
  border-top: #e3d19e solid 1px;
}

.simtitle {
  position: relative;
  width: 300px;
  border-left: 20px solid transparent;
  border-right: 20px solid transparent;
  border-top: 20px solid #e3d19e;
  margin: 0px auto;
}

.simtitle > p {
  position: absolute;
  top: -22px;
  left: 30px;
}
</style>
