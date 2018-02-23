<template>  
  <div>
    <b-btn variant="primary"><-</b-btn>
    <b-btn v-b-modal.modalPrevent variant="primary">+</b-btn>
    <b-btn variant="primary">-></b-btn>
    <!-- Modal Component -->
    <b-modal id="modalPrevent"
             ref="modal"
             title="選擇自選股"
             @ok="handleOk"
             @shown="clearName">
      <form @submit.stop.prevent="handleSubmit">
        <!-- <b-form-input type="text"
                      placeholder="Enter your name"
                      v-model="name"></b-form-input> -->
        <b-form-select v-model="selected" :options="options" class="mb-3" />
      </form>
    </b-modal>

    <span v-for="i in list">
      <router-link :to="'/content/' + i.stockId+'/1'">
        {{ i.company }}
      </router-link><br />
    </span>
  </div>
</template>

<script>
import Bus from '../eventBus'
export default {
  data () {
    return {
      list: [],
      selected: null,
      options: [
        { value: null, text: 'Please select an option' },
        { value: 'a', text: 'This is First option' },
        { value: 'b', text: 'Selected Option' },
        { value: {'C': '3PO'}, text: 'This is an option with object value' },
        { value: 'd', text: 'This one is disabled', disabled: true }
      ]
    }
  },
  created () {
    this.getData()
  },
  methods: {
    clearName () {
      this.selected = ''
    },
    handleOk (evt) {
      // Prevent modal from closing
      evt.preventDefault()
      if (!this.selected) {
        alert('Please enter your name')
      } else {
        this.handleSubmit()
      }
    },
    handleSubmit () {
      //this.names.push(this.name)
      alert(this.selected)
      this.clearName()
      this.$refs.modal.hide()
    },
    getData () {
      this.$api.get('/api/stock/getStockMyDatas', null, r => {
        this.list = r
        let stockId = this.$route.params.stockId == undefined ? r[0].stockId : this.$route.params.stockId
        Bus.$emit('filterResultData', stockId);
      })
    }
  }
}
</script>