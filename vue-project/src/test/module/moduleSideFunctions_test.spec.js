import {shallowMount, createLocalVue} from "@vue/test-utils";
import {expect} from "chai";
import Vuex from "vuex";
import moduleSideFunctions from "@/components/module/moduleSideFunctions";
import moduleIntroInput from "@/components/module/moduleIntroInput";

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

  it('should show correct point', () => {
    const wrapper = shallowMount(moduleSideFunctions, {store, localVue})
    const point = wrapper.findAll('b-card').at(1)

    wrapper.vm.points = 999

    wrapper.vm.$nextTick(() => {
      expect(point.text()).to.include('999')
    })
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
