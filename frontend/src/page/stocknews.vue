<template>
  <div>
  <b-table striped hover :items="items" :fields="fields"></b-table>
          <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" v-model="currentPage" :link-gen="linkGen" />
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      company: '',
      stockId: '',
      numberOfPages: 0,
      currentPage: this.$route.params.pageNum,
      pageSize: 15,
      fields: {
        content_title: {
          label: '<span id="content_id">: </span>個股新聞與研究報告',
          sortable: true
        }
      },
      items: []
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
  },
  mounted () {
  },
  methods: {
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
    // 第一次加载数据
    getData () {
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        this.$api.get('/api/stock/getStockData/'+this.stockId, null, stockData => {
          this.company = stockData.company
          let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
          let url = '/api/stock/getNewsBystockId/' + this.stockId+'/'+pageNum+'/'+this.pageSize
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
          $("#content_id").text(this.company+": ")
        })
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route' (to, from) {
      this.stockId = this.$route.params.stockId
      if(this.stockId != 0) {
        this.$api.get('/api/stock/getStockData/'+this.stockId, null, stockData => {
          this.company = stockData.company
          console.log("company--->"+this.company)
          let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
          let url = '/api/stock/getNewsBystockId/' + this.stockId+'/'+pageNum+'/'+this.pageSize
          // alert("url2--->"+url)
          this.$api.get(url, null, rs => {
            // this.dat = r
            // this.items = rs
            this.items = []
            this.numberOfPages = rs.pageTotal
            let rsData = rs.rows
            for(var i=0;i<rsData.length;i++) {
              let context = "<a target=\"_blank\" href=\""+rsData[i].url+"\">"+rsData[i].subject+"</a>"
              this.items.push({
                content_title: context
              }) 
            }
          })
          $("#content_id").text(this.company+": ")
        })
      } else {
        this.cleanNews()
      }
      
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>