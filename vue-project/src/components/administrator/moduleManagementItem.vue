<template>
  <b-list-group style="text-align: center; max-height: 3.5rem"
                class="shadow-sm"
                v-if="!deleteState"
                horizontal>
    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      {{ModuleInfo.module_name}}
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem">
      <b-button style="padding: 0"
                title="delete the module"
                v-b-modal="'delete-module-modal-'+ModuleInfo.module_name"
                variant="white">
        <b-icon icon="trash" variant="danger" />
      </b-button>

      <b-modal :id="'delete-module-modal-'+ModuleInfo.module_name"
               title="Delete module"
               centered>
        Are you sure to delete module <em><b>[{{ModuleInfo.module_name}}]</b></em>?
        <template #modal-footer>
          <b-button @click="hideModal">No</b-button>
          <b-button @click="deleteModule" variant="danger">Yes</b-button>
        </template>
      </b-modal>

    </b-list-group-item>
  </b-list-group>
</template>

<script>
import axios from "axios";
export default {
  name: "moduleManagementItem",
  props:['ModuleInfo'],
  data() {
    return{
      deleteState: false
    }
  },
  methods:{
    deleteModule(){
      axios
        .post('/deletemodule', null, {params:{moduleName: this.ModuleInfo.module_name}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.deleteState = true
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.hideModal()
      this.$nextTick()
    },
    hideModal() {
      this.$bvModal.hide('delete-module-modal-'+this.ModuleInfo.module_name)
    }
  }
}
</script>

<style scoped>

</style>
