<template>
  <div>
    <!-- <div class="w-100 px-1 mx-1 kline-height">
      <p class="font-weight-bold text-center align-middle px-1 mx-1" href="#" @click.prevent="showZoomKline">
        日線
      </p>
    </div> -->
    
    <!-- <chart :options="stockCandle" :auto-resize="resize" @mousemove="openNewKline"></chart> -->
    <!-- stockCandle -->
    <div>
      <div :id="'tooltipId1'+kineType" class="w-100 text-left ml-5 pl-3 my-1" v-html="rawHtml1"></div>
      <div :id="'myChart1'+kineType" class="echarts1"></div>
    </div>
    <!-- stockVol -->
    <div>
      <div :id="'tooltipId2'+kineType" class="w-100 text-left ml-5 pl-3 my-1" v-html="rawHtml2"></div>
      <div :id="'myChart2'+kineType" class="echarts2"></div>
    </div>
    <!-- stockRsi -->
    <div>
      <div :id="'tooltipId3'+kineType" class="w-100 text-left ml-5 pl-3 my-1" v-html="rawHtml3"></div>
      <div :id="'myChart3'+kineType" class="echarts2"></div>
    </div>
    <!-- stockDmi -->
    <div>
      <div :id="'tooltipId4'+kineType" class="w-100 text-left ml-5 pl-3 my-1" v-html="rawHtml4"></div>
      <div :id="'myChart4'+kineType" class="echarts2"></div>
    </div>
    <!-- stockMacd -->
    <div>
      <div :id="'tooltipId5'+kineType" class="w-100 text-left ml-5 pl-3 my-1" v-html="rawHtml5"></div>
      <div :id="'myChart5'+kineType" class="echarts3"></div>
    </div>
  </div>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import stockCandle from '../data/stockCandle'
import stockVol from '../data/stockVol'
import stockRsi from '../data/stockRsi'
import stockDmi from '../data/stockDmi'
import stockMacd from '../data/stockMacd'
// 引用alert文件, alerts.error('xxx')
// import * as alerts from '../utils/alert.js'
// import {success, error} from '../utils/alert.js'

// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
    
export default {
  components: {
    // MainLayout//, Alert
  },
  data () {
    return {
      stockId: '',
      chart: null,
      // stockCandle: null,
      resize: true,
      rawHtml1: '',
      rawHtml2: '',
      rawHtml3: '',
      rawHtml4: '',
      rawHtml5: '',
    }
  },
  props: ["kineType"],
  mounted () {
    this.rawHtml1 = `收 開 高 低<br/>
                ${this.kineType == 1 ? "月" : "日"}線
                <font color="${STOCK_CONFIG.col.m5}">M5 </font> 
                <font color="${STOCK_CONFIG.col.m10}">M10 </font> 
                <font color="${STOCK_CONFIG.col.m20}">M20 </font> 
                <font color="${STOCK_CONFIG.col.m60}">M60 </font>`

    this.rawHtml2 =  `<font color="${STOCK_CONFIG.col.up}">成交量 </font>`

    this.rawHtml3 =  `<font color="${STOCK_CONFIG.col.up}">RSI-12 </font>
                <font color="${STOCK_CONFIG.col.down}">RSI-100 </font>`

    this.rawHtml4 =  `<font color="${STOCK_CONFIG.col.mdi}">MDI </font>
                <font color="${STOCK_CONFIG.col.pdi}">PDI </font>
                <font color="${STOCK_CONFIG.col.adx}">ADX </font>`

    this.rawHtml5 =  `<font color="${STOCK_CONFIG.col.up}">MACD </font>
                <font color="${STOCK_CONFIG.col.dif}">DIF </font>
                <font color="${STOCK_CONFIG.col.dea}">DEA </font>`
    this.init()
  },
  created () {
    // this.getRecentDate()
  },
  methods: {
    openNewKline(param) {
      // console.log(param)
    },
    showZoomKline() {
    },
    /* getRecentDate() {
      var now = new Date();
      var newDate = dateAdd("d", -RECENT_DATE, now);
      return {
        startDate: newDate.toLocaleDateString(),
        endDate: new Date().toLocaleDateString()
      };
    }, */
    init() {
      if (this.chart != null) {
        return
      }
      let chart1 = this.$echarts.init(document.getElementById('myChart1'+this.kineType))
      let chart2 = this.$echarts.init(document.getElementById('myChart2'+this.kineType))
      let chart3 = this.$echarts.init(document.getElementById('myChart3'+this.kineType))
      let chart4 = this.$echarts.init(document.getElementById('myChart4'+this.kineType))
      let chart5 = this.$echarts.init(document.getElementById('myChart5'+this.kineType))
      this.setOptions(chart1, chart2, chart3, chart4, chart5)
      this.$echarts.connect([chart1, chart2, chart3, chart4, chart5]);
      setTimeout(function (){
          window.onresize = function () {
              chart1.resize();
              chart2.resize();
              chart3.resize();
              chart4.resize();
              chart5.resize();
          }
      },200)
    },
    setOptions(chart1, chart2, chart3, chart4, chart5) {
      // this.stockCandle = null
      let this_ = this
      let data1s = []
      let data2s = []
      let data3s = []
      let data4s = []
      let data5s = []
      // let dateRange = this.getRecentDate()
      // let dateRange = this.getRecentDate()
      // this.stockId = '402396117293928448'
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        // type 0: 日 1: 周 2: 月
        // let url = `/api/stock/selectHistory?stockId=${this.stockId}&type=${kineType}&startDate=${dateRange.startDate}&endDate=${dateRange.endDate}`
        let url = `/api/stock/selectLastDayHistory?stockId=${this.stockId}`
        if(this.kineType == 1) {
          url = `/api/stock/selectLastWeekHistory?stockId=${this.stockId}`
        }
        // alert("kDay url--->"+url)
        // chart1.showLoading()
        this.$api.get(url, null, rs => {
          // chart1.hideLoading()
          if(rs != undefined && rs.length > 0) {
            for(let i=0;i<rs.length;i++) {
              // alert(this.stockId+"--->"+rs[i].date+"--->"+rs[i].opening+"--->"+rs[i].highest+"--->"+rs[i].lowest+"--->"+rs[i].closing+"--->"+rs[i].vol)
              // let stockHistorys = [rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]
              // console.log(stockHistorys)
              data1s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol])
              data2s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol])
              data3s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol])
              data4s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol])
              data5s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol])
            }
          } else {
            this_.$alerts.error("找不到數據:"+this.stockId)
            // alert("找不到數據:"+this.stockId)
          }
          // return stockCandle(datas, this.kineType)
          chart1.setOption(stockCandle(data1s, this.kineType))
          chart2.setOption(stockVol(data2s, this.kineType))
          chart3.setOption(stockRsi(data3s, this.kineType))
          chart4.setOption(stockDmi(data4s, this.kineType))
          chart5.setOption(stockMacd(data5s, this.kineType))
        })
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route' (to, from) {
      // this.getData()
      this.init()
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
.echarts1 {
  width: 100%;
  height: 190px;
}
.echarts2 {
  width: 100%;
  height: 90px;
}
.echarts3 {
  width: 100%;
  height: 120px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
