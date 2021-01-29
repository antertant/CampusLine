import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import BlogLogin from "../components/manage/BlogLogin";
import BlogIndex from "../components/home/BlogIndex";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'/login'
    },
    {
      path:'/login',
      name: 'BlogLogin',
      component: BlogLogin
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
