<template>
  <main-layout>
    <div>
      <form @submit.stop.prevent="handleSubmit">
              <!-- <b-form-input type="text"
                            placeholder="Enter your name"
                            v-model="name"></b-form-input> -->
        <div class="p-3 float-left w-50">
          編輯自選股: <b-form-select v-model="selected" @change="changedValue" :options="options" class="w-50" />
        </div>
        <div class="p-3 float-left w-25">
          <b-button variant="success">保存</b-button>
        </div>
        <div class="clearfix"></div>
        
      </form>
      <!-- List with handle -->
      <div id="listWithHandle">
        <div class="list-group-item float-left wd-fixed py-1 my-1" v-for="(item, index) in list">
          <span class="badge">{{index+1}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>

          <autocomplete-vue class="autocomplete-item"
          :list="[{name: 'item1'}, {name: 'item2'}, {name: 'item3'}]" :value="item.no +' '+item.company" />
        </div>

        <div v-if="selected != ''" class="list-group-item float-left wd-fixed py-1 my-1" v-for="i in initNumber-list.length">
          <span class="badge">{{i+list.length}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete-vue class="autocomplete-item" 
          url="/api/stock/search4StockData?value=瑞基" 
          property="no" 
          :required="true" />
        </div>

        <div class="clearfix"></div>
      </div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import Bus from '../eventBus'
import Sortable from 'sortablejs'
// https://github.com/tecbeast42/autocomplete-vue
import AutocompleteVue from 'autocomplete-vue'
import { autocompleteBus } from 'autocomplete-vue'
export default {
  components: { 
    MainLayout, AutocompleteVue 
  },
  data () {
    return {
      selected: null,
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
    });
    autocompleteBus.$on('input', function (value) {
      // do something
      alert("value--->"+value)
    })
  },
  methods: {
    changedValue (value) {
      this.selected = value
      this.getMyStockMySelected(this.selected)
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
      this.selected = ''
      let url = '/api/stock/getStockMySelectedTypes'
      this.$api.get(url, null, rs => {
        this.items = rs
        for (var i = 0; i < rs.length; i++) {
          this.options.push({
            value: rs[i].type, text: rs[i].name
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.wd-fixed {
  width: 200px;
}
.autocomplete-item {
  float: right;
  width: 100px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->