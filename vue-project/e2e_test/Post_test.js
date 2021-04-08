module.exports = {
  'Post actions test': function (browser){
    browser
      .maximizeWindow()
      .url('http://localhost:8080')
      .waitForElementVisible('body')
      .assert.titleContains('CampusLine - Home')

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
      .assert.urlContains('home')
      .assert.containsText('#navBar-user-popover', 'Mao')

    // sending post test
      .assert.visible('#hot-life-input')
      .click('#hot-life-input')
      .frame('my-tinymce-life_ifr', function (result){console.log(result)})
      .assert.visible('#tinymce')
      .setValue('#tinymce', 'This is a E2E testing post.')
      .frameParent(function(result) {console.log(result)})
      .click('#hot-text-submit-life')
      .waitForElementVisible('body')
      .assert.urlContains('life')
      .assert.visible('#life-post-list')
      .assert.containsText('#life-post-list', 'This is a E2E testing post.')

    // delete post test
      .click('#delete-post-button-25')
      .assert.visible('#delete-post-modal-250')
      .click('#delete-post-modal-button-yes')
      .assert.not.containsText('#life-post-list', 'This is a E2E testing post.')

    // liking post test
      .assert.visible('#pc-like-16')
      .assert.containsText('#pc-like-16', '0')
      .click('#pc-like-16')
      .assert.containsText('#pc-like-16', '1')
      .click('#pc-like-16')
      .assert.containsText('#pc-like-16', '0')

    // show comment test
      .assert.visible('#pc-comment-16')
      .click('#pc-comment-16')
      .click('#pc-comment-popover-16')
      .assert.visible('#postComment-16')
      .assert.containsText('#postComment-16', 'There is no comment here yet.')

    // send comment test
      .assert.visible('#comment-modal-16')
      .assert.visible('#comment-input')
      .setValue('#comment-input', 'This is a E2E testing comment.')
      .click('#comment-input-submit')
      .assert.containsText('#postComment-16', 'This is a E2E testing comment.')

    // like comment test
      .click('#pc-comment-12')
      .assert.visible('#comment-like-14')
      .assert.containsText('#comment-like-14', '0')
      .click('#comment-like-14')
      .assert.containsText('#comment-like-14', '1')
      .click('#comment-like-14')
      .assert.containsText('#comment-like-14', '0')

    // reply comment test
      .click('#comment-reply-14')
      .assert.visible('#reply-input')
      .setValue('#reply-input', 'This is a E2E testing reply.')
      .click('#reply-submit')
      .assert.containsText('#postComment-12', 'To @Mao: This is a E2E testing reply.')

    // reply replies test
      .click('#comment-reply-button-1421')
      .assert.visible('#reply-input')
      .setValue('#reply-input', 'This is a E2E testing reply to a reply.')
      .click('#reply-submit')
      .assert.containsText('#postComment-12', 'To @Mao: This is a E2E testing reply to a reply.')

    // delete reply test
      .click('#reply-delete-button-1421')
      .click('#reply-delete-yes-button')
      .assert.not.containsText('#postComment-12', 'To @Mao: This is a E2E testing reply.')

      // show user's own post test
      .click('#headerHome')
      .click('#modules-popover')
      .waitForElementVisible('#modulePopLife')
      .click('#modulePopLife')
      .waitForElementPresent('#lifeHeader')
      .assert.urlContains('/life')

      .click('#lifeHeader')
      .assert.visible('#life-self-button')
      .click('#life-self-button')
      .assert.not.containsText('#life-post-list', 'meng')
      .assert.not.containsText('#life-post-list', 'ppp')
      .click('#life-all-button')
      .assert.containsText('#life-post-list', 'meng')
      .assert.containsText('#life-post-list', 'ppp')

    // subscribe a user when browsing posts
      .click('#avatar-meng')
      .waitForElementVisible('body')
      .assert.urlContains('profile=meng')
      .moveToElement('#profile-avatar', 0, -500)
      .click('#profile-avatar')
      .click('#navBar-user-popover')
      .click('#navBar-user-profile')
      .waitForElementVisible('body')
      .assert.urlContains('profile=Mao')
      .click('#profile-following-button')
      .assert.visible('#profile-following-card')
      .assert.containsText('#profile-following-card', 'meng')

      .end()
  }
}
