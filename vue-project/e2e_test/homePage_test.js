module.exports = {
  'Correctly change hot posts tabs and enter the corresponding module page' : function(browser) {
    browser
      .maximizeWindow()
      .url('http://localhost:8080/')
      .waitForElementVisible('body')
      .assert.titleContains('Asuka - Home')

    // sign in
      .click('#navBar-user-popover')
      .assert.visible('#loginButton')
      .assert.visible('#registerButton')
      .click('#loginButton')
      .waitForElementVisible('#loginCard')
      .assert.value('#input-username', '')
      .assert.value('#input-password', '')
      .setValue('#input-username', 'Mao')
      .setValue('#input-password', 1234)
      .assert.visible('#loginSubmit')
      .click('#loginSubmit')
      .waitForElementVisible('body')
      .assert.containsText('#navBar-user-popover', 'Mao')

    // life hot card test
      .waitForElementVisible('#lifeEnterButton')
      .assert.containsText('#lifeEnterButton', 'LIFE')
      .click('#lifeEnterButton')
      .waitForElementNotPresent('#lifeEnterButton')
      .assert.urlContains('life')
      .assert.titleContains('Asuka - Life Circle')
      .click('#headerHome')
      .waitForElementVisible('body')
      .assert.titleContains('Asuka - Home')

    // module hot card test
      .waitForElementVisible('#module-tab-java___BV_tab_button__')
      .click('#module-tab-java___BV_tab_button__')
      .assert.containsText('#hot-card-input-java', 'Write your own post on <JAVA>')
      .waitForElementVisible('#module-tab-springboot___BV_tab_button__')
      .click('#module-tab-springboot___BV_tab_button__')
      .assert.containsText('#hot-card-input-springboot', 'Write your own post on <SPRINGBOOT>')
      .waitForElementVisible('#module-tab-vue___BV_tab_button__')
      .click('#module-tab-vue___BV_tab_button__')
      .assert.containsText('#hot-card-input-vue', 'Write your own post on <VUE>')
      .waitForElementVisible('#moduleEnterButton')
      .assert.containsText('#moduleEnterButton', 'module')
      .click('#moduleEnterButton')
      .waitForElementNotPresent('#moduleEnterButton')
      .assert.urlContains('module-modules')

      .end();
  }
};
