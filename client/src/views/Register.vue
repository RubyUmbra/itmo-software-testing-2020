<template>
  <div>
    <form name="form" @submit.prevent="handleRegister">
      <div v-if="!successful">
        <label for="username">Username:</label>
        <input
          v-model="user.username"
          type="text"
          class="form-control"
          name="username"
          id="username"
        />
        <label for="email">Email:</label>
        <input
          v-model="user.email"
          type="email"
          class="form-control"
          name="email"
          id="email"
        />
        <label for="password">Password:</label>
        <input
          v-model="user.password"
          type="password"
          class="form-control"
          name="password"
          id="password"
        />
        <button>Sign Up</button>
      </div>
    </form>
  </div>
</template>

<script>
import User from "../models/user";

export default {
  name: "Register",
  data() {
    return {
      user: new User("", "", ""),
      submitted: false,
      successful: false,
      message: ""
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister() {
      this.message = "";
      this.submitted = true;
      this.$store.dispatch("auth/register", this.user).then(
        data => {
          this.message = data.message;
          this.successful = true;
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.successful = false;
        }
      );
    }
  }
};
</script>
