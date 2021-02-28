<template>
  <b-row align-h="center">
    <b-col lg="5" sm="9">
      <h1 style="text-align: center">LIFE</h1>
      <post-input :module-name="null"></post-input>
      <div v-for="list in lifePostList">
        <post-card :post-content="list"></post-card>
      </div>
    </b-col>
  </b-row>
</template>

<script>
import PostCard from "../components/post/postCard";
import {mapGetters} from "vuex";
import PostInput from "@/components/post/postInput";
export default {
  name: "lifeCirclePage",
  components: {PostInput, PostCard},
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
