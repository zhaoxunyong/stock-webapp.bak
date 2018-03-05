<template>
  <main-layout>
    <template>
      <div>
        <Alert></Alert>
        <b-container class="bv-example-row" fluid>
        <b-row>
            <b-col id="row_title">&nbsp;&nbsp;請選擇需要導入的每天買入賣出股文件:</b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-file id="file1" v-model="file" ref="fileinput" placeholder="Please choose a file." @change="update"></b-form-file>
              <!-- <div class="mt-3">Selected file: {{file && file.name}}</div> -->
            </b-col>
          </b-row>
      </b-container>
      <div id="row_title">每天買入賣出股列表：</div>
      <b-table striped hover :items="items" style="width: 90%"></b-table>
      </div>
    </template>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import Alert from '../components/alert.vue'
import Bus from '../eventBus'
export default {
  components: { 
    MainLayout, Alert
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
      Bus.$emit('success', "正在導入數據, 請稍候......")
      this.$api.fileUpload(file, url, rs => {
        Bus.$emit('success', "導入成功!")
        this.getData()
      })
      this.$refs.fileinput.reset()
    },
    getData () {
      let url = '/api/stock/getStockDailyTransactions'
      this.$api.get(url, null, rs => {
        // this.items = rs
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
      });
    }
  },
  watch: {
    '$route' (to, from) {
      
    }
  }
}
</script>

<style scoped>
div {
  font-size: 12px;
}
.bv-example-row {
  padding: 10px 5px;
}

#row_title {
  padding: 10px 5px;
  margin: 10px 5px;
  font-weight: bold;
}
</style>