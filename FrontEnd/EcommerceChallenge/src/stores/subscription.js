import { defineStore } from 'pinia';

export const useSubscriptionStore = defineStore('subscription', {
  state: () => ({
    subscription: {
      id: '',
      type: '',
      price: 0.0,
      expirationDate: ""
    }
  }),
  actions: {
    subscribe(id, type, price, startDate, expirationDate) {
      Object.assign(this.subscription, {
        id,
        type,
        price,
        startDate,
        expirationDate
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
