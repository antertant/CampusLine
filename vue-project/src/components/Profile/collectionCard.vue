<template>
  <div>
    <div v-for="collection in collectList" key="1">
      <post-card :post-content="collection"></post-card>
    </div>
  </div>
</template>

<script>
import PostCard from "@/components/post/postCard";
import axios from "axios";
export default {
  name: "collectionCard",
  components: {PostCard},
  props: ['profileUser'],
  data() {
    return{
      collectList: []
    }
  },
  methods: {
    getCollection() {
      axios
        .get('/getcollects', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.collectList = response.data.data
          }
          else{
            this.collectList = 'error'
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getCollection()
  }
}
</script>

<style scoped>

</style>
