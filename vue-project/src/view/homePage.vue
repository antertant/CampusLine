<template>
  <div style="margin-top: 6rem">
    <hot-card :hot-data="hotPosts"></hot-card>
  </div>
</template>

<script>
import StickyHeader from "../components/header/stickyHeader";
import PostCard from "../components/post/postCard";
import axios from "axios";
import HotCard from "@/components/home/hotCard";
export default {
  name: "homePage",
  components: {HotCard, PostCard, StickyHeader},
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
    document.title = 'UWSK - Home'
  },
  mounted() {
    this.getHotPosts()
  }
}
</script>

<style scoped>

</style>
