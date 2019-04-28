// import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

// import Login from '@/sign-in/index'
// import Content from '@/page/content'
// import ImportantNews from '@/page/importantNews'
// import StockDailyTransactions from '@/page/stockDailyTransactions'
// import EditMySelectedType from '@/page/editMySelectedType'
// import NewsSettings from '@/page/newsSettings'
// import KCandle from '@/page/kCandle'

const Login = resolve => require(['@/sign-in/index'], resolve)
const Content = resolve => require(['@/page/content'], resolve)
const ImportantNews = resolve => require(['@/page/importantNews'], resolve)
const StockDailyTransactions = resolve => require(['@/page/stockDailyTransactions'], resolve)
const EditMySelectedType = resolve => require(['@/page/editMySelectedType'], resolve)
const NewsSettings = resolve => require(['@/page/newsSettings'], resolve)
const KCandle = resolve => require(['@/page/kCandle'], resolve)



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
