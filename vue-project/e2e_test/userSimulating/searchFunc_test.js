module.exports = {
  ' Search function test ': function (browser) {
    browser
      .maximizeWindow()
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
      .assert.containsText('#searchPostBody', 'Get 2 results from searching "test" in Posts ...')

      .setValue('#searchInput', 'is')
      .assert.visible('#searchSelect')
      .click('#searchSelect')
      .assert.visible('#selectPost')
      .click('#selectPost')
      .click('#searchButton')
      .waitForElementVisible('#searchPostBody')
      .assert.containsText('#searchPostBody', 'Get 3 results from searching "is" in Posts ...')

      .end();
  }
}
