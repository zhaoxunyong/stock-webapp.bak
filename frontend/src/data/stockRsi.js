// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'

let RSI = require('technicalindicators').RSI

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)
    let inputRSI12 = {
    values : stockUtils.getCloses(datas),
    period : 12
    }

    let rsi12 = RSI.calculate(inputRSI12)

    let inputRSI100 = {
    values : stockUtils.getCloses(datas),
    period : 100
    }
    let rsi100 = RSI.calculate(inputRSI100)

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
            formatter: function (params) {
                let v = `<font color="${STOCK_CONFIG.col.up}">RSI-12</font> ${params[0].value}
                <font color="${STOCK_CONFIG.col.down}">RSI-100</font> ${params[0].value}`
                $("#tooltipId3"+kineType).html(v)
                return "";
            },
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
            top: '5%',
            left: '15%',
            right: '5%',
            height: '90%'
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
            /* axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
                }
            } */
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
            // y: '94%',
            start: STOCK_CONFIG.st,
            end: STOCK_CONFIG.ed
        }],
        series: [
            {
                name: 'RSI-12',
                type: 'line',
                data: stockUtils.getSlice(rsi12),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.down
                    }
                }
            } ,{
                name: 'RSI-100',
                type: 'line',
                data: stockUtils.getSlice(rsi100),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.up
                    }
                }
            }
        ]
    }
}