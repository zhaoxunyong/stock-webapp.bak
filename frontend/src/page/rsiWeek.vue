<template>
    <div class="hello">
      <h1>TechanJS</h1>
    </div>
</template>
<script>
// https://github.com/chovy/techan-vue/blob/master/src/components/Hello.vue
// import MainLayout from '../layouts/Main.vue'
// import Alert from '../components/alert.vue'
import Bus from '../eventBus'

// http://bl.ocks.org/andredumas/6da267f1c51a13dea35b
    
export default {
  components: {
    // MainLayout//, Alert
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    var api = "/api/stock/data?stockId=402378924086857728&startDate=2017/07/18&endDate=2018/08/20";
    var margin = {top: 20, right: 20, bottom: 30, left: 50},
            width = 960 - margin.left - margin.right,
            height = 500 - margin.top - margin.bottom;

    var parseDate = d3.timeParse("%Y/%m/%d");

    var x = techan.scale.financetime()
            .range([0, width]);

    var y = d3.scaleLinear()
            .range([height, 0]);

    var rsi = techan.plot.rsi()
            .xScale(x)
            .yScale(y);

    var xAxis = d3.axisBottom(x);

    var yAxis = d3.axisLeft(y)
            .tickFormat(d3.format(",.3s"));

    var svg = d3.select("body").append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
        .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    d3.csv(api, function(error, data) {
        var accessor = rsi.accessor();

        data = data.slice(0, 200).map(function(d) {
            // Open, high, low, close generally not required, is being used here to demonstrate colored volume
            // bars
            return {
                date: parseDate(d.Date),
                volume: +d.Volume,
                open: +d.Open,
                high: +d.High,
                low: +d.Low,
                close: +d.Close
            };
        }).sort(function(a, b) { return d3.ascending(accessor.d(a), accessor.d(b)); });

        svg.append("g")
                .attr("class", "rsi");

        svg.append("g")
                .attr("class", "x axis")
                .attr("transform", "translate(0," + height + ")");

        svg.append("g")
                .attr("class", "y axis")
            .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .text("RSI");

        // Data to display initially
        draw(data.slice(0, data.length-20));
        // Only want this button to be active if the data has loaded
        d3.select("button").on("click", function() { draw(data); }).style("display", "inline");
    });

    function draw(data) {
        var rsiData = techan.indicator.rsi()(data);
        x.domain(rsiData.map(rsi.accessor().d));
        y.domain(techan.scale.plot.rsi(rsiData).domain());

        svg.selectAll("g.rsi").datum(rsiData).call(rsi);
        svg.selectAll("g.x.axis").call(xAxis);
        svg.selectAll("g.y.axis").call(yAxis);
    }
    
  },
  created () {

  },
  methods: {

  }
}
</script>

<style>
    body {
        font: 10px sans-serif;
    }

    text {
        fill: #000;
    }

    button {
        position: absolute;
        right: 20px;
        top: 440px;
        display: none;
    }

    .rsi path {
        fill: none;
        stroke-width: 1;
    }

    .rsi {
        stroke: #000000;
    }

    .rsi path.overbought, .rsi path.oversold {
        stroke: #FF9999;
        stroke-dasharray: 5, 5;
    }

    .rsi path.middle, path.zero {
        stroke: #BBBBBB;
        stroke-dasharray: 5, 5;
    }
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
