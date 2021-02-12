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

          <b-nav-item to="/">Home</b-nav-item>
          <b-nav-item to="news">News</b-nav-item>

          <b-nav-dropdown text="Modules" lazy>
            <b-dropdown-item to="/life-circle">Life Circle</b-dropdown-item>
            <b-dropdown-item to="/knowledge-modules">Knowledge Modules</b-dropdown-item>
          </b-nav-dropdown>

          <b-nav-dropdown text="Messenger" lazy>
            <b-dropdown-item to="/comment">Comment</b-dropdown-item>
            <b-dropdown-item to="#">Repost</b-dropdown-item>
            <b-dropdown-item to="#">Like</b-dropdown-item>
            <b-dropdown-item to="#">Message</b-dropdown-item>
          </b-nav-dropdown>
        </b-navbar-nav>

        <b-navbar-nav class="mx-auto">

          <b-nav-item id="navBar-user-popover" class="mr-3">
            <span v-show="!loginState">Visitor</span>
            <span v-show="loginState">{{ loginName }}</span>
          </b-nav-item>
          <b-popover
            target="navBar-user-popover"
            placement="bottom"
            triggers="hover"
          >
            <b-list-group style="text-align: center" v-show="!loginState">
              <b-list-group-item to="/login">Login</b-list-group-item>
              <b-list-group-item to="/register">Register</b-list-group-item>
            </b-list-group>
            <b-list-group style="text-align: center" v-show="loginState">
              <b-list-group-item @click="logout" button>Logout</b-list-group-item>
            </b-list-group>
          </b-popover>

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
import {mapGetters} from 'vuex'

export default {
  name: "stickyHeader",
  data() {
    return {
      accessMode:"/login"
    }
  },
  computed: {
    ...mapGetters({
      loginName: "loginInfo/getLUName",
      loginState: "loginInfo/getLoginState"
    })
  },
  methods: {
    logout(event){
      event.preventDefault()
      this.$store.commit("loginInfo/userLogout")
    }
  }
}
</script>

<style scoped>

</style>
