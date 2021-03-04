<template>
<div>
  <b-card v-for="follower in followerList" key="1">
    <b-avatar @click="goProfile(follower)" button></b-avatar>
    {{ follower }}
  </b-card>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "followerCard",
  props: ['profileUser'],
  data() {
    return {
      followerList: []
    }
  },
  methods: {
    getFollower() {
      axios
        .get('getfollower', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.followerList = response.data.data
        })
    },
    goProfile(follwer) {
      this.$emit('hideFollowerModal')
      this.$nextTick(()=>{
        this.$router.replace('/profile='+follwer)
      })
    }
  },
  mounted() {
    this.getFollower()
  }
}
</script>

<style scoped>

</style>
