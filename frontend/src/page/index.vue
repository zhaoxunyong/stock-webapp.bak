<template>
  <main-layout>
    <MyStock></MyStock>
    <div>
      index
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import MyStock from './mystock.vue'
export default {
  components: { 
    MainLayout, MyStock
  }/*,
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
      this.$api.get('topics', null, r => {
        this.list = r.data
      })

      // this.$api.get('topics', null, function (r) {
      //   this.list = r.data
      // })
    }
  }*/
}
</script>