<template>
  <div>
    <div class="w-100 px-1 mx-1 kline-height">
      <p class="font-weight-bold text-center align-middle px-1 mx-1" href="#" @click.prevent="showZoomKline">
        周線
      </p>
    </div>
    <chart :options="kline" :auto-resize="resize" @click="openNewKline" class="pl-2"></chart>
  </div>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import candlestick from '../data/candlestick'
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
      kline: null,
      resize: true
    }
  },
  mounted () {
    
  },
  created () {
    this.getData()
    this.getRecentDate()
  },
  methods: {
    openNewKline(params) {
      console.log(params.componentType+","+params.name)
    },
    showZoomKline() {
    },
    getRecentDate() {
      var now = new Date();
      var newDate = dateAdd("w", -RECENT_DATE, now);
      return {
        startDate: newDate.toLocaleDateString(),
        endDate: new Date().toLocaleDateString()
      };

    },
    getData() {
      let this_ = this
      let datas = []
      let dateRange = this.getRecentDate()
      // this.stockId = '402396117293928448'
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        // type 0: 日 1: 周 2: 月
        //let url = `/api/stock/selectHistory?stockId=${this.stockId}&type=1&startDate=${dateRange.startDate}&endDate=${dateRange.endDate}`
        let url = `/api/stock/selectLastWeekHistory?stockId=${this.stockId}`
        console.log("kWeek url--->"+url)
        this.$api.get(url, null, rs => {
          if(rs != undefined && rs.length > 0) {
            for(let i=0;i<rs.length;i++) {
              let stockHistorys = [rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest]
              // console.log(stockHistorys)
              datas.push(stockHistorys)
            }
          } else {
            this_.$alerts.error("找不到數據:"+this.stockId)
            // alert("找不到數據:"+this.stockId)
          }
          this.kline = candlestick(datas, '周')
        })
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route' (to, from) {
      this.getData()
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>

<style>
.echarts {
  width: 85%;
  height: 400px;
}
.kline-height {
  height: 25px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
