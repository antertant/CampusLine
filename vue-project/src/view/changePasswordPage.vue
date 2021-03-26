<template>
  <div>
    <b-card v-if="user!==currentUser" text-variant="danger" align="center" style="margin-top: 6rem">
      <h3>
        <b-icon icon="exclamation-diamond" variant="danger"></b-icon>
        You have no authentication to access this page!
      </h3>
    </b-card>
    <update-password-card v-if="user===currentUser"
                          style="max-width: 30rem;
                          margin-top: 6rem"></update-password-card>

  </div>
</template>

<script>
import {mapGetters} from "vuex";
import UpdatePasswordCard from "@/components/manage/updatePasswordCard";

export default {
  name: "changePasswordPage",
  components: {UpdatePasswordCard},
  props: ['user'],
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  created() {
    document.title = 'UWSK - Password Modification'
  }
}
</script>

<style scoped>

</style>
