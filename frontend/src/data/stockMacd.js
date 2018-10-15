// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'

import * as macd from './MACD'

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)
    let difs = macd.DIF(datas.values);
    let deas = macd.DEA(datas.values,9);
    let macds = macd.BAR(datas.values);

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip : {
            trigger: 'axis',
            backgroundColor: 'black',
            position : [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: "Series formatter: <br/>{a}<br/>{b}:{c}",
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [{
            top: '3%',
            left: '15%',
            right: '5%',
            height: '80%',
            /* tooltip : {             // Series STOCK_CONFIG.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        }],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            },
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: stockUtils.getSlice(datas.categoryData),
            boundaryGap: false,
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }],
        // 
        yAxis: [{
            splitNumber: 2,
            /* splitArea: {
                show: false
            }, */
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            },
            axisLabel: {
                lineStyle:{  
                    color:'red',  
                },
                show: true,
                color: STOCK_CONFIG.col.y
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [
            {
                name: 'MACD',
                type: 'bar',
                barWidth: STOCK_CONFIG.barWidth,
                data: stockUtils.getSlice(macds),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                itemStyle: {
                    normal: {
                        width: 1,
                        color: function(params) {
                            var colorList;
                            if (params.data >= 0) {
                                colorList = '#ef232a';
                            } else {
                                colorList = '#14b143';
                            }
                            return colorList;
                        },
                    }
                }
            },{
                name: 'DIF',
                type: 'line',
                data: stockUtils.getSlice(difs),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: 'blue'
                    }
                }
            },{
                name: 'DEA',
                type: 'line',
                data: stockUtils.getSlice(deas),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: 'blue'
                    }
                }
            }
        ]
    }
}