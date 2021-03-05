<template>
  <b-card align="center" class="shadow" bg-variant="light" style="min-width: 13rem">
<!--    Avatar-->
<!--    follow user-->
    <b-avatar :badge-variant="avColor"
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
        <b-button variant="info" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge>{{followerCounter}}</b-badge>
        </b-button>
      </div>
<!--      Follow list button-->
      <div class="mb-2">
        <b-button variant="info" v-b-modal="'following-modal'" block>
          Following
          <b-badge>{{followingCounter}}</b-badge>
        </b-button>
      </div>
<!--      Collection button-->
      <div>
        <b-button variant="success" v-b-modal="'collection-modal'" block>
          Collection
          <b-badge>{{collectCounter}}</b-badge>
        </b-button>
      </div>
    </div>

<!--    Others profile-->
    <div v-if="!sameUser">
<!--      Follower list button-->
      <div class="mb-2">
        <b-button variant="info" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge>{{followerCounter}}</b-badge>
        </b-button>
      </div>
<!--      Follow list button-->
      <div>
        <b-button variant="info" v-b-modal="'following-modal'" block>
          Following
          <b-badge>{{followingCounter}}</b-badge>
        </b-button>
      </div>
    </div>

<!--    Modals-->
<!--    Follower List-->
    <b-modal id="follower-modal"
             hide-backdrop
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
             hide-backdrop
             content-class="shadow"
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Following List</h5>
      </template>
      <following-card :profile-user="profileUser" @hideFollowingModal="hideModal('following-modal')"></following-card>
    </b-modal>
<!--    Collection List-->
    <b-modal id="collection-modal"
             hide-backdrop
             content-class="shadow"
             size="lg"
             scrollable
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Collection List</h5>
      </template>
      <collection-card :profile-user="profileUser"></collection-card>
    </b-modal>
  </b-card>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";
import FollowerCard from "@/components/Profile/followerCard";
import FollowingCard from "@/components/Profile/followingCard";
import CollectionCard from "@/components/Profile/collectionCard";

export default {
  name: "userInfoCard",
  components: {CollectionCard, FollowingCard, FollowerCard},
  props: ['profileUser'],
  data() {
    return{
      followed: false,
      avColor: "primary",
      followerCounter: 0,
      followingCounter: 0,
      collectCounter: 0
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
              this.avColor = "success"
            }
            else {
              this.followed = false
              this.avColor = "primary"
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
              this.avColor = "success"
            }
            if(response.data.data === "cancel follow successfully"){
              this.followed = false
              this.avColor = "primary"
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
    // hideCollectionModal() {
    //   this.$nextTick(()=>{
    //     this.$bvModal.hide('collection-modal')
    //   })
    //   this.followed = false
    //   this.avColor = "primary"
    // }
  },
  mounted() {
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
