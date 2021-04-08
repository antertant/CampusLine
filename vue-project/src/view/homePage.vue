<template>
  <div style="padding-top: 6rem; padding-bottom: 6rem;">
    <hot-card></hot-card>
  </div>
</template>

<script>
import StickyHeader from "../components/header/stickyHeader";
import PostCard from "../components/post/postCard";
import axios from "axios";
import HotCard from "@/components/home/hotCard";
import RichTextEditor from "@/components/rich-text/tinymceEditor";
export default {
  name: "homePage",
  components: {RichTextEditor, HotCard, PostCard, StickyHeader},
  data() {
    return {
      hotPosts: []
    }
  },
  methods: {
    getHotPosts() {
      axios
        .get('/homeposts')
      .then(response=>{
        console.log(response)
        if(response.data.code === 200){
          this.hotPosts = response.data.data
        }
      })
      .catch(failResponse=>{
        console.log(failResponse)
      })
    }
  },
  created() {
    document.title = 'CampusLine - Home'
  }
}
</script>

<style scoped>

</style>
