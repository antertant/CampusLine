<template>
  <div>
    <b-jumbotron
      v-for="singleModule in multiModules"
      :key="singleModule.module_name"
      :header="singleModule.module_name.toUpperCase()"
      :lead="singleModule.module_intro"
      border-variant="secondary"
      style="max-width: 50rem"
      class="mx-auto"
      bg-variant="info"
      text-variant="white">
      <b-button
        variant="secondary"
        :to="'knowledge-modules/'+singleModule.module_name">
        ENTER
      </b-button>
    </b-jumbotron>
  </div>
</template>

<script>
export default {
  name: "moduleCard",
  props: ['multiModules']
}
</script>

<style scoped>

</style>
