<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/timeline">Timeline</router-link> |
      <router-link to="/auth">Auth</router-link>
    </div>
    <router-view />
  </div>
</template>
