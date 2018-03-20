<template>
  <div>

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
      Bus.$on('alerts', (message) => {
        // alert("alerts--->"+message)
        this.$msg({text: message, duration: 3000})
      });
      Bus.$on('success', (message) => {
        // alert("success--->"+message)
        this.$msg({text: message, duration: 3000})
      });
      Bus.$on('loading', (message, isOpen) => {
        // alert("loading--->"+message)
        if(isOpen) {
          this.$msg({text: message})
        } else {
          $(".vue-Message").hide()
        }
      });
    },
    methods: {
    }
  }
</script>
