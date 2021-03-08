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
          <b-nav-item to="/">
            <b-icon icon="house"></b-icon>
            Home
          </b-nav-item>
<!--          New Posts Button-->
          <b-nav-item to="news" v-if="loginName!==''">
            <b-icon icon="newspaper"></b-icon>
            News
          </b-nav-item>
          <b-nav-item to="/login" v-if="loginName===''">
            <b-icon icon="newspaper"></b-icon>
            News
          </b-nav-item>
<!--          Modules Button-->
          <b-nav-item id="modules-popover">
            <b-icon icon="box-seam"></b-icon> Modules
          </b-nav-item>
          <b-popover target="modules-popover" placement="buttom" triggers="focus">
            <b-list-group style="font-size: medium; color: black">
              <b-list-group-item to="/life" class="border-0 py-2">Life</b-list-group-item>
              <b-list-group-item to="/knowledge-modules" class="border-0 py-2">Knowledge</b-list-group-item>
            </b-list-group>
          </b-popover>
<!--          Message Button-->
          <b-nav-item id="message-popover">
            <b-icon icon="bell"></b-icon> Messages
            <b-badge variant="danger"
                     v-if="newsCounter.cnewpostcomment+newsCounter.cnewcommentreply
            +newsCounter.cnewlpost+newsCounter.cnewlcomment!==0">
              {{ newsCounter.cnewpostcomment+newsCounter.cnewcommentreply
            +newsCounter.cnewlpost+newsCounter.cnewlcomment }}
            </b-badge>
          </b-nav-item>
          <b-popover target="message-popover" placement="buttom" triggers="focus">
            <b-list-group style="font-size: medium; color: black">
              <b-list-group-item to="/messages=comment" class="border-0 py-2">
                Comment
                <b-badge variant="danger" v-if="newsCounter.cnewpostcomment+newsCounter.cnewcommentreply!==0">
                {{newsCounter.cnewpostcomment+newsCounter.cnewcommentreply}}
                </b-badge>
              </b-list-group-item>
<!--              <b-list-group-item to="/messages=repost" class="border-0 py-2">Repost</b-list-group-item>-->
              <b-list-group-item to="/messages=like" class="border-0 py-2">
                Like
                <b-badge variant="danger" v-if="newsCounter.cnewlpost+newsCounter.cnewlcomment!==0">
                  {{newsCounter.cnewlpost+newsCounter.cnewlcomment}}
                </b-badge>
              </b-list-group-item>
<!--              <b-list-group-item to="/messages=message" class="border-0 py-2">Message</b-list-group-item>-->
            </b-list-group>
          </b-popover>
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
            triggers="focus"
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
            <b-form-input aria-label="Input"
                          v-model="searchKey"
                          placeholder="Search..."
                          required></b-form-input>

            <b-form-select v-model="searchSelect" class="mr-2" required>
              <b-form-select-option value="searchmodule">Module</b-form-select-option>
              <b-form-select-option value="searchpost">Post</b-form-select-option>
            </b-form-select>

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
      searchResult: [],
      counter: 0,
      interval: ''
    }
  },
  computed: {
    ...mapGetters({
      loginName: "loginInfo/getLUName",
      loginState: "loginInfo/getLoginState",
      newsCounter: "newMessage/getNewMessageCount"
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
  },
  created() {
    this.newsCounter.newsCounter = 0
    this.newsCounter.cnewlcomment = 0
    this.newsCounter.cnewpostcomment = 0
    this.newsCounter.cnewcommentreply = 0
    this.$store.dispatch("newMessage/getNewMessageCountFS", this.loginName)
  },
  mounted() {
    // update message notifications every 5 minutes
    this.interval = setInterval(()=>{
      this.$store.dispatch("newMessage/getNewMessageCountFS", this.loginName)
    }, 1000*60*5)
  },
  destroyed() {
    this.clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
