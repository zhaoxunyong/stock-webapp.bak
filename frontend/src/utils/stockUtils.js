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
// DMI
export function getDmis(datas) {
    const dayCount = 14
    
    let trResult = []
    let dmUpResult = []
    let dmDownResult = []
    let dxResult = []

    let tr14Result = []
    let dmUp14Result = []
    let dmDown14Result = []
    let diUp14Result = []
    let diDown14Result = []
    let adx14Result = []
    let adxr14Result = []

    for (var i = 0, len = datas.values.length; i < len; i++) {
        // TR
        let tr = 0
        let dmUp = 0
        let dmDown = 0
        let dx = 0

        let tr14 = 0
        let dmUp14 = 0
        let dmDown14 = 0
        let diUp14 = 0
        let diDown14 = 0
        let adx14 = 0
        let adxr14 = 0
        if (i > 0) {
            // 前一天收盘价
            let lowest = datas.values[i][2]
            let highest = datas.values[i][3]
            let previousClose = datas.values[i - 1][1]
            // TR值(True Range)波動值 = MAX(最高-最低, ABS(最高-昨收), ABS(最低-昨收))
            let t1 = highest-lowest
            let t2 = Math.abs(highest-previousClose)
            let t3 = Math.abs(lowest-previousClose)
            tr = [t1, t2, t3].reduce((pre,cur) => pre>cur?pre:cur)
            // TR14 = 前一日TR14 × 13/14 + 今日TR × 1/14
            let previousTr14 = tr14Result[i-1]
            tr14 = previousTr14 * (dayCount-1) / dayCount + tr / dayCount
            // +DM = 最高 - 昨高
            // -DM = 昨低 - 最低
            // If +DM > -DM And +DM > 0 Then +DM = +DM Else +DM = 0
            // If +DM < -DM And -DM > 0 Then -DM = -DM Else -DM = 0
            let previousLowest = datas.values[i - 1][2]
            let previousHigh = datas.values[i - 1][3]
            dmUp = highest - previousHigh
            dmDown = previousLowest - lowest
            if(!(dmUp > dmDown && dmUp > 0)) {
                dmUp = 0
            }
            if(!(dmUp < dmDown && dmDown > 0)) {
                dmDown = 0
            }
            // +DM14 = 前一日+DM14 × 13/14 + +DM × 1/14
            // -DM14 = 前一日-DM14 × 13/14 + -DM × 1/14
            let previousDmUp14 = dmUp14Result[i-1]
            let previousDmDown14 = dmDown14Result[i-1]
            dmUp14 = previousDmUp14 * (dayCount-1) / dayCount + dmUp / dayCount
            dmDown14 = previousDmDown14 * (dayCount-1) / dayCount + dmDown / dayCount
            // console.log('previousDmUp14->'+previousDmUp14+"/dmUp->"+dmUp+"/dmUp14->"+dmUp14)
            // console.log('previousDmDown14->'+previousDmDown14+"/dmDown->"+dmDown+"/dmDown14->"+dmDown14)
            // DI
            // +DI14 = +DM14 ÷ TR14 × 100
            // -DI14 = -DM14 ÷ TR14 × 100
            diUp14 = dmUp14 / tr14 * 100
            diDown14 = dmDown14 / tr14 * 100

            // DX
            // DX = ABS(+DI14 - -DI14 ) ÷ (+DI14 + -DI14) × 100
            if(diUp14 + diDown14 > 0) {
                dx = Math.abs(diUp14 - diDown14) / (diUp14 + diDown14) * 100
            } else {
                dx = 0
            }

            // ADX14
            // ADX14 = 前一日ADX14 × 13/14 + 今日DX × 1/14
            let previousAdx14 = adx14Result[i-1]
            adx14 = previousAdx14 * (dayCount-1) / dayCount + dx / dayCount

            // ADXR14(没用到)
            // (当日ADX14 + 第前14日ADX14) / 2
            let before14Adx = (i-dayCount) >=0 ? adx14Result[i-dayCount] : 0
            adxr14 = (adx14 + before14Adx) / 2
        }

        trResult.push(tr)
        dmUpResult.push(dmUp)
        dmDownResult.push(dmDown)
        dxResult.push(dx)
    
        tr14Result.push(tr14)
        dmUp14Result.push(dmUp14)
        dmDown14Result.push(dmDown14)
        // console.log("dmUp14Result push++++>"+dmUp14)
        // console.log("dmDown14Result push++++>"+dmDown14)
        // console.log("dmUp14Result++++>"+dmUp14Result)
        // console.log("dmDown14Result++++>"+dmDown14Result)
        diUp14Result.push(diUp14)
        diDown14Result.push(diDown14)
        adx14Result.push(adx14)
        adxr14Result.push(adxr14)
    }
    // console.log("tr14Result->"+tr14Result)
    // console.log("dmUpResult->"+dmUpResult)
    // console.log("dmDownResult->"+dmDownResult)
    // console.log("dmUp14Result->"+dmUp14Result)
    // console.log("dmDown14Result->"+dmDown14Result)
    // console.log("dxResult->"+dxResult)
    // console.log("diUp14Result->"+diUp14Result)
    // console.log("diDown14Result->"+diDown14Result)
    // console.log("adx14Result->"+adx14Result)
    // console.log("adxr14Result->"+adxr14Result)
    return {
        trResult: trResult,
        tr14Result: tr14Result,
        dmUpResult: dmUpResult,
        dmDownResult: dmDownResult,
        dmUp14Result: dmUp14Result,
        dmDown14Result: dmDown14Result,
        dxResult: dxResult,
        diUp14Result: diUp14Result,
        diDown14Result: diDown14Result,
        adx14Result: adx14Result,
        adxr14Result: adxr14Result
    }
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

/* // DMI中的adx
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
} */

// 取数据中的最近RECENT_DATE数据
export function getSlice(datas) {
    let recentDatas = datas.slice(RECENT_DATE)
    let absRecentDate = Math.abs(RECENT_DATE)
    if (recentDatas.length < absRecentDate) {
        return leftPad(recentDatas, absRecentDate - recentDatas.length, '-')
    }
    return recentDatas
}

export function getDisplaySlice(datas1, start, end) {
    let datas = getSlice(datas1)
    let absRecentDate = Math.abs(RECENT_DATE)
    let recentDatas = datas.slice(start, end)
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

