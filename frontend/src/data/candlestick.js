// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
function splitData(rawData) {
    var categoryData = [];
    var values = []
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i])
    }
    return {
        categoryData: categoryData,
        values: values
    };
}

function calculateMA(data0, dayCount) {
    var result = [];
    for (var i = 0, len = data0.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data0.values[i - j][1];
        }
        result.push(sum / dayCount);
    }
    return result;
}

export default function getData (datas, kDisplay) {
  let data0 = splitData(datas)
/*let data0 = splitData([
    ['2017-1-3', 21.37, 20.99, 20.9, 21.37],
    ['2017-1-4', 20.92, 21.17, 20.84, 21.24]
]);*/
  return {
    // backgroundColor: '#2c343c',
    // 下面日期文字颜色
    textStyle: {
        color: 'black'
    },
    /*title: {
        text: kDisplay+'線',
        left: 0
    },*/
    /* tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'line'
        }
    }, */
    /* kline: {
        center: ['50%', '54%']
    }, */
    legend: {
        data: [kDisplay+'K', 'MA5', 'MA10', 'MA20', 'MA60']
    },
    grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
    },
    xAxis: {
        type: 'category',
        data: data0.categoryData,
        scale: true,
        boundaryGap : false,
        axisLine: {onZero: false},
        splitLine: {show: false},
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax'
    },
    yAxis: {
        scale: true,
        splitArea: {
            show: true
        }
    },
    dataZoom: [
        {
            type: 'inside',
            start: 0,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            y: '90%',
            start: 0,
            end: 100
        }
    ],
    series: [
        {
            name: kDisplay+'K',
            type: 'candlestick',
            data: data0.values,
            itemStyle: {
                normal: {
                    // 阳线：红
                    // color: "#fa6464",
                    color: "red",
                    // 阴线：绿
                    // color0: "#32C896",
                    color0: "green",
                    borderColor: "#fa6464",
                    borderColor0: "#32C896"
                }
            },
            /* markPoint: {
                label: {
                    normal: {
                        formatter: function (param) {
                            return param != null ? Math.round(param.value) : '';
                        }
                    }
                },
                data: [
                    {
                        name: 'XX标点',
                        coord: ['2013/5/31', 2300],
                        value: 2300,
                        itemStyle: {
                            normal: {color: 'green'}
                        }
                    },
                    {
                        name: 'highest value',
                        type: 'max',
                        valueDim: 'highest'
                    },
                    {
                        name: 'lowest value',
                        type: 'min',
                        valueDim: 'lowest'
                    },
                    {
                        name: 'average value on close',
                        type: 'average',
                        valueDim: 'close'
                    }
                ],
                tooltip: {
                    formatter: function (param) {
                        return param.name + '<br>' + (param.data.coord || '');
                    }
                }
            },
            markLine: {
                symbol: ['none', 'none'],
                data: [
                    {
                        name: 'min line on close',
                        type: 'min',
                        valueDim: 'close'
                    },
                    {
                        name: 'max line on close',
                        type: 'max',
                        valueDim: 'close'
                    }
                ]
            } */
        },
        {
            name: 'MA5',
            type: 'line',
            data: calculateMA(data0, 5),
            smooth: true,
            showSymbol: false,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA10',
            type: 'line',
            data: calculateMA(data0, 10),
            smooth: true,
            showSymbol: false,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA20',
            type: 'line',
            data: calculateMA(data0, 20),
            smooth: true,
            showSymbol: false,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA60',
            type: 'line',
            data: calculateMA(data0, 60),
            smooth: true,
            showSymbol: false,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        }

    ]
  }
}