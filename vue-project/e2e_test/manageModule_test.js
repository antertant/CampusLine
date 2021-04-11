module.exports = {
  'Managing module test': function (browser){
    browser
      .maximizeWindow()
      .url('http://localhost:8080')
      .waitForElementVisible('body')
      .assert.titleContains('CampusLine - Home')

    // sign in
      .click('#navBar-user-popover')
      .assert.visible('#loginButton')
      .assert.visible('#registerButton')
      .click('#loginButton')
      .assert.value('#input-username', '')
      .assert.value('#input-password', '')
      .setValue('#input-username', 'meng')
      .setValue('#input-password', 123)
      .assert.visible('#loginSubmit')
      .click('#loginSubmit')
      .waitForElementVisible('body')
      .assert.urlContains('home')
      .assert.containsText('#navBar-user-popover', 'meng')

    // enter the life module's manager page
      .click('#lifeEnterButton')
      .waitForElementVisible('body')
      .assert.urlContains('life')
      .assert.visible('#manager-quit-life')
      .assert.visible('#manager-manage-life')
      .click('#manager-manage-life')
      .waitForElementVisible('#module-intro-input-life')

    // pin post test
      .click('#pin-button-15')
      .waitForElementVisible('#to-top-15')
      .click('#pin-confirm-button')
      .assert.visible('#pin-button-15')
      .click('#unpin-button-12')
      .waitForElementVisible('#cancel-top-12')
      .click('#unpin-confirm-button')
      .assert.visible('#pin-button-12')
      .click('#pin-button-12')
      .waitForElementVisible('#to-top-12')
      .click('#pin-confirm-button')
      .assert.visible('#unpin-button-12')

    // delete normal user's post
      .assert.visible('#postCard_12')
      .click('#delete-post-button-12')
      .click('#delete-post-modal-button-yes')
      .waitForElementNotPresent('#postCard_12')

    // log out the life manager account & log in with springboot manager account
      .click('#navBar-user-popover')
      .click('#navBar-user-logout')
      .assert.containsText('#navBar-user-popover', 'Visitor')
      .click('#navBar-user-popover')
      .click('#loginButton')
      .setValue('#input-username', 'gang')
      .setValue('#input-password', 123)
      .assert.visible('#loginSubmit')
      .click('#loginSubmit')
      .waitForElementVisible('body')
      .assert.urlContains('home')
      .assert.containsText('#navBar-user-popover', 'gang')

    // enter the springboot module's manager page
      .waitForElementVisible('body')
      .click('#modules-popover')
      .waitForElementVisible('#modulePopKnow')
      .click('#modulePopKnow')
      .waitForElementPresent('#springboot_block')
      .assert.urlContains('/knowledge-modules')
      .click('#springboot_button')
      .waitForElementVisible('#module-title-springboot')
      .assert.urlContains('springboot')
      .click('#manager-manage-springboot')
      .waitForElementVisible('#module-intro-input-springboot')

    // edit springboot module introduction
      .waitForElementVisible('#module-intro-input-springboot')
      .assert.containsText('#module-intro-input-springboot',
      'Spring Boot offers a fast way to build applications. ' +
      'It looks at your classpath and at the beans you have configured, ' +
      'makes reasonable assumptions about what you are missing, and adds those items. ' +
      'With Spring Boot, you can focus more on business features and less on ' +
      'infrastructure.')
      .click('#module-intro-input-button-reset')
      .assert.not.containsText('#module-intro-input-textarea-springboot',
      'Spring Boot offers a fast way to build applications. ' +
      'It looks at your classpath and at the beans you have configured, ' +
      'makes reasonable assumptions about what you are missing, and adds those items. ' +
      'With Spring Boot, you can focus more on business features and less on ' +
      'infrastructure.')
      .setValue('#module-intro-input-textarea-springboot',
        'This is an E2E tesing introduction.')
      .click('#module-intro-input-button-post')
      .waitForElementVisible('#module-intro-input-springboot')
      .assert.containsText('#module-intro-input-springboot',
      'This is an E2E tesing introduction.')

      .end();
  }
}
