<template>
  <b-row align-h="center" style="margin-top: 5rem">
    <b-col lg="5" sm="8">
<!--      header button-->
      <b-button v-b-toggle="'life-header-toggle'"
                variant="info"
                class="mb-2"
                block>
        <span class="h3"><b-icon icon="justify" class="float-left"></b-icon></span>
        <h2 style="text-align: center">{{modName.toUpperCase()}}</h2>
      </b-button>
<!--      header collapse-->
      <b-collapse id="life-header-toggle" class="mb-2">
        <b-list-group>
<!--        current administrators-->
          <b-list-group-item>
            <b>Current Administrator in this module:</b>
            <span v-for="admin in adminList">{{ admin }} || </span>
            <span v-if="adminList.length === 0">
            There is no administrator in this module currently.
              <b-icon icon="exclamation-octagon"></b-icon>
            </span>
          </b-list-group-item>

          <b-list-group-item v-if="currentUser !== ''" style="text-align: center">
            <b-button variant="info" :pressed="ownPosts" @click="ownPosts=true">Self Posts</b-button>
            <b-button variant="info" :pressed="!ownPosts" @click="ownPosts=false">All Posts</b-button>
          </b-list-group-item>
        </b-list-group>
      </b-collapse>

<!--      module posts creator-->
      <post-input :module-name="modName"></post-input>

<!--      post list-->
      <div v-for="list in postList">
        <post-card :post-content="list"></post-card>
      </div>
    </b-col>

    <b-col cols="auto">
      <b-row class="position-fixed">
<!--        side bar-->
        <module-side-functions :module-name="modName"></module-side-functions>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import {mapGetters} from "vuex";
import PostCard from "../components/post/postCard";
import PostInput from "@/components/post/postInput";
import ModuleSideFunctions from "@/components/module/moduleSideFunctions";
import axios from "axios";

export default {
  name: "modulesContentPage",
  components: {ModuleSideFunctions, PostInput, PostCard},
  props: ['modName'],
  data() {
    return {
      adminList: [],
      ownPosts: false,
      selfPosts: []
    }
  },
  computed: {
    ...mapGetters ({
      modulePostList: "modulePostInfo/getModulePostList",
      currentUser: "loginInfo/getLUName"
    }),
    postList: function (){
      if(!this.ownPosts)
        return this.modulePostList
      else
        return this.selfPosts
    }
  },
  methods:{
    getAdminList() {
      axios
        .get('/getadmins', {params:{module_name: this.modName}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.adminList = response.data.data
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getOwnPosts() {
      axios
        .get('/getselfposts', {params:{
            module_name: this.modName,
            username: this.currentUser
          }})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.selfPosts = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    }
  },
  created() {
    document.title = 'UWSK - Knowledge_' + String(this.modName).toUpperCase()
  },
  mounted() {
    this.$store.dispatch("modulePostInfo/getModulePostfromServer", this.modName)
    this.getOwnPosts()
    this.getAdminList()
  }
}
</script>

<style scoped>

</style>
