<template>
  <b-row>
    <b-col cols="12" sm="12">
      <b-row>
          <b-col class="p-0 m-0 mw-100">
            <KCandle kineType="0"></KCandle>
          </b-col>
          <b-col class="p-0 m-0 mw-100">
            <!-- <KCandle kineType="1"></KCandle> -->
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
import KCandle from './kCandle.vue'

export default {
  components: {
    KCandle
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
