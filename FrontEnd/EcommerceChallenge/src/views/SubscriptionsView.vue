<template>
  <div class="vh-100 gradient-custom d-flex justify-content-center">
    <div class="container">
      <div v-if="loading" class="text-center">Cargando...</div>
      <div v-else class="d-flex justify-content-center flex-wrap">
        <div class="col-md-4 pt-3" v-for="subscription in subscriptionTypes" :key="subscription.id">
          <div class="card text-white bg-dark mb-3">
            <div class="card-header text-center">{{ subscription.name }}</div>
            <div class="card-body text-center">
              <h5 class="card-title">${{ subscription.price }}</h5>
              <p class="card-text">{{ subscription.description }}</p>
              <button
                class="btn btn-light"
                :disabled="userHasSubscription()"
                @click="subscribe(subscription)"
              >
                Suscribirse
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useSubscriptionStore } from '../stores/subscription';
import subscriptionService from '../services/subscriptionService';
import paymentService from '../services/paymentService';
import { jwtDecode } from 'jwt-decode';

export default {
  setup() {
    const subscriptionTypes = ref([]);
    const { userHasSubscription } = useSubscriptionStore();
    const loading = ref(true);

    onMounted(async () => {
      subscriptionTypes.value = (await subscriptionService.getSubscriptionTypes()).data;
      loading.value = false;
    });

    return {
      subscriptionTypes,
      userHasSubscription,
      loading
    };
  },
  data() {
    return {
      vue: this
    };
  },
  methods: {
    subscribe(subscription) {
      const token = localStorage.getItem('token');
      const userId = jwtDecode(token).id;

      paymentService
        .subscribe(subscription.name, userId, token)
        .then(function (response) {
          const link = response.data;
          window.location.href = link;
        })
        .catch(function (error) {
          console.log(error);
          alert(error.response.data);
        });
    }
  }
};
</script>

<style scoped>
.gradient-custom {
  background: #6a11cb;
  background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
  background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
}
</style>
