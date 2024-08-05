<template>
  <input type="file" id="f" v-show="false" @change="f_input" multiple="multiple" >

  <div class="container mt-2">
    <div class="el-row">
      <div class="el-col-12 aa">
        <div class="file_upload_div file_upload_div_default" :class="{file_upload_div_highlight: isHeightLightDisplay}" @click="file_upload_click" @dragover.prevent="dragover" @dragleave="dragleave" @drop.prevent="drop" >
          <p>拖入文件</p>
        </div>
      </div>
    </div>
    <div class="el-row test_interface" v-if="isShowTestInterface">
      <div class="el-col-12">
        <h3>测试界面</h3>
        <span>加载进来的文件个数: {{ fileSize }}</span>
      </div>
    </div>
  </div>
</template>



<script setup lang="ts">
import {isShowTestInterface} from '../configuration'
import {ref} from "vue";
import Text from "@/test/Text.vue";

/**
 * 文件的数量，并非文件的大小
 */
const fileSize = ref<number>(0)

/**
 * 是否高亮显示
 */
const isHeightLightDisplay = ref<boolean>(false)


/**
 * 文件上传界面点击事件
 */
function file_upload_click() {
  let file: HTMLInputElement = document.querySelector('#f')
  file.click()
}

/**
 * 获取文件，进行处理
 */
function f_input() {
  const file: HTMLInputElement = document.querySelector('#f')
  let fileList: FileList = file.files;
  fileSize.value = fileList.length

  if (fileList.length > 0) {
    let fileListElement: File = fileList[0];
    console.log(fileListElement.name)
  }
}

/**
 * 将文件拖进来后处理文件
 * @param e {DragEvent}
 */
function drop(e: DragEvent) {
  const files = e.dataTransfer.files;
  fileSize.value = files.length
}

/**
 * 当文件拖进来显示高亮
 */
function dragover() {
  isHeightLightDisplay.value = true
}

/**
 * 当文件拖出范围或者松开，则恢复默认样式
 */
function dragleave() {
  isHeightLightDisplay.value = false
}

</script>

<style scoped>


.aa {
  /*width: 500px;*/
  height: 54rem;
  /*background-color: cyan;*/
}

.file_upload_div {
  width: 80%;
  height: 80%;
  position: absolute;
  cursor: pointer;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: black;
  border: 3px dashed #604200;
  background-color: #e3e3e3;
}
.file_upload_div p {
  line-height: 691px;
  font-weight: bold;
  font-size: 2.5em;
  letter-spacing: 1.2rem;
  text-align: center;
  user-select: none;
}

/**
文件上传高亮显示
 */
.file_upload_div_highlight {
  color: white;
  border: 3px dashed #604200;
  background-color: #99a6e3;
}

</style>
