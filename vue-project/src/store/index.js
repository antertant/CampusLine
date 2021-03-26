import Vue from 'vue'
import Vuex from 'vuex'
import moduleList from "./Modules/moduleList";
import loginInfo from "./Profile/loginInfo";
import newMessage from "./Profile/newMessage"
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
      newMessage
  }
})

export default store;
