import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import commentReplyCard from "@/components/post/commentReplyCard";
import VueRouter from "vue-router";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('commentReplyCard.vue', () => {
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

  it('show correct from user', ()=>{
    const wrapper = shallowMount(commentReplyCard, {store, localVue,
      propsData: {
        replyData: {
          creply_time: 0,
          reply_id: 1,
          from_user: 'testFromUser',
          to_user: 'testToUser',
          creply_content: 'testContent'}
      }})
    const fromUser = wrapper.findAll('b-col').at(2)

    expect(fromUser.text()).to.include('testFromUser')
  })

  it('show correct to user and content', ()=>{
    const wrapper = shallowMount(commentReplyCard, {store, localVue,
      propsData: {
        replyData: {
          creply_time: 0,
          reply_id: 1,
          from_user: 'testFromUser',
          to_user: 'testToUser',
          creply_content: 'testContent'}
      }})
    const toUserContent = wrapper.findAll('b-row').at(2)

    expect(toUserContent.text()).to.include('testToUser')
    expect(toUserContent.text()).to.include('testContent')
  })

})
