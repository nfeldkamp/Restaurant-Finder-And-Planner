import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LandingPage from '../views/LandingPage.vue'
import Restaurants from '../views/Restaurants.vue'
import Preferences from '../views/Preferences.vue'

import Invitations from '../views/Invitations.vue'
import GuestEntry from '../views/GuestEntry.vue'
import UserInvitations from '../views/UserInvitations.vue'
import GuestView from '../views/GuestView.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/landing-page",
      name: "landing-page",
      component: LandingPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/restaurants",
      name: "restaurants",
      component: Restaurants,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/invitations/restaurants",
      name: "invitations-restaurants",
      component: Restaurants,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/preferences',
      name: 'preferences',
      component: Preferences,
      meta: {
        // Change back to true after done editing -Essence
        requiresAuth: false
      }
    },
    {
      path: "/invitations",
      name: "invitations",
      component: Invitations,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/invitations/guestentry",
      name: "guest-entry",
      component: GuestEntry,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/invitations/user",
      name: "user-invitations",
      component: UserInvitations,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/invitations/guestview/:guestEmail",
      name: "guest-view",
      component: GuestView,
      meta: {
        requiresAuth: false
      }
    },
  ],
})


router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/landing-page");
  } else {
    // Else let them go to their next destination
    next();
  }
}
);

export default router;
