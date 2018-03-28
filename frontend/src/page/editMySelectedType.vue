<template>
  <main-layout>
    <div>
      <!--<Alert></Alert>-->
      <span v-for="item in items">
        <b-button :variant="isSelected(item.type)" :id="item.type" @click="changedValue(item.type)">
            {{ item.name }}
        <span @click.prevent="removeStockMySelected(item.type, item.name)" aria-hidden="true">×</span>
        </b-button>
      </span>

      <div class="float-right w-50">
        <b-btn v-b-modal.modalPrevent variant="info">添加</b-btn>
        <b-btn v-if="currSelectedType != ''" variant="info" v-b-modal.modalPrevent2>修改名稱</b-btn>
        <b-btn v-if="currSelectedType != ''" variant="info" @click="save2StockMyData">保存</b-btn>
        <!-- Modal Component -->
        <b-modal id="modalPrevent"
                 ref="modal"
                 title="请输入自選股名称"
                 @ok="handleOk"
                 @shown="clearName">
          <form @submit.stop.prevent="handleSubmit">
            <b-form-input type="text"
                          placeholder="输入自選股名称"
                          v-model="name" ref="focusThis"></b-form-input>
          </form>
        </b-modal>

        <!-- Modal Component -->
        <b-modal id="modalPrevent2"
                 ref="modal2"
                 title="请输入需要修改的自選股名称"
                 @ok="handleOk2"
                 @shown="clearName2">
          <form @submit.stop.prevent="handleSubmit2">
            <b-form-input type="text"
                          placeholder="请输入需要修改的自選股名称"
                          v-model="name" ref="focusThis2"></b-form-input>
          </form>
        </b-modal>
      </div>
      <!-- List with handle -->
      <div id="listWithHandle">
        <div class="list-group-item float-left wd-fixed py-1 my-1" v-for="(item, index) in list">
          <span class="badge">{{index+1}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete
            :ref="'autocomplete'+index"
            :source="getUrl"
            input-class="form-control"
            results-property="data"
            :results-display="formattedDisplay"
            @selected="selectedProcess"
            :initialValue="item.stockId" :initialDisplay="item.no +' '+item.company">
          </autocomplete>
        </div>

        <div v-if="currSelectedType != ''" class="list-group-item float-left wd-fixed py-1 my-1" v-for="i in initNumber-list.length">
          <span class="badge">{{i+list.length}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete
            :ref="'autocomplete'+i"
            :source="getUrl"
            input-class="form-control empty-form-control"
            results-property="data"
            :results-display="formattedDisplay"
            @selected="selectedProcess">
          </autocomplete>
        </div>

        <div class="clearfix"></div>
      </div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
import Sortable from 'sortablejs'

// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'
import Autocomplete from 'stock-auto-complete'
// import Autocomplete from '../components/Autocomplete'
export default {
  components: {
    MainLayout, Autocomplete//, Alert
  },
  data () {
    return {
      name: '',
      currSelectedType: '',
      initNumber: 50,
      options: [{value: '', text: 'Please select...'}],
      list: [],
      items: []
    }
  },
  created () {
    this.getData()
  },
  mounted () {
    Sortable.create(listWithHandle, {
      handle: '.oi-move',
      animation: 150
    })
  },
  methods: {
    clearName () {
      this.name = ''
      this.$refs.focusThis.focus()
    },
    handleOk (evt) {
      // Prevent modal from closing
      evt.preventDefault()
      if (!this.name) {
        alert('请输入自選股名称!')
      } else {
        this.handleSubmit()
      }
    },
    handleSubmit () {
      // this.names.push(this.name)
      this.saveData(this.name)
      this.clearName()
      this.$refs.modal.hide()
    },
    // 保存自選股名稱
    saveData (name) {
      let url = '/api/stock/saveStockMySelectedType?name='+name
      this.$api.post(url, null, rs => {
        this.getData()
        // Bus.$emit('reGetStockMySelectedTypes')
      })
    },

    // reame stockmydata name
    clearName2 () {
      this.name = ''
      this.$refs.focusThis2.focus()
    },
    handleOk2 (evt) {
      // Prevent modal from closing
      evt.preventDefault()
      if (!this.name) {
        alert('请输入需要修改的自選股名称!')
      } else {
        this.handleSubmit2()
      }
    },
    handleSubmit2 () {
      // this.names.push(this.name)
      this.renameStockMydataName(this.name)
      this.clearName2()
      this.$refs.modal2.hide()
    },
    // 保存自選股名稱
    renameStockMydataName (name) {
      if(this.currSelectedType == '') {
        alert("請先選擇對應的自選股名稱!")
      } else {
        let url = '/api/stock/renameStockMydataName?selectedType='+this.currSelectedType+'&name='+name
        // alert(url)
        this.$api.post(url, null, rs => {
          this.getData()
          Bus.$emit('success', "修改名稱成功!")
        })
      }
    },

    // 保存選擇的股票到自選股中
    save2StockMyData () {
      if(this.currSelectedType == '') {
        alert("請先選擇對應的自選股名稱!")
      } else {
        let stockIds = []
        $(".form-control input[type='hidden']").each(function(index, data){
          let inputValue = $(data).val()
          if(inputValue != "") {
            stockIds.push(inputValue)
          }
        })
        if(stockIds.length > 0) {
          let url = '/api/stock/changeStockMySelected'
          let params = {
            "selectedType": this.currSelectedType,
            "stockIds": stockIds
          }
          this.$api.post(url, params, rs => {
            Bus.$emit('success', "保存成功!")
          })
        }
      }
    },
    isSelected(type) {
       // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if(this.currSelectedType == type) {
        return 'warning'
      }
      return 'success'
    },
    getUrl (input) {
      return '/api/stock/search4StockMyData?query='+input
    },
    selectedProcess (result, refs) {
        // console.log("===>"+$(this['$el']).html())
      // for(var x in this) {
      //   console.log(x+"--->"+this[x])
      // }
      $(".form-control input[type='hidden']").each(function(index, data){
        let inputValue = $(data).val()
        // alert("value->"+result.value+"/inputValue->"+inputValue)
        if(inputValue != "" && inputValue == result.value) {
          refs.clear()
          // alert(result.display+"已經存在!")
          Bus.$emit('alerts', result.display+"已經存在!")
        }
      })
    },
    formattedDisplay (result) {
      return result.no + ' ' + result.company
    },
    // 将某个股票从自选股中移除
    removeStockMySelected(selectedType, selectedName) {
      let stockId = this.$route.params.stockId
      let $this = this
      this.$confirm("是否確定從"+selectedName+"中移除?").then(
        function(){
         // alert(stockId+"--->"+selectedType)
          let url = '/api/stock/removeStockMySelected?selectedType='+selectedType
          $this.$api.post(url, null, rs => {
            $this.getData()
            $this.currSelectedType = ''
            $this.list = []
          })
        }
      ).catch(function(e){
          // alert("Exception--->"+e)
          console.log("Exception--->"+e)
      })
    },
    changedValue (value) {
      this.currSelectedType = value
      // 清空手动输入的内容
      $(".empty-form-control input").val("")
      this.getMyStockMySelected(value)
    },
    getMyStockMySelected (type) {
      this.list = []
      if(type != '') {
        this.$api.get('/api/stock/getStockMyDatasByType/'+type, null, rs => {
          this.list = rs
        })
      }
    },
    getData () {
      // this.options = []
      let url = '/api/stock/getStockMySelectedTypes'
      this.$api.get(url, null, rs => {
        this.items = rs
        /*for (var i = 0; i < rs.length; i++) {
          this.options.push({
            value: rs[i].type, text: rs[i].name
        }*/
      })
    }
  }
}
</script>

<style scoped>
div {
  padding: 1px;
}

div button {
  margin: 5px;
}
.autocomplete {
  float: right;
  width: 140px;
  padding: 1px;
}

.wd-fixed {
  width: 200px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
