import { shallowMount, createLocalVue } from "@vue/test-utils";
import Login from "@/views/Login.vue";
import VueRouter from "vue-router";
import Vuex from "vuex";
import router from "@/router";

const localVue = createLocalVue();
localVue.use(VueRouter);
localVue.use(Vuex);

describe("Login component", () => {
  let store = new Vuex.Store({
    state: { auth: { status: { loggedIn: false }, user: null } }
  });
  test("Check label", () => {
    const wrapper = shallowMount(Login, { store, localVue, router });
    expect(wrapper.html()).toContain("Username:");
  });
  test("Check button exists", () => {
    const wrapper = shallowMount(Login, { store, localVue, router });
    expect(wrapper.contains("button")).toBe(true);
  });
});
