<template>
  <main-layout>
    <b-container fluid class="border">
      <b-row>
        <!--left-->
        <b-col style="max-width: 15%" class="border-right border-primary">
          <stock-my-data></stock-my-data>
        </b-col>
        <b-col style="max-width: 85%">
        <!--自选股top-->
          <b-row>
            <b-col cols="12" sm="12">
              <stock-my-selected-type></stock-my-selected-type>
              <stock-detail></stock-detail>
              <!--<img src="../images/stock03.png" class="w-100" />-->
            </b-col>
          </b-row>
          <!--个股详情与新闻-->
          <b-row>
            <!--个股左侧信息-->
            <b-col cols="9" sm="9">
              <!--个股详情-->
              <!--<b-row>
                <b-col cols="12" sm="12">
                  <b-row>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                  </b-row>
                  <b-row>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                  </b-row>
                  <b-row>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                      <b-col>
                        <img src="../images/stock01.png" class="w-100" />
                      </b-col>
                  </b-row>
                </b-col>
              </b-row>-->
              <!--新闻-->
              <b-row class="mt-3">
                <b-col cols="12" sm="12">
                  <stock-news></stock-news>
                </b-col>
              </b-row>
            </b-col>
            <!--个股右侧信息-->
            <b-col cols="3" sm="3">
              <img src="../images/stock02.png" class="w-100" />
              <img src="../images/stock04.png" class="w-100" />
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
// import Alert from '../components/alert.vue'
export default {
  components: {
    MainLayout, StockMyData, StockMySelectedType, StockDetail, StockNews//, Alert//, Loading
  }
}
</script>
