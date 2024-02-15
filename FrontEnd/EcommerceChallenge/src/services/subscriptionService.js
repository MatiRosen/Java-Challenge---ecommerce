import axiosClient from './axiosClient.js';
const apiClient = axiosClient.getApiClient();

export default {
  getSubscriptionByUserId(userId, token) {
    return apiClient.get(`subscription/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  getSubscriptionTypes() {
    return apiClient.get('/subscription/types');
  }
};
