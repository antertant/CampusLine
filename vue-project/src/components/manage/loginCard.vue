<template>
  <div id="loginCard">
    <b-card style="max-width: 28rem; margin-top: 8rem" header-tag="loginHeader" class="mx-auto">

      <b-alert v-model="showLoginError" variant="danger" dismissible fade>
        Incorrect username/password.
      </b-alert>

<!--      Card Header-->
      <template #header>
        <h5 class="mb-0">
          <b-icon icon="card-checklist" variant="primary"></b-icon>
          Login
        </h5>
      </template>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
          id="input-group-1"
          label="Username:"
          label-for="input-1"
        >

<!--          Username/Email input-->
          <b-form-input
            id="input-username"
            v-model="loginInfo.username"
            type="text"
            placeholder="Enter email address or username"
            required
          ></b-form-input>
        </b-form-group>

<!--        Password input-->
        <b-form-group id="input-group-2" label="Password" label-for="input-2">
          <b-form-input
            id="input-password"
            v-model="loginInfo.password"
            type="password"
            placeholder="Enter password"
            required
          ></b-form-input>
        </b-form-group>

        <div class="mb-2">
          <a href="/forgetpw">Forget your password?</a>
        </div>

<!--        Buttons-->
        <b-button id="loginSubmit" type="submit" variant="primary">Login</b-button>
        <b-button id="loginReset" type="reset" variant="warning">Reset</b-button>
        <b-button id="loginRegister" href="/register" variant="info" style="float:right;">Register</b-button>
      </b-form>
    </b-card>

  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
name: "loginCard",
  data(){
    return{
      loginInfo:{username:'',password:''},
      responseResult:[],
      show: true,
      showLoginError: false
    }
  },
  methods:{
    onSubmit(event) {
      event.preventDefault()
      // http request-url&params
      this.$axios
        .post('/login',{
          username:this.loginInfo.username,
          password:this.loginInfo.password
        })
        .then(successResponse=>{
          console.log(successResponse)
          if(successResponse.data.code === 200){
            //this.responseResult = JSON.stringify(successResponse.data)
            this.$router.replace({path:'/home'})
            this.$store.commit('loginInfo/setLUName', this.loginInfo.username)
            this.$nextTick(()=>{
              this.$store.dispatch("newMessage/getNewMessageCountFS", this.loginInfo.username)
            })
          }
          else{
            this.showLoginError = true
            this.loginInfo.username = ''
            this.loginInfo.password = ''
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    onReset(event) {
      event.preventDefault()
      this.loginInfo.username = ''
      this.loginInfo.password = ''
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style scoped>

</style>
