import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import axios from "axios";
import moduleSideFunctions from "@/components/module/moduleSideFunctions";
import MockAdapter from "axios-mock-adapter";

axios.defaults.baseURL = 'http://localhost:8843/api'

const localVue = createLocalVue()

localVue.use(Vuex)

describe('moduleSideFunctions.vue', () => {
  let getters;
  let store;
  let currentUser = '';

  beforeEach(() => {
    //Construct virtual vuex store
    getters = {
      "loginInfo/getLUName": () => currentUser
    }

    store = new Vuex.Store({
      getters
    })

  })

  // it('get user side bar correctly', done => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const cardCotent = wrapper.findAll('b-card').at(2).text()
  //
  //   mock
  //     .onGet('/getrole', {username: 'testName', module_name: 'testModule'})
  //     .reply(200, 'user')
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.getRole()
  //
  //   wrapper.vm
  //     .$nextTick()
  //     .then(()=>{
  //
  //     })
  //   expect(wrapper.vm.role).to.equal('user')
  //   expect(cardCotent).to.include('Apply')
  //   expect(cardCotent).to.not.include('Quit')
  //   expect(cardCotent).to.not.include('Manage')
  //   done();
  // })

  // it('get admin side bar correctly', done => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const cardCotent = wrapper.findAll('b-card').at(2).text()
  //
  //   mock
  //     .onGet('/getrole', {params:{username: 'testName', module_name: 'testModule'}})
  //     .reply(200, 'admin')
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.getRole()
  //
  //   wrapper.vm.$nextTick(()=>{
  //     expect(wrapper.vm.role).to.equal('admin')
  //     expect(cardCotent).to.include('Apply')
  //     expect(cardCotent).to.include('Quit')
  //     expect(cardCotent).to.include('Manage')
  //   })
  //   done();
  // })

  // it('get points correctly', done => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const point = wrapper.findAll('b-card').at(1).text()
  //
  //   mock
  //     .onPost('/getpoints', null, {username: 'testName', module_name: 'testModule'})
  //     .reply(200, 999)
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.getPoints()
  //
  //   wrapper.vm
  //     .$nextTick()
  //     .then(()=>{
  //       expect(wrapper.vm.points).to.equal(999)
  //       expect(point).to.include('999')
  //     })
  //   done();
  // })

  // it('apply for administrator successfully', async() => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const applyButton = wrapper.findAll('b-button').at(0)
  //   const cardCotent = wrapper.findAll('b-card').at(2).text()
  //
  //   mock
  //     .onPost('/applyadmin', null, {params:{username: 'testName', module_name: 'testModule'}})
  //     .reply(200)
  //
  //   mock
  //     .onGet('/getrole', {params:{username: 'testName', module_name: 'testModule'}})
  //     .reply(200, 'admin')
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.role = 'user'
  //
  //   applyButton.trigger('click')
  //   await wrapper.vm.$nextTick(()=>{
  //     expect(wrapper.vm.role).to.equal('admin')
  //     expect(cardCotent).to.not.include('Apply')
  //     expect(cardCotent).to.include('Quit')
  //     expect(cardCotent).to.include('Manage')
  //   })
  // })

  // it('apply for administrator unsuccessfully', done => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const applyButton = wrapper.findAll('b-button').at(0)
  //   const cardCotent = wrapper.findAll('b-card').at(2).text()
  //
  //   mock
  //     .onPost('/applyadmin', null, {username: 'testName', module_name: 'testModule'})
  //     .reply(400)
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.role = 'user'
  //
  //   applyButton
  //     .trigger('click')
  //     .then(() => {
  //       expect(wrapper.vm.role).to.equal('admin')
  //       expect(cardCotent).to.not.include('Apply')
  //       expect(cardCotent).to.include('Quit')
  //       expect(cardCotent).to.include('Manage')
  //       expect(wrapper.vm.$el.classList.contains('error-toast')).to.be.true
  //     })
  //   done()
  // })

  // it('quit administrator test', done => {
  //   const mock = new MockAdapter(axios)
  //   const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
  //   const quitButton = wrapper.findAll('b-button').at(0)
  //   const cardCotent = wrapper.findAll('b-card').at(2).text()
  //
  //   mock
  //     .onPost('/deladmin', null, {username: 'testName', module_name: 'testModule'})
  //     .reply(200)
  //
  //   mock
  //     .onGet('/getrole', {username: 'testName', module_name: 'testModule'})
  //     .reply(200, 'admin')
  //
  //   wrapper.vm.currentUser = 'testName'
  //   wrapper.vm.moduleName = 'testModule'
  //   wrapper.vm.role = 'admin'
  //
  //   quitButton
  //     .trigger('click')
  //     .then(() => {
  //       expect(wrapper.vm.role).to.equal('admin')
  //       expect(cardCotent).to.include('Apply')
  //       expect(cardCotent).to.not.include('Quit')
  //       expect(cardCotent).to.not.include('Manage')
  //     })
  //   done()
  // })

  it('should show correct point', () => {
    const wrapper = shallowMount(moduleSideFunctions, {store, localVue})

    wrapper.vm.points = 999
    wrapper.vm.$mount()
    const point = wrapper.findAll('b-card').at(1)
    expect(point.text()).to.include('999')
  })

  it('show error when have no enough quality to be administrator', () => {
    const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
    const applyButton = wrapper.findAll('b-card').at(0)

    expect(wrapper.vm.$el.classList.contains('error-toast')).to.be.false
    applyButton.trigger('click')
    wrapper.vm.$nextTick(()=>{
      expect(wrapper.vm.$el.classList.contains('error-toast')).to.be.true
    })
  })
})
