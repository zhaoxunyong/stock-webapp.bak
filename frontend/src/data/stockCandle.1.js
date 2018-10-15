// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from './stockUtils'

import * as macd from './MACD'
// import boll from 'bollinger-bands'
// var MACD = require('technicalindicators').MACD
let BB = require('technicalindicators').BollingerBands
let RSI = require('technicalindicators').RSI
let ADX = require('technicalindicators').ADX
import echarts from 'echarts/lib/echarts'
const PERIOD = -88

export default function getData (datasets, kineType) {
  let kDisplay = kineType == 1 ? "月" : "日"
  let datas = stockUtils.splitData(datasets)
  let sliceVols = stockUtils.getSlice(datas.vols)
//   let EMAS = macd.EMASL(datas.values,12);
//   let EMAL = macd.EMASL(datas.values,26);
//   console.log("EMAS->"+EMAS);
//   console.log("EMAL->"+EMAL);
  let difs = macd.DIF(datas.values);
  let deas = macd.DEA(datas.values,9);
  let macds = macd.BAR(datas.values);
//   console.log("macds->"+macds);
//   console.log("difs->"+difs);
//   console.log("deas->"+deas);

  let inputBoll = {
    period : 20, 
    values : stockUtils.getCloses(datas),
    stdDev : 2
    
  }
  let bolls = BB.calculate(inputBoll)
  let lowers = stockUtils.getBollLowers(bolls)
  let middles = stockUtils.getBollMiddles(bolls)
  let uppers = stockUtils.getBollUppers(bolls)

//   let bolls = boll(getCloses(datas), 20, 2)
//   let lowers = bolls.lower
//   let middles = bolls.mid
//   let uppers = bolls.upper

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
//   alert(datas.values.length+"->"+adxs.length)

//   alert(rsi12)
//   alert(rsi100)

/* let datas = splitData([
    ['2017-1-3', 21.37, 20.99, 20.9, 21.37],
    ['2017-1-4', 20.92, 21.17, 20.84, 21.24]
]); */
    const config = {
        // barWidth: 3,//指定柱宽度
        col: {
            // up: 'rgb(153, 14, 14)',
            // down: '#19b34c',
            up: '#ff4238',
            down: '#30d94c',
            m5: '#ec63a7',
            m10: '#f5cc65',
            m20: '#1e88e5',
            m60: 'purple',
            // y: '#ffefef'
        },
        // bg: '#000',
        st: 0,
        ed: 100
    }

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
            height: '30%',
            /* tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        },{
            top: '38%',
            left: '15%',
            right: '5%',
            height: '13%',
            /* tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        },{
            top: '53%',
            left: '15%',
            right: '5%',
            height: '13%',
            /* tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        },{
            top: '68%',
            left: '15%',
            right: '5%',
            height: '13%',
            /* tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        },{
            top: '83%',
            left: '15%',
            right: '5%',
            height: '13%',
            /* tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            } */
        }
        ],
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
            tooltip : {             // Series config.
                trigger: 'item',
                backgroundColor: 'black',
                position : [0, 0],
                formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
            },
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
        }, {
            type: 'category',
            data: stockUtils.getSlice(datas.categoryData),
            boundaryGap: false,
            gridIndex: 1,
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }, {
            type: 'category',
            data: stockUtils.getSlice(datas.categoryData),
            boundaryGap: false,
            gridIndex: 2,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            }
        }, {
            type: 'category',
            data: stockUtils.getSlice(datas.categoryData),
            boundaryGap: false,
            gridIndex: 3,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            }
        }, {
            type: 'category',
            data: stockUtils.getSlice(datas.categoryData),
            boundaryGap: false,
            gridIndex: 4,
            axisTick: {
                show: false
            },
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
                color: config.col.y
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
        }, {
            gridIndex: 1,
            // position: 'right',
            xAxisIndex: 1,
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
                color: config.col.y
            }
        }, {
            gridIndex: 2,
            // position: 'right',
            xAxisIndex: 2,
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
                color: config.col.y
            }
        }, {
            gridIndex: 3,
            // position: 'right',
            xAxisIndex: 3,
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
                color: config.col.y
            }
        }, {
            gridIndex: 4,
            // position: 'right',
            xAxisIndex: 4,
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
                color: config.col.y
            }
        }],

        dataZoom: [{
            type: 'inside',
            xAxisIndex: [0, 1],
            start: config.st,
            end: config.ed
        }, {
            show: false,
            type: 'slider',
            xAxisIndex: [0, 1],
            y: '94%',
            start: config.st,
            end: config.ed
        }, {
            show: false,
            type: 'slider',
            xAxisIndex: [0, 2],
            y: '94%',
            start: config.st,
            end: config.ed
        }, {
            show: false,
            type: 'slider',
            xAxisIndex: [0, 3],
            y: '94%',
            start: config.st,
            end: config.ed
        }, {
            show: false,
            type: 'slider',
            xAxisIndex: [0, 4],
            y: '94%',
            start: config.st,
            end: config.ed
        }],
        series: [
            {
                type: 'k', //Candlestick 
                name: '日K',
                // barGap:'1%',
                // barCategoryGap:"1%",
                barWidth: config.barWidth,
                data: stockUtils.getSlice(datas.values),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                itemStyle: {
                    normal: {
                        width: 1,
                        color: config.col.up, //阳线填充色
                        color0: config.col.down,
                        borderColor: config.col.up, //阳线边框色
                        borderColor0: config.col.down
                    }
                }
            }, {
                type: 'line',
                name: '5'+kDisplay+'平均线',
                data: stockUtils.getSlice(stockUtils.calculateMA(datas, 5)),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                tooltip : {             // Series config.
                    trigger: 'item',
                    backgroundColor: 'black',
                    position : [0, 0],
                    formatter: "Series formatter: <br/>{a}<br/>{b}:{c}"
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.m5
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
                        color: config.col.m10
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
                        color: config.col.m20
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
                        color: config.col.m60
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
            } /* , {
                type: 'line',
                name: '振幅', //下面的折线图
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: stockUtils.calculateSA(datas),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: 'red'
                    }
                }
            } */ , {
                type: 'bar',
                name: '成交量', //下面的柱状图
                barWidth: config.barWidth,
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: sliceVols,
                smooth: true,
                showSymbol: false,
                symbol: "none",
                itemStyle: {
                    normal: {
                        width: 1,
                        color: (params) => {
                            let currVol = params.data
                            let previousVol = params.dataIndex > 0 ? sliceVols[params.dataIndex - 1] : 0
                            if(parseInt(currVol) < parseInt(previousVol)) {
                                return config.col.down;
                            } else {
                                return config.col.up;
                            }
                        },
                    }
                }
            }, {
                name: 'RSI-12',
                type: 'line',
                xAxisIndex: 2,
                yAxisIndex: 2,
                data: stockUtils.getSlice(rsi12),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.down
                    }
                }
            } ,{
                name: 'RSI-100',
                type: 'line',
                xAxisIndex: 2,
                yAxisIndex: 2,
                data: stockUtils.getSlice(rsi100),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.up
                    }
                }
            }, {
                name: 'MDI',
                type: 'line',
                xAxisIndex: 3,
                yAxisIndex: 3,
                data: stockUtils.getSlice(mdis),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#2E2EFE'
                    }
                }
            }, {
                name: 'PDI',
                type: 'line',
                xAxisIndex: 3,
                yAxisIndex: 3,
                data: stockUtils.getSlice(pdis),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#FE9A2E'
                    }
                }
            }, {
                name: 'ADX',
                type: 'line',
                xAxisIndex: 3,
                yAxisIndex: 3,
                data: stockUtils.getSlice(adxs),
                smooth: true,
                showSymbol: false,
                symbol: "none",
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#B45F04'
                    }
                }
            }, {
                name: 'MACD',
                type: 'bar',
                barWidth: config.barWidth,
                xAxisIndex: 4,
                yAxisIndex: 4,
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
                xAxisIndex: 4,
                yAxisIndex: 4,
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
                xAxisIndex: 4,
                yAxisIndex: 4,
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