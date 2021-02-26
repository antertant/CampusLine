import { shallowMount } from "@vue/test-utils";
import {expect} from "chai";
import loginCard from "@/components/manage/loginCard";


describe('loginCard.vue', () =>{

  it('when click reset, flush all input', async () => {
    const wrapper = shallowMount(loginCard);
    const resetButton = wrapper.findAll('b-button').at(1)
    resetButton.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(wrapper.vm.loginInfo.username).to.be.equal('')
      expect(wrapper.vm.password.password).to.be.equal('')
      expect(wrapper.vm.show).not.to.be.ok
    })
  })
})
