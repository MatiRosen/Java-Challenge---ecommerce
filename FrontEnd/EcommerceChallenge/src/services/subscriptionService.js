import axiosClient from './axiosClient.js';
const apiClient = axiosClient.getApiClient();

export default {
  getSubscriptionByUserId(userId) {
    return apiClient.get(`subscription/${userId}`, userId);
  },
  getSubscriptionTypes() {
    return apiClient.get('/subscription/types');
  }
};
