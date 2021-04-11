<template>
  <div style="padding-top: 6rem; padding-bottom: 6rem">
    <module-card></module-card>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import ModuleCard from "@/components/module/moduleCard";
export default {
  name: "modulesPage",
  components: {ModuleCard},
  created() {
    document.title = 'CampusLine - Modules List'
  }
}
</script>

<style scoped>

</style>
