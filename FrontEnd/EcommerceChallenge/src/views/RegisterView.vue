<template>
  <section id="Register" class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Registrarse</h2>

                <form @submit.prevent="register(vue, user)" name="form">
                  <div class="form-outline form-white mb-4">
                    <input
                      v-model="user.firstName"
                      type="text"
                      id="txtFirstName"
                      name="txtFirstName"
                      class="form-control form-control-lg"
                      placeholder="Nombre"
                      required
                    />
                  </div>
                  <div class="form-outline form-white mb-4">
                    <input
                      v-model="user.lastName"
                      type="text"
                      id="txtLastName"
                      name="txtLastName"
                      class="form-control form-control-lg"
                      placeholder="Apellido"
                      required
                    />
                  </div>
                  <div class="form-outline form-white mb-4">
                    <input
                      v-model="user.email"
                      type="email"
                      id="txtEmail"
                      name="txtEmail"
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
                  <div class="form-outline form-white mb-4">
                    <div class="form-check">
                      <input
                        v-model="user.receiveNewsletter"
                        type="checkbox"
                        class="form-check-input"
                        id="chkReceiveNewsletter"
                      />
                      <label class="form-check-label" for="chkReceiveNewsletter">
                        Recibir noticias
                      </label>
                    </div>
                  </div>

                  <button class="btn btn-outline-light btn-lg px-5">Registrarse</button>
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
import { jwtDecode } from 'jwt-decode'

export default {
  setup() {
    const storeUser = useUserStore()
    const { addUser } = storeUser
    let { isAuthenticated } = storeToRefs(storeUser)

    return {
      addUser,
      isAuthenticated,
      userService
    }
  },
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        receiveNewsletter: false
      },
      vue: this
    }
  },
  methods: {
    register(vue, user) {
      userService
        .register(user)
        .then(function (response) {
          var token = response.data.token;
          localStorage.setItem('token', token);
          var decodedToken = jwtDecode(token);

          var userId = decodedToken.id;
          var firstName = decodedToken.firstName;
          var lastName = decodedToken.lastName;
          var email = decodedToken.email;
          var receiveNewsletter = decodedToken.receiveNewsletter;

          vue.addUser(userId, firstName, lastName, email, receiveNewsletter);

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
