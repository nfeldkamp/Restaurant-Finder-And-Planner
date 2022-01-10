<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link
        :to="{ name: 'register' }"
        id="notamember"
        style="
          color: white;
          text-align: center;
          font-size: 30px;
          text-decoration: none;
        "
        >Not a member yet? Please register here.</router-link
      >
      <button type="submit" v-on:click="login">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
div.alert.alert-danger {
  color: black;
  display: block;
  text-align: center;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

div.alert.alert-sucess {
  color: black;
  display: block;
  text-align: center;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

div form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 30vw;
  /* flex-grow: 1; */
}

button {
  margin-left: auto;
  margin-right: auto;
}

#username {
  justify-content: center;
  align-content: center;
  text-align: center;
  flex-wrap: wrap;
  /* flex-shrink: 3; */
  /* margin-left: auto;
  margin-right: auto; */
  width: 20vw;
  height: 3vw;
}

#password {
  margin-top: 1vw;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  text-align: center;
  flex-wrap: wrap;
  /* margin-left: auto;
  margin-right: auto; */
  /* flex-shrink: 3; */
  width: 20vw;
  height: 3vw;
}

label.sr-only {
  margin-left: auto;
  margin-right: auto;
  color: white;
  font-size: 20px;
}

h1 {
  text-align: center;
  color: white;
}

a:link {
  color: white;
}

#notamember {
  margin-top: 1vw;
  margin-bottom: 1vw;
}

/* @media only screen and (max-width: 550px) {
  form {
    align-content: center;
    justify-content: center;
  }
} */
</style>