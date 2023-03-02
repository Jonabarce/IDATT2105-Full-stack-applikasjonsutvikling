import { test, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import { createStore } from 'vuex';
import Form from 'src/components/ContactPage/MainSection.vue';

test('check that submit button works and updates store', async () => {
  const store = createStore({
    state: {
      name: '',
      email: '',
    },
    mutations: {
      SET_NAME: (state, name) => (state.name = name),
      SET_EMAIL: (state, email) => (state.email = email),
    },
  });
  const wrapper = mount(Form, {
    global: {
      plugins: [store],
    },
  });

  await wrapper.find('#firstname').setValue('John');
  await wrapper.find('#email').setValue('john@example.com');
  await wrapper.find('#message').setValue('Hello World!');
  const button2 = wrapper.find('#submit')
  await button2.trigger('click')
  await new Promise(resolve => setTimeout(resolve, 0));
  expect(wrapper.find('#firstname').element.value).toBe('John');
  expect(wrapper.find('#email').element.value).toBe('john@example.com');
  expect(store.state.name).toBe('John');
  expect(store.state.email).toBe('john@example.com');
});
