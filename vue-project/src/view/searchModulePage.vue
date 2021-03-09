<template>
  <div id="searchModBody" style="margin-top: 6rem">
    <h3 class="text-center my-5" v-if="searchResult.length>1">
      Get {{ searchResult.length }} results from searching
      "{{ searchKey }}" in Modules ...
    </h3>
    <h3 class="text-center my-5" v-if="searchResult.length<=1">
      Get {{ searchResult.length }} result from searching
      "{{ searchKey }}" in Modules ...
    </h3>
    <module-card :multi-modules="searchResult"></module-card>
  </div>
</template>

<script>
import axios from "axios";
import ModuleCard from "@/components/module/moduleCard";

export default {
  name: "searchModulePage",
  components: {ModuleCard},
  props: ['searchKey'],
  data (){
    return{
      searchResult: []
    }
  },
  methods: {
    getSearchResult() {
      axios
        .get('/searchmodule', {params:{key: this.searchKey}})
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
  created() {
    document.title = 'UWSK - SearchModule_' + this.searchKey
  },
  mounted() {
    this.getSearchResult()
  }
}
</script>

<style scoped>

</style>
