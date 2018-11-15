<template>
  <main-layout>
    <div class="border border-primary m-2 float-left" style="width: 45%">
      <h5 class="text-primary p-1">去重關鍵字：
      <button type="button" class="btn btn-primary" @click="saveExcludeItem($event)">保存</button>
      <button type="button" class="btn btn-primary" @click="addExcludeItem($event)">添加</button>
      </h5>
      <div class="exclude-item w-5 float-left p-1" v-for="item in excludesItems">
          <input type="text" class="form-control" :value="item.key" />
      </div>
      <div id="add-exclude-item" class="w-3 float-left p-1">
      </div>
      <div class="clearfix"></div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'
export default {
  components: {
    MainLayout//, Alert
  },
  data () {
    return {

    }
  },
  created () {
  },
  mounted () {

  },
  methods: {
  }
}
</script>

<style scoped>
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
