<template>
  <b-card style="max-width: 68rem" class="mx-auto" no-body>
    <b-tabs card>

      <b-tab>
        <template #title>
          <b-icon icon="tag" variant="primary"></b-icon>
          <b>Users Management</b>
        </template>
        <user-item-card :user-info="userInfo" />
      </b-tab>

      <b-tab>
        <template #title>
          <b-icon icon="tag" variant="primary"></b-icon>
          <b>Module Request Management</b>
        </template>
        <module-request-card/>
      </b-tab>

    </b-tabs>
  </b-card>
</template>

<script>
import UserItemCard from "@/components/administrator/userItemCard";
import axios from "axios";
import ModuleRequestCard from "@/components/administrator/moduleRequestCard";
export default {
  name: "adminTabs",
  components: {ModuleRequestCard, UserItemCard},
  data() {
    return{
      userInfo:[{
        userName: 'testName1',
        userEmail: 'testEmail@example.com',
        moduleAdmin: 'testAdmin1'
      },
        {
          userName: 'testName2',
          userEmail: 'testEmail@example.com',
          moduleAdmin: 'testAdmin2'
        }],
    }
  },
  methods:{
    getUserList() {
      axios
        .get('/getusers')
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.userInfo = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  mounted() {
    this.getUserList()
  }
}
</script>

<style scoped>

</style>
