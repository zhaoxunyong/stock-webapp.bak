<template>
  <b-row>
    <b-col cols="12" sm="12">
      <b-row>
          <b-col class="p-0 m-0 mw-100">
            <KCandle kineType="0"></KCandle>
          </b-col>
          <b-col class="p-0 m-0 mw-100">
            <KCandle kineType="1"></KCandle>
          </b-col>
      </b-row>
      <!-- <b-row>
          <b-col class="p-0 m-0 mw-100">
            <RsiDay></RsiDay>
          </b-col>
          <b-col class="p-0 m-0 mw-100">
            <RsiWeek></RsiWeek>
          </b-col>
      </b-row>
      <b-row>
          <b-col class="p-0 m-0 mw-100">
            <DmiDay></DmiDay>
          </b-col>
          <b-col class="p-0 m-0 mw-100">
            <DmiWeek></DmiWeek>
          </b-col>
      </b-row>
      <b-row>
          <b-col class="p-0 m-0 mw-100">
            <MacdDay></MacdDay>
          </b-col>
          <b-col class="p-0 m-0 mw-100">
            <MacdWeek></MacdWeek>
          </b-col>
      </b-row> -->
    </b-col>
  </b-row>
</template>
<script>
import Bus from '../eventBus'
// 日K
import KCandle from './kCandle.vue'
/* // 周K
import KWeek from './kWeek.vue'

// 日rsi
import RsiDay from './rsiDay.vue'
// 周rsi
import RsiWeek from './rsiWeek.vue'

// 日宝塔
// 周宝塔

// 日dmi
import DmiDay from './dmiDay.vue'
// 周dmi
import DmiWeek from './dmiWeek.vue'

// 日macd
import MacdDay from './macdDay.vue'
// 周macd
import MacdWeek from './macdWeek.vue' */
export default {
  components: {
    KCandle/*, KWeek , RsiDay, RsiWeek, DmiDay, DmiWeek, MacdDay, MacdWeek */
  },
  data () {
    return {

    }
  },
  created () {
    
  },
  mounted () {
  },
  methods: {
  },
  watch: {
  }
}
</script>

<style scoped>

</style>
