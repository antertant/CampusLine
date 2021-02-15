<template>
  <b-card style="max-width: 50rem;" class="mt-4 mx-auto">
    {{foo}}
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
        <b-list-group-item  @click="likePost" button>
          Like
          <b-badge>{{ postContent.post_likes + likeCount }}</b-badge>
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
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "postCard",
  props: ['postContent'],
  data() {
    return{
      postTime: Date,
      likeCount: 0
    }
  },
  computed: {
    ...mapGetters ({
      current_user: "loginInfo/getLUName",
    })
  },
  methods: {
    likePost() {

      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Error')
        ]
      )

      if(this.current_user === '')
        this.$bvToast.toast(
          'Please login before interacting with posts.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      else{
        axios
          .post('/likepost', null, {params:{
              post_id: this.postContent.post_id,
              username: this.current_user
            }
          })
          .then(response=>{
            console.log(response)
            if(response.data.code === 200){
              if(response.data.data === 'like successfully'){
                this.likeCount += 1
              }
              else if(response.data.data === 'cancel like successfully'){
                this.likeCount -= 1
              }
            }
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
      }
    }
  },
  mounted() {
    this.likeCount = 0
    this.postTime = moment(new Date(this.postContent.post_createtime))
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
