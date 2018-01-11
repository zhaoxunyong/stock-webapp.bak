package org.stock.fetch.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stock.fetch.constant.BuyTypeEnum;
import org.stock.fetch.constant.StockTypeEnum;
import org.stock.fetch.dao.StockDailyTransactionsMapper;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockHistoryMapper;
import org.stock.fetch.dao.StockMyDataMapper;
import org.stock.fetch.dao.StockTypeMapper;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockType;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.exception.BusinessException;
import com.aeasycredit.commons.lang.exception.ParameterException;
import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.aeasycredit.commons.lang.utils.DatesUtils;
import com.aeasycredit.commons.poi.excel.ExcelUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

/**
 * https://www.cnyes.com/twstock/ps_historyprice/2881.htm
 * https://tw.stock.yahoo.com/h/getclass.php
 * https://github.com/andredumas/techan.js/wiki/Gallery
 */
@Service
public class FetchServiceImpl implements FetchService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final static String ROOT_URL = "https://tw.stock.yahoo.com";
    
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    
    @Autowired
    private StockTypeMapper stockTypeMapper;
    
    @Autowired
    private StockDataMapper stockDataMapper;
    
    @Autowired
    private StockMyDataMapper stockMyDataMapper;
    
    @Autowired
    private StockDailyTransactionsMapper stockDailyTransactionsMapper;
    

    @Override
    @Transactional
    public void fetchAll() throws Exception {
        // 上市
        List<StockType> marketTypes = fetchKinds(StockTypeEnum.MARKET);
        fetchStocks(marketTypes);
        // 上柜
        List<StockType> counterTypes = fetchKinds(StockTypeEnum.COUNTER);
        fetchStocks(counterTypes);
        // 電子行業
        List<StockType> electronicTypes = fetchKinds(StockTypeEnum.ELECTRONIC);
        fetchStocks(electronicTypes);
        // 概念
        List<StockType> conceptTypes = fetchKinds(StockTypeEnum.CONCEPT);
        fetchStocks(conceptTypes);
        // 集團
        List<StockType> groupTypes = fetchKinds(StockTypeEnum.GROUP);
        fetchStocks(groupTypes);
    }

    @Override
    @Transactional
    public void fetchDetail() throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
                // 
                /*HtmlPage page = webClient.getPage("https://www.cmoney.tw/finance/f00026.aspx?s="+stockMyData.getNo());
                HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'細產業別')]").get(0);
                DomElement domElement = htmlElement.getNextElementSibling();
                String kinds = domElement.asText();
                System.out.println("kinds--->"+kinds);*/
                
                // 細產類別
                String kinds = "";
                try {
                    String detailUrl = ROOT_URL + "/d/s/company_"+stockMyData.getNo().replaceAll("[A-Z]+$", "")+".html";
                    HtmlPage page = webClient.getPage(detailUrl);
                    HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'產業類別')]").get(0);
                    DomElement domElement = htmlElement.getNextElementSibling();
                    kinds = domElement.asText();
                    if(StringUtils.isNotBlank(kinds)) {
                        stockMyData.setKinds(kinds);
                        stockMyDataMapper.updateByPrimaryKey(stockMyData);
                    }
                } catch(Exception e) {
                    logger.warn(e.getMessage());
                }
            }
        }
    }
    
    private List<StockType> fetchKinds(StockTypeEnum stockTypeEnum) throws Exception {
        String eleId = "";
        switch(stockTypeEnum) {
            case MARKET:
                eleId = "table1";
                break;
            case COUNTER:
                eleId = "table3";
                break;
            case ELECTRONIC:
                eleId = "table5";
                break;
            case CONCEPT:
                eleId = "table7";
                break;
            case GROUP:
                eleId = "table9";
                break;
            default:
                throw new ParameterException("stockTypeEnum must be not empty!");
        }
        HtmlPage page = webClient.getPage(ROOT_URL + "/h/getclass.php");
        //        HtmlElement element = page.getHtmlElementById("table7");
        DomElement element = page.getElementById(eleId);
        DomElement ele = element.getNextElementSibling();
        List<HtmlElement> trs = ele.getElementsByTagName("tr");
        List<StockType> stockTypes = Lists.newArrayList();
//        System.out.println("2===>" + trs);
        if (trs != null && !trs.isEmpty()) {
            Date date = new Date();
            for (HtmlElement tr : trs) {
//                System.out.println("3===>" + tr.asXml());
                List<HtmlElement> tds = tr.getElementsByTagName("td");
                if (tds != null && !tds.isEmpty()) {
                    for (HtmlElement td : tds) {
//                        System.out.println("td=" + td.asXml());
                        List<HtmlElement> aNodes = td.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(0);
                        String url = ROOT_URL + aElement.getAttribute("href");
//                        String id = StringUtils.substringAfterLast(href, " ");
                        String name = td.asText();
                        System.out.println("url=" + url + "/name=" + name);
                        StockType stockType = new StockType();
                        stockType.setId(IdUtils.genLongId());
                        stockType.setName(name);
                        stockType.setType(stockTypeEnum.getType());
                        stockType.setUrl(url);
                        stockType.setStatus(true);
                        stockType.setCreateDate(date);
                        stockTypes.add(stockType);
//                        stockTypeConceptMapper.deleteByName(name);
                        if(stockTypeMapper.selectByName(name, stockTypeEnum.getType()) == null) {
                            stockTypeMapper.insert(stockType);
                        }
                    }
                }
            }
        }
        return stockTypes;
    }
    

    
    private void fetchStocks(List<StockType> stockTypes) throws Exception {
        Date date = new Date();
        if(stockTypes!=null && !stockTypes.isEmpty()) {
            for(StockType stockType : stockTypes) {
                HtmlPage nextPage = webClient.getPage(stockType.getUrl());
                List<?> domNodes = nextPage.querySelectorAll("table.yui-text-left tbody tr");
                if(domNodes==null || domNodes.size() < 3) {
                    System.out.print("name="+stockType.getName());
                    System.out.println("/url="+stockType.getUrl());
                    continue;
                }
                HtmlElement domNode = (HtmlElement) domNodes.get(2);
//                System.out.println("domNode===>"+domNode.asXml());
                
                List<DomNode> trDomNodes = domNode.querySelectorAll("table table tbody tr");//.get(2);
                if(trDomNodes!=null && !trDomNodes.isEmpty()) {
                    for(int i=2;i<trDomNodes.size();i++) {
                        HtmlElement trDomNode = (HtmlElement) trDomNodes.get(i);
//                        System.out.println("trDomNode="+trDomNode.asXml());
//                      System.out.println("trDomNode===>"+trDomNode);
                        List<HtmlElement> nextTds = trDomNode.getElementsByTagName("td");
                        HtmlElement tdElement = nextTds.get(1);
//                        System.out.println("tdElement===>"+tdElement.asXml());
                        
                        List<HtmlElement> aNodes = tdElement.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(0);
//                        System.out.println("aElement===>"+aElement.asXml());
                        String url = ROOT_URL + aElement.getAttribute("href");
//                        String id = StringUtils.substringAfterLast(href, " ");
                        String value = tdElement.asText();
                        String no = StringUtils.substringBefore(value, " ");
                        String company = StringUtils.substringAfter(value, " ").replaceAll("\r\n", "");
                        
                        // tx_price closing highest lowest
                        HtmlElement txPriceElement = nextTds.get(3);
                        HtmlElement closingElement = nextTds.get(8);
                        HtmlElement highestElement = nextTds.get(10);
                        HtmlElement lowestElement = nextTds.get(11);
                        
//                        System.out.println("url=" + url + "/no=" + no + "/company=" + company);
                        /*
                        // 細產類別
                        String kinds = "";
                        try {
                            String detailUrl = ROOT_URL + "/d/s/company_"+no.replaceAll("[A-Z]+$", "")+".html";
                            HtmlPage page = webClient.getPage(detailUrl);
                            HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'產業類別')]").get(0);
                            DomElement domElement = htmlElement.getNextElementSibling();
                            kinds = domElement.asText();
                        } catch(Exception e) {
                            logger.warn(e.getMessage());
                        }*/
                        if(stockType.getType().equals(StockTypeEnum.MARKET.getType()) || stockType.getType().equals(StockTypeEnum.COUNTER.getType())) {
                            StockData stockData = new StockData();
                            stockData.setId(IdUtils.genLongId());
                            stockData.setNo(no);
                            stockData.setCompany(company);
                            stockData.setCreateDate(date);
                            stockData.setTypeName(stockType.getName());
                            stockData.setUrl(url);
                            stockData.setType(stockType.getType());
                            stockData.setTxPrice(toBigDecimal(txPriceElement.asText()));
                            stockData.setClosing(toBigDecimal(closingElement.asText()));
                            stockData.setHighest(toBigDecimal(highestElement.asText()));
                            stockData.setLowest(toBigDecimal(lowestElement.asText()));
                            stockDataMapper.deleteByNo(no);
                            stockDataMapper.insert(stockData);
                        } else {
                            StockData stockData = stockDataMapper.selectByNo(no);
                            if(stockData == null) {
                                // not exist, insert
                                stockData = new StockData();
                                stockData.setId(IdUtils.genLongId());
                                stockData.setNo(no);
                                stockData.setCompany(company);
                                stockData.setCreateDate(date);
                                stockData.setUrl(url);
                                stockData.setType(stockType.getType());
                                stockDataMapper.insert(stockData);
                            } else {
                                // exist, update
                                if(stockType.getType().equals(StockTypeEnum.CONCEPT.getType())) {
                                    stockData.setConcepts(stockType.getName());
                                } else if(stockType.getType().equals(StockTypeEnum.GROUP.getType())) {
                                    stockData.setGroups(stockType.getName());
                                } else if(stockType.getType().equals(StockTypeEnum.ELECTRONIC.getType())) {
                                    stockData.setElectronics(stockType.getName());
                                }
                                stockDataMapper.updateByPrimaryKey(stockData);
                            }
                        }
                    }
                }
            }
        }
        
    }

    /**
     * 日期格式為：yyyy/MM/dd
     */
    @Override
    @Transactional
    public void fetchHistory(String no, String startDate, String endDate) throws Exception {
        StockData stockData = stockDataMapper.selectByNo(no);
        if(stockData == null) {
            throw new BusinessException("Not found stock data by no: " + no);
        }
        long stockId = stockData.getId();
        HtmlPage page = webClient.getPage("https://www.cnyes.com/twstock/ps_historyprice.aspx?code="+stockData.getNo());
        page.getElementById("ctl00_ContentPlaceHolder1_startText").setAttribute("value", startDate);  
        page.getElementById("ctl00_ContentPlaceHolder1_endText").setAttribute("value", endDate);  
        HtmlForm form = page.getHtmlElementById("aspnetForm");
        HtmlPage page2 = form.getOneHtmlElementByAttribute("input", "id", "ctl00_ContentPlaceHolder1_submitBut").click();
        HtmlForm form2 = page2.getHtmlElementById("aspnetForm");
        HtmlElement element = form2.getOneHtmlElementByAttribute("table", "enableviewstate", "false");
//        System.out.println("1===>"+element.asXml());
        List<HtmlElement> trs = element.getElementsByTagName("tr");
//        System.out.println("2===>"+trs);
        if(trs!=null && !trs.isEmpty()) {
            stockHistoryMapper.deleteByDate(stockId, DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
            for(HtmlElement tr : trs) {
//                System.out.println("3===>"+tr.asXml());
                List<HtmlElement> tds = tr.getElementsByTagName("td");
                if(tds!=null && !tds.isEmpty()) {
                    int i=0;
                    StockHistory stockHistory = new StockHistory();
                    stockHistory.setId(IdUtils.genLongId());
                    stockHistory.setStockId(stockId);
                    stockHistory.setCreateDate(new Date());
                    for(HtmlElement td : tds) {
//                        System.out.println("===>"+td.asXml());
                        switch(i) {
                            case 0:
                              // 日期
                                stockHistory.setDate(DatesUtils.YYMMDD2.toDate(td.asText()));
                            break;
                            case 1:
                              // 開盤
                                stockHistory.setOpening(new BigDecimal(td.asText()));
                              break;
                            case 2:
                                // 最高    
                                stockHistory.setHighest(new BigDecimal(td.asText()));
                                break;
                            case 3:
                                // 最低    
                                stockHistory.setLowest(new BigDecimal(td.asText()));
                                break;
                            case 4:
                                // 收盤    
                                stockHistory.setClosing(new BigDecimal(td.asText()));
                                break;
                            case 5:
                                // 漲跌    
                                stockHistory.setUpsDowns(new BigDecimal(td.asText()));
                                break;
                            case 6:
                                // 漲%    
                                stockHistory.setRiseRate(td.asText());
                                break;
                            case 7:
                                // 成交量    
                                stockHistory.setVol(td.asText());
                                break;
                            case 8:
                                // 成交金額    
                                stockHistory.setAmount(td.asText());
                                break;
                            case 9:
                                // 本益比
                                stockHistory.setPer(td.asText());
                                break;
                        }
                        i++;
                    }
                    System.out.println("===>"+stockHistory);
                    stockHistoryMapper.insert(stockHistory);
                }
            }
        }
    }

    @Override
    public List<StockHistory> select(Date startDate, Date endDate) {
        return stockHistoryMapper.select(startDate, endDate);
    }

    @Override
    @Transactional
    public void importBydailyTransactions(String excelFile) throws IOException {
        Date date = new Date();
        Table<Integer, String, Object> table = ExcelUtils.readExcel2table(excelFile, 1, 1);
//        System.out.println("all=>"+table);
        Set<Integer> rowKeys = table.rowKeySet();
        System.out.println("rowKeys size=>"+rowKeys.size());
//        List<StockDailyTransactions> dailyTxs = Lists.newArrayList();
        for(Integer rowKey : rowKeys) {
            Map<String, Object> rows = table.row(rowKey);
//            String columnKey = rowKey.getColumnKey();
//            String value = set.getValue();
//            table.get(rowKey, columnKey);
//            System.out.println(rows);
            
            StockDailyTransactions tx = new StockDailyTransactions();
            String no = rows.get("代號").toString().replaceAll("\\.0", "");
            StockData stockData = stockDataMapper.selectByNo(no);
            if(stockData == null) {
                throw new BusinessException("Not found stock data by no: " + no);
            }

            String txKind = rows.get("種類").toString();
            if(txKind.indexOf("買") != -1) {
                tx.setTxKind(BuyTypeEnum.BUY.getType());
            } else if(txKind.indexOf("賣") != -1) {
                tx.setTxKind(BuyTypeEnum.SELL.getType());
            }
            
            tx.setId(IdUtils.genLongId());
            tx.setStockId(stockData.getId());
            tx.setCreateDate(date);
//            tx.setUpdateDate(updateDate);

            tx.setQuantity(toInteger(rows.get("數量").toString()));
            
            tx.setAccountNo(rows.get("交易帳號").toString());
            tx.setCashDeposit(toBigDecimal(rows.get("資自備款/券保證金").toString()));
            tx.setCurrency(rows.get("幣別").toString());
            tx.setCustomerCharge(toBigDecimal(rows.get("客戶淨收付").toString()));
            tx.setDebFee(toBigDecimal(rows.get("借券費").toString()));
            tx.setFee(toBigDecimal(rows.get("手續費").toString()));
            tx.setFinancingAmount(toBigDecimal(rows.get("融資金額/券擔保品").toString()));
            tx.setHoldingCost(toBigDecimal(rows.get("持有成本").toString()));
            tx.setInterest(toBigDecimal(rows.get("利息").toString()));
//            tx.setName(rows.get("商品名稱").toString());
            tx.setPayRate(toBigDecimal(rows.get("報酬率(%)").toString()));
            tx.setProfit(toBigDecimal(rows.get("損益").toString()));
            tx.setTxAmount(toBigDecimal(rows.get("成交金額").toString()));
            tx.setTxDate((Date)rows.get("成交日期"));
            tx.setTxPrice(toBigDecimal(rows.get("成交價").toString()));
            tx.setTxTallage(toBigDecimal(rows.get("交易稅").toString()));
            tx.setZsTallage(toBigDecimal(rows.get("證所稅").toString()));
            System.out.println(tx);
            stockDailyTransactionsMapper.delete(tx.getStockId(), tx.getTxDate(), tx.getTxKind(), tx.getTxPrice(), tx.getQuantity());
            stockDailyTransactionsMapper.insert(tx);
            
            StockMyData stockMyData = stockMyDataMapper.selectByStockId(tx.getStockId());
            if(stockMyData == null) {
                stockMyData = new StockMyData();
                stockMyData.setId(IdUtils.genLongId());
                stockMyData.setStockId(tx.getStockId());
                stockMyData.setCreateDate(date);
                stockMyData.setStatus(true);
                stockMyDataMapper.insert(stockMyData);
            }
        }
    }
    
    private BigDecimal toBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch(Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    private Integer toInteger(String value) {
        try {
            return (int)Double.parseDouble(value);
        }catch(Exception e) {
            logger.warn(e.getMessage());
            return 0;
        }
    }
    
}
