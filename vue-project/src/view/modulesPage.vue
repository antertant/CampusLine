<template>
  <div>
    <div v-for="list in modulesList">
      <b-jumbotron
      :header="list.module_name"
      :lead="list.module_info"
      border-variant="secondary"
      style="max-width: 60rem"
      class="mx-auto">
<!--        {{list.module_info}}-->
        <b-button variant="primary" :to="'knowledge-modules/'+list.module_name">Join</b-button>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import axios from "axios";
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
