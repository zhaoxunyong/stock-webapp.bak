// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh

import * as macd from './MACD'
import boll from 'bollinger-bands'
let BB = require('technicalindicators').BollingerBands
let RSI = require('technicalindicators').RSI

// rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
// 获取对应的数据格式
function splitData(rawData) {
    var categoryData = [];
    var values = []
    var vols = []
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i])
        vols.push(rawData[i][4])
        // alert(rawData[i])
    }
    return {
        categoryData: categoryData,
        values: values,
        vols: vols
    };
}
// 平均值
function calculateMA(datas,dayCount) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += datas.values[i - j][1];
        }
        result.push((sum / dayCount).toFixed(2));
    }
    return result;
}

// 收盘价
function getCloses(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][1])
    }
    return result;
}

function getUppers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].upper)
    }
    return result;
}

function getMiddles(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].middle)
    }
    return result;
}

function getLowers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].lower)
    }
    return result;
}

export default function getData (datasets, kDisplay) {
  let datas = splitData(datasets)
//   let EMAS = macd.EMASL(datas.values,12);
//   let EMAL = macd.EMASL(datas.values,26);
//   console.log("EMAS->"+EMAS);
//   console.log("EMAL->"+EMAL);
  let difs = macd.DIF(datas.values);
  let deas = macd.DEA(datas.values,9);
  let macds = macd.BAR(datas.values);
  console.log("macds->"+macds);
  console.log("difs->"+difs);
  console.log("deas->"+deas);

  let period = 20
  let input = {
    period : period, 
    values : getCloses(datas),
    stdDev : 1
    
  }
  let bolls = BB.calculate(input)
  let lowers = getLowers(bolls)
  let middles = getMiddles(bolls)
  let uppers = getUppers(bolls)

//   let bolls = boll(getCloses(datas), 20, 2)
//   let lowers = bolls.lower
//   let middles = bolls.mid
//   let uppers = bolls.upper

  let inputRSI12 = {
    values : getCloses(datas),
    period : 6
  }
  
  let rsi12 = RSI.calculate(inputRSI12)

  let inputRSI100 = {
    values : getCloses(datas),
    period : 20
  }
  let rsi100 = RSI.calculate(inputRSI100)

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
            up: 'red',
            down: 'green',
            m5: '#f00',
            m10: 'yellow',
            m20: '#dd1ce0',
            m60: 'purple',
            // y: '#ffefef'
        },
        // bg: '#000',
        st: 0,
        ed: 100
    }

    return {
        // backgroundColor: config.bg,
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        tooltip: {
            trigger: 'axis',
            position: [10, '70%'],
            formatter: '{a0}:{c0}  {a1}:{c1} {a2}:{c2}',
            // formatter:function(params){
            //     return params.data
            // },
            // backgroundColor: '#fff',
            borderWidth: 1,
            textStyle: {
                color: '#fff',
                width: '100%'
            },
            // 坐标轴指示器配置项
            axisPointer: {
                type: 'cross',
                label: {
                    show: true,
                    color: '#ff0',
                    rich: {
                        a: {
                            // 没有设置 `lineHeight`，则 `lineHeight` 为 56
                        }
                    }
                    // formatter: function(params) {
                    //     // 假设此轴的 type 为 'time'。
                    //     return 'some text' + params.value;
                    // },
                },
                crossStyle: {
                    type: 'solid'
                },

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
                '布林通道3',
                '成交量', 
                'RSI12', 
                'RSI100', 
                'MACD', 
                'DIF', 
                'DEA']
        }, */
        grid: [/* {
            top: '14%',
            // show:true,
            left: '15%', //grid 组件离容器左侧的距离。
            right: '2%',
            height: '60%',
            // borderColor:'#ccc',
        }, {
            top: '80%',
            left: '15%',
            right: '2%',
            height: '18%',
        } */
        {
            top: '3%',
            left: '15%',
            right: '5%',
            height: '40%'
        },{
            top: '50%',
            left: '15%',
            right: '5%',
            height: '13%'
        },{
            top: '65%',
            left: '15%',
            right: '5%',
            height: '13%'
        },{
            top: '80%',
            left: '15%',
            right: '5%',
            height: '13%'
        }
        ],
        axisPointer: {
            link: {
                xAxisIndex: 'all'
            },
            label: {
                backgroundColor: '#777'
            },
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [{
            type: 'category',
            data: datas.categoryData,
            // scale: true,
            // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
            // boundaryGap: false,
            axisLabel: {
                textStyle: {
                    fontSize: '12px',
                    // color: '#a1a1a1'
                }/* ,
                formatter: function (value) {
                    return echarts.format.formatTime('MM-dd', value);
                } */
            }
        }, {
            type: 'category',
            data: datas.categoryData,
            //boundaryGap: false,
            gridIndex: 1,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            }
        }, {
            type: 'category',
            data: datas.categoryData,
            //boundaryGap: false,
            gridIndex: 2,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            }
        }, {
            type: 'category',
            data: datas.categoryData,
            //boundaryGap: false,
            gridIndex: 3,
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
                color: config.col.y
            },
            scale: true,
            // position: 'right',
            // splitArea: {
            //     show: false
            // },
            splitLine: {
                show: true,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            },

            // splitNumber: 10
        }, {
            gridIndex: 1,
            // position: 'right',
            xAxisIndex: 1,
            //splitNumber: 3,
            splitArea: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            },
            axisLabel: {
                show: true,
                color: config.col.y
            }
        }, {
            gridIndex: 2,
            // position: 'right',
            xAxisIndex: 2,
            //splitNumber: 3,
            splitArea: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            },
            axisLabel: {
                show: true,
                color: config.col.y
            }
        }, {
            gridIndex: 3,
            // position: 'right',
            xAxisIndex: 3,
            //splitNumber: 3,
            splitArea: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: ['#888'],
                    type: 'dotted'
                }
            },
            axisLabel: {
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
        }],
        series: [
            /* {
                type: 'line',
                name: '分时',
                data: datas.now
            }, */
            {
                type: 'k', //Candlestick 
                name: '日K',
                barWidth: config.barWidth,
                itemStyle: {
                    normal: {
                        width: 1,
                        color: config.col.up, //阳线填充色
                        color0: config.col.down,
                        borderColor: config.col.up, //阳线边框色
                        borderColor0: config.col.down
                    }
                },
                data: datas.values
            }, {
                type: 'line',
                name: '5'+kDisplay+'平均线',
                data: calculateMA(datas, 5),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.m5
                    }
                }
            }, {
                type: 'line',
                name: '10'+kDisplay+'平均线',
                data: calculateMA(datas, 10),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.m10
                    }
                }
            }, {
                type: 'line',
                name: '20'+kDisplay+'平均线',
                data: calculateMA(datas, 20),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.m20
                    }
                }
            }, {
                type: 'line',
                name: '60'+kDisplay+'平均线',
                data: calculateMA(datas, 60),
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.m60
                    }
                }
            }, {
                type: 'line',
                name: 'bollinger-upper',
                data: uppers,
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }, {
                type: 'line',
                name: 'bollinger-middle',
                data: middles,
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }, {
                type: 'line',
                name: 'bollinger-lower',
                data: lowers,
                smooth: true,
                showSymbol: false,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: '#7999f2'
                    }
                }
            }/* , {
                type: 'line',
                name: '振幅', //下面的折线图
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: calculateSA(datas)
            } */, {
                type: 'bar',
                name: '成交量', //下面的柱状图
                barWidth: config.barWidth,
                xAxisIndex: 1,
                yAxisIndex: 1,
                // data: calculateUD(datas),
                data: datas.vols,
                itemStyle: {
                    normal: {
                        width: 1,
                        color: (params) => {
                            let currVol = params.data
                            let previousVol = params.dataIndex > 0 ? datas.vols[params.dataIndex - 1] : 0
                            // console.log(datas.vols)
                            // console.log("currVol:"+currVol+"->previousVol:"+previousVol)
                            if(parseInt(currVol) < parseInt(previousVol)) {
                                return config.col.down;
                            } else {
                                return config.col.up;
                            }
                        },
                    }
                }
            }, {
                name: 'RSI12',
                type: 'line',
                xAxisIndex: 2,
                yAxisIndex: 2,
                smooth: true,
                showSymbol: false,
                data: rsi12,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.down
                    }
                }
            } ,{
                name: 'RSI100',
                type: 'line',
                xAxisIndex: 2,
                yAxisIndex: 2,
                smooth: true,
                showSymbol: false,
                data: rsi100,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: config.col.up
                    }
                }
            }, {
                name: 'MACD',
                type: 'bar',
                barWidth: config.barWidth,
                xAxisIndex: 3,
                yAxisIndex: 3,
                smooth: true,
                showSymbol: false,
                data: macds,
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
                xAxisIndex: 3,
                yAxisIndex: 3,
                smooth: true,
                showSymbol: false,
                data: difs,
                lineStyle: {
                    normal: {
                        width: 1,
                        color: 'blue'
                    }
                }
            },{
                name: 'DEA',
                type: 'line',
                xAxisIndex: 3,
                yAxisIndex: 3,
                smooth: true,
                showSymbol: false,
                data: deas,
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