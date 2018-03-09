<template>
  <div>
    <b-container fluid class="bv-example-row border">
      <b-row class="border">
        <b-col sm="9" class="border">
          Level 1: sm="9"
          <b-row class="border">
            <b-col cols="8" sm="6" class="border">
              Level 2: cols="8" sm="6"
            </b-col>
            <b-col cols="4" sm="6" class="border">
              Level 2: cols="4" sm="6"
            </b-col>
          </b-row>
        </b-col>
        <b-col sm="3" class="border">
          xxxxxx
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
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