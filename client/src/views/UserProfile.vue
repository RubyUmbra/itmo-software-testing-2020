<template>
  <div>
    <header>
      <h3>
        <strong>{{ user.username }}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Id:</strong>
      {{ $route.params.id }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ user.email }}
    </p>
  </div>
</template>

<script>
import User from "@/models/user";
import UserService from "../services/user.service";

export default {
  name: "UserProfile",
  data() {
    return {
      user: new User("", "")
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
    UserService.getUserProfile(this.$route.params.id).then(
      response => {
        this.user.username = response.data.username;
        this.user.email = response.data.email;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
