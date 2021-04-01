<template>
  <b-card class="mx-auto">
    <template #header>
      <h5>Find Your Password by Email</h5>
    </template>

    <b-form v-if="!successFlag"
            @submit.prevent="submitNewPassword">
      <!--      Email Address-->
      <b-form-group label-for="forgetEmail"
                    label="Email:"
                    description="Only email address from @uwaterloo.ca is acceptable.">
        <b-form-input id="forgetEmail"
                      type="email"
                      :state="emailState"
                      placeholder="<example@uwaterloo.ca>"
                      v-model="email"
                      required>
        </b-form-input>
        <b-form-invalid-feedback id="forgetEmail" v-if="!successFlag">
          Invalid E-mail format
        </b-form-invalid-feedback>
        <b-form-invalid-feedback id="forgetEmail" v-if="successFlag">
          {{forgetFeedback}}
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

      <!--     Verified Code-->
      <b-form-group label-for="forgetCode" label="Access Code:">
        <b-row>
          <b-col cols="4">
            <b-form-input id="forgetCode"
                          v-model="verifycode"
                          :state="verifyState"
                          required>
            </b-form-input>
            <b-form-invalid-feedback for="forgetCode">
              {{forgetFeedback}}
            </b-form-invalid-feedback>
          </b-col>
          <b-col>
            <b-button variant="info" @click="getEmailCode" :disabled="!emailState">Send Code</b-button>
          </b-col>
        </b-row>
      </b-form-group>
      <b-card-text style="color: green" v-if="sendSuccess">
        <em>Send Code Successful.</em>
      </b-card-text>

      <b-button class="float-right" type="submit" variant="primary">Submit</b-button>
    </b-form>

    <b-card-text v-if="successFlag" style="font-size: large; text-align: center">
      <em><b>Password Changed successfully!</b></em>
    </b-card-text>
  </b-card>
</template>

<script>
import axios from "axios";

export default {
  name: "forgetPasswordCard",
  data() {
    return{
      email: '',
      newPassword: '',
      verifycode: '',
      forgetFeedback: '',
      verifyState: null,
      sendSuccess: false,
      successFlag: false,
    }
  },
  computed: {
    emailState() {
      let emailReg = /^[a-zA-Z0-9_-]+@uwaterloo\.ca$/
      if(this.forgetFeedback === "E-mail address does not exist!")
        return false
      if(!this.email)
        return null
      else if(!emailReg.test(this.email))
        return false
      else
        return true
    },
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
  methods: {
    submitNewPassword() {
      axios
        .post('/updatepassword_email', null, {params:{
            email: this.email,
            verifycode: this.verifycode,
            password: this.newPassword
          }})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.successFlag = true
          else{
            this.verifyState = false
            this.forgetFeedback = 'Incorrect code or Email does not exist.'
          }
        })
    },
    getEmailCode() {
      // Communicating with back-end
      axios
        .post('/sendmail', null, {params:{email: this.email}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            if(response.data.data === "verify code send successful")
              this.sendSuccess = true
            return
          }
          else
            this.forgetFeedback = "E-mail address does not exist!"
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.$nextTick()
    }
  }
}
</script>

<style scoped>

</style>
