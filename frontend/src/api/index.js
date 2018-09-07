// 配置API接口地址
// var root = 'https://cnodejs.org/api/v1'
var root = '/'
// 引用axios
var axios = require('axios')
// import Bus from '../eventBus'
import * as alerts from '../utils/alert.js'

// 自定义判断元素类型JS
function toType (obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}

/*
  接口处理函数
  这个函数每个项目都是不一样的，我现在调整的是适用于
  https://cnodejs.org/api/v1 的接口，如果是其他接口
  需要根据接口的参数进行调整。参考说明文档地址：
  https://cnodejs.org/topic/5378720ed6e2d16149fa16bd
  主要是，不同的接口的成功标识和失败提示是不一致的。
  另外，不同的项目的处理方法也是不一致的，这里出错就是简单的alert
*/

function fileUpload(file, url, success, failure) {
  let param = new FormData() //创建form对象
  param.append('file',file,file.name)//通过append向form对象添加数据
  param.append('chunk','0')//添加form表单中其他数据

  let config = {
    headers:{'Content-Type':'multipart/form-data'}
  }
  alerts.loadingStart()
  axios.post(url,param,config)
  .then(response=>{
    // console.log(response.data)
    alerts.loadingEnd()
    if(success) {
      success(response.data)
    }
  })
  .catch(function (err) {
      alerts.loadingEnd()
      console.log("err--->"+err)
      let errMsg = err.response.statusText +" : "+err.response.data
      if(failure) {
        failure(err)
      }
  })
}

function apiAxios (method, url, params, success, failure) {
  alerts.loadingStart()
  axios({
    method: method,
    headers : {
      'X-Requested-With' : 'XMLHttpRequest'
    },
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: false
  })
  .then(function (res) {
    // console.log("res.data---->"+res.data)
    alerts.loadingEnd()
    if(success) {
      success(res.data)
    }
  })
  .catch(function (err) {
    alerts.loadingEnd()
    if (err) {
        console.log("err--->"+err)
        console.log("failure--->"+failure)
        console.log("err.response.statusText--->"+err.response.statusText)
        console.log("err.response.data--->"+err.response.data)
      if(failure) {
        console.log("1--->")
        failure(err)
      } else if('Unauthorized' == err.response.statusText || err.response.data.indexOf("code: 906,") != -1) {
        console.log("2--->")
        alerts.error('會話超時，請重新登錄！')
        window.location.href = "/#/login"
      } else {
        console.log("3--->")
        // window.alert('api error, HTTP CODE: ' + err)
        let errMsg = err.response.statusText +" : "+err.response.data
        alerts.error(errMsg)
      }
    }
  })
}

// 返回在vue模板中的调用接口
export default {
  get: function (url, params, success, failure) {
    return apiAxios('GET', url, params, success, failure)
  },
  post: function (url, params, success, failure) {
    return apiAxios('POST', url, params, success, failure)
  },
  put: function (url, params, success, failure) {
    return apiAxios('PUT', url, params, success, failure)
  },
  delete: function (url, params, success, failure) {
    return apiAxios('DELETE', url, params, success, failure)
  },
  fileUpload: function(file, url, success, failure) {
    return fileUpload(file, url, success, failure)
  }
}
