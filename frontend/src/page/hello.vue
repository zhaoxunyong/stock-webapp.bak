<template>
  <div v-bind:class="bgYellow">
    {{heading}}
  </div>
</template>
<script> 
 
export default {
  data () {
    return {
      heading: 'Hello World',
      bgYellow: 'bgYellow'
    }
  }
}
</script>

<style scoped>
.bgYellow{
  background-color: yellow;
}
</style>