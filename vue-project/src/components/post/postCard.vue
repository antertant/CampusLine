<template>
  <b-card
    style="max-width: 50rem;" :id="'postCard_'+postContent.post_id"
    class="mt-4 mx-auto bg-light shadow-sm"
    footer-tag="footer"
    header-tag="header"
    v-if="showPost" no-body>
<!--    Post author-->
    <b-form-row class="my-2 ml-2 p-3 text-dark rounded-bottom"
                id="header"
                align-v="stretch">
      <b-col cols="auto"><b-avatar :to="'/profile='+postContent.post_author" size="md"></b-avatar></b-col>
      <b-col cols="auto" id="post-author" align-self="center">
        <b>{{ postContent.post_author }}</b>
        <span id="post-time"><{{ postTime }}></span>
      </b-col>
    </b-form-row>
<!--    Post content-->
      <b-card-text class="ml-4" style="position:relative; max-height:150px; overflow-y:auto">
        {{ postContent.post_content }}
      </b-card-text>
      <b-img src="https://placekitten.com/380/200" class="border-secondary shadow" center></b-img>
<!--    Post buttons-->
    <b-row align-h="center" class="mt-3">
      <b-col>
      <b-list-group style="text-align: center" class="shadow-sm" horizontal>
<!--        Collect Button-->
        <b-list-group-item @click="collectPost" button>
          <b-icon icon="star"></b-icon>
        </b-list-group-item>

<!--        Like Button-->
        <b-list-group-item :id="'pc-like-'+postContent.post_id"
                           @click="likePost"
                           button>
          <b-icon icon="hand-thumbs-up" v-if="!likePress"></b-icon>
          <b-icon icon="hand-thumbs-up" variant="info" v-if="likePress"></b-icon>
          <b-badge>{{ postContent.post_likes + likeCount }}</b-badge>
        </b-list-group-item>
<!--        Like list trigger-->
        <b-popover class="like-popover" :target="'pc-like-'+postContent.post_id" triggers="hover" placement="bottom">
          <b-button variant="white"
                    size="sm"
                    v-b-toggle="'postLike-'+postContent.post_id"
                    @click="getLike">
            <b-icon icon="list-stars" size="sm" variant="primary"></b-icon> Like list
          </b-button>
        </b-popover>

<!--        Comment button-->
        <b-list-group-item :id="'pc-comment-'+postContent.post_id"
                           :ref="'pc-comment-'+postContent.post_id"
                           v-b-toggle="'postComment-'+postContent.post_id"
                           @click="getCommentHook"
                           button>
          <b-icon icon="chat-left-text" size="sm"></b-icon>
          <b-badge>{{ comments.length }}</b-badge>
        </b-list-group-item>
<!--        Comment input trigger-->
        <b-popover :target="'pc-comment-'+postContent.post_id" triggers="hover" placement="bottom">
          <b-button variant="white"
                    size="sm"
                    v-b-modal="'comment-modal-'+postContent.post_id">
            <b-icon icon="pencil-square" size="sm" variant="primary"></b-icon> Write Comment
          </b-button>
        </b-popover>
<!--        Child Component: Comment input-->
        <comment-input :comment-id="postContent.post_id" @emit-comment="cButtonGetCommentHook"></comment-input>

<!--        Repost Button-->
<!--        <b-list-group-item button>-->
<!--          <b-icon icon="box-arrow-up-right"></b-icon>-->
<!--        </b-list-group-item>-->

<!--        Delete Button-->
        <b-list-group-item button
                           v-if="current_user===postContent.post_author || admin"
                           v-b-modal="'delete-post-modal-'+postContent.post_id+0">
          <b-icon icon="trash"></b-icon>
        </b-list-group-item>
        <b-modal :ref="'delete-post-modal-'+postContent.post_id+0"
                 :id="'delete-post-modal-'+postContent.post_id+0"
                 content-class="shadow"
                 title="Delete Comment"
                 centered>
          Are you sure to delete the post?
          <template #modal-footer>
            <b-button @click="hideModal">No</b-button>
            <b-button @click="deletePost" variant="danger">Yes</b-button>
          </template>
        </b-modal>

      </b-list-group>

<!--      Comment Cards-->
      <b-collapse class="comment-collapse"
                  :id="'postComment-'+postContent.post_id"
                  v-model="visible">
        <b-card v-if="!commentEmpty" style="text-align: center">There is no comment here yet.</b-card>
        <div v-if="commentEmpty" v-for="comment in comments" :key="comment.comment_id">
<!--          Child Component: Comment Card-->
          <comment-card :comment-data="comment"
                        :id="comment.comment_id"
                        @reply-event="getCommentHook"
                        @comment-deleted="getCommentHook"></comment-card>
        </div>
      </b-collapse>

<!--       Like list-->
      <b-collapse class="like-collapse" :id="'postLike-'+postContent.post_id">
        <b-card v-if="!likeEmpty" style="text-align: center">Nobody likes this post yet.</b-card>
        <b-card v-if="likeEmpty" v-for="like in likes" :key="like.comment_id">
          <div class="ml-1"><b-avatar size="sm"></b-avatar> <b>{{ like }}</b></div>
        </b-card>
      </b-collapse>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import moment from "moment";
import {mapGetters} from "vuex";
import axios from "axios";
import CommentCard from "@/components/post/commentCard";
import CommentInput from "@/components/post/commentInput";

export default {
  name: "postCard",
  components: {CommentInput, CommentCard},
  props: ['postContent', 'adminCode'],
  data() {
    return{
      postTime: Date,
      likeCount: 0, // Like counter
      comments: [], // The content of comments
      postId: this.postContent.post_id,
      showPost: true,
      likePress: false,  // Like pressed flag
      likes: [],  // Like lists
      visible: false // comment collapse status
    }
  },
  computed: {
    ...mapGetters ({
      current_user: "loginInfo/getLUName",
    }),
    // Comment list empty detector
    commentEmpty: function() {
      if(this.comments.length === 0){
        return false;
      }
      else{
        return true;
      }
    },
    // Like list empty detector
    likeEmpty: function() {
      if(this.likes.length === 0){
        return false;
      }
      else{
        return true;
      }
    },
    // flag for management page
    admin: function (){
      if(this.adminCode === 1)
        return true
      else
        return false
    }
  },
  methods: {
    collectPost() {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Error')
        ]
      )

      // Show alert when the like action is done by a visitor
      if(this.current_user === '')
        this.$bvToast.toast(
          'Please login before interacting with posts.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else {
        axios
          .post('/collect', null, {
            params: {
              post_id: this.postContent.post_id,
              username: this.current_user
            }
          })
          .then(response => {
            console.log(response)
            if (response.data.code === 200) {
              if (response.data.data === "collect successfully")
                this.constructSuccessToast('Collection added')
              else if (response.data.data === "remove collection successfully")
                this.constructSuccessToast('Collection removed')
            }
          })
          .catch(failResponse => {
            console.log(failResponse)
          })
      }
    },
    likePost() {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Error')
        ]
      )

      // Show alert when the like action is done by a visitor
      if(this.current_user === '')
        this.$bvToast.toast(
          'Please login before interacting with posts.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
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
              this.getLike()
              if(response.data.data === 'like successfully'){
                this.likeCount += 1
                this.likePress = true
              }
              else if(response.data.data === 'cancel like successfully'){
                this.likeCount -= 1
                this.likePress = false
              }
            }
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
      }
    },
    deletePost() {
      axios
        .post('/deletepost', null, {params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.showPost = false
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.hideModal()
      this.$nextTick()
    },
    cButtonGetCommentHook() {
      if(this.visible === false){
        this.visible = true
      }
      this.getCommentHook()
    },
    async getCommentHook() {
      let res = await this.getComment()
    },
    // get comment list
    getComment() {
      // Communication
      axios
        .get('/getcomments',{params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.setComments(response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    // set comment data
    setComments(comments) {
      this.comments = comments
    },
    getLike() {
      axios
        .get('/getlikes', {params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.likes = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    hideModal() {
      this.$refs['delete-post-modal-'+this.postContent.post_id+0].hide()
    },
    constructSuccessToast(action) {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Success')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        action+'!',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'success',
          solid: true
        }
      )
    }
  },
  mounted() {
    this.likeCount = 0
    this.getComment()
    // Transfer the form of Date
    this.postTime = moment(new Date(this.postContent.post_createtime)).format("MMM Do YYYY, HH:mm")
  }
}
</script>

<style scoped>
  #header {

  }
  #post-author {
    font-size: x-large;
  }
  #post-time {
    text-align: right;
    font-size: small;
    color: #888888;
  }
</style>
