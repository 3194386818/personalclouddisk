import { createRouter, createWebHashHistory } from 'vue-router'

import Main from '@/components/Main.vue'
import Test from '@/components/Main2.vue'
import Test1 from '@/test/Test1.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: Main
    },
    {
        path: '/test',
        name: 'test',
        component: Test
    },
    {
        path: '/test1',
        name: 'test1',
        component: Test1
    }
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})
