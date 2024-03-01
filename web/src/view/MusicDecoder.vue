<template>
  <el-collapse>
    <el-row>
      <el-upload
          drag
          :auto-upload="false">
        <el-icon class="el-icon--upload"><upload-filled/></el-icon>
        <div class="el-upload__text">
          拖拽或者<em>点击</em>
        </div>
      </el-upload>
    </el-row>
  </el-collapse>

</template>

<script setup lang="ts">
import {fileConversion} from 'public/decoder'
import {UploadFilled} from "@element-plus/icons-vue";



</script>

<style scoped>

</style>
