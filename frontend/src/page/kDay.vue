<template>
  <chart :options="polar"></chart>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import polar from '../data/polar'
// http://bl.ocks.org/andredumas/2da5cf683eb1e0589fa10418d442479c
    
export default {
  data: function () {
    return {
      polar
    }
  },
  methods: {
 
  }
}
</script>

<style>
.echarts {
  height: 300px;
}
