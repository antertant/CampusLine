import HelloWorld from "@/components/HelloWorld";
import { shallowMount } from "@vue/test-utils";
import { expect } from "chai";

describe("postCard", () => {
  let component;

  beforeEach(() => {
    component = shallowMount(HelloWorld);
  });

  it("should render Spinner on mount", () => {
    expect(component.find('Hello').exists()).to.be.not.true;
  });
});
