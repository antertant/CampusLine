import { shallowMount } from "@vue/test-utils";
import {expect} from "chai";
import axios from "axios";
import updatePasswordCard from "@/components/manage/updatePasswordCard";
import MockAdapter from "axios-mock-adapter";

describe('updatePasswordCard.vue', () => {

  it('when click reset, flush all input', async () => {
    const wrapper = shallowMount(updatePasswordCard);

    wrapper.vm.newPassword = 123456
    wrapper.vm.resetPassword()

    expect(wrapper.vm.newPassword).to.be.empty
  })
})
