<template>
  <b-row align-h="center" style="margin-top: 5rem">
    <b-col lg="5" sm="8">
      <h1 style="text-align: center">{{modName.toUpperCase()}}</h1>
      <post-input :module-name="modName"></post-input>
      <div v-for="list in modulePostList">
        <post-card :post-content="list"></post-card>
      </div>
    </b-col>
    <b-col cols="auto">
      <b-row class="position-fixed">
        <module-side-functions :module-name="modName"></module-side-functions>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import {mapGetters} from "vuex";
import PostCard from "../components/post/postCard";
import PostInput from "@/components/post/postInput";
import ModuleSideFunctions from "@/components/module/moduleSideFunctions";

export default {
  name: "modulesContentPage",
  components: {ModuleSideFunctions, PostInput, PostCard},
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
