// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from './stockUtils'

// import boll from 'bollinger-bands'
// var MACD = require('technicalindicators').MACD
let BB = require('technicalindicators').BollingerBands

import echarts from 'echarts/lib/echarts'

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)

    let inputBoll = {
    period : 20, 
    values : stockUtils.getCloses(datas),
    stdDev : 2

    }
    let bolls = BB.calculate(inputBoll)
    let lowers = stockUtils.getBollLowers(bolls)
    let middles = stockUtils.getBollMiddles(bolls)
    let uppers = stockUtils.getBollUppers(bolls)

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
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
            // scale: true,
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: false,
            // 坐标文字内容
            axisLabel: {
                onZero: false,
			    /* lineStyle:{  
                    color:'red',  
                }, */
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return echarts.format.formatTime('MM/dd', value.replace('/','-'));
                }
            }
        }],
        // 
        yAxis: [{
            axisLabel: {
                lineStyle:{  
                    color:'red',  
                },
                color: STOCK_CONFIG.col.y
            },
            scale: true,
            // position: 'right',,
            splitNumber: 2,
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: false,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            }
        }],
        dataZoom: [{
            type: 'inside',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }, {
            show: false,
            type: 'slider',
            y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [
            {
                type: 'k', //Candlestick 
                name: '日K',
                // barGap:'1%',
                // barCategoryGap:"1%",
                barWidth: STOCK_CONFIG.barWidth,
                data: stockUtils.getSlice(datas.values),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                itemStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.up, //阳线填充色
                        color0: STOCK_CONFIG.col.down,
                        borderColor: STOCK_CONFIG.col.up, //阳线边框色
                        borderColor0: STOCK_CONFIG.col.down
                    }
                }
            }, {
                type: 'line',
                name: '5'+kDisplay+'平均线',
                data: stockUtils.getSlice(stockUtils.calculateMA(datas, 5)),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                tooltip : {             // Series STOCK_CONFIG.
                    trigger: 'item',
                    backgroundColor: 'black',
                    position : [0, 0],
                    formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.m5
                    }
                }
            }, {
                type: 'line',
                name: '10'+kDisplay+'平均线',
                data: stockUtils.getSlice(stockUtils.calculateMA(datas, 10)),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.m10
                    }
                }
            }, {
                type: 'line',
                name: '20'+kDisplay+'平均线',
                data: stockUtils.getSlice(stockUtils.calculateMA(datas, 20)),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.m20
                    }
                }
            }, {
                type: 'line',
                name: '60'+kDisplay+'平均线',
                data: stockUtils.getSlice(stockUtils.calculateMA(datas, 60)),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.m60
                    }
                }
            }, {
                type: 'line',
                name: '上軸線',
                data: stockUtils.getSlice(uppers),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }, {
                type: 'line',
                name: '中軸線',
                data: stockUtils.getSlice(middles),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }, {
                type: 'line',
                name: '下軸線',
                data: stockUtils.getSlice(lowers),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }
        ]
    }
}