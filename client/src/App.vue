<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      |
      <router-link to="/about">About</router-link>
      |
      <router-link to="/login">Login</router-link>
      |
      <router-link to="/register">Register</router-link>
      |
      <router-link to="/profile">Profile</router-link>
      |
      <a href @click.prevent="logOut" id="logoutButton">LogOut</a>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    }
  }
};
</script>
