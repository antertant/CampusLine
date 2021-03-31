<template>
  <admin-tabs/>
</template>

<script>
import userItem from '@/components/administrator/userItem'
import userItemTitle from "@/components/administrator/userItemTitle";
import UserItemCard from "@/components/administrator/userItemCard";
import AdminTabs from "@/components/administrator/adminTabs";
export default {
  name: "administratorPage",
  components: {AdminTabs, UserItemCard, userItemTitle, userItem},
}
</script>

<style scoped>

</style>
