import axios from "axios";

export default {
  namespaced: true,

  state: {
    lifePostList: []
  },
  getters:{
    getLifePostList: state => {
      return state.lifePostList
    }
  },
  mutations: {
    setLifePostList(state, list) {
      return state.lifePostList = list
    }
  },
  actions: {
    getlifePostfromServer(context) {
      axios
        .get('/getmodule', {
          params:{
            choice: 1
          }
        })
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            context.commit('setLifePostList', response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
