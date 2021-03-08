module.exports = {
  'Correctly change hot posts tabs and enter the corresponding module page' : function(browser) {
    browser
      .url('http://localhost:8080/')
      .waitForElementVisible('body')
      .assert.titleContains('UWSK - Home')
      // test life tab
      .waitForElementVisible('#lifeEnterButton')
      .assert.visible('#lifeEnterButton')
      .assert.containsText('#lifeEnterButton', 'LIFE')
      .assert.visible('[aria-controls=tab_life]')
      .click('#lifeEnterButton')
      .waitForElementNotPresent('#lifeEnterButton')
      .assert.urlContains('life')
      .click('#headerHome')

      // test java tab
      .click('[aria-controls=tab_java]')
      .waitForElementVisible('#tab_java')
      .waitForElementVisible('#javaEnterButton')
      .assert.containsText('#javaEnterButton', 'JAVA')
      .click('#javaEnterButton')
      .waitForElementNotPresent('#javaEnterButton')
      .assert.urlContains('/knowledge-modules/java')
      .click('#headerHome')

      // test springboot tab
      .click('[aria-controls=tab_springboot]')
      .waitForElementVisible('#tab_springboot')
      .waitForElementVisible('#springbootEnterButton')
      .assert.containsText('#springbootEnterButton', 'SPRINGBOOT')
      .click('#springbootEnterButton')
      .waitForElementNotPresent('#springbootEnterButton')
      .assert.urlContains('/knowledge-modules/springboot')
      .click('#headerHome')

      // test vue tab
      .click('[aria-controls=tab_vue]')
      .waitForElementVisible('#tab_vue')
      .waitForElementVisible('#vueEnterButton')
      .assert.containsText('#vueEnterButton', 'VUE')
      .click('#vueEnterButton')
      .waitForElementNotPresent('#vueEnterButton')
      .assert.urlContains('/knowledge-modules/vue')
      .click('#headerHome')

      .end();
  },

  'Sticky header routing test without sign in' : function(browser){
    browser
      .url('http://localhost:8080/')
      // test news button for visitor
      .waitForElementPresent('body')
      .waitForElementPresent('[aria-label=newspaper]')
      .click('[aria-label=newspaper]')
      .waitForElementNotPresent('#lifeEnterButton')
      .assert.urlContains('/login')

      // test modules popover -- life
      .click('#modules-popover')
      .waitForElementVisible('#modulePopLife')
      .click('#modulePopLife')
      .waitForElementPresent('#lifeHeader')
      .assert.urlContains('/life')

      // test modules popover -- knowledge modules
      .click('#modules-popover')
      .waitForElementVisible('#modulePopKnow')
      .click('#modulePopKnow')
      .waitForElementPresent('#java_block')
      .assert.urlContains('/knowledge-modules')

      // test java module enter button
      .click('#java_button')
      .waitForElementNotPresent('#java_block')
      .assert.urlContains('/knowledge-modules/java')
      .click('#modules-popover')
      .waitForElementVisible('#modulePopKnow')
      .click('#modulePopKnow')

      // test springboot module enter button
      .waitForElementPresent('#springboot_block')
      .click('#springboot_button')
      .waitForElementNotPresent('#springboot_block')
      .assert.urlContains('/knowledge-modules/springboot')
      .click('#modules-popover')
      .waitForElementVisible('#modulePopKnow')
      .click('#modulePopKnow')

      // test vue module enter button
      .waitForElementPresent('#vue_block')
      .click('#vue_button')
      .waitForElementNotPresent('#vue_block')
      .assert.urlContains('/knowledge-modules/vue')

  }
};
