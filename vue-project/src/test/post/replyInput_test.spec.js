import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import replyInput from "@/components/post/replyInput";
import VueRouter from "vue-router";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('replyInput.vue', () => {
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
    const wrapper = shallowMount(replyInput, {store, localVue})
    wrapper.vm.postReplyContent = 'testContent'
    wrapper.vm.resetCModal()

    expect(wrapper.vm.postReplyContent).to.be.empty
  })

})
