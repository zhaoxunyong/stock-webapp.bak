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
          <!-- <b-col>
            <img src="../images/stock01.png" class="w-100" />
          </b-col> -->
      </b-row>
      <b-row>
          <b-col>
            <RsiDay></RsiDay>
          </b-col>
          <b-col>
            <RsiWeek></RsiWeek>
          </b-col>
          <!-- <b-col>
            <img src="../images/stock01.png" class="w-100" />
          </b-col> -->
      </b-row>
      <b-row>
          <b-col>
            <DmiDay></DmiDay>
          </b-col>
          <b-col>
            <DmiWeek></DmiWeek>
          </b-col>
          <!-- <b-col>
            <img src="../images/stock01.png" class="w-100" />
          </b-col> -->
      </b-row>
      <b-row>
          <b-col>
            <MacdDay></MacdDay>
          </b-col>
          <b-col>
            <MacdWeek></MacdWeek>
          </b-col>
          <!-- <b-col>
            <img src="../images/stock01.png" class="w-100" />
          </b-col> -->
      </b-row>
    </b-col>
  </b-row>
</template>
<script>
import Bus from '../eventBus'
import KDay from './kDay.vue'
import KWeek from './kWeek.vue'
import RsiDay from './rsiDay.vue'
import RsiWeek from './rsiWeek.vue'
import DmiDay from './dmiDay.vue'
import DmiWeek from './dmiWeek.vue'
import MacdDay from './macdDay.vue'
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
