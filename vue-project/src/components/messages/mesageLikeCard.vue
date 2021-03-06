<template>
  <b-card class="shadow">
    <!--    header-->
    <template #header>
      <h5>Like List</h5>
    </template>
    <b-card v-for="like in likeList" :key="like.post_id+like.like_user" no-body class="shadow-sm">
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
  </b-card>
</template>

<script>
import moment from "moment";
import axios from "axios";

export default {
  name: "mesageLikeCard",
  data() {
    return {
      likeList:[{
        post_id: 2,
        like_user: 'Mao',
        like_time: '2021-02-16 22:56:25'
      }]
    }
  },
  methods: {
    getLikeList() {

    },
    timeTransfer(time) {
      let postTime = moment(new Date(time)).format("MMM Do YYYY, HH:mm")
      return postTime
    }
  },
}
</script>

<style scoped>

</style>
