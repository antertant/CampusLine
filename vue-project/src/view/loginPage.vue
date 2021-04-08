<template>
  <div style="padding-top: 10rem; padding-bottom: 40rem">
    <login-card v-if="!loginState"></login-card>

    <b-card align="center" v-if="loginState" style="margin-top: 6rem">
      <h5>You have been signed in.</h5>
    </b-card>
  </div>
</template>

<script>
import LoginCard from "../components/manage/loginCard";
import {mapGetters} from "vuex";
export default {
  name: "loginPage",
  components: {LoginCard},
  computed:{
    ...mapGetters({
      loginState: "loginInfo/getLoginState"
    })
  },
  created() {
    document.title = 'CampusLine - Sign in'
  },
}
</script>

<style scoped>

</style>
