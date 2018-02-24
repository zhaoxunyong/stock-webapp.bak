<template>
  <div>
  left
  <router-link v-bind:to="{name:'Goodlists'}">图片2</router-link>
  </div>
</template>

<script>
export default {
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    getData () {
      this.$api.get('/api/stock/getStockDatas', null, r => {
        this.list = r
      })

      // this.$api.get('topics', null, function (r) {
      //   this.list = r.data
      // })
    }
  }
}
</script>