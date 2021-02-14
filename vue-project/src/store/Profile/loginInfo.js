export default {
  namespaced: true,

  state: {
    loginUserName: '',
    loginState: false
  },
  getters:{
    getLUName: state => {
      if(!state.loginUserName){
        state.loginUserName = localStorage.getItem('loginUserName')
      }
      return state.loginUserName
    },
    getLoginState: state => {
      if(!state.loginState){
        if(localStorage.getItem('loginState') === 'true') {
          state.loginState = true
        }
      }
      return state.loginState
    }
  },
  mutations: {
    setLUName(state, name) {
      state.loginState = true
      state.loginUserName = name
      localStorage.loginUserName = name
      localStorage.loginState = true
    },
    userLogout(state) {
      state.loginState = false
      state.loginUserName = ''
      localStorage.loginUserName = ''
      localStorage.loginState = false
    }
  }
}
