<template>
  <b-row align-h="center" style="margin-top: 5rem">
    <b-col lg="5" sm="8">
      <h1 style="text-align: center">LIFE</h1>
      <post-input :module-name="null"></post-input>
      <div v-for="list in lifePostList">
        <post-card :post-content="list"></post-card>
      </div>
    </b-col>
    <b-col cols="auto">
      <b-row class="position-fixed">
        <module-side-functions module-name="life"></module-side-functions>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import PostCard from "../components/post/postCard";
import {mapGetters} from "vuex";
import PostInput from "@/components/post/postInput";
import ModuleSideFunctions from "@/components/module/moduleSideFunctions";
export default {
  name: "lifeCirclePage",
  components: {ModuleSideFunctions, PostInput, PostCard},
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
