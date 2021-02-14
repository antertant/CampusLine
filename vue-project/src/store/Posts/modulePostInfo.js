import axios from "axios";

export default {
  namespaced: true,

  state: {
    modulePostList: [],
    moduleName: ''
  },
  getters:{
    getModulePostList: state => {
      return state.modulePostList
    }
  },
  mutations: {
    setModulePostList(state, list) {
      return state.modulePostList = list
    }
  },
  actions: {
    getModulePostfromServer(context, name) {
      axios
        .get('/getposts', {
          params:{
            module_name: name
          }
        })
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            context.commit('setModulePostList', response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
