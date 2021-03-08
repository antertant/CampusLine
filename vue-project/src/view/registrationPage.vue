<template>
  <register-card></register-card>
</template>

<script>
import RegisterCard from "@/components/manage/registerCard";
export default {
  name: "registrationPage",
  components: {RegisterCard},
  created() {
    document.title = 'UWSK - Registration'
  },
}
</script>

<style scoped>

</style>
