<template>
  <b-card style="max-width: 50rem" class="mx-auto">
  <editor
    :id="'my-tinymce-'+modName+'-'+ownCode"
    v-model="sunHtml"
    :key="ownCode"
    @input="inputEmit"
    :init="{
         height: 250,
         menubar: false,
         branding: false,
         toolbar_mode: 'sliding',
         placeholder: 'Write Something...',
         skin_url: '/static/tinymce/skins/ui/oxide',
         fontsize_formats: '11px 12px 14px 16px 18px 24px 36px 48px',
         plugins: [
           'advlist autolink lists link image charmap print preview anchor',
           'searchreplace visualblocks code fullscreen',
           'insertdatetime media table paste code help wordcount'
         ],
         toolbar:
           'undo redo | formatselect | fontsizeselect | bold italic backcolor | \
           alignleft aligncenter alignright alignjustify | \
           bullist numlist code outdent indent image | removeformat | help'
       }"
  />
  </b-card>
</template>

<script>
import tinymce from 'tinymce/tinymce'
import Editor from '@tinymce/tinymce-vue'
// base requirement
import 'tinymce/icons/default/icons'
import 'tinymce/themes/silver'
// plugins
import 'tinymce/plugins/advlist'
import 'tinymce/plugins/autolink'
import 'tinymce/plugins/lists'
import 'tinymce/plugins/link'
import 'tinymce/plugins/image'
import 'tinymce/plugins/charmap'
import 'tinymce/plugins/print'
import 'tinymce/plugins/preview'
import 'tinymce/plugins/anchor'
import 'tinymce/plugins/visualblocks'
import 'tinymce/plugins/code'
import 'tinymce/plugins/insertdatetime'
import 'tinymce/plugins/media'
import 'tinymce/plugins/table'
import 'tinymce/plugins/paste'
import 'tinymce/plugins/help'
import 'tinymce/plugins/wordcount'
import 'tinymce/plugins/searchreplace'
import 'tinymce/plugins/fullscreen'

export default {
  name: "richTextEditor",
  components: {'editor': Editor},
  props:['textPipe', 'modName', 'ownFlag'],
  computed:{
    ownCode() {
      return this.ownFlag
    }
  },
  watch:{
    textPipe: {
      handler() {this.sunHtml = this.textPipe}
    }
  },
  data() {
    return {
      sunHtml: this.textPipe,
    }
  },
  methods: {
    inputEmit() {
      this.$emit('rich-input-content', this.sunHtml)
    }
  },
  mounted() {
    tinymce.init({})
  }
}
</script>

<style scoped>

</style>
