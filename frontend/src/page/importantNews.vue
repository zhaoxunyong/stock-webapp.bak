<template>
  <main-layout>
    <template>
      <b-table striped hover :items="items"></b-table>
      <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" v-model="currentPage" :link-gen="linkGen" />
    </template>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
let items = []
export default {
  components: { 
    MainLayout
  },
  data () {
    return {
      items: [],
      numberOfPages: 0,
      currentPage: this.$route.params.pageNum,
      pageSize: 10
    }
  },
  created () {
    this.getData()
  },
  methods: {
    linkGen(pageNum) {
      return {
        path: '/importantNews/' + pageNum
      }
    },
    getData () {
      items = []
      let url = '/api/stock/getImportantNews/'+this.$route.params.pageNum+"/"+this.pageSize
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            '重點資訊': context
          })
        });
        this.items = items
      })
    }
  },
  watch: {
    '$route' (to, from) {
      items = []
      let url = '/api/stock/getImportantNews/'+this.$route.params.pageNum+"/"+this.pageSize
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            '重點資訊': context
          })
        });
        this.items = items
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>