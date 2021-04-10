<template>
<!--  Navigation for all pages-->
  <div role="navigation">
    <b-navbar style="max-width: 120rem;"
              class="mx-auto fixed-top"
              toggleable="lg"
              type="light"
              variant="light">

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
<!--        Menu-->
        <b-navbar-nav class="mx-auto">
<!--          Website Logo-->
          <b-navbar-brand href="/">
            <em>
              <b>
                CampusLine
              </b>
            </em>
            <b-img src="https://upload.wikimedia.org/wikipedia/en/thumb/6/6e/University_of_Waterloo_seal.svg/1200px-University_of_Waterloo_seal.svg.png"
                   alt="University of Waterloo seal.svg"
                   width="36"
                   height="36" class="d-inline-block align-top"/>
          </b-navbar-brand>
<!--          Homepage Button-->
          <b-nav-item to="/" id="headerHome">
            <b-icon variant="warning" icon="house"></b-icon>
            <b>
              Home
            </b>
          </b-nav-item>
<!--          New Posts Button-->
          <b-nav-item to="/news" v-if="loginState" id="SignInNews">
            <b-icon variant="warning" icon="newspaper"></b-icon>
            <b>
              News
            </b>
          </b-nav-item>
          <b-nav-item to="/login" v-if="!loginState" id="VisitorNews">
            <b-icon variant="warning" icon="newspaper"></b-icon>
            <b>
              News
            </b>
          </b-nav-item>
<!--          Modules Button-->
          <b-nav-item id="modules-popover">
            <b-icon variant="warning" icon="box-seam"></b-icon>
            <b>
              Modules
            </b>
          </b-nav-item>
          <b-popover target="modules-popover" placement="buttom" triggers="focus">
            <b-list-group style="font-size: medium; color: black">
              <b-list-group-item to="/life" class="border-0 py-2" id="modulePopLife">
                Life
              </b-list-group-item>
              <b-list-group-item to="/knowledge-modules" class="border-0 py-2" id="modulePopKnow">
                Knowledge
              </b-list-group-item>
            </b-list-group>
          </b-popover>
<!--          Message Button-->
          <b-nav-item id="message-popover">
            <b-icon variant="warning" icon="bell"></b-icon>
            <b>
              Notifications
            </b>
            <b-badge variant="danger" id="message-badge"
                     v-if="newsCounter.cnewpostcomment+newsCounter.cnewcommentreply
            +newsCounter.cnewlpost+newsCounter.cnewlcomment+newChatCounter!==0 && loginState">
              {{ newsCounter.cnewpostcomment+newsCounter.cnewcommentreply
            +newsCounter.cnewlpost+newsCounter.cnewlcomment+newChatCounter }}
            </b-badge>
          </b-nav-item>
          <b-popover target="message-popover" placement="buttom" triggers="focus">
            <b-list-group style="font-size: medium; color: black">

              <b-list-group-item id="message-comment-popover"
                                 to="/messages=comment"
                                 class="border-0 py-2">
                Comment
                <b-badge id="message-comment-badge"
                         variant="danger"
                         v-if="newsCounter.cnewpostcomment+newsCounter.cnewcommentreply!==0 && loginState">
                {{newsCounter.cnewpostcomment+newsCounter.cnewcommentreply}}
                </b-badge>
              </b-list-group-item>

              <b-list-group-item id="message-like-popover"
                                 to="/messages=like"
                                 class="border-0 py-2">
                Like
                <b-badge id="message-like-badge"
                         variant="danger"
                         v-if="newsCounter.cnewlpost+newsCounter.cnewlcomment!==0 && loginState">
                  {{newsCounter.cnewlpost+newsCounter.cnewlcomment}}
                </b-badge>
              </b-list-group-item>

              <b-list-group-item id="message-chat-popover"
                                 to="/messages=chat"
                                 class="border-0 py-2">
                Chat
                <b-badge id="message-chat-badge"
                         variant="danger"
                         v-if="newChatCounter!==0 && loginState">
                  {{newChatCounter}}
                </b-badge>
              </b-list-group-item>

            </b-list-group>
          </b-popover>
        </b-navbar-nav>

        <b-navbar-nav class="mx-auto">
<!--          Login/Logout and Profile Button-->
          <b-nav-item id="navBar-user-popover" class="mr-3">
            <b-avatar variant="warning" button></b-avatar>
<!--            Show 'Visitor' when do not login yet-->
            <span v-if="!loginState"><b>Visitor</b></span>
            <span v-if="loginState"><b>{{ loginName }}</b></span>
          </b-nav-item>
          <b-popover
            target="navBar-user-popover"
            placement="bottom"
            triggers="focus"
          >
<!--            Login popover for visitor-->
            <b-button-group vertical v-show="!loginState">
              <b-button class="mb-1" id="loginButton" to="/login" variant="dark" style="text-align: left">
                <b-icon icon="person-check" aria-hidden="true" variant="warning"></b-icon>
                Login
              </b-button>
              <b-button id="registerButton" to="/register" variant="dark" style="text-align: left">
                <b-icon icon="person-plus" aria-hidden="true" variant="danger"></b-icon>
                Register
              </b-button>
            </b-button-group>
<!--            Login popover for user-->
            <b-button-group vertical v-show="loginState">
              <b-button id="navBar-user-profile" class="mb-1" :to="'/profile='+loginName" variant="dark" style="text-align: left">
                <b-icon icon="person" aria-hidden="true" variant="warning"></b-icon>
                Profile
              </b-button>
              <b-button id="navBar-user-logout" @click="logout" variant="dark" style="text-align: left">
                <b-icon icon="power" aria-hidden="true" variant="danger"></b-icon>
                Logout
              </b-button>
            </b-button-group>
          </b-popover>
<!--          Search bar-->
          <b-nav-form @submit="searchAction">
            <b-form-input id="searchInput"
                          v-model="searchKey"
                          placeholder="Search..."
                          required></b-form-input>

            <b-form-select id="searchSelect" v-model="searchSelect" class="mx-1" required>
              <b-form-select-option id="selectModule" value="searchmodule">Module</b-form-select-option>
              <b-form-select-option id="selectPost" value="searchpost">Post</b-form-select-option>
            </b-form-select>

            <b-button variant="white"
                      id="searchButton"
                      class="my-2 my-sm-0"
                      type="submit">
              <b-icon icon="search" variant="dark" />
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
      newsCounter: "newMessage/getNewMessageCount",
      newChatCounter: 'newMessage/getNewChatMessageCount'
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
  },
  mounted() {
    if(this.loginState){
      this.$store.dispatch("newMessage/getNewMessageCountFS", this.loginName)
      this.$store.dispatch('newMessage/getNewChatMessageCountFS', this.loginName)
      // update message notifications every 5 minutes
      this.interval = setInterval(()=>{
        this.$store.dispatch("newMessage/getNewMessageCountFS", this.loginName)
        this.$store.dispatch('newMessage/getNewChatMessageCountFS', this.loginName)
      }, 1000*60*5)
    }
    else{
      this.newsCounter.newsCounter = 0
      this.newsCounter.cnewlcomment = 0
      this.newsCounter.cnewpostcomment = 0
      this.newsCounter.cnewcommentreply = 0
    }
  },
  destroyed() {
    if(this.loginName !== '')
      this.clearInterval(this.interval)
  }
}
</script>

<style scoped>

</style>
