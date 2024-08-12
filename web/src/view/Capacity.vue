<template>
  <!--  <Tip/>-->
  <div ref="main" style="width: 100%; height: 800px"></div>

</template>

<script setup lang="ts">
import Tip from "@/components/Tip.vue";
import * as echarts from 'echarts'
import {nextTick, onMounted, ref} from "vue";
import {ECBasicOption} from "echarts/types/dist/shared";
import {ECUnitOption} from "echarts/types/src/util/types";
import axios from "axios";

// 获取要渲染的图形的dom
const main = ref(null)

type Quota = {
  // 总空间大小，单位B
  total: number,
  // 已使用大小，单位B
  used: number
}

const quotaData = ref<Quota>()




onMounted(async () => {
  const response = await axios.get('http://127.0.0.1:8081/quota')
  quotaData.value = response.data
  const myChart = echarts.init(main.value);
  myChart.setOption(setData(quotaData.value))
})

function setData(data?: Quota): ECUnitOption | null {
  if (data == null) return null
  // 计算剩余容量
  const surplus = data.total - data.used

  const ec_data: ECBasicOption = {
    title: {
      text: '容量',
      left: 'center',
      subtext: 'GB为单位'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '容量',
        type: 'pie',
        radius: '50%',
        data: [
          {value: aG(data.used), name: '已使用'},
          {value: aG(surplus), name: '剩余', itemStyle: {color: '#bebebe'}},
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ],
  }
  return ec_data
}

function aG(size: number): number {
  return Number((size / 1024 / 1024 / 1024).toFixed(2))
}

</script>

<style scoped>

</style>
