<template>
  <b-row align-h="center" style="padding-top: 6rem; padding-bottom: 6rem">
    <b-col lg="5" sm="8">
<!--      header button-->
      <b-button v-b-toggle="'life-header-toggle'"
                id="lifeHeader"
                variant="dark"
                class="mb-2"
                block>
        <span class="h3"><b-icon icon="justify" class="float-left"></b-icon></span>
        <h2 style="text-align: center">LIFE</h2>
      </b-button>
<!--      header collapse-->
      <b-collapse id="life-header-toggle" class="mb-2">
        <b-list-group>
<!--        current administrators-->
          <b-list-group-item id="admin-list-life">
            <div v-if="adminList.length !== 0" style="text-align: center">
              <b><em>Current Administrators in this module:</em></b>
              <b-button v-for="admin in adminList"
                        :key="admin[0]"
                        style="text-align: center;"
                        variant="dark"
                        :to="'/profile='+admin"
                        class="mx-1">
                @{{ admin }}
              </b-button>
            </div>
            <div style="text-align: center" v-else>
              <b>
                <em>
                  There is no administrator in this module currently.
                  <b-icon icon="exclamation-octagon"></b-icon>
                </em>
              </b>
            </div>
          </b-list-group-item>

          <b-list-group-item v-if="currentUser !== ''" style="text-align: center">
            <b-button id="life-self-button" variant="warning" :pressed="ownPosts===1" @click="ownPosts=1">Self Posts</b-button>
            <b-button id="life-all-button" variant="warning" :pressed="ownPosts===0" @click="ownPosts=0">All Posts</b-button>
          </b-list-group-item>
        </b-list-group>
      </b-collapse>

<!--      post creator-->
      <post-rich-input v-if="ownPosts===0" :own-flag="ownPosts" module-name="life"/>

<!--      post list-->
      <div id="life-post-list">
        <post-card v-for="list in postList"
                   :post-content="list"
                   :is-mod="true"
                   :key="list.post_id"
                   class="mx-auto" />
      </div>
    </b-col>
    <b-col cols="auto">
      <b-row class="position-fixed">

<!--        side bar-->
        <module-side-functions module-name="life"></module-side-functions>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import PostCard from "../components/post/postCard";
import {mapGetters} from "vuex";
import PostInput from "@/components/post/postInput";
import ModuleSideFunctions from "@/components/module/moduleSideFunctions";
import axios from "axios";
import PostRichInput from "@/components/post/postRichInput";

export default {
  name: "lifeCirclePage",
  components: {PostRichInput, ModuleSideFunctions, PostInput, PostCard},
  data() {
    return {
      adminList: [],
      ownPosts: 0,
      selfPosts: []
    }
  },
  computed: {
    ...mapGetters({
      lifePostList: 'lifePostInfo/getLifePostList',
      currentUser: "loginInfo/getLUName"
    }),
    postList: function () {
      if(this.ownPosts === 0)
        return this.lifePostList
      else
        return this.selfPosts
    }
  },
  methods:{
    getAdminList() {
      axios
        .get('/getadmins', {params:{module_name: 'life'}})
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
            module_name: 'life',
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
    document.title = 'CampusLine - Life Circle'
  },
  mounted() {
    this.$store.dispatch("lifePostInfo/getlifePostfromServer")
    this.getAdminList()
    this.getOwnPosts()
  }
}
</script>

<style scoped>

</style>
