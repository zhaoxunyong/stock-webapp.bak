<template>
  <main-layout>
    <div>
      <div class="article_list">
        <ul>
          <li v-for="i in list">
            <time v-text="$utils.goodTime(i.create_at)"></time>
            <router-link :to="'/content/' + i.id">
              {{ i.title }}
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
export default {
  components: { 
    MainLayout 
  }/*,
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    getData () {
      this.$api.get('topics', null, r => {
        this.list = r.data
      })

      // this.$api.get('topics', null, function (r) {
      //   this.list = r.data
      // })
    }
  }*/
}
</script>

<style>
  .article_list {margin: auto;}
</style>