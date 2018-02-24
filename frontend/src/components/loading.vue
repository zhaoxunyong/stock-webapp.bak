<template>
  <div id="loading_div">
    <b-progress :value="100" variant="success" :animated="true" :show-value="dismissDown" :show-progress="dismissDown" class="mb-3">
    </b-progress>
  </div>
</template>

<script>
  import Bus from '../eventBus'
  export default {
    data () {
      return {
        dismissDown: true,
        err: ''
      }
    },
    created () {
      Bus.$on('loading', (display) => {
        this.dismissDown = display
        if(!display) {
          $("#loading_div").hide()
        } else {
          $("#loading_div").show()
        }
      });
    }
  }
</script>