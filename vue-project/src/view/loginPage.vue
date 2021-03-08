<template>
  <login-card></login-card>
</template>

<script>
import LoginCard from "../components/manage/loginCard";
export default {
  name: "loginPage",
  components: {LoginCard},
  created() {
    document.title = 'UWSK - Sign in'
  },
}
</script>

<style scoped>

</style>
