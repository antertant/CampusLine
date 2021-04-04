<template>
 <b-card class="mx-auto shadow"
         no-body>
  <b-card-header class="border-0" header-bg-variant="white" style="text-align: center; font-size: xx-large">
    <h2>
      <b-icon icon="thermometer-half"/>
      <span>Current Hot Posts</span>
    </h2>
  </b-card-header>
   <b-row>
     <b-col v-for="singleModule in hotData"
            :key="singleModule.module_name"
            style="padding: 0"
            cols="4">
       <b-card :style=innerCardStyle class="mx-4 mb-3" no-body>
         <!--       Enter the module-->
         <b-button variant="outline-secondary"
                   v-if="singleModule.module_name === 'life'"
                   :to="'/'+singleModule.module_name"
                   style="border-radius: 0"
                   class="border-right-0 border-left-0 border-top-0">
           <h3>
             <b-icon icon="text-right" variant="dark" />
             <em style="color: black">{{ String(singleModule.module_name).toUpperCase() }}</em>
           </h3>
         </b-button>
         <b-button variant="outline-secondary"
                   v-else
                   :to="'/knowledge-modules/'+singleModule.module_name"
                   style="border-radius: 0"
                   class="border-right-0 border-left-0 border-top-0">
           <h3>
             <b-icon icon="text-right" variant="dark" />
             <em style="color: black">{{ String(singleModule.module_name).toUpperCase() }}</em>
           </h3>
         </b-button>
         <!--       module post list-->
         <post-card v-for="singlePost in singleModule.posts"
                    :post-content="singlePost"
                    :key="singlePost.post_id"
                    :is-hot="true"></post-card>
       </b-card>
     </b-col>
   </b-row>
<!--   <div v-for="singleModulePosts in hotPosts">-->
<!--     <post-card :post-content="singleModulePosts"></post-card>-->
<!--   </div>-->
 </b-card>
</template>

<script>
import PostCard from "@/components/post/postCard";
export default {
  name: "hotCard",
  components: {PostCard},
  props: ['hotData'],
  data(){
    return{
      innerCardStyle:{
        'border-radius': '8px',
        'border-width': '2px',
        'box-shadow': '2px 2px 5px grey',
        'height': '',
        'overflow-y': 'auto',
      }
    }
  },
  computed: {
    moduleName() {
      if(this.hotData.module_name === 'life')
        return 'LIFE CIRCLE'
      else
        return String(this.hotData.module_name).toUpperCase()
    }
  },
  methods:{
    getHeight(){
      this.innerCardStyle.height = (window.innerHeight-180).toString()+'px'
    }
  },
  created() {
    window.addEventListener("resize", this.getHeight)
    this.getHeight()
  },
  destroyed() {
    window.removeEventListener("resize", this.getHeight)
  }
}
</script>

<style scoped>

</style>
