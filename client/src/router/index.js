import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/timeline",
    name: "Timeline",
    component: () => import("../views/Timeline.vue")
  },
  {
    path: "/auth",
    name: "Auth",
    component: () => import("../views/Auth.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  linkActiveClass: "active",
  linkExactActiveClass: "active"
});

export default router;
