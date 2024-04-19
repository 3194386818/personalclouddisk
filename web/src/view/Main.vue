<template>
  <input type="file" id="f" v-show="false" @change="f_input">

  <div class="container mt-2">
    <div class="row">
      <div class="col-12 aa">
        <div class="file_upload_div" @click="file_upload_click">
          <p>拖入文件</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'




function file_upload_click() {
  let file: HTMLInputElement = document.querySelector('#f')
  file.click()
}

function f_input() {
  let file: HTMLInputElement = document.querySelector('#f')
  let fileList: FileList = file.files;
  if (fileList.length > 0) {
    let fileListElement: File = fileList[0];
    console.log(fileListElement.name)
  }
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
  border: 3px dashed #604200;
  background-color: #99a6e3;
}
.file_upload_div p {
  color: white;
  line-height: 691px;
  font-weight: bold;
  font-size: 2.5em;
  letter-spacing: 1.2rem;
  text-align: center;
  user-select: none;
}

</style>
