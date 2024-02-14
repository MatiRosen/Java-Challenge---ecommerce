<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary">
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <RouterLink to="/" class="nav-item nav-link"
          ><img src="../assets/logo.jpg" height="15" alt="Logo" />
        </RouterLink>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <RouterLink to="/" class="nav-link">Home</RouterLink>
          </li>
          <li class="nav-item">
            <div v-if="isAuthenticated">
              <RouterLink to="/profile" class="nav-link">Perfil</RouterLink>
            </div>
          </li>
        </ul>
      </div>
      <div class="d-flex align-items-center">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <div v-if="isAuthenticated">
            <li class="nav-item">
              <RouterLink to="/" class="nav-link" @click="logout()">Cerrar sesión</RouterLink>
            </li>
          </div>
          <div v-else>
            <li class="nav-item d-flex">
              <RouterLink to="/login" class="nav-link"> Iniciar sesion</RouterLink>
              <RouterLink to="/register" class="nav-link">Registrarse</RouterLink>
            </li>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { storeToRefs } from 'pinia'
import { useUserStore } from '../stores/user'

const storeUser = useUserStore()
let { isAuthenticated } = storeToRefs(storeUser)
const { removeUser } = storeUser

const logout = () => {
  removeUser()
}
</script>

<style scoped>
.nav {
  padding: 25px;
  text-align: center;
}

.nav-item {
  font-weight: bold;
  color: #e20000;
  font-size: 20px;
}
img {
  height: 50px;
  margin-right: 10px;
}
</style>
