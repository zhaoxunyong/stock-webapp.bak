<template>  
  <div>
    <div id="select_div">
      <b-btn variant="primary"><-</b-btn>
      <b-btn v-b-modal.modalPrevent variant="primary">+</b-btn>
      <b-btn variant="primary">-></b-btn>
      <!-- Modal Component -->
      <b-modal id="modalPrevent"
               ref="modal"
               title="xxx"
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

    <span v-for="i in list">
      <a :href="'/content/' + i.stockId+'/1'" @click.prevent="go(i.stockId,i.selectedTypes)" :class="isSelected(i.stockId)">
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
      selected: null,
      options: []
    }
  },
  created () {
    this.getData()
    this.getStockMySelectedTypes()
    Bus.$on('getAllMyStockData', () => {
      this.getData()
    });Bus.$on('reGetStockMySelectedTypes', () => {
      this.getStockMySelectedTypes()
    });
    Bus.$on('getMyStockSelected', (type) => {
      if(type != undefined) {
        this.$api.get('/api/stock/getStockMyDatasByType/'+type, null, r => {
          this.list = r
          if(r != "") {
            this.firstStockId = r[0].stockId
            let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
            this.$router.push('/content/' + this.firstStockId+'/1')
            // this.isSelected(stockId)
            // Bus.$emit('setFirstStock', stockId)
          }
        })
      }
    });
  },
  methods: {
    isSelected(_stockId) {
      let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
      if(_stockId == stockId) {
        return 'selected'
      }
      return ''
    },
    go (stockId, selectedTypes) {
      // '/content/' + i.stockId+'/1'
      this.$router.push('/content/' + stockId+'/1')
      Bus.$emit('deliverySelectedTypes', selectedTypes)

    },
    clearName () {
      this.selected = ''
    },
    handleOk (evt) {
      // Prevent modal from closing
      evt.preventDefault()
      if (!this.selected) {
        alert('Õˆßx“ñ×Ôßx¹É!')
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
    getData () {
      this.$api.get('/api/stock/getStockMyDatas', null, r => {
        this.list = r
        this.firstStockId = r[0].stockId
        let stockId = this.$route.params.stockId == undefined ? this.firstStockId : this.$route.params.stockId
        Bus.$emit('setFirstStock', stockId)
        Bus.$emit('deliverySelectedTypes', r[0].selectedTypes)
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
        Bus.$emit('deliveryOneSelectedType', selectedType)
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
</style>