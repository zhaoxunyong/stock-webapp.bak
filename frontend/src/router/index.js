import Vue from 'vue'
import Router from 'vue-router'
import StockData from '@/page/stockdata'
import Content from '@/page/content'
import ImportantNews from '@/page/importantNews'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  mode: 'hash',
  routes: [
    {
      path: '/',
      component: StockData
    }, 
    {
      path: '/stockData',
      component: StockData
    }, 
    {
      path: '/content/:stockId',
      component: Content,
      children: [
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:stockId/:pageNum is matched
        { path: ':pageNum', component: Content }
      ]
    }, 
    {
      path: '/importantNews/:pageNum',
      component: ImportantNews
    }
  ]
})