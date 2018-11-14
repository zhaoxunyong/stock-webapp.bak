// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

// rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
// 获取对应的数据格式
export function splitData(rawData) {
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
export function calculateMA(datas, dayCount) {
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
        result.push((sum / dayCount).toFixed(1));
    }
    return result;
}
// 宝塔图
export function getTowerDatas(datas) {
    let values = []

    let dayCount = 3
    let zftResult = []
    let hhtResult = []
    let towerOpenResult = []
    let towerCloseResult = []

    let openPriceTowerResult = []
    let lowMinTowerResult = []
    let lowMaxTowerResult = []
    let closePriceTowerResult = []
    for (var i = 0, len = datas.values.length; i < len; i++) {
        // 涨跌态
        let zft = -1
        // 红黑态
        let hht = -1
        // 宝塔开
        let towerOpen = 0
        // 宝塔收
        let towerClose = 0

        // 开盘价宝塔
        let openPriceTower = 0
        // 最高价宝塔
        let lowMinTower = 0
        // 最低价宝塔
        let lowMaxTower = 0
        // 收盘价宝塔
        let closePriceTower = 0
        
        // 当天收盘价
        let nowClose = datas.values[i][1]
        if (i > 0) {
            // 前一天收盘价
            let previousClose = datas.values[i - 1][1]
            // --- 涨跌态
            if (nowClose >= previousClose) {
                zft = 1
            } else {
                zft = -1
            }
            //--- 红黑态
            // 前一天红黑态
            let previousHht = hhtResult[i-1]
            // 获取前3天的最低价与最高价
            let previousLows = []
            let previousHigh = []
            for (var j = 1; j <= dayCount && i >= j; j++) {
                previousLows.push(datas.values[i - j][2])
                previousHigh.push(datas.values[i - j][3])
            } 
            // 求previousLows的最小值
            // lowMinTower = Math.min(previousLows)
            // https://aotu.io/notes/2016/04/14/js-reduce/index.html
            lowMinTower = previousLows.reduce((pre, cur) => pre < cur ? pre : cur)

            // 求previousHigh的最大值
            // lowMaxTower = Math.max(previousHigh)
            lowMaxTower = previousHigh.reduce((pre,cur) => pre>cur?pre:cur)
            if((previousHht >= 0 && nowClose >= lowMinTower) ||
                (previousHht == -1 && nowClose >= lowMaxTower)) {
                hht = 1
            } else {
                hht = -1
            }

            // ---宝塔开
            let previousTowerOpen = towerOpenResult[i-1]
            let previousTowerClose = towerCloseResult[i-1]
            if((previousHht == 1 && zft  == -1 && nowClose < lowMinTower && previousTowerOpen > previousTowerClose) ||
                (previousHht == -1 && zft  == 1 && nowClose > lowMaxTower && previousTowerOpen < previousTowerClose)) {
                towerOpen = previousTowerOpen
            } else {
                towerOpen = previousTowerClose
            }
        }
        // --- 宝塔收
        towerClose = nowClose

        // --- 开盘价宝塔
        if((hht == 1 && towerClose < towerOpen) ||
            (hht == -1 && towerClose > towerOpen)) {
            openPriceTower = towerClose
        } else {
            openPriceTower = towerOpen
        }
        // --- 收盘价宝塔
        // let closePriceTower = 0
        if(openPriceTower == towerClose) {
            closePriceTower = towerOpen
        } else {
            closePriceTower = towerClose
        }

        zftResult.push(zft)
        hhtResult.push(hht)
        towerOpenResult.push(towerOpen)
        towerCloseResult.push(towerClose)
        openPriceTowerResult.push(openPriceTower)
        lowMinTowerResult.push(lowMinTower)
        lowMaxTowerResult.push(lowMaxTower)
        closePriceTowerResult.push(closePriceTower)
        // opening, closing, lowest, highest, vol
        // 開盤價_寶塔	收盤價_寶塔 最低價_寶塔	最高價_寶塔	
        // values.push([openPriceTower, closePriceTower, lowMaxTower, lowMinTower, 0])
        values.push([openPriceTower, closePriceTower, openPriceTower, closePriceTower, 0])
    }
    return values;
}

// 单独抽出收盘价
export function getCloses(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][1])
    }
    return result;
}

// 单独抽出最低
export function getLows(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][2])
    }
    return result;
}

// 单独抽出最高
export function getHighs(datas) {
    var result = [];
    for (var i = 0, len = datas.values.length; i < len; i++) {
        result.push(datas.values[i][3])
    }
    return result;
}

// 布林通道中的upper
export function getBollUppers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].upper)
    }
    return result;
}

// 布林通道中的middle
export function getBollMiddles(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].middle)
    }
    return result;
}

// 布林通道中的lower
export function getBollLowers(bolls) {
    var result = [];
    for (var i = 0, len = bolls.length; i < len; i++) {
        result.push(bolls[i].lower)
    }
    return result;
}

// DMI中的adx
export function getDmiAdxs(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].adx)
    }
    return result;
}

// DMI中的mdi
export function getDmiMdis(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].mdi)
    }
    return result;
}

// DMI中的pdi
export function getDmipdis(dmis) {
    var result = [];
    for (var i = 0, len = dmis.length; i < len; i++) {
        result.push(dmis[i].pdi)
    }
    return result;
}

// 取数据中的最近RECENT_DATE数据
export function getSlice(datas) {
    let recentDatas = datas.slice(RECENT_DATE);
    let absRecentDate = Math.abs(RECENT_DATE)
    if (recentDatas.length < absRecentDate) {
        return leftPad(recentDatas, absRecentDate - recentDatas.length, '-')
    }
    return recentDatas
}

export function leftPad(arrays, pad_length, pad_string) {
    for (let i = 0; i < pad_length; i++) {
        arrays.unshift(pad_string)
    }
    return arrays
}

/* export function getSeriesIndex(tooltipDatas, index) {
    for (var i = 0, len = tooltipDatas.length; i < len; i++) {
        if(tooltipDatas[i].seriesIndex == index) {
            return tooltipDatas[i];
        }
    }
    return null;
} */

