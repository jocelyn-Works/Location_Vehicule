import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/HomePage.vue'),
        },
        {
            path: '/catalog',
            name: 'catalog',
            component: () => import('../views/ProductList.vue'),
        },
        {
            path: '/product/:id',
            name: 'productInfo',
            component: () => import('../views/ProductInfo.vue'),

        }
    ]
})

export default router