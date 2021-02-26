<template>
  <div>
    <h1 style="text-align: center">Hello World!!!</h1>
<!--    {{hotPosts}}-->
  </div>
</template>

<script>
import StickyHeader from "../components/header/stickyHeader";
import PostCard from "../components/post/postCard";
import axios from "axios";
export default {
  name: "homePage",
  components: {PostCard, StickyHeader},
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
  // mounted() {
  //   this.getHotPosts()
  // }
}
</script>

<style scoped>

</style>
