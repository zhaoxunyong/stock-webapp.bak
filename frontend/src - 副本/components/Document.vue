<template>
  <div>Document</div>
</template>

<script>
export default {
  data () {
    return {
      //id: this.$route.params.id,
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