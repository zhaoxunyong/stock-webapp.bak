<template>
 <chart :options="kline"></chart>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import candlestick from '../data/candlestick'
import dateAdd from '../utils/dates'

// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
    
export default {
  components: {
    // MainLayout//, Alert
  },
  data () {
    return {
      stockId: '',
      kline: null
    }
  },
  mounted () {
    
  },
  created () {
    this.getData()
    this.getRecentDate()
  },
  methods: {
    getRecentDate() {
      var now = new Date();
      var newDate = dateAdd("d ", -RECENT_DATE, now);
      return {
        startDate: newDate.toLocaleDateString(),
        endDate: new Date().toLocaleDateString()
      };

    },
    getData() {
      let datas = []
      let dateRange = this.getRecentDate()
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        let url = `/api/stock/selectHistory?stockId=${this.stockId}&type=0&startDate=${dateRange.startDate}&endDate=${dateRange.endDate}`
        this.$api.get(url, null, rs => {
          if(rs != undefined && rs.length > 0) {
            
            for(let i=0;i<rs.length;i++) {
              let stockHistorys = [rs[i].date, rs[i].opening, rs[i].highest, rs[i].lowest, rs[i].closing, rs[i].vol]
              console.log(stockHistorys)
              datas.push(stockHistorys)
            }
            this.kline = candlestick(datas, '日')
          } else {
            alert("找不到數據!")
          }
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

<style scoped>
.echarts {
  width: 100%;
  height: 400px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
