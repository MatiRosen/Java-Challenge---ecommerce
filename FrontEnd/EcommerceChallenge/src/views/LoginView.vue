<template>
  <section id="Login" class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Iniciar sesión</h2>
                <p class="text-white-50 mb-5">Ingrese su email y su contraseña!</p>

                <form @submit.prevent="login(user, vue)" name="form">
                  <div class="form-outline form-white mb-4">
                    <input
                      v-model="user.email"
                      type="email"
                      id="txtEmail"
                      name="Email"
                      class="form-control form-control-lg"
                      placeholder="Correo"
                      required
                    />
                  </div>
                  <div class="form-outline form-white mb-4">
                    <input
                      v-model="user.password"
                      type="password"
                      name="Password"
                      id="txtPassword"
                      class="form-control form-control-lg"
                      placeholder="Contraseña"
                      required
                    />
                  </div>

                  <button class="btn btn-outline-light btn-lg px-5">Iniciar sesión</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { storeToRefs } from 'pinia'
import { useUserStore } from '../stores/user'
import userService from '../services/userService'

export default {
  setup() {
    const storeUser = useUserStore()
    const { user } = storeToRefs(storeUser)
    const { addUser } = storeUser
    let { isAuthenticated } = storeToRefs(storeUser)

    return {
      user,
      addUser,
      isAuthenticated,
      userService
    }
  },
  data() {
    return {
      user: {
        email: '',
        password: ''
      },
      vue: this
    }
  },
  methods: {
    login: (user, vue) => {
      userService
        .login(user)
        .then(function (response) {
          vue.user.firstName = response.data.firstName
          vue.addUser(
            response.data.firstName,
            response.data.lastName,
            response.data.email,
            response.data.receiveNewsletter
          )
          vue.$router.push('/profile')
        })
        .catch(function (error) {
          alert(error.response.data)
        })
    }
  }
}
</script>

<style scoped>
.gradient-custom {
  background: #6a11cb;
  background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
  background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
}
</style>
