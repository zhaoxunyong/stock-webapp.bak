<template>
  <div>
  <b-table striped hover :items="items"></b-table>
          <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" v-model="currentPage" :link-gen="linkGen" />
  </div>
</template>
<script>
import Bus from '../eventBus'
let items = []
export default {
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
      Bus.$on('setFirstStock', (stockId) => {
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