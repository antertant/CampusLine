import Vue from 'vue'
import Router from 'vue-router'
import homePage from "../view/homePage";
import loginPage from "../view/loginPage";
import modulesPage from "../view/modulesIndexPage";
import pageNotFound from "../view/pageNotFound";
import lifeCirclePage from "../view/lifeCirclePage";
import modulesContentPage from "../view/modulesContentPage";
import searchModulePage from "@/view/searchModulePage";
import searchPostPage from "@/view/searchPostPage";
import moduleManagePage from "@/view/moduleManagePage";
import registrationPage from "@/view/registrationPage";
import profilePage from "@/view/profilePage";
import messagePage from "@/view/messagePage";

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
      path: '/register',
      name: 'registerPage',
      component: registrationPage
    },
    {
      path: '/knowledge-modules',
      name: 'modulesPage',
      component: modulesPage
    },
    {
      path: '/life',
      name: 'lifeCirclePage',
      component: lifeCirclePage
    },
    {
      path: '/knowledge-modules/:modName',
      name: 'modulesContentPage',
      component: modulesContentPage,
      props: true
    },
    {
      path: '/module_search=:searchKey',
      name: 'searchModulePage',
      component: searchModulePage,
      props: true
    },
    {
      path: '/post_search=:searchKey',
      name: 'searchPostPage',
      component: searchPostPage,
      props:true
    },
    {
      path: '/module_management=:modName',
      name: 'moduleManagePage',
      component: moduleManagePage,
      props: true
    },
    {
      path: '/profile=:profileUser',
      name: 'profilePage',
      component: profilePage,
      props: true
    },
    {
      path: '/messages=:mType',
      name: 'messagePage',
      component: messagePage,
      props: true
    },
    {
      path:'*',
      name: 'pageNotFound',
      component: pageNotFound
    }
  ]
})
