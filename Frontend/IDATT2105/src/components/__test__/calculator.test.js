import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from 'src/components/CalculatorPage/MainSection.vue';

test("add two numbers", async () => {
  const wrapper = mount(Calculator)
  const button1 = wrapper.find('#button1')
  await button1.trigger('click')
  const button2 = wrapper.find('#button2')
  await button2.trigger('click')
  const buttonAdd = wrapper.find('#buttonPlus')
  await buttonAdd.trigger('click')
  const result = wrapper.find('#currentNumber')
  expect(result.text()).toBe('12')
  
})


