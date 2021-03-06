<template>
  <div style="margin-top: 6rem">
    <module-card :multi-modules="modulesList"></module-card>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import ModuleCard from "@/components/module/moduleCard";
export default {
  name: "modulesPage",
  components: {ModuleCard},
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
