<template>
 <b-card class="mx-auto">
   <template #header>
     <h5>Change your Password</h5>
   </template>

   <b-form v-if="!successFlag"
           @submit.prevent="submitNewPassword"
           @reset="resetPassword">
     <b-form-input class="mb-2"
       type="password"
       placeholder="Enter your new password..."
       v-model="newPassword"></b-form-input>

     <b-button class="float-right" type="submit" variant="primary">Submit</b-button>
   </b-form>

   <b-card-text v-if="successFlag">
     Changed successfully!
   </b-card-text>
 </b-card>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "updatePasswordCard",
  data(){
    return{
      newPassword: '',
      successFlag: false
    }
  },
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  methods:{
    submitNewPassword(event) {
      event.preventDefault()
      axios
        .post('/updatepassword', null, {params:{
            username: this.currentUser,
            password: this.newPassword
          }})
        .then(response=>{
          console.log(response)
          this.successFlag = true
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.resetPassword()
    },
    resetPassword() {
      this.newPassword = ''
    }
  }
}
</script>

<style scoped>

</style>
