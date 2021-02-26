<template>
<!--Father Components: comment card & comment reply card-->
  <b-modal ref="reply-modal"
           :id="'reply-modal-'+commentId+replyId"
           content-class="shadow"
           title="Post Your Reply"
           @show="resetCModal"
           @hidden="resetCModal"
           @ok="handleCOk"
           centered>
    <form ref='replyform'
          class="mb-3"
          @submit.stop.prevent="handleCSubmit">
      <b-form-group label-for="reply-input"
                    invalid-feedback="Content is required"
                    :state="postReplyState">
        <b-form-input placeholder="Enter your reply..."
                      id="reply-input"
                      type="text"
                      v-model="postReplyContent"
                      :state="postReplyState"
                      required >
        </b-form-input>
      </b-form-group>
    </form>
  </b-modal>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "replyInput",
  props: ['replyId', 'commentId', 'fromUser'],
  computed: {
    ...mapGetters({
      current_user: "loginInfo/getLUName",
    }),
  },
  data () {
    return {
      postReplyContent: '',
      postReplyState: null,
      hasReplies: false
    }
  },
  methods: {
    resetCModal() {
      this.postReplyContent = ''
      this.postReplyState = null
    },
    checkFormValidity() {
      let valid = this.$ref.replyform.checkFormValidity()
      this.postReplyState = valid
      return valid
    },
    handleCOk(bvModalEvt) {
      bvModalEvt.preventDefault()
      this.handleCSubmit()
    },
    handleCSubmit() {
      // if form content is invalid, return
      // if (!this.checkFormValidity()){
      //   return
      // }
      // post comment to back-end
      if(this.postReplyContent) {
        axios
          .post('/commentreply', {
              'comment_id': this.commentId,
              'creply_content': this.postReplyContent,
              'from_user': this.current_user,
              'to_user': this.fromUser
            })
          .then(response=>{
            console.log(response)
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
        this.$emit('rreply')
      }
      // after submitting, hide modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('reply-modal-'+this.commentId+this.replyId)
      })
    }
  }
}
</script>

<style scoped>

</style>
