import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import BlogLogin from "../components/manage/BlogLogin";
import BlogIndex from "../components/home/BlogIndex";
import homePage from "../view/homePage";
import loginPage from "../view/loginPage";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect:'/home'
    },
    {
      path: '/home',
      name: 'homePage',
      component: homePage
    },
    {
      path:'/login',
      name: 'loginPage',
      component: loginPage
    },
    {
      path:'/index',
      name: 'BlogIndex',
      component: BlogIndex
    },
    {
      path:'/manage',
      redirect:'/login'
    }
  ]
})
