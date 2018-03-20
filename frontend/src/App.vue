<template>
  <div id="app">
    <Alert></Alert>
    <router-view></router-view>
  </div>
</template>

<script>
  import Alert from './components/alert.vue'
  export default {
    name: 'app',
    components: {
      Alert
    },
  }
</script>

<style lang="scss">
  @import "./style/style";
</style>
