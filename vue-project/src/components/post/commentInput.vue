<template>
<!--Father component: post card-->
  <b-modal ref="comment-modal"
           :id="'comment-modal-'+commentId"
           content-class="shadow"
           title="Post Your Comment"
           @show="resetCModal"
           @hidden="resetCModal"
           @ok="handleCOk"
           centered>
    <form ref='commentform'
          class="mb-3"
          @submit.stop.prevent="handleCSubmit">
      <b-form-group label-for="comment-input"
                    invalid-feedback="Content is required"
                    :state="postCommentState">
        <b-form-input placeholder="Enter your comment..."
                      id="comment-input"
                      type="text"
                      v-model="postCommentContent"
                      :state="postCommentState"
                      required >
        </b-form-input>
      </b-form-group>
    </form>
  </b-modal>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "commentInput",
  props: ['commentId'],
  computed: {
    ...mapGetters({
      current_user: "loginInfo/getLUName",
    }),
  },
  data () {
    return {
      postCommentContent: '',
      postCommentState: null,
      hasComments: false
    }
  },
  methods: {
    resetCModal() {
      this.postCommentContent = ''
      this.postCommentState = null
    },
    checkFormValidity() {
      let valid = this.$ref.commentform.checkFormValidity()
      this.postCommentState = valid
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
      if(this.postCommentContent) {
        axios
          .post('/comment', null, {params:{
              'post_id': this.commentId,
              'username': this.current_user,
              'content': this.postCommentContent
            }})
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
        this.$bvModal.hide('comment-modal-'+this.commentId)
      })
    }
  }
}
</script>

<style scoped>

</style>
