<template>
  <div>
    <span v-for="i in list">
      <router-link :to="'/content/' + i.stockId">
        {{ i.company }}
      </router-link><br />
    </span>
  </div>
</template>

<script>
import Bus from '../eventBus'
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
      this.$api.get('/api/stock/getStockMyDatas', null, r => {
        this.list = r
        let stockId = this.$route.params.stockId == undefined ? r[0].stockId : this.$route.params.stockId
        Bus.$emit('filterResultData', stockId);
      })
    }
  }
}
</script>