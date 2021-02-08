<template>
  <!--Fixed nav bar-->
  <div role="navigation">
    <b-navbar toggleable="lg" type="light" variant="light" fixed="top">

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="mx-auto">
          <b-navbar-brand href="/" tag="h1">
            <img src="./logo.svg" height="20" alt="logo">
          </b-navbar-brand>

          <b-nav-item href="/">Home</b-nav-item>
          <b-nav-item href="news">News</b-nav-item>

          <b-nav-dropdown text="Modules" lazy>
            <b-dropdown-item href="#">Life Circle</b-dropdown-item>
            <b-dropdown-item href="#">Knowledge Boxes</b-dropdown-item>
          </b-nav-dropdown>

          <b-nav-dropdown text="Messenger" lazy>
            <b-dropdown-item href="#">Comment</b-dropdown-item>
            <b-dropdown-item href="#">Repost</b-dropdown-item>
            <b-dropdown-item href="#">Like</b-dropdown-item>
            <b-dropdown-item href="#">Message</b-dropdown-item>
          </b-nav-dropdown>

          <b-nav-item :href="accessMode">Me</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="mx-auto">
          <b-nav-form>
            <b-form-input aria-label="Input" class="mr-sm-2" placeholder="Search..."></b-form-input>
            <b-button variant="outline-dark" class="my-2 my-sm-0" type="submit">
              <img src="./search.svg" height="14" alt="search">
            </b-button>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "stickyHeader",
  data() {
    return {
      accessMode:"/login"
    }
  }
}
</script>

<style scoped>

</style>
