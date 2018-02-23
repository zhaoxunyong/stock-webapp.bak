<template>
  <div>
    <span v-for="i in list">
      <router-link :to="'/content/' + i.id">
        {{ i.company + ' - ' + i.no }}
      </router-link><br />
    </span>
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