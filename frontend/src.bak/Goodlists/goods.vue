<template>
  <div>
    <Header></Header>
    <Left></Left>
  <div>
  </div>
    goods: {{ id }}
  </div>
</template>

<script>
import Left from './left.vue'
import Header from './header.vue'
export default {
  components: { 
    Left,Header
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
      // this.$api.get('/api/stock/getNewsBystockId/' + this.id, null, r => {
      this.$api.get('/api/stock/getNewsBystockId/402396131105771520', null, r => {
        this.dat = r
      })
    }
  }
}
</script>