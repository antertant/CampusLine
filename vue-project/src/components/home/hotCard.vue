<template>
 <div class="mx-auto border-0" no-body>
<!--  hot content-->
   <b-row align-h="center">
     <b-col cols="auto" class="mx-3">
       <!--       Enter the module-->
       <div>
         <hot-card-title id="lifeEnterButton" :mod-name="hotData[0].module_name"/>
       </div>
       <!--       module post list-->
       <b-card :style=innerCardStyle class="mb-3" no-body>
         <hot-card-input id="hot-life-input" v-if="currentUser" class="mt-2 mx-2" :mod-name="hotData[0].module_name" />
         <post-card v-for="singlePost in hotData[0].posts"
                    :post-content="singlePost"
                    :key="singlePost.post_id"
                    class="mx-auto"></post-card>
       </b-card>
     </b-col>
     <b-col cols="auto">
       <div>
         <hot-card-title id="knowledgeEnterButton" style="width: 55rem" mod-name="Knowledge"/>
       </div>
        <b-tabs active-nav-item-class="font-weight-bold text-uppercase text-dark bg-white"
                v-model="tabIndex"
                justified>
          <b-tab v-for="singleKnowModule in hotKnowData"
                 :id="'knowledge-tab-'+singleKnowModule.module_name"
                 :key="singleKnowModule.module_name">
            <template #title>
              <b style="color: #000000">
                {{singleKnowModule.module_name}}
              </b>
            </template>
            <b-card :style=innerKnowCardStyle  no-body>
              <hot-card-input v-if="currentUser"
                              :id="'hot-card-input-'+singleKnowModule.module_name"
                              class="mt-2 mx-2"
                              :mod-name="singleKnowModule.module_name" />
              <post-card v-for="singlePost in singleKnowModule.posts"
                         :post-content="singlePost"
                         :key="singlePost.post_id"
                         class="mx-auto"></post-card>
            </b-card>
          </b-tab>
        </b-tabs>
     </b-col>
   </b-row>
<!--   <div v-for="singleModulePosts in hotPosts">-->
<!--     <post-card :post-content="singleModulePosts"></post-card>-->
<!--   </div>-->
 </div>
</template>

<script>
import PostCard from "@/components/post/postCard";
import HotCardTitle from "@/components/home/hotCardTitle";
import HotCardInput from "@/components/home/hotCardInput";
import axios from "axios";
import {mapGetters} from "vuex";
export default {
  name: "hotCard",
  components: {HotCardInput, HotCardTitle, PostCard},
  // props: ['hotData'],
  data(){
    return{
      innerCardStyle:{
        'border-radius': '8px',
        'border-width': '2px',
        'width': '55rem',
        'height': '',
        'overflow-y': 'auto'
      },
      innerKnowCardStyle:{
        'border-radius': '0px',
        'border-width': '2px',
        'width': '55rem',
        'height': '',
        'overflow-y': 'auto'
      },
      hotData: [],
      hotKnowData: [],
      tabIndex: 0
    }
  },
  computed: {
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    }),
    moduleName() {
      if(this.hotData.module_name === 'life')
        return 'LIFE CIRCLE'
      else
        return String(this.hotData.module_name).toUpperCase()
    }
  },
  methods:{
    getHeight(){
      this.innerCardStyle.height = (window.innerHeight-250).toString()+'px'
      this.innerKnowCardStyle.height = (window.innerHeight-295).toString()+'px'
    },
    getHotPosts() {
      axios
        .get('/homeposts')
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            this.hotData = response.data.data
            this.hotKnowData = [this.hotData[1],this.hotData[2],this.hotData[3]]
          }
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    linkClass(idx) {
      if (this.tabIndex === idx) {
        return ['bg-primary', 'text-dark']
      } else {
        return ['bg-light', 'text-dark']
      }
    }
  },
  created() {
    window.addEventListener("resize", this.getHeight)
    this.getHeight()
  },
  mounted() {
    this.getHotPosts()
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight)
  }
}
</script>

<style scoped>

</style>
