import { shallowMount, createLocalVue, mount } from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import VueRouter from "vue-router";
import commentCard from "@/components/post/commentCard";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('commentCard.vue', () =>{
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

  it('cc test', async () => {
    const wrapper = mount(commentCard, {store, localVue,
      propsData: {
        commentData: {post_createtime: 0}
      }});
  })
})
