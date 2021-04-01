<template>
  <b-card style="max-width: 65rem; align-items: center"
          class="mx-auto border-0">
    <module-request-title class="mb-2" />
    <module-request-item v-for="singleRequest in moduleRequestList"
                         :key="singleRequest.module_name"
                         :module-request="singleRequest" />
  </b-card>
</template>

<script>
import ModuleRequestTitle from "@/components/administrator/moduleRequestTitle";
import axios from "axios";
import ModuleRequestItem from "@/components/administrator/moduleRequestItem";
export default {
  name: "moduleRequestCard",
  components: {ModuleRequestItem, ModuleRequestTitle},
  data() {
    return {
      moduleRequestList: []
    }
  },
  methods:{
    getModuleRequestList() {
      axios
        .get('/getrequests')
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.moduleRequestList = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getModuleRequestList()
  }
}
</script>

<style scoped>

</style>
