<template>
  <b-row>
    <b-col cols="12" sm="12">
      <b-row>
          <b-col>
            <KDay></KDay>
          </b-col>
          <b-col>
            <KWeek></KWeek>
          </b-col>
      </b-row>
      <!-- <b-row>
          <b-col>
            <RsiDay></RsiDay>
          </b-col>
          <b-col>
            <RsiWeek></RsiWeek>
          </b-col>
      </b-row>
      <b-row>
          <b-col>
            <DmiDay></DmiDay>
          </b-col>
          <b-col>
            <DmiWeek></DmiWeek>
          </b-col>
      </b-row>
      <b-row>
          <b-col>
            <MacdDay></MacdDay>
          </b-col>
          <b-col>
            <MacdWeek></MacdWeek>
          </b-col>
      </b-row> -->
    </b-col>
  </b-row>
</template>
<script>
import Bus from '../eventBus'
// 日K
import KDay from './kDay.vue'
// 周K
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
import MacdWeek from './macdWeek.vue'
export default {
  components: {
    KDay, KWeek, RsiDay, RsiWeek, DmiDay, DmiWeek, MacdDay, MacdWeek//, Alert//, Loading
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
