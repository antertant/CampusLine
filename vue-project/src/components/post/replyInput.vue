<template>
<!--Father Components: comment card & comment reply card-->
  <b-modal ref="reply-modal"
           :id="'reply-modal-'+commentId+replyId"
           content-class="shadow"
           title="Post Your Reply"
           @show="resetCModal"
           @hidden="resetCModal"
           centered
           hide-footer>
    <div v-if="current_user===''">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon> Please login before make replies!
    </div>
    <form ref='replyform'
          class="mb-3"
          @submit.prevent="handleCSubmit"
          v-if="current_user!==''">
      <b-form-group label-for="reply-input"
                    invalid-feedback="Content is required"
                    :state="postReplyState">
        <b-form-textarea rows="3" max-rows="6"
                         placeholder="Enter your reply..."
                      id="reply-input"
                      type="text"
                      v-model="postReplyContent"
                      :state="postReplyState"
                      required autofocus>
        </b-form-textarea>
      </b-form-group>
    </form>
    <div class="float-right">
      <b-button id="reply-cancel" @click="$bvModal.hide('reply-modal-'+commentId+replyId)" variant="dark">Cancel</b-button>
      <b-button id="reply-submit" @click="handleCSubmit" variant="warning">Submit</b-button>
    </div>
  </b-modal>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "replyInput",
  props: ['replyId', 'commentId', 'postId', 'fromUser'],
  computed: {
    ...mapGetters({
      current_user: "loginInfo/getLUName",
    }),
  },
  data () {
    return {
      postReplyContent: '',
      postReplyState: null,
      hasReplies: false,
      comments: []
    }
  },
  methods: {
    resetCModal() {
      this.postReplyContent = ''
      this.postReplyState = null
    },
    getComment() {
      // Communication
      axios
        .get('/getcomments',{params:{post_id:this.postId}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.comments = response.data.data
            this.$emit('r-reply', this.comments)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    handleCSubmit() {
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
      if(!this.postReplyContent)
        this.$bvToast.toast(
          'Cannot post empty reply.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else {
        axios
          .post('/commentreply', {
              'comment_id': this.commentId,
              'creply_content': this.postReplyContent,
              'from_user': this.current_user,
              'to_user': this.fromUser
            })
          .then(response=>{
            console.log(response)
            this.getComment()
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
        // after submitting, hide modal manually
        this.$nextTick(() => {
          this.$bvModal.hide('reply-modal-'+this.commentId+this.replyId)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
