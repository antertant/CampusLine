import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import axios from "axios";
import moduleIntroInput from "@/components/module/moduleIntroInput";
import MockAdapter from "axios-mock-adapter";

const localVue = createLocalVue()

localVue.use(Vuex)

describe('moduleIntroInput.vue', () => {
  let getters;
  let store;
  let moduleList = [];

  beforeEach(() => {
    //Construct virtual vuex store
    getters = {
      'moduleList/getModuleList': () => moduleList
    }

    store = new Vuex.Store({
      getters
    })

  })

  it('reset button flushes all input', () => {
    const wrapper = shallowMount(moduleIntroInput, {store, localVue})
    const resetButton = wrapper.findAll('b-button').at(0)

    wrapper.vm.introContent = 'test'

    resetButton.trigger('click')
    wrapper.vm.$nextTick(() => {
      expect(wrapper.vm.introContent).to.be.empty
    })
  })

  // it('submit button gives correct response', done => {
  //   const mock = new MockAdapter(axios);
  //   const wrapper = shallowMount(moduleIntroInput, {store, localVue});
  //   const postButton = wrapper.findAll('b-button').at(1);
  //
  //   wrapper.vm.moduleName = 'testModule';
  //   wrapper.vm.introContent = 'testIntro';
  //
  //   mock.onAny('/api/editintro').reply(200);
  //
  //   postButton
  //     .trigger('click')
  //     .then(() => {
  //       expect(wrapper.vm.moduleName).to.be.empty
  //       expect(wrapper.vm.introContent).to.be.empty
  //     })
  //   done();
  // })

  it('should show correct module intro', () => {
    const wrapper = shallowMount(moduleIntroInput, {store, localVue})
    const intro = wrapper.findAll('b-card-text').at(0)

    wrapper.vm.moduleIntro = 'testIntro'

    wrapper.vm.$nextTick(() => {
      expect(intro.text()).to.include('testIntro')
    })
  })

  it('should show correct module name in title', () => {
    const wrapper = shallowMount(moduleIntroInput, {store, localVue})
    const title = wrapper.findAll('b-card-header').at(0)

    wrapper.vm.moduleName = 'testName'

    wrapper.vm.$nextTick(() => {
      expect(title.text()).to.include('testName')
    })
  })
})
