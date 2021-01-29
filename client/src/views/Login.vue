<template>
  <div>
    <form name="form" @submit.prevent="handleLogin">
      <label for="username">Username:</label>
      <input
        v-model="user.username"
        type="text"
        name="username"
        id="username"
        required
      />
      <label for="password">Password:</label>
      <input
        v-model="user.password"
        type="password"
        class="form-control"
        name="password"
        id="password"
      />
      <button>Login</button>
    </form>
  </div>
</template>

<script>
import User from "../models/user";

export default {
  name: "Login",
  data() {
    return {
      user: new User("", ""),
      message: ""
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleLogin() {
      if (this.user.username && this.user.password) {
        this.$store.dispatch("auth/login", this.user).then(
          () => {
            this.$router.push("/profile");
          },
          error => {
            this.message =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
          }
        );
      }
    }
  }
};
</script>
