import '@/css/clear.css'

import 'element-plus/dist/index.css'
import {createApp} from 'vue'
import App from './App.vue'
import {router} from "@/router/index.ts";
import elementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(elementPlus)
app.use(router)
app.mount('#app')


