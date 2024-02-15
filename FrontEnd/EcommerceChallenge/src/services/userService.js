import axiosClient from './axiosClient.js';
const apiClient = axiosClient.getApiClient();

export default {
  login(user) {
    return apiClient.post('/auth/login', user);
  },
  register(user) {
    return apiClient.post('/auth/register', user);
  },
  getUserByToken(token) {
    return apiClient.get(`/user/me`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }
};
