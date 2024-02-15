import axiosClient from './axiosClient.js';
const apiClient = axiosClient.getApiClient();

export default {
  subscribe(subscriptionType, userId, token) {
    return apiClient.post('order/checkout', {subscriptionType, userId}, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
};
