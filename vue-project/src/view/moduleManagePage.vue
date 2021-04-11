<template>
  <div>
    <b-row style="padding-top: 6rem; padding-bottom: 6rem" align-h="center" v-if="role === 'admin'">
      <b-col lg="5" sm="8">
        <h1 style="text-align: center">{{modName.toUpperCase()}} MANAGEMENT</h1>
        <module-intro-input :module-name="modName"></module-intro-input>
        <div v-for="list in modulePostList">
          <post-card :post-content="list" :is-mod="true" :admin-code="1"></post-card>
        </div>
      </b-col>
      <b-col cols="auto">
        <b-row class="position-fixed">
          <module-side-functions style="margin-top: 6rem" :module-name="modName"></module-side-functions>
        </b-row>
      </b-col>
    </b-row>
    <b-row align-h="center" v-if="role === 'user'">
      <h2>Sorry, you have no authentication to access this page.</h2>
    </b-row>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";
import PostCard from "@/components/post/postCard";
import ModuleSideFunctions from "@/components/module/moduleSideFunctions";
import ModuleIntroInput from "@/components/module/moduleIntroInput";

export default {
  name: "moduleManagePage",
  components: {ModuleIntroInput, ModuleSideFunctions, PostCard},
  props: ['modName'],
  data() {
    return{
      role: 'user',
      moduleIntro: 'test'
    }
  },
  computed: {
    ...mapGetters ({
      modulePostList: "modulePostInfo/getModulePostList",
      modulesList: 'moduleList/getModuleList',
      currentUser: "loginInfo/getLUName"
    })
  },
  methods: {
    getRole() {
      axios
        .get('/getrole', {params:{
            username: this.currentUser,
            module_name: this.modName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.role = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  created() {
    document.title = 'CampusLine - Module Management'
  },
  mounted() {
    this.$store.dispatch("modulePostInfo/getModulePostfromServer", this.modName)
    this.$store.dispatch('moduleList/getModulefromServer')
    this.getRole()

    this.$nextTick(()=>{
      // this.moduleIntro = this.modulesList[0].module_intro
      let module
      for(let i=0; i<this.modulesList.length; i++){
        module = this.modulesList[i]
        if(module.module_name === this.modName){
          this.moduleIntro = module.module_intro
          break
        }
      }
    })
  }
}
</script>

<style scoped>

</style>
