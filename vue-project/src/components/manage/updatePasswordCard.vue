<template>
 <b-card class="mx-auto">
   <form>
     <b-form-input
       type="password"
       placeholder="Enter your new password..."
       v-model="newPassword"
       @submit="submitNewPassword"
       @reset="resetPassword"></b-form-input>

     <b-button type="reset">Reset</b-button>
     <b-button type="submit">Submit</b-button>
   </form>
 </b-card>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "updatePasswordCard",
  data(){
    return{
      newPassword: ''
    }
  },
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  methods:{
    submitNewPassword() {
      axios
        .post('/updatepassword', null, {params:{
            username: this.currentUser,
            password: this.newPassword
          }})
        .then(response=>{
          console.log(response)
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    resetPassword() {
      this.newPassword = ''
    }
  }
}
</script>

<style scoped>

</style>
