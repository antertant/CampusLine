<template>
  <div>
<!--    Register Card-->
    <b-card style="max-width: 30rem;"
            class="mx-auto"
            header-text-variant="light"
            header-bg-variant="dark"
            v-if="registerFeedback!=='Registration Successful!'">
  <!--    Header-->
      <template #header>
        <h5 class="mb-0">
          <b-icon icon="card-list" variant="danger"></b-icon>
          REGISTRATION
        </h5>
      </template>

      <b-form @submit="submitRegister" @reset="resetRegister">
  <!--      Username-->
        <b-form-group label-for="registerName"
                      label="Username:"
                      description="Your username must be at least 3 characters long,
                      start with letters, and must not contain spaces, special
                      characters except underline.">
          <b-form-input id="registerName"
                        :state="usernameState"
                        v-model="registerForm.username"
                        required>
          </b-form-input>
          <b-form-invalid-feedback id="registerName" v-if="!registerFeedbackState">
            Invalid username format
          </b-form-invalid-feedback>
          <b-form-invalid-feedback id="registerName" v-if="registerFeedbackState">
            {{registerFeedback}}
          </b-form-invalid-feedback>
        </b-form-group>
  <!--      Password-->
        <b-form-group label-for="registerPassword"
                      label="Password"
                      description="Your password must be 8-20 characters long,
                      contain letters and numbers, and must not
            contain spaces, special characters, or emoji.">
          <b-form-input id="registerPassword"
                        :state="passwordState"
                        v-model="registerForm.password"
                        type="password"
                        required>
          </b-form-input>
          <b-form-invalid-feedback id="registerPassword" v-if="!registerFeedbackState">
            Invalid password format
          </b-form-invalid-feedback>
          <b-form-invalid-feedback id="registerPassword" v-if="registerFeedbackState">
            {{registerFeedback}}
          </b-form-invalid-feedback>
        </b-form-group>
  <!--      Email Address-->
        <b-form-group label-for="registerEmail"
                      label="Email:"
                      description="Only email address from @uwaterloo.ca is acceptable.">
          <b-form-input id="registerEmail"
                        type="email"
                        :state="emailState"
                        placeholder="<example@uwaterloo.ca>"
                        v-model="registerForm.email"
                        required>
          </b-form-input>
          <b-form-invalid-feedback id="registerEmail" v-if="!registerFeedbackState">
            Invalid E-mail format
          </b-form-invalid-feedback>
          <b-form-invalid-feedback id="registerEmail" v-if="registerFeedbackState">
            {{registerFeedback}}
          </b-form-invalid-feedback>
        </b-form-group>
  <!--      Verify Code-->
        <b-form-group label-for="registerCode" label="Access Code:">
          <b-row>
            <b-col cols="4">
              <b-form-input id="registerCode"
                            v-model="registerForm.verifycode"
                            :state="verifyState"
                            required>
              </b-form-input>
            </b-col>
            <b-col>
              <b-button variant="warning" @click="getEmailCode" :disabled="!emailState">Send Code</b-button>
            </b-col>
          </b-row>
          <b-form-invalid-feedback for="registerCode">
            {{registerFeedback}}
          </b-form-invalid-feedback>
        </b-form-group>
        <b-card-text v-if="sendSuccess">
          <em>Send Code Successful.</em>
        </b-card-text>
  <!--      Buttons-->
        <div class="float-right">
          <b-button type="reset" variant="dark">Reset</b-button>
          <b-button type="submit" variant="danger" :disabled="!allReadyState">Submit</b-button>
        </div>

      </b-form>
    </b-card>

<!--    Register Success Card-->
    <b-card style="max-width: 30rem; margin-top: 8rem; text-align: center"
            class="mx-auto"
            v-if="registerFeedback==='Registration Successful!'">
      <b-card-text style="font-size: larger">
        <b><em>{{registerFeedback}}</em></b>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "registerCard",
  data() {
    return {
      registerForm: {
        email: '',
        username: '',
        password: '',
        verifycode: ''
      },
      sendSuccess: false,
      verifyState: null,
      registerFeedbackState: false,
      registerFeedback: ''
    }
  },
  computed:{
    emailState() {
      let emailReg = /^[a-zA-Z0-9_-]+@uwaterloo\.ca$/
      if(this.registerFeedback === "E-mail existed!")
        return false
      if(!this.registerForm.email)
        return null
      else if(!emailReg.test(this.registerForm.email))
        return false
      else
        return true
    },
    usernameState() {
      let userReg = /^[a-zA-Z][a-zA-Z0-9_]{2,}$/
      if(this.registerFeedback === "Username existed!")
        return false
      if(!this.registerForm.username)
        return null
      else if(!userReg.test(this.registerForm.username))
        return false
      else
        return true
    },
    passwordState() {
      let pwReg = /^[a-zA-Z0-9]{8,}$/
      if(!this.registerForm.password)
        return null
      else if(!pwReg.test(this.registerForm.password))
        return false
      else
        return true
    },
    allReadyState() {
      return this.passwordState & this.usernameState & this.emailState
    }
  },
  methods: {
    submitRegister(event) {
      event.preventDefault()
      // If any format is invalid, return
      if(!(this.emailState === true | this.usernameState === true | this.passwordState === true))
        return
      // communicating with back end
      axios
        .post('/register', null, {params:{
            username: this.registerForm.username,
            password: this.registerForm.password,
            email: this.registerForm.email,
            verifycode: this.registerForm.verifycode
          }})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.registerFeedback = 'Registration Successful!'
            return
          }
          else if(response.data.data === "wrong verify code!"){
            this.verifyState = false
            this.registerForm.password = ''
            this.registerFeedback = "Invalid verify code!"
          }
          else if(response.data.data === "email exist!"){
            this.registerForm.password = ''
            this.registerFeedback = "E-mail existed!"
          }
          else if(response.data.data === "username exist!"){
            this.registerForm.password = ''
            this.registerFeedback = "Username existed!"
          }
          this.registerFeedbackState = true
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getEmailCode() {
      // Communicating with back-end
      axios
        .post('/sendmail', null, {params:{email: this.registerForm.email}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            if(response.data.data === "verify code send successful")
              this.sendSuccess = true
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.$nextTick()
    },
    resetRegister() {
      this.registerForm.email = ''
      this.registerForm.username = ''
      this.registerForm.password = ''
      this.registerForm.verifycode = ''
      this.registerFeedback = ''
      this.emailState = null
      this.usernameState = null
      this.passwordState = null
      this.verifyState = null
      this.registerFeedbackState = false
    },
  },
  mounted() {
    this.registerForm.email = ''
    this.registerForm.username = ''
    this.registerForm.password = ''
    this.registerForm.verifycode = ''
    this.registerFeedback = ''
    this.emailState = null
    this.usernameState = null
    this.passwordState = null
    this.verifyState = null
    this.registerFeedbackState = false
  }
}
</script>

<style scoped>

</style>
