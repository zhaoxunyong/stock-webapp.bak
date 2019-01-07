<template>
  <main-layout>
    <b-container fluid class="border">
      <b-row>
        <!--left-->
        <b-col style="max-width: 100px" class="border-right border-primary pr-0 pl-2">
          <stock-my-data></stock-my-data>
        </b-col>
        <b-col style="max-width: 100%">
        <!--自选股top-->
          <b-row>
            <b-col cols="12" sm="12" class="pl-1">
              <stock-my-selected-type></stock-my-selected-type>
              <stock-detail></stock-detail>
            </b-col>
          </b-row>
          <!--个股详情与新闻-->
          <b-row>
            <!--个股左侧信息-->
            <b-col cols="9" sm="9">
              <!--个股K线图-->
              <StockKLineChart></StockKLineChart>
              <!--新闻-->
              <b-row class="mt-3">
                <b-col cols="12" sm="12" class="pl-0 pr-0">
                  <stock-news></stock-news>
                </b-col>
              </b-row>
            </b-col>
            <!--个股右侧信息-->
            <b-col cols="3" sm="3" class="pl-0 pr-0">
              <img src="../images/stock02.png" class="w-80" />
              <img src="../images/stock04.png" class="w-80" />
            </b-col>
          </b-row>
        </b-col>

      </b-row>
    </b-container>
  </main-layout>
</template>
<script>
import MainLayout from '../layouts/Main.vue'
import StockMyData from './StockMyData.vue'
import StockMySelectedType from './stockMySelectedType.vue'
import StockDetail from './stockDetail.vue'
import StockNews from './stockNews.vue'
import StockKLineChart from './stockKLineChart.vue'
// import Alert from '../components/alert.vue'
export default {
  components: {
    MainLayout, StockMyData, StockMySelectedType, StockDetail, StockNews, StockKLineChart//, Alert//, Loading
  }
}
</script>
