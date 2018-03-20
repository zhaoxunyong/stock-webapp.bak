<template>
  <div>
    <div class="float-left">
      <div><h2>{{ stockData.company }}({{stockData.no}})</h2></div>
      <div>{{stockData.typeName}} - {{stockData.electronics}}</div>
    </div>
    <!--<div class="float-left">-->
      <!--<b-btn size="sm" v-b-modal.modalPrevent variant="primary">+</b-btn><br/>-->
      <!--<b-btn size="sm" variant="primary" @click="toBack">-</b-btn>-->
    <!--</div>-->
    <div class="float-left pl-3">
      <b-tabs>
        <b-tab title="法人動態" active>
          <textarea class="form-control" id="formControlTextarea1" rows="3">{{companyStatus}}</textarea>
        </b-tab>
        <b-tab title="資卷變化" >
          <br>I'm the second tab content
        </b-tab>
        <b-tab title="大戶籌碼">
          <br>Disabled tab!
        </b-tab>
        <b-tab title="主力進出">
          <br>Disabled tab!
        </b-tab>
        <b-tab title="周報股利">
          <br>Disabled tab!
        </b-tab>
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
      companyStatus: ''
    }
  },
  created () {
    this.stockId = this.$route.params.stockId
    this.getData(this.stockId)
  },
  mounted () {
  },
  methods: {
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
