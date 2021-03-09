import { shallowMount, createLocalVue } from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import VueRouter from "vue-router";
import stickyHeader from "@/components/header/stickyHeader";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter);

describe("stickyHeader.vue", () => {
  let getters;
  let actions;
  let store;
  let name = 'testName'
  let logState = true
  let newsCounter = {newsCounter:0, cnewlcomment:0, cnewpostcomment:0, cnewcommentreply:0}

  beforeEach(() => {
    // Contruct virtual vuex
    getters = {
      "loginInfo/getLUName": () => name,
      "loginInfo/getLoginState": () => logState,
      "newMessage/getNewMessageCount": () => newsCounter
    }

    actions = {
      "loginInfo/userLogout": () => {
        name = '';
        logState = false;
      }
    }

    store = new Vuex.Store({
      getters,
      actions
    })

  })

  it("user name should be showed when the loginState is true", () => {
    const wrapper = shallowMount(stickyHeader, {store, localVue});
    const loginName = wrapper.findAll('b-nav-item').at(4).findAll('span').at(0)
    expect(loginName.text()).to.equal(name)
  })

  it("logout is correctly called", async () => {
    const wrapper = shallowMount(stickyHeader, {store, localVue});
    const loginName = wrapper.findAll('b-nav-item').at(4).findAll('span').at(0)
    const logout = wrapper.findAll('b-button-group').at(1).findAll('b-button').at(1)
    logState = true
    expect(loginName.text()).not.to.be.empty
    logout.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(logState).to.be.equal(false)
      expect(loginName.text()).to.be.empty
    })
  })

})
