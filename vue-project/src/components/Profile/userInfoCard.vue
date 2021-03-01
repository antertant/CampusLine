<template>
  <b-card align="center" class="shadow" bg-variant="light" style="min-width: 13rem">
<!--    Avatar-->
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
<!--      Follower button-->
      <div class="mb-2">
        <b-button variant="info" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge>5</b-badge>
        </b-button>
      </div>
<!--      Follow button-->
      <div class="mb-2">
        <b-button variant="info" v-b-modal="'following-modal'" block>
          Following
          <b-badge>5</b-badge>
        </b-button>
      </div>
<!--      Collection button-->
      <div>
        <b-button variant="success" v-b-modal="'collection-modal'" block>
          Collection
          <b-badge>5</b-badge>
        </b-button>
      </div>
    </div>

<!--    Others profile-->
    <div v-if="!sameUser">
<!--      Follow button-->
      <div class="mb-2">
        <b-button variant="info" v-b-modal="'following-modal'" block>
          Follow
          <b-badge>5</b-badge>
        </b-button>
      </div>
<!--      Follower button-->
      <div>
        <b-button variant="info" v-b-modal="'follower-modal'" block>
          Follower
          <b-badge>5</b-badge>
        </b-button>
      </div>
    </div>

<!--    Modals-->
    <b-modal id="follower-modal"
             hide-backdrop
             content-class="shadow"
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Follower List</h5>
      </template>
      <follower-card @hideFollowerModal="hideFollowerModal"></follower-card>
    </b-modal>
    <b-modal id="following-modal"
             ref="following-modal"
             hide-backdrop
             content-class="shadow"
             hide-footer centered lazy>
      <template #modal-header>
        <h5>Following List</h5>
      </template>
      <following-card @hideFollowingModal="hideFollowingModal"></following-card>
    </b-modal>
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
      avColor: "primary"
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
  methods: {
    followUser() {
      // not followed, then follow
      if(this.followed === false){
        this.followed = true
        this.avColor = "success"
      }
      // followed, then cancel follow
      else{
        this.followed = false
        this.avColor = "primary"
      }
    },
    hideFollowerModal() {
      this.$nextTick(()=>{
        this.$bvModal.hide('follower-modal')
      })
      this.followed = false
      this.avColor = "primary"
    },
    hideFollowingModal() {
      this.$nextTick(()=>{
        this.$bvModal.hide('following-modal')
      })
      this.followed = false
      this.avColor = "primary"
    },
    hideCollectionModal() {
      this.$nextTick(()=>{
        this.$bvModal.hide('collection-modal')
      })
      this.followed = false
      this.avColor = "primary"
    }
  },
  mounted() {
    this.followed = false
    this.avColor = "primary"
  }
}
</script>

<style scoped>

</style>
