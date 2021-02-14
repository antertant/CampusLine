<template>
  <div>
    <h1 style="text-align: center"> Life Circle</h1>
    <div v-for="list in lifePostList">
      <post-card :post-content="list"></post-card>
    </div>
  </div>
</template>

<script>
import PostCard from "../components/post/postCard";
import {mapGetters} from "vuex";
export default {
  name: "lifeCirclePage",
  components: {PostCard},
  computed: {
    ...mapGetters({
      lifePostList: 'lifePostInfo/getLifePostList'
    })
  },
  mounted() {
    this.$store.dispatch("lifePostInfo/getlifePostfromServer")
  }
}
</script>

<style scoped>

</style>
