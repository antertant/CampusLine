<template>
  <b-button variant="warning"
            v-if="modName === 'life'"
            :to="'/life'"
            id="lifeEnterButton"
            style="border-radius: 8px; box-shadow: 0px 0px 8px #4a4a4a"
            class="border-right-0 border-left-0 border-top-0"
            block>
    <h3>
      <b-icon icon="text-right" variant="dark" />
      <em style="color: black">{{ String(modName).toUpperCase() }}</em>
    </h3>
  </b-button>
  <b-button variant="warning"
            v-else
            :to="'/knowledge-modules/'"
            id="knowledgeEnterButton"
            style="border-radius: 8px; box-shadow: 0px 0px 8px #4a4a4a"
            class="border-right-0 border-left-0 border-top-0"
            block>
    <h3>
      <b-icon icon="text-right" variant="dark" />
      <em style="color: black">{{ String(modName).toUpperCase() }}</em>
    </h3>
  </b-button>
</template>

<script>
export default {
  name: "hotCardTitle",
  props:['modName']
}
</script>

<style scoped>

</style>
