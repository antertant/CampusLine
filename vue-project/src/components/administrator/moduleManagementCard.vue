<template>
  <b-card style="max-width: 65rem; align-items: center"
          class="mx-auto border-0">
    <module-management-title class="mb-2" />
    <module-management-item v-for="singleModuleName in moduleExistList"
                            :module-info="singleModuleName"
                            :key="singleModuleName.module_name" />
  </b-card>
</template>

<script>
import ModuleManagementTitle from "@/components/administrator/moduleManagementTitle.vue";
import ModuleManagementItem from "@/components/administrator/moduleManagementItem.vue";
import axios from "axios";


export default {
  name: "moduleManagementCard",
  components: {ModuleManagementItem, ModuleManagementTitle},
  props: ['ModuleInfo'],
  data(){
    return{
      moduleExistList: [],
    }
  },
  methods:{
    getModuleList(){
      axios
        .get('/getmodule',{params:{choice:2}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.moduleExistList = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getModuleList();
  }
}

</script>

<style scoped>

</style>

