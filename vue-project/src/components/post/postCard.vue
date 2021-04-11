<template>
  <b-card
    style="width: 50rem; border-radius: 14px"
    :id="'postCard_'+postContent.post_id"
    class="mt-4 bg-light shadow-sm"
    footer-tag="footer"
    header-tag="header"
    v-if="showPost" no-body>
<!--    Pinned Bar-->
    <b-card style="border-radius: 14px 14px 0 0;" no-body>
      <b-button variant="white" :disabled="!admin || !postContent.if_top" @click="$bvModal.show('cancel-top-'+postId)">
        <b><em v-if="postContent.module_name===null">Life</em></b>
        <b><em>{{ postContent.module_name }}</em></b>
        <span v-if="postContent.if_top & isMod">
          | <b-icon variant="danger" icon="lock-fill"/> PINNED
        </span>
      </b-button>
    </b-card>
    <b-modal :id="'cancel-top-'+postId" hide-header centered>
      <b>
        Are you sure to cancel the pin?
      </b>
      <template #modal-footer>
        <b-button @click="$bvModal.hide('cancel-top-'+postId)">No</b-button>
        <b-button @click="pinPostToTop(); $bvModal.hide('cancel-top-'+postId)"
                  variant="info">
          Yes
        </b-button>
      </template>
    </b-modal>

    <b-card style="border-radius: 0" v-if="admin & !postContent.if_top" no-body>
      <b-button variant="white" @click="$bvModal.show('to-top-'+postId)">
        <b-icon variant="success" icon="unlock-fill"/> CLICK TO PIN
      </b-button>
    </b-card>
    <b-modal :id="'to-top-'+postId" hide-header centered>
      <b>
        Are you sure to pin the post?
      </b>
      <template #modal-footer>
        <b-button @click="$bvModal.hide('to-top-'+postId)">No</b-button>
        <b-button @click="pinPostToTop(); $bvModal.hide('to-top-'+postId)"
                  variant="info">
          Yes
        </b-button>
      </template>
    </b-modal>
<!--    post editor button-->
    <b-button variant="white"
              :id="'post-editor-'+postContent.post_id"
              v-b-toggle="'post-editor-'+postContent.post_id"
              v-if="current_user===postContent.post_author"
              style="position: absolute; right: 0.6rem; top: 0.1rem"
              title="edit the post">
      <b-icon icon="pencil"/>
    </b-button>
<!--    post editor modal-->
    <b-collapse :id="'post-editor-'+postContent.post_id" centered>
      <b-card class="border-bottom bg-light mb-3" no-body>
        <h4 style="text-align: center" class="mt-2">Post Editor</h4>
        <rich-text-editor v-if="editorState"
                          :mod-name="postContent.module_name"
                          :own-flag="'2-'+postContent.post_id"
                          :text-pipe="postContent.post_content"
                          @rich-input-content="updatePostContent" />
        <b-button style="margin: 4px"
                  :id="'post-editor-update-button-'+postContent.post_id"
                  class="float-right my-1"
                  variant="warning"
                  @click="$bvModal.show('update-post-'+postContent.post_id)">
          Update
        </b-button>
        <b-button style="margin: 4px"
                  class="float-right my-1"
                  variant="dark"
                  @click="$bvModal.show('quit-editor-'+postContent.post_id)">
          Reset
        </b-button>
      </b-card>
    </b-collapse>
    <!--    Quit editor modal-->
    <b-modal :id="'quit-editor-'+postContent.post_id" hide-header centered>
      Are you sure to reset the editor? You will lose all content edited.
      <template #modal-footer>
        <b-button @click="$bvModal.hide('quit-editor-'+postContent.post_id)">No</b-button>
        <b-button @click="resetEditor" variant="danger">Yes</b-button>
      </template>
    </b-modal>
    <!--    Update post-->
    <b-modal :id="'update-post-'+postContent.post_id" hide-header centered>
      Are you sure to update the post?
      <template #modal-footer>
        <b-button :id="'editor-update-cancel-button-'+postContent.post_id" @click="$bvModal.hide('update-post-'+postContent.post_id)">No</b-button>
        <b-button :id="'editor-update-confirm-button-'+postContent.post_id" @click="updatePost" variant="success">Yes</b-button>
      </template>
    </b-modal>
<!--    logo-->
<!--    <b-img src="https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/University_of_Waterloo_seal.svg/1200px-University_of_Waterloo_seal.svg.png"-->
<!--           alt="University of Waterloo seal.svg"-->
<!--           width="32"-->
<!--           height="32"-->
<!--           style="position: absolute; right: 0.8rem; top: 0.2rem"/>-->
<!--    show post in modal-->
    <div style="position: absolute; left: 1rem; top: 0.3rem">
      <b-button @click="$bvModal.show('post-content-modal-'+postContent.post_id)"
                variant="white"
                style="padding: 0"
                title="show post in modal">
        <b-icon variant="dark" icon="aspect-ratio"/>
      </b-button>
    </div>
    <b-modal :id="'post-content-modal-'+postContent.post_id"
             size="lg"
             hide-header
             hide-footer
             centered>
      <div v-html="postContent.post_content"/>
    </b-modal>

<!--    Post avatar-->
    <b-row class="ml-2 p-3 text-dark rounded-bottom"
                id="header"
                align-v="stretch">
      <b-col cols="auto">
        <b-avatar :id="'avatar-'+postContent.post_author" :to="'/profile='+postContent.post_author" size="md"/>
      </b-col>
<!--      post author-->
      <b-col cols="10" id="post-author">
        <b-row>
          <b>{{ postContent.post_author }}</b>
        </b-row>
<!--    post time-->
        <b-row>
          <span id="post-time"><{{ postTime }}></span>
        </b-row>
<!--        <b-img src="https://placekitten.com/380/200" class="border-secondary shadow" center></b-img>-->
      </b-col>
<!--    Post content-->
      <b-card-text style="width:100%; max-height:400px; overflow-y: auto; margin-left: 4.5rem"
                   v-html="postContent.post_content" class="mt-2"/>
    </b-row>
<!--    Post buttons-->
    <b-row align-h="center" class="mt-3">
      <b-col>
      <b-list-group style="text-align: center" class="shadow-sm" horizontal>
    <!--        Collect Button-->
        <b-list-group-item @click="collectPost"
                           button>
          <b-icon icon="star"></b-icon>
        </b-list-group-item>

    <!--        Like Button-->
        <b-list-group-item :id="'pc-like-'+postContent.post_id"
                           @click="likePost"
                           button>
          <b-icon icon="hand-thumbs-up" v-if="!likePress"></b-icon>
          <b-icon icon="hand-thumbs-up" variant="danger" v-if="likePress"></b-icon>
          <b-badge variant="warning">{{ postContent.post_likes + likeCount }}</b-badge>
        </b-list-group-item>
    <!--        Like list trigger-->
        <b-popover class="like-popover" :target="'pc-like-'+postContent.post_id" triggers="hover" placement="bottom">
          <b-button variant="white"
                    size="sm"
                    v-b-toggle="'postLike-'+postContent.post_id"
                    @click="getLike">
            <b-icon icon="list-stars" size="sm" variant="danger"></b-icon> Like list
          </b-button>
        </b-popover>

    <!--        Comment button-->
        <b-list-group-item :id="'pc-comment-'+postContent.post_id"
                           :ref="'pc-comment-'+postContent.post_id"
                           v-b-toggle="'postComment-'+postContent.post_id"
                           @click="getComment"
                           button>
          <b-icon icon="chat-left-text" size="sm"></b-icon>
          <b-badge variant="warning">{{ comments.length }}</b-badge>
        </b-list-group-item>
        <!--        Comment input trigger-->
        <b-popover :target="'pc-comment-'+postContent.post_id" triggers="hover" placement="bottom">
          <b-button variant="white"
                    :target="'pc-comment-popover-'+postContent.post_id"
                    :id="'pc-comment-popover-'+postContent.post_id"
                    size="sm"
                    v-b-modal="'comment-modal-'+postContent.post_id">
            <b-icon icon="pencil-square" size="sm" variant="danger"></b-icon> Write Comment
          </b-button>
        </b-popover>
    <!--        Child Component: Comment input-->
        <comment-input :post-id="postContent.post_id" @emit-comment="cButtonGetCommentHook"></comment-input>

    <!--        Delete Button-->
        <b-list-group-item button
                           :id="'delete-post-button-'+postContent.post_id"
                           v-if="current_user===postContent.post_author || admin"
                           v-b-modal="'delete-post-modal-'+postContent.post_id+0">
          <b-icon icon="trash"></b-icon>
        </b-list-group-item>
        <b-modal :ref="'delete-post-modal-'+postContent.post_id+0"
                 :id="'delete-post-modal-'+postContent.post_id+0"
                 content-class="shadow"
                 title="Delete Comment"
                 centered>
          Are you sure to delete the post?
          <template #modal-footer>
            <b-button id="delete-post-modal-button-no" @click="hideModal">No</b-button>
            <b-button id="delete-post-modal-button-yes" @click="deletePost" variant="danger">Yes</b-button>
          </template>
        </b-modal>

      </b-list-group>

    <!--      Comment Cards-->
      <b-collapse class="comment-collapse"
                  :id="'postComment-'+postContent.post_id">
        <b-card v-if="!commentEmpty" style="text-align: center">There is no comment here yet.</b-card>
        <div v-if="commentEmpty" v-for="comment in comments" :key="comment.comment_id">
        <!--          Child Component: Comment Card-->
          <comment-card :comment-data="comment"
                        :post-id="postId"
                        :id="comment.comment_id"
                        @reply-event="cButtonGetCommentHook"
                        @comment-deleted="getComment"></comment-card>
        </div>
      </b-collapse>

    <!--       Like list-->
      <b-collapse class="like-collapse" :id="'postLike-'+postContent.post_id">
        <b-card v-if="!likeEmpty" style="text-align: center">Nobody likes this post yet.</b-card>
        <b-card v-if="likeEmpty" v-for="like in likes" :key="like.comment_id">
          <div class="ml-1"><b-avatar size="sm"></b-avatar> <b>{{ like }}</b></div>
        </b-card>
      </b-collapse>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import moment from "moment";
import {mapGetters} from "vuex";
import axios from "axios";
import CommentCard from "@/components/post/commentCard";
import CommentInput from "@/components/post/commentInput";
import RichTextEditor from "@/components/rich-text/tinymceEditor";

export default {
  name: "postCard",
  components: {RichTextEditor, CommentInput, CommentCard},
  props: ['postContent', 'adminCode', 'isMod'],
  data() {
    return{
      postTime: Date,
      isMod: false,
      tmpContent: '',
      likeCount: 0, // Like counter
      comments: [], // The content of comments
      postId: this.postContent.post_id,
      showPost: true,
      likePress: false,  // Like pressed flag
      likes: [],  // Like lists
      editorState: true
    }
  },
  computed: {
    ...mapGetters ({
      current_user: "loginInfo/getLUName",
    }),
    // Comment list empty detector
    commentEmpty: function() {
      if(!this.comments.length){
        return false;
      }
      else{
        return true;
      }
    },
    // Like list empty detector
    likeEmpty: function() {
      if(this.likes.length === 0){
        return false;
      }
      else{
        return true;
      }
    },
    // flag for management page
    admin: function (){
      if(this.adminCode === 1)
        return true
      else
        return false
    }
  },
  methods: {
    collectPost() {
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
      if(this.current_user === '')
        this.$bvToast.toast(
          'Please login before interacting with posts.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else {
        axios
          .post('/collect', null, {
            params: {
              post_id: this.postContent.post_id,
              username: this.current_user
            }
          })
          .then(response => {
            console.log(response)
            if (response.data.code === 200) {
              if (response.data.data === "collect successfully")
                this.constructSuccessToast('Collection added')
              else if (response.data.data === "remove collection successfully")
                this.constructSuccessToast('Collection removed')
            }
          })
          .catch(failResponse => {
            console.log(failResponse)
          })
      }
    },
    likePost() {
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
      if(this.current_user === '')
        this.$bvToast.toast(
          'Please login before interacting with posts.',{
            title: [errTitle],
            toaster: 'b-toaster-top-center',
            variant: 'danger',
            solid: true
          }
        )
      // Communication
      else{
        axios
          .post('/likepost', null, {params:{
              post_id: this.postContent.post_id,
              username: this.current_user
            }
          })
          .then(response=>{
            console.log(response)
            if(response.data.code === 200){
              this.getLike()
              if(response.data.data === 'like successfully'){
                this.likeCount += 1
                this.likePress = true
              }
              else if(response.data.data === 'cancel like successfully'){
                this.likeCount -= 1
                this.likePress = false
              }
            }
          })
          .catch(failResponse=>{
            console.log(failResponse)
          })
      }
    },
    deletePost() {
      axios
        .post('/deletepost', null, {params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.showPost = false
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.hideModal()
      this.$nextTick()
    },
    cButtonGetCommentHook(data) {
      this.setComments(data)
      this.commentEmpty = true
    },
    getComment() {
      // Communication
      axios
        .get('/getcomments',{params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200 && response.data.data){
            this.setComments(response.data.data)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    setComments(comments) {
      this.comments = comments
    },
    getLike() {
      axios
        .get('/getlikes', {params:{post_id:this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.likes = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    hideModal() {
      this.$refs['delete-post-modal-'+this.postContent.post_id+0].hide()
    },
    constructSuccessToast(action) {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Success')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        action+'!',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'success',
          solid: true
        }
      )
    },
    constructFailureToast(action) {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Fail')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        action+'!',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'danger',
          solid: true
        }
      )
    },
    resetEditor(){
      this.editorState = false
      this.$nextTick(()=>{
        this.editorState = true
      })
      this.$bvModal.hide('quit-editor-'+this.postContent.post_id)
    },
    updatePostContent(newContent){
      this.tmpContent = newContent
    },
    updatePost(){
      axios
        .post('/editpost', {post_id: this.postId, post_content: this.tmpContent})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.postContent.post_content = this.tmpContent
            this.constructSuccessToast('Update post successfully')
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.$nextTick(()=>{
        this.$bvModal.hide('update-post-'+this.postId)
        this.$root.$emit('bv::toggle::collapse', 'post-editor-'+this.postContent.post_id)
      })
    },
    pinPostToTop(){
      axios
        .post('/totop', null, {params:{post_id: this.postContent.post_id}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            if(response.data.data === 'Pin post to top successfully')
              this.constructSuccessToast('Pin post to top successfully')
            else if(response.data.data === 'Cancel top')
              this.constructSuccessToast('Cancel top successfully')
            this.postContent.if_top = !this.postContent.if_top
          }
          else{
            this.constructFailureToast('There can only be up to 3 pinned posts at the same time')
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.likeCount = 0
    this.getComment()
    // Transfer the form of Date
    this.postTime = moment(new Date(this.postContent.post_createtime)).format("MMM Do YYYY, HH:mm")
  }
}
</script>

<style scoped>
  #header {

  }
  #post-author {
    font-size: x-large;
  }
  #post-time {
    text-align: right;
    font-size: small;
    color: #888888;
  }
</style>
