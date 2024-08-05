<template>
<el-row>
  <el-col :span="1">
    <el-button @click="onUploadBtn">上传</el-button>
  </el-col>
  <el-col :span="1">
    <el-button>下载</el-button>
  </el-col>
  <el-col :span="4" :offset="19">
<!--    <el-icon><Search /></el-icon>-->
    <el-input class="a_input">a</el-input>
  </el-col>
</el-row>
<el-row>

</el-row>
</template>

<script setup lang="ts">
import {useRouter} from 'vue-router'

const router = useRouter();

/**
 * 上传按钮点击事件,点击后跳转到上传界面
 */
function onUploadBtn() {
  router.push('/upload')
}



</script>

<style scoped>

.a_input::before {
  content: url("a.svg");
}

</style>
