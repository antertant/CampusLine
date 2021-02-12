import Vue from 'vue'
import Vuex from 'vuex'
import moduleList from "./Modules/moduleList";
import loginInfo from "./Modules/loginInfo";

Vue.use(Vuex)

const store = new Vuex.Store({
  strict: true,
  modules:{
      moduleList,
      loginInfo
  }
})

export default store;
