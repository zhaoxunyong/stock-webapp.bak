<template>
  <div>
    <div class="float-left mt-4">
      <div><h2>{{ company }}({{no}})</h2></div>
      <!-- <div v-if="typeName !='' || electronics != ''">{{typeName}} - {{electronics}}</div> -->
    </div>
    <div class="float-left pl-2 mt-4">
      <div><a href="#" v-b-modal.modalPrevent><span class="oi oi-plus"></span></a></div>
      <div><a href="#" @click.prevent="removeOneStockMySelected"><span class="oi oi-minus"></span></a></div>
    </div>
    <!--<div class="float-left">-->
      <!--<b-btn size="sm" v-b-modal.modalPrevent variant="primary">+</b-btn><br/>-->
      <!--<b-btn size="sm" variant="primary" @click="toBack">-</b-btn>-->
    <!--</div>-->

    <div class="float-left pl-2" style="width: 80%">
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
          <input class="float-left form-control my-3" style="width: 80%" id="formControlTextarea1" v-model="companyStatus" />
          <b-button class="float-left ml-3 my-3" variant="success" @click="saveCompanyStatus">保存</b-button>
          <!--<textarea class="form-control" id="formControlTextarea1" rows="3" v-model="stockData.companyStatus"></textarea>-->
        </div>
      </b-tabs>
    </div>
    <!-- <div class="float-left border" style="height: 125px;padding-top: 40px;padding-left: 10px">
      <b-button variant="success" @click="saveCompanyStatus">保存</b-button>
    </div> -->
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      stockId: '',
      // stockData: null,
      company: '',
      no: '',
      typeName: '',
      electronics: '',
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
              // location.reload();
              // $this.$router.go(this.$router.currentRoute)
              // 自動選擇自選股
              Bus.$emit('autoSelectedMyStockSelectedType', $this.currSelectedType, $this.currSelectedName)
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
      if(stockId != undefined && stockId != '' && stockId != 0) {
        this.$api.get('/api/stock/getStockData/'+stockId, null, stockData => {
          if(stockData != '' && stockData != undefined) {
            // this.stockData = stockData
            this.company = stockData.company
            this.no = stockData.no
            this.typeName = stockData.typeName
            this.electronics = stockData.electronics
            this.companyStatus = stockData.companyStatus
          }
        })
      }
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

<style scoped>
.mt-4 {
  margin-top: 38px !important;
}
</style>
