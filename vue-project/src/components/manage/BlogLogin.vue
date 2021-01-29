<template>
  <div>
    <blog-header></blog-header>
    <hr>
    <div>
      username:<input type="text" v-model="loginInfo.username" placeholder="Please enter your username"/>
      <br><br>
      password:<input type="password" v-model="loginInfo.password" placeholder="Please enter your password"/>
      <br><br>
      <!--login: 登录事件-->
      <button @click="login">sign in</button>
      <br><br>
      Backend-Response:
      <textarea cols="30" rows="10" v-model="responseResult"></textarea>
    </div>
    <hr>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import BlogHeader from "../common/BlogHeader";
import BlogFooter from "../common/BlogFooter";
export default {
  name: "BlogLogin",
  components: {BlogFooter, BlogHeader},
  data(){
    return{
      loginInfo:{username:'',password:''},
      responseResult:[]
    }
  },
  methods:{
    login(){
      // http request-url&params
      this.$axios
      .post('/login',{
        username:this.loginInfo.username,
        password:this.loginInfo.password
      })
      .then(successResponse=>{
        this.responseResult = JSON.stringify(successResponse.data)
        if(successResponse.data.code === 200){
          this.$router.replace({path:'/index'})
        }
      })
      .catch(failResponse=>{})
    }
  }
}
</script>

<style scoped>

</style>
