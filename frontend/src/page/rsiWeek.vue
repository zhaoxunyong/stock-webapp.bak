<template>
 <chart :options="kline"></chart>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import getKline from '../data/kline'

// http://bl.ocks.org/andredumas/6da267f1c51a13dea35b
    
export default {
  components: {
    // MainLayout//, Alert
  },
  data () {
    return {
      kline: getKline()
    }
  },
  mounted () {
    
  },
  created () {

  },
  methods: {

  }
}
</script>

<style>

</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
