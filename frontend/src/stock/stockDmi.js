// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'

// let ADX = require('technicalindicators').ADX

export default function getData (datasets, kineType) {
    let kDisplay = kineType == 1 ? "月" : "日"
    let datas = stockUtils.splitData(datasets)
    /* // DMI
    let inputDMI = {
        close: stockUtils.getCloses(datas),
        high: stockUtils.getHighs(datas),
        low: stockUtils.getLows(datas),
        period : 14
    }
    let dmis = ADX.calculate(inputDMI)

    let adxs = stockUtils.getDmiAdxs(dmis)
    let mdis = stockUtils.getDmiMdis(dmis)
    let pdis = stockUtils.getDmipdis(dmis) */
    
    let dmiResult = stockUtils.getDmis(datas)
    // let adxs = dmiResult.adx14
    // let diUps = dmiResult.diUp14
    // let diDowns = dmiResult.diDown14

    let diUp14Results = dmiResult.diUp14Result
    let diDown14Results = dmiResult.diDown14Result
    let adx14Results = dmiResult.adx14Result

    // let trResults = dmiResult.trResult
    // let tr14Results = dmiResult.tr14Result
    // let dmUpResults = dmiResult.dmUpResult
    // let dmDownResults = dmiResult.dmDownResult
    // let dmUp14Results = dmiResult.dmUp14Result
    // let dmDown14Results = dmiResult.dmDown14Result
    // let dxResults = dmiResult.dxResult
    let adxr14Results = dmiResult.adxr14Result

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
                let v = `
                <font color="${STOCK_CONFIG.col.diUp}">+DI14:</font> ${params[0].value.toFixed(1)}
                <font color="${STOCK_CONFIG.col.diDown}">-DI14:</font> ${params[1].value.toFixed(1)}
                <font color="${STOCK_CONFIG.col.adx}">ADX14:</font> ${params[2].value.toFixed(1)}
                <font color="${STOCK_CONFIG.col.adxr}">ADXR14:</font> ${params[3].value.toFixed(1)}
                `
                // <font color="${STOCK_CONFIG.col.adx}">TR:</font> ${params[3].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">TR14:</font> ${params[4].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">+DM:</font> ${params[5].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">-DM:</font> ${params[6].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">+DM14:</font> ${params[7].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">-DM14:</font> ${params[8].value.toFixed(1)}
                // <font color="${STOCK_CONFIG.col.adx}">DX:</font> ${params[9].value.toFixed(1)}
                $("#tooltipId4"+kineType).html(v)
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
            left: '10%',
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
            // position: 'right',
            min: function(value) {
                return (value.min*0.95).toFixed(0);
            },
            max: function(value) {
                return (value.max*1.05).toFixed(0);
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
                name: '+DI14',
                type: 'line',
                data: stockUtils.getSlice(diUp14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.diUp
                    }
                }
            }, {
                name: '-DI14',
                type: 'line',
                data: stockUtils.getSlice(diDown14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.diDown
                    }
                }
            }, {
                name: 'ADX14',
                type: 'line',
                data: stockUtils.getSlice(adx14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, /* {
                name: 'TR',
                type: 'line',
                data: stockUtils.getSlice(trResults),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: 'TR14',
                type: 'line',
                data: stockUtils.getSlice(tr14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: '+DM',
                type: 'line',
                data: stockUtils.getSlice(dmUpResults),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: '-DM',
                type: 'line',
                data: stockUtils.getSlice(dmDownResults),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: '+DM14',
                type: 'line',
                data: stockUtils.getSlice(dmUp14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: '-DM14',
                type: 'line',
                data: stockUtils.getSlice(dmDown14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            }, {
                name: 'DX',
                type: 'line',
                data: stockUtils.getSlice(dxResults),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adx
                    }
                }
            },  */{
                name: 'ADXR14',
                type: 'line',
                data: stockUtils.getSlice(adxr14Results),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: STOCK_CONFIG.col.adxr
                    }
                }
            }
        ]
    }
}