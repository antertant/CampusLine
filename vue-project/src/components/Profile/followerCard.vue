<template>
<div>
  <b-card v-for="follower in followerList" key="1">
    <b-avatar @click="goProfile(follower)" button></b-avatar>
    {{ follower }}
  </b-card>
</div>
</template>

<script>
export default {
  name: "followerCard",
  // props: ['followerList'],
  data() {
    return {
      followerList: ['meng', 'Mao', 'zhao']
    }
  },
  methods: {
    goProfile(follwer) {
      this.$emit('hideFollowerModal')
      this.$nextTick(()=>{
        this.$router.replace('/profile='+follwer)
      })
    }
  }
}
</script>

<style scoped>

</style>
