<template>
  <b-card class="shadow" no-body>
<!--    header-->
    <template #header>
      <h5>Comment List</h5>
    </template>
    <b-tabs card>
      <b-tab @click="getCommentList">
        <template #title>
          <b-icon icon="tag" variant="info"></b-icon>
          <span style="color: black"><b>Post Comments</b></span>
          <b-badge variant="danger">1</b-badge>
        </template>
        {{commentList}}
        <b-card v-for="comment in commentList" :key="comment.comment_id" no-body class="shadow-sm">
          <!--      avator, comment user and comment time-->
          <b-row class="p-3">
            <!--        comment user's avatar-->
            <b-col cols="auto">
              <b-avatar class="mr-2" size="md"></b-avatar>
            </b-col>
            <b-col>
              <!--          comment user-->
              <b-row><b>{{ comment.comment_user }}</b></b-row>
              <!--          comment time-->
              <b-row style="color: gray;font-size: small">
                <{{ timeTransfer(comment.comment_time) }}>
              </b-row>
              <!--          comment content-->
              <b-row class="mt-3">
                {{ comment.comment_content }}
              </b-row>
            </b-col>
          </b-row>

          <!--      the post commented-->
          <!--      <b-card no-body style="background-color: lightgray" text-variant="black" class="rounded-0 border-0 p-3">-->
          <!--        <b style="color: dimgrey" class="ml-1">Comment to my post:</b> {{ comment.post_content }}-->
          <!--      </b-card>-->

          <b-list-group horizontal class="rounded-0" style="text-align: center">
            <b-list-group-item variant="light" button>Origin Post</b-list-group-item>
            <b-list-group-item variant="light" button>Reply</b-list-group-item>
          </b-list-group>

        </b-card>
      </b-tab>

      <b-tab @click="getReplyList">
        <template #title>
          <b-icon icon="tag" variant="info"></b-icon>
          <span style="color: black"><b>Comment Replies</b></span>
          <b-badge variant="danger">1</b-badge>
        </template>
        {{replyList}}
        <b-card v-for="comment in replyList" :key="comment.reply_id" no-body class="shadow-sm">
          <!--      avator, comment user and comment time-->
          <b-row class="p-3">
            <!--        comment user's avatar-->
            <b-col cols="auto">
              <b-avatar class="mr-2" size="md"></b-avatar>
            </b-col>
            <b-col>
              <!--          comment user-->
              <b-row><b>{{ comment.comment_user }}</b></b-row>
              <!--          comment time-->
              <b-row style="color: gray;font-size: small">
                <{{ timeTransfer(comment.comment_time) }}>
              </b-row>
              <!--          comment content-->
              <b-row class="mt-3">
                {{ comment.comment_content }}
              </b-row>
            </b-col>
          </b-row>

          <!--      the post commented-->
          <!--      <b-card no-body style="background-color: lightgray" text-variant="black" class="rounded-0 border-0 p-3">-->
          <!--        <b style="color: dimgrey" class="ml-1">Comment to my post:</b> {{ comment.post_content }}-->
          <!--      </b-card>-->

          <b-list-group horizontal class="rounded-0" style="text-align: center">
            <b-list-group-item variant="light" button>Origin Post</b-list-group-item>
            <b-list-group-item variant="light" button>Reply</b-list-group-item>
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
  name: "messageCommentCard",
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  data() {
    return {
      commentList: [],
      replyList: []
    }
  },
  methods: {
    getCommentList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 3}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.commentList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getReplyList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 4}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.replyList = response.data.data
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
    this.getCommentList()
  }
}
</script>

<style scoped>

</style>
