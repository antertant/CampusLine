import {createLocalVue, mount, shallowMount} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import VueRouter from "vue-router";
import commentCard from "@/components/post/commentCard";
import moment from "moment";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment, axios);

const axiosInstance = axios.create()

describe('commentCard.vue', () =>{
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

  it('show correct like count', () => {
    const wrapper = mount(commentCard, {store, localVue,
      propsData: {
        commentData: {
          post_createtime: 0,
          comment_time: 0,
          comment_id: 1,
          comment_user: 'testUser',
          comment_likes: 1}
      }})
    let likeCount = wrapper.findAll('b-badge').at(0).text()

    expect(likeCount).to.equal('1')

    wrapper.vm.likeCount = 1
    wrapper.vm.$mount()

    likeCount = wrapper.findAll('b-badge').at(0).text()
    expect(likeCount).to.equal('2')

    wrapper.vm.likeCount = -1
    wrapper.vm.$mount()

    likeCount = wrapper.findAll('b-badge').at(0).text()
    expect(likeCount).to.equal('0')

  })

  it('show correct user name', ()=>{
    const wrapper = mount(commentCard, {store, localVue,
      propsData: {
        commentData: {
          post_createtime: 0,
          comment_id: 1,
          comment_user: 'testUser',
          comment_likes: 1}
      }})
    let commentUser = wrapper.findAll('b-col').at(2).text()

    expect(commentUser).to.include('testUser')
  })

  it('show correct comment time', ()=>{
    const wrapper = mount(commentCard, {store, localVue,
      propsData: {
        commentData: {
          comment_content: 'testContent',
          comment_id: 1,
          comment_user: 'testUser',
          comment_likes: 1}
      }})
    let commentTime = wrapper.findAll('b-row').at(2).text()

    expect(commentTime).to.include('testContent')
  })

  // it('like comment successfully', async() => {
  //   const wrapper = shallowMount(commentCard, {store, localVue,
  //     propsData: {
  //       commentData: {
  //         post_createtime: 0,
  //         comment_id: 1,
  //         comment_user: 'testUser',
  //         comment_likes: 1}
  //     }});
  //   const mock = new MockAdapter(axiosInstance)
  //   const likeButton = wrapper.findAll('b-button').at(1)
  //   let likeCount = wrapper.findAll('b-badge').at(0).text()
  //
  //   // '/likecomment', null, {params:{
  //   //     comment_id: 1,
  //   //     clike_user: 'testUser'
  //   //   }
  //   // }
  //   mock.onAny()
  //     .reply(200, 'like this comment successfully')
  //
  //   wrapper.vm.likeCount = 0
  //   await likeButton.trigger('click')
  //   expect(wrapper.vm.likePress).to.be.true
  //
  //   likeCount = wrapper.findAll('b-badge').at(0).text()
  //   expect(likeCount).to.equal('2')
  // })

})
