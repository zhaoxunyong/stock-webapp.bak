<template>
  <div>
    <b-navbar type="dark" variant="primary" toggleable>
    <b-navbar-toggle target="nav_dropdown_collapse"></b-navbar-toggle>
    <b-collapse is-nav id="nav_dropdown_collapse">
      <b-navbar-nav>
        <b-nav-item href="#">Home</b-nav-item>
        <b-nav-item href="#">Link</b-nav-item>
        <!-- Navbar dropdowns -->
        <b-nav-item-dropdown text="台股" right>
          <b-dropdown-item href="#" @click.prevent="go('/')">
            自選股
          </b-dropdown-item>
          <b-dropdown-item href="#" @click.prevent="go('/importantNews/1')">
            市場重大新聞
          </b-dropdown-item>
        </b-nav-item-dropdown>

        <b-nav-item-dropdown text="個性化設置" right>
          <b-dropdown-item href="#" @click.prevent="go('/editMySelectedType')">
            新聞設置
          </b-dropdown-item>  
          <b-dropdown-item href="#" @click.prevent="go('/editMySelectedType')">
            自選股
          </b-dropdown-item>   
          <b-dropdown-item href="#" @click.prevent="go('/stockDailyTransactions')">
            每天買入賣出股
          </b-dropdown-item>
        </b-nav-item-dropdown>
        </b-nav-item>
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