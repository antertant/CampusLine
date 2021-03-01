<template>
  <b-card no-body style="min-width: 8rem">
<!--    Role info-->
    <b-card align="center">
      <template #header>
        ROLE
      </template>
      <b>{{ role }}</b>
    </b-card>
<!--    Point info-->
    <b-card align="center">
      <template #header>
        POINT
      </template>
      <b>{{ points }}</b>
    </b-card>
<!--    Manager info-->
    <b-card align="center">
      <template #header>
        MANAGER
      </template>
<!--      Apply for manager-->
      <b-button block variant="info" v-if="role === 'user'" @click="applyAdmin">
        Apply
      </b-button>
<!--      Retire from manager-->
      <b-button block variant="danger" v-if="role === 'admin'" @click="retireAdmin">
        Retire
      </b-button>
<!--      Manage the module-->
      <b-button block variant="primary" v-if="role === 'admin'"
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
        'Apply for admin authentication failed.',{
          title: [errTitle],
          toaster: 'b-toaster-top-center',
          variant: 'danger',
          solid: true
        }
      )
    }
  },
  mounted() {
    this.getRole()
    this.getPoints()
  }
}
</script>

<style scoped>

</style>
