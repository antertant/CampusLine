import { shallowMount } from "@vue/test-utils";
import {expect} from "chai";
import axios from "axios";
import registerCard from "@/components/manage/registerCard";
import MockAdapter from "axios-mock-adapter";

describe('registerCard.vue', () => {

  it('when click reset, flush all input', async () => {
    const wrapper = shallowMount(registerCard);
    const resetButton = wrapper.findAll('b-button').at(1)

    wrapper.vm.registerForm.email = 'test@test.com'
    wrapper.vm.registerForm.username = 'testName'
    wrapper.vm.registerForm.password = 123456

    resetButton.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(wrapper.vm.registerForm.email).to.be.empty
      expect(wrapper.vm.registerForm.username).to.be.empty
      expect(wrapper.vm.registerForm.password).to.be.empty
    })
  })
})
