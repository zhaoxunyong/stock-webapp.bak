import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/page/index'
import Content from '@/page/content'
import ImportantNews from '@/page/importantNews'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  mode: 'hash',
  routes: [
    {
      path: '/',
      component: Index
    }, 
    {
      path: '/content/:id',
      component: Content
    }, 
    {
      path: '/importantNews',
      component: ImportantNews
    }
  ]
})