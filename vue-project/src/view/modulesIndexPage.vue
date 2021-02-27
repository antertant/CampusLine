<template>
  <div>
    <div v-for="list in modulesList">
      <b-jumbotron
      :header="list.module_name.toUpperCase()"
      :lead="list.module_intro"
      border-variant="secondary"
      style="max-width: 50rem"
      class="mx-auto" bg-variant="info" text-variant="white">
        <b-button
        variant="secondary"
        :to="'knowledge-modules/'+list.module_name">
          JOIN
        </b-button>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  name: "modulesPage",
  data(){
    return {
    }
  },
  computed: {
    ...mapGetters({
      modulesList: 'moduleList/getModuleList'
    })
  },
  mounted() {
    this.$store.dispatch('moduleList/getModulefromServer')
  }
}
</script>

<style scoped>

</style>
