<template>
<!--  Navigation for all pages-->
  <div role="navigation">
    <b-navbar toggleable="lg" type="light" variant="light" fixed="top">

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
<!--        Menu-->
        <b-navbar-nav class="mx-auto">
<!--          Website Logo-->
          <b-navbar-brand href="/" tag="h1">
            <img src="./logo.svg" height="20" alt="logo">
          </b-navbar-brand>
<!--          Homepage Button-->
          <b-nav-item to="/">Home</b-nav-item>
<!--          New Posts Button-->
          <b-nav-item to="news">News</b-nav-item>
<!--          Modules Button-->
          <b-nav-dropdown text="Modules" lazy>
            <b-dropdown-item to="/life">Life Circle</b-dropdown-item>
            <b-dropdown-item to="/knowledge-modules">Knowledge Modules</b-dropdown-item>
          </b-nav-dropdown>
<!--          Message Button-->
          <b-nav-dropdown text="Messenger" lazy>
            <b-dropdown-item to="/comment">Comment</b-dropdown-item>
            <b-dropdown-item to="#">Repost</b-dropdown-item>
            <b-dropdown-item to="#">Like</b-dropdown-item>
            <b-dropdown-item to="#">Message</b-dropdown-item>
          </b-nav-dropdown>
        </b-navbar-nav>

        <b-navbar-nav class="mx-auto">
<!--          Login/Logout and Profile Button-->
          <b-nav-item id="navBar-user-popover" class="mr-3">
            <b-avatar button></b-avatar>
<!--            Show 'Visitor' when do not login yet-->
            <span v-show="!loginState">Visitor</span>
            <span v-show="loginState">{{ loginName }}</span>
          </b-nav-item>
          <b-popover
            target="navBar-user-popover"
            placement="bottom"
            triggers="hover"
          >
<!--            Login popover for visitor-->
            <b-button-group vertical v-show="!loginState">
              <b-button to="/login" variant="outline-secondary" style="text-align: left">
                <b-icon icon="person-check" aria-hidden="true" variant="primary"></b-icon>
                Login
              </b-button>
              <b-button to="/register" variant="outline-secondary" style="text-align: left">
                <b-icon icon="person-plus" aria-hidden="true" variant="success"></b-icon>
                Register
              </b-button>
            </b-button-group>
<!--            Login popover for user-->
            <b-button-group vertical v-show="loginState">
              <b-button :to="'/profile='+loginName" variant="outline-secondary" style="text-align: left">
                <b-icon icon="person" aria-hidden="true" variant="primary"></b-icon>
                Profile
              </b-button>
              <b-button @click="logout" variant="outline-secondary" style="text-align: left">
                <b-icon icon="power" aria-hidden="true" variant="danger"></b-icon>
                Logout
              </b-button>
            </b-button-group>
          </b-popover>
<!--          Search bar-->
          <b-nav-form @submit="searchAction">
            <b-form-select v-model="searchSelect" required>
              <b-form-select-option value="searchmodule">Module</b-form-select-option>
              <b-form-select-option value="searchpost">Post</b-form-select-option>
            </b-form-select>
            <b-form-input aria-label="Input"
                          class="mr-sm-2"
                          v-model="searchKey"
                          placeholder="Search..."
                          required></b-form-input>
            <b-button variant="outline-dark"
                      class="my-2 my-sm-0"
                      type="submit">
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
import axios from "axios";

export default {
  name: "stickyHeader",
  data() {
    return {
      searchSelect: 'searchmodule',
      searchKey: null,
      searchResult: []
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
      this.$router.replace('/home')
      this.$store.commit("loginInfo/userLogout")
    },
    searchAction() {
      if(this.searchSelect === 'searchmodule')
        this.$router.push('/module_search='+this.searchKey)
      else if(this.searchSelect === 'searchpost')
        this.$router.push('/post_search='+this.searchKey)
    }
  }
}
</script>

<style scoped>

</style>
