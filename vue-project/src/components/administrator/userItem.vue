<template>
  <b-list-group style="text-align: center; max-height: 3.5rem"
                class="shadow-sm"
                v-if="!deleteState"
                horizontal>
    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      <b>{{userInfo.username}}</b>
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      <span v-if="userInfo.email"><b>{{userInfo.email}}</b></span>
      <span v-else style="font-size: small"><em>N/A</em></span>
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem" class="border-right-0">
      <span v-if="userInfo.module_admin"><b>{{userInfo.module_admin}}</b></span>
      <span v-else style="font-size: small"><em>N/A</em></span>
    </b-list-group-item>

    <b-list-group-item style="min-width: 15rem">
      <b-button style="padding: 0"
                title="delete the user"
                v-b-modal="'delete-user-modal-'+userInfo.username"
                variant="white">
        <b-icon icon="trash" variant="danger" />
      </b-button>

      <b-modal :id="'delete-user-modal-'+userInfo.username"
               title="Delete User"
               centered>
        Are you sure to delete user <em><b>[{{userInfo.username}}]</b></em>?
        <template #modal-footer>
          <b-button @click="hideModal">No</b-button>
          <b-button @click="deleteUser" variant="danger">Yes</b-button>
        </template>
      </b-modal>

    </b-list-group-item>
  </b-list-group>
</template>

<script>
import axios from "axios";
export default {
  name: "userItem",
  props:['userInfo'],
  data() {
    return{
      deleteState: false
    }
  },
  methods:{
    deleteUser(){
      axios
        .post('/deleteuser', null, {params:{username: this.userInfo.username}})
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
      this.$bvModal.hide('delete-user-modal-'+this.userInfo.username)
    }
  }
}
</script>

<style scoped>

</style>
