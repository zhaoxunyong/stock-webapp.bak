<template>
  <div>
    <b-alert :show="dismissCountDown"
             dismissible
             variant="danger"
             title="Error"
             @dismissed="dismissCountdown=0"
             @dismiss-count-down="countDownChanged">
      <!-- Close after {{dismissCountDown}} seconds...<br /> -->
      {{ err }}
      
    </b-alert>
  </div>
</template>

<script>
  import Bus from '../eventBus'
  export default {
    data () {
      return {
        dismissSecs: 5,
        dismissCountDown: 0,
        err: ''
      }
    },
    created () {
      Bus.$on('alerts', (err) => {
        this.err = err.response.statusText +" : "+err.response.data
        this.showAlert()
      });
    },
    methods: {
      countDownChanged (dismissCountDown) {
        this.dismissCountDown = dismissCountDown
      },
      showAlert () {
        this.dismissCountDown = this.dismissSecs
      }
    }
  }
</script>