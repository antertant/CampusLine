<template>
  <b-card v-if="showComment">
    <b-row class="mb-4">
      <!--avatar-->
      <b-col cols="auto"><b-avatar :to="'/profile='+comment_user" size="md"></b-avatar></b-col>
      <!--main content-->
      <b-col cols="auto">
        <b-row class="mb-1" align-h="start">
          <!--author and post time-->
          <b-col cols="auto" id="post-author" align-self="center">
            <span><b>{{ comment_user }}</b></span>
            <span id="post-time"><{{ postTime }}></span>
          </b-col>
<!--          likes & reply icon-->
          <b-col cols="auto">
<!--            Comment Button-->
            <b-button variant="white"
                      size="sm"
                      :id="'comment-reply-'+comment_id"
                      v-b-modal="'reply-modal-'+comment_id+0">
              <b-icon icon="chat-left-text" variant="dark"></b-icon>
            </b-button>
<!--            Child Component: reply input-->
            <reply-input :comment-id="comment_id"
                         :post-id="postId"
                         :reply-id="0"
                         :from-user="comment_user"
                         @r-reply="pushReplyQuest"/>
<!--            Like Button-->
            <b-button :id="'comment-like-'+comment_id" variant="white" @click="likeComment">
              <b-icon icon="hand-thumbs-up" v-if="!likePress"></b-icon>
              <b-icon variant="info" icon="hand-thumbs-up" v-if="likePress"></b-icon>
              <b-badge variant="light">{{ comment_likes + likeCount }}</b-badge>
            </b-button>

<!--            Delete Button-->
            <b-button v-b-modal="'delete-comment-modal-'+comment_id+0"
                      variant="white"
                      :id="'comment-delete-'+comment_id"
                      v-if="current_user===comment_user">
              <b-icon icon="trash" variant="danger"></b-icon>
            </b-button>
            <b-modal :ref="'delete-comment-modal-'+comment_id+0"
                     :id="'delete-comment-modal-'+comment_id+0"
                     content-class="shadow"
                     title="Delete Comment"
                     centered>
              Are you sure to delete the comment?
              <template #modal-footer>
                <b-button id="comment-delete-no-button" @click="hideModal">No</b-button>
                <b-button id="comment-delete-yes-button" @click="deleteComment" variant="danger">Yes</b-button>
              </template>
            </b-modal>

          </b-col>
        </b-row>
        <!--comment content-->
        <b-row class="ml-0">
          {{ comment_content }}
        </b-row>
      </b-col>
    </b-row>
    <!--replies-->
    <div v-for="rp in comment_replies" :key="rp.reply_id">
<!--      Child Component: comment reply card-->
      <comment-reply-card :reply-data="rp"
                          :comment-id="comment_id"
                          :post-id="postId"
                          @rreply-event="pushReplyQuest"></comment-reply-card>
    </div>
  </b-card>
</template>

<script>
import moment from "moment";
import axios from "axios";
import {mapGetters} from "vuex";
import CommentReplyCard from "@/components/post/commentReplyCard";
import ReplyInput from "@/components/post/replyInput";

export default {
  name: "commentCard",
  components: {ReplyInput, CommentReplyCard},
  props: ['commentData', 'postId'],
  data() {
    return {
      // comment_id: this.commentData.comment_id,
      // comment_user: this.commentData.comment_user,
      // comment_content: this.commentData.comment_content,
      // comment_time: this.commentData.comment_time,
      // comment_likes: this.commentData.comment_likes,
      // comment_replies: this.commentData.replies,
      postTime: Date,
      likeCount: 0,
      showComment: true,
      likePress: false  // Flag for like notation
    }
  },
  computed: {
    ...mapGetters({
      current_user: "loginInfo/getLUName",
    }),
    comment_id() {
      return this.commentData.comment_id
    },
    comment_user() {
      return this.commentData.comment_user
    },
    comment_content() {
      return this.commentData.comment_content
    },
    comment_time() {
      return this.commentData.comment_time
    },
    comment_likes() {
      this.likeCount = 0  // Reset like counter when data has updated
      return this.commentData.comment_likes
    },
    comment_replies() {
      return this.commentData.replies
    },
  },
  mounted() {
    this.postTime = moment(new Date(this.comment_time)).format("MMM Do YYYY, HH:mm")
    this.likeCount = 0
  },
  methods: {
    likeComment() {
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
          .post('/likecomment', null, {params:{
              comment_id: this.comment_id,
              clike_user: this.current_user
            }
          })
          .then(response=>{
            this.likePress = true
            console.log(response)
            if(response.data.code === 200){
              if(response.data.data === 'like this comment successfully'){
                this.likeCount += 1
                this.likePress = true
              }
              else if(response.data.data === 'cancel successfully'){
                this.likeCount -= 1
                this.likePress = false
              }
            }
          })
          .catch(failResponse=>{
            this.likePress = true

            console.log(failResponse)
          })
      }
    },
    deleteComment() {
      this.hideModal()
      axios
        .post('/deletecomment', null, {params: {comment_id: this.comment_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.showComment = false
            this.$nextTick()
            this.$emit('comment-deleted')
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    pushReplyQuest(data) {
      this.$emit('reply-event', data)
    },
    hideModal() {
      this.$refs['delete-comment-modal-'+this.comment_id+0].hide()
    }
  }
}
</script>

<style scoped>
#post-author {
  font-size:medium;
}
#post-time {
  text-align: right;
  font-size: small;
  color: #888888;
}
</style>
