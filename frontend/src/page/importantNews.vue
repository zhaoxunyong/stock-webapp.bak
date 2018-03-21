<template>
  <main-layout>
    <template>
      <nav class="mt-3 ml-3 nav nav-pills nav-justified">
        <a class="nav-link active" href="#" @click.prevent="showExcludeNews($event)">市場重大新聞</a>
        <a class="nav-link" href="#" @click.prevent="showIncludeNews($event)">市場焦點新聞</a>
      </nav>
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
      type: 1,
      pageSize: PAGE_SIZE
    }
  },
  created () {
    this.getData(1)
    Bus.$on('initCurrentPage', (pageNum) => {
      this.currentPage = pageNum
    })
  },
  methods: {
    linkGen(pageNum) {
      return {
        path: '/importantNews/' + pageNum
      }
    },
    showExcludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 1
      this.getData(1)
    },
    showIncludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 0
      this.getData(1)
    },
    getData (pageNum) {
      if(pageNum != undefined) {
        this.pageNum = pageNum
      } else {
        this.pageNum = this.$route.params.pageNum
      }
      items = []
      let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getImportantNewsInclude/' : '/api/stock/getImportantNewsExclude/'
      let url = rootUrl+this.pageNum+"/"+this.pageSize
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            '市場重大新聞': context
          })
        });
        this.items = items
      })
    }
  },
  watch: {
    '$route' (to, from) {
      this.pageNum = this.$route.params.pageNum
      items = []
      let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getImportantNewsInclude/' : '/api/stock/getImportantNewsExclude/'
      let url = rootUrl+this.pageNum+"/"+this.pageSize
      // alert("url2--->"+url)
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            '市場重大新聞': context
          })
        });
        this.items = items
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>
