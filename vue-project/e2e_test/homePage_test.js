module.exports = {
  'Correctly change hot posts tabs and enter the corresponding module page' : function(browser) {
    browser
      .maximizeWindow()
      .url('http://localhost:8080/')
      .waitForElementVisible('body')
      .assert.titleContains('CampusLine - Home')

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
      .assert.titleContains('CampusLine - Life Circle')
      .click('#headerHome')
      .waitForElementVisible('body')
      .assert.titleContains('CampusLine - Home')

    // knowledge hot card test
      .waitForElementVisible('#knowledge-tab-java___BV_tab_button__')
      .click('#knowledge-tab-java___BV_tab_button__')
      .assert.containsText('#hot-card-input-java', 'Write your own post on <JAVA>')
      .waitForElementVisible('#knowledge-tab-springboot___BV_tab_button__')
      .click('#knowledge-tab-springboot___BV_tab_button__')
      .assert.containsText('#hot-card-input-springboot', 'Write your own post on <SPRINGBOOT>')
      .waitForElementVisible('#knowledge-tab-vue___BV_tab_button__')
      .click('#knowledge-tab-vue___BV_tab_button__')
      .assert.containsText('#hot-card-input-vue', 'Write your own post on <VUE>')
      .waitForElementVisible('#knowledgeEnterButton')
      .assert.containsText('#knowledgeEnterButton', 'KNOWLEDGE')
      .click('#knowledgeEnterButton')
      .waitForElementNotPresent('#knowledgeEnterButton')
      .assert.urlContains('knowledge-modules')

      .end();
  }
};
