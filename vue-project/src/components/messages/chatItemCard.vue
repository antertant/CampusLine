<template>
  <b-card>
    <b-row>

      <b-col cols="auto">
        <b-avatar :badge="unreadCount" badge-variant="danger" />
      </b-col>

      <b-col cols="auto">
        <b-row>
          <b>{{chatData.from_user}}</b>
        </b-row>
        <b-row>
          {{chatData.content}}
        </b-row>
      </b-col>

    </b-row>
  </b-card>
</template>

<script>
import axios from "axios";

export default {
  name: "chatItemCard",
  props:['chatData'],
  data() {
    return{
      unreadCount: false,
      interval: ''
    }
  },
  methods:{
    getUnreadCount(){
      axios
        .get('/countunreadchat', {
          params:{
            username: this.chatData.to_user,
            from_user: this.chatData.from_user}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            if(response.data.data > 0)
              this.unreadCount = Number(response.data.data)
            else
              this.unreadCount = false
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getUnreadCount()
    this.interval = setInterval(this.getUnreadCount, 1000)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
