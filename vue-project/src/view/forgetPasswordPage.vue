<template>
  <div style="padding-top: 12rem; padding-bottom: 28rem">
    <forget-password-card style="max-width: 40rem" />
  </div>
</template>

<script>
import ForgetPasswordCard from "@/components/manage/forgetPasswordCard";
export default {
name: "forgetPasswordPage",
  components: {ForgetPasswordCard},
  created() {
    document.title = 'CampusLine - Forget Password'
  }
}
</script>

<style scoped>

</style>
