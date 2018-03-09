<template>
  <main-layout>
    <div class="border border-primary m-2">
      <h5 class="text-primary p-1">去重關鍵字：
      <button type="button" class="btn btn-primary" @click="saveExcludeItem($event)">保存</button>
      </h5>
      <div class="exclude-item w-5 float-left p-1" v-for="item in excludesItems">
          <input type="text" class="form-control" :value="item.key" />
      </div>
      <div id="add-exclude-item" class="w-5 float-left p-1">
          <button type="button" class="btn btn-primary" @click="addExcludeItem($event)">添加</button>
      </div>
      <div class="clearfix"></div>
    </div>


    <div class="border border-success m-2 mt-3">
      <h5 class="text-success p-1">焦點關鍵字：
      <button type="button" class="btn btn-success" @click="saveIncludeItem($event)">保存</button>
      </h5>
      <div class="include-item w-5 float-left p-1" v-for="item in includesItems">
          <input type="text" class="form-control" :value="item.key" />
      </div>
      <div id="add-include-item" class="w-5 float-left p-1">
          <button type="button" class="btn btn-success" @click="addIncludeItem($event)">添加</button>
      </div>
      <div class="clearfix"></div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import Alert from '../components/alert.vue'
import Bus from '../eventBus'
export default {
  components: { 
    MainLayout, Alert 
  },
  data () {
    return {
      excludesItems: [],
      includesItems: []
    }
  },
  created () {
    this.getExcludesItems()
    this.getIncludesItems()
  },
  mounted () {
    
  },
  methods: {
    saveExcludeItem(event) {
      let values = []
      $(".exclude-item input").each((index, obj) => {
        let value = $(obj).val()
        if(values.indexOf(value) == -1) {
          values.push(value)
        }
      })

      if(values.length > 0) {
        let url = '/api/stock/saveStockNewsKeyByExclude?keys[]='+values.join()
        let $this = this
        this.$api.post(url, null, rs => {
          this.getExcludesItems()
        })
      } else {
        alert("記錄不能為空!")
      }
    },
    saveIncludeItem(event) {
      let values = []
      $(".include-item input").each((index, obj) => {
        let value = $(obj).val()
        if(values.indexOf(value) == -1) {
          values.push(value)
        }
      })

      if(values.length > 0) {
        let url = '/api/stock/saveStockNewsKeyByInclude?keys[]='+values.join()
        let $this = this
        this.$api.post(url, null, rs => {
          this.getIncludesItems()
        })
      } else {
        alert("記錄不能為空!")
      }
    },
    addExcludeItem(event) {
      // alert(event.target)
      let inputHtml = `
      <div class="exclude-item w-5 float-left p-1">
          <input type="text" class="form-control" />
      </div>        
      `
      $("#add-exclude-item").before(inputHtml)
    },
    addIncludeItem(event) {
      // alert(event.target)
      let inputHtml = `
      <div class="include-item w-5 float-left p-1">
          <input type="text" class="form-control" />
      </div>        
      `
      $("#add-include-item").before(inputHtml)
    },
    getExcludesItems () {
      this.excludesItems = []
      let url = '/api/stock/getStockNewsKeyByExclude'
      this.$api.get(url, null, rs => {
        $(".exclude-item").remove()
        this.excludesItems = rs
      })
    },
    getIncludesItems () {
      this.includesItems = []
      let url = '/api/stock/getStockNewsKeyByInclude'
      this.$api.get(url, null, rs => {
        $(".include-item").remove()
        this.includesItems = rs
      })
    }
  }
}
</script>

<style scoped>
 .w-5 {
  width: ($spacer * .05) !important;
 }
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->