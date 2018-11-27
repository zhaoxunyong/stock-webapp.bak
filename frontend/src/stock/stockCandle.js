// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'

// import boll from 'bollinger-bands'
// var MACD = require('technicalindicators').MACD
let BB = require('technicalindicators').BollingerBands

export default function getData(datasets, kineType) {
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
            // formatter: "Series formatter: <br/>{a}<br/>{b}<br/>{c}",
            // 数据结构：dataIndex/opening/closing/lowest/highest/vol
            // formatter: "{b} 收 {c2} 開 {c1} 高 {c4} 低 {c3}",
            formatter: function (params) {
                // rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
                // let data0 = stockUtils.getSeriesIndex(params,0)
                // 83, 72.5, 70, 67, 73.8, "16032"
                let v = `${params[0].axisValue} 
                收: ${params[0].data[2]} 
                開: ${params[0].data[1]} 
                高: ${params[0].data[4]} 
                低: ${params[0].data[3]}<br/>
                ${kDisplay}線 
                <font color="${STOCK_CONFIG.col.m5}">M5:</font> ${params[1].value} 
                <font color="${STOCK_CONFIG.col.m10}">M10:</font> ${params[2].value} 
                <font color="${STOCK_CONFIG.col.m20}">M20:</font> ${params[3].value} 
                <font color="${STOCK_CONFIG.col.m60}">M60:</font> ${params[4].value}`
                $("#tooltipId1"+kineType).html(v)
                // return `<font color="read">${params[0].data}</font>`;
                return "";
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: false,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        /* legend: {
            // type: 'scroll',
            // orient: 'vertical',
            // left: 'center',
            data: [kDisplay+'K', 
                '5'+kDisplay+'平均线', 
                '10'+kDisplay+'平均线', 
                '20'+kDisplay+'平均线', 
                '60'+kDisplay+'平均线', 
                '布林通道1', 
                '布林通道2', 
                '布林通道3',]
        }, */
        grid: [{
            top: '8%',
            left: '9%',
            right: '0%',
            height: '80%'
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
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            boundaryGap: true,
            // 坐标文字内容
            axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
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
            // min: 'dataMin',
            // max: 'dataMax',
            min: function(value) {
                return (value.min*0.95).toFixed(0);
            },
            max: function(value) {
                return (value.max*1.05).toFixed(0);
            },
            // position: 'right',,
            // type: 'value',
            splitNumber:6,
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
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [
            {
                type: 'k', //Candlestick 
                name: '日K',
                id: 'k-line',
                // braGap用于设置同一个类目内的柱形之间的间距
                // barGap: '1%',
                // barCategoryGap则用于设置不同类目之间的间距
                barCategoryGap: STOCK_CONFIG.barCategoryGap,
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