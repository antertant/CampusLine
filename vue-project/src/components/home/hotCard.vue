<template>
 <b-card style="max-width: 46rem"
         class="mx-auto shadow"
         no-body>
  <b-card-header style="text-align: center; font-size: xx-large">
    <b>Current Hot Posts</b>
  </b-card-header>
   <b-tabs card>
     <b-tab v-for="singleModule in hotData"
            :id="'tab_'+singleModule.module_name"
            :key="singleModule.module_name"
            lazy>
       <template #title>
         <b-icon icon="tag" variant="info"></b-icon>
         <span style="color: black"><b>{{ String(singleModule.module_name).toUpperCase() }}</b></span>
       </template>
       <!--       Enter the module-->
       <b-button block :to="'/'+singleModule.module_name"
                 id="lifeEnterButton"
                 variant="primary"
                 v-if="singleModule.module_name === 'life'">
         Enter the <{{ String(singleModule.module_name).toUpperCase() }}> Module
       </b-button>
       <b-button block :to="'/knowledge-modules/'+singleModule.module_name"
                 :id="singleModule.module_name+'EnterButton'"
                 variant="primary"
                 v-else>
         Enter the <{{ String(singleModule.module_name).toUpperCase() }}> Module
       </b-button>
<!--       module post list-->
       <post-card v-for="singlePost in singleModule.posts"
                  :post-content="singlePost"
                  :key="singlePost.post_id"></post-card>
     </b-tab>
   </b-tabs>
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
  computed: {
    moduleName() {
      if(this.hotData.module_name === 'life')
        return 'LIFE CIRCLE'
      else
        return String(this.hotData.module_name).toUpperCase()
    },
    hotPosts() {
      return this.hotData.posts
    }
  }
}
</script>

<style scoped>

</style>
