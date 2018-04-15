<template>
  <div>
    <b-navbar type="dark" variant="primary" toggleable>
    <b-navbar-toggle target="nav_dropdown_collapse"></b-navbar-toggle>
    <b-collapse is-nav id="nav_dropdown_collapse">
      <b-navbar-nav class="pl-5">
        <b-nav-item href="#" @click.prevent="go('/')">自選股</b-nav-item>
        <b-nav-item href="#" @click.prevent="go('/importantNews/1')">市場重大新聞</b-nav-item>
        <!-- Navbar dropdowns -->
        <!--<b-nav-item-dropdown text="台股" right>-->
          <!--<b-dropdown-item href="#" @click.prevent="go('/')">-->
            <!--自選股-->
          <!--</b-dropdown-item>-->
          <!--<b-dropdown-item href="#" @click.prevent="go('/importantNews/1')">-->
            <!--市場重大新聞-->
          <!--</b-dropdown-item>-->
        <!--</b-nav-item-dropdown>-->

        <b-nav-item-dropdown text="個性化設置" right>
          <b-dropdown-item href="#" @click.prevent="go('/newsSettings')">
            新聞設置
          </b-dropdown-item>
          <b-dropdown-item href="#" @click.prevent="go('/editMySelectedType')">
            自選股設置
          </b-dropdown-item>
          <b-dropdown-item href="#" @click.prevent="go('/stockDailyTransactions')">
            交易資料導入與查詢
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  created () {
  },
  methods: {
    go (url) {
      // '/content/' + i.stockId+'/1'
      this.$router.push(url)

    }
  }
}
</script>

<style scoped>
  a {
    display: inline-block;
  }
</style>
