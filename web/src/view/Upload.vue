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
        <p><span>加载进来的文件个数: {{ fileList?.length }}</span></p>
        <button @click="handleFile">解析文件</button>
        <button @click="showObject">显示obj数组</button>
        <p>------------------------------------分隔线------------------------------------</p>

        <el-table :data="testData" style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column label="Id" >
            <template #default="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column property="name" label="Name"  />
          <el-table-column property="md5" label="Md5"  />
        </el-table>

      </div>
    </div>
  </div>
</template>



<script setup lang="ts">
import {isShowTestInterface} from '../configuration'
import {nextTick, ref} from "vue";
import {handleBlog, getBlobs, BlobObj} from '../utils/file_utils'

/**
 * 文件的数量，并非文件的大小
 */
const fileList = ref<FileList|null>(null)

interface Test {
  id: number,
  name: string,
  md5: string
}

const testData = ref<Test[]>([])

const blobs = ref(getBlobs())

function showObject() {
  blobs.value = getBlobs()
  console.log(blobs.value)
  for (let i = 0; i < blobs.value.length; i++) {
    const t: Test = {
      id: blobs.value[i].index,
      name: ""+blobs.value[i].index,
      md5: blobs.value[i].md5
    }
    testData.value.push(t)

    console.log(testData.value)
  }
  nextTick()
}

function handleFile() {
  handleBlog(fileList.value[0])
}


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
  fileList.value = file.files
}

/**
 * 将文件拖进来后处理文件
 * @param e {DragEvent}
 */
function drop(e: DragEvent) {
  fileList.value = e.dataTransfer.files;
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
