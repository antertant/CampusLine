<template>
  <div id="app">
    <sticky-header></sticky-header>
    <router-view/>
  </div>
</template>

<script>
import stickyHeader from "./components/header/stickyHeader";
export default {
  name: 'App',
  components: {
    stickyHeader
  }
}
</script>

<style>
#app {
  margin-top: 4rem;
}
</style>
