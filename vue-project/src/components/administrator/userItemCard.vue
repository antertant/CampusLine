<template>
  <b-card style="max-width: 65rem; align-items: center"
          class="mx-auto border-0">
    <user-item-title class="mb-2" />
    <user-item v-for="singeUserInfo in userInfo"
               :user-info="singeUserInfo"
               :key="singeUserInfo.username" />
  </b-card>
</template>

<script>
import UserItemTitle from "@/components/administrator/userItemTitle";
import UserItem from "@/components/administrator/userItem";
export default {
name: "userItemCard",
  components: {UserItem, UserItemTitle},
  props: ['userInfo']
}
</script>

<style scoped>

</style>
