import axiosClient from './axiosClient.js';
const apiClient = axiosClient.getApiClient();

export default {
  subscribe(subscription) {
    return apiClient.post('/subscription/', subscription);
  },
  getSubscriptionByUserId(userId) {
    return apiClient.get(`subscription/${userId}`, userId);
  },
  getSubscriptionTypes() {
    return apiClient.get('/subscription/types');
  }
};
