// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'

let ADX = require('technicalindicators').ADX

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)
    // DMI
    let inputDMI = {
        close: stockUtils.getCloses(datas),
        high: stockUtils.getHighs(datas),
        low: stockUtils.getLows(datas),
        period : 14
    }
    let dmis = ADX.calculate(inputDMI)

    let adxs = stockUtils.getDmiAdxs(dmis)
    let mdis = stockUtils.getDmiMdis(dmis)
    let pdis = stockUtils.getDmipdis(dmis)

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
                let v = `<font color="${STOCK_CONFIG.col.mdi}">MDI</font> ${params[0].value.toFixed(2)}
                <font color="${STOCK_CONFIG.col.pdi}">PDI</font> ${params[1].value.toFixed(2)}
                <font color="${STOCK_CONFIG.col.adx}">ADX</font> ${params[2].value.toFixed(2)}`
                $("#tooltipId4"+kineType).html(v)
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
            height: '85%'
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
                name: 'MDI',
                type: 'line',
                data: stockUtils.getSlice(mdis),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.mdi
                    }
                }
            }, {
                name: 'PDI',
                type: 'line',
                data: stockUtils.getSlice(pdis),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.pdi
                    }
                }
            }, {
                name: 'ADX',
                type: 'line',
                data: stockUtils.getSlice(adxs),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }
        ]
    }
}