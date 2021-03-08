import {createLocalVue, mount, shallowMount} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import VueRouter from "vue-router";
import moment from "moment";
import MockAdapter from "axios-mock-adapter";
import commentInput from "@/components/post/commentInput";


const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('commentInput.vue', () => {
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

  it('reset button works well', ()=>{
    const wrapper = shallowMount(commentInput, {store, localVue, propsData:{commentId:2}})
    wrapper.vm.postCommentContent = 'testContent'

    wrapper.vm.resetCModal()
    wrapper.vm.$mount()
    expect(wrapper.vm.postCommentContent).to.be.empty
  })

})
