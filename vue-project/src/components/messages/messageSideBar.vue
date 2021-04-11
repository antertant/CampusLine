<template>
  <b-card no-body class="border-0" header-bg-variant="white">
    <template #header><h5>MESSAGE BOX</h5></template>
    <b-list-group>
      <b-list-group-item variant="secondary"
                         id="mbCommentButton"
                         to="/messages=comment"
                         :active="mType === 'comment'">
        <b>Comment</b>
        <b-badge id="mbCommentBadge"
                 variant="danger"
                 v-if="commentCount!==0">
          {{ commentCount }}
        </b-badge>
      </b-list-group-item>

      <b-list-group-item variant="secondary"
                         id="mbLikeButton"
                         to="/messages=like"
                         :active="mType === 'like'">
        <b>Like</b>
        <b-badge id="mbLikeBadge"
                 variant="danger"
                 v-if="likeCount!==0">
          {{ likeCount }}
        </b-badge>
      </b-list-group-item>

      <b-list-group-item variant="secondary"
                         id="mbChatButton"
                         to="/messages=chat"
                         :active="mType === 'chat'">
        <b>Chat</b>
        <b-badge id="mbChatBadge"
                 variant="danger"
                 v-if="chatCount!==0">
          {{ chatCount }}
        </b-badge>
      </b-list-group-item>
    </b-list-group>
  </b-card>
</template>

<script>
export default {
  name: "messageSideBar",
  props: ['mType', 'commentCount', 'likeCount', 'chatCount']
}
</script>

<style scoped>

</style>
