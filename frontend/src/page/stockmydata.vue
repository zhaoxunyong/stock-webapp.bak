<template>  
  <div>
    <div id="select_div">
      <b-btn variant="primary" @click="toFront"><-</b-btn>
      <b-btn v-b-modal.modalPrevent variant="primary">+</b-btn>
      <b-btn variant="primary" @click="toBack">-></b-btn>
      <!-- Modal Component -->
      <b-modal id="modalPrevent"
               ref="modal"
               title="請選擇自選股"
               @ok="handleOk"
               @shown="clearName">
        <form @submit.stop.prevent="handleSubmit">
          <!-- <b-form-input type="text"
                        placeholder="Enter your name"
                        v-model="name"></b-form-input> -->
          <b-form-select v-model="selected" :options="options" class="mb-3" />
        </form>
      </b-modal>
    </div>

    <div class="selected_name">
   當前自選股 : <br />{{ myStockSelectedName }}
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
export default {
  data () {
    return {
      list: [],
      firstStockId: '',
      myStockSelectedName: '所有',
      selected: null,
      options: []
    }
  },
  created () {
    this.getData()

    // 获取添加到自选股时的下拉选项
    this.getStockMySelectedTypes()

    // 从stockmyselectedtype.vue中过来：当点击所有按扭时
    Bus.$on('getAllMyStockData', () => {
      this.myStockSelectedName = '所有'
      this.getData()
    });

    // 从stockmyselectedtype.vue中过来：当将某个股票从某个自选股中移除时
    Bus.$on('reGetStockMySelectedTypes', () => {
      this.getStockMySelectedTypes()
    });

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
            this.$router.push('/content/' + this.firstStockId+'/1')
            // Bus.$emit('setStock', this.firstStockId)
            //自动将自选股类型选中
            // Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
          } else {
            this.$router.push('/content/0/1')
            Bus.$emit('emptyNews')
            // Bus.$emit('deliverySelectedTypes', [])
          }
        })
      }
    });
  },
  methods: {
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
      this.$router.push('/content/' + stockId+'/1')
      //自动将自选股类型选中
      // Bus.$emit('deliverySelectedTypes', selectedTypes)

    },

    // 上一个股票
    toFront () {
      let _router = this.$router
      $(".selected").each(function(){
        var aObj = $(this).closest("span").prev().find("a");
        var href = aObj.attr('href')
        if(href != undefined) {
          _router.push(href)
        }
      });
    },

    // 下一个股票
    toBack () {
      let _router = this.$router
      $(".selected").each(function(){
        var aObj = $(this).closest("span").next().find("a");
        var href = aObj.attr('href')
        if(href != undefined) {
          _router.push(href)
        }
      });
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
        let stockId = this.$route.params.stockId == undefined || this.$route.params.stockId == 0 ? this.firstStockId : this.$route.params.stockId
        //改变路由的地址
        this.$router.push('/content/' + stockId+'/1')
      })
    },
    getStockMySelectedTypes () {
      this.options = []
      let url = '/api/stock/getStockMySelectedTypes'
      this.$api.get(url, null, rs => {
        for (var i = 0; i < rs.length; i++) {
          this.options.push({
            value: rs[i].type, text: rs[i].name
          })
        }
      })
    },
    changeStockMySelected (stockId, selectedType) {
      let url = '/api/stock/changeStockMySelected?stockId='+stockId+"&selectedType="+selectedType
      this.$api.post(url, null, rs => {
        //改变路由的地址
        this.$router.push('/content/' + stockId+'/1')
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