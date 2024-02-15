import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  state: () => ({
    user: {
      id: '',
      firstName: '',
      lastName: '',
      email: '',
      receiveNewsletter: false
    }
  }),
  actions: {
    addUser(id, firstName, lastName, email, receiveNewsletter) {
      Object.assign(this.user, {
        id,
        firstName,
        lastName,
        email,
        receiveNewsletter
      });
    },
    removeUser() {
      this.$reset();
    }
  },
  getters: {
    isAuthenticated() {
      return this.user.email !== '';
    }
  }
});
