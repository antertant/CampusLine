<template>
  <b-list-group style="text-align: center; max-height: 3.5rem"
                class="shadow-sm"
                v-if="!createState"
                horizontal>
    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      {{moduleRequest.module_name}}
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      {{moduleRequest.request_count}}
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem">
      <b-button style="padding: 0"
                title="create the module"
                v-b-modal="'create-module-modal-'+moduleRequest.module_name"
                variant="white">
        <b-icon icon="plus-square" variant="success" />
      </b-button>

      <b-modal :id="'create-module-modal-'+moduleRequest.module_name"
               title="Module Creation"
               centered>
        Are you sure to create module <em><b>[{{moduleRequest.module_name}}]</b></em>?
        <template #modal-footer>
          <b-button @click="hideModal">No</b-button>
          <b-button @click="createModule" variant="success">Yes</b-button>
        </template>
      </b-modal>

    </b-list-group-item>
  </b-list-group>
</template>

<script>
import axios from "axios";
export default {
  name: "moduleRequestItem",
  props:['moduleRequest'],
  data() {
    return{
      createState: false
    }
  },
  methods:{
    hideModal(){
      this.$bvModal.hide("create-module-modal-"+this.moduleRequest.module_name)
    },
    createModule(){
      axios
        .post('/createmodule', null, {params:{module_name: this.moduleRequest.module_name}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.createState = true
            this.$nextTick()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
      this.hideModal()
    }
  }
}
</script>

<style scoped>

</style>
