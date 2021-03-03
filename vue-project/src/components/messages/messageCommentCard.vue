<template>
  <b-card class="shadow">
    <b-card v-for="comment in commentList" :key="comment.comment_id" no-body class="shadow-sm">
<!--      avator, comment user and comment time-->
      <b-row class="p-3">
        <b-col cols="auto">
          <b-avatar class="mr-2" size="md"></b-avatar>
        </b-col>
        <b-col>
          <b-row><b>{{ comment.comment_user }}</b></b-row>
          <b-row style="color: gray;font-size: small">
            <{{ timeTransfer(comment.comment_time) }}>
          </b-row>
        </b-col>
      </b-row>

<!--      comment content-->
      <b-card-text class="ml-3 mt-2">
        {{ comment.comment_content }}
      </b-card-text>

<!--      the post commented-->
<!--      <b-card no-body style="background-color: lightgray" text-variant="black" class="rounded-0 border-0 p-3">-->
<!--        <b style="color: dimgrey" class="ml-1">Comment to my post:</b> {{ comment.post_content }}-->
<!--      </b-card>-->

        <b-list-group horizontal class="rounded-0" style="text-align: center">
          <b-list-group-item variant="light" button>Origin Post</b-list-group-item>
          <b-list-group-item variant="light" button>Reply</b-list-group-item>
        </b-list-group>

    </b-card>
  </b-card>
</template>

<script>
import moment from "moment";
import axios from "axios";

export default {
  name: "messageCommentCard",
  data() {
    return {
      commentList: [{
        comment_id: 3,
        post_id: 2,
        comment_user: 'Mao',
        comment_content: 'good post',
        comment_time: '2021-02-17 05:18:44',
        post_content: 'first post'}]
    }
  },
  methods: {
    getCommentList() {

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
