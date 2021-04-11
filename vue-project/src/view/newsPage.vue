<template>
  <div style="padding-top: 6rem; padding-bottom: 6rem">
    <h3 id="newsTitle" style="text-align: center;">
      <b-icon icon="newspaper"/>
      New Posts from Following List
    </h3>
    <b-card v-if="currentUser===''" align="center">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon>
      Please login before browsing current following posts.
    </b-card>
    <post-card v-for="newsPost in newsPostList"
               :post-content="newsPost"
               :key="newsPost.post_id"
               v-if="currentUser!==''"
               class="mx-auto"></post-card>
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
    document.title = 'CampusLine - News Posts'
  },
  mounted() {
    this.getFollowPost()
  }
}
</script>

<style scoped>

</style>
