<template>
  <b-row align-h="center">
    <b-col>
      <b-jumbotron
        v-for="singleModule in multiModules"
        :key="singleModule.module_name"
        :header="singleModule.module_name.toUpperCase()"
        :lead="singleModule.module_intro"
        :id="singleModule.module_name+'_block'"
        border-variant="secondary"
        style="max-width: 40rem"
        class="mx-auto"
        bg-variant="dark"
        text-variant="white">
        <b-button
          variant="warning"
          :id="singleModule.module_name+'_button'"
          :to="'knowledge-modules/'+singleModule.module_name">
          ENTER
        </b-button>
      </b-jumbotron>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "moduleList",
  props: ['multiModules']
}
</script>

<style scoped>

</style>
