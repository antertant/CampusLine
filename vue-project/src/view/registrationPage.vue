<template>
  <register-card style="padding-bottom: 24rem; padding-top: 12rem" />
</template>

<script>
import RegisterCard from "@/components/manage/registerCard";
export default {
  name: "registrationPage",
  components: {RegisterCard},
  created() {
    document.title = 'CampusLine - Registration'
  },
}
</script>

<style scoped>

</style>
