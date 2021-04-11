<template>
  <b-row class="ml-5 mt-3" align-h="start" v-if="showReply">
    <b-icon icon="arrow-return-right"></b-icon>
    <!--avatar-->
    <b-col cols="auto"><b-avatar :to="'/profile='+reply_from_user" size="md"></b-avatar></b-col>
    <!--main content-->
    <b-col cols='auto'>
      <b-row class="mb-1" align-h="between">
<!--        author and post time-->
        <b-col cols="auto" id="post-author" align-self="center">
          <span><b>{{ reply_from_user }}</b></span>
          <span id="post-time"><{{ postTime }}></span>
        </b-col>
<!--        Reply button-->
        <b-col cols="auto">
          <b-button variant="white"
                    size="sm"
                    :id="'comment-reply-button-'+commentId+reply_id"
                    v-b-modal="'reply-modal-'+commentId+reply_id">
            <b-icon icon="chat-left-text" variant="dark"></b-icon>
          </b-button>

<!--          Child component: reply input-->
          <reply-input :comment-id="commentId"
                       :reply-id="reply_id"
                       :post-id="postId"
                       :from-user="reply_from_user"
                       @r-reply="replyComment"></reply-input>
<!--          Delete Button-->
          <b-button variant="white"
                    size="sm"
                    :id="'reply-delete-button-'+commentId+reply_id"
                    v-b-modal="'delete-reply-modal-'+commentId+reply_id"
                    v-if="current_user === reply_from_user">
            <b-icon icon="trash" variant="danger"></b-icon>
          </b-button>
          <b-modal :ref="'delete-reply-modal-'+commentId+reply_id"
                   :id="'delete-reply-modal-'+commentId+reply_id"
                   content-class="shadow"
                   title="Delete Reply"
                   centered>
            Are you sure to delete the reply?
            <template #modal-footer>
              <b-button id="reply-delete-no-button" @click="hideModal">No</b-button>
              <b-button id="reply-delete-yes-button" @click="deleteReply" variant="danger">Yes</b-button>
            </template>
          </b-modal>

        </b-col>
      </b-row>
      <!--comment content-->
      <b-row class="ml-0">
        To @{{reply_to_user}}: {{ reply_content }}
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import {mapGetters} from "vuex";
import moment from "moment";
import CommentInput from "@/components/post/commentInput";
import ReplyInput from "@/components/post/replyInput";
import axios from "axios";

export default {
  name: "commentReplyCard",
  components: {ReplyInput},
  props: ['replyData', 'commentId', 'postId'],
  data() {
    return {
      // reply_id: this.replyData.reply_id,
      // reply_from_user: this.replyData.from_user,
      // reply_to_user: this.replyData.to_user,
      // reply_time: this.replyData.creply_time,
      // reply_content: this.replyData.creply_content,
      postTime: Date,
      showReply: true
    }
  },
  computed: {
    ...mapGetters ({
      current_user: "loginInfo/getLUName",
    }),
    reply_id() {
      return this.replyData.reply_id
    },
    reply_from_user() {
      return this.replyData.from_user
    },
    reply_to_user() {
      return this.replyData.to_user
    },
    reply_time() {
      return this.replyData.creply_time
    },
    reply_content() {
      return this.replyData.creply_content
    },
  },
  methods: {
    replyComment(data) {
      this.$emit('rreply-event', data)
    },
    deleteReply() {
      axios
        .post('/deletereply', null, {params:{reply_id: this.reply_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.showReply = false
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.hideModal()
      this.$nextTick()
    },
    hideModal() {
      this.$refs['delete-reply-modal-'+this.commentId+this.reply_id].hide()
    }
  },
  mounted() {
    this.postTime = moment(new Date(this.reply_time)).format("MMM Do YYYY, HH:mm")
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
