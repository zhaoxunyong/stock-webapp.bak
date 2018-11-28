// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'
import * as macd from '../utils/macdUtils'

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)
    let difs = macd.DIF(datas.values);  // DIF
    let macds = macd.MACD(datas.values,9); // 也就是DEM或MACD
    let oscs = macd.OSC(datas.values); // 也就是OSC
    let displayOscs = stockUtils.getSlice(oscs)
    let lowest = displayOscs.reduce((pre, cur) => pre < cur ? pre : cur)
    let highest = displayOscs.reduce((pre,cur) => pre>cur?pre:cur)

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
                let v = `<font color="${STOCK_CONFIG.col.oscup}">OSC:</font> ${params[0].value.toFixed(1)}
                <font color="${STOCK_CONFIG.col.dif}">DIF:</font> ${params[1].value.toFixed(1)}
                <font color="${STOCK_CONFIG.col.macd}">MACD:</font> ${params[2].value.toFixed(1)}`
                $("#tooltipId5"+kineType).html(v)
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
        grid: [{
            top: '5%',
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
            min: function(value) {
                return (value.min*0.95).toFixed(0);
            },
            max: function(value) {
                return (value.max*1.05).toFixed(0);
            },
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
            // position: 'right',
            // min: 'dataMin',
            // max: 'dataMax',
            min: function(value) {
                return (lowest).toFixed(0);
            },
            max: function(value) {
                return (highest).toFixed(0);
            },
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
            disabled: true,
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
                name: 'OSC', // BAR
                type: 'bar',
                // braGap用于设置同一个类目内的柱形之间的间距
                // barGap: '1%',
                // barCategoryGap则用于设置不同类目之间的间距
                barCategoryGap: STOCK_CONFIG.barCategoryGap,
                barWidth: STOCK_CONFIG.barWidth,
                data: displayOscs,
                smooth: true,
                showSymbol: false,
                symbol: "none",
                itemStyle: {
                    normal: {
                        width: 1,
                        color: function(params) {
                            var colorList;
                            if (params.data >= 0) {
                                colorList = STOCK_CONFIG.col.oscup;
                            } else {
                                colorList = STOCK_CONFIG.col.oscdown;
                            }
                            return colorList;
                        },
                    }
                }
            },{
                name: 'DIF', // DIF
                type: 'line',
                data: stockUtils.getSlice(difs),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.dif
                    }
                }
            },{
                name: 'MACD',
                type: 'line',
                data: stockUtils.getSlice(macds),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.macd
                    }
                }
            }
        ]
    }
}