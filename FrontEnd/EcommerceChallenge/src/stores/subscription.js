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
    subscribeByFields(id, type, price, startDate, expirationDate) {
      Object.assign(this.subscription, {
        id,
        type,
        price,
        startDate,
        expirationDate
      });
    },
    subscribe(subscription) {
      Object.assign(this.subscription, subscription);
    },
    unsubscribe() {
      this.$reset();
    },
    userHasSubscription() {
      return this.subscription.id !== '';
    }
  },
});
