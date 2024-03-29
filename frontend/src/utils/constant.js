// 分页显示20条
window.PAGE_SIZE = 20
// K线图获取最近xx天的历史数据
window.RECENT_DATE = -88

window.STOCK_CONFIG = {
    // barCategoryGap则用于设置不同类目之间的间距
    barCategoryGap: "5px",
    barWidth: '80%',//指定柱宽度
    col: {
        // up: 'rgb(153, 14, 14)',
        // down: '#19b34c',
        // k
        up: 'red',
        down: '#337aff', // 蓝
        m5: '#ec63a7',
        m10: '#f5cc65',
        m20: '#1e88e5',
        m60: 'purple',
        // vol
        volup: 'red', // red
        voldown: '#30d94c',
        // rsi
        rsi12: 'blue',
        rsi100: 'red',
        // dmi
        diUp: 'red',
        diDown: 'green',
        adx: 'blue',
        adxr: 'purple',
        // macd
        oscup: 'red', // red
        oscdown: '#30d94c', // green
        dif: 'orange',
        macd: 'blue'
        // y: '#ffefef'
    },
    // bg: '#000',
    st: 43,
    ed: 100
}
