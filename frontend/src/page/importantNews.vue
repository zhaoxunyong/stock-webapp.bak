<template>
  <main-layout>
    <template>
      <nav class="mt-3 ml-3 nav nav-pills nav-justified">
        <a class="nav-link active" href="#" @click.prevent="showExcludeNews($event)">市場重大新聞</a>
        <a class="nav-link" href="#" @click.prevent="showIncludeNews($event)">市場焦點新聞</a>
      </nav>
      <div class="text container-fluid">
        <b-table striped hover :items="items" :fields="fields">
          <span slot="content_title" slot-scope="data" v-html="data.value" />
        </b-table>
        <div align="center">
          <span class="oi oi-arrow-thick-left" style="cursor: pointer;"  @click="toBack"></span>
          <span style="padding-left: 10px;"></span>
          <span class="oi oi-arrow-thick-right" style="cursor: pointer;" @click="toFront"></span>
        </div>
        <!-- <b-pagination-nav align="center" :number-of-pages="numberOfPages" base-url="#" :link-gen="linkGen" /> -->
      </div>
    </template>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import Bus from '../eventBus'
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
      pageSize: PAGE_SIZE,
      intervalid1: null,
      fields: {
        content_title: {
          label: '<span id="content_id">: </span>市場重大新聞',
          sortable: true
        }
      }
    }
  },
  created () {
    // this.getData(1)
    Bus.$on('initCurrentPage', (pageNum) => {
      this.currentPage = pageNum
    })
    // this.timeOutsetInterval()
    this.autoFetch()
  },
  destroyed:function(){
    if(this.intervalid1 != null) {
      clearInterval(this.intervalid1)
    }
  },
  methods: {
    /* timeOutsetInterval (){
      if(this.intervalid1 != null) {
        clearInterval(this.intervalid1)
      }
      let $this = this
      this.autoFetch($this)
      this.intervalid1 = setInterval(() => {
        // this.changes = ((Math.random() * 100).toFixed(2))+'%';
        this.autoFetch($this)
      }, 10 * 60 * 1000);
    } */

    // 上一个股票
    toFront () {
      let _router = this.$router
      // let obj = $(".selected").get(0)
      // let aObj = $(obj).closest("span").prev().find("a");
      // let href = aObj.attr('href')
      // if(href != undefined) {
      //   this.push("/importantNews/")
      // }
      if(!this.items.length || this.items.length == 0) {
        alert("已经没有下一页了.")
      } else {
        let nextPage = parseInt(this.currentPage)+1
        _router.push("/importantNews/"+nextPage)
      }
    },

    // 下一个股票
    toBack () {
      let _router = this.$router
      // let obj = $(".selected").get(0)
      // let aObj = $(obj).closest("span").next().find("a");
      // let href = aObj.attr('href')
      // if(href != undefined) {
      //   this.push(href)
      // }
      let nextPage = parseInt(this.currentPage)-1
      if(nextPage<1) {
        alert("已经没有上一页了.")
      } else {
        _router.push("/importantNews/"+nextPage)
      }
    },
    autoFetch() {
      Bus.$emit('loading', "正在自動獲取最新的新聞中...", true)
      console.log("News autoFetch importantNews started......")
      let url = "/api/stock/fetchImportantLatestNews"
      this.$api.post(url, null, rs => {
        Bus.$emit('success', "自動更新新聞成功!")
        this.getData(1)
        console.log("News autoFetch importantNews end......")
      })
    },
    linkGen(pageNum) {
      return {
        path: '/importantNews/' + pageNum
      }
    },
    showExcludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 1
      this.$router.push('/importantNews/1')
      this.getData(1)
    },
    showIncludeNews(event) {
      $(".active").removeClass('active')
      $(event.target).addClass('active')
      this.type = 0
      this.$router.push('/importantNews/1')
      this.getData(1)
    },
    getData (pageNum) {
      if(pageNum != undefined) {
        this.currentPage = pageNum
      } else {
        this.currentPage = this.$route.params.pageNum
      }
      this.items = []
      items = []
      let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getImportantNewsInclude/' : '/api/stock/getImportantNewsExclude/'
      let url = rootUrl+this.currentPage+"/"+this.pageSize
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            content_title: context
          })
        });
        this.items = items
        $("#content_id").parent().remove()
      })
    }
  },
  watch: {
    '$route' (to, from) {
      this.getData()
    }
  }
}

</script>

<style scoped>
  .text {
    height: 75vh;
    margin: 0 auto;
    overflow: auto;
  }
</style>
