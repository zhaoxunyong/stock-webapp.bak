<template>
  <div>
    <span>
    <!-- <b-button :variant="currSelectedType==''?'warning':'success'" @click="getAllMyStockData">
      所有
    </b-button> -->

    <b-button :variant="currSelectedType==''?'warning':'success'" @click="getAllStockMyStore">
      庫存股
    </b-button>

    </span>
    <span v-for="item in items">
    <b-button :variant="isSelected(item.type)" :id="item.type" @click="getMyStockSelected(item.type, item.name)">
        {{ item.name }}
        <!-- <span @click.prevent="removeStockMySelected(item.type, item.name)" aria-hidden="true" v-if="isSelected(item.type) == 'warning'">×</span> -->
    </b-button>
    </span>
    <b-btn v-b-modal.modalPrevent2 variant="success">+</b-btn>
    <!-- Modal Component -->
    <b-modal id="modalPrevent2"
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
  </div>
</template>
<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      name: '',
      items: [],
      currSelectedType: '',
      // firstStockId: '',
      name: ''
    }
  },
  created () {
    // this.autoSelectedTypes()
    this.getData()
    
    // Bus.$on('triggerAutoSelectedTypes', () => {
    //   this.autoSelectedTypes()
    // });
  },
  methods: {
    // 自动高亮显示某个股票所属的自选股名称
    /*autoSelectedTypes() {
      this.selectedTypes = []
      let stockId = this.$route.params.stockId
      if(stockId != undefined && stockId != "") {
        this.$api.get('/api/stock/getMySelectedTypesByStockId/'+stockId, null, rs => {
          if(rs != undefined && rs.length>0) {
            for(var i=0;i<rs.length;i++) {
              let type = rs[i].type
              this.selectedTypes.push(type)
            }
          }
        })
      }
    },*/
    // 触发stockmydata.vue重新摘取所有的自选股
    /*getAllMyStockData() {
      this.selectedTypes = []
      Bus.$emit('getAllMyStockData')
    },  */   
    // 触发stockmydata.vue重新摘取所有的sotre股
    getAllStockMyStore() {
      this.selectedTypes = []
      this.currSelectedType = '0'
      Bus.$emit('getAllStockMyStore')
    }, 
    // 触发stockmydata.vue重新摘取某个自选股中的所有股票   
    getMyStockSelected(type, name) {
      this.currSelectedType = type
      Bus.$emit('getMyStockSelected', type, name)
    },
    isSelected(type) {
       // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if(this.currSelectedType == type) {
        return 'warning'
      }
      return 'success'
    },
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
    getData () {
      let url = '/api/stock/getStockMySelectedTypes'
      this.$api.get(url, null, rs => {
        this.items = rs
      })
    },
    saveData (name) {
      let url = '/api/stock/saveStockMySelectedType?name='+name
      this.$api.post(url, null, rs => {
        this.getData()
        // Bus.$emit('reGetStockMySelectedTypes')
      })
    }
  },
  // 从stockmydata.vue中的第一次之后的请求
  watch: {
    '$route' (to, from) {
      // this.autoSelectedTypes()
      //this.$router.push('/content/' + this.getStatus(this.$route.path))
    }
  }
}
</script>

<style scoped>
div {
  padding: 10px;
}

div button {
  margin: 5px;
}
</style>