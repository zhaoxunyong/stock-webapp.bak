<template>
  <main-layout>
  <b-container fluid>
      <b-row v-for="i in dat">
        <b-col style="background-color: #dedef8;
         box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
            <a target="_blank" :href="i.url">{{i.froms}}</a>
            <br />
            {{i.subject}}
        </b-col>
      </b-row>
  </b-container>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
export default {
  components: { 
    MainLayout,
  },
  data () {
    return {
      id: this.$route.params.id,
      dat: {}
    }
  },
  created () {
    this.getData()
  },
  methods: {
    getData () {
      alert("xxx")
      this.$api.get('/api/stock/getImportantNews/1', null, r => {
        this.dat = r
      })
    },
    getStatus (urlStr) {
      var urlStrArr = urlStr.split('/')
      return urlStrArr[urlStrArr.length - 1]
    }
  },
  beforeRouteUpdate (to, from, next) {
    console.log("2--->"+this.getStatus(this.$route.path))
    alert("222")
    // this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
    this.$api.get('/api/stock/getImportantNews/1', null, r => {
      this.dat = r
    })
    //this.$router.push('/content/' + this.getStatus(this.$route.path))
    next()
  }/*,
  watch: {
    '$route' (to, from) {
      console.log("2--->"+this.getStatus(this.$route.path))
      alert("222")
      // this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
      this.$api.get('/api/stock/getNewsBystockId/402396131105771520', null, r => {
        this.dat = r
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }*/
}
</script>