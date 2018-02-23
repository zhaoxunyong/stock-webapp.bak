<template>
  <main-layout>
  <b-container fluid>
      <b-row>
        <b-col xl="2" sm="2" md="2" lg="2" style="box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;"><stock-my-data></stock-my-data></b-col>
        <b-col xl="10" sm="10" md="10" lg="10" style="box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          <b-table striped hover :items="items"></b-table>
          <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" v-model="currentPage" :link-gen="linkGen" />
        </b-col>
      </b-row>
  </b-container>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import StockMyData from './stockmydata.vue'
import Bus from '../eventBus'
let items = []
export default {
  components: { 
    MainLayout, StockMyData
  },
  data () {
    return {
      items: [],
      stockId: '',
      numberOfPages: 0,
      currentPage: this.$route.params.pageNum,
      pageSize: 10
    }
  },
  created () {
      Bus.$on('filterResultData', (stockId) => {
        // alert("--->"+stockId)
        this.getData(stockId)
      });
  },
  mounted () {
  },
  methods: {
    linkGen(pageNum) {
      return {
        path: '/content/' + this.stockId+'/'+pageNum
      }
    },
    getData (stockId) {
      items = []
      stockId = stockId == undefined ? this.$route.params.stockId : stockId
      this.stockId = stockId
      let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
      let url = '/api/stock/getNewsBystockId/' + stockId+'/'+pageNum+'/'+this.pageSize
      // alert("url1--->"+url)
      this.$api.get(url, null, rs => {
        // this.dat = r
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.subject+"</a>"
          items.push({
            '個股新聞與研究報告': context
          })
        });
        this.items = items
      })
    }
  },
  watch: {
    '$route' (to, from) {
      items = []
      // alert("this.$route.params.stockId--->"+this.$route.params.stockId)
      let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
      let url = '/api/stock/getNewsBystockId/' + this.$route.params.stockId+'/'+pageNum+'/'+this.pageSize
      // alert("url2--->"+url)
      this.$api.get(url, null, rs => {
        // this.dat = r
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.subject+"</a>"
          items.push({
            '個股新聞與研究報告': context
          })
        });
        this.items = items
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>