<template>
  <div>
    <div class="float-left">
      <div><h2>{{ stockData.company }}({{stockData.no}})</h2></div>
      <div>{{stockData.typeName}} - {{stockData.electronics}}</div>
    </div>
    <div class="float-left px-3">
      <div><a href="#" v-b-modal.modalPrevent><span class="oi oi-plus"></span></a></div>
      <div><a href="#" @click.prevent="removeOneStockMySelected"><span class="oi oi-minus"></span></a></div>
    </div>
    <!--<div class="float-left">-->
      <!--<b-btn size="sm" v-b-modal.modalPrevent variant="primary">+</b-btn><br/>-->
      <!--<b-btn size="sm" variant="primary" @click="toBack">-</b-btn>-->
    <!--</div>-->

    <div class="float-left pl-3">
      <!--<b-nav tabs>-->
        <!--<b-nav-item active>Active</b-nav-item>-->
        <!--<b-nav-item>Link</b-nav-item>-->
        <!--<b-nav-item>Another Link</b-nav-item>-->
        <!--<b-nav-item disabled>Disabled</b-nav-item>-->
      <!--</b-nav>-->

      <b-tabs>
        <b-tab title="法人動態" active>
          <!--<textarea class="form-control" id="formControlTextarea1" rows="3">{{companyStatus}}</textarea>-->
        </b-tab>
        <b-tab title="資卷變化" >
          <!--<br>I'm the second tab content-->
        </b-tab>
        <b-tab title="大戶籌碼">
          <!--<br>Disabled tab!-->
        </b-tab>
        <b-tab title="主力進出">
          <!--<br>Disabled tab!-->
        </b-tab>
        <b-tab title="周報股利">
          <!--<br>Disabled tab!-->
        </b-tab>
        <div>
          <textarea class="form-control" id="formControlTextarea1" rows="3" v-model="stockData.companyStatus"></textarea>
        </div>
      </b-tabs>
    </div>
    <div class="float-left" style="height: 125px;padding-top: 60px;padding-left: 10px">
      <b-button variant="success" @click="saveCompanyStatus">保存</b-button>
    </div>
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      stockId: '',
      stockData: null,
      currSelectedType: '',
      currSelectedName: '',
      companyStatus: ''
    }
  },
  created () {
    this.stockId = this.$route.params.stockId
    this.getData(this.stockId)

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    Bus.$on('getAllStockMyStore', () => {
      // 主要記錄一下對應的selectedType是哪個，用於移除自選股時使用
      this.currSelectedType = ''
      this.currSelectedName = ''
    });
    // 从stockmyselectedtype.vue中过来：当点击某个自选股标签时
    Bus.$on('getMyStockSelected', (type, name) => {
      // 主要記錄一下對應的selectedType是哪個，用於移除自選股時使用
      this.currSelectedType = type
      this.currSelectedName = name
    });
  },
  mounted () {
  },
  methods: {
    removeOneStockMySelected() {
      this.stockId = this.$route.params.stockId
      //alert(this.stockId+"/"+this.currSelectedType)
      if(this.currSelectedType == '' || this.currSelectedType == undefined) {
        Bus.$emit('success', "該股不在某個自選股中，無需移除!")
      } else {
        let $this = this
        this.$confirm("是否確定從"+$this.currSelectedName+"中移除?").then(
          function(){
            let url = '/api/stock/removeOneStockMySelected?stockId='+$this.stockId+"&selectedType="+$this.currSelectedType
            $this.$api.post(url, null, rs => {
              Bus.$emit('success', "移除成功!")
              location.reload();
              // $this.$router.go(this.$router.currentRoute)
            })
          }
        ).catch(function(e){
          // alert("Exception--->"+e)
          console.log("Exception--->"+e)
        })

      }
    },
    saveCompanyStatus () {
      this.stockId = this.$route.params.stockId
      if(this.stockId == undefined || this.stockId == '') {

      } else {
        let companyStatus = $("#formControlTextarea1").val()
        let url = '/api/stock/saveCompanyStatus?stockId='+this.stockId+"&companyStatus="+companyStatus
        this.$api.post(url, null, rs => {
          Bus.$emit('success', "保存公司現況成功!")
        })
      }
    },
    getData (stockId) {
      this.$api.get('/api/stock/getStockData/'+stockId, null, stockData => {
        this.stockData = stockData
        this.companyStatus = stockData.companyStatus
      })
    }
  },
  watch: {
    '$route' (to, from) {
      this.stockId = this.$route.params.stockId
      this.getData(this.stockId)
    }
  }
}
</script>
