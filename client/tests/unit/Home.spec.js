import { mount, createLocalVue } from "@vue/test-utils";
import Home from "@/views/Home.vue";
import VueRouter from "vue-router";
import router from "@/router";

const localVue = createLocalVue();
localVue.use(VueRouter);

test("Check title", () => {
  const wrapper = mount(Home, {
    localVue,
    router
  });
  expect(wrapper.html()).toContain("<h1>Home page</h1>");
});
