webpackJsonp([1],{"1/oy":function(t,e){},"1JPK":function(t,e){},"27ZN":function(t,e){},"7fvs":function(t,e){},"8e4C":function(t,e){window.PAGE_SIZE=20},"9M+g":function(t,e){},DwKh:function(t,e){},Id91:function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("7+uW"),s=new n.default,i={data:function(){return{}},created:function(){var t=this;s.$on("alerts",function(e){t.$msg({text:e,duration:3e3})}),s.$on("success",function(e){t.$msg({text:e,duration:3e3})}),s.$on("loading",function(e,a){a?t.$msg({text:e}):$(".vue-Message").hide()})},methods:{}},o={render:function(){var t=this.$createElement;return(this._self._c||t)("div")},staticRenderFns:[]},c={name:"app",components:{Alert:a("VU/8")(i,o,!1,null,null,null).exports}},r={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("Alert"),this._v(" "),e("router-view")],1)},staticRenderFns:[]};var l=a("VU/8")(c,r,!1,function(t){a("zrBF")},null,null).exports,u=a("/ocq"),d={components:{},data:function(){return{loginName:"",password:""}},created:function(){},methods:{login:function(){var t="/api/login?loginName="+this.loginName+"&password="+this.password;this.$api.post(t,null,function(t){window.location.href="/#/index"},function(t){s.$emit("alerts","賬戶或密碼錯誤！")})}}},p={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"text-center"},[a("form",{staticClass:"form-signin",on:{submit:function(e){return e.stopPropagation(),e.preventDefault(),t.login(e)}}},[a("img",{staticClass:"mb-4",attrs:{src:"https://getbootstrap.com/assets/brand/bootstrap-solid.svg",alt:"",width:"72",height:"72"}}),t._v(" "),a("h1",{staticClass:"h3 mb-3 font-weight-normal"},[t._v("Please sign in")]),t._v(" "),a("label",{staticClass:"sr-only",attrs:{for:"inputEmail"}},[t._v("loginName")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.loginName,expression:"loginName"}],staticClass:"form-control",attrs:{type:"loginName",id:"inputEmail",placeholder:"用戶",required:"",autofocus:""},domProps:{value:t.loginName},on:{input:function(e){e.target.composing||(t.loginName=e.target.value)}}}),t._v(" "),a("label",{staticClass:"sr-only",attrs:{for:"inputPassword"}},[t._v("password")]),t._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],staticClass:"form-control",attrs:{type:"password",id:"inputPassword",placeholder:"密碼",required:""},domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}}),t._v(" "),a("button",{staticClass:"btn btn-lg btn-primary btn-block",attrs:{type:"submit"}},[t._v("登錄")]),t._v(" "),a("p",{staticClass:"mt-5 mb-3 text-muted"},[t._v("© 2017-2018")])])])},staticRenderFns:[]};var m=a("VU/8")(d,p,!1,function(t){a("wY6c")},"data-v-3b1b9490",null).exports,f={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("b-navbar",{attrs:{type:"dark",variant:"primary",toggleable:""}},[a("b-navbar-toggle",{attrs:{target:"nav_dropdown_collapse"}}),t._v(" "),a("b-collapse",{attrs:{"is-nav":"",id:"nav_dropdown_collapse"}},[a("b-navbar-nav",{staticClass:"pl-5"},[a("b-nav-item",{attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.go("/")}}},[t._v("自選股")]),t._v(" "),a("b-nav-item",{attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.go("/importantNews/1")}}},[t._v("市場重大新聞")]),t._v(" "),a("b-nav-item-dropdown",{attrs:{text:"個性化設置",right:""}},[a("b-dropdown-item",{attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.go("/newsSettings")}}},[t._v("\n          新聞設置\n        ")]),t._v(" "),a("b-dropdown-item",{attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.go("/editMySelectedType")}}},[t._v("\n          自選股\n        ")]),t._v(" "),a("b-dropdown-item",{attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.go("/stockDailyTransactions")}}},[t._v("\n          交易資料導入與查詢\n        ")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var v={render:function(){var t=this.$createElement;return(this._self._c||t)("div",{staticClass:"copy"},[this._v("\n  Copy © wenchun\n")])},staticRenderFns:[]},h={components:{Header:a("VU/8")({data:function(){return{}},created:function(){},methods:{go:function(t){this.$router.push(t)}}},f,!1,function(t){a("qXB5")},"data-v-4d445be6",null).exports,Footer:a("VU/8")(null,v,!1,null,null,null).exports}},g={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("Header"),this._v(" "),this._t("default"),this._v(" "),e("Footer")],2)},staticRenderFns:[]},y=a("VU/8")(h,g,!1,null,null,null).exports,k=a("UxJx"),b=a.n(k),S={components:{Autocomplete:b.a},data:function(){return{list:[],firstStockId:"",myStockSelectedName:"庫存股",selected:null,options:[],selectedTypes:[],mySelectedTypes:[]}},created:function(){var t=this;this.getStockMySelectedTypes(),this.getData(),s.$on("getMyStockSelected",function(e,a){void 0!=e&&(t.myStockSelectedName=a,t.$api.get("/api/stock/getStockMyDatasByType/"+e,null,function(e){t.list=e,void 0!=e&&e.length>0?(t.firstStockId=e[0].stockId,t.push("/content/"+t.firstStockId+"/1")):(t.push("/content/0/1"),s.$emit("emptyNews"))}))}),s.$on("getAllStockMyStore",function(){t.myStockSelectedName="庫存股",t.$api.get("/api/stock/getStockMyDatasByStore",null,function(e){t.list=e,void 0!=e&&e.length>0?(t.firstStockId=e[0].stockId,t.push("/content/"+t.firstStockId+"/1")):(t.push("/content/0/1"),s.$emit("emptyNews"))})})},methods:{getUrl:function(t){return"/api/stock/search4StockData?query="+t},selectedProcess:function(t,e){e.clear(),this.push("/content/"+t.value+"/1")},formattedDisplay:function(t){return t.no+" "+t.company},push:function(t){this.$router.push(t),s.$emit("initCurrentPage",1)},isSelected:function(t){return t==(void 0==this.$route.params.stockId?this.firstStockId:this.$route.params.stockId)?"selected":""},go:function(t){this.push("/content/"+t+"/1")},toFront:function(){this.$router;var t=$(".selected").get(0),e=$(t).closest("span").prev().find("a").attr("href");void 0!=e&&this.push(e)},toBack:function(){this.$router;var t=$(".selected").get(0),e=$(t).closest("span").next().find("a").attr("href");void 0!=e&&this.push(e)},openModal:function(){this.getStockMySelectedTypes(),this.clearName()},clearName:function(){this.selected=""},handleOk:function(t){t.preventDefault(),this.selected?this.handleSubmit():alert("請選擇自選股!")},handleSubmit:function(t){var e=void 0==this.$route.params.stockId?this.firstStockId:this.$route.params.stockId;this.changeStockMySelected(e,this.selected,this.myStockSelectedName),this.clearName(),this.$refs.modal.hide()},getData:function(){var t=this;this.$api.get("/api/stock/getStockMyDatasByStore",null,function(e){t.list=e,t.firstStockId=e[0].stockId,t.push("/content/"+t.firstStockId+"/1")})},getStockMySelectedTypes:function(){var t=this;this.options=[];var e=this.$route.params.stockId;void 0!=e&&""!=e&&this.$api.get("/api/stock/getMySelectedTypesByStockId/"+e,null,function(e){var a=[];if(void 0!=e&&e.length>0)for(var n=0;n<e.length;n++){var s=e[n].type;a.push(s)}t.$api.get("/api/stock/getStockMySelectedTypes",null,function(e){for(var n=0;n<e.length;n++){var s=-1!=a.indexOf(e[n].type);s||t.options.push({value:e[n].type,text:e[n].name,disabled:s})}})})},changeStockMySelected:function(t,e,a){var n=[];n.push(t);var i={selectedType:e,stockIds:n};this.$api.post("/api/stock/changeStockMySelected",i,function(t){s.$emit("success","保存成功!"),s.$emit("autoSelectedMyStockSelectedType",e,a)})}}},_={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"m-2"},[a("autocomplete",{ref:"autocomplete",attrs:{source:t.getUrl,"input-class":"form-control empty-form-control","results-property":"data","results-display":t.formattedDisplay},on:{selected:t.selectedProcess}})],1),t._v(" "),a("div",{staticClass:"pt-2 pb-2"},[a("b-btn",{attrs:{size:"sm",variant:"primary"},on:{click:t.toFront}},[t._v("<-")]),t._v(" "),a("b-btn",{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent",modifiers:{modalPrevent:!0}}],attrs:{size:"sm",variant:"primary"}},[t._v("+")]),t._v(" "),a("b-btn",{attrs:{size:"sm",variant:"primary"},on:{click:t.toBack}},[t._v("->")]),t._v(" "),a("b-modal",{ref:"modal",attrs:{id:"modalPrevent",title:"請選擇自選股"},on:{ok:t.handleOk,shown:t.openModal}},[a("form",{on:{submit:function(e){return e.stopPropagation(),e.preventDefault(),t.handleSubmit(e)}}},[a("b-form-select",{staticClass:"mb-3",attrs:{options:t.options},model:{value:t.selected,callback:function(e){t.selected=e},expression:"selected"}})],1)])],1),t._v(" "),a("div",{staticClass:"selected_name text"},[a("h5",[t._v(t._s(t.myStockSelectedName))]),t._v(" "),t._l(t.list,function(e){return a("span",{staticClass:"d-lg-block"},[a("a",{class:t.isSelected(e.stockId),attrs:{href:"/content/"+e.stockId+"/1"},on:{click:function(a){a.preventDefault(),t.go(e.stockId)}}},[t._v("\n      "+t._s(e.company)+"\n    ")]),a("br")])})],2)])},staticRenderFns:[]};var w=a("VU/8")(S,_,!1,function(t){a("27ZN")},"data-v-636eb39c",null).exports,x=a("bOdI"),I=a.n(x),N={data:function(){return I()({name:"",items:[],currSelectedType:""},"name","")},created:function(){var t=this;this.getData(),s.$on("autoSelectedMyStockSelectedType",function(e,a){t.currSelectedType=e,s.$emit("getMyStockSelected",e,a)})},methods:{getAllStockMyStore:function(){this.selectedTypes=[],this.currSelectedType="0",s.$emit("getAllStockMyStore")},getMyStockSelected:function(t,e){this.currSelectedType=t,s.$emit("getMyStockSelected",t,e)},isSelected:function(t){return this.currSelectedType==t?"warning":"success"},clearName:function(){this.name="",this.$refs.focusThis.focus()},handleOk:function(t){t.preventDefault(),this.name?this.handleSubmit():alert("请输入自選股名称!")},handleSubmit:function(){this.saveData(this.name),this.clearName(),this.$refs.modal.hide()},getData:function(){var t=this;this.$api.get("/api/stock/getStockMySelectedTypes",null,function(e){t.items=e})},saveData:function(t){var e=this,a="/api/stock/saveStockMySelectedType?name="+t;this.$api.post(a,null,function(t){e.getData()})}},watch:{$route:function(t,e){}}},C={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("span",[a("b-button",{attrs:{variant:""==t.currSelectedType||"0"==t.currSelectedType?"warning":"success"},on:{click:t.getAllStockMyStore}},[t._v("\n    庫存股\n  ")])],1),t._v(" "),t._l(t.items,function(e){return a("span",[a("b-button",{attrs:{variant:t.isSelected(e.type),id:e.type},on:{click:function(a){t.getMyStockSelected(e.type,e.name)}}},[t._v("\n      "+t._s(e.name)+"\n      ")])],1)}),t._v(" "),a("b-btn",{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent2",modifiers:{modalPrevent2:!0}}],attrs:{variant:"success"}},[t._v("+")]),t._v(" "),a("b-modal",{ref:"modal",attrs:{id:"modalPrevent2",title:"请输入自選股名称"},on:{ok:t.handleOk,shown:t.clearName}},[a("form",{on:{submit:function(e){return e.stopPropagation(),e.preventDefault(),t.handleSubmit(e)}}},[a("b-form-input",{ref:"focusThis",attrs:{type:"text",placeholder:"输入自選股名称"},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1)])],2)},staticRenderFns:[]};var D={data:function(){return{stockId:"",company:"",no:"",typeName:"",electronics:"",currSelectedType:"",currSelectedName:"",companyStatus:""}},created:function(){var t=this;this.stockId=this.$route.params.stockId,this.getData(this.stockId),s.$on("getAllStockMyStore",function(){t.currSelectedType="",t.currSelectedName=""}),s.$on("getMyStockSelected",function(e,a){t.currSelectedType=e,t.currSelectedName=a})},mounted:function(){},methods:{removeOneStockMySelected:function(){if(this.stockId=this.$route.params.stockId,""==this.currSelectedType||void 0==this.currSelectedType)s.$emit("success","該股不在某個自選股中，無需移除!");else{var t=this;this.$confirm("是否確定從"+t.currSelectedName+"中移除?").then(function(){var e="/api/stock/removeOneStockMySelected?stockId="+t.stockId+"&selectedType="+t.currSelectedType;t.$api.post(e,null,function(e){s.$emit("success","移除成功!"),s.$emit("autoSelectedMyStockSelectedType",t.currSelectedType,t.currSelectedName)})}).catch(function(t){console.log("Exception---\x3e"+t)})}},saveCompanyStatus:function(){if(this.stockId=this.$route.params.stockId,void 0==this.stockId||""==this.stockId);else{var t=$("#formControlTextarea1").val(),e="/api/stock/saveCompanyStatus?stockId="+this.stockId+"&companyStatus="+t;this.$api.post(e,null,function(t){s.$emit("success","保存公司現況成功!")})}},getData:function(t){var e=this;void 0!=t&&""!=t&&0!=t&&this.$api.get("/api/stock/getStockData/"+t,null,function(t){""!=t&&void 0!=t&&(e.company=t.company,e.no=t.no,e.typeName=t.typeName,e.electronics=t.electronics,e.companyStatus=t.companyStatus)})}},watch:{$route:function(t,e){this.stockId=this.$route.params.stockId,this.getData(this.stockId)}}},M={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"float-left mt-3"},[a("div",[a("h2",[t._v(t._s(t.company)+"("+t._s(t.no)+")")])]),t._v(" "),""!=t.typeName||""!=t.electronics?a("div",[t._v(t._s(t.typeName)+" - "+t._s(t.electronics))]):t._e()]),t._v(" "),a("div",{staticClass:"float-left px-3 mt-3"},[a("div",[a("a",{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent",modifiers:{modalPrevent:!0}}],attrs:{href:"#"}},[a("span",{staticClass:"oi oi-plus"})])]),t._v(" "),a("div",[a("a",{attrs:{href:"#"},on:{click:function(e){return e.preventDefault(),t.removeOneStockMySelected(e)}}},[a("span",{staticClass:"oi oi-minus"})])])]),t._v(" "),a("div",{staticClass:"float-left pl-3",staticStyle:{width:"65%"}},[a("b-tabs",[a("b-tab",{attrs:{title:"法人動態",active:""}}),t._v(" "),a("b-tab",{attrs:{title:"資卷變化"}}),t._v(" "),a("b-tab",{attrs:{title:"大戶籌碼"}}),t._v(" "),a("b-tab",{attrs:{title:"主力進出"}}),t._v(" "),a("b-tab",{attrs:{title:"周報股利"}}),t._v(" "),a("div",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.companyStatus,expression:"companyStatus"}],staticClass:"form-control",attrs:{id:"formControlTextarea1"},domProps:{value:t.companyStatus},on:{input:function(e){e.target.composing||(t.companyStatus=e.target.value)}}})])],1)],1),t._v(" "),a("div",{staticClass:"float-left",staticStyle:{height:"125px","padding-top":"40px","padding-left":"10px"}},[a("b-button",{attrs:{variant:"success"},on:{click:t.saveCompanyStatus}},[t._v("保存")])],1)])},staticRenderFns:[]},T={data:function(){return{company:"",stockId:"",numberOfPages:0,currentPage:1,displayAll:0,type:1,pageSize:15,fields:{content_title:{label:'<span id="content_id">: </span>個股新聞與研究報告',sortable:!0}},items:[],intervalid1:null}},created:function(){var t=this;this.getData(),s.$on("emptyNews",function(){t.cleanNews()}),s.$on("initCurrentPage",function(e){t.currentPage=e}),this.timeOutsetInterval()},mounted:function(){},destroyed:function(){null!=this.intervalid1&&clearInterval(this.intervalid1)},methods:{timeOutsetInterval:function(){var t=this;null!=this.intervalid1&&clearInterval(this.intervalid1);var e=this;this.autoFetch(e),e.intervalid1=setInterval(function(){t.autoFetch(e)},3e5)},autoFetch:function(t){var e=this;if(s.$emit("loading","正在自動獲取最新的新聞中...",!0),this.stockId=this.$route.params.stockId,void 0!=this.stockId){console.log("News autoFetch stockNews started......"+this.stockId);var a="/api/stock/fetchLatestNews?stockId="+this.stockId;t.$api.post(a,null,function(t){s.$emit("success","自動更新新聞成功!"),e.getData(),console.log("News autoFetch stockNews end......"+e.stockId)})}},linkGen:function(t){return{path:"/content/"+this.stockId+"/"+t}},cleanNews:function(){this.items=[],$("#content_id").text(""),this.numberOfPages=0,this.currentPage=1},showExcludeNews:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=1,this.displayAll=0,this.getData(1)},showIncludeNews:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=0,this.displayAll=0,this.getData(1)},showExcludeNewsAll:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=1,this.displayAll=1,this.getData(1)},showIncludeNewsAll:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=0,this.displayAll=1,this.getData(1)},getData:function(t){var e=this;void 0==t&&(t=this.$route.params.pageNum),this.stockId=this.$route.params.stockId,void 0!=this.stockId&&""!=this.stockId&&0!=this.stockId&&this.$api.get("/api/stock/getStockData/"+this.stockId,null,function(a){e.company=a.company;var n=1==e.displayAll?0:e.stockId,s=(void 0==e.type||0==e.type?"/api/stock/getNewsIncludeBystockId/":"/api/stock/getNewsExcludeBystockId/")+n+"/"+t+"/"+e.pageSize+"/0";e.$api.get(s,null,function(t){e.numberOfPages=t.pageTotal;var a=t.rows;e.items=[];for(var n=0;n<a.length;n++){var s='<a target="_blank" href="'+a[n].url+'">'+a[n].subject+"</a>";e.items.push({content_title:s})}}),$("#content_id").text(e.company+": ").parent().remove()})}},watch:{$route:function(t,e){var a=this;this.stockId=this.$route.params.stockId,void 0!=this.stockId&&""!=this.stockId&&0!=this.stockId?(this.timeOutsetInterval(),this.$api.get("/api/stock/getStockData/"+this.stockId,null,function(t){a.company=t.company,console.log("company---\x3e"+a.company);var e=void 0==a.$route.params.pageNum?1:a.$route.params.pageNum,n=1==a.displayAll?0:a.stockId,s=(void 0==a.type||0==a.type?"/api/stock/getNewsIncludeBystockId/":"/api/stock/getNewsExcludeBystockId/")+n+"/"+e+"/"+a.pageSize+"/0";a.$api.get(s,null,function(t){a.items=[],a.numberOfPages=t.pageTotal;for(var e=t.rows,n=0;n<e.length;n++){var s='<a target="_blank" href="'+e[n].url+'">'+e[n].subject+"</a>";a.items.push({content_title:s})}}),$("#content_id").text(a.company+": ").parent().hide()})):this.cleanNews()}}},P={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("nav",{staticClass:"nav nav-pills nav-justified"},[a("div",{staticClass:"pt-2 pr-2"},[a("h5",[t._v(t._s(this.company))])]),t._v(" "),a("a",{staticClass:"nav-link active",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showExcludeNews(e)}}},[t._v("個股新聞")]),t._v(" "),a("a",{staticClass:"nav-link",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showIncludeNews(e)}}},[t._v("焦點新聞")]),t._v(" "),a("span",{staticStyle:{"padding-left":"250px"}}),t._v(" "),a("a",{staticClass:"nav-link",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showExcludeNewsAll(e)}}},[t._v("自選股全新聞")]),t._v(" "),a("a",{staticClass:"nav-link",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showIncludeNewsAll(e)}}},[t._v("自選股全焦點")])]),t._v(" "),a("div",{staticClass:"text container-fluid"},[a("b-table",{attrs:{striped:"",hover:"",items:t.items,fields:t.fields},scopedSlots:t._u([{key:"content_title",fn:function(e){return a("span",{domProps:{innerHTML:t._s(e.value)}})}}])}),t._v(" "),a("b-pagination-nav",{attrs:{align:"center","number-of-pages":t.numberOfPages,"base-url":"#","link-gen":t.linkGen},model:{value:t.currentPage,callback:function(e){t.currentPage=e},expression:"currentPage"}})],1)])},staticRenderFns:[]};var E={components:{MainLayout:y,StockMyData:w,StockMySelectedType:a("VU/8")(N,C,!1,function(t){a("pAPe")},"data-v-bbc70ac2",null).exports,StockDetail:a("VU/8")(D,M,!1,null,null,null).exports,StockNews:a("VU/8")(T,P,!1,function(t){a("7fvs")},"data-v-5281eb32",null).exports}},F={render:function(){var t=this.$createElement,e=this._self._c||t;return e("main-layout",[e("b-container",{staticClass:"border",attrs:{fluid:""}},[e("b-row",[e("b-col",{staticClass:"border-right border-primary",staticStyle:{"max-width":"12%"}},[e("stock-my-data")],1),this._v(" "),e("b-col",{staticStyle:{"max-width":"88%"}},[e("b-row",[e("b-col",{attrs:{cols:"12",sm:"12"}},[e("stock-my-selected-type"),this._v(" "),e("stock-detail")],1)],1),this._v(" "),e("b-row",[e("b-col",{attrs:{cols:"9",sm:"9"}},[e("b-row",{staticClass:"mt-3"},[e("b-col",{attrs:{cols:"12",sm:"12"}},[e("stock-news")],1)],1)],1),this._v(" "),e("b-col",{attrs:{cols:"3",sm:"3"}},[e("img",{staticClass:"w-100",attrs:{src:a("vhDn")}}),this._v(" "),e("img",{staticClass:"w-100",attrs:{src:a("Pbsv")}})])],1)],1)],1)],1)],1)},staticRenderFns:[]},O=a("VU/8")(E,F,!1,null,null,null).exports,U=[],A={components:{MainLayout:y},data:function(){return{items:[],numberOfPages:0,currentPage:this.$route.params.pageNum,type:1,pageSize:PAGE_SIZE,fields:{content_title:{label:'<span id="content_id">: </span>市場重大新聞',sortable:!0}}}},created:function(){var t=this;this.getData(1),s.$on("initCurrentPage",function(e){t.currentPage=e}),this.timeOutsetInterval()},destroyed:function(){null!=this.intervalid1&&clearInterval(this.intervalid1)},methods:{timeOutsetInterval:function(){var t=this;null!=this.intervalid1&&clearInterval(this.intervalid1);var e=this;this.autoFetch(e),this.intervalid1=setInterval(function(){t.autoFetch(e)},6e5)},autoFetch:function(t){var e=this;s.$emit("loading","正在自動獲取最新的新聞中...",!0),console.log("News autoFetch importantNews started......");t.$api.post("/api/stock/fetchImportantLatestNews",null,function(t){s.$emit("success","自動更新新聞成功!"),e.getData(1),console.log("News autoFetch importantNews end......")})},linkGen:function(t){return{path:"/importantNews/"+t}},showExcludeNews:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=1,this.$router.push("/importantNews/1"),this.getData(1)},showIncludeNews:function(t){$(".active").removeClass("active"),$(t.target).addClass("active"),this.type=0,this.$router.push("/importantNews/1"),this.getData(1)},getData:function(t){var e=this;this.currentPage=void 0!=t?t:this.$route.params.pageNum,U=[];var a=(void 0==this.type||0==this.type?"/api/stock/getImportantNewsInclude/":"/api/stock/getImportantNewsExclude/")+this.currentPage+"/"+this.pageSize;this.$api.get(a,null,function(t){e.numberOfPages=t.pageTotal,$(t.rows).each(function(){var t='<a target="_blank" href="'+this.url+'">'+this.froms+"</a>";U.push({content_title:t})}),e.items=U,$("#content_id").parent().remove()})}},watch:{$route:function(t,e){var a=this;this.currentPage=this.$route.params.pageNum,U=[];var n=(void 0==this.type||0==this.type?"/api/stock/getImportantNewsInclude/":"/api/stock/getImportantNewsExclude/")+this.currentPage+"/"+this.pageSize;this.$api.get(n,null,function(t){a.numberOfPages=t.pageTotal,$(t.rows).each(function(){var t='<a target="_blank" href="'+this.url+'">'+this.froms+"</a>";U.push({content_title:t})}),a.items=U}),$("#content_id").parent().remove()}}},V={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main-layout",[[a("nav",{staticClass:"mt-3 ml-3 nav nav-pills nav-justified"},[a("a",{staticClass:"nav-link active",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showExcludeNews(e)}}},[t._v("市場重大新聞")]),t._v(" "),a("a",{staticClass:"nav-link",attrs:{href:"#"},on:{click:function(e){e.preventDefault(),t.showIncludeNews(e)}}},[t._v("市場焦點新聞")])]),t._v(" "),a("div",{staticClass:"text container-fluid"},[a("b-table",{attrs:{striped:"",hover:"",items:t.items,fields:t.fields},scopedSlots:t._u([{key:"content_title",fn:function(e){return a("span",{domProps:{innerHTML:t._s(e.value)}})}}])}),t._v(" "),a("b-pagination-nav",{attrs:{align:"center","number-of-pages":t.numberOfPages,"base-url":"#","link-gen":t.linkGen},model:{value:t.currentPage,callback:function(e){t.currentPage=e},expression:"currentPage"}})],1)]],2)},staticRenderFns:[]};var B=a("VU/8")(A,V,!1,function(t){a("P6mD")},"data-v-596c57dc",null).exports,R={components:{MainLayout:y},data:function(){return{file:null,items:[]}},created:function(){this.getData()},methods:{update:function(t){var e=this,a=t.target.files[0];this.$api.fileUpload(a,"/api/stock/uploadStockDailyTransactions",function(t){s.$emit("success","導入成功!"),e.getData()}),this.$refs.fileinput.reset()},datePick:function(t){var e=t.target;WdatePicker({el:e,readOnly:!0,dateFmt:"yyyy/MM/dd",lang:"zh-tw"})},selectByDate:function(){this.getData()},getData:function(){var t=this,e=$("#startDate").val(),a=$("#endDate").val();if(void 0!=e&&""!=e){var n="/api/stock/getStockDailyTransactions?startDate="+e+"&endDate="+a;this.$api.get(n,null,function(e){if(e.length>0)for(var a=0;a<e.length;a++){var n=e[a];t.items.push({"成交日期":n.txDate,"股票名稱":"["+n.no+"] "+n.company,"交易種類":n.txKind,"成交數量":n.quantity,"成交價格":n.txPrice,"成交金額":n.txAmount,"手續費":n.fee,"交易稅":n.txTallage,"證所稅":n.zsTallage,"融資金額":n.financingAmount,"利息":n.interest,"借券費":n.debFee,"客戶淨收付":n.customerCharge,"損益":n.profit,"報酬率(%)":n.payRate,"持有成本":n.holdingCost})}else t.items=[]})}}},watch:{$route:function(t,e){}}},q={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main-layout",[[a("div",[a("b-container",{attrs:{fluid:""}},[a("b-row",{staticClass:"p-3"},[a("b-col",{staticClass:"pt-3"},[a("b",[t._v("請選擇需要導入的每天買入賣出股文件:")])])],1),t._v(" "),a("b-row",[a("b-col",[a("b-form-file",{ref:"fileinput",staticClass:"p-3",attrs:{id:"file1",placeholder:"Please choose a file."},on:{change:t.update},model:{value:t.file,callback:function(e){t.file=e},expression:"file"}})],1)],1),t._v(" "),a("b-row",[a("b-col",[a("form",{staticClass:"p-3",on:{submit:function(e){return e.preventDefault(),t.selectByDate(e)}}},[a("input",{staticClass:"form-control w-25 float-left",attrs:{id:"startDate",placeholder:"請選擇開始日期",required:"",type:"text",autocomplete:"off"},on:{click:t.datePick}}),t._v(" "),a("div",{staticClass:"float-left px-3"},[t._v(" - ")]),t._v(" "),a("input",{staticClass:"form-control w-25 float-left",attrs:{id:"endDate",placeholder:"請選擇結束日期",type:"text",autocomplete:"off"},on:{click:t.datePick}}),t._v(" "),a("b-button",{staticClass:"px-3",attrs:{variant:"primary",type:"submit"}},[t._v("查詢")])],1),t._v(" "),a("p",{staticClass:"p-3 mb-2 bg-info text-white"},[t._v("每天買入賣出股列表：")]),t._v(" "),a("b-table",{attrs:{hover:"",items:t.items}})],1)],1)],1)],1)]],2)},staticRenderFns:[]};var z=a("VU/8")(R,q,!1,function(t){a("sT6q")},"data-v-77a70750",null).exports,L=a("Lokx"),H=a.n(L),j={components:{MainLayout:y,Autocomplete:b.a},data:function(){return{name:"",currSelectedType:"",initNumber:50,options:[{value:"",text:"Please select..."}],list:[],items:[]}},created:function(){this.getData()},mounted:function(){var t=this.$api;H.a.create(stockMySelectedItem,{handle:".move-item",animation:150,onUpdate:function(e){e.item;var a=[];if($("#stockMySelectedItem button").each(function(t,e){var n={type:$(e).attr("id"),name:$(e).attr("data")};a.push(n)}),a.length>0){var n={changeStockMySelectedTypeDtos:a};t.post("/api/stock/changeStockMySelectedType",n,function(t){s.$emit("success","調整自選股顯示順序成功!")})}}}),H.a.create(listWithHandle,{handle:".oi-move",animation:150})},methods:{clearName:function(){this.name="",this.$refs.focusThis.focus()},handleOk:function(t){t.preventDefault(),this.name?this.handleSubmit():alert("请输入自選股名称!")},handleSubmit:function(){this.saveData(this.name),this.clearName(),this.$refs.modal.hide()},saveData:function(t){var e=this,a="/api/stock/saveStockMySelectedType?name="+t;this.$api.post(a,null,function(t){e.getData()})},clearName2:function(){this.name="",this.$refs.focusThis2.focus()},handleOk2:function(t){t.preventDefault(),this.name?this.handleSubmit2():alert("请输入需要修改的自選股名称!")},handleSubmit2:function(){this.renameStockMydataName(this.name),this.clearName2(),this.$refs.modal2.hide()},renameStockMydataName:function(t){var e=this;if(""==this.currSelectedType)alert("請先選擇對應的自選股名稱!");else{var a="/api/stock/renameStockMydataName?selectedType="+this.currSelectedType+"&name="+t;this.$api.post(a,null,function(t){e.getData(),s.$emit("success","修改名稱成功!")})}},save2StockMyData:function(){if(""==this.currSelectedType)alert("請先選擇對應的自選股名稱!");else{var t=[];if($(".form-control input[type='hidden']").each(function(e,a){var n=$(a).val();""!=n&&t.push(n)}),t.length>0){var e={selectedType:this.currSelectedType,stockIds:t};this.$api.post("/api/stock/saveAllStockMySelected",e,function(t){s.$emit("success","保存成功!")})}}},isSelected:function(t){return this.currSelectedType==t?"warning":"success"},getUrl:function(t){return"/api/stock/search4StockMyData?query="+t},selectedProcess:function(t,e){$(".form-control input[type='hidden']").each(function(a,n){var i=$(n).val();""!=i&&i==t.value&&(e.clear(),s.$emit("alerts",t.display+"已經存在!"))})},formattedDisplay:function(t){return t.no+" "+t.company},removeStockMySelected:function(t,e){this.$route.params.stockId;var a=this;this.$confirm("是否確定從"+e+"中移除?").then(function(){var e="/api/stock/removeStockMySelected?selectedType="+t;a.$api.post(e,null,function(t){a.getData(),a.currSelectedType="",a.list=[]})}).catch(function(t){console.log("Exception---\x3e"+t)})},changedValue:function(t){this.currSelectedType=t,$(".empty-form-control input").val(""),this.getMyStockMySelected(t)},getMyStockMySelected:function(t){var e=this;this.list=[],""!=t&&this.$api.get("/api/stock/getStockMyDatasByType/"+t,null,function(t){e.list=t})},getData:function(){var t=this;this.$api.get("/api/stock/getStockMySelectedTypes",null,function(e){t.items=e})}}},G={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main-layout",[a("div",[a("div",{attrs:{id:"stockMySelectedItem"}},t._l(t.items,function(e){return a("span",{staticClass:"move-item"},[a("b-button",{attrs:{variant:t.isSelected(e.type),id:e.type,data:e.name},on:{click:function(a){t.changedValue(e.type)}}},[t._v("\n            "+t._s(e.name)+"\n        "),a("span",{attrs:{"aria-hidden":"true"},on:{click:function(a){a.preventDefault(),t.removeStockMySelected(e.type,e.name)}}},[t._v("×")])])],1)})),t._v(" "),a("div",{staticClass:"float-right w-25"},[a("b-btn",{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent",modifiers:{modalPrevent:!0}}],attrs:{variant:"info"}},[t._v("添加")]),t._v(" "),""!=t.currSelectedType?a("b-btn",{directives:[{name:"b-modal",rawName:"v-b-modal.modalPrevent2",modifiers:{modalPrevent2:!0}}],attrs:{variant:"info"}},[t._v("修改名稱")]):t._e(),t._v(" "),""!=t.currSelectedType?a("b-btn",{attrs:{variant:"info"},on:{click:t.save2StockMyData}},[t._v("保存")]):t._e(),t._v(" "),a("b-modal",{ref:"modal",attrs:{id:"modalPrevent",title:"请输入自選股名称"},on:{ok:t.handleOk,shown:t.clearName}},[a("form",{on:{submit:function(e){return e.stopPropagation(),e.preventDefault(),t.handleSubmit(e)}}},[a("b-form-input",{ref:"focusThis",attrs:{type:"text",placeholder:"输入自選股名称"},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1)]),t._v(" "),a("b-modal",{ref:"modal2",attrs:{id:"modalPrevent2",title:"请输入需要修改的自選股名称"},on:{ok:t.handleOk2,shown:t.clearName2}},[a("form",{on:{submit:function(e){return e.stopPropagation(),e.preventDefault(),t.handleSubmit2(e)}}},[a("b-form-input",{ref:"focusThis2",attrs:{type:"text",placeholder:"请输入需要修改的自選股名称"},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1)])],1),t._v(" "),a("div",{attrs:{id:"listWithHandle"}},[t._l(t.list,function(e,n){return a("div",{staticClass:"list-group-item float-left wd-fixed py-1 my-1"},[a("span",{staticClass:"badge"},[t._v(t._s(n+1))]),t._v(" "),a("span",{staticClass:"oi oi-move",attrs:{"aria-hidden":"true"}}),t._v(" "),a("autocomplete",{ref:"autocomplete"+n,refInFor:!0,attrs:{source:t.getUrl,"input-class":"form-control","results-property":"data","results-display":t.formattedDisplay,initialValue:e.stockId,initialDisplay:e.no+" "+e.company},on:{selected:t.selectedProcess}})],1)}),t._v(" "),t._l(t.initNumber-t.list.length,function(e){return""!=t.currSelectedType?a("div",{staticClass:"list-group-item float-left wd-fixed py-1 my-1"},[a("span",{staticClass:"badge"},[t._v(t._s(e+t.list.length))]),t._v(" "),a("span",{staticClass:"oi oi-move",attrs:{"aria-hidden":"true"}}),t._v(" "),a("autocomplete",{ref:"autocomplete"+e,refInFor:!0,attrs:{source:t.getUrl,"input-class":"form-control empty-form-control","results-property":"data","results-display":t.formattedDisplay},on:{selected:t.selectedProcess}})],1):t._e()}),t._v(" "),a("div",{staticClass:"clearfix"})],2)])])},staticRenderFns:[]};var K=a("VU/8")(j,G,!1,function(t){a("1JPK")},"data-v-1d49ab45",null).exports,W={components:{MainLayout:y},data:function(){return{excludesItems:[],includesItems:[]}},created:function(){this.getExcludesItems(),this.getIncludesItems()},mounted:function(){},methods:{saveExcludeItem:function(t){var e=this,a=[];if($(".exclude-item input").each(function(t,e){var n=$(e).val();-1==a.indexOf(n)&&a.push(n)}),a.length>0){var n="/api/stock/saveStockNewsKeyByExclude?keys[]="+a.join();this.$api.post(n,null,function(t){e.getExcludesItems()})}else alert("記錄不能為空!")},saveIncludeItem:function(t){var e=this,a=[];if($(".include-item input").each(function(t,e){var n=$(e).val();-1==a.indexOf(n)&&a.push(n)}),a.length>0){var n="/api/stock/saveStockNewsKeyByInclude?keys[]="+a.join();this.$api.post(n,null,function(t){e.getIncludesItems()})}else alert("記錄不能為空!")},addExcludeItem:function(t){$("#add-exclude-item").before('\n      <div class="exclude-item w-5 float-left p-1">\n          <input type="text" class="form-control" />\n      </div>\n      ')},addIncludeItem:function(t){$("#add-include-item").before('\n      <div class="include-item w-5 float-left p-1">\n          <input type="text" class="form-control" />\n      </div>\n      ')},getExcludesItems:function(){var t=this;this.excludesItems=[];this.$api.get("/api/stock/getStockNewsKeyByExclude",null,function(e){$(".exclude-item").remove(),t.excludesItems=e})},getIncludesItems:function(){var t=this;this.includesItems=[];this.$api.get("/api/stock/getStockNewsKeyByInclude",null,function(e){$(".include-item").remove(),t.includesItems=e})}}},X={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main-layout",[a("div",{staticClass:"border border-primary m-2 float-left",staticStyle:{width:"45%"}},[a("h5",{staticClass:"text-primary p-1"},[t._v("去重關鍵字：\n    "),a("button",{staticClass:"btn btn-primary",attrs:{type:"button"},on:{click:function(e){t.saveExcludeItem(e)}}},[t._v("保存")]),t._v(" "),a("button",{staticClass:"btn btn-primary",attrs:{type:"button"},on:{click:function(e){t.addExcludeItem(e)}}},[t._v("添加")])]),t._v(" "),t._l(t.excludesItems,function(t){return a("div",{staticClass:"exclude-item w-5 float-left p-1"},[a("input",{staticClass:"form-control",attrs:{type:"text"},domProps:{value:t.key}})])}),t._v(" "),a("div",{staticClass:"w-3 float-left p-1",attrs:{id:"add-exclude-item"}}),t._v(" "),a("div",{staticClass:"clearfix"})],2),t._v(" "),a("div",{staticClass:"border border-success m-2 float-left",staticStyle:{width:"45%"}},[a("h5",{staticClass:"text-success p-1"},[t._v("焦點關鍵字：\n    "),a("button",{staticClass:"btn btn-success",attrs:{type:"button"},on:{click:function(e){t.saveIncludeItem(e)}}},[t._v("保存")]),t._v(" "),a("button",{staticClass:"btn btn-success",attrs:{type:"button"},on:{click:function(e){t.addIncludeItem(e)}}},[t._v("添加")])]),t._v(" "),t._l(t.includesItems,function(t){return a("div",{staticClass:"include-item w-5 float-left p-1"},[a("input",{staticClass:"form-control",attrs:{type:"text"},domProps:{value:t.key}})])}),t._v(" "),a("div",{staticClass:"w-3 float-left p-1",attrs:{id:"add-include-item"}}),t._v(" "),a("div",{staticClass:"clearfix"})],2),t._v(" "),a("div",{staticClass:"clearfix"})])},staticRenderFns:[]};var Z=a("VU/8")(W,X,!1,function(t){a("yZDo")},"data-v-04b63713",null).exports;n.default.use(u.a);var J=new u.a({mode:"hash",routes:[{path:"/",component:O},{path:"/login",component:m},{path:"/index",component:O},{path:"/content/:stockId",component:O,children:[{path:":pageNum",component:O}]},{path:"/importantNews/:pageNum",component:B},{path:"/stockDailyTransactions",component:z},{path:"/editMySelectedType",component:K},{path:"/newsSettings",component:Z}]}),Y=a("e6fC"),Q=(a("qb6w"),a("9M+g"),a("DwKh"),a("XGXU")),tt=a.n(Q),et={render:function(){var t=this.$createElement;return(this._self._c||t)("transition",{attrs:{name:"fade-up"}})},staticRenderFns:[]};var at=a("VU/8")({name:"Vue-Message",data:function(){return{text:"Hello World",position:"bottom",duration:3e3,background:"rgba(7,17,27,0.8)"}},mounted:function(){}},et,!1,function(t){a("UFhp")},null,null).exports,nt={showMessage:!1,showMessageNew:null,time:null,install:function(t,e){"undefined"!=typeof window&&window.Vue&&(t=window.Vue),t.component("Message",at),t.prototype.$msg=function(a,n){var s=at.data();for(var i in e)s[i]=e[i];if(!a instanceof Object)if(a)s.text=a;else for(var o in a)s[o]=a[o];else if("string"==typeof a)s.text=a;else for(var c in a)s[c]=a[c];if((nt.showMessage||nt.showMessageNew)&&(clearTimeout(nt.time),nt.showMessage=!1,document.body.removeChild(nt.showMessageNew.$mount().$el),nt.showMessageNew=null),!nt.showMessageNew){var r=t.extend({template:'<transition name=fade-up><div class="vue-Message '+s.position+'" style="background:'+s.background+'" v-show="isShow">'+s.text+"<Message></Message></div></transition>",data:function(){return{isShow:nt.showMessage}}});nt.showMessageNew=new r;var l=nt.showMessageNew.$mount().$el;document.body.appendChild(l),nt.showMessageNew.isShow=nt.showMessage=!0}nt.time=setTimeout(function(){nt.showMessageNew.isShow=nt.showMessage=!1,"function"==typeof n&&n()},s.duration)}}},st=nt,it="/",ot=a("mtWM");function ct(t,e,a,n,i){s.$emit("loading","正在處理中, 請稍候......",!0),ot({method:t,headers:{"X-Requested-With":"XMLHttpRequest"},url:e,data:"POST"===t||"PUT"===t?a:null,params:"GET"===t||"DELETE"===t?a:null,baseURL:it,withCredentials:!1}).then(function(t){s.$emit("loading","",!1),n&&n(t.data)}).catch(function(t){if(s.$emit("loading","",!1),t)if(console.log("err---\x3e"+t),console.log("failure---\x3e"+i),console.log("err.response.statusText---\x3e"+t.response.statusText),console.log("err.response.data---\x3e"+t.response.data),i)console.log("1---\x3e"),i(t);else if("Unauthorized"==t.response.statusText||-1!=t.response.data.indexOf("code: 906,"))console.log("2---\x3e"),s.$emit("alerts","會話超時，請重新登錄！"),window.location.href="/#/login";else{console.log("3---\x3e");var e=t.response.statusText+" : "+t.response.data;s.$emit("alerts",e)}})}var rt={get:function(t,e,a,n){return ct("GET",t,e,a,n)},post:function(t,e,a,n){return ct("POST",t,e,a,n)},put:function(t,e,a,n){return ct("PUT",t,e,a,n)},delete:function(t,e,a,n){return ct("DELETE",t,e,a,n)},fileUpload:function(t,e,a,n){return function(t,e,a,n){var i=new FormData;i.append("file",t,t.name),i.append("chunk","0"),s.$emit("loading","正在處理中, 請稍候......",!0),ot.post(e,i,{headers:{"Content-Type":"multipart/form-data"}}).then(function(t){s.$emit("loading","",!1),a&&a(t.data)}).catch(function(t){s.$emit("loading","",!1),console.log("err---\x3e"+t);var e=t.response.statusText+" : "+t.response.data;s.$emit("alerts",e),n&&n(t)})}(t,e,a,n)}},lt={goodTime:function(t){var e=(new Date).getTime()-new Date(t).getTime(),a=e/31104e6,n=e/2592e6,s=e/6048e5,i=e/864e5,o=e/36e5,c=e/6e4;return a>=1?"发表于 "+~~a+" 年前":n>=1?"发表于 "+~~n+" 个月前":s>=1?"发表于 "+~~s+" 周前":i>=1?"发表于 "+~~i+" 天前":o>=1?"发表于 "+~~o+" 个小时前":c>=1?"发表于 "+~~c+" 分钟前":"刚刚"}};a("8e4C"),a("mtWM");n.default.use(Y.a),n.default.use(tt.a),n.default.use(st,{position:"top",duration:999999999}),n.default.prototype.$api=rt,n.default.prototype.$utils=lt,n.default.config.productionTip=!1,new n.default({el:"#app",router:J,components:{App:l},template:"<App/>"})},P6mD:function(t,e){},Pbsv:function(t,e,a){t.exports=a.p+"static/img/stock04.519da72.png"},UFhp:function(t,e){},pAPe:function(t,e){},qXB5:function(t,e){},qb6w:function(t,e){},sT6q:function(t,e){},vhDn:function(t,e,a){t.exports=a.p+"static/img/stock02.64af7c8.png"},wY6c:function(t,e){},yZDo:function(t,e){},zj2Q:function(t,e){},zrBF:function(t,e){}},["NHnr"]);