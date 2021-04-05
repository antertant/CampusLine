<template>
  <div style="padding-bottom: 6rem; padding-top: 6rem">
    <b-card id="visitorMessageInfo" v-if="!loginState" align="center">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon>
      Please login before browsing message box.
    </b-card>
    <b-row align-h="center" v-if="loginState">
      <!--    message side bar-->
      <b-col cols="auto">
        <message-side-bar :m-type="mType"
                          :like-count="newMessageCount.cnewlpost + newMessageCount.cnewlcomment"
                          :comment-count="newMessageCount.cnewpostcomment + newMessageCount.cnewcommentreply"></message-side-bar>
      </b-col>

      <!--    message content card-->
      <b-col cols="auto">
        <!--      comment list card-->
        <message-comment-card :style="cardStyle"
                              v-if="mType === 'comment'"
                              :post-c="newMessageCount.cnewpostcomment"
                              :comment-r="newMessageCount.cnewcommentreply"></message-comment-card>
        <!--      repost list card-->
<!--        <message-repost-card v-if="mType === 'repost'"></message-repost-card>-->
        <!--      like list card-->
        <mesage-like-card :style="cardStyle"
                          v-if="mType === 'like'"
                          :post-l="newMessageCount.cnewlpost"
                          :comment-l="newMessageCount.cnewlcomment"></mesage-like-card>
        <!--      messager card-->
<!--        <message-m-card v-if="mType === 'message'"></message-m-card>-->
      </b-col>
    </b-row>
  </div>
</template>

<script>
import MessageSideBar from "@/components/messages/messageSideBar";
import MessageCommentCard from "@/components/messages/messageCommentCard";
import MessageRepostCard from "@/components/messages/messageRepostCard";
import MesageLikeCard from "@/components/messages/mesageLikeCard";
import MessageMCard from "@/components/messages/messageMCard";
import {mapGetters} from "vuex";
import axios from "axios";
export default {
  name: "messagePage",
  components: {MessageMCard, MesageLikeCard, MessageRepostCard, MessageCommentCard, MessageSideBar},
  props: ['mType'],
  data() {
    return{
      cardStyle:{
        'min-width': '50rem',
        'height': '',
        'overflow-y': 'auto'
      }
    }
  },
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName",
      loginState: "loginInfo/getLoginState",
      newMessageCount: "newMessage/getNewMessageCount"
    })
  },
  methods:{
    getHeight(){
      this.cardStyle.height = (window.innerHeight-180).toString()+'px'
    }
  },
  created() {
    document.title = 'UWSK - Message Box'
    window.addEventListener("resize", this.getHeight)
    this.getHeight()
  },
  mounted() {
    this.$store.dispatch("newMessage/getNewMessageCountFS", this.currentUser)
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight)
  }
}
</script>

<style scoped>

</style>
