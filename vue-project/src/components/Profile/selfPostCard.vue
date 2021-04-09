<template>
  <div style="min-width: 50rem">
      <post-card v-for="selfPost in selfPostList"
                 :key="selfPost.post_id"
                 :post-content="selfPost" />
  </div>
</template>

<script>
import PostCard from "@/components/post/postCard";
import axios from "axios";
export default {
  name: "selfPostCard",
  components: {PostCard},
  props: ['profileUser'],
  data() {
    return{
      selfPostList: []
    }
  },
  watch: {
    profileUser() {
      this.getselfPostList()
    }
  },
  methods: {
    getselfPostList() {
      axios
        .get('/getpostbyauthor', {params:{
            username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.selfPostList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getselfPostList()
  }
}
</script>

<style scoped>

</style>
