<template>
  <b-card class="shadow" no-body>
    <template #header>
      <h5>Chat</h5>
    </template>
    <b-list-group>
      <div v-for="chatMessageListItem in chatMessageList"
           :key="chatMessageListItem.from_user">
        <b-list-group-item @click="showChatModal(chatMessageListItem)"
                           variant="dark"
                           style="padding: 0"
                           button>
          <chat-item-card :chat-data="chatMessageListItem"/>
        </b-list-group-item>

        <chat-item-modal :from-user="chatMessageListItem.from_user"
                         :to-user="chatMessageListItem.to_user"
                         :user-name="userName"
                         v-if="modalFlag & currentFromUser === chatMessageListItem.from_user"/>

      </div>
    </b-list-group>
  </b-card>
</template>

<script>
import axios from "axios";
import ChatItemCard from "@/components/messages/chatItemCard";
import ChatItemModal from "@/components/messages/chatItemModal";
import {mapGetters} from "vuex";
export default {
  name: "messageChatCard",
  components: {ChatItemModal, ChatItemCard},
  props: ['userName'],
  data() {
    return{
      chatMessageList: [],
      interval: '',
      modalFlag: false,
      currentFromUser: ''
    }
  },
  methods:{
    getChatMessage(){
      axios
        .get('/getallnewestchat', {params:{username: this.userName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.chatMessageList = response.data.data
            this.$store.dispatch('newMessage/getNewChatMessageCountFS', this.userName)
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    showChatModal(chatMessageListItem) {
      this.modalFlag = true
      this.currentFromUser = chatMessageListItem.from_user
      this.$nextTick(()=>{
        this.$bvModal.show('chat-messenger'+chatMessageListItem.from_user)
      })
    }
  },
  mounted() {
    this.$root.$on('bv::modal::hide', (bvEvent, modalId) => {
      this.modalFlag = false
    })
    this.getChatMessage()
    this.interval = setInterval(this.getChatMessage, 1000)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
