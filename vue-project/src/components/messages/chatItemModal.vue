<template>
  <b-modal size="lg" style="padding: 0"
           :id="'chat-messenger'+fromUser"
           centered>

    <template #modal-header>
      <b style="font-size: larger"><em>
        Chatting with [{{fromUser}}]
      </em></b>
    </template>

    <b-card class="border-0">
      <div v-for="singleMessage in chatMessageList">
        <b-row v-show="singleMessage.to_user === userName" align-h="end" class="mb-3">
          <b-col cols="auto">
            <b-card style="background-color: #f3da86; max-width: 20rem; border-radius: 10px; padding:0.7rem" no-body>
              {{singleMessage.content}}
            </b-card>
          </b-col>
          <b-avatar :to="'/profile='+fromUser" :title="fromUser" />
        </b-row>
        <b-row v-show="singleMessage.to_user !== userName" class="mb-3">
          <b-avatar :title="toUser" />
          <b-col cols="auto">
            <b-card style="background-color: #e3e3e3; max-width: 20rem; border-radius: 10px;padding: 0.7rem" no-body>
              {{singleMessage.content}}
            </b-card>
          </b-col>
        </b-row>
      </div>
    </b-card>

    <template #modal-footer>
      <b-textarea v-model="currentChatContent"
                  placeholder="Write your message here..."/>
      <b-button @click="resetChatInput" variant="dark" class="float-right">Reset</b-button>
      <b-button @click="sendChatMessage" variant="warning" class="float-right">Send</b-button>
    </template>

  </b-modal>
</template>

<script>
import axios from "axios";

export default {
  name: "chatItemModal",
  props:['fromUser', 'toUser', 'userName'],
  data() {
    return{
      chatMessageList: [],
      currentChatContent: '',
      interval: ''
    }
  },
  methods:{
    getChatList() {
      axios
        .get('/getchat', {
          params:{
            username: this.toUser,
            from_user: this.fromUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.chatMessageList = response.data.data.reverse()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    resetChatInput() {
      this.currentChatContent = ''
    },
    sendChatMessage() {
      axios
        .post('/chatsend', {
          to_user: this.fromUser,
          from_user: this.toUser,
          content: this.currentChatContent
        })
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.getChatList()
            this.resetChatInput()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getChatList()
    this.interval = setInterval(()=>{
      this.getChatList()
    }, 1000*5)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
