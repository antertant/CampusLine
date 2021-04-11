<template>
  <b-card body-bg-variant="light"
          body-text-variant="dark"
          align="center"
          class="shadow border-white"
          style="min-width: 13rem; border-radius: 6px">
<!--    Avatar-->
<!--    follow user-->
    <b-avatar :badge-variant="avColor"
              id="profile-avatar"
              button
              @click="followUser"
              v-if="!sameUser">
      <template v-if="!sameUser" #badge>
        <b-icon icon="plus" v-if="!followed"></b-icon>
        <b-icon icon="check" v-if="followed"></b-icon>
      </template>
    </b-avatar>
    <b-avatar v-if="sameUser"></b-avatar>

<!--    User name-->
    <h5 class="mt-2">{{ profileUser }}</h5>

<!--    Own profile-->
    <div v-if="sameUser">
<!--      Follower list button-->
      <div class="mb-2">
        <b-button id="profile-follower-button" variant="warning" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge>{{followerCounter}}</b-badge>
        </b-button>
      </div>
<!--      Follow list button-->
      <div class="mb-2">
        <b-button id="profile-following-button" variant="warning" v-b-modal="'following-modal'" block>
          Following
          <b-badge>{{followingCounter}}</b-badge>
        </b-button>
      </div>
<!--      Collection button-->
      <div>
        <b-button id="profile-collection-button" variant="danger" v-b-modal="'collection-modal'" block>
          Collection
          <b-badge>{{collectCounter}}</b-badge>
        </b-button>
      </div>
<!--      Change password-->
      <div>
        <a :href="'change_password='+currentUser">Change Password</a>
      </div>
    </div>

<!--    Others profile-->
    <div v-if="!sameUser">
<!--      Follower list button-->
      <div class="mb-2">
        <b-button variant="dark" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge variant="warning">{{followerCounter}}</b-badge>
        </b-button>
      </div>
<!--      Follow list button-->
      <div class="mb-2">
        <b-button variant="dark" v-b-modal="'following-modal'" block>
          Following
          <b-badge variant="warning">{{followingCounter}}</b-badge>
        </b-button>
      </div>
<!--      Send message button-->
      <div>
        <b-button variant="dark" @click="showChatModal()" block>
          Chat
        </b-button>
      </div>
    </div>

<!--    Modals-->
<!--    Follower List-->
    <b-modal id="follower-modal"

             content-class="shadow"
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Follower List</h5>
      </template>
      <follower-card :profile-user="profileUser" @hideFollowerModal="hideModal('follower-modal')"></follower-card>
    </b-modal>
<!--    Following List-->
    <b-modal id="following-modal"
             ref="following-modal"

             content-class="shadow"
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Following List</h5>
      </template>
      <following-card :profile-user="profileUser" @hideFollowingModal="hideModal('following-modal')"></following-card>
    </b-modal>
<!--    Collection List-->
    <b-modal id="collection-modal"

             content-class="shadow"
             size="lg"
             scrollable
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Collection List</h5>
      </template>
      <collection-card :profile-user="profileUser"></collection-card>
    </b-modal>
<!--    Chat windows-->
    <chat-item-modal :from-user="profileUser"
                     :to-user="currentUser"
                     :user-name="currentUser"
                     v-if="chatModalFlag" />
  </b-card>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";
import FollowerCard from "@/components/Profile/followerCard";
import FollowingCard from "@/components/Profile/followingCard";
import CollectionCard from "@/components/Profile/collectionCard";
import UpdatePasswordCard from "@/components/manage/updatePasswordCard";
import ChatItemModal from "@/components/messages/chatItemModal";

export default {
  name: "userInfoCard",
  components: {ChatItemModal, UpdatePasswordCard, CollectionCard, FollowingCard, FollowerCard},
  props: ['profileUser'],
  data() {
    return{
      followed: false,
      avColor: "warning",
      followerCounter: 0,
      followingCounter: 0,
      collectCounter: 0,
      chatModalFlag: false
    }
  },
  computed: {
    ...mapGetters({
      currentUser: "loginInfo/getLUName"
    }),
    sameUser: function (){
      return this.profileUser === this.currentUser
    }
  },
  watch: {
    profileUser() {
      this.getFollowingCount()
      this.getFollowerCount()
      this.getCollectCount()
      this.isFollowed()
    }
  },
  methods: {
    isFollowed() {
      axios
        .get('/isfollowed', {params:{
            username: this.currentUser,
            follower: this.profileUser
          }})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            if(response.data.data === 1){
              this.followed = true
              this.avColor = "danger"
            }
            else {
              this.followed = false
              this.avColor = "warning"
            }
        })
    },
    followUser() {
      axios
        .post('/follow', null, {params:{
          follower: this.currentUser,
          username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200){
            if(response.data.data === "follow successfully"){
              this.followed = true
              this.avColor = "danger"
            }
            if(response.data.data === "cancel follow successfully"){
              this.followed = false
              this.avColor = "warning"
            }
          }
        })
        .catch(failResponse=>{
          console.log = failResponse
        })
    },
    getFollowingCount() {
      axios
        .get('/countfollow', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.followingCounter = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getFollowerCount() {
      axios
        .get('/countfollower', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.followerCounter = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    getCollectCount() {
      axios
        .get('/countcollects', {params:{username: this.profileUser}})
        .then(response=>{
          console.log(response)
          if(response.data.code === 200)
            this.collectCounter = response.data.data
        })
        .catch(failResponse=>{
          console.log(failResponse)
        })
    },
    hideModal(ref) {
      this.$nextTick(()=>{
        this.$bvModal.hide(ref)
      })
      this.followed = false
      this.avColor = "primary"
    },
    showChatModal() {
      this.chatModalFlag = true
      this.$nextTick(()=>{
        this.$bvModal.show('chat-messenger'+this.profileUser)
      })
    }
  },
  mounted() {
    this.$root.$on('bv::modal::hide', (bvEvent, modalId) => {
      this.chatModalFlag = false
    })
    this.followed = false
    this.avColor = "primary"
    this.getFollowingCount()
    this.getFollowerCount()
    this.getCollectCount()
    this.isFollowed()
  }
}
</script>

<style scoped>

</style>
