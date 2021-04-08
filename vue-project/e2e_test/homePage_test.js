// module.exports = {
//   'Correctly change hot posts tabs and enter the corresponding module page' : function(browser) {
//     browser
//       .url('http://localhost:8080/')
//       .waitForElementVisible('body')
//       .assert.titleContains('CampusLine - Home')
//       // test life tab
//       .waitForElementVisible('#lifeEnterButton')
//       .assert.visible('#lifeEnterButton')
//       .assert.containsText('#lifeEnterButton', 'LIFE')
//       .click('#lifeEnterButton')
//       .waitForElementNotPresent('#lifeEnterButton')
//       .assert.urlContains('life')
//       .assert.titleContains('CampusLine - Life Circle')
//       .click('#headerHome')
//
//       .assert.titleContains('CampusLine - Home')
//       // test life tab
//       .waitForElementVisible('#knowledgeEnterButton')
//       .assert.visible('#knowledgeEnterButton')
//       .assert.containsText('#knowledgeEnterButton', 'KNOWLEDGE')
//       .click('#knowledgeEnterButton')
//       .waitForElementNotPresent('#knowledgeEnterButton')
//       .assert.urlContains('knowledge-modules')
//
//       .end();
//   },
//
//   'Sticky header routing test' : function(browser){
//     browser
//       .url('http://localhost:8080/')
//       // test news button for visitor
//       .waitForElementPresent('body')
//       .waitForElementPresent('[aria-label=newspaper]')
//       .click('[aria-label=newspaper]')
//       .waitForElementNotPresent('#lifeEnterButton')
//       .assert.urlContains('/login')
//
//       // test modules popover -- life
//       .click('#modules-popover')
//       .waitForElementVisible('#modulePopLife')
//       .click('#modulePopLife')
//       .waitForElementPresent('#lifeHeader')
//       .assert.urlContains('/life')
//
//       // test modules popover -- knowledge modules
//       .click('#modules-popover')
//       .waitForElementVisible('#modulePopKnow')
//       .click('#modulePopKnow')
//       .waitForElementPresent('#java_block')
//       .assert.urlContains('/knowledge-modules')
//
//       // test java module enter button
//       .click('#java_button')
//       .waitForElementNotPresent('#java_block')
//       .assert.urlContains('/knowledge-modules/java')
//
//       // test message box for visitor
//       .click('#message-popover')
//       .assert.not.elementPresent('#message-badge')
//       .waitForElementVisible('#message-comment-popover')
//       .assert.not.elementPresent('#message-comment-badge')
//       .click('#message-comment-popover')
//       .assert.urlContains('/messages=comment')
//       .assert.containsText('#visitorMessageInfo', 'Please login before browsing message box.')
//
//       .click('#message-popover')
//       .assert.not.elementPresent('#message-badge')
//       .waitForElementVisible('#message-like-popover')
//       .assert.not.elementPresent('#message-like-badge')
//       .click('#message-like-popover')
//       .assert.urlContains('/messages=like')
//       .assert.containsText('#visitorMessageInfo', 'Please login before browsing message box.')
//
//       // sign in test
//       .click('#navBar-user-popover')
//       .assert.visible('#loginButton')
//       .assert.visible('#registerButton')
//       .click('#loginButton')
//       .waitForElementVisible('#loginCard')
//       .assert.visible('#input-username')
//       .assert.visible('#input-password')
//       .setValue('#input-username', 'Mao')
//       .setValue('#input-password', 1234)
//       .assert.visible('#loginReset')
//       .click('#loginReset')
//       .assert.value('#input-username', '')
//       .assert.value('#input-password', '')
//       .setValue('#input-username', 'Mao')
//       .setValue('#input-password', 1234)
//       .assert.visible('#loginSubmit')
//       .click('#loginSubmit')
//       .waitForElementVisible('body')
//       .assert.containsText('#navBar-user-popover', 'Mao')
//
//       // test news button for user
//       .waitForElementPresent('[aria-label=newspaper]')
//       .click('[aria-label=newspaper]')
//       .waitForElementNotPresent('#lifeEnterButton')
//       .assert.urlContains('/news')
//       .assert.containsText('#newsTitle', 'New Posts from Following List')
//       .assert.visible('#postCard_12')
//       .assert.visible('#postCard_9')
//       .assert.visible('#postCard_3')
//
//       // test message box for non visitor
//       .click('#message-popover')
//       .assert.elementPresent('#message-badge')
//       .assert.containsText('#message-badge', '5')
//       .waitForElementVisible('#message-comment-popover')
//       .assert.elementPresent('#message-comment-badge')
//       .assert.containsText('#message-comment-badge', '2')
//       .click('#message-comment-popover')
//       .assert.urlContains('/messages=comment')
//       .assert.elementPresent('#mbCommentBadge')
//       .assert.containsText('#mbCommentBadge', '2')
//       .assert.visible('#commentReplyTab')
//       .assert.visible('#postCommentTab')
//       .click('#commentReplyTab')
//       .assert.not.elementPresent('#commentReplyBadge')
//
//       .click('#message-popover')
//       .waitForElementVisible('#message-like-popover')
//       .assert.elementPresent('#message-like-badge')
//       .assert.containsText('#message-like-badge', '3')
//       .click('#message-like-popover')
//       .assert.urlContains('/messages=like')
//       .assert.elementPresent('#mbLikeBadge')
//       .assert.containsText('#mbLikeBadge', '3')
//       .assert.visible('#postLikeTab')
//       .assert.visible('#commentLikeTab')
//
//       .click('#mbCommentButton')
//       .assert.urlContains('/messages=comment')
//       .click('#mbLikeButton')
//       .assert.urlContains('/messages=like')
//
//       .end();
//   },
//
//   ' Search function test ': function (browser) {
//     browser
//       .url('http://localhost:8080')
//       .waitForElementVisible('body')
//
//       // test searching modules
//       .waitForElementVisible('#searchInput')
//       .assert.value('#searchSelect', 'searchmodule')
//       .setValue('#searchInput', 'java')
//       .click('#searchButton')
//       .waitForElementVisible('#searchModBody')
//       .assert.containsText('#searchModBody', 'Get 1 result from searching "java" in Modules ...')
//       .assert.visible('#java_block')
//
//       .setValue('#searchInput', 'v')
//       .click('#searchButton')
//       .waitForElementVisible('#searchModBody')
//       .assert.containsText('#searchModBody', 'Get 2 results from searching "v" in Modules ...')
//       .assert.visible('#java_block')
//       .assert.visible('#vue_block')
//
//       // test searching posts
//       .setValue('#searchInput', 'test')
//       .assert.visible('#searchSelect')
//       .click('#searchSelect')
//       .assert.visible('#selectPost')
//       .click('#selectPost')
//       .click('#searchButton')
//       .waitForElementVisible('#searchPostBody')
//       .assert.containsText('#searchPostBody', 'Get 2 results from searching "test" in Posts ...')
//
//       .setValue('#searchInput', 'is')
//       .assert.visible('#searchSelect')
//       .click('#searchSelect')
//       .assert.visible('#selectPost')
//       .click('#selectPost')
//       .click('#searchButton')
//       .waitForElementVisible('#searchPostBody')
//       .assert.containsText('#searchPostBody', 'Get 3 results from searching "is" in Posts ...')
//
//       .end();
//   }
// };
