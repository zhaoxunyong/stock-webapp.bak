<template>
  <div :id="'stockLineItem'+kineType">
    <!-- <div class="w-100 px-1 mx-1 kline-height">
      <p class="font-weight-bold text-center align-middle px-1 mx-1" href="#" @click.prevent="showZoomKline">
        日線
      </p>
    </div> -->
    
    <!-- <chart :options="stockCandle" :auto-resize="resize" @mousemove="openNewKline"></chart> -->
    <!-- stockCandle -->
    <!-- 顺序不能变 1:stockCandle 2:stockVol 3:stockRsi 4:stockDmi 5:stockMacd 6:stockTower -->
    <div v-for="item in items" class="move-item">
      <div :id="'tooltipId'+item.type+kineType" class="tooltips w-100 text-left" :v-html="'rawHtml'+item.type"></div>
      <div :id="'myChart'+item.type+kineType" :class="'echarts'+item.type"></div>
    </div>
  </div>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import Sortable from 'sortablejs'
import stockCandle from '../stock/stockCandle'
import stockTower from '../stock/stockTower'
import stockVol from '../stock/stockVol'
import stockRsi from '../stock/stockRsi'
import stockDmi from '../stock/stockDmi'
import stockMacd from '../stock/stockMacd'
// 引用alert文件, alerts.error('xxx')
// import * as alerts from '../utils/alert.js'
// import {success, error} from '../utils/alert.js'

// http://gallery.echartsjs.com/editor.html?c=candlestick-sh

export default {
  components: {
    // MainLayout//, Alert
  },
  data() {
    return {
      items: [],
      stockId: '',
      chart: null,
      // stockCandle: null,
      resize: true,
      intervalid1: null,
      rawHtml1: '',
      rawHtml2: '',
      rawHtml3: '',
      rawHtml4: '',
      rawHtml5: '',
      rawHtml6: ''
    }
  },
  props: ['kineType'],
  // https://blog.csdn.net/zeroyulong/article/details/80255323
/* 一般在 created（或beforeRouter） 里面就可以，如果涉及到需要页面加载完成之后的话就用 mounted。
在created的时候，视图中的html并没有渲染出来，所以此时如果直接去操作html的dom节点，一定找不到相关的元素
而在mounted中，由于此时html已经渲染出来了，所以可以直接操作dom节点，（此时document.getelementById 即可生效了）。
 */
  mounted() {
    this.chartInit()
  },
  created() {
    this.init()
  },
  destroyed: function() {
    if (this.intervalid1 != null) {
      clearInterval(this.intervalid1)
    }
  },
  methods: {
    openNewKline(param) {
      // console.log(param)
    },
    init() {
      this.rawHtml1 = `收: 開: 高: 低:<br/>
                  ${this.kineType == 1 ? '月' : '日'}線
                  <font color="${STOCK_CONFIG.col.m5}">M5: </font> 
                  <font color="${STOCK_CONFIG.col.m10}">M10: </font> 
                  <font color="${STOCK_CONFIG.col.m20}">M20: </font> 
                  <font color="${STOCK_CONFIG.col.m60}">M60: </font>`

      this.rawHtml2 = `<font color="${STOCK_CONFIG.col.volup}">成交量: </font>`

      this.rawHtml3 = `<font color="${STOCK_CONFIG.col.rsi12}">RSI-12: </font>
                  <font color="${STOCK_CONFIG.col.rsi100}">RSI-100: </font>`

      this.rawHtml4 = `<font color="${STOCK_CONFIG.col.diUp}">+DI14: </font>
                  <font color="${STOCK_CONFIG.col.diDown}">-DI14: </font>
                  <font color="${STOCK_CONFIG.col.adx}">ADX14: </font>`

      this.rawHtml5 = `<font color="${STOCK_CONFIG.col.oscup}">OSC: </font>
                  <font color="${STOCK_CONFIG.col.dif}">DIF: </font>
                  <font color="${STOCK_CONFIG.col.macd}">MACD: </font>`
                  
      this.rawHtml6 = `<font color="${STOCK_CONFIG.col.rsi12}">寶塔:</font>`
      
      let this_ = this
      // this_.items = [{"id":"402379055645396992","type":6,"memo":"stockTower","status":1,"sortOrder":1},{"id":"402378966034092032","type":2,"memo":"stockVol","status":1,"sortOrder":2},{"id":"402378966617100288","type":3,"memo":"stockRsi","status":1,"sortOrder":3},{"id":"402379033608523776","type":4,"memo":"stockDmi","status":1,"sortOrder":4},{"id":"402379042320093184","type":5,"memo":"stockMacd","status":1,"sortOrder":5},{"id":"402378924086857728","type":1,"memo":"stockCandle","status":1,"sortOrder":6}]
      let url = '/api/stock/getAvailabelStockLineSettings'
      /* this.$api.get(url, null, rs => {
        // this_.items = [1, 2, 3, 4, 5, 6]
        
      }); */
      // 此处需要同步加载数据，否则显示不了
      $.ajax({
          url: url,
          type: "get",
          async: false,
          success: function(rs){
            this_.items = rs
          }
      });
    },
    chartInit() {
      let chartArray = []
      for(let i=0;i<this.items.length;i++) {
        let type = this.items[i].type
        let memo = this.items[i].memo
        console.log("rs--->"+type+"/"+memo)
        // chart對象沒有其他用處，只作是否加載判斷
        if (this.chart != null) {
          return
        }
        let el = document.getElementById('myChart' + type+ '' + this.kineType)
        let chart = this.$echarts.init(el)
        chartArray.push({
          type: type,
          chart: chart
        })
      }
      this.setOptions(chartArray)
      this.$echarts.connect(chartArray)

      let el = document.getElementById('stockLineItem'+this.kineType)
      Sortable.create(el, {
        handle: '.move-item',
        animation: 150,
        onUpdate: function (evt){
          var item = evt.item; // the current dragged HTMLElement
          // alert(item.outerHTML)
          
        }
      })
      /* setTimeout(function() {
        window.onresize = function() {
          chart1.resize();
          chart2.resize();
          chart3.resize();
          chart4.resize();
          chart5.resize();
        };
      }, 200); */
      /* let _this = this
      if(_this.intervalid1 == null) {
        _this.intervalid1 = setInterval(function() {
          if (_this.kineType == 0) {
            // 重新抓取数据
            let url = `/api/stock/fetchCurrentHistoryDaily?stockId=${
              _this.stockId
            }`
            _this.$api.post(url, null, rs => {
              _this.setOptions(chart1, chart6, chart2, chart3, chart4, chart5)
            })
          } else {
            _this.setOptions(chart1, chart6, chart2, chart3, chart4, chart5)
          }
        }, 10000) // ms
      } */
    },
    setOptions(chartArray) {
      // chart1, chart6, chart2, chart3, chart4, chart5
      // this.stockCandle = null
      let this_ = this
      let data1s = []
      let data2s = []
      let data3s = []
      let data4s = []
      let data5s = []
      let data6s = []
      // let dateRange = this.getRecentDate()
      // let dateRange = this.getRecentDate()
      // this.stockId = '402396117293928448'
      this_.stockId = this_.$route.params.stockId
      if (
        this_.stockId != undefined &&
        this_.stockId != '' &&
        this_.stockId != 0
      ) {
        // type 0: 日 1: 周 2: 月
        // let url = `/api/stock/selectHistory?stockId=${this.stockId}&type=${kineType}&startDate=${dateRange.startDate}&endDate=${dateRange.endDate}`
        let url = `/api/stock/selectLastDayHistory?stockId=${this.stockId}`
        if (this_.kineType == 1) {
          url = `/api/stock/selectLastWeekHistory?stockId=${this.stockId}`
        }
        // alert("kDay url--->"+url)
        // chart1.showLoading();
        // chart2.showLoading();
        // chart3.showLoading();
        // chart4.showLoading();
        // chart5.showLoading();
        this_.$api.get(url, null, rs => {
          if (rs != undefined && rs.length > 0) {
            for (let i = 0; i < rs.length; i++) {
              // alert(this.stockId+"--->"+rs[i].date+"--->"+rs[i].opening+"--->"+rs[i].highest+"--->"+rs[i].lowest+"--->"+rs[i].closing+"--->"+rs[i].vol)
              // let stockHistorys = [rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]
              // console.log(stockHistorys)
              data1s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
              data2s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
              data3s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
              data4s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
              data5s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
              data6s.push([
                rs[i].date,
                rs[i].opening,
                rs[i].closing,
                rs[i].lowest,
                rs[i].highest,
                rs[i].vol
              ])
            }
          } else {
            this_.$alerts.error('找不到數據:' + this_.stockId)
            // alert("找不到數據:"+this.stockId)
          }
          // return stockCandle(datas, this.kineType)
          // chart1.hideLoading();
          // chart2.hideLoading();
          // chart3.hideLoading();
          // chart4.hideLoading();
          // chart5.hideLoading();
          for(let j=0;j<chartArray.length;j++) {
            let chartJson = chartArray[j]
            let chartType = chartJson.type
            let chartObj = chartJson.chart
            switch(chartType) {
              case 1:
              default:
                chartObj.setOption(stockCandle(data1s, this_.kineType))
                break;
              case 2:
                chartObj.setOption(stockVol(data2s, this_.kineType))
                break;
              case 3:
                chartObj.setOption(stockRsi(data3s, this_.kineType))
                break;
              case 4:
                chartObj.setOption(stockDmi(data4s, this_.kineType))
                break;
              case 5:
                chartObj.setOption(stockMacd(data5s, this_.kineType))
                break;
              case 6:
                chartObj.setOption(stockTower(data6s, this_.kineType))
                break;
            }
          }

          /* chart1.on('click', function (params) {
            console.log('params.componentType--->'+params.componentType)
            // if(params.componentType === 'series'){
                chart1.dispatchAction({
                    type: 'legendUnSelect',
                    seriesseriesIdIndex: 'k-line'

                });
            // }
          
          }); */
        })
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    $route(to, from) {
      // this.getData()
      this.init()
      this.chartInit()
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>

<style scoped>
/* .echarts {
  width: 100%;
  height: 580px;
} */
.echarts1, .echarts6 {
  width: 100%;
  height: 190px;
}
.echarts2,.echarts3,.echarts4 {
  width: 100%;
  height: 90px;
}
.echarts5 {
  width: 100%;
  height: 120px;
}
.tooltips {
  font-size: 100%;
  margin-left: 40px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
