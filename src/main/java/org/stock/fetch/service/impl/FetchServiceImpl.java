package org.stock.fetch.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stock.fetch.constant.StockTypeEnum;
import org.stock.fetch.dao.StockDailyTransactionsMapper;
import org.stock.fetch.dao.StockHistoryMapper;
import org.stock.fetch.dao.StockTypeMapper;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockType;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.exception.ParameterException;
import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.aeasycredit.commons.lang.utils.DatesUtils;
import com.aeasycredit.commons.poi.excel.ExcelUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
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
    
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    
    @Autowired
    private StockTypeMapper stockTypeMapper;
    
    @Autowired
    private StockDailyTransactionsMapper stockDailyTransactionsMapper;
    

    @Override
    @Transactional
    public void fetchAll() throws Exception {
        
    }
    

    @Override
    @Transactional
    public void fetchAllKinds() throws Exception {
        fetchKinds(StockTypeEnum.CONCEPT);
        fetchKinds(StockTypeEnum.GROUP);
    }
    
    @Transactional
    private void fetchKinds(StockTypeEnum stockTypeEnum) throws Exception {
        String eleId = "";
        switch(stockTypeEnum) {
            case CONCEPT:
                eleId = "table7";
                break;
            case GROUP:
                eleId = "table9";
                break;
            default:
                throw new ParameterException("stockTypeEnum must be not empty!");
        }
        String rootUrl = "https://tw.stock.yahoo.com";
        HtmlPage page = webClient.getPage(rootUrl + "/h/getclass.php");
        //        HtmlElement element = page.getHtmlElementById("table7");
        DomElement element = page.getElementById(eleId);
        DomElement ele = element.getNextElementSibling();
        List<HtmlElement> trs = ele.getElementsByTagName("tr");
//        System.out.println("2===>" + trs);
        if (trs != null && !trs.isEmpty()) {
            Date date = new Date();
            List<StockType> stockTypes = Lists.newArrayList();
            for (HtmlElement tr : trs) {
//                System.out.println("3===>" + tr.asXml());
                List<HtmlElement> tds = tr.getElementsByTagName("td");
                if (tds != null && !tds.isEmpty()) {
                    for (HtmlElement td : tds) {
//                        System.out.println("td=" + td.asXml());
                        List<HtmlElement> aNodes = td.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(0);
                        String href = rootUrl + aElement.getAttribute("href");
//                        String id = StringUtils.substringAfterLast(href, " ");
                        String name = td.asText();
                        System.out.println("href=" + href + "/name=" + name);
                        StockType stockType = new StockType();
                        stockType.setId(IdUtils.genLongId());
                        stockType.setName(name);
                        stockType.setType(stockTypeEnum.getType());
                        stockType.setStatus(true);
                        stockType.setCreateDate(date);
                        stockTypes.add(stockType);
                        /*HtmlPage nextPage = webClient.getPage(href);
                        HtmlElement domNode = (HtmlElement) nextPage.querySelectorAll("table.yui-text-left tbody tr").get(2);
//                        System.out.println("domNode===>"+domNode.asXml());
                        
                        HtmlElement trDomNode = (HtmlElement) domNode.querySelectorAll("table table tbody tr").get(2);
//                        System.out.println("trDomNode===>"+trDomNode);
                        List<HtmlElement> nextTds = trDomNode.getElementsByTagName("td");
                        HtmlElement tdElement = nextTds.get(1);
                        System.out.println("tdElement===>"+tdElement.asXml());*/
//                        stockTypeConceptMapper.deleteByName(name);
                        if(stockTypeMapper.selectByName(name, stockTypeEnum.getType()) == null) {
                            stockTypeMapper.insert(stockType);
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
    public void fetch(long stockId, String startDate, String endDate) throws Exception {
        HtmlPage page = webClient.getPage("https://www.cnyes.com/twstock/ps_historyprice.aspx?code="+stockId);
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
                                stockHistory.setOpening(td.asText());
                              break;
                            case 2:
                                // 最高    
                                stockHistory.setHighest(td.asText());
                                break;
                            case 3:
                                // 最低    
                                stockHistory.setLowest(td.asText());
                                break;
                            case 4:
                                // 收盤    
                                stockHistory.setClosing(td.asText());
                                break;
                            case 5:
                                // 漲跌    
                                stockHistory.setUpsDowns(td.asText());
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
            
            tx.setId(IdUtils.genLongId());
            tx.setStockId(123L);
            tx.setCreateDate(new Date());
//            tx.setUpdateDate(updateDate);

            tx.setQuantity(toInteger(rows.get("數量").toString()));
            tx.setNo(toInteger(rows.get("代號").toString()));
            
            tx.setAccountNo(rows.get("交易帳號").toString());
            tx.setCashDeposit(toBigDecimal(rows.get("資自備款/券保證金").toString()));
            tx.setCurrency(rows.get("幣別").toString());
            tx.setCustomerCharge(toBigDecimal(rows.get("客戶淨收付").toString()));
            tx.setDebFee(toBigDecimal(rows.get("借券費").toString()));
            tx.setFee(toBigDecimal(rows.get("手續費").toString()));
            tx.setFinancingAmount(toBigDecimal(rows.get("融資金額/券擔保品").toString()));
            tx.setHoldingCost(toBigDecimal(rows.get("持有成本").toString()));
            tx.setInterest(toBigDecimal(rows.get("利息").toString()));
            tx.setName(rows.get("商品名稱").toString());
            tx.setPayRate(toBigDecimal(rows.get("報酬率(%)").toString()));
            tx.setProfit(toBigDecimal(rows.get("損益").toString()));
            tx.setTxAmount(toBigDecimal(rows.get("成交金額").toString()));
            tx.setTxDate((Date)rows.get("成交日期"));
            tx.setTxKind(rows.get("種類").toString());
            tx.setTxPrice(toBigDecimal(rows.get("成交價").toString()));
            tx.setTxTallage(toBigDecimal(rows.get("交易稅").toString()));
            tx.setZsTallage(toBigDecimal(rows.get("證所稅").toString()));
            System.out.println(tx);
            stockDailyTransactionsMapper.delete(tx.getNo(), tx.getTxDate(), tx.getTxKind(), tx.getTxPrice(), tx.getQuantity());
            stockDailyTransactionsMapper.insert(tx);
        }
    }
    
    private BigDecimal toBigDecimal(String value) {
        if(!"-".equals(value)) {
            return BigDecimal.valueOf(Double.parseDouble(value));
        }
        return BigDecimal.ZERO;
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
