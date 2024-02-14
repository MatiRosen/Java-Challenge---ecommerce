import axiosClient from './axiosClient.js'
const apiClient = axiosClient.getApiClient()

export default {
  login(user) {
    console.log(user)
    return apiClient.post('/auth/login', user)
  },
  register(user) {
    return apiClient.post('/auth/register', user)
  },
  getUser(id) {
    return apiClient.get(`/user/${id}`)
  }
}
