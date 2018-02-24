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
      // this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
      this.$api.get('/api/stock/getNewsBystockId/402396131105771520', null, r => {
        this.dat = r
      })
    }
  }
}
</script>