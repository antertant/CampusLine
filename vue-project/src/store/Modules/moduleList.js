import axios from "axios";

export default {
  namespaced: true,

  state: {
    moduleList: []
  },
  getters:{
    getModuleList: state => {
      return state.moduleList
    }
  },
  mutations: {
    setModuleList(state, list) {
      return state.moduleList = list
    }
  },
  actions: {
    getModulefromServer(context) {
      axios
        .get('/getmodule', {
          params:{
            choice: 2
          }
        })
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            context.commit('setModuleList', response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
