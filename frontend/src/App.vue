<template>
  <div id="app">
    <Alert></Alert>
    <!-- <router-view :key="key"></router-view> -->
    <router-view></router-view>
  </div>
</template>

<script>
  import Alert from './components/alert.vue'
  export default {
    name: 'app',
    components: {
      Alert
    }/* ,
    computed: {
      key() {
          return this.$route.name !== undefined? this.$route.name + +new Date(): this.$route + +new Date()
      }
    } */
  }
</script>

<style lang="scss">
  @import "./style/style";
</style>
