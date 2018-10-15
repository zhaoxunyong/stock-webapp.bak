<template>
  <div class="echarts">
    <!-- <div class="w-100 px-1 mx-1 kline-height">
      <p class="font-weight-bold text-center align-middle px-1 mx-1" href="#" @click.prevent="showZoomKline">
        日線
      </p>
    </div> -->
    <!-- <div :id="'tooltipId'+kineType" class="position-relative" style="top:100px;"></div> -->
    <!-- <chart :options="stockCandle" :auto-resize="resize" @mousemove="openNewKline"></chart> -->
    <div id="myChart1" class="echarts1"></div>
    <div id="myChart2" class="echarts2"></div>
    <div id="myChart3" class="echarts2"></div>
    <div id="myChart4" class="echarts2"></div>
    <div id="myChart5" class="echarts2"></div>
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
import dateAdd from '../utils/dates'
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
      resize: true
    }
  },
  props: ["kineType"],
  mounted () {
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
      let chart1 = this.$echarts.init(document.getElementById('myChart1'))
      let chart2 = this.$echarts.init(document.getElementById('myChart2'))
      let chart3 = this.$echarts.init(document.getElementById('myChart3'))
      let chart4 = this.$echarts.init(document.getElementById('myChart4'))
      let chart5 = this.$echarts.init(document.getElementById('myChart5'))
      this.setOptions(chart1, chart2, chart3, chart4, chart5)
      this.$echarts.connect([chart1, chart2, chart3, chart4, chart5]);
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
  height: 240px;
}
.echarts2 {
  width: 100%;
  height: 180px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
