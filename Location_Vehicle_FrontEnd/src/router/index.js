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
            path: '/connexion',
            name: 'connexion',
            component: () => import('../views/Connexion.vue'),
        },
        {
            path: '/inscription',
            name: 'inscription',
            component: () => import('../views/Inscription.vue'),
        }
    ]
})

export default router