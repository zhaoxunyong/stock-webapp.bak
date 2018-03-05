<template>
  <main-layout>
    <div>
      <span v-for="item in items">
        <b-button :variant="isSelected(item.type)" :id="item.type" @click="changedValue(item.type)">
            {{ item.name }}
            <!-- <span @click.prevent="removeStockMySelected(item.type, item.name)" aria-hidden="true" v-if="isSelected(item.type) == 'warning'">×</span> -->
        </b-button>
      </span>

      <!-- <form @submit.stop.prevent="handleSubmit">
        <div class="p-3 float-left w-50">
          編輯自選股: <b-form-select v-model="selected" @change="changedValue" :options="options" class="w-50" />
        </div>
        <div class="p-3 float-left w-25">
          <b-button variant="success">保存</b-button>
        </div>
        <div class="clearfix"></div>
        
      </form> -->
      <!-- List with handle -->
      <div id="listWithHandle">
        <div class="list-group-item float-left wd-fixed py-1 my-1" v-for="(item, index) in list">
          <span class="badge">{{index+1}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete
            ref="autocomplete"
            :source="getUrl"
            input-class="form-control"
            results-property="data"
            :results-display="formattedDisplay"
            @selected="selectedProcess"
            :initialValue="item.id" :initialDisplay="item.no +' '+item.company">
          </autocomplete>
        </div>

        <div v-if="currSelectedType != ''" class="list-group-item float-left wd-fixed py-1 my-1" v-for="i in initNumber-list.length">
          <span class="badge">{{i+list.length}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete
            ref="autocomplete"
            :source="getUrl"
            input-class="form-control"
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
import Bus from '../eventBus'
import Sortable from 'sortablejs'

// https://github.com/charliekassel/vuejs-autocomplete
import Autocomplete from 'vuejs-auto-complete'
export default {
  components: { 
    MainLayout, Autocomplete 
  },
  data () {
    return {
      // selected: null,
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
    isSelected(type) {
       // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if(this.currSelectedType == type) {
        return 'warning'
      }
      return 'success'
    },
    getUrl (input) {
      return '/api/stock/search4StockData?query='+input
    },
    selectedProcess (group) {
      // this.group = group
      // access the autocomplete component methods from the parent
      // this.$refs.autocomplete.clear()
    },
    formattedDisplay (result) {
      return result.no + ' ' + result.company
    },

    changedValue (value) {
      this.currSelectedType = value
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
      this.selected = ''
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