// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import {BootstrapVue, BootstrapVueIcons} from "bootstrap-vue";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Use bootstrap-vue
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

// Render axios, and set basic URL for back-end
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8843/api'
Vue.config.productionTip = false

Vue.prototype.$axios = axios
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
