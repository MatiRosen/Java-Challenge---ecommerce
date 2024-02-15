<script setup>
import { RouterView } from 'vue-router';
import MainMenu from './components/MainMenu.vue';
import { jwtDecode } from 'jwt-decode';
import { useUserStore } from './stores/user';
import { useSubscriptionStore } from './stores/subscription';
import subscriptionService from './services/subscriptionService';

const storeUser = useUserStore();
const { addUser } = storeUser;

var token;
try {
  token = localStorage.getItem('token');
  var decodedToken = jwtDecode(token);

  var userId = decodedToken.id;
  var firstName = decodedToken.firstName;
  var lastName = decodedToken.lastName;
  var email = decodedToken.email;
  var receiveNewsletter = decodedToken.receiveNewsletter;

  addUser(userId, firstName, lastName, email, receiveNewsletter);

  const storeSubscription = useSubscriptionStore();
  const { subscribeByFields } = storeSubscription;

  subscriptionService
    .getSubscriptionByUserId(userId)
    .then(function (subscriptionResponse) {
      subscribeByFields(
        subscriptionResponse.data.id,
        subscriptionResponse.data.type,
        subscriptionResponse.data.price,
        subscriptionResponse.data.startDate,
        subscriptionResponse.data.expirationDate
      );
    })
    .catch(function (error) {
      vue.$router.push('/profile');
    });
} catch (error) {}
</script>

<template>
  <header>
    <div class="wrapper">
      <MainMenu></MainMenu>
    </div>
  </header>

  <body>
    <RouterView />
  </body>

  <footer></footer>
</template>

<style scoped></style>
