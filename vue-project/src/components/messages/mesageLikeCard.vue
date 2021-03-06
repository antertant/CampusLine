<template>
  <b-card no-body class="shadow">
    <!--    header-->
    <template #header>
      <h5>Like List</h5>
    </template>
    <b-tabs card>
      <b-tab @click="getPostLikeList">
        <template #title>
          <b-icon icon="tag" variant="info"></b-icon>
          <span style="color: black"><b>Post Likes</b></span>
          <b-badge variant="danger">1</b-badge>
        </template>
        {{postLikeList}}
        <b-card v-for="like in postLikeList" :key="like.post_id+like.like_user" no-body class="shadow-sm">
          <!--      avator, comment user and comment time-->
          <b-row class="p-3">
            <b-col cols="auto">
              <b-avatar class="mr-2" size="md"></b-avatar>
            </b-col>
            <b-col>
              <!--          like user-->
              <b-row><b>{{ like.like_user }}</b></b-row>
              <!--          like time-->
              <b-row style="color: gray;font-size: small">
                <{{ timeTransfer(like.like_time) }}>
              </b-row>
              <!--          like message-->
              <b-row class="mt-3">
                <b-icon icon="hand-thumbs-up"></b-icon> This person likes your post.
              </b-row>
            </b-col>
          </b-row>

          <b-list-group horizontal class="rounded-0" style="text-align: center">
            <b-list-group-item variant="light" button>Origin Post</b-list-group-item>
          </b-list-group>

        </b-card>
      </b-tab>

      <b-tab @click="getCommentLikeList">
        <template #title>
          <b-icon icon="tag" variant="info"></b-icon>
          <span style="color: black"><b>Comment Likes</b></span>
          <b-badge variant="danger">1</b-badge>
        </template>
        {{commentLikeList}}
        <b-card v-for="like in commentLikeList" :key="like.comment_id+like.like_user" no-body class="shadow-sm">
          <!--      avator, comment user and comment time-->
          <b-row class="p-3">
            <b-col cols="auto">
              <b-avatar class="mr-2" size="md"></b-avatar>
            </b-col>
            <b-col>
              <!--          like user-->
              <b-row><b>{{ like.like_user }}</b></b-row>
              <!--          like time-->
              <b-row style="color: gray;font-size: small">
                <{{ timeTransfer(like.like_time) }}>
              </b-row>
              <!--          like message-->
              <b-row class="mt-3">
                <b-icon icon="hand-thumbs-up"></b-icon> This person likes your post.
              </b-row>
            </b-col>
          </b-row>

          <b-list-group horizontal class="rounded-0" style="text-align: center">
            <b-list-group-item variant="light" button>Origin Post</b-list-group-item>
          </b-list-group>

        </b-card>
      </b-tab>
    </b-tabs>
  </b-card>
</template>

<script>
import moment from "moment";
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "mesageLikeCard",
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  data() {
    return {
      postLikeList:[],
      commentLikeList: []
    }
  },
  methods: {
    getPostLikeList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 1}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.postLikeList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getCommentLikeList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 2}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.commentLikeList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    timeTransfer(time) {
      let postTime = moment(new Date(time)).format("MMM Do YYYY, HH:mm")
      return postTime
    }
  },
  mounted() {
    this.getPostLikeList()
  }
}
</script>

<style scoped>

</style>
