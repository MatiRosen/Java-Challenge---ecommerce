import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

import { useUserStore } from '../stores/user.js';
import { useSubscriptionStore } from '../stores/subscription.js';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue'),
      beforeEnter: (to, from, next) => {
        if (useUserStore().isAuthenticated) {
          next();
        } else {
          next({ name: 'home' });
        }
      }
    },
    {
      path: '/subscriptions',
      name: 'subscriptions',
      component: () => import('../views/SubscriptionsView.vue'),
      beforeEnter: (to, from, next) => {
        if (useUserStore().isAuthenticated) {
          next();
        } else {
          next({ name: 'home' });
        }
      }
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: { name: 'home' }
    }
  ]
});

export default router;
