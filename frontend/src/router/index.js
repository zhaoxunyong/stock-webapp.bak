import Vue from 'vue'
import Router from 'vue-router'
import Content from '@/page/content'
import ImportantNews from '@/page/importantNews'
import StockDailyTransactions from '@/page/stockDailyTransactions'
import EditMySelectedType from '@/page/editMySelectedType'
import NewsSettings from '@/page/newsSettings'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  mode: 'hash',
  routes: [
    {
      path: '/',
      component: Content
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
    }, 
    {
      path: '/stockDailyTransactions',
      component: StockDailyTransactions
    }, 
    {
      path: '/editMySelectedType',
      component: EditMySelectedType
    }, 
    {
      path: '/newsSettings',
      component: NewsSettings
    }
  ]
})