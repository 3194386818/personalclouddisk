<template>
  <p v-for="item in count">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad animi ea, eos, facere illo neque
    nulla officiis pariatur quaerat quibusdam rem rerum suscipit, tempore unde ut vero voluptate voluptatem
    voluptatibus?</p>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  count: {
    type: Number,
    default: 10
  }
})





</script>

<style scoped>
p {
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
