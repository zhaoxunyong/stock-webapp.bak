<template>
  <main-layout>
    <div>
      <ul>
        <li v-for="i in dat">
          <p>{{i.froms}}</p>
          <article v-html="i.subject"></article>
        </li>
      </ul>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
export default {
  components: { 
    MainLayout
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
      this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
      // this.$api.get('/api/stock/getNewsBystockId/402396131105771520', null, r => {
        this.dat = r
      })
    },
    getStatus (urlStr) {
      var urlStrArr = urlStr.split('/')
      return urlStrArr[urlStrArr.length - 1]
    }
  },
  watch: {
    '$route' (to, from) {
      console.log("2--->"+this.getStatus(this.$route.path))
      alert("222")
      this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
        this.dat = r
      })
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>