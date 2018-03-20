<template>
  <main-layout>
    <template>
      <div>
        <!--<Alert></Alert>-->
        <b-container fluid>
        <b-row class="p-3">
          <b-col class="pt-3"><b>請選擇需要導入的每天買入賣出股文件:</b></b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-form-file class="p-3" id="file1" v-model="file" ref="fileinput" placeholder="Please choose a file." @change="update"></b-form-file>
            <!-- <div class="mt-3">Selected file: {{file && file.name}}</div> -->
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <form @submit.prevent="selectByDate" class="p-3">
              <!--<input type="text" class="form-control" id="validationDefault03" placeholder="City" required>-->
              <input id="startDate" class="form-control w-25 float-left" placeholder="請選擇開始日期" required type="text" autocomplete="off" @click="datePick" />
              <div class="float-left px-3"> - </div>
              <input id="endDate" class="form-control w-25 float-left" placeholder="請選擇結束日期" type="text" autocomplete="off" @click="datePick" />
              <b-button class="px-3" variant="primary" type="submit">查詢</b-button>
              <!--<button class="btn btn-primary" type="submit">Submit form</button>-->
            </form>
            <p class="p-3 mb-2 bg-info text-white">每天買入賣出股列表：</p>
            <b-table hover :items="items"></b-table>
          </b-col>
        </b-row>
      </b-container>
      </div>
    </template>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'

// import '../components/datepicker/js/bootstrap-datepicker.min'
// import '../components/datepicker/css/bootstrap-datepicker.min.css'
// import '../components/datepicker/locales/bootstrap-datepicker.zh-CN.min'
// import '../components/My97DatePicker/WdatePicker.js'
// import '../components/My97DatePicker/skin/WdatePicker.css'

import Bus from '../eventBus'
export default {
  components: {
    MainLayout//, Alert
  },
  data () {
    return {
      file: null,
      items: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    update (e) {
      let url = "/api/stock/uploadStockDailyTransactions"
      let file = e.target.files[0]
      this.$api.fileUpload(file, url, rs => {
        Bus.$emit('success', "導入成功!")
        this.getData()
      })
      this.$refs.fileinput.reset()
    },
    datePick (e) {
      let obj = e.target
      WdatePicker({el: obj, readOnly:true, dateFmt:'yyyy/MM/dd', lang:'zh-tw'})
    },
    selectByDate () {
      this.getData()
    },
    getData () {
      let startDate = $("#startDate").val()
      let endDate = $("#endDate").val()
      if(startDate != undefined && startDate != '') {
        let url = '/api/stock/getStockDailyTransactions?startDate='+startDate+'&endDate='+endDate
        this.$api.get(url, null, rs => {
          if(rs.length > 0) {
            for(var i=0;i<rs.length;i++) {
              let data = rs[i]
              this.items.push({
                '成交日期': data.txDate,
                '股票名稱': '['+data.no+'] '+data.company,
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
              })
            }
          } else {
            this.items = []
          }
        });
      }
    }
  },
  watch: {
    '$route' (to, from) {

    }
  }
}
</script>

<style scoped>
/*div {
  font-size: 12px;
}*/
</style>
