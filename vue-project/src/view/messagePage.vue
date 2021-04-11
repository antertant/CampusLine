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
                          :chat-count="newChatMessageCount"
                          :like-count="newMessageCount.cnewlpost + newMessageCount.cnewlcomment"
                          :comment-count="newMessageCount.cnewpostcomment + newMessageCount.cnewcommentreply"/>
      </b-col>

      <!--    message content card-->
      <b-col cols="auto">
        <!--      comment list card-->
        <message-comment-card :style="cardStyle"
                              v-if="mType === 'comment'"
                              :post-c="newMessageCount.cnewpostcomment"
                              :comment-r="newMessageCount.cnewcommentreply"/>
        <!--      like list card-->
        <mesage-like-card :style="cardStyle"
                          v-if="mType === 'like'"
                          :post-l="newMessageCount.cnewlpost"
                          :comment-l="newMessageCount.cnewlcomment"/>
        <!--      chat list card-->
        <message-chat-card :style="cardStyle"
                           v-if="mType === 'chat'"
                           :user-name="currentUser"/>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import MessageSideBar from "@/components/messages/messageSideBar";
import MessageCommentCard from "@/components/messages/messageCommentCard";
import MesageLikeCard from "@/components/messages/mesageLikeCard";
import {mapGetters} from "vuex";
import axios from "axios";
import MessageChatCard from "@/components/messages/messageChatCard";
export default {
  name: "messagePage",
  components: {MessageChatCard, MesageLikeCard, MessageCommentCard, MessageSideBar},
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
      newMessageCount: "newMessage/getNewMessageCount",
      newChatMessageCount: 'newMessage/getNewChatMessageCount'
    })
  },
  methods:{
    getHeight(){
      this.cardStyle.height = (window.innerHeight-180).toString()+'px'
    }
  },
  created() {
    document.title = 'CampusLine - Message Box'
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
