<template>
  <div>
    <!-- <div class="m-2">
      <autocomplete
        ref="autocomplete"
        :source="getUrl"
        input-class="form-control empty-form-control"
        results-property="data"
        :results-display="formattedDisplay"
        @selected="selectedProcess">
      </autocomplete>
    </div> -->

    <div class="pt-2 pb-2">
      <!-- <b-btn size="sm" variant="primary" @click="toFront"><-</b-btn> -->
      <!-- <b-btn size="sm" v-b-modal.modalPrevent variant="primary">+</b-btn> -->
      <!-- <b-btn size="sm" variant="primary" @click="toBack">-></b-btn> -->
      <img src="static/image/left.png" style="width: 35px; height: 35px; cursor: pointer;"  @click="toFront" />
      <!-- <b-btn size="sm" v-b-modal.modalPrevent variant="primary">+</b-btn> -->
      <span style="padding: 0px 0px"></span>
      <img src="static/image/right.png" style="width: 35px; height: 35px; cursor: pointer;" @click="toBack" />
      <!-- Modal Component -->
      <b-modal id="modalPrevent"
               ref="modal"
               title="請選擇自選股"
               hide-footer
               @ok="handleOk"
               @shown="openModal">
        <!-- <form @submit.stop.prevent="handleSubmit">
          <b-form-select v-model="selected" :options="options" class="mb-3" />
        </form> -->
        <span v-for="item in items">
          <b-button class="m-1" variant="success" @click="changeType(item.type, item.name)">{{ item.name }}</b-button>
    </span>
      </b-modal>
    </div>

    <div class="selected_name text">
    <h5>{{ myStockSelectedName }}</h5>

    <span v-for="i in list" class="d-lg-block">
      <a :href="'/content/' + i.stockId+'/1'" @click.prevent="go(i.stockId)" :class="isSelected(i.stockId)">
        {{ i.company }}
      </a><br />
    </span>
    </div>
  </div>
</template>

<script>
import Bus from '../eventBus'

// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'
import Autocomplete from 'stock-auto-complete'
// import Autocomplete from '../components/Autocomplete'
export default {
  components: {
    Autocomplete
  },
  data () {
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
      // this.clearName()
    },
    // clearName () {
    //   this.selected = ''
    // },
    handleOk (evt) {
      // // Prevent modal from closing
      // evt.preventDefault()
      // if (!this.selected) {
      //   alert('請選擇自選股!')
      // } else {
      //   this.handleSubmit()
      // }
    },
    // handleSubmit (evt) {
    changeType (selectedType, selectedName) {
      //this.names.push(this.name)
      let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
      // alert(stockId +'/' + this.selected)
      this.changeStockMySelected(stockId, selectedType, selectedName)
      // this.clearName()
    },
    // 第一次加载数据
    getData () {
      this.$api.get('/api/stock/getStockMyDatasByStore', null, r => {
        this.list = r
        this.firstStockId = r[0].stockId
        // let stockId = this.$route.params.stockId == undefined || this.$route.params.stockId == 0 ? this.firstStockId : this.$route.params.stockId
        //改变路由的地址
        this.push('/content/' + this.firstStockId+'/1')
      })
    },
    getStockMySelectedTypes () {
      // this.options = []
      this.items = []
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
          // this.$api.get(url, null, rs => {
          //   this.items = rs
          // })
          this.$api.get(url, null, rs => {
            for (var i = 0; i < rs.length; i++) {
              let isDisabled = mySelectedType.indexOf(rs[i].type) != -1
              if(!isDisabled) {
                // this.options.push({
                //   value: rs[i].type, text: rs[i].name, disabled:isDisabled
                // })
                this.items.push(rs[i])
              }
            }
          })
        })
      }
    },
    changeStockMySelected (stockId, selectedType, myStockSelectedName) {
      let url = '/api/stock/changeStockMySelected'
      let stockIds = []
      stockIds.push(stockId)
      let params = {
        "selectedType": selectedType,
        "stockIds": stockIds
      }
      this.$api.post(url, params, rs => {
        //改变路由的地址
        Bus.$emit('success', "保存成功!")
        // 自動選擇右邊的自選股
        Bus.$emit('autoSelectedMyStockSelectedType', selectedType, myStockSelectedName)
        // this.push('/content/' + stockId+'/1')
        // location.reload()
        this.$refs.modal.hide()
      })
    }
  }
}
</script>

<style scoped>
.selected {
  color: red;
}

.text {
    height: 73vh;
    margin: 0 auto;
    overflow: auto;
}
</style>
