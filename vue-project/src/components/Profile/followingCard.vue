<template>
  <div id="profile-following-card">
    <b-card v-for="following in followingList" key="1">
      <b-avatar @click="goProfile(following)" button></b-avatar>
      {{ following }}
    </b-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "followingCard",
  props: ['profileUser'],
  data() {
    return {
      followingList: []
    }
  },
  methods: {
    getFollowing() {
      axios
        .get('/getfollow', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.followingList = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    goProfile(following) {
      this.$emit('hideFollowingModal')
      this.$nextTick(()=>{
        this.$router.replace('/profile='+following)
      })
    }
  },
  mounted() {
    this.getFollowing()
  }
}
</script>

<style scoped>

</style>
