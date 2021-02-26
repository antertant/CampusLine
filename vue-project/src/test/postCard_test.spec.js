import { shallowMount, createLocalVue, mount } from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import VueRouter from "vue-router";
import postCard from "@/components/post/postCard";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('postCard.vue', () =>{
  let getters;
  let store;
  let name = ''

  beforeEach(() => {
    // Contruct virtual vuex
    getters = {
      "loginInfo/getLUName": () => name,
    }

    store = new Vuex.Store({
      getters
    })

  })

  it('when visitor click like, show warning', async () => {
    const wrapper = mount(postCard, {store, localVue,
      propsData: {
        postContent: {post_createtime: 0}
      }});
    const likeButton = wrapper.find('b-list-group-item')
    name = ''
    likeButton.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(wrapper.find('b-toast').exists()).to.be.ok
    })
  })
})
