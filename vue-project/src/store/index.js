import Vue from 'vue'
import Vuex from 'vuex'
import moduleList from "./Modules/moduleList";
import loginInfo from "./Profile/loginInfo";
import lifePostInfo from "./Posts/lifePostInfo";
import modulePostInfo from "./Posts/modulePostInfo";

Vue.use(Vuex)

const store = new Vuex.Store({
  strict: true,
  modules:{
      moduleList,
      loginInfo,
      lifePostInfo,
      modulePostInfo,
  }
})

export default store;
