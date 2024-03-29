import { createRouter, createWebHashHistory } from 'vue-router'

import Main from '@/components/Main.vue'
import Test from '@/components/Main2.vue'
import Test1 from '@/test/Test1.vue'
import MusicDecoder from '@/view/MusicDecoder.vue'

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
    },
    {
        path: '/music',
        name: 'music',
        component: MusicDecoder
    }
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})
