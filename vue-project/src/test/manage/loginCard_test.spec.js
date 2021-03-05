import { shallowMount } from "@vue/test-utils";
import {expect} from "chai";
import axios from "axios";
import loginCard from "@/components/manage/loginCard";
import MockAdapter from "axios-mock-adapter";

describe('loginCard.vue', () =>{

  it('when click reset, flush all input', async () => {
    const wrapper = shallowMount(loginCard);
    const resetButton = wrapper.findAll('b-button').at(1)

    wrapper.vm.loginInfo.username = 'testName'
    wrapper.vm.loginInfo.password = 123456
    wrapper.vm.show = true

    resetButton.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(wrapper.vm.loginInfo.username).to.be.equal('')
      expect(wrapper.vm.loginInfo.password).to.be.equal('')
      expect(wrapper.vm.show).not.to.be.ok
    })
  })

  // it('login success test', done => {
  //   const wrapper = shallowMount(loginCard);
  //   const loginButton = wrapper.findAll('b-button').at(0)
  //
  //   const mock = new MockAdapter(axios);
  //   mock.onAny("/api/login").reply(200);
  //
  //   loginButton
  //     .trigger('click')
  //     .then(() => {
  //       expect(wrapper.vm.showLoginError).to.be.false;
  //     })
  //   done();
  // })

  // it('login fail test', done => {
  //   const wrapper = shallowMount(loginCard);
  //   const loginButton = wrapper.findAll('b-button').at(0)
  //
  //   const mock = new MockAdapter(axios);
  //   mock.onAny("/api/login").reply(400);
  //
  //   loginButton
  //     .trigger('click')
  //     .then(() => {
  //       expect(wrapper.vm.showLoginError).to.be.true;
  //     })
  //   done();
  // })

})
