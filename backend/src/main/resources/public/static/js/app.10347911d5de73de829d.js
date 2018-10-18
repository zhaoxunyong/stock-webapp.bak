webpackJsonp([1],{

/***/ "1/oy":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "2Rhn":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "3URq":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "4V5R":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "8e4C":
/***/ (function(module, exports) {

// 分页显示20条
window.PAGE_SIZE = 20;
// K线图获取最近xx天的历史数据
window.RECENT_DATE = -88;

window.STOCK_CONFIG = {
    // barCategoryGap则用于设置不同类目之间的间距
    barCategoryGap: "2px",
    barWidth: '90%', //指定柱宽度
    col: {
        // up: 'rgb(153, 14, 14)',
        // down: '#19b34c',
        // k
        up: 'red',
        down: '#00008b', // 深蓝
        m5: '#ec63a7',
        m10: '#f5cc65',
        m20: '#1e88e5',
        m60: 'purple',
        // vol
        volup: 'red', // red
        voldown: '#30d94c', // 深蓝
        // rsi
        rsi12: '#30d94c', // green
        rsi100: 'red', // red
        // dmi
        mdi: '#2E2EFE',
        pdi: '#FE9A2E',
        adx: '#B45F04',
        // macd
        oscup: 'red', // red
        oscdown: '#30d94c', // green
        dif: 'orange',
        macd: 'blue'
        // y: '#ffefef'
    },
    // bg: '#000',
    st: 43,
    ed: 100
};

/***/ }),

/***/ "9M+g":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "9kpU":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "A/d9":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "DwKh":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "DwTE":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "GfHa":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "H0jI":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "Id91":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "NHnr":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
var utils_alert_namespaceObject = {};
__webpack_require__.d(utils_alert_namespaceObject, "error", function() { return error; });
__webpack_require__.d(utils_alert_namespaceObject, "success", function() { return alert_success; });
__webpack_require__.d(utils_alert_namespaceObject, "loadingStart", function() { return loadingStart; });
__webpack_require__.d(utils_alert_namespaceObject, "loadingEnd", function() { return loadingEnd; });

// EXTERNAL MODULE: ./node_modules/vue/dist/vue.esm.js
var vue_esm = __webpack_require__("7+uW");

// CONCATENATED MODULE: ./src/eventBus.js
/**
 * Created by gubaoer on 17/4/21.
 */


var Bus = new vue_esm["default"]();
/* harmony default export */ var eventBus = (Bus);
// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/components/alert.vue
//
//
//
//
//
//


/* harmony default export */ var components_alert = ({
  data: function data() {
    return {};
  },
  created: function created() {
    var _this = this;

    eventBus.$on('alerts', function (message) {
      // alert("alerts--->"+message)
      _this.$msg({ text: message, duration: 3000 });
    });
    eventBus.$on('success', function (message) {
      // alert("success--->"+message)
      _this.$msg({ text: message, duration: 3000 });
    });
    eventBus.$on('loading', function (message, isOpen) {
      // alert("loading--->"+message)
      if (isOpen) {
        _this.$msg({ text: message });
      } else {
        $(".vue-Message").hide();
      }
    });
  },

  methods: {}
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-af5823a0","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/components/alert.vue
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div')}
var staticRenderFns = []
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ var selectortype_template_index_0_src_components_alert = (esExports);
// CONCATENATED MODULE: ./src/components/alert.vue
var normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var __vue_template_functional__ = false
/* styles */
var __vue_styles__ = null
/* scopeId */
var __vue_scopeId__ = null
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  components_alert,
  selectortype_template_index_0_src_components_alert,
  __vue_template_functional__,
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)

/* harmony default export */ var src_components_alert = (Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/App.vue
//
//
//
//
//
//
//


/* harmony default export */ var App = ({
  name: 'app',
  components: {
    Alert: src_components_alert
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-d3e3d4fe","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/App.vue
var App_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',{attrs:{"id":"app"}},[_c('Alert'),_vm._v(" "),_c('router-view')],1)}
var App_staticRenderFns = []
var App_esExports = { render: App_render, staticRenderFns: App_staticRenderFns }
/* harmony default export */ var selectortype_template_index_0_src_App = (App_esExports);
// CONCATENATED MODULE: ./src/App.vue
function injectStyle (ssrContext) {
  __webpack_require__("VJET")
}
var App_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var App___vue_template_functional__ = false
/* styles */
var App___vue_styles__ = injectStyle
/* scopeId */
var App___vue_scopeId__ = null
/* moduleIdentifier (server only) */
var App___vue_module_identifier__ = null
var App_Component = App_normalizeComponent(
  App,
  selectortype_template_index_0_src_App,
  App___vue_template_functional__,
  App___vue_styles__,
  App___vue_scopeId__,
  App___vue_module_identifier__
)

/* harmony default export */ var src_App = (App_Component.exports);

// EXTERNAL MODULE: ./node_modules/vue-router/dist/vue-router.esm.js
var vue_router_esm = __webpack_require__("/ocq");

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/sign-in/index.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ var sign_in = ({
  components: {
    // Autocomplete
  },
  data: function data() {
    return {
      loginName: '',
      password: ''
    };
  },
  created: function created() {},

  methods: {
    login: function login() {
      var url = '/api/login?loginName=' + this.loginName + '&password=' + this.password;
      this.$api.post(url, null, function (rs) {
        window.location.href = "/#/index";
      }, function (ex) {
        eventBus.$emit('alerts', "賬戶或密碼錯誤！");
      });
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-0eab4da9","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/sign-in/index.vue
var sign_in_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',{staticClass:"text-center"},[_c('form',{staticClass:"form-signin",on:{"submit":function($event){$event.stopPropagation();$event.preventDefault();return _vm.login($event)}}},[_c('img',{staticClass:"mb-4",attrs:{"src":"/static/image/login.png","alt":"","width":"120","height":"120"}}),_vm._v(" "),_c('label',{staticClass:"sr-only",attrs:{"for":"inputEmail"}},[_vm._v("loginName")]),_vm._v(" "),_c('input',{directives:[{name:"model",rawName:"v-model",value:(_vm.loginName),expression:"loginName"}],staticClass:"form-control",attrs:{"type":"loginName","id":"inputEmail","placeholder":"用戶","required":"","autofocus":""},domProps:{"value":(_vm.loginName)},on:{"input":function($event){if($event.target.composing){ return; }_vm.loginName=$event.target.value}}}),_vm._v(" "),_c('label',{staticClass:"sr-only",attrs:{"for":"inputPassword"}},[_vm._v("password")]),_vm._v(" "),_c('input',{directives:[{name:"model",rawName:"v-model",value:(_vm.password),expression:"password"}],staticClass:"form-control",attrs:{"type":"password","id":"inputPassword","placeholder":"密碼","required":""},domProps:{"value":(_vm.password)},on:{"input":function($event){if($event.target.composing){ return; }_vm.password=$event.target.value}}}),_vm._v(" "),_c('button',{staticClass:"btn btn-lg btn-primary btn-block",attrs:{"type":"submit"}},[_vm._v("登錄")]),_vm._v(" "),_c('p',{staticClass:"mt-5 mb-3 text-muted"},[_vm._v("© 2017-2018")])])])}
var sign_in_staticRenderFns = []
var sign_in_esExports = { render: sign_in_render, staticRenderFns: sign_in_staticRenderFns }
/* harmony default export */ var selectortype_template_index_0_src_sign_in = (sign_in_esExports);
// CONCATENATED MODULE: ./src/sign-in/index.vue
function sign_in_injectStyle (ssrContext) {
  __webpack_require__("H0jI")
}
var sign_in_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var sign_in___vue_template_functional__ = false
/* styles */
var sign_in___vue_styles__ = sign_in_injectStyle
/* scopeId */
var sign_in___vue_scopeId__ = "data-v-0eab4da9"
/* moduleIdentifier (server only) */
var sign_in___vue_module_identifier__ = null
var sign_in_Component = sign_in_normalizeComponent(
  sign_in,
  selectortype_template_index_0_src_sign_in,
  sign_in___vue_template_functional__,
  sign_in___vue_styles__,
  sign_in___vue_scopeId__,
  sign_in___vue_module_identifier__
)

/* harmony default export */ var src_sign_in = (sign_in_Component.exports);

// EXTERNAL MODULE: ./node_modules/stock-auto-complete/dist/build.js
var build = __webpack_require__("UxJx");
var build_default = /*#__PURE__*/__webpack_require__.n(build);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/components/header.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//



/* harmony default export */ var header = ({
  components: {
    Autocomplete: build_default.a
  },
  data: function data() {
    return {};
  },
  created: function created() {},

  methods: {
    getUrl: function getUrl(input) {
      return '/api/stock/search4StockData?query=' + input;
    },
    selectedProcess: function selectedProcess(result, refs) {
      refs.clear();
      this.push('/content/' + result.value + '/1');
      eventBus.$emit('selectedProcess');
      /*$(".form-control input[type='hidden']").each(function(index, data){
        let inputValue = $(data).val()
        // alert("value->"+result.value+"/inputValue->"+inputValue)
        if(inputValue != "" && inputValue == result.value) {
          refs.clear()
          // alert(result.display+"已經存在!")
          Bus.$emit('alerts', result.display+"已經存在!")
        }
      })*/
    },
    formattedDisplay: function formattedDisplay(result) {
      return result.no + ' ' + result.company;
    },
    push: function push(url) {
      this.$router.push(url);
      eventBus.$emit('initCurrentPage', 1);
    },
    go: function go(url) {
      // '/content/' + i.stockId+'/1'
      this.$router.push(url);
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-6bae5b6f","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/components/header.vue
var header_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('b-navbar',{attrs:{"type":"dark","variant":"primary","toggleable":""}},[_c('b-navbar-toggle',{attrs:{"target":"nav_dropdown_collapse"}}),_vm._v(" "),_c('b-collapse',{attrs:{"is-nav":"","id":"nav_dropdown_collapse"}},[_c('b-navbar-nav',{staticClass:"pl-5"},[_c('b-nav-item',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.go('/')}}},[_vm._v("自選股")]),_vm._v(" "),_c('b-nav-item',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.go('/importantNews/1')}}},[_vm._v("市場重大新聞")]),_vm._v(" "),_c('b-nav-item-dropdown',{attrs:{"text":"個性化設置","right":""}},[_c('b-dropdown-item',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.go('/newsSettings')}}},[_vm._v("\n          新聞設置\n        ")]),_vm._v(" "),_c('b-dropdown-item',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.go('/editMySelectedType')}}},[_vm._v("\n          自選股設置\n        ")]),_vm._v(" "),_c('b-dropdown-item',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.go('/stockDailyTransactions')}}},[_vm._v("\n          交易資料導入與查詢\n        ")])],1)],1),_vm._v(" "),_c('b-navbar-nav',[_c('autocomplete',{ref:"autocomplete",attrs:{"source":_vm.getUrl,"input-class":"form-control empty-form-control","results-property":"data","results-display":_vm.formattedDisplay},on:{"selected":_vm.selectedProcess}})],1)],1)],1)],1)}
var header_staticRenderFns = []
var header_esExports = { render: header_render, staticRenderFns: header_staticRenderFns }
/* harmony default export */ var components_header = (header_esExports);
// CONCATENATED MODULE: ./src/components/header.vue
function header_injectStyle (ssrContext) {
  __webpack_require__("DwTE")
}
var header_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var header___vue_template_functional__ = false
/* styles */
var header___vue_styles__ = header_injectStyle
/* scopeId */
var header___vue_scopeId__ = "data-v-6bae5b6f"
/* moduleIdentifier (server only) */
var header___vue_module_identifier__ = null
var header_Component = header_normalizeComponent(
  header,
  components_header,
  header___vue_template_functional__,
  header___vue_styles__,
  header___vue_scopeId__,
  header___vue_module_identifier__
)

/* harmony default export */ var src_components_header = (header_Component.exports);

// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-59015552","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/components/footer.vue
var footer_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',{staticClass:"copy"},[_vm._v("\n  Copy © wenchun\n")])}
var footer_staticRenderFns = []
var footer_esExports = { render: footer_render, staticRenderFns: footer_staticRenderFns }
/* harmony default export */ var footer = (footer_esExports);
// CONCATENATED MODULE: ./src/components/footer.vue
var footer_normalizeComponent = __webpack_require__("VU/8")
/* script */
var __vue_script__ = null
/* template */

/* template functional */
var footer___vue_template_functional__ = false
/* styles */
var footer___vue_styles__ = null
/* scopeId */
var footer___vue_scopeId__ = null
/* moduleIdentifier (server only) */
var footer___vue_module_identifier__ = null
var footer_Component = footer_normalizeComponent(
  __vue_script__,
  footer,
  footer___vue_template_functional__,
  footer___vue_styles__,
  footer___vue_scopeId__,
  footer___vue_module_identifier__
)

/* harmony default export */ var components_footer = (footer_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/layouts/Main.vue
//
//
//
//
//
//
//
//




/* harmony default export */ var Main = ({
  components: {
    Header: src_components_header, Footer: components_footer
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-66e11817","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/layouts/Main.vue
var Main_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('Header'),_vm._v(" "),_vm._t("default"),_vm._v(" "),_c('Footer')],2)}
var Main_staticRenderFns = []
var Main_esExports = { render: Main_render, staticRenderFns: Main_staticRenderFns }
/* harmony default export */ var layouts_Main = (Main_esExports);
// CONCATENATED MODULE: ./src/layouts/Main.vue
var Main_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var Main___vue_template_functional__ = false
/* styles */
var Main___vue_styles__ = null
/* scopeId */
var Main___vue_scopeId__ = null
/* moduleIdentifier (server only) */
var Main___vue_module_identifier__ = null
var Main_Component = Main_normalizeComponent(
  Main,
  layouts_Main,
  Main___vue_template_functional__,
  Main___vue_styles__,
  Main___vue_scopeId__,
  Main___vue_module_identifier__
)

/* harmony default export */ var src_layouts_Main = (Main_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/StockMyData.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//



// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'

// import Autocomplete from '../components/Autocomplete'
/* harmony default export */ var StockMyData = ({
  components: {
    Autocomplete: build_default.a
  },
  data: function data() {
    return {
      list: [],
      firstStockId: '',
      myStockSelectedName: '庫存股',
      // selected: null,
      // options: [],
      // 所有自选股标签
      selectedTypes: [],
      // 某只股票的自选股标签
      mySelectedTypes: [],
      items: []
    };
  },
  created: function created() {
    var _this = this;

    // 获取添加到自选股时的下拉选项
    this.getStockMySelectedTypes();
    this.getData();

    // 从stockmyselectedtype.vue中过来：当点击所有按扭时
    /*Bus.$on('getAllMyStockData', () => {
      this.myStockSelectedName = '所有'
      this.getData()
    });*/

    // 从stockmyselectedtype.vue中过来：当将某个股票从某个自选股中移除时
    /*Bus.$on('reGetStockMySelectedTypes', () => {
      this.getStockMySelectedTypes()
    });*/

    // 从stockmyselectedtype.vue中过来：当点击某个自选股标签时
    eventBus.$on('getMyStockSelected', function (type, name) {
      if (type != undefined) {
        _this.myStockSelectedName = name;
        _this.$api.get('/api/stock/getStockMyDatasByType/' + type, null, function (r) {
          _this.list = r;
          if (r != undefined && r.length > 0) {
            _this.firstStockId = r[0].stockId;
            // let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
            //改变路由的地址
            _this.push('/content/' + _this.firstStockId + '/1');
            // Bus.$emit('setStock', this.firstStockId)
            //自动将自选股类型选中
            // Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
          } else {
            _this.push('/content/0/1');
            eventBus.$emit('emptyNews');
            // Bus.$emit('deliverySelectedTypes', [])
          }
        });
      }
    });

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    eventBus.$on('getAllStockMyStore', function () {
      _this.myStockSelectedName = '庫存股';
      // this.myStockSelectedName = name
      _this.$api.get('/api/stock/getStockMyDatasByStore', null, function (r) {
        _this.list = r;
        if (r != undefined && r.length > 0) {
          _this.firstStockId = r[0].stockId;
          // let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
          //改变路由的地址
          _this.push('/content/' + _this.firstStockId + '/1');
          // Bus.$emit('setStock', this.firstStockId)
          //自动将自选股类型选中
          // Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
        } else {
          _this.push('/content/0/1');
          eventBus.$emit('emptyNews');
          // Bus.$emit('deliverySelectedTypes', [])
        }
      });
    });
  },

  methods: {
    push: function push(url) {
      this.$router.push(url);
      eventBus.$emit('initCurrentPage', 1);
    },

    // 将当前股票高亮显示
    isSelected: function isSelected(_stockId) {
      var stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId;
      if (_stockId == stockId) {
        return 'selected';
      }
      return '';
    },


    // 点击某个股票
    go: function go(stockId) {
      // '/content/' + i.stockId+'/1'
      this.push('/content/' + stockId + '/1');
      //自动将自选股类型选中
      // Bus.$emit('deliverySelectedTypes', selectedTypes)
    },


    // 上一个股票
    toFront: function toFront() {
      var _router = this.$router;
      var obj = $(".selected").get(0);
      var aObj = $(obj).closest("span").prev().find("a");
      var href = aObj.attr('href');
      if (href != undefined) {
        this.push(href);
      }
    },


    // 下一个股票
    toBack: function toBack() {
      var _router = this.$router;
      var obj = $(".selected").get(0);
      var aObj = $(obj).closest("span").next().find("a");
      var href = aObj.attr('href');
      if (href != undefined) {
        this.push(href);
      }
    },
    openModal: function openModal() {
      this.getStockMySelectedTypes();
      // this.clearName()
    },

    // clearName () {
    //   this.selected = ''
    // },
    handleOk: function handleOk(evt) {
      // // Prevent modal from closing
      // evt.preventDefault()
      // if (!this.selected) {
      //   alert('請選擇自選股!')
      // } else {
      //   this.handleSubmit()
      // }
    },

    // handleSubmit (evt) {
    changeType: function changeType(selectedType, selectedName) {
      //this.names.push(this.name)
      var stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId;
      // alert(stockId +'/' + this.selected)
      this.changeStockMySelected(stockId, selectedType, selectedName);
      // this.clearName()
    },

    // 第一次加载数据
    getData: function getData() {
      var _this2 = this;

      this.$api.get('/api/stock/getStockMyDatasByStore', null, function (r) {
        _this2.list = r;
        _this2.firstStockId = r[0].stockId;
        // let stockId = this.$route.params.stockId == undefined || this.$route.params.stockId == 0 ? this.firstStockId : this.$route.params.stockId
        //改变路由的地址
        _this2.push('/content/' + _this2.firstStockId + '/1');
      });
    },
    getStockMySelectedTypes: function getStockMySelectedTypes() {
      var _this3 = this;

      // this.options = []
      this.items = [];
      var stockId = this.$route.params.stockId;
      if (stockId != undefined && stockId != "") {
        this.$api.get('/api/stock/getMySelectedTypesByStockId/' + stockId, null, function (rs) {

          var mySelectedType = [];
          if (rs != undefined && rs.length > 0) {
            for (var i = 0; i < rs.length; i++) {
              var type = rs[i].type;
              mySelectedType.push(type);
            }
          }

          var url = '/api/stock/getStockMySelectedTypes';
          // this.$api.get(url, null, rs => {
          //   this.items = rs
          // })
          _this3.$api.get(url, null, function (rs) {
            for (var i = 0; i < rs.length; i++) {
              var isDisabled = mySelectedType.indexOf(rs[i].type) != -1;
              if (!isDisabled) {
                // this.options.push({
                //   value: rs[i].type, text: rs[i].name, disabled:isDisabled
                // })
                _this3.items.push(rs[i]);
              }
            }
          });
        });
      }
    },
    changeStockMySelected: function changeStockMySelected(stockId, selectedType, myStockSelectedName) {
      var _this4 = this;

      var url = '/api/stock/changeStockMySelected';
      var stockIds = [];
      stockIds.push(stockId);
      var params = {
        "selectedType": selectedType,
        "stockIds": stockIds
      };
      this.$api.post(url, params, function (rs) {
        //改变路由的地址
        eventBus.$emit('success', "保存成功!");
        // 自動選擇右邊的自選股
        eventBus.$emit('autoSelectedMyStockSelectedType', selectedType, myStockSelectedName);
        // this.push('/content/' + stockId+'/1')
        // location.reload()
        _this4.$refs.modal.hide();
      });
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-4e7f2138","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/StockMyData.vue
var StockMyData_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('div',{staticClass:"pt-2 pb-2"},[_c('img',{staticStyle:{"width":"35px","height":"35px","cursor":"pointer"},attrs:{"src":"static/image/left.png"},on:{"click":_vm.toFront}}),_vm._v(" "),_c('span',{staticStyle:{"padding":"0px 0px"}}),_vm._v(" "),_c('img',{staticStyle:{"width":"35px","height":"35px","cursor":"pointer"},attrs:{"src":"static/image/right.png"},on:{"click":_vm.toBack}}),_vm._v(" "),_c('b-modal',{ref:"modal",attrs:{"id":"modalPrevent","title":"請選擇自選股","hide-footer":""},on:{"ok":_vm.handleOk,"shown":_vm.openModal}},_vm._l((_vm.items),function(item){return _c('span',[_c('b-button',{staticClass:"m-1",attrs:{"variant":"success"},on:{"click":function($event){_vm.changeType(item.type, item.name)}}},[_vm._v(_vm._s(item.name))])],1)}))],1),_vm._v(" "),_c('div',{staticClass:"selected_name text"},[_c('h5',[_vm._v(_vm._s(_vm.myStockSelectedName))]),_vm._v(" "),_vm._l((_vm.list),function(i){return _c('span',{staticClass:"d-lg-block"},[_c('a',{class:_vm.isSelected(i.stockId),attrs:{"href":'/content/' + i.stockId+'/1'},on:{"click":function($event){$event.preventDefault();_vm.go(i.stockId)}}},[_vm._v("\n      "+_vm._s(i.company)+"\n    ")]),_c('br')])})],2)])}
var StockMyData_staticRenderFns = []
var StockMyData_esExports = { render: StockMyData_render, staticRenderFns: StockMyData_staticRenderFns }
/* harmony default export */ var page_StockMyData = (StockMyData_esExports);
// CONCATENATED MODULE: ./src/page/StockMyData.vue
function StockMyData_injectStyle (ssrContext) {
  __webpack_require__("ZWwq")
}
var StockMyData_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var StockMyData___vue_template_functional__ = false
/* styles */
var StockMyData___vue_styles__ = StockMyData_injectStyle
/* scopeId */
var StockMyData___vue_scopeId__ = "data-v-4e7f2138"
/* moduleIdentifier (server only) */
var StockMyData___vue_module_identifier__ = null
var StockMyData_Component = StockMyData_normalizeComponent(
  StockMyData,
  page_StockMyData,
  StockMyData___vue_template_functional__,
  StockMyData___vue_styles__,
  StockMyData___vue_scopeId__,
  StockMyData___vue_module_identifier__
)

/* harmony default export */ var src_page_StockMyData = (StockMyData_Component.exports);

// EXTERNAL MODULE: ./node_modules/babel-runtime/helpers/defineProperty.js
var defineProperty = __webpack_require__("bOdI");
var defineProperty_default = /*#__PURE__*/__webpack_require__.n(defineProperty);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/stockMySelectedType.vue

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ var stockMySelectedType = ({
  data: function data() {
    return defineProperty_default()({
      name: '',
      items: [],
      currSelectedType: ''
    }, 'name', '');
  },
  created: function created() {
    var _this = this;

    // this.autoSelectedTypes()
    this.getData();

    // 從changeStockMySelected中trigger過來
    // autoSelectedMyStockSelectedType (selectedType) {
    //   alert(selectedType)
    // },
    eventBus.$on('autoSelectedMyStockSelectedType', function (selectedType, myStockSelectedName) {
      _this.currSelectedType = selectedType;
      eventBus.$emit('getMyStockSelected', selectedType, myStockSelectedName);
    });
  },

  methods: {
    // 自动高亮显示某个股票所属的自选股名称
    /*autoSelectedTypes() {
      this.selectedTypes = []
      let stockId = this.$route.params.stockId
      if(stockId != undefined && stockId != "") {
        this.$api.get('/api/stock/getMySelectedTypesByStockId/'+stockId, null, rs => {
          if(rs != undefined && rs.length>0) {
            for(var i=0;i<rs.length;i++) {
              let type = rs[i].type
              this.selectedTypes.push(type)
            }
          }
        })
      }
    },*/
    // 触发stockmydata.vue重新摘取所有的自选股
    /*getAllMyStockData() {
      this.selectedTypes = []
      Bus.$emit('getAllMyStockData')
    },  */
    // 触发stockmydata.vue重新摘取所有的sotre股
    getAllStockMyStore: function getAllStockMyStore() {
      this.selectedTypes = [];
      this.currSelectedType = '0';
      eventBus.$emit('getAllStockMyStore');
    },

    // 触发stockmydata.vue重新摘取某个自选股中的所有股票
    getMyStockSelected: function getMyStockSelected(type, name) {
      this.currSelectedType = type;
      eventBus.$emit('getMyStockSelected', type, name);
    },
    isSelected: function isSelected(type) {
      // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if (this.currSelectedType == type) {
        return 'warning';
      }
      return 'success';
    },
    clearName: function clearName() {
      this.name = '';
      this.$refs.focusThis.focus();
    },
    handleOk: function handleOk(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name) {
        alert('请输入自選股名称!');
      } else {
        this.handleSubmit();
      }
    },
    handleSubmit: function handleSubmit() {
      // this.names.push(this.name)
      this.saveData(this.name);
      this.clearName();
      this.$refs.modal.hide();
    },
    getData: function getData() {
      var _this2 = this;

      var url = '/api/stock/getStockMySelectedTypes';
      this.$api.get(url, null, function (rs) {
        _this2.items = rs;
      });
    },
    saveData: function saveData(name) {
      var _this3 = this;

      var url = '/api/stock/saveStockMySelectedType?name=' + name;
      this.$api.post(url, null, function (rs) {
        _this3.getData();
        // Bus.$emit('reGetStockMySelectedTypes')
      });
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route': function $route(to, from) {
      // this.autoSelectedTypes()
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-02b78d8a","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/stockMySelectedType.vue
var stockMySelectedType_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('span',[_c('b-button',{attrs:{"variant":_vm.currSelectedType=='' || _vm.currSelectedType=='0'?'warning':'success'},on:{"click":_vm.getAllStockMyStore}},[_vm._v("\n    庫存股\n  ")])],1),_vm._v(" "),_vm._l((_vm.items),function(item){return _c('span',[_c('b-button',{attrs:{"variant":_vm.isSelected(item.type),"id":item.type},on:{"click":function($event){_vm.getMyStockSelected(item.type, item.name)}}},[_vm._v("\n      "+_vm._s(item.name)+"\n      ")])],1)}),_vm._v(" "),_c('b-btn',{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent2",modifiers:{"modalPrevent2":true}}],attrs:{"variant":"success"}},[_vm._v("+")]),_vm._v(" "),_c('b-modal',{ref:"modal",attrs:{"id":"modalPrevent2","title":"请输入自選股名称"},on:{"ok":_vm.handleOk,"shown":_vm.clearName}},[_c('form',{on:{"submit":function($event){$event.stopPropagation();$event.preventDefault();return _vm.handleSubmit($event)}}},[_c('b-form-input',{ref:"focusThis",attrs:{"type":"text","placeholder":"输入自選股名称"},model:{value:(_vm.name),callback:function ($$v) {_vm.name=$$v},expression:"name"}})],1)])],2)}
var stockMySelectedType_staticRenderFns = []
var stockMySelectedType_esExports = { render: stockMySelectedType_render, staticRenderFns: stockMySelectedType_staticRenderFns }
/* harmony default export */ var page_stockMySelectedType = (stockMySelectedType_esExports);
// CONCATENATED MODULE: ./src/page/stockMySelectedType.vue
function stockMySelectedType_injectStyle (ssrContext) {
  __webpack_require__("ws5n")
}
var stockMySelectedType_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var stockMySelectedType___vue_template_functional__ = false
/* styles */
var stockMySelectedType___vue_styles__ = stockMySelectedType_injectStyle
/* scopeId */
var stockMySelectedType___vue_scopeId__ = "data-v-02b78d8a"
/* moduleIdentifier (server only) */
var stockMySelectedType___vue_module_identifier__ = null
var stockMySelectedType_Component = stockMySelectedType_normalizeComponent(
  stockMySelectedType,
  page_stockMySelectedType,
  stockMySelectedType___vue_template_functional__,
  stockMySelectedType___vue_styles__,
  stockMySelectedType___vue_scopeId__,
  stockMySelectedType___vue_module_identifier__
)

/* harmony default export */ var src_page_stockMySelectedType = (stockMySelectedType_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/stockDetail.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ var stockDetail = ({
  data: function data() {
    return {
      stockId: '',
      // stockData: null,
      company: '',
      no: '',
      type: '',
      typeName: '',
      electronics: '',
      currSelectedType: '',
      currSelectedName: '',
      companyStatus: ''
    };
  },
  created: function created() {
    var _this = this;

    this.stockId = this.$route.params.stockId;
    this.getData(this.stockId);

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    eventBus.$on('getAllStockMyStore', function () {
      // 主要記錄一下對應的selectedType是哪個，用於移除自選股時使用
      _this.currSelectedType = '';
      _this.currSelectedName = '';
    });
    // 从stockmyselectedtype.vue中过来：当点击某个自选股标签时
    eventBus.$on('getMyStockSelected', function (type, name) {
      // 主要記錄一下對應的selectedType是哪個，用於移除自選股時使用
      _this.currSelectedType = type;
      _this.currSelectedName = name;
    });
  },
  mounted: function mounted() {},

  methods: {
    removeOneStockMySelected: function removeOneStockMySelected() {
      this.stockId = this.$route.params.stockId;
      //alert(this.stockId+"/"+this.currSelectedType)
      if (this.currSelectedType == '' || this.currSelectedType == undefined) {
        eventBus.$emit('success', "該股不在某個自選股中，無需移除!");
      } else {
        var $this = this;
        this.$confirm("是否確定從" + $this.currSelectedName + "中移除?").then(function () {
          var url = '/api/stock/removeOneStockMySelected?stockId=' + $this.stockId + "&selectedType=" + $this.currSelectedType;
          $this.$api.post(url, null, function (rs) {
            eventBus.$emit('success', "移除成功!");
            // location.reload();
            // $this.$router.go(this.$router.currentRoute)
            // 自動選擇自選股
            eventBus.$emit('autoSelectedMyStockSelectedType', $this.currSelectedType, $this.currSelectedName);
          });
        }).catch(function (e) {
          // alert("Exception--->"+e)
          console.log("Exception--->" + e);
        });
      }
    },
    saveCompanyStatus: function saveCompanyStatus() {
      this.stockId = this.$route.params.stockId;
      if (this.stockId == undefined || this.stockId == '') {} else {
        var companyStatus = $("#formControlTextarea1").val();
        var url = '/api/stock/saveCompanyStatus?stockId=' + this.stockId + "&companyStatus=" + companyStatus;
        this.$api.post(url, null, function (rs) {
          eventBus.$emit('success', "保存公司現況成功!");
        });
      }
    },
    getData: function getData(stockId) {
      var _this2 = this;

      this.company = '';
      this.no = '';
      this.typeName = '';
      this.electronics = '';
      this.companyStatus = '';
      this.typeName = '';
      this.type = '';
      if (stockId != undefined && stockId != '' && stockId != 0) {
        this.$api.get('/api/stock/getStockData/' + stockId, null, function (stockData) {
          if (stockData != '' && stockData != undefined) {
            // this.stockData = stockData
            _this2.company = stockData.company;
            _this2.no = stockData.no;
            _this2.typeName = stockData.typeName;
            _this2.electronics = stockData.electronics;
            _this2.companyStatus = stockData.companyStatus;
            _this2.typeName = stockData.typeName;
            if (stockData.type == 0) {
              _this2.type = '上市';
            } else if (stockData.type == 1) {
              _this2.type = "上櫃";
            }
          }
        });
      }
    }
  },
  watch: {
    '$route': function $route(to, from) {
      this.stockId = this.$route.params.stockId;
      this.getData(this.stockId);
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-7a9bb3b6","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/stockDetail.vue
var stockDetail_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('div',{staticClass:"float-left mt-1 text-center"},[_c('div',[_c('h4',[_vm._v(_vm._s(_vm.company))]),_vm._v(" "),_c('h6',[_vm._v("("+_vm._s(_vm.no)+")")]),_vm._v(" "),(_vm.type!='')?_c('h6',[_vm._v(_vm._s(_vm.type)+" - "+_vm._s(_vm.typeName))]):_vm._e()])]),_vm._v(" "),_c('div',{staticClass:"float-left pl-2 mt-3"},[_c('div',{staticClass:"py-2"},[_c('a',{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent",modifiers:{"modalPrevent":true}}],attrs:{"href":"#"}},[_c('span',{staticClass:"oi oi-plus"})])]),_vm._v(" "),_c('div',{staticClass:"py-2"},[_c('a',{attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();return _vm.removeOneStockMySelected($event)}}},[_c('span',{staticClass:"oi oi-minus"})])])]),_vm._v(" "),_c('div',{staticClass:"float-left pl-2",staticStyle:{"width":"75%"}},[_c('b-tabs',[_c('b-tab',{attrs:{"title":"法人動態","active":""}}),_vm._v(" "),_c('b-tab',{attrs:{"title":"資卷變化"}}),_vm._v(" "),_c('b-tab',{attrs:{"title":"大戶籌碼"}}),_vm._v(" "),_c('b-tab',{attrs:{"title":"主力進出"}}),_vm._v(" "),_c('b-tab',{attrs:{"title":"周報股利"}}),_vm._v(" "),_c('div',[_c('input',{directives:[{name:"model",rawName:"v-model",value:(_vm.companyStatus),expression:"companyStatus"}],staticClass:"float-left form-control my-3",staticStyle:{"width":"75%"},attrs:{"id":"formControlTextarea1"},domProps:{"value":(_vm.companyStatus)},on:{"input":function($event){if($event.target.composing){ return; }_vm.companyStatus=$event.target.value}}}),_vm._v(" "),_c('b-button',{staticClass:"float-left ml-3 my-3",attrs:{"variant":"success"},on:{"click":_vm.saveCompanyStatus}},[_vm._v("保存")])],1)],1)],1)])}
var stockDetail_staticRenderFns = []
var stockDetail_esExports = { render: stockDetail_render, staticRenderFns: stockDetail_staticRenderFns }
/* harmony default export */ var page_stockDetail = (stockDetail_esExports);
// CONCATENATED MODULE: ./src/page/stockDetail.vue
function stockDetail_injectStyle (ssrContext) {
  __webpack_require__("A/d9")
}
var stockDetail_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var stockDetail___vue_template_functional__ = false
/* styles */
var stockDetail___vue_styles__ = stockDetail_injectStyle
/* scopeId */
var stockDetail___vue_scopeId__ = "data-v-7a9bb3b6"
/* moduleIdentifier (server only) */
var stockDetail___vue_module_identifier__ = null
var stockDetail_Component = stockDetail_normalizeComponent(
  stockDetail,
  page_stockDetail,
  stockDetail___vue_template_functional__,
  stockDetail___vue_styles__,
  stockDetail___vue_scopeId__,
  stockDetail___vue_module_identifier__
)

/* harmony default export */ var src_page_stockDetail = (stockDetail_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/stockNews.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ var stockNews = ({
  data: function data() {
    return {
      company: '',
      stockId: '',
      selectedType: '0',
      numberOfPages: 0,
      currentPage: 1,
      displayAll: 0,
      type: 1,
      fromSelectedProcess: 0,
      pageSize: PAGE_SIZE,
      fields: {
        content_title: {
          label: '<span id="content_id">: </span>個股新聞與研究報告',
          sortable: true
        }
      },
      items: [],
      intervalid1: null
    };
  },
  created: function created() {
    var _this = this;

    this.getData();
    eventBus.$on('emptyNews', function () {
      _this.cleanNews();
    });
    eventBus.$on('initCurrentPage', function (pageNum) {
      _this.currentPage = pageNum;
    });
    // 从左侧搜索后触发
    eventBus.$on('selectedProcess', function () {
      // 自动抓一次数据
      // Bus.$emit('loading', "正在自動獲取最新的新聞中...", true)
      _this.stockId = _this.$route.params.stockId;
      if (_this.stockId != undefined) {
        console.log("News autoFetch stockNews started......" + _this.stockId);
        var url = "/api/stock/fetchLatestNews?stockId=" + _this.stockId;
        // alert("fetchNews=============>"+url)
        _this.$api.post(url, null, function (rs) {
          // Bus.$emit('success', "自動更新新聞成功!")
          _this.getData();
          console.log("News autoFetch stockNews end......" + _this.stockId);
        });
      }
    });
    // 从stockmyselectedtype.vue中过来：当点击某个自选股标签时
    eventBus.$on('getMyStockSelected', function (selectedType, selectedName) {
      if (selectedType == undefined) {
        _this.selectedType = '0';
      } else {
        _this.selectedType = selectedType;
      }
    });

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    eventBus.$on('getAllStockMyStore', function () {
      _this.selectedType = '0';
    });

    // this.timeOutsetInterval()
  },
  mounted: function mounted() {},

  /*destroyed:function(){
    if(this.intervalid1 != null) {
      clearInterval(this.intervalid1)
    }
  },*/
  methods: {
    /*timeOutsetInterval (){
      if(this.intervalid1 != null) {
        clearInterval(this.intervalid1)
      }
      let $this = this
      this.autoFetch($this)
      $this.intervalid1 = setInterval(() => {
        // this.changes = ((Math.random() * 100).toFixed(2))+'%';
        this.autoFetch($this)
      }, 5 * 60 * 1000);
    },
    autoFetch($this) {
      Bus.$emit('loading', "正在自動獲取最新的新聞中...", true)
      this.stockId = this.$route.params.stockId
      if(this.stockId != undefined) {
        console.log("News autoFetch stockNews started......"+this.stockId)
        let url = "/api/stock/fetchLatestNews?stockId="+this.stockId
        // alert("fetchNews=============>"+url)
        $this.$api.post(url, null, rs => {
          Bus.$emit('success', "自動更新新聞成功!")
          this.getData()
          console.log("News autoFetch stockNews end......"+this.stockId)
        })
      }
    },*/
    linkGen: function linkGen(pageNum) {
      return {
        path: '/content/' + this.stockId + '/' + pageNum
      };
    },

    // 第一次加载数据
    cleanNews: function cleanNews() {
      this.items = [];
      $("#content_id").text("");
      this.numberOfPages = 0;
      this.currentPage = 1;
    },
    showExcludeNews: function showExcludeNews(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 1;
      this.displayAll = 0;
      this.getData(1);
    },
    showIncludeNews: function showIncludeNews(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 0;
      this.displayAll = 0;
      this.getData(1);
    },
    showExcludeNewsAll: function showExcludeNewsAll(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 1;
      this.displayAll = 1;
      this.getData(1);
    },
    showIncludeNewsAll: function showIncludeNewsAll(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 0;
      this.displayAll = 1;
      this.getData(1);
    },

    // 第一次加载数据
    getData: function getData(pageNum) {
      var _this2 = this;

      // if(pageNum != undefined) {
      //   this.currentPage = pageNum
      // } else {
      //   this.currentPage = this.$route.params.pageNum
      // }

      if (pageNum == undefined) {
        pageNum = this.$route.params.pageNum;
      }
      // pageNum = this.currentPage
      this.stockId = this.$route.params.stockId;
      if (this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        this.$api.get('/api/stock/getStockData/' + this.stockId, null, function (stockData) {
          _this2.company = stockData.company;
          // let pageNum = this.$route.params.pageNum == undefined ? 1 : this.$route.params.pageNum
          // let rootUrl = type == 0 ? '/api/stock/getNewsIncludeBystockId/' : '/api/stock/getNewsExcludeBystockId/'
          // let url = rootUrl + this.stockId+'/'+pageNum+'/'+this.pageSize
          var url = '';
          if (_this2.displayAll == 1) {
            var paramStockId = 0;
            var rootUrl = _this2.type == undefined || _this2.type == 0 ? '/api/stock/getNewsIncludeBystockId/' : '/api/stock/getNewsExcludeBystockId/';
            url = rootUrl + paramStockId + '/' + _this2.selectedType + '/' + pageNum + '/' + _this2.pageSize;
          } else {
            var _paramStockId = _this2.stockId;
            var _rootUrl = _this2.type == undefined || _this2.type == 0 ? '/api/stock/getNewsIncludeBystockId4All/' : '/api/stock/getNewsExcludeBystockId4All/';
            url = _rootUrl + _paramStockId + '/' + pageNum + '/' + _this2.pageSize;
          }
          // alert("url1--->"+url)
          _this2.$api.get(url, null, function (rs) {
            // this.dat = r
            _this2.numberOfPages = rs.pageTotal;
            var rsData = rs.rows;
            _this2.items = [];
            for (var i = 0; i < rsData.length; i++) {
              var context = "<a target=\"_blank\" href=\"" + rsData[i].url + "\">" + rsData[i].subject + "</a>";
              _this2.items.push({
                content_title: context
              });
            }
          });
          $("#content_id").text(_this2.company + ": ").parent().remove();
        });
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route': function $route(to, from) {
      this.stockId = this.$route.params.stockId;
      if (this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        this.getData();
      } else {
        this.cleanNews();
      }

      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-67c5c47e","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/stockNews.vue
var stockNews_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('div',{staticClass:"container-fluid"},[_c('nav',{staticClass:"nav nav-pills nav-justified"},[_c('div',{staticClass:"pt-2 pr-2"},[_c('h5',[_vm._v(_vm._s(this.company))])]),_vm._v(" "),_c('a',{staticClass:"nav-link active",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showExcludeNews($event)}}},[_vm._v("個股新聞")]),_vm._v(" "),_c('a',{staticClass:"nav-link",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showIncludeNews($event)}}},[_vm._v("焦點新聞")]),_vm._v(" "),_c('a',{staticClass:"nav-link",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showExcludeNewsAll($event)}}},[_vm._v("自選股全新聞")]),_vm._v(" "),_c('a',{staticClass:"nav-link",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showIncludeNewsAll($event)}}},[_vm._v("自選股全焦點")])])]),_vm._v(" "),_c('div',{staticClass:"text container-fluid"},[_c('b-table',{attrs:{"striped":"","hover":"","items":_vm.items,"fields":_vm.fields},scopedSlots:_vm._u([{key:"content_title",fn:function(data){return _c('span',{domProps:{"innerHTML":_vm._s(data.value)}})}}])}),_vm._v(" "),_c('b-pagination-nav',{attrs:{"align":"center","number-of-pages":_vm.numberOfPages,"base-url":"#","link-gen":_vm.linkGen},model:{value:(_vm.currentPage),callback:function ($$v) {_vm.currentPage=$$v},expression:"currentPage"}})],1)])}
var stockNews_staticRenderFns = []
var stockNews_esExports = { render: stockNews_render, staticRenderFns: stockNews_staticRenderFns }
/* harmony default export */ var page_stockNews = (stockNews_esExports);
// CONCATENATED MODULE: ./src/page/stockNews.vue
function stockNews_injectStyle (ssrContext) {
  __webpack_require__("3URq")
}
var stockNews_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var stockNews___vue_template_functional__ = false
/* styles */
var stockNews___vue_styles__ = stockNews_injectStyle
/* scopeId */
var stockNews___vue_scopeId__ = "data-v-67c5c47e"
/* moduleIdentifier (server only) */
var stockNews___vue_module_identifier__ = null
var stockNews_Component = stockNews_normalizeComponent(
  stockNews,
  page_stockNews,
  stockNews___vue_template_functional__,
  stockNews___vue_styles__,
  stockNews___vue_scopeId__,
  stockNews___vue_module_identifier__
)

/* harmony default export */ var src_page_stockNews = (stockNews_Component.exports);

// CONCATENATED MODULE: ./src/utils/stockUtils.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

// rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
// 获取对应的数据格式
function splitData(rawData) {
    var categoryData = [];
    var values = [];
    var vols = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i]);
        vols.push(rawData[i][4]);
        // alert(rawData[i])
    }
    return {
        categoryData: categoryData,
        values: values,
        vols: vols
    };
}
// 平均值
function calculateMA(datas, dayCount) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += datas.values[i - j][1];
        }
        result.push((sum / dayCount).toFixed(2));
    }
    return result;
}

// 单独抽出收盘价
function getCloses(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][1]);
    }
    return result;
}

// 单独抽出最低
function getLows(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][2]);
    }
    return result;
}

// 单独抽出最高
function getHighs(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][3]);
    }
    return result;
}

// 布林通道中的upper
function getBollUppers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].upper);
    }
    return result;
}

// 布林通道中的middle
function getBollMiddles(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].middle);
    }
    return result;
}

// 布林通道中的lower
function getBollLowers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].lower);
    }
    return result;
}

// DMI中的adx
function getDmiAdxs(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].adx);
    }
    return result;
}

// DMI中的mdi
function getDmiMdis(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].mdi);
    }
    return result;
}

// DMI中的pdi
function getDmipdis(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].pdi);
    }
    return result;
}

// 取数据中的最近RECENT_DATE数据
function getSlice(datas) {
    var recentDatas = datas.slice(RECENT_DATE);
    var absRecentDate = Math.abs(RECENT_DATE);
    if (recentDatas.length < absRecentDate) {
        return leftPad(recentDatas, absRecentDate - recentDatas.length, '-');
    }
    return recentDatas;
}

function leftPad(arrays, pad_length, pad_string) {
    for (var i = 0; i < pad_length; i++) {
        arrays.unshift(pad_string);
    }
    return arrays;
}

/* export function getSeriesIndex(tooltipDatas, index) {
    for (var i = 0, len = tooltipDatas.length; i < len; i++) {
        if(tooltipDatas[i].seriesIndex == index) {
            return tooltipDatas[i];
        }
    }
    return null;
} */
// CONCATENATED MODULE: ./src/utils/dateUtils.js
function formatTime(fmt, date1) {
    var date = new Date(Date.parse(date1.replace(/\//g, "-")));
    var o = {
        "M+": date.getMonth() + 1, //月份 
        "d+": date.getDate(), //日 
        "h+": date.getHours(), //小时 
        "m+": date.getMinutes(), //分 
        "s+": date.getSeconds(), //秒 
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    }return fmt;
}
// CONCATENATED MODULE: ./src/data/stockCandle.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13




// import boll from 'bollinger-bands'
// var MACD = require('technicalindicators').MACD
var BB = __webpack_require__("k4Xa").BollingerBands;

function getData(datasets, kineType) {
    var kDisplay = kineType == 1 ? "月" : "日";
    var datas = splitData(datasets);

    var inputBoll = {
        period: 20,
        values: getCloses(datas),
        stdDev: 2

    };
    var bolls = BB.calculate(inputBoll);
    var lowers = getBollLowers(bolls);
    var middles = getBollMiddles(bolls);
    var uppers = getBollUppers(bolls);

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            // formatter: "Series formatter: <br/>{a}<br/>{b}<br/>{c}",
            // 数据结构：dataIndex/opening/closing/lowest/highest/vol
            // formatter: "{b} 收 {c2} 開 {c1} 高 {c4} 低 {c3}",
            formatter: function formatter(params) {
                // rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
                // let data0 = stockUtils.getSeriesIndex(params,0)
                // 83, 72.5, 70, 67, 73.8, "16032"
                var v = params[0].axisValue + ' \n                \u6536: ' + params[0].data[2] + ' \n                \u958B: ' + params[0].data[1] + ' \n                \u9AD8: ' + params[0].data[4] + ' \n                \u4F4E: ' + params[0].data[3] + '<br/>\n                ' + kDisplay + '\u7DDA \n                <font color="' + STOCK_CONFIG.col.m5 + '">M5:</font> ' + params[1].value + ' \n                <font color="' + STOCK_CONFIG.col.m10 + '">M10:</font> ' + params[2].value + ' \n                <font color="' + STOCK_CONFIG.col.m20 + '">M20:</font> ' + params[3].value + ' \n                <font color="' + STOCK_CONFIG.col.m60 + '">M60:</font> ' + params[4].value;
                $("#tooltipId1" + kineType).html(v);
                // return `<font color="read">${params[0].data}</font>`;
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        /* legend: {
            // type: 'scroll',
            // orient: 'vertical',
            // left: 'center',
            data: [kDisplay+'K', 
                '5'+kDisplay+'平均线', 
                '10'+kDisplay+'平均线', 
                '20'+kDisplay+'平均线', 
                '60'+kDisplay+'平均线', 
                '布林通道1', 
                '布林通道2', 
                '布林通道3',]
        }, */
        grid: [{
            top: '3%',
            left: '15%',
            right: '5%',
            height: '80%'
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: getSlice(datas.categoryData),
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                },
                formatter: function formatter(value) {
                    return formatTime('MM/dd', value);
                }
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle: {
                    color: 'red'
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [{
            type: 'k', //Candlestick 
            name: '日K',
            id: 'k-line',
            // braGap用于设置同一个类目内的柱形之间的间距
            // barGap: '1%',
            // barCategoryGap则用于设置不同类目之间的间距
            barCategoryGap: STOCK_CONFIG.barCategoryGap,
            barWidth: STOCK_CONFIG.barWidth,
            data: getSlice(datas.values),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            itemStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.up, //阳线填充色
                    color0: STOCK_CONFIG.col.down,
                    borderColor: STOCK_CONFIG.col.up, //阳线边框色
                    borderColor0: STOCK_CONFIG.col.down
                }
            }
        }, {
            type: 'line',
            name: '5' + kDisplay + '平均线',
            data: getSlice(calculateMA(datas, 5)),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.m5
                }
            }
        }, {
            type: 'line',
            name: '10' + kDisplay + '平均线',
            data: getSlice(calculateMA(datas, 10)),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.m10
                }
            }
        }, {
            type: 'line',
            name: '20' + kDisplay + '平均线',
            data: getSlice(calculateMA(datas, 20)),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.m20
                }
            }
        }, {
            type: 'line',
            name: '60' + kDisplay + '平均线',
            data: getSlice(calculateMA(datas, 60)),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.m60
                }
            }
        }, {
            type: 'line',
            name: '上軸線',
            data: getSlice(uppers),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: '#7999f2'
                }
            }
        }, {
            type: 'line',
            name: '中軸線',
            data: getSlice(middles),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: '#7999f2'
                }
            }
        }, {
            type: 'line',
            name: '下軸線',
            data: getSlice(lowers),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: '#7999f2'
                }
            }
        }]
    };
}
// CONCATENATED MODULE: ./src/data/stockVol.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13




function stockVol_getData(datasets, kineType) {
    var kDisplay = kineType == 1 ? "月" : "日";
    var datas = splitData(datasets);
    var sliceVols = getSlice(datas.vols);

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: function formatter(params) {
                var v = '<font color="' + STOCK_CONFIG.col.volup + '">\u6210\u4EA4\u91CF:</font> ' + params[0].value;
                $("#tooltipId2" + kineType).html(v);
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [{
            top: '5%',
            left: '15%',
            right: '5%',
            height: '85%'
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: getSlice(datas.categoryData),
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            /* axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
                }
            } */
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle: {
                    color: 'red'
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [{
            type: 'bar',
            name: '成交量', //下面的柱状图
            // braGap用于设置同一个类目内的柱形之间的间距
            // barGap: '1%',
            // barCategoryGap则用于设置不同类目之间的间距
            barCategoryGap: STOCK_CONFIG.barCategoryGap,
            barWidth: STOCK_CONFIG.barWidth,
            data: sliceVols,
            smooth: true,
            showSymbol: false,
            symbol: "none",
            itemStyle: {
                normal: {
                    width: 1,
                    color: function color(params) {
                        var currVol = params.data;
                        var previousVol = params.dataIndex > 0 ? sliceVols[params.dataIndex - 1] : 0;
                        if (parseInt(currVol) < parseInt(previousVol)) {
                            return STOCK_CONFIG.col.voldown;
                        } else {
                            return STOCK_CONFIG.col.volup;
                        }
                    }
                }
            }
        }]
    };
}
// CONCATENATED MODULE: ./src/data/stockRsi.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13




var RSI = __webpack_require__("k4Xa").RSI;

function stockRsi_getData(datasets, kineType) {
    var kDisplay = kineType == 1 ? "月" : "日";
    var datas = splitData(datasets);
    var inputRSI12 = {
        values: getCloses(datas),
        period: 12
    };

    var rsi12_src = RSI.calculate(inputRSI12);
    var rsi12 = getSlice(rsi12_src);

    var inputRSI100 = {
        values: getCloses(datas),
        period: 100
    };

    var rsi100_src = RSI.calculate(inputRSI100);
    var rsi100 = getSlice(rsi100_src);

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: function formatter(params) {
                var v = '<font color="' + STOCK_CONFIG.col.rsi12 + '">RSI-12:</font> ' + params[0].value + '\n                <font color="' + STOCK_CONFIG.col.rsi100 + '">RSI-100:</font> ' + params[0].value;
                $("#tooltipId3" + kineType).html(v);
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [{
            top: '5%',
            left: '15%',
            right: '5%',
            height: '85%'
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: getSlice(datas.categoryData),
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            /* axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
                }
            } */
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle: {
                    color: 'red'
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [{
            name: 'RSI-12',
            type: 'line',
            data: rsi12,
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.rsi12
                }
            }
        }, {
            name: 'RSI-100',
            type: 'line',
            data: rsi100,
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.rsi100
                }
            }
        }]
    };
}
// CONCATENATED MODULE: ./src/data/stockDmi.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13




var ADX = __webpack_require__("k4Xa").ADX;

function stockDmi_getData(datasets, kineType) {
    var kDisplay = kineType == 1 ? "月" : "日";
    var datas = splitData(datasets);
    // DMI
    var inputDMI = {
        close: getCloses(datas),
        high: getHighs(datas),
        low: getLows(datas),
        period: 14
    };
    var dmis = ADX.calculate(inputDMI);

    var adxs = getDmiAdxs(dmis);
    var mdis = getDmiMdis(dmis);
    var pdis = getDmipdis(dmis);

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: function formatter(params) {
                var v = '<font color="' + STOCK_CONFIG.col.mdi + '">MDI:</font> ' + params[0].value.toFixed(2) + '\n                <font color="' + STOCK_CONFIG.col.pdi + '">PDI:</font> ' + params[1].value.toFixed(2) + '\n                <font color="' + STOCK_CONFIG.col.adx + '">ADX:</font> ' + params[2].value.toFixed(2);
                $("#tooltipId4" + kineType).html(v);
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [{
            top: '5%',
            left: '15%',
            right: '5%',
            height: '85%'
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: getSlice(datas.categoryData),
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            /* axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
                }
            } */
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle: {
                    color: 'red'
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [{
            name: 'MDI',
            type: 'line',
            data: getSlice(mdis),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.mdi
                }
            }
        }, {
            name: 'PDI',
            type: 'line',
            data: getSlice(pdis),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.pdi
                }
            }
        }, {
            name: 'ADX',
            type: 'line',
            data: getSlice(adxs),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.adx
                }
            }
        }]
    };
}
// CONCATENATED MODULE: ./src/utils/macdUtils.js
// https://github.com/fshlny/MACD-js-implements/blob/master/MACD.md

function EMA(fNow, fPre, N) {
  return Number((fNow * 2 / (N + 1) + fPre * (N - 1) / (N + 1)).toFixed(2));
}

// {"tdate":20170103,"c":45760,"t":0,"v":183888,"dtime":"1519","h":46290,"oi":0,"l":45630,"vs":"183888","o":45630,"trddate":20170103}
// rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
/**
   * 计算EMA12和EMA26
   */
function EMASL(points, N) {
  if (points == null || points.length <= 0 || N == 0) return new Array();
  var result = new Array();
  for (var i = 0; i < points.length; i++) {
    var candle = points[i];
    var c = points[i][1];
    if (i == 0) {
      //第一天的EMA为收盘价
      result.push(c);
    } else {
      result.push(EMA(c, result[i - 1], N));
    }
  }
  return result;
}

// DIF = EMA(close,12) - EMA(close,26)
function DIF(points) {
  if (points == null || points.length <= 0) return new Array();
  var EMAS = EMASL(points, 12);
  var EMAL = EMASL(points, 26);
  var DIFs = new Array();
  for (var i = 0; i < EMAL.length; i++) {
    DIFs.push(formartNumber(EMAS[i] - EMAL[i]));
  }
  return DIFs;
}

// DEM = DEA
function DEA(points, N) {
  if (points == null || points.length <= 0) return new Array();
  var dif = DIF(points);
  var result = new Array();
  for (var i = 0; i < points.length; i++) {
    if (i == 0) {
      result.push(0);
    } else {
      result.push(EMA(dif[i], result[i - 1], N));
    }
  }
  return result;
}

// BAR = OSC
function BAR(points) {
  if (points == null || points.length <= 0) return new Array();
  var dif = DIF(points);
  var dea = DEA(points, 9);
  var bar = new Array();
  for (var i = 0; i < points.length; i++) {
    bar.push(2 * formartNumber(dif[i] - dea[i]));
  }
  return bar;
}

function formartNumber(n) {
  return Number(n.toFixed(2));
}


// CONCATENATED MODULE: ./src/data/stockMacd.js
// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13





function stockMacd_getData(datasets, kineType) {
    var kDisplay = kineType == 1 ? "月" : "日";
    var datas = splitData(datasets);
    var difs = DIF(datas.values); // DIF
    var macds = DEA(datas.values, 9); // 也就是DEM或MACD
    var oscs = BAR(datas.values); // 也就是OSC

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: function formatter(params) {
                var v = '<font color="' + STOCK_CONFIG.col.oscup + '">OSC:</font> ' + params[0].value + '\n                <font color="' + STOCK_CONFIG.col.dif + '">DIF:</font> ' + params[1].value + '\n                <font color="' + STOCK_CONFIG.col.macd + '">MACD:</font> ' + params[2].value;
                $("#tooltipId5" + kineType).html(v);
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [{
            top: '5%',
            left: '15%',
            right: '5%',
            height: '75%'
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: getSlice(datas.categoryData),
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            axisLabel: {
                onZero: false,
                /* lineStyle:{  
                             color:'red',  
                         }, */
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                },
                formatter: function formatter(value) {
                    return formatTime('MM/dd', value);
                }
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle: {
                    color: 'red'
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [{
            name: 'OSC', // BAR
            type: 'bar',
            // braGap用于设置同一个类目内的柱形之间的间距
            // barGap: '1%',
            // barCategoryGap则用于设置不同类目之间的间距
            barCategoryGap: STOCK_CONFIG.barCategoryGap,
            barWidth: STOCK_CONFIG.barWidth,
            data: getSlice(oscs),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            itemStyle: {
                normal: {
                    width: 1,
                    color: function color(params) {
                        var colorList;
                        if (params.data >= 0) {
                            colorList = STOCK_CONFIG.col.oscup;
                        } else {
                            colorList = STOCK_CONFIG.col.oscdown;
                        }
                        return colorList;
                    }
                }
            }
        }, {
            name: 'DIF', // DIF
            type: 'line',
            data: getSlice(difs),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.dif
                }
            }
        }, {
            name: 'MACD', // BAR
            type: 'line',
            data: getSlice(macds),
            smooth: true,
            showSymbol: false,
            symbol: "none",
            lineStyle: {
                normal: {
                    width: 1,
                    color: STOCK_CONFIG.col.macd
                }
            }
        }]
    };
}
// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/kCandle.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'






// 引用alert文件, alerts.error('xxx')
// import * as alerts from '../utils/alert.js'
// import {success, error} from '../utils/alert.js'

// http://gallery.echartsjs.com/editor.html?c=candlestick-sh

/* harmony default export */ var kCandle = ({
  components: {
    // MainLayout//, Alert
  },
  data: function data() {
    return {
      stockId: '',
      chart: null,
      // stockCandle: null,
      resize: true,
      rawHtml1: '',
      rawHtml2: '',
      rawHtml3: '',
      rawHtml4: '',
      rawHtml5: ''
    };
  },

  props: ["kineType"],
  mounted: function mounted() {
    this.rawHtml1 = '\u6536: \u958B: \u9AD8: \u4F4E:<br/>\n                ' + (this.kineType == 1 ? "月" : "日") + '\u7DDA\n                <font color="' + STOCK_CONFIG.col.m5 + '">M5: </font> \n                <font color="' + STOCK_CONFIG.col.m10 + '">M10: </font> \n                <font color="' + STOCK_CONFIG.col.m20 + '">M20: </font> \n                <font color="' + STOCK_CONFIG.col.m60 + '">M60: </font>';

    this.rawHtml2 = '<font color="' + STOCK_CONFIG.col.volup + '">\u6210\u4EA4\u91CF: </font>';

    this.rawHtml3 = '<font color="' + STOCK_CONFIG.col.rsi12 + '">RSI-12: </font>\n                <font color="' + STOCK_CONFIG.col.rsi100 + '">RSI-100: </font>';

    this.rawHtml4 = '<font color="' + STOCK_CONFIG.col.mdi + '">MDI: </font>\n                <font color="' + STOCK_CONFIG.col.pdi + '">PDI: </font>\n                <font color="' + STOCK_CONFIG.col.adx + '">ADX: </font>';

    this.rawHtml5 = '<font color="' + STOCK_CONFIG.col.oscup + '">OSC: </font>\n                <font color="' + STOCK_CONFIG.col.dif + '">DIF: </font>\n                <font color="' + STOCK_CONFIG.col.macd + '">MACD: </font>';
    this.init();
  },
  created: function created() {
    // this.getRecentDate()
  },

  methods: {
    openNewKline: function openNewKline(param) {
      // console.log(param)
    },
    showZoomKline: function showZoomKline() {},

    /* getRecentDate() {
      var now = new Date();
      var newDate = dateAdd("d", -RECENT_DATE, now);
      return {
        startDate: newDate.toLocaleDateString(),
        endDate: new Date().toLocaleDateString()
      };
    }, */
    init: function init() {
      if (this.chart != null) {
        return;
      }
      var chart1 = this.$echarts.init(document.getElementById('myChart1' + this.kineType));
      var chart2 = this.$echarts.init(document.getElementById('myChart2' + this.kineType));
      var chart3 = this.$echarts.init(document.getElementById('myChart3' + this.kineType));
      var chart4 = this.$echarts.init(document.getElementById('myChart4' + this.kineType));
      var chart5 = this.$echarts.init(document.getElementById('myChart5' + this.kineType));
      this.setOptions(chart1, chart2, chart3, chart4, chart5);
      this.$echarts.connect([chart1, chart2, chart3, chart4, chart5]);
      setTimeout(function () {
        window.onresize = function () {
          chart1.resize();
          chart2.resize();
          chart3.resize();
          chart4.resize();
          chart5.resize();
        };
      }, 200);
    },
    setOptions: function setOptions(chart1, chart2, chart3, chart4, chart5) {
      var _this = this;

      // this.stockCandle = null
      var this_ = this;
      var data1s = [];
      var data2s = [];
      var data3s = [];
      var data4s = [];
      var data5s = [];
      // let dateRange = this.getRecentDate()
      // let dateRange = this.getRecentDate()
      // this.stockId = '402396117293928448'
      this.stockId = this.$route.params.stockId;
      if (this.stockId != undefined && this.stockId != '' && this.stockId != 0) {
        // type 0: 日 1: 周 2: 月
        // let url = `/api/stock/selectHistory?stockId=${this.stockId}&type=${kineType}&startDate=${dateRange.startDate}&endDate=${dateRange.endDate}`
        var url = '/api/stock/selectLastDayHistory?stockId=' + this.stockId;
        if (this.kineType == 1) {
          url = '/api/stock/selectLastWeekHistory?stockId=' + this.stockId;
        }
        // alert("kDay url--->"+url)
        // chart1.showLoading()
        this.$api.get(url, null, function (rs) {
          // chart1.hideLoading()
          if (rs != undefined && rs.length > 0) {
            for (var i = 0; i < rs.length; i++) {
              // alert(this.stockId+"--->"+rs[i].date+"--->"+rs[i].opening+"--->"+rs[i].highest+"--->"+rs[i].lowest+"--->"+rs[i].closing+"--->"+rs[i].vol)
              // let stockHistorys = [rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]
              // console.log(stockHistorys)
              data1s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]);
              data2s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]);
              data3s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]);
              data4s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]);
              data5s.push([rs[i].date, rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol]);
            }
          } else {
            this_.$alerts.error("找不到數據:" + _this.stockId);
            // alert("找不到數據:"+this.stockId)
          }
          // return stockCandle(datas, this.kineType)
          chart1.setOption(getData(data1s, _this.kineType));
          chart2.setOption(stockVol_getData(data2s, _this.kineType));
          chart3.setOption(stockRsi_getData(data3s, _this.kineType));
          chart4.setOption(stockDmi_getData(data4s, _this.kineType));
          chart5.setOption(stockMacd_getData(data5s, _this.kineType));

          /* chart1.on('click', function (params) {
            console.log('params.componentType--->'+params.componentType)
            // if(params.componentType === 'series'){
                chart1.dispatchAction({
                    type: 'legendUnSelect',
                    seriesseriesIdIndex: 'k-line'
                  });
            // }
          
          }); */
        });
      }
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route': function $route(to, from) {
      // this.getData()
      this.init();
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-36ca25d4","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/kCandle.vue
var kCandle_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('div',[_c('div',[_c('div',{staticClass:"w-100 text-left ml-5 pl-3 my-1",attrs:{"id":'tooltipId1'+_vm.kineType},domProps:{"innerHTML":_vm._s(_vm.rawHtml1)}}),_vm._v(" "),_c('div',{staticClass:"echarts1",attrs:{"id":'myChart1'+_vm.kineType}})]),_vm._v(" "),_c('div',[_c('div',{staticClass:"w-100 text-left ml-5 pl-3 my-1",attrs:{"id":'tooltipId2'+_vm.kineType},domProps:{"innerHTML":_vm._s(_vm.rawHtml2)}}),_vm._v(" "),_c('div',{staticClass:"echarts2",attrs:{"id":'myChart2'+_vm.kineType}})]),_vm._v(" "),_c('div',[_c('div',{staticClass:"w-100 text-left ml-5 pl-3 my-1",attrs:{"id":'tooltipId3'+_vm.kineType},domProps:{"innerHTML":_vm._s(_vm.rawHtml3)}}),_vm._v(" "),_c('div',{staticClass:"echarts2",attrs:{"id":'myChart3'+_vm.kineType}})]),_vm._v(" "),_c('div',[_c('div',{staticClass:"w-100 text-left ml-5 pl-3 my-1",attrs:{"id":'tooltipId4'+_vm.kineType},domProps:{"innerHTML":_vm._s(_vm.rawHtml4)}}),_vm._v(" "),_c('div',{staticClass:"echarts2",attrs:{"id":'myChart4'+_vm.kineType}})]),_vm._v(" "),_c('div',[_c('div',{staticClass:"w-100 text-left ml-5 pl-3 my-1",attrs:{"id":'tooltipId5'+_vm.kineType},domProps:{"innerHTML":_vm._s(_vm.rawHtml5)}}),_vm._v(" "),_c('div',{staticClass:"echarts3",attrs:{"id":'myChart5'+_vm.kineType}})])])}
var kCandle_staticRenderFns = []
var kCandle_esExports = { render: kCandle_render, staticRenderFns: kCandle_staticRenderFns }
/* harmony default export */ var page_kCandle = (kCandle_esExports);
// CONCATENATED MODULE: ./src/page/kCandle.vue
function kCandle_injectStyle (ssrContext) {
  __webpack_require__("2Rhn")
}
var kCandle_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var kCandle___vue_template_functional__ = false
/* styles */
var kCandle___vue_styles__ = kCandle_injectStyle
/* scopeId */
var kCandle___vue_scopeId__ = "data-v-36ca25d4"
/* moduleIdentifier (server only) */
var kCandle___vue_module_identifier__ = null
var kCandle_Component = kCandle_normalizeComponent(
  kCandle,
  page_kCandle,
  kCandle___vue_template_functional__,
  kCandle___vue_styles__,
  kCandle___vue_scopeId__,
  kCandle___vue_module_identifier__
)

/* harmony default export */ var src_page_kCandle = (kCandle_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/stockKLineChart.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//




/* harmony default export */ var stockKLineChart = ({
  components: {
    KCandle: src_page_kCandle
  },
  data: function data() {
    return {};
  },
  created: function created() {},
  mounted: function mounted() {},

  methods: {},
  watch: {}
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-25f56f46","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/stockKLineChart.vue
var stockKLineChart_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('b-row',[_c('b-col',{attrs:{"cols":"12","sm":"12"}},[_c('b-row',[_c('b-col',{staticClass:"p-0 m-0 mw-100"},[_c('KCandle',{attrs:{"kineType":"0"}})],1),_vm._v(" "),_c('b-col',{staticClass:"p-0 m-0 mw-100"},[_c('KCandle',{attrs:{"kineType":"1"}})],1)],1)],1)],1)}
var stockKLineChart_staticRenderFns = []
var stockKLineChart_esExports = { render: stockKLineChart_render, staticRenderFns: stockKLineChart_staticRenderFns }
/* harmony default export */ var page_stockKLineChart = (stockKLineChart_esExports);
// CONCATENATED MODULE: ./src/page/stockKLineChart.vue
function stockKLineChart_injectStyle (ssrContext) {
  __webpack_require__("tC7s")
}
var stockKLineChart_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var stockKLineChart___vue_template_functional__ = false
/* styles */
var stockKLineChart___vue_styles__ = stockKLineChart_injectStyle
/* scopeId */
var stockKLineChart___vue_scopeId__ = "data-v-25f56f46"
/* moduleIdentifier (server only) */
var stockKLineChart___vue_module_identifier__ = null
var stockKLineChart_Component = stockKLineChart_normalizeComponent(
  stockKLineChart,
  page_stockKLineChart,
  stockKLineChart___vue_template_functional__,
  stockKLineChart___vue_styles__,
  stockKLineChart___vue_scopeId__,
  stockKLineChart___vue_module_identifier__
)

/* harmony default export */ var src_page_stockKLineChart = (stockKLineChart_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/content.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//







// import Alert from '../components/alert.vue'
/* harmony default export */ var content = ({
  components: {
    MainLayout: src_layouts_Main, StockMyData: src_page_StockMyData, StockMySelectedType: src_page_stockMySelectedType, StockDetail: src_page_stockDetail, StockNews: src_page_stockNews, StockKLineChart: src_page_stockKLineChart //, Alert//, Loading
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-222423d5","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/content.vue
var content_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('main-layout',[_c('b-container',{staticClass:"border",attrs:{"fluid":""}},[_c('b-row',[_c('b-col',{staticClass:"border-right border-primary pr-0 pl-2",staticStyle:{"max-width":"100px"}},[_c('stock-my-data')],1),_vm._v(" "),_c('b-col',{staticStyle:{"max-width":"100%"}},[_c('b-row',[_c('b-col',{staticClass:"pl-1",attrs:{"cols":"12","sm":"12"}},[_c('stock-my-selected-type'),_vm._v(" "),_c('stock-detail')],1)],1),_vm._v(" "),_c('b-row',[_c('b-col',{staticClass:"pl-1",attrs:{"cols":"9","sm":"9"}},[_c('StockKLineChart'),_vm._v(" "),_c('b-row',{staticClass:"mt-3"},[_c('b-col',{staticClass:"pl-0 pr-0",attrs:{"cols":"12","sm":"12"}},[_c('stock-news')],1)],1)],1),_vm._v(" "),_c('b-col',{staticClass:"pl-0 pr-0",attrs:{"cols":"3","sm":"3"}},[_c('img',{staticClass:"w-100",attrs:{"src":__webpack_require__("vhDn")}}),_vm._v(" "),_c('img',{staticClass:"w-100",attrs:{"src":__webpack_require__("Pbsv")}})])],1)],1)],1)],1)],1)}
var content_staticRenderFns = []
var content_esExports = { render: content_render, staticRenderFns: content_staticRenderFns }
/* harmony default export */ var page_content = (content_esExports);
// CONCATENATED MODULE: ./src/page/content.vue
var content_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var content___vue_template_functional__ = false
/* styles */
var content___vue_styles__ = null
/* scopeId */
var content___vue_scopeId__ = null
/* moduleIdentifier (server only) */
var content___vue_module_identifier__ = null
var content_Component = content_normalizeComponent(
  content,
  page_content,
  content___vue_template_functional__,
  content___vue_styles__,
  content___vue_scopeId__,
  content___vue_module_identifier__
)

/* harmony default export */ var src_page_content = (content_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/importantNews.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//



var items = [];
/* harmony default export */ var importantNews = ({
  components: {
    MainLayout: src_layouts_Main
  },
  data: function data() {
    return {
      items: [],
      numberOfPages: 0,
      currentPage: this.$route.params.pageNum,
      type: 1,
      pageSize: PAGE_SIZE,
      fields: {
        content_title: {
          label: '<span id="content_id">: </span>市場重大新聞',
          sortable: true
        }
      }
    };
  },
  created: function created() {
    var _this = this;

    // this.getData(1)
    eventBus.$on('initCurrentPage', function (pageNum) {
      _this.currentPage = pageNum;
    });
    this.timeOutsetInterval();
  },

  destroyed: function destroyed() {
    if (this.intervalid1 != null) {
      clearInterval(this.intervalid1);
    }
  },
  methods: {
    timeOutsetInterval: function timeOutsetInterval() {
      var _this2 = this;

      if (this.intervalid1 != null) {
        clearInterval(this.intervalid1);
      }
      var $this = this;
      this.autoFetch($this);
      this.intervalid1 = setInterval(function () {
        // this.changes = ((Math.random() * 100).toFixed(2))+'%';
        _this2.autoFetch($this);
      }, 10 * 60 * 1000);
    },
    autoFetch: function autoFetch($this) {
      var _this3 = this;

      eventBus.$emit('loading', "正在自動獲取最新的新聞中...", true);
      console.log("News autoFetch importantNews started......");
      var url = "/api/stock/fetchImportantLatestNews";
      $this.$api.post(url, null, function (rs) {
        eventBus.$emit('success', "自動更新新聞成功!");
        _this3.getData(1);
        console.log("News autoFetch importantNews end......");
      });
    },
    linkGen: function linkGen(pageNum) {
      return {
        path: '/importantNews/' + pageNum
      };
    },
    showExcludeNews: function showExcludeNews(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 1;
      this.$router.push('/importantNews/1');
      this.getData(1);
    },
    showIncludeNews: function showIncludeNews(event) {
      $(".active").removeClass('active');
      $(event.target).addClass('active');
      this.type = 0;
      this.$router.push('/importantNews/1');
      this.getData(1);
    },
    getData: function getData(pageNum) {
      var _this4 = this;

      if (pageNum != undefined) {
        this.currentPage = pageNum;
      } else {
        this.currentPage = this.$route.params.pageNum;
      }
      this.items = [];
      items = [];
      var rootUrl = this.type == undefined || this.type == 0 ? '/api/stock/getImportantNewsInclude/' : '/api/stock/getImportantNewsExclude/';
      var url = rootUrl + this.currentPage + "/" + this.pageSize;
      this.$api.get(url, null, function (rs) {
        // this.items = rs
        _this4.numberOfPages = rs.pageTotal;
        $(rs.rows).each(function () {
          var context = "<a target=\"_blank\" href=\"" + this.url + "\">" + this.froms + "</a>";
          items.push({
            content_title: context
          });
        });
        _this4.items = items;
        $("#content_id").parent().remove();
      });
    }
  },
  watch: {
    '$route': function $route(to, from) {
      this.getData();
      /* this.currentPage = this.$route.params.pageNum
      this.items = []
      items = []
      let rootUrl = (this.type == undefined || this.type == 0) ? '/api/stock/getImportantNewsInclude/' : '/api/stock/getImportantNewsExclude/'
      let url = rootUrl+this.currentPage+"/"+this.pageSize
      this.$api.get(url, null, rs => {
        // this.items = rs
        this.numberOfPages = rs.pageTotal
        $(rs.rows).each(function(){
          let context = "<a target=\"_blank\" href=\""+this.url+"\">"+this.froms+"</a>"
          items.push({
            content_title: context
          })
        });
        this.items = items
      })
      $("#content_id").parent().remove() */
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-3b82c474","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/importantNews.vue
var importantNews_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('main-layout',[[_c('nav',{staticClass:"mt-3 ml-3 nav nav-pills nav-justified"},[_c('a',{staticClass:"nav-link active",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showExcludeNews($event)}}},[_vm._v("市場重大新聞")]),_vm._v(" "),_c('a',{staticClass:"nav-link",attrs:{"href":"#"},on:{"click":function($event){$event.preventDefault();_vm.showIncludeNews($event)}}},[_vm._v("市場焦點新聞")])]),_vm._v(" "),_c('div',{staticClass:"text container-fluid"},[_c('b-table',{attrs:{"striped":"","hover":"","items":_vm.items,"fields":_vm.fields},scopedSlots:_vm._u([{key:"content_title",fn:function(data){return _c('span',{domProps:{"innerHTML":_vm._s(data.value)}})}}])}),_vm._v(" "),_c('b-pagination-nav',{attrs:{"align":"center","number-of-pages":_vm.numberOfPages,"base-url":"#","link-gen":_vm.linkGen}})],1)]],2)}
var importantNews_staticRenderFns = []
var importantNews_esExports = { render: importantNews_render, staticRenderFns: importantNews_staticRenderFns }
/* harmony default export */ var page_importantNews = (importantNews_esExports);
// CONCATENATED MODULE: ./src/page/importantNews.vue
function importantNews_injectStyle (ssrContext) {
  __webpack_require__("4V5R")
}
var importantNews_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var importantNews___vue_template_functional__ = false
/* styles */
var importantNews___vue_styles__ = importantNews_injectStyle
/* scopeId */
var importantNews___vue_scopeId__ = "data-v-3b82c474"
/* moduleIdentifier (server only) */
var importantNews___vue_module_identifier__ = null
var importantNews_Component = importantNews_normalizeComponent(
  importantNews,
  page_importantNews,
  importantNews___vue_template_functional__,
  importantNews___vue_styles__,
  importantNews___vue_scopeId__,
  importantNews___vue_module_identifier__
)

/* harmony default export */ var src_page_importantNews = (importantNews_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/stockDailyTransactions.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


// import Alert from '../components/alert.vue'

// import '../components/datepicker/js/bootstrap-datepicker.min'
// import '../components/datepicker/css/bootstrap-datepicker.min.css'
// import '../components/datepicker/locales/bootstrap-datepicker.zh-CN.min'
// import '../components/My97DatePicker/WdatePicker.js'
// import '../components/My97DatePicker/skin/WdatePicker.css'


/* harmony default export */ var stockDailyTransactions = ({
  components: {
    MainLayout: src_layouts_Main //, Alert
  },
  data: function data() {
    return {
      file: null,
      items: []
    };
  },
  created: function created() {
    this.getData();
  },

  methods: {
    update: function update(e) {
      var _this = this;

      var url = "/api/stock/uploadStockDailyTransactions";
      var file = e.target.files[0];
      this.$api.fileUpload(file, url, function (rs) {
        eventBus.$emit('success', "導入成功!");
        _this.getData();
      });
      this.$refs.fileinput.reset();
    },
    datePick: function datePick(e) {
      var obj = e.target;
      WdatePicker({ el: obj, readOnly: true, dateFmt: 'yyyy/MM/dd', lang: 'zh-tw' });
    },
    selectByDate: function selectByDate() {
      this.getData();
    },
    getData: function getData() {
      var _this2 = this;

      var startDate = $("#startDate").val();
      var endDate = $("#endDate").val();
      if (startDate != undefined && startDate != '') {
        var url = '/api/stock/getStockDailyTransactions?startDate=' + startDate + '&endDate=' + endDate;
        this.$api.get(url, null, function (rs) {
          if (rs.length > 0) {
            for (var i = 0; i < rs.length; i++) {
              var data = rs[i];
              _this2.items.push({
                '成交日期': data.txDate,
                '股票名稱': '[' + data.no + '] ' + data.company,
                '交易種類': data.txKind,
                '成交數量': data.quantity,
                '成交價格': data.txPrice,
                '成交金額': data.txAmount,
                '手續費': data.fee,
                '交易稅': data.txTallage,
                '證所稅': data.zsTallage,
                '融資金額': data.financingAmount,
                '利息': data.interest,
                '借券費': data.debFee,
                '客戶淨收付': data.customerCharge,
                '損益': data.profit,
                '報酬率(%)': data.payRate,
                '持有成本': data.holdingCost
              });
            }
          } else {
            _this2.items = [];
          }
        });
      }
    }
  },
  watch: {
    '$route': function $route(to, from) {}
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-56223567","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/stockDailyTransactions.vue
var stockDailyTransactions_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('main-layout',[[_c('div',[_c('b-container',{attrs:{"fluid":""}},[_c('b-row',{staticClass:"p-3"},[_c('b-col',{staticClass:"pt-3"},[_c('b',[_vm._v("請選擇需要導入的每天買入賣出股文件:")])])],1),_vm._v(" "),_c('b-row',[_c('b-col',[_c('b-form-file',{ref:"fileinput",staticClass:"p-3",attrs:{"id":"file1","placeholder":"Please choose a file."},on:{"change":_vm.update},model:{value:(_vm.file),callback:function ($$v) {_vm.file=$$v},expression:"file"}})],1)],1),_vm._v(" "),_c('b-row',[_c('b-col',[_c('form',{staticClass:"p-3",on:{"submit":function($event){$event.preventDefault();return _vm.selectByDate($event)}}},[_c('input',{staticClass:"form-control w-25 float-left",attrs:{"id":"startDate","placeholder":"請選擇開始日期","required":"","type":"text","autocomplete":"off"},on:{"click":_vm.datePick}}),_vm._v(" "),_c('div',{staticClass:"float-left px-3"},[_vm._v(" - ")]),_vm._v(" "),_c('input',{staticClass:"form-control w-25 float-left",attrs:{"id":"endDate","placeholder":"請選擇結束日期","type":"text","autocomplete":"off"},on:{"click":_vm.datePick}}),_vm._v(" "),_c('b-button',{staticClass:"px-3",attrs:{"variant":"primary","type":"submit"}},[_vm._v("查詢")])],1),_vm._v(" "),_c('p',{staticClass:"p-3 mb-2 bg-info text-white"},[_vm._v("每天買入賣出股列表：")]),_vm._v(" "),_c('b-table',{attrs:{"hover":"","items":_vm.items}})],1)],1)],1)],1)]],2)}
var stockDailyTransactions_staticRenderFns = []
var stockDailyTransactions_esExports = { render: stockDailyTransactions_render, staticRenderFns: stockDailyTransactions_staticRenderFns }
/* harmony default export */ var page_stockDailyTransactions = (stockDailyTransactions_esExports);
// CONCATENATED MODULE: ./src/page/stockDailyTransactions.vue
function stockDailyTransactions_injectStyle (ssrContext) {
  __webpack_require__("k49T")
}
var stockDailyTransactions_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var stockDailyTransactions___vue_template_functional__ = false
/* styles */
var stockDailyTransactions___vue_styles__ = stockDailyTransactions_injectStyle
/* scopeId */
var stockDailyTransactions___vue_scopeId__ = "data-v-56223567"
/* moduleIdentifier (server only) */
var stockDailyTransactions___vue_module_identifier__ = null
var stockDailyTransactions_Component = stockDailyTransactions_normalizeComponent(
  stockDailyTransactions,
  page_stockDailyTransactions,
  stockDailyTransactions___vue_template_functional__,
  stockDailyTransactions___vue_styles__,
  stockDailyTransactions___vue_scopeId__,
  stockDailyTransactions___vue_module_identifier__
)

/* harmony default export */ var src_page_stockDailyTransactions = (stockDailyTransactions_Component.exports);

// EXTERNAL MODULE: ./node_modules/sortablejs/Sortable.js
var Sortable = __webpack_require__("Lokx");
var Sortable_default = /*#__PURE__*/__webpack_require__.n(Sortable);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/editMySelectedType.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


// import Alert from '../components/alert.vue'



// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'

// import Autocomplete from '../components/Autocomplete'
/* harmony default export */ var editMySelectedType = ({
  components: {
    MainLayout: src_layouts_Main, Autocomplete: build_default.a //, Alert
  },
  data: function data() {
    return {
      name: '',
      currSelectedType: '',
      initNumber: 50,
      options: [{ value: '', text: 'Please select...' }],
      list: [],
      items: []
    };
  },
  created: function created() {
    this.getData();
  },
  mounted: function mounted() {
    var _api = this.$api;
    Sortable_default.a.create(stockMySelectedItem, {
      handle: '.move-item',
      animation: 150,
      onUpdate: function onUpdate(evt) {
        var item = evt.item; // the current dragged HTMLElement
        // alert(item.outerHTML)
        var changeStockMySelectedTypeParams = [];
        $("#stockMySelectedItem button").each(function (index, data) {
          // alert($(data).attr("id")+"/"+$(data).attr("data"))
          var param = {
            "type": $(data).attr("id"),
            "name": $(data).attr("data")
          };
          changeStockMySelectedTypeParams.push(param);
        });
        if (changeStockMySelectedTypeParams.length > 0) {
          var url = '/api/stock/changeStockMySelectedType';
          var params = {
            "changeStockMySelectedTypeDtos": changeStockMySelectedTypeParams
          };
          _api.post(url, params, function (rs) {
            eventBus.$emit('success', "調整自選股顯示順序成功!");
          });
        }
      }
    });

    Sortable_default.a.create(listWithHandle, {
      handle: '.oi-move',
      animation: 150
    });
  },

  methods: {
    clearName: function clearName() {
      this.name = '';
      this.$refs.focusThis.focus();
    },
    handleOk: function handleOk(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name) {
        alert('请输入自選股名称!');
      } else {
        this.handleSubmit();
      }
    },
    handleSubmit: function handleSubmit() {
      // this.names.push(this.name)
      this.saveData(this.name);
      this.clearName();
      this.$refs.modal.hide();
    },

    // 保存自選股名稱
    saveData: function saveData(name) {
      var _this = this;

      var url = '/api/stock/saveStockMySelectedType?name=' + name;
      this.$api.post(url, null, function (rs) {
        _this.getData();
        // Bus.$emit('reGetStockMySelectedTypes')
      });
    },


    // reame stockmydata name
    clearName2: function clearName2() {
      this.name = '';
      this.$refs.focusThis2.focus();
    },
    handleOk2: function handleOk2(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name) {
        alert('请输入需要修改的自選股名称!');
      } else {
        this.handleSubmit2();
      }
    },
    handleSubmit2: function handleSubmit2() {
      // this.names.push(this.name)
      this.renameStockMydataName(this.name);
      this.clearName2();
      this.$refs.modal2.hide();
    },

    // 保存自選股名稱
    renameStockMydataName: function renameStockMydataName(name) {
      var _this2 = this;

      if (this.currSelectedType == '') {
        alert("請先選擇對應的自選股名稱!");
      } else {
        var url = '/api/stock/renameStockMydataName?selectedType=' + this.currSelectedType + '&name=' + name;
        // alert(url)
        this.$api.post(url, null, function (rs) {
          _this2.getData();
          eventBus.$emit('success', "修改名稱成功!");
        });
      }
    },


    // 保存選擇的股票到自選股中
    save2StockMyData: function save2StockMyData() {
      if (this.currSelectedType == '') {
        alert("請先選擇對應的自選股名稱!");
      } else {
        var stockIds = [];
        $(".form-control input[type='hidden']").each(function (index, data) {
          var inputValue = $(data).val();
          if (inputValue != "") {
            stockIds.push(inputValue);
          }
        });
        if (stockIds.length > 0) {
          var url = '/api/stock/saveAllStockMySelected';
          var params = {
            "selectedType": this.currSelectedType,
            "stockIds": stockIds
          };
          this.$api.post(url, params, function (rs) {
            eventBus.$emit('success', "保存成功!");
          });
        }
      }
    },
    isSelected: function isSelected(type) {
      // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if (this.currSelectedType == type) {
        return 'warning';
      }
      return 'success';
    },
    getUrl: function getUrl(input) {
      return '/api/stock/search4StockMyData?query=' + input;
    },
    selectedProcess: function selectedProcess(result, refs) {
      // console.log("===>"+$(this['$el']).html())
      // for(var x in this) {
      //   console.log(x+"--->"+this[x])
      // }
      $(".form-control input[type='hidden']").each(function (index, data) {
        var inputValue = $(data).val();
        // alert("value->"+result.value+"/inputValue->"+inputValue)
        if (inputValue != "" && inputValue == result.value) {
          refs.clear();
          // alert(result.display+"已經存在!")
          eventBus.$emit('alerts', result.display + "已經存在!");
        }
      });
    },
    formattedDisplay: function formattedDisplay(result) {
      return result.no + ' ' + result.company;
    },

    // 将某个股票从自选股中移除
    removeStockMySelected: function removeStockMySelected(selectedType, selectedName) {
      var stockId = this.$route.params.stockId;
      var $this = this;
      this.$confirm("是否確定從" + selectedName + "中移除?").then(function () {
        // alert(stockId+"--->"+selectedType)
        var url = '/api/stock/removeStockMySelected?selectedType=' + selectedType;
        $this.$api.post(url, null, function (rs) {
          $this.getData();
          $this.currSelectedType = '';
          $this.list = [];
        });
      }).catch(function (e) {
        // alert("Exception--->"+e)
        console.log("Exception--->" + e);
      });
    },
    changedValue: function changedValue(value) {
      this.currSelectedType = value;
      // 清空手动输入的内容
      $(".empty-form-control input").val("");
      this.getMyStockMySelected(value);
    },
    getMyStockMySelected: function getMyStockMySelected(type) {
      var _this3 = this;

      this.list = [];
      if (type != '') {
        this.$api.get('/api/stock/getStockMyDatasByType/' + type, null, function (rs) {
          _this3.list = rs;
        });
      }
    },
    getData: function getData() {
      var _this4 = this;

      // this.options = []
      var url = '/api/stock/getStockMySelectedTypes';
      this.$api.get(url, null, function (rs) {
        _this4.items = rs;
        /*for (var i = 0; i < rs.length; i++) {
          this.options.push({
            value: rs[i].type, text: rs[i].name
        }*/
      });
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-6da1d143","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/editMySelectedType.vue
var editMySelectedType_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('main-layout',[_c('div',[_c('div',{attrs:{"id":"stockMySelectedItem"}},_vm._l((_vm.items),function(item){return _c('span',{staticClass:"move-item"},[_c('b-button',{attrs:{"variant":_vm.isSelected(item.type),"id":item.type,"data":item.name},on:{"click":function($event){_vm.changedValue(item.type)}}},[_vm._v("\n            "+_vm._s(item.name)+"\n        "),_c('span',{attrs:{"aria-hidden":"true"},on:{"click":function($event){$event.preventDefault();_vm.removeStockMySelected(item.type, item.name)}}},[_vm._v("×")])])],1)})),_vm._v(" "),_c('div',{staticClass:"float-right w-25"},[_c('b-btn',{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent",modifiers:{"modalPrevent":true}}],attrs:{"variant":"info"}},[_vm._v("添加")]),_vm._v(" "),(_vm.currSelectedType != '')?_c('b-btn',{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent2",modifiers:{"modalPrevent2":true}}],attrs:{"variant":"info"}},[_vm._v("修改名稱")]):_vm._e(),_vm._v(" "),(_vm.currSelectedType != '')?_c('b-btn',{attrs:{"variant":"info"},on:{"click":_vm.save2StockMyData}},[_vm._v("保存")]):_vm._e(),_vm._v(" "),_c('b-modal',{ref:"modal",attrs:{"id":"modalPrevent","title":"请输入自選股名称"},on:{"ok":_vm.handleOk,"shown":_vm.clearName}},[_c('form',{on:{"submit":function($event){$event.stopPropagation();$event.preventDefault();return _vm.handleSubmit($event)}}},[_c('b-form-input',{ref:"focusThis",attrs:{"type":"text","placeholder":"输入自選股名称"},model:{value:(_vm.name),callback:function ($$v) {_vm.name=$$v},expression:"name"}})],1)]),_vm._v(" "),_c('b-modal',{ref:"modal2",attrs:{"id":"modalPrevent2","title":"请输入需要修改的自選股名称"},on:{"ok":_vm.handleOk2,"shown":_vm.clearName2}},[_c('form',{on:{"submit":function($event){$event.stopPropagation();$event.preventDefault();return _vm.handleSubmit2($event)}}},[_c('b-form-input',{ref:"focusThis2",attrs:{"type":"text","placeholder":"请输入需要修改的自選股名称"},model:{value:(_vm.name),callback:function ($$v) {_vm.name=$$v},expression:"name"}})],1)])],1),_vm._v(" "),_c('div',{attrs:{"id":"listWithHandle"}},[_vm._l((_vm.list),function(item,index){return _c('div',{staticClass:"list-group-item float-left wd-fixed py-1 my-1"},[_c('span',{staticClass:"badge"},[_vm._v(_vm._s(index+1))]),_vm._v(" "),_c('span',{staticClass:"oi oi-move",attrs:{"aria-hidden":"true"}}),_vm._v(" "),_c('autocomplete',{ref:'autocomplete'+index,refInFor:true,attrs:{"source":_vm.getUrl,"input-class":"form-control","results-property":"data","results-display":_vm.formattedDisplay,"initialValue":item.stockId,"initialDisplay":item.no +' '+item.company},on:{"selected":_vm.selectedProcess}})],1)}),_vm._v(" "),_vm._l((_vm.initNumber-_vm.list.length),function(i){return (_vm.currSelectedType != '')?_c('div',{staticClass:"list-group-item float-left wd-fixed py-1 my-1"},[_c('span',{staticClass:"badge"},[_vm._v(_vm._s(i+_vm.list.length))]),_vm._v(" "),_c('span',{staticClass:"oi oi-move",attrs:{"aria-hidden":"true"}}),_vm._v(" "),_c('autocomplete',{ref:'autocomplete'+i,refInFor:true,attrs:{"source":_vm.getUrl,"input-class":"form-control empty-form-control","results-property":"data","results-display":_vm.formattedDisplay},on:{"selected":_vm.selectedProcess}})],1):_vm._e()}),_vm._v(" "),_c('div',{staticClass:"clearfix"})],2)])])}
var editMySelectedType_staticRenderFns = []
var editMySelectedType_esExports = { render: editMySelectedType_render, staticRenderFns: editMySelectedType_staticRenderFns }
/* harmony default export */ var page_editMySelectedType = (editMySelectedType_esExports);
// CONCATENATED MODULE: ./src/page/editMySelectedType.vue
function editMySelectedType_injectStyle (ssrContext) {
  __webpack_require__("9kpU")
}
var editMySelectedType_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var editMySelectedType___vue_template_functional__ = false
/* styles */
var editMySelectedType___vue_styles__ = editMySelectedType_injectStyle
/* scopeId */
var editMySelectedType___vue_scopeId__ = "data-v-6da1d143"
/* moduleIdentifier (server only) */
var editMySelectedType___vue_module_identifier__ = null
var editMySelectedType_Component = editMySelectedType_normalizeComponent(
  editMySelectedType,
  page_editMySelectedType,
  editMySelectedType___vue_template_functional__,
  editMySelectedType___vue_styles__,
  editMySelectedType___vue_scopeId__,
  editMySelectedType___vue_module_identifier__
)

/* harmony default export */ var src_page_editMySelectedType = (editMySelectedType_Component.exports);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/page/newsSettings.vue
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


// import Alert from '../components/alert.vue'

/* harmony default export */ var newsSettings = ({
  components: {
    MainLayout: src_layouts_Main //, Alert
  },
  data: function data() {
    return {
      excludesItems: [],
      includesItems: []
    };
  },
  created: function created() {
    this.getExcludesItems();
    this.getIncludesItems();
  },
  mounted: function mounted() {},

  methods: {
    saveExcludeItem: function saveExcludeItem(event) {
      var _this = this;

      var values = [];
      $(".exclude-item input").each(function (index, obj) {
        var value = $(obj).val();
        if (values.indexOf(value) == -1) {
          values.push(value);
        }
      });

      if (values.length > 0) {
        var url = '/api/stock/saveStockNewsKeyByExclude?keys[]=' + values.join();
        var $this = this;
        this.$api.post(url, null, function (rs) {
          _this.getExcludesItems();
        });
      } else {
        alert("記錄不能為空!");
      }
    },
    saveIncludeItem: function saveIncludeItem(event) {
      var _this2 = this;

      var values = [];
      $(".include-item input").each(function (index, obj) {
        var value = $(obj).val();
        if (values.indexOf(value) == -1) {
          values.push(value);
        }
      });

      if (values.length > 0) {
        var url = '/api/stock/saveStockNewsKeyByInclude?keys[]=' + values.join();
        var $this = this;
        this.$api.post(url, null, function (rs) {
          _this2.getIncludesItems();
        });
      } else {
        alert("記錄不能為空!");
      }
    },
    addExcludeItem: function addExcludeItem(event) {
      // alert(event.target)
      var inputHtml = '\n      <div class="exclude-item w-5 float-left p-1">\n          <input type="text" class="form-control" />\n      </div>\n      ';
      $("#add-exclude-item").before(inputHtml);
    },
    addIncludeItem: function addIncludeItem(event) {
      // alert(event.target)
      var inputHtml = '\n      <div class="include-item w-5 float-left p-1">\n          <input type="text" class="form-control" />\n      </div>\n      ';
      $("#add-include-item").before(inputHtml);
    },
    getExcludesItems: function getExcludesItems() {
      var _this3 = this;

      this.excludesItems = [];
      var url = '/api/stock/getStockNewsKeyByExclude';
      this.$api.get(url, null, function (rs) {
        $(".exclude-item").remove();
        _this3.excludesItems = rs;
      });
    },
    getIncludesItems: function getIncludesItems() {
      var _this4 = this;

      this.includesItems = [];
      var url = '/api/stock/getStockNewsKeyByInclude';
      this.$api.get(url, null, function (rs) {
        $(".include-item").remove();
        _this4.includesItems = rs;
      });
    }
  }
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-e6b400c4","hasScoped":true,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/page/newsSettings.vue
var newsSettings_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('main-layout',[_c('div',{staticClass:"border border-primary m-2 float-left",staticStyle:{"width":"45%"}},[_c('h5',{staticClass:"text-primary p-1"},[_vm._v("去重關鍵字：\n    "),_c('button',{staticClass:"btn btn-primary",attrs:{"type":"button"},on:{"click":function($event){_vm.saveExcludeItem($event)}}},[_vm._v("保存")]),_vm._v(" "),_c('button',{staticClass:"btn btn-primary",attrs:{"type":"button"},on:{"click":function($event){_vm.addExcludeItem($event)}}},[_vm._v("添加")])]),_vm._v(" "),_vm._l((_vm.excludesItems),function(item){return _c('div',{staticClass:"exclude-item w-5 float-left p-1"},[_c('input',{staticClass:"form-control",attrs:{"type":"text"},domProps:{"value":item.key}})])}),_vm._v(" "),_c('div',{staticClass:"w-3 float-left p-1",attrs:{"id":"add-exclude-item"}}),_vm._v(" "),_c('div',{staticClass:"clearfix"})],2),_vm._v(" "),_c('div',{staticClass:"border border-success m-2 float-left",staticStyle:{"width":"45%"}},[_c('h5',{staticClass:"text-success p-1"},[_vm._v("焦點關鍵字：\n    "),_c('button',{staticClass:"btn btn-success",attrs:{"type":"button"},on:{"click":function($event){_vm.saveIncludeItem($event)}}},[_vm._v("保存")]),_vm._v(" "),_c('button',{staticClass:"btn btn-success",attrs:{"type":"button"},on:{"click":function($event){_vm.addIncludeItem($event)}}},[_vm._v("添加")])]),_vm._v(" "),_vm._l((_vm.includesItems),function(item){return _c('div',{staticClass:"include-item w-5 float-left p-1"},[_c('input',{staticClass:"form-control",attrs:{"type":"text"},domProps:{"value":item.key}})])}),_vm._v(" "),_c('div',{staticClass:"w-3 float-left p-1",attrs:{"id":"add-include-item"}}),_vm._v(" "),_c('div',{staticClass:"clearfix"})],2),_vm._v(" "),_c('div',{staticClass:"clearfix"})])}
var newsSettings_staticRenderFns = []
var newsSettings_esExports = { render: newsSettings_render, staticRenderFns: newsSettings_staticRenderFns }
/* harmony default export */ var page_newsSettings = (newsSettings_esExports);
// CONCATENATED MODULE: ./src/page/newsSettings.vue
function newsSettings_injectStyle (ssrContext) {
  __webpack_require__("oZvZ")
}
var newsSettings_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var newsSettings___vue_template_functional__ = false
/* styles */
var newsSettings___vue_styles__ = newsSettings_injectStyle
/* scopeId */
var newsSettings___vue_scopeId__ = "data-v-e6b400c4"
/* moduleIdentifier (server only) */
var newsSettings___vue_module_identifier__ = null
var newsSettings_Component = newsSettings_normalizeComponent(
  newsSettings,
  page_newsSettings,
  newsSettings___vue_template_functional__,
  newsSettings___vue_styles__,
  newsSettings___vue_scopeId__,
  newsSettings___vue_module_identifier__
)

/* harmony default export */ var src_page_newsSettings = (newsSettings_Component.exports);

// CONCATENATED MODULE: ./src/router/index.js









// import KWeek from '@/page/kWeek'
/* import RsiDay from '@/page/rsiDay'
import RsiWeek from '@/page/rsiWeek'
import DmiDay from '@/page/dmiDay'
import DmiWeek from '@/page/dmiWeek'
import MacdDay from '@/page/macdDay'
import MacdWeek from '@/page/macdWeek' */

vue_esm["default"].use(vue_router_esm["a" /* default */]);

/* harmony default export */ var router = (new vue_router_esm["a" /* default */]({
  // mode: 'history',
  mode: 'hash',
  routes: [{
    path: '/',
    component: src_page_content
  }, {
    path: '/login',
    component: src_sign_in
  }, {
    path: '/index',
    component: src_page_content
  }, {
    path: '/content/:stockId',
    component: src_page_content,
    children: [
    // UserPosts will be rendered inside User's <router-view>
    // when /user/:stockId/:pageNum is matched
    { path: ':pageNum', component: src_page_content }]
  }, {
    path: '/importantNews/:pageNum',
    component: src_page_importantNews
  }, {
    path: '/stockDailyTransactions',
    component: src_page_stockDailyTransactions
  }, {
    path: '/editMySelectedType',
    component: src_page_editMySelectedType
  }, {
    path: '/newsSettings',
    component: src_page_newsSettings
  }, {
    path: '/kCandle',
    component: src_page_kCandle
  }]
}));
// EXTERNAL MODULE: ./node_modules/bootstrap-vue/es/index.js + 162 modules
var es = __webpack_require__("e6fC");

// EXTERNAL MODULE: ./node_modules/bootstrap/dist/css/bootstrap.min.css
var bootstrap_min = __webpack_require__("qb6w");
var bootstrap_min_default = /*#__PURE__*/__webpack_require__.n(bootstrap_min);

// EXTERNAL MODULE: ./node_modules/bootstrap-vue/dist/bootstrap-vue.css
var bootstrap_vue = __webpack_require__("9M+g");
var bootstrap_vue_default = /*#__PURE__*/__webpack_require__.n(bootstrap_vue);

// EXTERNAL MODULE: ./node_modules/open-iconic/font/css/open-iconic-bootstrap.min.css
var open_iconic_bootstrap_min = __webpack_require__("DwKh");
var open_iconic_bootstrap_min_default = /*#__PURE__*/__webpack_require__.n(open_iconic_bootstrap_min);

// EXTERNAL MODULE: ./node_modules/vue-confirm/index.js
var vue_confirm = __webpack_require__("XGXU");
var vue_confirm_default = /*#__PURE__*/__webpack_require__.n(vue_confirm);

// CONCATENATED MODULE: ./node_modules/babel-loader/lib!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/components/messagebox/message.vue
//
//
//
//
//
//

/* harmony default export */ var message = ({
  name: 'Vue-Message',
  data: function data() {
    return {
      text: 'Hello World', // 默认文案
      position: 'bottom', // 默认显示位置
      duration: 3000, // 持续时间
      background: 'rgba(7,17,27,0.8)' // 默认背景色
    };
  },
  mounted: function mounted() {}
});
// CONCATENATED MODULE: ./node_modules/vue-loader/lib/template-compiler?{"id":"data-v-3f1ceee6","hasScoped":false,"transformToRequire":{"video":["src","poster"],"source":"src","img":"src","image":"xlink:href"},"buble":{"transforms":{}}}!./node_modules/vue-loader/lib/selector.js?type=template&index=0!./src/components/messagebox/message.vue
var message_render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;return _c('transition',{attrs:{"name":"fade-up"}})}
var message_staticRenderFns = []
var message_esExports = { render: message_render, staticRenderFns: message_staticRenderFns }
/* harmony default export */ var messagebox_message = (message_esExports);
// CONCATENATED MODULE: ./src/components/messagebox/message.vue
function message_injectStyle (ssrContext) {
  __webpack_require__("tYkC")
}
var message_normalizeComponent = __webpack_require__("VU/8")
/* script */


/* template */

/* template functional */
var message___vue_template_functional__ = false
/* styles */
var message___vue_styles__ = message_injectStyle
/* scopeId */
var message___vue_scopeId__ = null
/* moduleIdentifier (server only) */
var message___vue_module_identifier__ = null
var message_Component = message_normalizeComponent(
  message,
  messagebox_message,
  message___vue_template_functional__,
  message___vue_styles__,
  message___vue_scopeId__,
  message___vue_module_identifier__
)

/* harmony default export */ var components_messagebox_message = (message_Component.exports);

// CONCATENATED MODULE: ./src/components/messagebox/index.js


var Message = {
	showMessage: false,
	showMessageNew: null,
	time: null, // 定时器
	install: function install(Vue, options) {
		if (typeof window !== 'undefined' && window.Vue) {
			Vue = window.Vue;
		}
		Vue.component('Message', components_messagebox_message);

		Vue.prototype.$msg = function (obj, callBack) {
			var opt = components_messagebox_message.data();

			// 这是在main.js时 将用户use的配置写入  覆盖掉默认配置   但这不是最高的配置信息
			for (var property in options) {
				opt[property] = options[property]; // 使用 options 的配置
			}

			// 如果$msg 中没有参数就设置默认的参数信息
			if (!obj instanceof Object) {
				// alert(JSON.stringify(opt))
				// opt.text = obj ? obj : (options ? options.text : opt.text)
				if (obj) {
					opt.text = obj;
				} else {
					for (var _property in obj) {
						opt[_property] = obj[_property];
					}
				}
			} else {
				if (typeof obj === 'string') {
					opt.text = obj;
				} else {
					// 这是选择优先级最高的参数当最终的参数信息
					for (var _property2 in obj) {
						opt[_property2] = obj[_property2]; // 使用 obj  自己在$msg事实 的配置
					}
				}
			}
			if (Message.showMessage || Message.showMessageNew) {
				// 如果Message还在，则先删除前一个Message 的信息以及定时器  并充值默认信息
				clearTimeout(Message.time);
				Message.showMessage = false;
				document.body.removeChild(Message.showMessageNew.$mount().$el);
				Message.showMessageNew = null;
			}
			if (!Message.showMessageNew) {
				// 创建构造器，定义好提示信息的模板
				var MessageT = Vue.extend({
					template: '<transition name=fade-up><div class="vue-Message ' + opt.position + '" style="background:' + opt.background + '" v-show="isShow">' + opt.text + '<Message></Message></div></transition>',
					data: function data() {
						return {
							isShow: Message.showMessage
						};
					}
				});
				// 创建实例，挂载到文档以后的地方
				Message.showMessageNew = new MessageT();
				var tpl = Message.showMessageNew.$mount().$el;
				document.body.appendChild(tpl);
				Message.showMessageNew.isShow = Message.showMessage = true;
			}

			Message.time = setTimeout(function () {
				Message.showMessageNew.isShow = Message.showMessage = false;
				if (typeof callBack === 'function') {
					callBack();
				}
			}, opt.duration);
		};
	}
};
/* harmony default export */ var messagebox = (Message);
// CONCATENATED MODULE: ./src/utils/alert.js

function error(msg) {
    eventBus.$emit('alerts', msg);
}

function alert_success(msg) {
    eventBus.$emit('success', msg);
}

function loadingStart() {
    eventBus.$emit('loading', "正在處理中, 請稍候......", true);
}

function loadingEnd() {
    eventBus.$emit('loading', "", false);
}

// export {error, success}
// CONCATENATED MODULE: ./src/api/index.js
// 配置API接口地址
// var root = 'https://cnodejs.org/api/v1'
var root = '/';
// 引用axios
var axios = __webpack_require__("mtWM");
// import Bus from '../eventBus'


// 自定义判断元素类型JS
function toType(obj) {
  return {}.toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase();
}

/*
  接口处理函数
*/
function _fileUpload(file, url, success, failure) {
  var param = new FormData(); //创建form对象
  param.append('file', file, file.name); //通过append向form对象添加数据
  param.append('chunk', '0'); //添加form表单中其他数据

  var config = {
    headers: { 'Content-Type': 'multipart/form-data' }
  };
  loadingStart();
  axios.post(url, param, config).then(function (response) {
    // console.log(response.data)
    loadingEnd();
    if (success) {
      success(response.data);
    }
  }).catch(function (err) {
    loadingEnd();
    console.log("err--->" + err);
    var errMsg = err.response.statusText + " : " + err.response.data;
    if (failure) {
      failure(err);
    }
  });
}

function apiAxios(method, url, params, success, failure) {
  loadingStart();
  axios({
    method: method,
    headers: {
      'X-Requested-With': 'XMLHttpRequest'
    },
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: false
  }).then(function (res) {
    // console.log("res.data---->"+res.data)
    loadingEnd();
    if (success) {
      success(res.data);
    }
  }).catch(function (err) {
    loadingEnd();
    if (err) {
      console.log("err--->" + err);
      console.log("failure--->" + failure);
      console.log("err.response.statusText--->" + err.response.statusText);
      console.log("err.response.data--->" + err.response.data);
      if (failure) {
        console.log("1--->");
        failure(err);
      } else if ('Unauthorized' == err.response.statusText || err.response.data.indexOf("code: 906,") != -1) {
        console.log("2--->");
        error('會話超時，請重新登錄！');
        window.location.href = "/#/login";
      } else {
        console.log("3--->");
        // window.alert('api error, HTTP CODE: ' + err)
        var errMsg = err.response.statusText + " : " + err.response.data;
        error(errMsg);
      }
    }
  });
}

// 返回在vue模板中的调用接口
/* harmony default export */ var api = ({
  get: function get(url, params, success, failure) {
    return apiAxios('GET', url, params, success, failure);
  },
  post: function post(url, params, success, failure) {
    return apiAxios('POST', url, params, success, failure);
  },
  put: function put(url, params, success, failure) {
    return apiAxios('PUT', url, params, success, failure);
  },
  delete: function _delete(url, params, success, failure) {
    return apiAxios('DELETE', url, params, success, failure);
  },
  fileUpload: function fileUpload(file, url, success, failure) {
    return _fileUpload(file, url, success, failure);
  }
});
// CONCATENATED MODULE: ./src/utils/index.js
/* harmony default export */ var utils = ({
  goodTime: function goodTime(str) {
    var now = new Date().getTime();
    var oldTime = new Date(str).getTime();
    var difference = now - oldTime;
    var result = '';
    var minute = 1000 * 60;
    var hour = minute * 60;
    var day = hour * 24;
    var month = day * 30;
    var year = month * 12;
    var _year = difference / year;
    var _month = difference / month;
    var _week = difference / (7 * day);
    var _day = difference / day;
    var _hour = difference / hour;
    var _min = difference / minute;

    if (_year >= 1) {
      result = '发表于 ' + ~~_year + ' 年前';
    } else if (_month >= 1) {
      result = '发表于 ' + ~~_month + ' 个月前';
    } else if (_week >= 1) {
      result = '发表于 ' + ~~_week + ' 周前';
    } else if (_day >= 1) {
      result = '发表于 ' + ~~_day + ' 天前';
    } else if (_hour >= 1) {
      result = '发表于 ' + ~~_hour + ' 个小时前';
    } else if (_min >= 1) {
      result = '发表于 ' + ~~_min + ' 分钟前';
    } else {
      result = '刚刚';
    }
    return result;
  }
});
// EXTERNAL MODULE: ./src/utils/constant.js
var constant = __webpack_require__("8e4C");
var constant_default = /*#__PURE__*/__webpack_require__.n(constant);

// EXTERNAL MODULE: ./node_modules/axios/index.js
var node_modules_axios = __webpack_require__("mtWM");
var axios_default = /*#__PURE__*/__webpack_require__.n(node_modules_axios);

// EXTERNAL MODULE: ./node_modules/echarts/index.js
var echarts = __webpack_require__("XLwt");
var echarts_default = /*#__PURE__*/__webpack_require__.n(echarts);

// CONCATENATED MODULE: ./src/main.js
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.










// import BootstrapDirectionalButtons from 'bootstrap-directional-buttons'
// import "bootstrap-directional-buttons/dist/bootstrap-directional-buttons.css"

vue_esm["default"].use(es["a" /* default */]);


vue_esm["default"].use(vue_confirm_default.a);

// import VueAlert from '@vuejs-pt/vue-alert'
// Vue.use(VueAlert)

// import Msg from 'vue-message'

vue_esm["default"].use(messagebox, {
  position: 'top', duration: 999999999
});

// 引用API文件

// 将API方法绑定到全局
vue_esm["default"].prototype.$api = api;

// 引用工具文件


// 引用alert文件, 使用时：alerts.error('xxx')

vue_esm["default"].prototype.$alerts = utils_alert_namespaceObject;

// 将工具方法绑定到全局
vue_esm["default"].prototype.$utils = utils;

vue_esm["default"].config.productionTip = false;




// Vue.prototype.$http = axios

/* eslint-disable no-new */

// vue echarts
// import ECharts from 'vue-echarts'
// Vue.component('chart', ECharts)


vue_esm["default"].prototype.$echarts = echarts_default.a;

new vue_esm["default"]({
  el: '#app',
  router: router,
  components: { App: src_App },
  template: '<App/>'
});

/***/ }),

/***/ "Pbsv":
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "static/img/stock04.519da72.png";

/***/ }),

/***/ "VJET":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "ZWwq":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "k49T":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "oZvZ":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "qb6w":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "tC7s":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "tYkC":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "vhDn":
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "static/img/stock02.64af7c8.png";

/***/ }),

/***/ "ws5n":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "zj2Q":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ })

},["NHnr"]);