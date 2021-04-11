<template>
 <b-card class="mx-auto">
   <template #header>
     <h5>Change Your Password</h5>
   </template>

   <b-form v-if="!successFlag"
           @submit.prevent="submitNewPassword"
           @reset="resetPassword">
<!--     Old password-->
     <b-form-group label-for="oldPassword"
                   label="Old Password">
       <b-form-input id="oldPassword"
                     class="mb-2"
                     :state="successFlag"
                     type="password"
                     placeholder="Enter your old password..."
                     v-model="oldPassword"></b-form-input>
       <b-form-invalid-feedback for="oldPassword">
         Old password is incorrect.
       </b-form-invalid-feedback>
     </b-form-group>
<!--     New password-->
     <b-form-group label-for="newPassword"
                   label="New Password"
                   description="Your new password must be 8-20 characters long,
                      contain letters and numbers, and must not
            contain spaces, special characters, or emoji.">
       <b-form-input id="newPassword"
                     class="mb-2"
                     :state="passwordState"
                     type="password"
                     placeholder="Enter your new password..."
                     v-model="newPassword"></b-form-input>
       <b-form-invalid-feedback for="newPassword">
         Invalid password format.
       </b-form-invalid-feedback>
     </b-form-group>

     <b-button class="float-right" type="submit" variant="primary">Submit</b-button>
   </b-form>

   <b-card-text v-if="successFlag" style="font-size: large; text-align: center">
     <em><b>Changed successfully!</b></em>
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
      oldPassword: '',
      newPassword: '',
      successFlag: null
    }
  },
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    }),
    passwordState() {
      let pwReg = /^[a-zA-Z0-9]{8,}$/
      if(!this.newPassword)
        return null
      else if(!pwReg.test(this.newPassword))
        return false
      else
        return true
    }
  },
  methods:{
    submitNewPassword(event) {
      event.preventDefault()
      if(this.passwordState !== true)
        return
      axios
        .post('/updatepassword', null, {params:{
            username: this.currentUser,
            password: this.newPassword,
            oldpassword: this.oldPassword
          }})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.successFlag = true
          else
            this.successFlag = false
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
