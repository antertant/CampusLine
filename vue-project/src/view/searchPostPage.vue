<template>
  <div style="margin-top: 6rem">
    <h3 class="text-center my-5" v-if="searchResult.length>1">
      Get {{ searchResult.length }} results from searching
      "{{ searchKey }}" in Posts ...
    </h3>
    <h3 class="text-center my-5" v-if="searchResult.length<=1">
      Get {{ searchResult.length }} result from searching
      "{{ searchKey }}" in Posts ...
    </h3>    <post-card v-for="singleResult in searchResult"
               :post-content="singleResult"
               :key="singleResult.post_id"></post-card>
  </div>
</template>

<script>
import axios from "axios";
import PostCard from "@/components/post/postCard";

export default {
  name: "searchPostPage",
  components: {PostCard},
  props: ['searchKey'],
  data (){
    return{
      searchResult: []
    }
  },
  methods: {
    getSearchResult() {
      axios
        .get('/searchpost', {params:{key: this.searchKey}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.searchResult = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getSearchResult()
  }
}
</script>

<style scoped>

</style>
