<template>
  <main-layout>
    <div>
      index
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
export default {
  components: { 
    MainLayout 
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

<style>
  .article_list {margin: auto;}
</style>