import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/sign-in/index'
import Content from '@/page/content'
import ImportantNews from '@/page/importantNews'
import StockDailyTransactions from '@/page/stockDailyTransactions'
import EditMySelectedType from '@/page/editMySelectedType'
import NewsSettings from '@/page/newsSettings'
import KCandle from '@/page/kCandle'
// import KWeek from '@/page/kWeek'
/* import RsiDay from '@/page/rsiDay'
import RsiWeek from '@/page/rsiWeek'
import DmiDay from '@/page/dmiDay'
import DmiWeek from '@/page/dmiWeek'
import MacdDay from '@/page/macdDay'
import MacdWeek from '@/page/macdWeek' */

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
      path: '/login',
      component: Login
    },
    {
      path: '/index',
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
    },
    {
      path: '/kCandle',
      component: KCandle
    },
    /*{
      path: '/kweek',
      component: KWeek
    } ,
    {
      path: '/rsiday',
      component: RsiDay
    },
    {
      path: '/rsiweek',
      component: RsiWeek
    },
    {
      path: '/dmiday',
      component: DmiDay
    },
    {
      path: '/dmiweek',
      component: DmiWeek
    },
    {
      path: '/macdday',
      component: MacdDay
    },
    {
      path: '/macdweek',
      component: MacdWeek
    } */
  ]
})
