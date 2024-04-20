import '@/css/clear.css'

import 'element-plus/dist/index.css'
import {createApp} from 'vue'
import App from './App.vue'
import {router} from "@/router/index.ts";
import elementPlus from 'element-plus'




createApp(App)
    .use(elementPlus)
    .use(router)
    .mount('#app')



