import axios from "axios";

export default {
  namespaced: true,

  state: {
    newMessageCounter: []
  },
  getters:{
    getNewMessageCount: state => {
      return state.newMessageCounter
    }
  },
  mutations: {
    setNewMessageCount (state, count) {
      return state.newMessageCounter = count
    }
  },
  actions: {
    getNewMessageCountFS(context, username) {
      axios
        .get('/getcountnew', {params:{username: username}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            context.commit('setNewMessageCount', response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
