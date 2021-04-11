<template>
  <div id="searchModBody" style="padding-bottom: 6rem; padding-top: 6rem">
    <h3 class="text-center my-5" v-if="searchResult.length>1">
      Get {{ searchResult.length }} results from searching
      "{{ searchKey }}" in Modules ...
    </h3>
    <h3 class="text-center my-5" v-if="searchResult.length<=1">
      Get {{ searchResult.length }} result from searching
      "{{ searchKey }}" in Modules ...
    </h3>
    <module-list :multi-modules="searchResult"/>
  </div>
</template>

<script>
import axios from "axios";
import ModuleCard from "@/components/module/moduleCard";
import ModuleList from "@/components/module/moduleList";

export default {
  name: "searchModulePage",
  components: {ModuleList, ModuleCard},
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
    document.title = 'CampusLine - SearchModule_' + this.searchKey
  },
  mounted() {
    this.getSearchResult()
  }
}
</script>

<style scoped>

</style>
