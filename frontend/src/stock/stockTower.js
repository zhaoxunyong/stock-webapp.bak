// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'

export default function getData(datasets, kineType) {
    let datas = stockUtils.splitData(datasets)
    return {
        // 提示框浮层的位置
        animation: false,
        tooltip : {
            trigger: 'axis',
            backgroundColor: 'black',
            position : [0, 0],
            formatter: function (params) {
                let v = `<font color="${STOCK_CONFIG.col.rsi12}">寶塔:</font> ${params[0].data[2]}`
                $("#tooltipId6"+kineType).html(v)
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
            }, */
            // 坐标刻度
            axisTick: {
                show: false
            },
            // 坐标文字内容
            axisLabel: {
                show: false
            }
        }],
        yAxis: [{
            /* axisLabel: {
                lineStyle:{  
                    color:'red',  
                },
                color: STOCK_CONFIG.col.y
            }, */
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
            },
            axisLabel: {
                onZero: false
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
                type: 'k', //Candlestick 
                name: '寶塔圖',
                id: 'tower',
                // braGap用于设置同一个类目内的柱形之间的间距
                // barGap: '1%',
                // barCategoryGap则用于设置不同类目之间的间距
                barCategoryGap: STOCK_CONFIG.barCategoryGap,
                barWidth: STOCK_CONFIG.barWidth,
                data: stockUtils.getSlice(stockUtils.getTowerDatas(datas)),
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
            }
        ]
    }
}