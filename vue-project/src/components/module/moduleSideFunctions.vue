<template>
  <b-card no-body style="min-width: 9rem">
<!--    Point info-->
    <b-card align="center">
      <b-card-header>POINT</b-card-header>
      <b>{{ points }}</b>
    </b-card>
<!--    Manager info-->
    <b-card align="center">
      <template #header>
        ADMIN
      </template>
<!--      Apply for manager-->
      <b-button :id="'manager-apply-'+moduleName" block variant="warning" v-if="role === 'user'" @click="applyAdmin">
        Apply
      </b-button>
<!--      Retire from manager-->
      <b-button :id="'manager-quit-'+moduleName" block variant="danger" v-if="role === 'admin'" @click="retireAdmin">
        Quit
      </b-button>
<!--      Manage the module-->
      <b-button :id="'manager-manage-'+moduleName" block variant="warning" v-if="role === 'admin'"
                :to="'/module_management=' + moduleName">
        Manage
      </b-button>
    </b-card>
  </b-card>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "moduleSideFunctions",
  props: ['moduleName'],
  data() {
    return {
      role: 'user',
      points: 0
    }
  },
  computed: {
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    })
  },
  methods: {
    getRole() {
      axios
        .get('/getrole', {params:{
            username: this.currentUser,
            module_name: this.moduleName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.role = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getPoints() {
      axios
        .post('/getpoints', null, {params:{
            username: this.currentUser,
            module_name: this.moduleName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.points = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    applyAdmin() {
      axios
        .post('/applyadmin', null, {params:{
            username: this.currentUser,
            module_name: this.moduleName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.getRole()
          }
          else if(response.data.code === 400){
            this.constructError()
          }
        })
      .catch(failResponse=>{
        console.log(failResponse)
      })
    },
    retireAdmin() {
      axios
        .post('/deladmin', null, {params:{
            username: this.currentUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.getRole()
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    constructError() {
      // Alert component construction
      const crtEl = this.$createElement
      const errTitle = crtEl(
        'p',
        { class: ['text-center', 'mb-0'] },
        [
          crtEl('b-icon', { props:{ icon: 'exclamation-diamond', small: true } }),
          crtEl('strong', ' Error')
        ]
      )
      // Show alert
      this.$bvToast.toast(
        'You have been an admin of certain module ,or the ' +
        'module has had enough admins ,or do not have enough points ' +
        'to become an admin',{
          title: [errTitle],
          class: 'error-toast',
          toaster: 'b-toaster-top-center',
          variant: 'danger',
          solid: true
        }
      )
    }
  },
  mounted() {
    if(this.currentUser !== ''){
      this.getRole()
      this.getPoints()
    }
  }
}
</script>

<style scoped>

</style>
