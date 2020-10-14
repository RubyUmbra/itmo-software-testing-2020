<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <router-link tag="li" to="/" exact>Home</router-link>
          <router-link tag="li" to="/timeline">Timeline</router-link>
          <router-link tag="li" to="/auth" class="right">Sign in</router-link>
        </ul>
      </nav>
    </header>
    <router-view />
    <footer>
      (c) 2020 <a href="https://github.com/RubyUmbra">RubyUmbra</a>
    </footer>
  </div>
</template>
