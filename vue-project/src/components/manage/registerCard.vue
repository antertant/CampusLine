<template>
  <b-card style="max-width: 30rem; margin-top: 8rem" class="mx-auto">
<!--    Header-->
    <template #header>
      <h5 class="mb-0">
        <b-icon icon="person-lines-fill" variant="info"></b-icon>
        REGISTRATION
      </h5>
    </template>

    <form @submit="submitRegister" @reset="resetRegister">
<!--      Username-->
      <b-form-group label-for="registerName"
                    label="Username:"
                    description="Your username must be at least 3 characters long,
                    contains at least one letter, and must not contain spaces, special
                    characters except underline.">
        <b-form-input id="registerName"
                      v-model="registerForm.username"
                      required>
        </b-form-input>
      </b-form-group>
<!--      Password-->
      <b-form-group label-for="registerPassword"
                    label="Password"
                    description="Your password must be 8-20 characters long,
                    contain letters and numbers, and must not
          contain spaces, special characters, or emoji.">
        <b-form-input id="registerPassword"
                      v-model="registerForm.password"
                      type="password"
                      required>
        </b-form-input>
      </b-form-group>
<!--      Email Address-->
      <b-form-group label-for="registerEmail"
                    label="Email:"
                    description="Only email address from @uwaterloo.ca is acceptable.">
        <b-form-input id="registerEmail"
                      type="email"
                      placeholder="<example@uwaterloo.ca>"
                      v-model="registerForm.email"
                      required>
        </b-form-input>
      </b-form-group>
<!--      Buttons-->
      <div class="float-right">
        <b-button type="reset" variant="warning">Reset</b-button>
        <b-button type="submit" variant="primary">Submit</b-button>
      </div>

    </form>
  </b-card>
</template>

<script>
export default {
  name: "registerCard",
  data() {
    return {
      registerForm: {
        email: '',
        username: '',
        password: ''
      }
    }
  },
  methods: {
    submitRegister() {

    },
    resetRegister() {
      this.registerForm.email = ''
      this.registerForm.username = ''
      this.registerForm.password = ''
    }
  }
}
</script>

<style scoped>

</style>
