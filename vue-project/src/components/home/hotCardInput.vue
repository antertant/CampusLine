<template>
  <div>
<!--    input activate button-->
    <b-button v-if="modName==='life'"
              variant="dark"
              v-b-toggle="'hot-input-'+modName"
              block>
      <b-icon icon="pen"/>
      Write your own post on <{{modName.toUpperCase()}}>
    </b-button>

    <b-button v-else
              variant="dark"
              v-b-toggle="'hot-input-'+modName"
              block>
      <b-icon icon="pen"/>
      Write your own post on <{{modName.toUpperCase()}}>
    </b-button>
<!--    input modal-->
    <b-collapse :id="'hot-input-'+modName">
      <b-card>
        <b-form @submit="sendPost" @reset="resetPContent">
          <b-form-group :id="'hot-text-area'+modName">
            <rich-text-editor :id="'hot-text-editor-'+modName" :own-flag="0" :mod-name="modName" :text-pipe="postContent" @rich-input-content="updatePContent" />
          </b-form-group>
          <div class="mt-2 mb-0 float-right">
            <b-button class="shadow-sm" variant="danger" type="reset">Reset</b-button>
            <b-button :id="'hot-text-submit-'+modName" class="shadow-sm" variant="warning" type="submit">Post</b-button>
          </div>
        </b-form>
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
import RichTextEditor from "@/components/rich-text/tinymceEditor";
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "hotCardInput",
  components: {RichTextEditor},
  props:['modName'],
  data() {
    return{
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
    updatePContent(newText) {
      this.postContent = newText
    },
    sendPost(event) {
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
      if(!this.postContent) {
        event.preventDefault()
        this.$bvToast.toast(
          'Cannot post empty content.', {
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      }
      // Communication
      else{
        axios
          .post('/createpost', {
            module_name: this.modName === 'life' ? null : this.modName,
            post_content: this.postContent,
            post_author: this.currentUser
          })
          .then(response=>{
            console.log(response)
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
        this.$nextTick(()=>{
          if(this.modName === 'life')
            this.$router.replace('/life')
          else
            this.$router.replace('/knowledge-modules/'+this.modName)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
