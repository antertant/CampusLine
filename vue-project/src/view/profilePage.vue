<template>
  <b-row align-h="center" style="padding-bottom: 6rem; padding-top: 6rem">
    <b-col cols="auto">
      <b-row>
        <user-info-card :profile-user="profileUser"></user-info-card>
      </b-row>
    </b-col>

    <b-col class="float-right" cols="auto">
      <b-card :style="selfPostCardStyle" class="shadow">
        <b-card-header header-bg-variant="white">
          <h4>Self Posts</h4>
        </b-card-header>
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
  props: ['profileUser'],
  data() {
    return{
      selfPostCardStyle:{
        'overflow-y': 'auto',
        'height': ''
      }
    }
  },
  methods:{
    getHeight(){
      this.selfPostCardStyle.height = (window.innerHeight-180).toString()+'px'
    }
  },
  created() {
    document.title = 'CampusLine - Profile_' + this.profileUser
    window.addEventListener("resize", this.getHeight)
    this.getHeight()
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight)
  }
}
</script>

<style scoped>

</style>
