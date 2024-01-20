<template>
  <header>
    <Navbar/>
  </header>
  <div class="container">
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts">
import Navbar from './components/Navbar.vue'
import Main from '@/components/Main.vue'
</script>


<style scoped>


</style>
