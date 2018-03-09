<template>  
  <div>
    <div class="p-2">
      <autocomplete
        ref="autocomplete"
        :source="getUrl"
        input-class="form-control empty-form-control"
        results-property="data"
        :results-display="formattedDisplay"
        @selected="selectedProcess">
      </autocomplete>
    </div>

    <div id="select_div">
      <b-btn variant="primary" @click="toFront"><-</b-btn>
      <b-btn v-b-modal.modalPrevent variant="primary">+</b-btn>
      <b-btn variant="primary" @click="toBack">-></b-btn>
      <!-- Modal Component -->
      <b-modal id="modalPrevent"
               ref="modal"
               title="請選擇自選股"
               @ok="handleOk"
               @shown="openModal">
        <form @submit.stop.prevent="handleSubmit">
          <!-- <b-form-input type="text"
                        placeholder="Enter your name"
                        v-model="name"></b-form-input> -->
          <b-form-select v-model="selected" :options="options" class="mb-3" />
        </form>
      </b-modal>
    </div>

    <div class="selected_name">
    <h5>{{ myStockSelectedName }}</h5>
    </div>

    <span v-for="i in list">
      <a :href="'/content/' + i.stockId+'/1'" @click.prevent="go(i.stockId)" :class="isSelected(i.stockId)">
        {{ i.company }}
      </a><br />
    </span>
  </div>
</template>

<script>
import Bus from '../eventBus'

// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'
import Autocomplete from '../components/Autocomplete'
export default {
  components: {
    Autocomplete
  },
  data () {
    return {
      list: [],
      firstStockId: '',
      myStockSelectedName: '庫存股',
      selected: null,
      options: [],
      // 所有自选股标签
      selectedTypes: [],
      // 某只股票的自选股标签
      mySelectedTypes: []
    }
  },
  created () {
    // 获取添加到自选股时的下拉选项
    this.getStockMySelectedTypes()
    this.getData()

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
    Bus.$on('getMyStockSelected', (type, name) => {
      if(type != undefined) {
        this.myStockSelectedName = name
        this.$api.get('/api/stock/getStockMyDatasByType/'+type, null, r => {
          this.list = r
          if(r != undefined && r.length > 0) {
            this.firstStockId = r[0].stockId
            // let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
            //改变路由的地址
            this.push('/content/' + this.firstStockId+'/1')
            // Bus.$emit('setStock', this.firstStockId)
            //自动将自选股类型选中
            // Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
          } else {
            this.push('/content/0/1')
            Bus.$emit('emptyNews')
            // Bus.$emit('deliverySelectedTypes', [])
          }
        })
      }
    });

    // 从stockmyselectedtype.vue中过来：当点击store股标签时
    Bus.$on('getAllStockMyStore', () => {
      this.myStockSelectedName = '庫存股'
      // this.myStockSelectedName = name
      this.$api.get('/api/stock/getStockMyDatasByStore', null, r => {
        this.list = r
        if(r != undefined && r.length > 0) {
          this.firstStockId = r[0].stockId
          // let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
          //改变路由的地址
          this.push('/content/' + this.firstStockId+'/1')
          // Bus.$emit('setStock', this.firstStockId)
          //自动将自选股类型选中
          // Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
        } else {
          this.push('/content/0/1')
          Bus.$emit('emptyNews')
          // Bus.$emit('deliverySelectedTypes', [])
        }
      })
    });
  },
  methods: {
    getUrl (input) {
      return '/api/stock/search4StockData?query='+input
    },
    selectedProcess (result, refs) {
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
    formattedDisplay (result) {
      return result.no + ' ' + result.company
    },
    push(url) {
      this.$router.push(url)
      Bus.$emit('initCurrentPage', 1)
    },
    // 将当前股票高亮显示
    isSelected(_stockId) {
      let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
      if(_stockId == stockId) {
        return 'selected'
      }
      return ''
    },

    // 点击某个股票
    go (stockId) {
      // '/content/' + i.stockId+'/1'
      this.push('/content/' + stockId+'/1')
      //自动将自选股类型选中
      // Bus.$emit('deliverySelectedTypes', selectedTypes)

    },

    // 上一个股票
    toFront () {
      let _router = this.$router
      let obj = $(".selected").get(0)
      let aObj = $(obj).closest("span").prev().find("a");
      let href = aObj.attr('href')
      if(href != undefined) {
        this.push(href)
      }
    },

    // 下一个股票
    toBack () {
      let _router = this.$router
      let obj = $(".selected").get(0)
      let aObj = $(obj).closest("span").next().find("a");
      let href = aObj.attr('href')
      if(href != undefined) {
        this.push(href)
      }
    },
    openModal () {
      this.getStockMySelectedTypes()
      this.clearName()
    },
    clearName () {
      this.selected = ''
    },
    handleOk (evt) {
      // Prevent modal from closing
      evt.preventDefault()
      if (!this.selected) {
        alert('請選擇自選股!')
      } else {
        this.handleSubmit()
      }
    },
    handleSubmit () {
      //this.names.push(this.name)
      let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
      // alert(stockId +'/' + this.selected)
      this.changeStockMySelected(stockId, this.selected)
      this.clearName()
      this.$refs.modal.hide()
    },
    // 第一次加载数据
    getData () {
      this.$api.get('/api/stock/getStockMyDatas', null, r => {
        this.list = r
        this.firstStockId = r[0].stockId
        // let stockId = this.$route.params.stockId == undefined || this.$route.params.stockId == 0 ? this.firstStockId : this.$route.params.stockId
        //改变路由的地址
        this.push('/content/' + this.firstStockId+'/1')
      })
    },
    getStockMySelectedTypes () {
      this.options = []

      let stockId = this.$route.params.stockId
      if(stockId != undefined && stockId != "") {
        this.$api.get('/api/stock/getMySelectedTypesByStockId/'+stockId, null, rs => {
          
          let mySelectedType = []
          if(rs != undefined && rs.length > 0) {
            for(var i=0;i<rs.length;i++) {
              let type = rs[i].type
              mySelectedType.push(type)
            }
          }

          let url = '/api/stock/getStockMySelectedTypes'
          this.$api.get(url, null, rs => {
            for (var i = 0; i < rs.length; i++) {
              let isDisabled = mySelectedType.indexOf(rs[i].type) != -1
              if(!isDisabled) {
                this.options.push({
                  value: rs[i].type, text: rs[i].name, disabled:isDisabled
                })
              }
            }
          })
            // if ($.isFunction(fn)){
            //   fn.call(this, mySelectedType)
            // }
        })
      }
    },
    changeStockMySelected (stockId, selectedType) {
      let url = '/api/stock/changeStockMySelected'
      let stockIds = []
      stockIds.push(stockId)
      let params = {
        "selectedType": selectedType,
        "stockIds": stockIds
      }
      this.$api.post(url, params, rs => {
        //改变路由的地址
        // Bus.$emit('triggerAutoSelectedTypes')
        this.push('/content/' + stockId+'/1')
      })
    }
  }
}
</script>

<style scoped>
#select_div {
  padding: 10px;
}

#select_div button {
}

.selected {
  color: red;
}

.selected_name {
  color: blue;
  padding: 5px 2px;
}
</style>