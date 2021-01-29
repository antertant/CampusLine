<template>
  <div>
    <blog-header></blog-header>
    <hr>
    <div>This is homepage</div>
    <hr>
    <blog-footer></blog-footer>
  </div>
</template>

<script>
import BlogHeader from "../common/BlogHeader";
import BlogFooter from "../common/BlogFooter";
export default {
  name: "BlogIndex",
  components: {BlogFooter, BlogHeader}
}
</script>

<style scoped>

</style>
