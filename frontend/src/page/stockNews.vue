<template>
  <div>
    <div class="container-fluid">
      <nav class="nav nav-pills nav-justified">
        <div class="pt-2 pr-2"><h5>{{this.company}}</h5></div>
        <a class="nav-link active" href="#" @click.prevent="showExcludeNews($event)">個股新聞</a>
        <a class="nav-link" href="#" @click.prevent="showIncludeNews($event)">焦點新聞</a>
        <!-- <span style="padding-left: 5px"></span> -->
        <a class="nav-link" href="#" @click.prevent="showExcludeNewsAll($event)">自選股全新聞</a>
        <a class="nav-link" href="#" @click.prevent="showIncludeNewsAll($event)">自選股全焦點</a>
      </nav>
    </div>
    <div class="container-fluid scrollbar" id="style-1">
      <b-table striped hover :items="items" :fields="fields">
        <span slot="content_title" slot-scope="data" v-html="data.value" />
      </b-table>
      <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" v-model="currentPage" :link-gen="linkGen" />
    </div>
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      company: '',
      stockId: '',
      selectedType: '0',
      numberOfPages: 0,
      currentPage: 1,
      displayAll: 0,
      type: 1,
      fromSelectedProcess: 0,
      pageSize: PAGE_SIZE,
      fields: {
        content_title: {
          label: '<span id="content_id">: </span>個股新聞與研究報告',
          sortable: true
        }
      },
      items: [],
      intervalid1: null
    }
  },
  created () {
    this.getData()
    Bus.$on('emptyNews', () => {
      this.cleanNews()
    })
    Bus.$on('initCurrentPage', (pageNum) => {
      this.currentPage = pageNum
    })
    // 从左侧搜索后触发
    Bus.$on('selectedProcess', () => {
      // 自动抓一次数据
      // Bus.$emit('loading', "正在自動獲取最新的新聞中...", true)
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined) {
        console.log("News autoFetch stockNews started......"+this.stockId)
        let url = "/api/stock/fetchLatestNews?stockId="+this.stockId
        // alert("fetchNews=============>"+url)
        this.$api.post(url, null, rs => {
          // Bus.$emit('success', "自動更新新聞成功!")
          this.getData()
          console.log("News autoFetch stockNews end......"+this.stockId)
        })
      }
    })
    // 从stockmyselectedtype.vue中过来：当点击某个自选股标签时
    Bus.$on('getMyStockSelected', (selectedType, selectedName) => {
      if(selectedType == undefined) {
        this.selectedType = '0'
      } else {
        this.selectedType = selectedType
      }
    });

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    Bus.$on('getAllStockMyStore', () => {
      this.selectedType = '0'
    });

    // this.timeOutsetInterval()
  },
  mounted () {
  },
  /*destroyed:function(){
    if(this.intervalid1 != null) {
      clearInterval(this.intervalid1)
    }
  },*/
  methods: {
    /*timeOutsetInterval (){
      if(this.intervalid1 != null) {
        clearInterval(this.intervalid1)
      }
      let $this = this
      this.autoFetch($this)
      $this.intervalid1 = setInterval(() => {
        // this.changes = ((Math.random() * 100).toFixed(2))+'%';
        this.autoFetch($this)
      }, 5 * 60 * 1000);
    },
    autoFetch($this) {
      Bus.$emit('loading', "正在自動獲取最新的新聞中...", true)
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined) {
        console.log("News autoFetch stockNews started......"+this.stockId)
        let url = "/api/stock/fetchLatestNews?stockId="+this.stockId
        // alert("fetchNews=============>"+url)
        $this.$api.post(url, null, rs => {
          Bus.$emit('success', "自動更新新聞成功!")
          this.getData()
          console.log("News autoFetch stockNews end......"+this.stockId)
        })
      }
    },*/
    linkGen(pageNum) {
      return {
        path: '/content/' + this.stockId+'/'+pageNum
      }
    },
    // 第一次加载数据
    cleanNews() {
      this.items = []
      $("#content_id").text("")
      this.numberOfPages = 0
      this.currentPage = 1
    },
    showExcludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 1
      this.displayAll = 0
      this.getData(1)
    },
    showIncludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 0
      this.displayAll = 0
      this.getData(1)
    },
    showExcludeNewsAll(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 1
      this.displayAll = 1
      this.getData(1)
    },
    showIncludeNewsAll(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 0
      this.displayAll = 1
      this.getData(1)
    },
    // 第一次加载数据
    getData (pageNum) {
      // if(pageNum != undefined) {
      //   this.currentPage = pageNum
      // } else {
      //   this.currentPage = this.$route.params.pageNum
      // }

      if(pageNum == undefined) {
        pageNum = this.$route.params.pageNum
      }
      // pageNum = this.currentPage
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        this.$api.get('/api/stock/getStockData/'+this.stockId, null, stockData => {
          this.company = stockData.company
          // let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
          // let rootUrl = type == 0 ? '/api/stock/getNewsIncludeBystockId/' : '/api/stock/getNewsExcludeBystockId/'
          // let url = rootUrl + this.stockId+'/'+pageNum+'/'+this.pageSize
          let url = ''
          if(this.displayAll == 1) {
            let paramStockId = 0
            let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getNewsIncludeBystockId/' : '/api/stock/getNewsExcludeBystockId/'
            url = rootUrl + paramStockId+'/'+this.selectedType+'/'+pageNum+'/'+this.pageSize
          } else {
            let paramStockId = this.stockId
            let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getNewsIncludeBystockId4All/' : '/api/stock/getNewsExcludeBystockId4All/'
            url = rootUrl + paramStockId+'/'+pageNum+'/'+this.pageSize
          }
          // alert("url1--->"+url)
          this.$api.get(url, null, rs => {
            // this.dat = r
            this.numberOfPages = rs.pageTotal
            let rsData = rs.rows
            this.items = []
            for(var i=0;i<rsData.length;i++) {
              let context = "<a target=\"_blank\" href=\""+rsData[i].url+"\">"+rsData[i].subject+"</a>"
              this.items.push({
                content_title: context
              })
            }
          })
          $("#content_id").text(this.company+": ").parent().remove()
        })
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route' (to, from) {
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        this.getData()
      } else {
        this.cleanNews()
      }

      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>

<style scoped>
/*   .text {
    height: 73vh;
    margin: 0 auto;
    overflow: auto;
  } */
.scrollbar {
  height: 73vh;
  margin: 0 auto;
  overflow: auto;
}
#style-1::-webkit-scrollbar {
    width: 8px;
    background-color: #F5F5F5;
} 
#style-1::-webkit-scrollbar-thumb {
    background-color: #72df184d;
}
#style-1::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    background-color: #F5F5F5;
}
</style>
