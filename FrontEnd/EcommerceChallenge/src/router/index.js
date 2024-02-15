import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';

import { useUserStore } from '../stores/user.js';

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
      component: () => import('../views/RegisterView.vue'),
      beforeEnter: (to, from, next) => {
        if (!useUserStore().isAuthenticated) {
          next();
        } else {
          next({ name: 'home' });
        }
      }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
      beforeEnter: (to, from, next) => {
        if (!useUserStore().isAuthenticated) {
          next();
        } else {
          next({ name: 'home' });
        }
      }
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
