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

  'Sticky header routing test' : function(browser){
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
      .assert.visible('#postCard_12')
      .assert.visible('#postCard_9')
      .assert.visible('#postCard_3')

      // test message box for visitor
      .click('#message-popover')
      .assert.elementPresent('#message-badge')
      .assert.containsText('#message-badge', '3')
      .waitForElementVisible('#message-comment-popover')
      .assert.elementPresent('#message-comment-badge')
      .assert.containsText('#message-comment-badge', '3')
      .click('#message-comment-popover')
      .assert.urlContains('/messages=comment')
      .assert.elementPresent('#mbCommentBadge')
      .assert.containsText('#mbCommentBadge', '3')
      .assert.visible('#commentReplyTab')
      .assert.visible('#postCommentTab')
      .click('#commentReplyTab')
      .assert.not.elementPresent('#commentReplyBadge')

      .click('#message-popover')
      .waitForElementVisible('#message-like-popover')
      .assert.not.elementPresent('#message-like-badge')
      .click('#message-like-popover')
      .assert.urlContains('/messages=like')
      .assert.visible('#postLikeTab')
      .assert.visible('#commentLikeTab')

      .click('#mbCommentButton')
      .assert.urlContains('/messages=comment')
      .click('#mbLikeButton')
      .assert.urlContains('/messages=like')

      .end();
  },

  ' Search function test ': function (browser) {
    browser
      .url('http://localhost:8080')
      .waitForElementVisible('body')

      // test searching modules
      .waitForElementVisible('#searchInput')
      .assert.value('#searchSelect', 'searchmodule')
      .setValue('#searchInput', 'java')
      .click('#searchButton')
      .waitForElementVisible('#searchModBody')
      .assert.containsText('#searchModBody', 'Get 1 result from searching "java" in Modules ...')
      .assert.visible('#java_block')

      .setValue('#searchInput', 'v')
      .click('#searchButton')
      .waitForElementVisible('#searchModBody')
      .assert.containsText('#searchModBody', 'Get 2 results from searching "v" in Modules ...')
      .assert.visible('#java_block')
      .assert.visible('#vue_block')

      // test searching posts
      .setValue('#searchInput', 'test')
      .assert.visible('#searchSelect')
      .click('#searchSelect')
      .assert.visible('#selectPost')
      .click('#selectPost')
      .click('#searchButton')
      .waitForElementVisible('#searchPostBody')
      .assert.containsText('#searchPostBody', 'Get 1 result from searching "test" in Posts ...')
      .assert.visible('#postCard_4')

      .setValue('#searchInput', 'is')
      .assert.visible('#searchSelect')
      .click('#searchSelect')
      .assert.visible('#selectPost')
      .click('#selectPost')
      .click('#searchButton')
      .waitForElementVisible('#searchPostBody')
      .assert.containsText('#searchPostBody', 'Get 3 results from searching "is" in Posts ...')
      .assert.visible('#postCard_11')
      .assert.visible('#postCard_6')
      .assert.visible('#postCard_2')

      .end();
  }
};
