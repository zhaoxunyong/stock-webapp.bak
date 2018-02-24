<template>
  <main-layout>
  <b-container fluid>
      <b-row>
        <b-col xl="2" sm="2" md="2" lg="2" style="box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;"><stock-my-data></stock-my-data></b-col>
        <b-col xl="10" sm="10" md="10" lg="10" style="box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;">
          <stock-my-selected-type></stock-my-selected-type>
          <stock-news></stock-news>
        </b-col>
      </b-row>
  </b-container>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import StockMyData from './stockmydata.vue'
import StockMySelectedType from './stockmyselectedtype.vue'
import StockNews from './stocknews.vue'
export default {
  components: { 
    MainLayout, StockMyData, StockMySelectedType, StockNews
  }
}
</script>