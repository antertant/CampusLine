<template>
  <b-card style="max-width: 50rem;" class="mt-4 mx-auto">
<!--    Post author-->
    <template #header>
      <b-avatar></b-avatar>
      <span id="post-author"><b>{{ postContent.post_author }}</b></span>
      <span id="post-time">{{postTime.format("MMM Do YYYY, HH:mm") }}</span>
    </template>
<!--    Post content-->
    <b-card-text>
      {{ postContent.post_content }}
    </b-card-text>
    <b-img src="https://placekitten.com/380/200" center></b-img>
<!--    Post buttons-->
    <template #footer>
      <b-list-group style="text-align: center" horizontal>
        <b-list-group-item button>
          Collect
          <b-badge>{{ postContent.post_collections }}</b-badge>
        </b-list-group-item>
        <b-list-group-item button>
          Like
          <b-badge>{{ postContent.post_likes }}</b-badge>
        </b-list-group-item>
        <b-list-group-item button>
          Comment
          <b-badge>{{ postContent.post_comments }}</b-badge>
        </b-list-group-item>
        <b-list-group-item button>
          Repost
        </b-list-group-item>
      </b-list-group>
    </template>
  </b-card>
</template>

<script>
import moment from "moment";

export default {
  name: "postCard",
  data() {
    return{
      postTime: Date
    }
  },
  props: ['postContent'],
  methods: {
    timeConvert() {
      this.postTime = moment(new Date(this.postContent.post_createtime))
    }
  },
  mounted() {
    this.timeConvert()
  }
}
</script>

<style scoped>
  #post-author {
    font-size: larger;
  }
  #post-time {
    font-size: small;
    color: #888888;
  }
</style>
