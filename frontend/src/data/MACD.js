// https://github.com/fshlny/MACD-js-implements/blob/master/MACD.md

function EMA(fNow,fPre,N){
    return Number((fNow * 2 / (N + 1) + fPre * (N - 1) / (N + 1)).toFixed(2));
}

// {"tdate":20170103,"c":45760,"t":0,"v":183888,"dtime":"1519","h":46290,"oi":0,"l":45630,"vs":"183888","o":45630,"trddate":20170103}
// rs[i].opening, rs[i].closing, rs[i].lowest, rs[i].highest, rs[i].vol
/**
   * 计算EMA12和EMA26
   */
  function EMASL(points,N){
    if (points == null || points.length <= 0 || N == 0)
      return new Array();
    let result = new Array();
    for(let i=0;i<points.length;i++){
      let candle = points[i];
      let c = points[i][1]
      if(i == 0){//第一天的EMA为收盘价
        result.push(c);
      }else{
        result.push(EMA(c,result[i-1],N));
      }
    }
    return result;
  }

  // DIF = EMA(close,12) - EMA(close,26)
  function DIF(points){
    if (points == null || points.length <= 0)
      return new Array();
    let EMAS = EMASL(points,12);
    let EMAL = EMASL(points,26);
    let DIFs = new Array();
    for(let i =0;i<EMAL.length;i++){
      DIFs.push(formartNumber(EMAS[i]-EMAL[i]));
    }
    return DIFs;
  }

  // DEM = DEA
  function DEA(points,N){
    if (points == null || points.length <= 0)
      return new Array();
    let dif = DIF(points);
    let result = new Array();
    for(let i=0;i<points.length;i++){
      if(i == 0){
        result.push(0);
      }else{
        result.push(EMA(dif[i],result[i-1],N));
      }
    }
    return result;
  }

  // BAR = OSC
  function BAR(points){
    if (points == null || points.length <= 0)
      return new Array();
    let dif = DIF(points);
    let dea = DEA(points,9);
    let bar = new Array();
    for(let i=0;i<points.length;i++){
      bar.push(2*(formartNumber(dif[i]-dea[i])));
    }
    return bar;
  }

  function formartNumber(n){
      return Number(n.toFixed(2));
  }

  export { EMASL, DIF, DEA, BAR } 