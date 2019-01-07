<template>
  <div :id="'stockLineItem'+kineType" class="kcandle_div">
    <!-- <div class="w-100 px-1 mx-1 kline-height">
      <p class="font-weight-bold text-center align-middle px-1 mx-1" href="#" @click.prevent="showZoomKline">
        日線
      </p>
    </div> -->
    
    <!-- <chart :options="stockCandle" :auto-resize="resize" @mousemove="openNewKline"></chart> -->
    <!-- stockCandle -->
    <!-- 顺序不能变 1:stockCandle 2:stockVol 3:stockRsi 4:stockDmi 5:stockMacd 6:stockTower -->
    <!-- <div v-for="item in items" :class="item.type == 1 ? 'move1-item' : 'move-item'"> -->
      <div v-for="item in items" class="move-item">
      <div :id="'tooltipId'+item.type+kineType" :charttype="item.type" class="tooltips w-100 text-left"></div>
      <div :id="'myChart'+item.type+kineType" :data="item.type" :class="'echarts'+item.type"></div>
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
      intervalid1: null
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
    let this_ = this
    let el = document.getElementById('stockLineItem'+this.kineType)
    Sortable.create(el, {
      handle: '.move-item',
      animation: 150,
      onUpdate: function (evt){
        var item = evt.item; // the current dragged HTMLElement
        // alert(item.outerHTML)
        let sortOrders = []
        $('#stockLineItem'+this_.kineType).find('.tooltips').each(function() {
          sortOrders.push($(this).attr("charttype"))
        });
        // alert(sortOrders)
        let url ='/api/stock/updateStockLineSettingsOrder?orders='+encodeURI(sortOrders.join(","))
        this_.$api.post(url, null, function(){
          // this_.chartInit()
          // window.location.assign(window.location.href)
          this_.$alerts.success('調整顯示順序成功！')
          // this_.$router.push(this_.$route.path)
          // this_.$forceUpdate()
          window.location.reload()
          /* let currentPath = this_.$route.path
          this_.$router.go({
            path: currentPath,
            query: {
                t: + new Date()
            }
          }) */
        })
      }
    })
    if(this.intervalid1 == null) {
      this.intervalid1 = setInterval(function() {
        if (this_.kineType == 0) {
          // 重新抓取数据
          let url = `/api/stock/fetchCurrentHistoryDaily?stockId=${this_.stockId}`
          this_.$api.post(url, null, rs => {
            this_.chartInit()
          })
        } else {
          this_.chartInit()
        }
      }, 10000) // ms
    }
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
    allinit() {
      this.init()
      this.chartInit()
    },
    init() {
      
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
      let chartObj = []
      for(let i=0;i<this.items.length;i++) {
        let type = this.items[i].type
        let memo = this.items[i].memo
        // console.log("rs--->"+type+"/"+memo)
        // chart對象沒有其他用處，只作是否加載判斷
        if (this.chart != null) {
          return
        }
        let el = document.getElementById('myChart' + type+ '' + this.kineType)
        let chart = this.$echarts.init(el)
        chartObj.push(chart)
        chartArray.push({
          type: type,
          chart: chart
        })
      }
      this.setOptions(chartArray)
      this.$echarts.connect(chartObj)

      /* setTimeout(function() {
        window.onresize = function() {
          chart1.resize();
          chart2.resize();
          chart3.resize();
          chart4.resize();
          chart5.resize();
        };
      }, 200); */
    },
    setOptions(chartArray) {
      // chart1, chart6, chart2, chart3, chart4, chart5
      // this.stockCandle = null
      let this_ = this
      let srcDatas = []
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
        /* for(let j=0;j<chartArray.length;j++) {
            let chartJson = chartArray[j]
            let chartObj = chartJson.chart
            chartObj.showLoading();
        } */
        this_.$api.get(url, null, rs => {
          if (rs != undefined && rs.length > 0) {
            for (let i = 0; i < rs.length; i++) {
              // alert(this.stockId+"--->"+rs[i].date+"--->"+rs[i].opening+"--->"+rs[i].highest+"--->"+rs[i].lowest+"--->"+rs[i].closing+"--->"+rs[i].vol)
              // let stockHistorys = [rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]
              // console.log(stockHistorys)
              srcDatas.push([
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
                chartObj.setOption(stockCandle(chartObj, this_.$utils.clone(srcDatas), this_.kineType))
                /* chartObj.getZr().on("mousewheel", function (param) {
                  console.log("mousewheel--->"+chartObj);
                }); */
                /* chartObj.on('datazoom', function (params){
                    //params里面有什么，可以打印出来看一下就明白
                    console.log(params);
                    //可以通过params获取缩放的起止百分比，但是鼠标滚轮和伸缩条拖动触发的params格式不同，所以用另一种方法
                    //获得图表数据数组下标
                    var startValue = chartObj.getModel().option.dataZoom[0].startValue;
                    var endValue = chartObj.getModel().option.dataZoom[0].endValue;
                    //获得起止位置百分比
                    var startPercent = chartObj.getModel().option.dataZoom[0].start;
                    var endPercent = chartObj.getModel().option.dataZoom[0].end;
                    // console.log("startValue--->"+startValue)
                    // console.log("endValue--->"+endValue)
                    // console.log("startPercent--->"+startPercent)
                    // console.log("endPercent--->"+endPercent)
                }) */
                break;
              case 2:
                chartObj.setOption(stockVol(this_.$utils.clone(srcDatas), this_.kineType))
                break;
              case 3:
                chartObj.setOption(stockRsi(this_.$utils.clone(srcDatas), this_.kineType))
                break;
              case 4:
                chartObj.setOption(stockDmi(this_.$utils.clone(srcDatas), this_.kineType))
                break;
              case 5:
                chartObj.setOption(stockMacd(this_.$utils.clone(srcDatas), this_.kineType))
                break;
              case 6:
                chartObj.setOption(stockTower(this_.$utils.clone(srcDatas), this_.kineType))
                break;
            }
        // var index = 50; //播放所在下标
				// var mTime = setInterval(function() {
				// 	chartObj.dispatchAction({
				// 		type: 'showTip',
				// 		seriesIndex: 0,
				// 		dataIndex: index
				// 	});
        // }, 100);
        
        setTimeout(function(){
            chartObj.dispatchAction({
                type: 'showTip',
                seriesIndex:0,  // 显示第几个series
                dataIndex: 87 // 显示第几个数据
            });
        }, 10)

            
            // chartObj.hideLoading()
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
    // '$route': 'allinit',
    $route(to, from) {
      // this.getData()
      this.allinit()
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
.kcandle_div {
  margin-left: 0;
  padding-left: 0;
  /* border: 1px solid red; */
}
.echarts1 {
  width: 99%;
  height: 190px;
}
.echarts2, .echarts3, .echarts4, .echarts5, .echarts6 {
  width: 99%;
  height: 90px;
}
.tooltips {
  font-size: 100%;
  margin-left: 40px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
