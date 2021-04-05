<template>
  <b-card class="shadow" no-body>
<!--    header-->
    <template #header>
      <h5>Comment List</h5>
    </template>
<!--    post comment list-->
    <b-tabs card>
      <b-tab @click="getCommentList">
        <template #title>
          <b-icon id="postCommentTab" icon="tag" variant="danger"></b-icon>
          <span style="color: black"><b>Post Comments</b></span>
          <b-badge id="postCommentBadge" variant="danger" v-if="postC!==0">{{ postC }}</b-badge>
        </template>
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
            <b-list-group-item @click="getPostByID(comment.post_id)" v-b-modal="'origin-post-modal'" variant="light" button>
              Origin Post
            </b-list-group-item>
            <!--            <b-list-group-item v-b-modal="'reply-modal-'+comment.comment_id+0" variant="light" button>-->
            <!--              Reply-->
            <!--            </b-list-group-item>-->
          </b-list-group>

          <!--    reply comment modal-->
          <!--          <reply-input :comment-id="comment.comment_id"-->
          <!--                       :reply-id="0"-->
          <!--                       :from-user="currentUser"></reply-input>-->

        </b-card>
      </b-tab>

<!--      comment reply list-->
      <b-tab @click="getReplyList">
        <template #title>
          <b-icon id="commentReplyTab" icon="tag" variant="danger"></b-icon>
          <span style="color: black"><b>Comment Replies</b></span>
          <b-badge id="commentReplyBadge" variant="danger" v-if="commentR!==0">{{commentR}}</b-badge>
        </template>
        <b-card v-for="comment in replyList" :key="comment.reply_id" no-body class="shadow-sm">
          <!--      avator, comment user and comment time-->
          <b-row class="p-3">
            <!--        comment user's avatar-->
            <b-col cols="auto">
              <b-avatar class="mr-2" size="md"></b-avatar>
            </b-col>
            <b-col>
              <!--          comment user-->
              <b-row><b>{{ comment.from_user }}</b></b-row>
              <!--          comment time-->
              <b-row style="color: gray;font-size: small">
                <{{ timeTransfer(comment.creply_time) }}>
              </b-row>
              <!--          comment content-->
              <b-row class="mt-3">
                {{ comment.creply_content }}
              </b-row>
            </b-col>
          </b-row>

          <!--      the post commented-->
          <!--      <b-card no-body style="background-color: lightgray" text-variant="black" class="rounded-0 border-0 p-3">-->
          <!--        <b style="color: dimgrey" class="ml-1">Comment to my post:</b> {{ comment.post_content }}-->
          <!--      </b-card>-->

          <b-list-group horizontal class="rounded-0" style="text-align: center">
            <b-list-group-item @click="getPostByID(comment.post_id)" v-b-modal="'origin-post-modal'" variant="light" button>
              Origin Post
            </b-list-group-item>
<!--            <b-list-group-item variant="light" button>Reply</b-list-group-item>-->
          </b-list-group>

        </b-card>
      </b-tab>
    </b-tabs>

<!--    origin post modal-->
    <b-modal id="origin-post-modal" centered hide-footer>
      <template #modal-header>
        <h4>Origin Post</h4>
      </template>
      <post-card :post-content="originPost"></post-card>
    </b-modal>
  </b-card>
</template>

<script>
import moment from "moment";
import axios from "axios";
import {mapGetters} from "vuex";
import PostCard from "@/components/post/postCard";
import CommentInput from "@/components/post/commentInput";

export default {
  name: "messageCommentCard",
  components: {CommentInput, PostCard},
  props: ['postC', 'commentR'],
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  data() {
    return {
      commentList: [],
      replyList: [],
      originPost: []
    }
  },
  methods: {
    getCommentList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 3}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.commentList = response.data.data.reverse()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.$nextTick(()=>{
        this.$store.dispatch("newMessage/getNewMessageCountFS", this.currentUser)
      })
    },
    getReplyList() {
      axios
        .get('/getnewlist', {params:{username: this.currentUser, choice: 4}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.replyList = response.data.data.reverse()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.$nextTick(()=>{
        this.$store.dispatch("newMessage/getNewMessageCountFS", this.currentUser)
      })
    },
    getPostByID(ID) {
      axios
        .get('/getpost', {params:{post_id: ID}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.originPost = response.data.data
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
