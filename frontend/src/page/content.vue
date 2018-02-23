<template>
  <main-layout>
  <b-container fluid>
      <b-row>
        <b-col xl="2" sm="2" md="2" lg="2" style="background-color: #dedef8;
         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;"><stock-my-data></stock-my-data></b-col>
        <b-col xl="10" sm="10" md="10" lg="10" style="background-color: #dedef8;
         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          <!-- <p>{{i.froms}}</p> -->
          <p  v-for="i in dat">
            <a target="_blank" :href="i.url">
              {{ i.subject }}
            </a>
          </p>
        </b-col>
      </b-row>
  </b-container>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import StockMyData from './stockmydata.vue'
import Bus from '../eventBus'
export default {
  components: { 
    MainLayout, StockMyData
  },
  data () {
    return {
      stockId: this.$route.params.stockId,
      dat: {}
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
    getData (stockId) {
      stockId = stockId == undefined ? this.$route.params.stockId : stockId
      let url = '/api/stock/getNewsBystockId/' + stockId
      this.$api.get(url, null, r => {
        this.dat = r
      })
    }
  },
  watch: {
    '$route' (to, from) {
      // alert("this.$route.params.stockId--->"+this.$route.params.stockId)
      this.$api.get('/api/stock/getNewsBystockId/' + this.$route.params.stockId, null, r => {
        this.dat = r
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>