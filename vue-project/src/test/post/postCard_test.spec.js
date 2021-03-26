import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import postCard from "@/components/post/postCard";
import VueRouter from "vue-router";
import moment from "moment";

const localVue = createLocalVue();

localVue.use(Vuex, VueRouter, moment);

describe('postCard.vue', () => {
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

  it('show correct post author', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    const postAuthor = wrapper.findAll('b-col').at(1)

    expect(postAuthor.text()).to.include('testAuthor')
  })

  it('show correct post content', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    const postContent = wrapper.findAll('b-card-text').at(0)

    expect(postContent.text()).to.include('testContent')
  })

  it('show correct post like count', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    let postLike = wrapper.findAll('b-badge').at(0)

    wrapper.vm.likeCount = 0
    wrapper.vm.$mount()
    postLike = wrapper.findAll('b-badge').at(0)
    expect(postLike.text()).to.equal('1')

    wrapper.vm.likeCount = 1
    wrapper.vm.$mount()
    postLike = wrapper.findAll('b-badge').at(0)
    expect(postLike.text()).to.equal('2')

    wrapper.vm.likeCount = -1
    wrapper.vm.$mount()
    postLike = wrapper.findAll('b-badge').at(0)
    expect(postLike.text()).to.equal('0')
  })

  it('show correct content count', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    wrapper.vm.comments = ['testComment1', "testComment2"]
    wrapper.vm.$mount()
    const contentCount = wrapper.findAll('b-badge').at(1)
    expect(contentCount.text()).to.equal('2')
  })

  it('show correct comment collapse', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    const commentButton = wrapper.findAll('b-button').at(1)
    wrapper.vm.comments = ['testComment1', "testComment2"]
    wrapper.vm.$mount()

    expect(wrapper.vm.$el.classList.contains('comment-collapse')).to.not.be.ok
    commentButton.trigger('click')
    wrapper.vm.$nextTick(()=>{
      expect(wrapper.vm.$el.classList.contains('comment-collapse')).to.be.ok
    })

    wrapper.vm.comments = []
    wrapper.vm.$mount()
    commentButton.trigger('click')
    wrapper.vm.$nextTick(()=>{
      expect(wrapper.findAll('b-collapse').at(0).text()).to.include('no comment')
    })
  })

  it('show correct like collapse', ()=>{
    const wrapper = shallowMount(postCard, {store, localVue,
      propsData: {
        postContent: {
          post_createtime: 0,
          post_id: 1,
          post_likes: 1,
          post_author: 'testAuthor',
          post_content: 'testContent'}
      }})
    const likeButton = wrapper.findAll('b-list-group-item').at(1)
    let likeHover

    wrapper.vm.likes = ['testLike1', "testLike2"]
    wrapper.vm.$mount()
    expect(wrapper.vm.$el.classList.contains('like-popover')).to.not.be.ok
    likeButton.trigger('hover')
    wrapper.vm.$nextTick(()=>{
      likeHover = wrapper.findAll('b-popover').at(0)
      likeHover.trigger('click')
      wrapper.vm.$nextTick(()=>{
        expect(wrapper.vm.$el.classList.contains('like-collapse')).to.be.ok
      })
    })

    wrapper.vm.likes = []
    wrapper.vm.$mount()
    expect(wrapper.vm.$el.classList.contains('like-popover')).to.not.be.ok
    likeButton.trigger('hover')
    wrapper.vm.$nextTick(()=>{
      likeHover = wrapper.findAll('b-popover').at(0)
      likeHover.trigger('click')
      wrapper.vm.$nextTick(()=>{
        expect(wrapper.vm.$el.classList.contains('like-collapse')).to.be.ok
        expect(wrapper.findAll('b-collapse').at(0)).to.include('Nobody likes')
      })
    })
  })

})
