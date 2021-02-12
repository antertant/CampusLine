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
            //this.modulesList = JSON.stringify(response.data)
            let list = [
              {
                module_name: 'testMod',
                module_info: 'This is a test mod',
              },
              {
                module_name: 'errorMod',
                module_info: 'This is an error mod',
              }
            ]
            context.commit('setModuleList', list)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
