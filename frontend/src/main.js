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

// 将工具方法绑定到全局
Vue.prototype.$utils = utils

Vue.config.productionTip = false

import './utils/constant.js'

import axios from 'axios'
// Vue.prototype.$http = axios

/* eslint-disable no-new */

import ECharts from 'vue-echarts'
Vue.component('chart', ECharts)
/*import ECharts from 'vue-echarts/components/ECharts.vue'
// 手动引入 ECharts 各模块来减小打包体积
import 'echarts/lib/component/dataset'
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/bar'
import 'echarts/lib/chart/pie'
import 'echarts/lib/chart/scatter'
import 'echarts/lib/chart/radar'
import 'echarts/lib/chart/map'
import 'echarts/lib/chart/tree'
import 'echarts/lib/chart/treemap'
import 'echarts/lib/chart/graph'
import 'echarts/lib/chart/gauge'
import 'echarts/lib/chart/funnel'
import 'echarts/lib/chart/parallel'
import 'echarts/lib/chart/sankey'
import 'echarts/lib/chart/boxplot'
import 'echarts/lib/chart/candlestick'
import 'echarts/lib/chart/effectScatter'
import 'echarts/lib/chart/lines'
import 'echarts/lib/chart/heatmap'
import 'echarts/lib/chart/pictorialBar'
import 'echarts/lib/chart/themeRiver'
import 'echarts/lib/chart/sunburst'
import 'echarts/lib/chart/custom'
import 'echarts/lib/component/graphic'
import 'echarts/lib/component/grid'
import 'echarts/lib/component/legendScroll'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/axisPointer'
import 'echarts/lib/component/polar'
import 'echarts/lib/component/geo'
import 'echarts/lib/component/parallel'
import 'echarts/lib/component/singleAxis'
import 'echarts/lib/component/brush'
import 'echarts/lib/component/calendar'
import 'echarts/lib/component/title'
import 'echarts/lib/component/dataZoom'
import 'echarts/lib/component/visualMap'
import 'echarts/lib/component/markPoint'
import 'echarts/lib/component/markLine'
import 'echarts/lib/component/markArea'
import 'echarts/lib/component/timeline'
import 'echarts/lib/component/toolbox'
// 注册组件后即可使用
Vue.component('chart', ECharts)*/

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
