<template>
  <b-card class="shadow" no-body>
    <template #header>
      <h5>Chat</h5>
    </template>
    <b-list-group>
      <div v-for="chatMessageListItem in chatMessageList"
           :key="chatMessageListItem.from_user">
        <b-list-group-item @click="$bvModal.show('chat-messenger'+chatMessageListItem.from_user)"
                           variant="dark"
                           style="padding: 0"
                           button>
          <chat-item-card :chat-data="chatMessageListItem"/>
        </b-list-group-item>

        <chat-item-modal :from-user="chatMessageListItem.from_user"
                         :to-user="chatMessageListItem.to_user"
                         :user-name="userName"/>

      </div>
    </b-list-group>
  </b-card>
</template>

<script>
import axios from "axios";
import ChatItemCard from "@/components/messages/chatItemCard";
import ChatItemModal from "@/components/messages/chatItemModal";
export default {
  name: "messageChatCard",
  components: {ChatItemModal, ChatItemCard},
  props: ['userName'],
  data() {
    return{
      chatMessageList: [],
      interval: ''
    }
  },
  methods:{
    getChatMessage(){
      axios
        .get('/getunreadchatlist', {params:{username: this.userName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.chatMessageList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getChatMessage()
    this.interval = setInterval(this.getChatMessage, 1000*5)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
