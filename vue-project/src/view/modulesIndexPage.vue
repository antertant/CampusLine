<template>
  <div style="margin-top: 6rem">
    <module-card></module-card>
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
  created() {
    document.title = 'UWSK - Modules List'
  }
}
</script>

<style scoped>

</style>
