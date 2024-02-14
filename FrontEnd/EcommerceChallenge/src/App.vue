<script setup>
import { RouterView } from 'vue-router'
import MainMenu from './components/MainMenu.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <MainMenu></MainMenu>
    </div>
  </header>

  <body>
    <RouterView />
  </body>

  <footer></footer>
</template>

<style scoped></style>
