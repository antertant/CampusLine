<template>
  <div id="app">
    <sticky-header/>
    <router-view/>
    <sticky-footer/>
  </div>
</template>

<script>
import stickyHeader from "./components/header/stickyHeader";
import StickyFooter from "@/components/common/stickyFooter";
export default {
  name: 'App',
  components: {
    StickyFooter,
    stickyHeader
  }
}
</script>

<style>
#app {
  background: #efe8e0;
}
</style>
