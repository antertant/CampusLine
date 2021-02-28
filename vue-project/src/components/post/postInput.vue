<template>
  <b-card class="border-secondary shadow" bg-variant="light">
    <b-form @submit="sendPost" @reset="resetPContent">
      <b-form-group id="test-area-1">
        <b-form-textarea placeholder="Write your post..."
                         rows="3"
                         max-rows="6"
                         v-model="postContent"
                         required>
        </b-form-textarea>
      </b-form-group>
      <div class="mt-2 mb-0 float-right">
        <b-button class="shadow-sm" variant="warning" type="reset">Reset</b-button>
        <b-button class="shadow-sm" variant="primary" type="submit">Post</b-button>
      </div>
    </b-form>
  </b-card>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "postInput",
  props: ['moduleName'],
  data() {
    return {
      postContent: ''
    }
  },
  computed: {
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  methods: {
    resetPContent() {
      this.postContent = ''
    },
    sendPost() {
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
      if(!this.postContent)
        this.$bvToast.toast(
          'Cannot post empty content.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else{
        axios
          .post('/createpost', {
            module_name: this.moduleName,
            post_content: this.postContent,
            post_author: this.currentUser
          })
          .then(response=>{
            console.log(response)
            if(response.data.code === 200){
              this.resetPContent()
            }
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
      }
    }
  }
}
</script>

<style scoped>

</style>
