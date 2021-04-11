module.exports = {
  'Sticky header routing test' : function(browser){
    browser
      .maximizeWindow()
      .url('http://localhost:8080/')
      // test news button for visitor
      .waitForElementPresent('body')
      .waitForElementPresent('[aria-label=newspaper]')
      .click('[aria-label=newspaper]')
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

      // test message box for visitor
      .click('#message-popover')
      .assert.not.elementPresent('#message-badge')
      .waitForElementVisible('#message-comment-popover')
      .assert.not.elementPresent('#message-comment-badge')
      .click('#message-comment-popover')
      .assert.urlContains('/messages=comment')
      .assert.containsText('#visitorMessageInfo', 'Please login before browsing message box.')

      .click('#message-popover')
      .assert.not.elementPresent('#message-badge')
      .waitForElementVisible('#message-like-popover')
      .assert.not.elementPresent('#message-like-badge')
      .click('#message-like-popover')
      .assert.urlContains('/messages=like')
      .assert.containsText('#visitorMessageInfo', 'Please login before browsing message box.')

      // sign in test
      .click('#navBar-user-popover')
      .assert.visible('#loginButton')
      .assert.visible('#registerButton')
      .click('#loginButton')
      .waitForElementVisible('#loginCard')
      .assert.visible('#input-username')
      .assert.visible('#input-password')
      .setValue('#input-username', 'Mao')
      .setValue('#input-password', 1234)
      .assert.visible('#loginReset')
      .click('#loginReset')
      .assert.value('#input-username', '')
      .assert.value('#input-password', '')
      .setValue('#input-username', 'Mao')
      .setValue('#input-password', 1234)
      .assert.visible('#loginSubmit')
      .click('#loginSubmit')
      .waitForElementVisible('body')
      .assert.containsText('#navBar-user-popover', 'Mao')

      // test news button for user
      .waitForElementPresent('[aria-label=newspaper]')
      .click('[aria-label=newspaper]')
      .waitForElementNotPresent('#lifeEnterButton')
      .assert.urlContains('/news')
      .assert.containsText('#newsTitle', 'New Posts from Following List')
      .getLocationInView('#postCard_9')
      .assert.visible('#postCard_9')
      .getLocationInView('#postCard_3')
      .assert.visible('#postCard_3')

      // test message box for non visitor
      .click('#message-popover')
      .assert.elementPresent('#message-badge')
      .assert.containsText('#message-badge', '4')
      .waitForElementVisible('#message-comment-popover')
      .assert.elementPresent('#message-comment-badge')
      .assert.containsText('#message-comment-badge', '2')
      .click('#message-comment-popover')
      .assert.urlContains('/messages=comment')
      .assert.elementPresent('#mbCommentBadge')
      .assert.containsText('#mbCommentBadge', '1')
      .assert.visible('#commentReplyTab')
      .assert.visible('#postCommentTab')

      .click('#message-popover')
      .waitForElementVisible('#message-like-popover')
      .assert.elementPresent('#message-like-badge')
      .assert.containsText('#message-like-badge', '2')
      .click('#message-like-popover')
      .assert.urlContains('/messages=like')
      .assert.elementPresent('#mbLikeBadge')
      .assert.containsText('#mbLikeBadge', '2')
      .assert.visible('#postLikeTab')
      .assert.visible('#commentLikeTab')

      .click('#mbCommentButton')
      .assert.urlContains('/messages=comment')
      .click('#mbLikeButton')
      .assert.urlContains('/messages=like')

      .end();
  },
}
