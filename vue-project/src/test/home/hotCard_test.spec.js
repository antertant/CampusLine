import hotCard from "@/components/home/hotCard";
import {shallowMount} from "@vue/test-utils";
import {expect} from 'chai'

describe('hotCard.vue', ()=>{

  it('correct moduleName value', ()=>{
    const wrapper = shallowMount(hotCard, {propsData:{hotData: {module_name: 'life'}}})
    let moduleName = wrapper.vm.moduleName
    expect(moduleName).to.equal('LIFE CIRCLE')

    wrapper.vm.hotData.module_name = 'test'
    moduleName = wrapper.vm.moduleName
    expect(moduleName).to.equal('TEST')
  })

  })
