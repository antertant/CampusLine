<template>
  <div style="margin-top: 6rem">
    <h3 style="text-align: center;">Current Following Posts</h3>
    <b-card v-if="currentUser===''" align="center">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon>
      Please login before browsing current following posts.
    </b-card>
    <post-card v-for="newsPost in newsPostList"
               :post-content="newsPost"
               :key="newsPost.post_id"
               v-if="currentUser!==''"></post-card>
  </div>
</template>

<script>
import PostCard from "@/components/post/postCard";
import {mapGetters} from "vuex";
import axios from "axios";
export default {
  name: "newsPage",
  components: {PostCard},
  data() {
    return{
      newsPostList: []
    }
  },
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  methods:{
    getFollowPost(){
      axios
        .get('/getfollowpost', {params:{follower:this.currentUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.newsPostList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  created() {
    document.title = 'UWSK - News Posts'
  },
  mounted() {
    this.getFollowPost()
  }
}
</script>

<style scoped>

</style>
