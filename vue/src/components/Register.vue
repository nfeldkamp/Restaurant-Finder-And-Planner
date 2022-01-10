<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link
        :to="{ name: 'login' }"
        class
        style="
          color: white;
          text-align: center;
          font-size: 30px;
          text-decoration: none;
        "
        >Already have an account?</router-link
      >
      <button type="submit">Create Account</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
button {
  width: 20vw;
}

div.alert.alert-danger {
  color: black;
  display: block;
  text-align: center;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

form.form-register {
  padding-top: 8vw;
  display: grid;
  flex-direction: row;
  justify-content: center;
  height: 65vh;
}
button {
  margin-left: auto;
  margin-right: auto;
}

h1 {
  text-align: center;
  color: white;
}
#username {
  display: grid;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  text-align: center;
  flex-wrap: wrap;
}

#password {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  text-align: center;
  flex-wrap: wrap;
}

#confirmPassword {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
  text-align: center;
  flex-wrap: wrap;
}

label.sr-only {
  margin-left: auto;
  margin-right: auto;
  color: white;
  font-size: 20px;
}
</style>