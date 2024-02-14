<template>
  <section id="Profile" class="vh-100 gradient-custom">
    <div class="d-flex justify-content-center py-3">
      <div class="card bg-dark text-white">
        <div class="card-header">
          <h2 class="card-title">Perfil</h2>
        </div>
        <div class="card-body">
          <h5 class="card-title">{{ user.firstName }} {{ user.lastName }}</h5>
          <p class="card-text">Email: {{ user.email }}</p>
          <p class="card-text">Receive Newsletters: {{ user.receiveNewsletters ? 'Si' : 'No' }}</p>
          <div v-if="userHasSubscription">
            <p class="card-text">Suscripción: {{ subscription.type }}</p>
            <p class="card-text">Desde : {{ formatDate(subscription.startDate) }}</p>
            <p class="card-text">Expira en: {{ formatDate(subscription.expirationDate) }}</p>
          </div>
          <p v-else class="card-text">Suscripción: No tiene</p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { storeToRefs } from 'pinia';
import { useUserStore } from '../stores/user';
import { useSubscriptionStore } from '../stores/subscription';
import { format } from 'date-fns';

export default {
  setup() {
    const storeUser = useUserStore();
    const { user } = storeToRefs(storeUser);

    const storeSubscription = useSubscriptionStore();
    const { subscription } = storeToRefs(storeSubscription);

    const userHasSubscription = () => {
      return useSubscriptionStore().userHasSubscription();
    };

    return {
      user,
      subscription,
      format,
      userHasSubscription
    };
  },
  methods: {
    formatDate(date) {
      return format(new Date(date), 'dd/MM/yyyy');
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
