<template>
  <b-card no-body class="border-0">
    <template #header><h5>MESSAGE BOX</h5></template>
    <b-list-group>
      <b-list-group-item to="/messages=comment" :active="mType === 'comment'"><b>Comment</b></b-list-group-item>
<!--      <b-list-group-item to="/messages=repost" :active="mType === 'repost'"><b>Repost</b></b-list-group-item>-->
      <b-list-group-item to="/messages=like" :active="mType === 'like'"><b>Like</b></b-list-group-item>
<!--      <b-list-group-item to="/messages=message" :active="mType === 'message'"><b>Message</b></b-list-group-item>-->
    </b-list-group>
  </b-card>
</template>

<script>
export default {
  name: "messageSideBar",
  props: ['mType']
}
</script>

<style scoped>

</style>
