<template>
  <el-container direction="vertical">
    <el-header class="header">
      <Navbar/>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>


<!--  <Test1/>-->
</template>

<script setup lang="ts">
import Navbar from './components/Navbar.vue'</script>

<style scoped>
.header {
  background-color: var(--el-color-primary-light-7);
  position: fixed;
  width: 100%;
  z-index: 100;
  margin-bottom: 0.5rem;
}

.el-main {
  margin-top: 3rem;
}

</style>
