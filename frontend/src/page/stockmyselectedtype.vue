<template>
  <div>
    <span>
    <b-button variant="success" @click="getAllMyStockData">
        所有
    </b-button>
    </span>
    <span v-for="item in items">
    <b-button :variant="isSelected(item.type)" :id="item.type" @click="getMyStockSelected(item.type)">
        {{ item.name }}
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
      items: [],
      selectedTypes: [],
      name: ''
    }
  },
  created () {
    this.getData()
    Bus.$on('deliverySelectedTypes', (selectedTypes) => {
      this.selectedTypes = selectedTypes
    });
    Bus.$on('deliveryOneSelectedType', (selectedType) => {
      this.selectedTypes.push(selectedType)
    });
  },
  methods: {
    getAllMyStockData() {
      Bus.$emit('getAllMyStockData')
    },    
    getMyStockSelected(type) {
      Bus.$emit('getMyStockSelected', type)
    },
    isSelected(type) {
       // ? 'success':'danger'
      if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
        return 'danger'
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
        Bus.$emit('reGetStockMySelectedTypes');
      })
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