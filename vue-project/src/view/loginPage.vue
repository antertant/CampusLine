<template>
  <div>
    <b-card style="max-width: 28rem; margin-top: 8rem" header-tag="loginHeader" class="mx-auto">
      <template #header>
        <h5 class="mb-0">Login</h5>
      </template>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
          id="input-group-1"
          label="Username:"
          label-for="input-1"
        >
          <b-form-input
            id="input-1"
            v-model="loginInfo.username"
            type="text"
            placeholder="Enter email address or username"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="Password" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="loginInfo.password"
            type="password"
            placeholder="Enter password"
            required
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="dark">Submit</b-button>
        <b-button type="reset" variant="secondary">Reset</b-button>
        <b-button href="/register" variant="info" style="float:right;">Register</b-button>
      </b-form>
    </b-card>

  </div>
</template>

<script>
export default {
  name: "loginPage",
  data(){
    return{
      loginInfo:{username:'',password:''},
      responseResult:[],
      show: true
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
          this.responseResult = JSON.stringify(successResponse.data)
          if(successResponse.data.code === 200){
            this.$router.replace({path:'/index'})
          }
        })
        .catch(failResponse=>{})
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
