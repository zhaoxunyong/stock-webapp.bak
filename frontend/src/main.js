// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import BootstrapVue from 'bootstrap-vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"

import "open-iconic/font/css/open-iconic-bootstrap.min.css"

// import BootstrapDirectionalButtons from 'bootstrap-directional-buttons'
// import "bootstrap-directional-buttons/dist/bootstrap-directional-buttons.css"

Vue.use(BootstrapVue)

import Confirm from 'vue-confirm'
Vue.use(Confirm)

// import VueAlert from '@vuejs-pt/vue-alert'
// Vue.use(VueAlert)

// import Msg from 'vue-message'
import Msg from './components/messagebox/index'
Vue.use(Msg, {
  position: 'top', duration: 999999999
})

// 引用API文件
import api from './api/index.js'
// 将API方法绑定到全局
Vue.prototype.$api = api

// 引用工具文件
import utils from './utils/index.js'

// 引用alert文件, 使用时：alerts.error('xxx')
import * as alerts from './utils/alert.js'
Vue.prototype.$alerts = alerts

// 将工具方法绑定到全局
Vue.prototype.$utils = utils

Vue.config.productionTip = false

import './utils/constant.js'

import axios from 'axios'
// Vue.prototype.$http = axios

/* eslint-disable no-new */

// vue echarts
// import ECharts from 'vue-echarts'
// Vue.component('chart', ECharts)

import echarts from 'echarts'
Vue.prototype.$echarts = echarts

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
