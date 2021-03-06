<template>
  <div>
    <b-row align-h="center">
<!--      hot life-->
      <b-col cols="auto">
        <b-card header-bg-variant="info"
                header-text-variant="light"
                header-class="p-2"
                class="overflow-auto shadow"
                style="max-height: 46rem;max-width: 40rem; min-width: 36rem">
          <template #header>
            <h5>LIFE</h5>
          </template>
          <!--       Enter the module-->
          <b-button block :to="'/'+hotLife.module_name"
                    variant="primary" class="shadow-sm">
            Enter the <{{ hotLife.module_name.toUpperCase() }}> Module
          </b-button>
          <post-card v-for="singleHotLife in hotLife.posts"
                     :post-content="singleHotLife"
                     :key="singleHotLife.post_id"></post-card>
        </b-card>
      </b-col>

<!--      hot module 1-->
      <b-col cols="auto">
        <b-card class="overflow-auto shadow"
                header-bg-variant="info"
                header-text-variant="light"
                style="max-height: 46rem;max-width: 40rem; min-width: 36rem">
          <template  #header>
            <h5>{{ hotModule1.module_name.toUpperCase() }}</h5>
          </template>
          <!--       Enter the module-->
          <b-button block :to="'/knowledge-modules/'+hotModule1.module_name"
                    variant="primary" class="shadow-sm">
            Enter the <{{ hotModule1.module_name.toUpperCase() }}> Module
          </b-button>
          <post-card v-for="singleHotModule1 in hotModule1.posts"
                     :post-content="singleHotModule1"
                     :key="singleHotModule1.post_id"></post-card>
        </b-card>
      </b-col>

    </b-row>

    <b-row align-h="center" class="mt-3">
      <!--      hot module 2-->
      <b-col cols="auto">
        <b-card class="overflow-auto shadow"
                header-bg-variant="info"
                header-text-variant="light"
                style="max-height: 46rem;max-width: 40rem; min-width: 36rem">
          <template  #header>
            <h5>{{ hotModule2.module_name.toUpperCase() }}</h5>
          </template>
          <!--       Enter the module-->
          <b-button block :to="'/knowledge-modules/'+hotModule2.module_name"
                    variant="primary" class="shadow-sm">
            Enter the <{{ hotModule2.module_name.toUpperCase() }}> Module
          </b-button>
          <post-card v-for="singleHotModule2 in hotModule2.posts"
                     :post-content="singleHotModule2"
                     :key="singleHotModule2.post_id"></post-card>
        </b-card>
      </b-col>

      <!--      hot module 3-->
      <b-col cols="auto">
        <b-card class="overflow-auto shadow"
                header-bg-variant="info"
                header-text-variant="light"
                style="max-height: 46rem;max-width: 40rem; min-width: 36rem">
          <template  #header>
            <h5>{{ hotModule3.module_name.toUpperCase() }}</h5>
          </template>
          <!--       Enter the module-->
          <b-button block :to="'/knowledge-modules/'+hotModule3.module_name"
                    variant="primary" class="shadow-sm">
            Enter the <{{ hotModule3.module_name.toUpperCase() }}> Module
          </b-button>
          <post-card v-for="singleHotModule3 in hotModule3.posts"
                     :post-content="singleHotModule3"
                     :key="singleHotModule3.post_id"></post-card>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import PostCard from "@/components/post/postCard";

export default {
  name: "hotModulesCard",
  props: ['hotPostList'],
  components: {PostCard},
  data() {
    return {
      hotLife: this.hotPostList[0],
      hotModule1: this.hotPostList[1],
      hotModule2: this.hotPostList[2],
      hotModule3: this.hotPostList[3]
    }
  }
}
</script>

<style scoped>

</style>
