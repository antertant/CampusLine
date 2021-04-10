import axios from "axios";

export default {
  namespaced: true,

  state: {
    newMessageCounter: [],
    newChatMessageCounter: 0
  },
  getters:{
    getNewMessageCount: state => {
      return state.newMessageCounter
    },
    getNewChatMessageCount: state => {
      return state.newChatMessageCounter
    }
  },
  mutations: {
    setNewMessageCount (state, count) {
      return state.newMessageCounter = count
    },
    setNewChatMessageCount(state, count) {
      return state.newChatMessageCounter = count
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
    },
    getNewChatMessageCountFS(context, username) {
      axios
        .get('/countunreadchat_all', {params:{username: username}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            context.commit('setNewChatMessageCount', response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  }
}
