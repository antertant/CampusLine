<template>
  <div>
    {{newMessageCount}}
    {{counter}}
    <b-card v-if="currentUser===''" align="center">
      <b-icon icon="exclamation-diamond" variant="danger"></b-icon>
      Please login before browsing message box.
    </b-card>
    <b-row align-h="center" style="margin-top: 6rem" v-if="currentUser!==''">
      <!--    message side bar-->
      <b-col lg="2" sm="3" md="3">
        <message-side-bar class="position-fixed" :m-type="mType"></message-side-bar>
      </b-col>

      <!--    message content card-->
      <b-col sm="8" md="6" class="">
        <!--      comment list card-->
        <message-comment-card v-if="mType === 'comment'"></message-comment-card>
        <!--      repost list card-->
<!--        <message-repost-card v-if="mType === 'repost'"></message-repost-card>-->
        <!--      like list card-->
        <mesage-like-card v-if="mType === 'like'"></mesage-like-card>
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
  computed:{
    ...mapGetters({
      currentUser: "loginInfo/getLUName",
      newMessageCount: "newMessage/getNewMessageCount"
    })
  },
  mounted() {
    this.$store.dispatch("newMessage/getNewMessageCountFS", this.currentUser)
  }
}
</script>

<style scoped>

</style>
