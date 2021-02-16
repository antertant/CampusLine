<template>
  <div>
    <h1 style="text-align: center">{{modName}}</h1>
    <div v-for="list in modulePostList">
      <post-card :post-content="list"></post-card>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import PostCard from "../components/post/postCard";

export default {
  name: "modulesContentPage",
  components: {PostCard},
  props: ['modName'],
  computed: {
    ...mapGetters ({
      modulePostList: "modulePostInfo/getModulePostList"
    })
  },
  mounted() {
    this.$store.dispatch("modulePostInfo/getModulePostfromServer", this.modName)
  }
}
</script>

<style scoped>

</style>
