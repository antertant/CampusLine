import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import postInput from "@/components/post/postInput";
import VueRouter from "vue-router";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('postInput.vue', () => {
  let getters;
  let store;
  let name = 'testUser'

  beforeEach(() => {
    // Contruct virtual vuex
    getters = {
      "loginInfo/getLUName": () => name,
    }

    store = new Vuex.Store({
      getters
    })
  })

  it('reset works well', ()=>{
    const wrapper = shallowMount(postInput, {store, localVue})
    wrapper.vm.postContent = 'testContent'
    wrapper.vm.resetPContent()

    expect(wrapper.vm.postContent).to.be.empty
  })

})
