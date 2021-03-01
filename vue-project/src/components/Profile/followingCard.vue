<template>
  <div>
    <b-card v-for="following in followingList" key="1">
      <b-avatar @click="goProfile(following)" button></b-avatar>
      {{ following }}
    </b-card>
  </div>
</template>

<script>
export default {
  name: "followingCard",
  // props: ['followingList'],
  data() {
    return {
      followingList: ['Mao', 'meng', 'zhao']
    }
  },
  methods: {
    goProfile(following) {
      this.$emit('hideFollowingModal')
      this.$nextTick(()=>{
        this.$router.replace('/profile='+following)
      })
    }
  }
}
</script>

<style scoped>

</style>
