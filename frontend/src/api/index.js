// 配置API接口地址
// var root = 'https://cnodejs.org/api/v1'
var root = '/'
// 引用axios
var axios = require('axios')
import Bus from '../eventBus'

// 自定义判断元素类型JS
function toType (obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}
// 参数过滤函数
function filterNull (o) {
  for (var key in o) {
    if (o[key] === null) {
      delete o[key]
    }
    if (toType(o[key]) === 'string') {
      o[key] = o[key].trim()
    } else if (toType(o[key]) === 'object') {
      o[key] = filterNull(o[key])
    } else if (toType(o[key]) === 'array') {
      o[key] = filterNull(o[key])
    }
  }
  return o
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
  }  //添加请求头  
  this.$http.post(url,param,config)  
  .then(response=>{  
    console.log(response.data) 
    if(success) {
      success(response.data)
    }
  })
  .catch(function (err) {
      console.log(err)
      let errMsg = err.response.statusText +" : "+err.response.data
      Bus.$emit('alerts', errMsg)
      if(failure) {
        failure(err)
      }
  }) 
}

function apiAxios (method, url, params, success, failure) {
/*  if (params) {
    params = filterNull(params)
  }
  axios.get(url).then(function (res) {
    console.log("res.data---->"+res.data)
    success(res.data)
    
  })*/
  // Bus.$emit('loading', true)
  axios({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: false
  })
  .then(function (res) {
    console.log("res.data---->"+res.data)
    if(success) {
      success(res.data)
    }
    Bus.$emit('loading', false)
    // let content = '<div class="row">'+
    //       '<div class="span4">'+
    //       '  <div class="alert">'+
    //       '    <a class="close" data-dismiss="alert">×</a>'+
    //       '    <strong>Warning!</strong> xxx.'+
    //       '  </div>'+
    //       '</div>'+
    //     '</div>'
    //   $("#error_id").remove()
    // $("body").append(content)
  })
  .catch(function (err) {
    Bus.$emit('loading', false)
    if (err) {
      // window.alert('api error, HTTP CODE: ' + err)
      let errMsg = err.response.statusText +" : "+err.response.data
      Bus.$emit('alerts', errMsg)
      if(failure) {
        failure(err)
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