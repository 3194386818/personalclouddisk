<template>
    <el-row justify="center">
      <el-col :span="12">
        <el-menu
            mode="horizontal"
            class="menu"
            :default-active="actionIndex"
            :router="true">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/capacity">容量</el-menu-item>
          <el-menu-item index="/upload">上传</el-menu-item>
          <el-menu-item index="/music">qq音乐解码</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
</template>

<script setup lang="ts">
import icon from '../../public/icon.svg'
import {ref} from "vue";

const actionIndex = ref("/")

</script>

<style scoped>
.menu {
  background-color: unset;
  justify-content: center;
}
.menu li {
  font-size: 1.1em;
  padding: 0 1.4rem;
}


</style>
