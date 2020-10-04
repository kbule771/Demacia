import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
    userInfo: null
}

export default new Vuex.Store({
    state,
    mutations,
})