<template>
  <div class="text-center">
    <form class="form-signin" @submit.stop.prevent="login">
      <img class="mb-4" src="/static/image/login.png" alt="" width="120" height="120">
      <!-- <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1> -->
      <label for="inputEmail" class="sr-only">loginName</label>
      <input type="loginName" id="inputEmail" v-model="loginName" class="form-control" placeholder="用戶" required autofocus>
      <label for="inputPassword" class="sr-only">password</label>
      <input type="password" id="inputPassword" v-model="password" class="form-control" placeholder="密碼" required>
      <!--<div class="checkbox mb-3">-->
        <!--<label>-->
          <!--<input type="checkbox" value="remember-me"> 記住我-->
        <!--</label>-->
      <!--</div>-->
      <button class="btn btn-lg btn-primary btn-block" type="submit">登錄</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
  </div>
</template>
<script>
  import Bus from '../eventBus'
  export default {
    components: {
      // Autocomplete
    },
    data () {
      return {
        loginName: '',
        password: ''
      }
    },
    created () {
    },
    methods: {
      login () {
        let url = '/api/login?loginName='+this.loginName+'&password='+this.password
        this.$api.post(url, null, rs => {
          window.location.href = "/#/index"
        }, ex => {
          Bus.$emit('alerts', "賬戶或密碼錯誤！")
        });
      }
    }
  }
</script>

<style scoped>
  html,
  body {
    height: 100%;
  }

  body {
    display: -ms-flexbox;
    display: -webkit-box;
    display: flex;
    -ms-flex-align: center;
    -ms-flex-pack: center;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
  }

  .form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
  }
  .form-signin .checkbox {
    font-weight: 400;
  }
  .form-signin .form-control {
    position: relative;
    box-sizing: border-box;
    height: auto;
    padding: 10px;
    font-size: 16px;
  }
  .form-signin .form-control:focus {
    z-index: 2;
  }
  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }
  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
</style>
