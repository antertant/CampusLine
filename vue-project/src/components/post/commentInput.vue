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
    <div v-if="current_user===''">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon> Please login before make comments!
    </div>
    <form ref='commentform'
          class="mb-3"
          @submit.prevent="handleCSubmit"
          v-if="current_user!==''">
      <b-form-group label-for="comment-input"
                    invalid-feedback="Content is required"
                    :state="postCommentState">
        <b-form-textarea rows="3" max-rows="6"
                      placeholder="Enter your comment..."
                      id="comment-input"
                      type="text"
                      v-model="postCommentContent"
                      required autofocus>
        </b-form-textarea>
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
      hasComments: false
    }
  },
  methods: {
    resetCModal() {
      this.postCommentContent = ''
    },
    // checkFormValidity() {
    //   let valid = this.$ref.commentform.checkFormValidity()
    //   return valid
    // },
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
      if(!this.postCommentContent)
        this.$bvToast.toast(
          'Cannot post empty comment.',{
            title: [errTitle],
            class: 'comment-input-error-toast',
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else {
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
        this.$nextTick(() => {
          this.$emit('emitComment')
          this.$bvModal.hide('comment-modal-'+this.commentId)
        })
      }
      // after submitting, hide modal manually
    }
  }
}
</script>

<style scoped>

</style>
