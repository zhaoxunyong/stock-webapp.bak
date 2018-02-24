<template>
  <div id="app">
    <ul class="f-cb">
        <li>
          <router-link to="/" exact tag="div"  event="mouseover">home</router-link>
        </li>
        <li>
          <router-link :to="{path:'/document'}" event="mouseover">document</router-link>
        </li>
        <router-link to="/about" tag="li" event="mouseover">
          <i></i>
          <span>about</span>
        </router-link>
      </ul>

    <div class="navf-cb">
      <ul>
          <li v-for="i in list">
            <router-link :to="{path:'/document'}">
              {{ i.company + ' - ' + i.no }}
            </router-link>
          </li>
        </ul>
    </div>
    <div>
      <router-view name="slider"></router-view> <!-- 命名视图 -->
      <router-view class="center"></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getData()
    console.log(this.getStatus(this.$route.path))
  },
  methods: {
    getStatus (urlStr) {
        var urlStrArr = urlStr.split('/')
        return urlStrArr[urlStrArr.length - 1]
      },
    getData () {
      this.$api.get('/api/stock/getStockDatas', null, r => {
        this.list = r
      })

      // this.$api.get('topics', null, function (r) {
      //   this.list = r.data
      // })
    }
  },
  watch: {
      '$route' (to, from) {
        console.log(this.getStatus(this.$route.path))
      }
    }
}
</script>
