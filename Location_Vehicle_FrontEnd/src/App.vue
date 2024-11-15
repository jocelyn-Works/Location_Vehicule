<script setup>
import Header from "@/views/Header.vue";
import { RouterView } from 'vue-router'
import Footer from "@/views/Footer.vue";
</script>

<template>
    <Header></Header>
    <RouterView />
    <Footer></Footer>
</template>

<style scoped></style>
