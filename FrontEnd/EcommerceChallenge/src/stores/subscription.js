import { defineStore } from 'pinia';

export const useSubscriptionStore = defineStore('subscription', {
  state: () => ({
    subscription: {
      id: '',
      type: '',
      price: 0.0,
    }
  }),
  actions: {
    subscribe(id, type, price) {
      Object.assign(this.subscription, {
        id,
        type,
        price
      });
    },
    unsubscribe() {
      this.$reset();
    },
    userHasSubscription() {
      return this.subscription.id !== '';
    }
  },
});
