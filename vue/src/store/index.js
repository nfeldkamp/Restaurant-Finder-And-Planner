import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    displayedRestaurant: '',
    displayedInvitation: '',
    guestCounter: 0,
    token: currentToken || '',
    user: currentUser || {},
    frominvitation: false,
    fromguestentry: false,
    preferences: [
      {
        id: "1",
        name: "Indian",
        value: ""
      },
      {
        id: "2",
        name: "Breakfast",
        value: ""
      },
      {
        id: "3",
        name: "Mexican",
        value: ""
      },
      {
        id: "4",
        name: "Japanese",
        value: ""
      },
      {
        id: "5",
        name: "Italian",
        value: ""
      },
      {
        id: "6",
        name: "Seafood",
        value: ""
      },
      {
        id: "7",
        name: "Pizza",
        value: ""
      },
      {
        id: "8",
        name: "Chinese",
        value: ""
      },
      {
        id: "9",
        name: "Thai",
        value: ""
      },
      {
        id: "10",
        name: "Cajun",
        value: ""
      }
    ],
    invitations: [
      {
        id: "1",
        location: "cleveland",
        date: "10/10/10",
        time: "12:00PM",
        numguests: "4",
        guests: "bob, dave, mike, allen"
      },
      {
        id: "2",
        location: "madison",
        date: "10/10/11",
        time: "12:00PM",
        numguests: "2",
        guests: "bob, dave"
      },
      {
        id: "3",
        location: "new york, new york",
        date: "10/10/10",
        time: "12:00PM",
        numguests: "3",
        guests: "bob, dave, mike"
      }
    ],
    invitation: { restaurants: [], guests: [] },
    restaurants: [
      {
        id: "1",
        name: "Rest Name",
        address: "Address",
        type: "Rest type",
        phone_number: "1234561234",
        open: true,
        hourstoday: "12pm-3pm",
        hoursall: [{ day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "closed" }, { day: "Monday", hours: "closed" }],
        stars: 3,
        price: 3,
        image: "https://media.istockphoto.com/photos/vintage-diner-exterior-picture-id182681168?k=20&m=182681168&s=612x612&w=0&h=XwAL_A-BVA7XbeGJKE6IcSINVq-zVGe8SZ2qnH88rGM="
      },
      {
        id: "2",
        name: "Bob's other place",
        address: "123 whatever",
        type: "FoooDDDd",
        phone_number: "1234561234",
        open: true,
        hourstoday: "12-3",
        hoursall: ["12-2", "12-3", "12-4", "12-5", "12-6", "closed", "closed"],
        stars: 3,
        price: 3,
        image: "https://media.istockphoto.com/photos/vintage-diner-exterior-picture-id182681168?k=20&m=182681168&s=612x612&w=0&h=XwAL_A-BVA7XbeGJKE6IcSINVq-zVGe8SZ2qnH88rGM="
      },
      {
        id: "3",
        name: "Bob's other other place",
        address: "123 whatever",
        type: "FoooDDDd",
        phone_number: "",
        open: false,
        hourstoday: "12-3",
        hoursall: ["12-2", "12-3", "12-4", "12-5", "12-6", "closed", "closed"],
        stars: 3,
        price: 3,
        image: "https://media.istockphoto.com/photos/vintage-diner-exterior-picture-id182681168?k=20&m=182681168&s=612x612&w=0&h=XwAL_A-BVA7XbeGJKE6IcSINVq-zVGe8SZ2qnH88rGM="
      },
      {
        id: "4",
        name: "Rest Name",
        address: "Address",
        type: "Rest type",
        phone_number: "1234561234",
        open: true,
        hourstoday: "12pm-3pm",
        hoursall: [{ day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "12-2" }, { day: "Monday", hours: "closed" }, { day: "Monday", hours: "closed" }],
        stars: 3,
        price: 3,
        image: "https://media.istockphoto.com/photos/vintage-diner-exterior-picture-id182681168?k=20&m=182681168&s=612x612&w=0&h=XwAL_A-BVA7XbeGJKE6IcSINVq-zVGe8SZ2qnH88rGM="
      },
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    UPDATE_DISPLAY(state, data) {
      state.displayedRestaurant = data;
    },
    UPDATE_INVITATION(state, data) {
      state.invitation = data;
    },
    UPDATE_FROM_INVITATION(state) {
      state.frominvitation = false;
    },
    UPDATE_FROM_GUEST_ENTRY(state) {
      state.fromguestentry = !state.fromguestentry;
    },
    UPDATE_RESTAURANTS(state, data) {
      state.restaurants = data;
    },
    UPDATE_DISPLAY_INVITATION(state, data) {
      state.displayedInvitation = data;
    },
    UPDATE_RESTAURANTS_INVITATION(state, data) {
      state.invitation.restaurants = data;
    },
    ADD_GUEST(state, data) {
      state.invitation.guests.push(data);
    },
    UPDATE_INVITATIONS(state, data) {
      state.invitations = data;
    },
    UPDATE_PREFERENCES(state, data) {
      state.preferences = data;
    },
    UPDATE_PREFERENCE_VALUE(state, data, i) {
      state.preferences[i].value = data;
    }
  }
})
