<template>
<!--Father component: post card-->
  <b-modal ref="comment-modal"
           :id="'comment-modal-'+postId"
           content-class="shadow"
           title="Post Your Comment"
           @show="resetCModal"
           @hidden="resetCModal"
           hide-footer
           centered>
    <div v-if="current_user===''">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon> Please login before make comments!
    </div>
    <form ref='commentform'
          class="mb-3"
          @submit.prevent="handleCSubmit"
          v-if="current_user!==''">
      <b-form-group label-for="comment-input"
                    invalid-feedback="Content is required">
        <b-form-textarea rows="3" max-rows="6"
                      placeholder="Enter your comment..."
                      id="comment-input"
                      type="text"
                      v-model="postCommentContent"
                      required autofocus>
        </b-form-textarea>
      </b-form-group>
    </form>
    <div class="float-right">
      <b-button id="comment-input-cancel" @click="$bvModal.hide('comment-modal-'+postId)" variant="dark">Cancel</b-button>
      <b-button id="comment-input-submit" @click="handleCSubmit" variant="warning">Submit</b-button>
    </div>
  </b-modal>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "commentInput",
  props: ['postId'],
  computed: {
    ...mapGetters({
      current_user: "loginInfo/getLUName",
    }),
  },
  data () {
    return {
      postCommentContent: '',
      hasComments: false,
      comments: []
    }
  },
  methods: {
    resetCModal() {
      this.postCommentContent = ''
    },
    getComment() {
      // Communication
      axios
        .get('/getcomments',{params:{post_id:this.postId}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.comments = response.data.data
            this.$emit('emit-comment', this.comments)
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
      // Communication: submit the comment
      else {
        axios
          .post('/comment', null, {params:{
              'post_id': this.postId,
              'username': this.current_user,
              'content': this.postCommentContent
            }})
          .then(response=>{
            console.log(response)
            this.getComment()
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })

        this.$nextTick(()=>{
          this.$bvModal.hide('comment-modal-'+this.postId)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
