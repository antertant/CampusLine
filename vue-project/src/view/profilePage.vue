<template>
  <b-row align-h="center" style="margin-top: 6rem">
    <b-col sm="3" md="3" lg="2">
      <b-row class="position-fixed">
        <user-info-card :profile-user="profileUser"></user-info-card>
      </b-row>
    </b-col>

    <b-col class="float-right" sm="8" lg="6">
      <b-card>
        <template #header>
          <h4>Self Posts</h4>
        </template>
        <self-post-card :profile-user="profileUser"></self-post-card>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
import UserInfoCard from "@/components/Profile/userInfoCard";
import SelfPostCard from "@/components/Profile/selfPostCard";
export default {
  name: "profilePage",
  components: {SelfPostCard, UserInfoCard},
  props: ['profileUser']
}
</script>

<style scoped>

</style>
