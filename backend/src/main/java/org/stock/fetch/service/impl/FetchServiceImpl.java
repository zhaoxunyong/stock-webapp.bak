package org.stock.fetch.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stock.fetch.constant.BuyTypeEnum;
import org.stock.fetch.constant.StockHistoryEnum;
import org.stock.fetch.constant.StockHistoryErrorEnum;
import org.stock.fetch.constant.StockTypeEnum;
import org.stock.fetch.dao.StockDailyTransactionsHistoryMapper;
import org.stock.fetch.dao.StockDailyTransactionsMapper;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockHistoryDailyMapper;
import org.stock.fetch.dao.StockHistoryErrorMapper;
import org.stock.fetch.dao.StockHistoryMapper;
import org.stock.fetch.dao.StockImportantNewsMapper;
import org.stock.fetch.dao.StockMyDataMapper;
import org.stock.fetch.dao.StockMyStoreMapper;
import org.stock.fetch.dao.StockNewsMapper;
import org.stock.fetch.dao.StockTypeMapper;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockDailyTransactionsHistory;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockHistoryDaily;
import org.stock.fetch.model.StockHistoryError;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockType;
import org.stock.fetch.service.FetchService;
import org.stock.utils.FileMd5Utils;
import org.stock.utils.MyDateUtils;
import org.stock.webclient.WebClientCallBack;
import org.stock.webclient.WebClientUtils;

import com.aeasycredit.commons.lang.exception.BusinessException;
import com.aeasycredit.commons.lang.exception.ParameterException;
import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.aeasycredit.commons.lang.utils.DatesUtils;
import com.aeasycredit.commons.poi.excel.ExcelUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

/**
 * https://www.cnyes.com/twstock/ps_historyprice/2881.htm
 * https://tw.stock.yahoo.com/h/getclass.php
 * https://github.com/andredumas/techan.js/wiki/Gallery
 * out of memory: https://blog.csdn.net/qq43599939/article/details/68958676
 */
@Service
public class FetchServiceImpl implements FetchService {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final static Integer MAX_ERR_COUNT = 10;
    
    private final static String ROOT_URL = "https://tw.stock.yahoo.com";
    
    // @Autowired
    // private WebClient webClient;
    
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    
    @Autowired
    private StockHistoryErrorMapper stockHistoryErrorMapper;
    
    @Autowired
    private StockTypeMapper stockTypeMapper;
    
    @Autowired
    private StockDataMapper stockDataMapper;
    
    @Autowired
    private StockMyDataMapper stockMyDataMapper;
    
    @Autowired
    private StockMyStoreMapper stockMyStoreMapper;
    
    @Autowired
    private StockNewsMapper stockNewsMapper;
    
    @Autowired
    private StockImportantNewsMapper stockImportantNewsMapper;
    
    @Autowired
    private StockDailyTransactionsHistoryMapper stockDailyTransactionsHistoryMapper;
    
    @Autowired
    private StockDailyTransactionsMapper stockDailyTransactionsMapper;
    
    @Autowired
    private StockHistoryDailyMapper stockHistoryDailyMapper;
    
    @Override
    @Transactional
    public void fetchAllMyStock() throws Exception {
        long s = System.currentTimeMillis();
        // 先将所有的股票status修改为0
        stockDataMapper.updateAllStatus(false);
        // 刪除所有的stock_type,stock_type為臨時表，只在導入所有股票時臨時使用
        stockTypeMapper.deleteAll();
        // 上市
        List<StockType> marketTypes = fetchNewsKinds(StockTypeEnum.MARKET);
        fetchNewStocks(marketTypes);
        // 上柜
        List<StockType> counterTypes = fetchNewsKinds(StockTypeEnum.COUNTER);
        fetchNewStocks(counterTypes);
        logger.info("fetchNewsKinds---->"+(System.currentTimeMillis()-s)+"ms.");
        s = System.currentTimeMillis();
        // 電子行業
        List<StockType> electronicTypes = fetchKinds(StockTypeEnum.ELECTRONIC);
        fetchStocks(electronicTypes);
        // 概念
        List<StockType> conceptTypes = fetchKinds(StockTypeEnum.CONCEPT);
        fetchStocks(conceptTypes);
        // 集團
        List<StockType> groupTypes = fetchKinds(StockTypeEnum.GROUP);
        fetchStocks(groupTypes);
        logger.info("fetchKinds---->"+(System.currentTimeMillis()-s)+"ms.");
      
        /*// 上市
//        List<StockType> marketTypes = fetchKinds(StockTypeEnum.MARKET);
//        fetchStocks(marketTypes);
         // 上柜
//        List<StockType> counterTypes = fetchKinds(StockTypeEnum.COUNTER);
        fetchStocks(counterTypes);
        // 電子行業
        List<StockType> electronicTypes = fetchKinds(StockTypeEnum.ELECTRONIC);
        fetchStocks(electronicTypes);
        // 概念
        List<StockType> conceptTypes = fetchKinds(StockTypeEnum.CONCEPT);
        fetchStocks(conceptTypes);
        // 集團
        List<StockType> groupTypes = fetchKinds(StockTypeEnum.GROUP);
        fetchStocks(groupTypes);*/
    }
    
    private boolean skipKinds(String name) {
        if("存託憑證".equals(name)
                || "市認購".equals(name)
                || "市認售".equals(name)
                || "指數類".equals(name)
                || "收益證劵".equals(name)
                
                || "指數類".equals(name)
                || "認購".equals(name)
                || "認售".equals(name)) {
            logger.info("不需要导入: "+name);
            return true;
        } 
        return false;
    }
    
    private boolean skipSocks(String no) {
        if(no.length() == 6) {
            logger.info("股號長度為6位時跳過：" + no);
            return true;
        }
        return false;
    }
    
    private List<StockType> fetchNewsKinds(StockTypeEnum stockTypeEnum) throws Exception {
        return WebClientUtils.process(webClient -> {
            List<StockType> stockTypes = Lists.newArrayList();
            Date date = new Date();
            // 上市
            String url = "";
            DomNode domNode = null;
            if(stockTypeEnum == StockTypeEnum.MARKET) {
                url = ROOT_URL+"/h/kimosel.php?tse=1&cat=%A5b%BE%C9%C5%E9&form=menu&form_id=stock_id&form_name=stock_name&domain=0";
                HtmlPage page = processGuceOathCom(webClient.getPage(url));
                HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'上市')]").get(0);
                domNode = htmlElement.getParentNode().getParentNode();
            } else if(stockTypeEnum == StockTypeEnum.COUNTER) {
                url = ROOT_URL+"/h/kimosel.php?tse=2&cat=%C2d%A5b%BE%C9&form=menu&form_id=stock_id&form_name=stock_name&domain=0";
                HtmlPage page = processGuceOathCom(webClient.getPage(url));
                HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'上市')]").get(0);
                domNode = htmlElement.getParentNode().getParentNode().getParentNode();
            }
          List<DomNode> trDomNodes = domNode.querySelectorAll("tr");
          if(trDomNodes != null && !trDomNodes.isEmpty()) {
              for(DomNode node : trDomNodes) {
                  DomNodeList<DomNode> tdNodes = node.getChildNodes();
                  if(tdNodes != null && !tdNodes.isEmpty()) {
                      for(DomNode tdNode : tdNodes) {
                          if(tdNode != null) {
                              DomNode aNode = tdNode.getFirstChild();
                              if(aNode != null && aNode instanceof HtmlAnchor) {
                                  HtmlAnchor anchor = (HtmlAnchor)aNode;
                                  String name = anchor.asText();
                                  if(stockTypeEnum == StockTypeEnum.MARKET || (stockTypeEnum == StockTypeEnum.COUNTER && !"上市".equals(name))) {
                                        String aHref = anchor.getHrefAttribute();
//                                        logger.info("name--->" + name + "/aHref--->" + aHref);
                                        StockType stockType = new StockType();
                                        stockType.setName(name);
                                        if(!skipKinds(name)) {
                                            stockType.setType(stockTypeEnum.getType());
                                            stockType.setUrl(aHref);
                                            stockType.setStatus(true);
                                            stockType.setCreateDate(date);
                                            stockTypes.add(stockType);
                                            StockType existStockType = stockTypeMapper.selectByName(name, stockTypeEnum.getType());
                                            if(existStockType == null) {
                                                stockType.setId(IdUtils.genLongId());
                                                stockTypeMapper.insert(stockType);
                                            } else {
                                                // 如果存在的话，用原来的id记录
                                                stockType.setId(existStockType.getId());
                                                stockTypeMapper.updateByPrimaryKey(stockType);
                                            }
                                        }
                                      
                                  }
                              }
                          }
                      }
                      
                  }
              }
          }
          return stockTypes;
        });
    }

    private void fetchNewStocks(List<StockType> stockTypes) throws Exception {
        WebClientUtils.process(webClient -> {
            Date date = new Date();
            if(stockTypes!=null && !stockTypes.isEmpty()) {
                for(StockType stockType : stockTypes) {
                    HtmlPage nextPage = processGuceOathCom(webClient.getPage(ROOT_URL+stockType.getUrl()));
                    List<?> formNodes = nextPage.querySelectorAll("form[name=\"stock\"]");
                    if(formNodes==null || formNodes.isEmpty()) {
                        throw new BusinessException("Not found form!");
                    }
                    HtmlElement domNode = (HtmlElement) formNodes.get(0);
//                    logger.info("domNode===>"+domNode.asXml());
                    
                    List<DomNode> tdDomNodes = domNode.querySelectorAll("table tbody tr td table tbody tr td");//.get(2);
                    if(tdDomNodes!=null && !tdDomNodes.isEmpty()) {
                        for(DomNode tdDomNode:tdDomNodes) {
                            String text = tdDomNode.asText().trim();
                            if(StringUtils.isNotBlank(text)) {
                                String no = StringUtils.substringBefore(text, " ");
                                if(!skipSocks(no)) {
                                    String company = StringUtils.substringAfter(text, " ");
//                                  logger.info("no--->"+no+"/"+company);
                                    if(stockType.getType().equals(StockTypeEnum.MARKET.getType()) || stockType.getType().equals(StockTypeEnum.COUNTER.getType())) {
                                        // 如果該股票存在時，一定要復用之前的id，不然所有的其他的記錄關聯id，這樣會有問題
                                        StockData existStockData = stockDataMapper.selectByNo(no);
                                        StockData stockData = new StockData();
                                        stockData.setNo(no);
                                        stockData.setCompany(company);
                                        stockData.setCreateDate(date);
                                        stockData.setTypeName(stockType.getName());
                                        stockData.setType(stockType.getType());
                                        stockData.setStatus(true);
                                        if(existStockData != null) {
                                            stockData.setId(existStockData.getId());
                                            stockDataMapper.updateByPrimaryKey(stockData);
                                        } else {
                                            stockData.setId(IdUtils.genLongId());
//                                            stockDataMapper.deleteByNo(no);
                                            stockDataMapper.insert(stockData);
                                        }
                                        /*stockData.setUrl(url);
                                        stockData.setTxPrice(toBigDecimal(txPriceElement.asText()));
                                        stockData.setClosing(toBigDecimal(closingElement.asText()));
                                        stockData.setHighest(toBigDecimal(highestElement.asText()));
                                        stockData.setLowest(toBigDecimal(lowestElement.asText()));*/
                                    } else {
                                        StockData stockData = stockDataMapper.selectByNo(no);
                                        if(stockData == null) {
                                            // not exist, insert
                                            stockData = new StockData();
                                            stockData.setId(IdUtils.genLongId());
                                            stockData.setNo(no);
                                            stockData.setCompany(company);
                                            stockData.setCreateDate(date);
                                            stockData.setType(stockType.getType());
                                            stockData.setStatus(true);
                                            stockDataMapper.insert(stockData);
//                                            stockData.setUrl(url);
                                        } else {
                                            stockData.setStatus(true);
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
            }
            return null;
        });
        
    }

    /*@Override
    @Transactional
    public void fetchDetail() throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
                // 
                HtmlPage page = webClient.getPage("https://www.cmoney.tw/finance/f00026.aspx?s="+stockMyData.getNo());
                HtmlElement htmlElement = (HtmlElement)page.getByXPath("//*[contains(text(),'細產業別')]").get(0);
                DomElement domElement = htmlElement.getNextElementSibling();
                String kinds = domElement.asText();
                logger.info("kinds--->"+kinds);
                
                // 細產類別
                String kinds = "";
                try {
                    String detailUrl = ROOT_URL + "/d/s/company_"+stockMyData.getNo().replaceAll("[A-Z]+$", "")+".html";
                    HtmlPage page = processGuceOathCom(webClient.getPage(detailUrl));
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
    }*/

    @Override
    @Transactional
    public void fetchLatestNews(StockData stockData) throws Exception {
        StockNews stockNews = stockNewsMapper.getLatestNews(stockData.getId());
        if(stockNews == null) {
            logger.info("fetchLatestNews: {}-{}第一次导入，只导入前4页面", stockData.getNo(), stockData.getCompany());
            for(int i=1;i<=4;i++) {
                this.fetchNews(stockData, i);
            }
        } else {
            Date newDate = stockNews.getNewsDate();
//            logger.info("fetchLatestNews: {}-{}从当前日期导到{}.", stockData.getNo(), stockData.getCompany(), DatesUtils.YYMMDDHHMMSS.toString(newDate));
            int i=1;
            int maxFetchPages = 10;
           //定义标签
//            labelA:
            while(i<=maxFetchPages) {
                List<StockNews> stockNewses =  this.fetchNews(stockData, i);
                if(stockNewses!=null && !stockNewses.isEmpty()) {
                    for(StockNews news : stockNewses) {
                        if(news.getNewsDate().getTime() <= newDate.getTime()) {
                            // 用system.out.println，可以在nohup.out文件中查看
                            // logger.info("fetchLatestNews: "+stockData.getNo()+"-"+stockData.getCompany()+"已经导到了"+DatesUtils.YYMMDDHHMMSS.toString(newDate)+"，无需再导入!");
                            return ;
                        }
                    }
                }
                i++;
            }
        }
    }
    
   private HtmlPage processGuceOathCom(HtmlPage page) {
    //    List<HtmlForm> forms = page.getForms();
//        if (forms != null && !forms.isEmpty()) {
//            for (HtmlForm form : forms) {
//                HtmlElement ele = null;
//                try {
//                    ele = form.getOneHtmlElementByAttribute("input", "name", "agree");
//                } catch (Exception e) {
// //                   e.printStackTrace();
//                }
//                if (ele != null) {
//                    try {
//                     page = ele.click();
//                 } catch (IOException e) {
// //                    e.printStackTrace();
//                 }
//                }
//            }
//        }
       return page;
   }

    @Override
    @Transactional
    public List<StockNews> fetchNews(StockData stockData, int fetchPage) throws Exception {
        return WebClientUtils.process(webClient -> {
            //          StockMyData stockMyData = stockMyDataMapper.selectByStockId(stockId);
            if(stockData == null) {
                throw new ParameterException("stockData must be not empty!");
            }
            String newUrl = ROOT_URL + "/q/h?s="+stockData.getNo().replaceAll("[A-Z]+$", "")+"&pg="+fetchPage;
            HtmlPage page = processGuceOathCom(webClient.getPage(newUrl));
            List<StockNews> stockNewses =  Lists.newArrayList();
            List<?> trDomNodes = page.querySelectorAll("tr table.yui-text-left tbody tr td table tbody tr");
            if(trDomNodes!=null && !trDomNodes.isEmpty()) {
                for(int i=0;i<trDomNodes.size();i=i+2) {
                    HtmlElement trDomNode = (HtmlElement) trDomNodes.get(i);
                    List<HtmlElement> nextTds = trDomNode.getElementsByTagName("td");
                    if(nextTds.size() == 2) {
                        HtmlElement titleDomNode = (HtmlElement) trDomNodes.get(i+1);
                        List<HtmlElement> titleTds = titleDomNode.getElementsByTagName("td");
                        String title = titleTds.get(0).asText().replaceAll("^\\(|\\)$", "");
                        String newsDate = StringUtils.substringBefore(title, " ");
                        String froms = StringUtils.substringAfter(title, " ");
                        
                        HtmlElement td = nextTds.get(1);
                        List<HtmlElement> aNodes = td.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(0);
                        String url = ROOT_URL + aElement.getAttribute("href");
                        String subject = td.asText() + "("+froms +" "+newsDate+")";
                        StockNews stockNews = new StockNews();
                        stockNews.setId(IdUtils.genLongId());
                        stockNews.setFroms(froms);
                        stockNews.setNewsDate(DatesUtils.YYMMDD2.toDate(newsDate));
                        stockNews.setStockId(stockData.getId());
                        stockNews.setSubject(subject);
                        stockNews.setUrl(url);
                        stockNews.setCreateDate(new Date());
                        stockNewses.add(stockNews);
                        stockNewsMapper.deleteByStockNews(stockNews);
//                      if(!checkNewsSubject(subject)) {
                        stockNewsMapper.insert(stockNews);
//                      logger.info(stockNews.toString());
//                      }
                    }
                }
            }
            return stockNewses;

        });
    }

    @Override
    @Transactional
    public void fetchNews(int fetchPage) throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
                this.fetchNews(stockDataMapper.selectByPrimaryKey(stockMyData.getStockId()), fetchPage);
            }
        }
    }

    @Override
    @Transactional
    public void fetchLatestNews() throws Exception {
        /*List<StockMyStore> stockMyDatasByStore = stockService.getStockMyDatasByStore();
        if(stockMyDatasByStore != null && !stockMyDatasByStore.isEmpty()) {
            
        }
        
        List<StockMySelectedType> stockMySelectedTypes = stockService.getStockMySelectedTypes();
        if(stockMySelectedTypes != null && !stockMySelectedTypes.isEmpty()) {
            for(StockMySelectedType stockMySelectedType : stockMySelectedTypes) {
                List<StockMyData> stockMyDatas = stockService.getStockMyDatasByType(stockMySelectedType.getType());
            }
        }*/
        
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
//                this.fetchNews(stockDataMapper.selectByPrimaryKey(stockMyData.getStockId()), fetchPage);
                this.fetchLatestNews(stockDataMapper.selectByPrimaryKey(stockMyData.getStockId()));
            }
        }
    }
    
    @Override
    @Transactional
    public void fetchImportantLatestNews() throws Exception {
        StockImportantNews stockImportantNews = stockImportantNewsMapper.getImportantLatestNews();
        if(stockImportantNews == null) {
            logger.info("stockImportantNews: 第一次导入，只导入前4页面");
            for(int i=1;i<=4;i++) {
                this.fetchImportantNews(i);
            }
        } else {
            Date newDate = stockImportantNews.getNewsDate();
//            logger.info("stockImportantNews: 从当前日期导到"+newDate+".");
            int i=1;
            int maxFetchPages = 10;
           //定义标签
//            labelA:
            while(i<=maxFetchPages) {
                List<StockImportantNews> stockImportantNewses =  this.fetchImportantNews(i);
                if(stockImportantNewses!=null && !stockImportantNewses.isEmpty()) {
                    for(StockImportantNews importantNews : stockImportantNewses) {
                        if(importantNews != null && importantNews.getNewsDate()!=null && importantNews.getNewsDate().getTime() <= newDate.getTime()) {
                            // logger.info("stockImportantNews: 已经导到了"+newDate+"，无需再导入!");
//                            break labelA;
                            return;
                        }
                    }
                }
                i++;
            }
        }
    }
    
    @Override
    @Transactional
    public List<StockImportantNews> fetchImportantNews(int fetchPage) throws Exception {
        return WebClientUtils.process(webClient -> {
            Date date = new Date();
            String newUrl = ROOT_URL + "/news_list/url/d/e/N1.html?q=&pg="+fetchPage;
            // logger.info("fetchImportantNews newUrl--->" + newUrl);
            HtmlPage page = processGuceOathCom(webClient.getPage(newUrl));
            List<StockImportantNews> stockImportantNewses = Lists.newArrayList();
            HtmlElement ele = page.getHtmlElementById("newListContainer");
            List<?> tableDomNodes = ele.querySelectorAll("table#newListTable tbody tr td table");
            if(tableDomNodes!=null && !tableDomNodes.isEmpty()) {
                for(int i=0;i<tableDomNodes.size();i++) {
                    HtmlElement tableDomNode = (HtmlElement) tableDomNodes.get(i);
                    List<?> trDomNodes = tableDomNode.querySelectorAll("tbody tr");
                    if(trDomNodes!=null && !trDomNodes.isEmpty()) {
                        HtmlElement fromDomNode = (HtmlElement) trDomNodes.get(0);
                        // from
                        HtmlElement td = fromDomNode.getElementsByTagName("td").get(0);
                        List<HtmlElement> aNodes = td.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(1);
                        String url = aElement.getAttribute("href");
                        String fromValue = td.asText();
                        String a1 = StringUtils.substringAfterLast(fromValue, "（");
                        String a2 = StringUtils.substringBeforeLast(a1, "）");
                        String newsDateString = StringUtils.substringAfter(a2, " ");
                        // subject
                        HtmlElement subjectDomNode = (HtmlElement) trDomNodes.get(1);
                        String subjectValue = subjectDomNode.asText().replace("(詳全文)", "");
                        StockImportantNews stockImportantNews = new StockImportantNews();
                        stockImportantNews.setId(IdUtils.genLongId());
                        stockImportantNews.setCreateDate(date);
                        stockImportantNews.setFroms(fromValue);
//                        logger.info("fromValue--->"+fromValue);
//                        logger.info("newsDateString--->"+newsDateString);
                        stockImportantNews.setNewsDate(DatesUtils.YYMMDDHHMMSS2.toDate(newsDateString+":00"));
                        stockImportantNews.setSubject(subjectValue);
                        stockImportantNews.setUrl(url);
                        stockImportantNewses.add(stockImportantNews);
                        stockImportantNewsMapper.deleteByFroms(fromValue);
                        stockImportantNewsMapper.insert(stockImportantNews);
                    }
                }
            }
            return stockImportantNewses;
        });
    }
    
    private List<StockType> fetchKinds(StockTypeEnum stockTypeEnum) throws Exception {
        return WebClientUtils.process(webClient -> {
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
//            logger.info("2===>" + trs);
            if (trs != null && !trs.isEmpty()) {
                Date date = new Date();
                for (HtmlElement tr : trs) {
//                    logger.info("3===>" + tr.asXml());
                    List<HtmlElement> tds = tr.getElementsByTagName("td");
                    if (tds != null && !tds.isEmpty()) {
                        for (HtmlElement td : tds) {
//                            logger.info("td=" + td.asXml());
                            List<HtmlElement> aNodes = td.getElementsByTagName("a");
                            if(aNodes==null || aNodes.isEmpty()) continue;
                            HtmlElement aElement = aNodes.get(0);
                            String url = ROOT_URL + aElement.getAttribute("href");
//                            String id = StringUtils.substringAfterLast(href, " ");
                            String name = td.asText();
                            if(!skipKinds(name)) {
//                              logger.info("url--->" + url + "/name=" + name);
                                StockType stockType = new StockType();
                                stockType.setId(IdUtils.genLongId());
                                stockType.setName(name);
                                stockType.setType(stockTypeEnum.getType());
                                stockType.setUrl(url);
                                stockType.setStatus(true);
                                stockType.setCreateDate(date);
                                stockTypes.add(stockType);
//                                stockTypeConceptMapper.deleteByName(name);
                                if(stockTypeMapper.selectByName(name, stockTypeEnum.getType()) == null) {
                                    stockTypeMapper.insert(stockType);
                                }
                            }
                        }
                    }
                }
            }
            return stockTypes;
        });
    }
    

    
    @Override
    public void fetchStocks(StockType stockType) throws Exception {
        WebClientUtils.process(webClient -> {
            Date date = new Date();
            HtmlPage nextPage = webClient.getPage(stockType.getUrl());
            List<?> domNodes = nextPage.querySelectorAll("table.yui-text-left tbody tr");
//            logger.info("url="+stockType.getUrl()+"/name="+stockType.getName());
            if(domNodes==null || domNodes.size() < 3) {
                logger.info("domNodes异常，跳过导入：url2="+stockType.getUrl()+"/name="+stockType.getName());
            } 
            // 存託憑證，市認購，市認售，指數類，收益證劵
            // 在櫃買部份，指數類，認購，認售
            else {
                HtmlElement domNode = (HtmlElement) domNodes.get(2);
    //            logger.info("domNode===>"+domNode.asXml());
                
                List<DomNode> trDomNodes = domNode.querySelectorAll("table table tbody tr");//.get(2);
                if(trDomNodes!=null && !trDomNodes.isEmpty()) {
                    for(int i=2;i<trDomNodes.size();i++) {
                        HtmlElement trDomNode = (HtmlElement) trDomNodes.get(i);
    //                    logger.info("trDomNode="+trDomNode.asXml());
    //                  logger.info("trDomNode===>"+trDomNode);
                        List<HtmlElement> nextTds = trDomNode.getElementsByTagName("td");
                        HtmlElement tdElement = nextTds.get(1);
    //                    logger.info("tdElement===>"+tdElement.asXml());
                        
                        List<HtmlElement> aNodes = tdElement.getElementsByTagName("a");
                        if(aNodes==null || aNodes.isEmpty()) continue;
                        HtmlElement aElement = aNodes.get(0);
    //                    logger.info("aElement===>"+aElement.asXml());
                        String url = ROOT_URL + aElement.getAttribute("href");
    //                    String id = StringUtils.substringAfterLast(href, " ");
                        String value = tdElement.asText();
                        String no = StringUtils.substringBefore(value, " ");
                        if(!skipSocks(no)) {
                            String company = StringUtils.substringAfter(value, " ").replaceAll("\r\n", "");
                            
                            // tx_price closing highest lowest
                            HtmlElement txPriceElement = nextTds.get(3);
                            HtmlElement closingElement = nextTds.get(8);
                            HtmlElement highestElement = nextTds.get(10);
                            HtmlElement lowestElement = nextTds.get(11);
                            
        //                    logger.info("url=" + url + "/no=" + no + "/company=" + company);
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
                                // 如果該股票存在時，一定要復用之前的id，不然所有的其他的記錄關聯id，這樣會有問題
                                StockData existStockData = stockDataMapper.selectByNo(no);
                                StockData stockData = new StockData();
                                if(existStockData != null) {
                                    stockData.setId(existStockData.getId());
                                } else {
                                    stockData.setId(IdUtils.genLongId());
                                }
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
            return null;
        });
    }
    
    private void fetchStocks(List<StockType> stockTypes) throws Exception {
        if(stockTypes!=null && !stockTypes.isEmpty()) {
            for(StockType stockType : stockTypes) {
                this.fetchStocks(stockType);
            }
        }
        
    }
    
    @Override
    @Transactional
    public void refetchAllHistory() throws Exception {
        List<StockHistoryError> stockHistoryErrors = stockHistoryErrorMapper.selectAllByStatus(0, StockHistoryErrorEnum.HISTORY.getType());
        if(stockHistoryErrors!=null && !stockHistoryErrors.isEmpty()) {
            for(StockHistoryError stockHistoryError : stockHistoryErrors) {
                int errCount = stockHistoryError.getErrCount();
                if(errCount <= MAX_ERR_COUNT) {
                    try {
                        long s = System.currentTimeMillis();
                        logger.info("股号: "+stockHistoryError.getNo()+", 重新抓取开始......");
                        this.fetchHistory(stockHistoryError.getNo(), stockHistoryError.getStartDate(), stockHistoryError.getEndDate());
                        stockHistoryErrorMapper.deleteByPrimaryKey(stockHistoryError.getId());
                        logger.info("股号: "+stockHistoryError.getNo()+", 重新抓取完成, 耗时: "+((System.currentTimeMillis()-s)/1000)+"s.");
                    } catch(Exception e) {
                        logger.error("股号: {}, 重新抓取异常: ", stockHistoryError.getNo(), e);
                        stockHistoryErrorMapper.updateErrCount(stockHistoryError.getId());
                    }
                } else {
                    logger.info("股号: "+stockHistoryError.getNo()+"错误次数太多: "+errCount+", 不再重新抓取......");
//                    stockHistoryErrorMapper.deleteByPrimaryKey(stockHistoryError.getId());
                    // 不再重试
                    stockHistoryErrorMapper.updateStatus(stockHistoryError.getId(), 1);
                }
            }
        }
    }
    
    @Override
    @Transactional
    public void refetchAllHistoryDaily() throws Exception {
        List<StockHistoryError> stockHistoryErrors = stockHistoryErrorMapper.selectAllByStatus(0, StockHistoryErrorEnum.DAILY.getType());
        if(stockHistoryErrors!=null && !stockHistoryErrors.isEmpty()) {
            for(StockHistoryError stockHistoryError : stockHistoryErrors) {
                int errCount = stockHistoryError.getErrCount();
                if(errCount <= MAX_ERR_COUNT) {
                    try {
                        long s = System.currentTimeMillis();
                        logger.info("股号: "+stockHistoryError.getNo()+", 重新抓取当日交易开始......");
                        this.fetchCurrentHistoryDaily(stockHistoryError.getNo());
                        stockHistoryErrorMapper.deleteByPrimaryKey(stockHistoryError.getId());
                        logger.info("股号: "+stockHistoryError.getNo()+", 重新抓取当日交易完成, 耗时: "+((System.currentTimeMillis()-s)/1000)+"s.");
                    } catch(Exception e) {
                        logger.error("股号: {}, 重新抓取当日交易异常: ", stockHistoryError.getNo(), e);
                        stockHistoryErrorMapper.updateErrCount(stockHistoryError.getId());
                    }
                } else {
                    logger.info("股号: "+stockHistoryError.getNo()+"当日交易错误次数太多: "+errCount+", 不再重新抓取......");
//                    stockHistoryErrorMapper.deleteByPrimaryKey(stockHistoryError.getId());
                    // 不再重试
                    stockHistoryErrorMapper.updateStatus(stockHistoryError.getId(), 1);
                }
            }
        }
    }
    
    @Override
    public void fetchAllHistory() throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        logger.info("fetchAllHistory size---->"+stockMyDatas.size());
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
//                String startDate = "2017/07/18";
//                String endDate = "2017/11/01";
//                String endDate = DatesUtils.YYMMDD2.toString(nowDate);
                // 402396061841035264 1528
                fetchHistory(stockMyData.getNo());
            }
        }
    }
    
    @Override
    public void fetchAllHistoryDaily() throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        logger.info("fetchAllHistoryDaily size---->"+stockMyDatas.size());
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
                String no = stockMyData.getNo();
                try {
                    logger.info("股号: "+no+", 抓取当日交易开始......");
                    fetchCurrentHistoryDaily(no);
                    logger.info("股号: "+no+", 抓取当日交易结束......");
                } catch(Exception e) {
                    logger.error("股号: {}, 抓取当日交易异常: ", no, e);
                    StockHistoryError stockHistoryError = new StockHistoryError();
                    Date date = new Date();
                    stockHistoryError.setId(IdUtils.genLongId());
                    stockHistoryError.setCreateDate(date);
                    stockHistoryError.setNo(no);
                    stockHistoryError.setStartDate(MyDateUtils.localDate2Date(LocalDate.now()));
                    stockHistoryError.setEndDate(MyDateUtils.localDate2Date(LocalDate.now()));
                    stockHistoryError.setStatus(0); //抓取状态 1: 重试后成功 0: 错误
                    stockHistoryError.setErrCount(1);
                    stockHistoryError.setType(StockHistoryErrorEnum.DAILY.getType());
                    stockHistoryErrorMapper.insert(stockHistoryError);
                }
            }
        }
    }
    
    /*@Override
//    @Transactional
    public void fetchAllHistory(String startDate, String endDate) throws Exception {
        List<StockMyData> stockMyDatas = stockMyDataMapper.selectAll();
        if(stockMyDatas !=null && !stockMyDatas.isEmpty()){
            for(StockMyData stockMyData : stockMyDatas) {
//                String startDate = "2017/07/18";
//                String endDate = "2017/11/01";
//                String endDate = DatesUtils.YYMMDD2.toString(nowDate);
                try {
                    long s = System.currentTimeMillis();
                    logger.info("股号: "+stockMyData.getNo()+", 开始日期: "+startDate+", 结束日期: "+endDate+", 抓取开始.");
                    this.fetchHistory(stockMyData.getNo(), startDate, endDate);
                    logger.info("股号: "+stockMyData.getNo()+", 开始日期: "+startDate+", 结束日期: "+endDate+", 抓取完成, 耗时: "+((System.currentTimeMillis()-s)/1000)+"s.");
                } catch(Exception e) {
                    logger.error("股号: {}, 开始日期: {}, 结束日期: {}, 抓取异常：", stockMyData.getNo(), startDate, endDate, e);
                    StockHistoryError stockHistoryError = new StockHistoryError();
                    stockHistoryError.setId(IdUtils.genLongId());
                    stockHistoryError.setCreateDate(new Date());
                    stockHistoryError.setNo(stockMyData.getNo());
                    stockHistoryError.setStartDate(DatesUtils.YYMMDD2.toDate(startDate));
                    stockHistoryError.setEndDate(DatesUtils.YYMMDD2.toDate(endDate));
                    stockHistoryError.setStatus(0); //抓取状态 1: 重试后成功 0: 错误
                    stockHistoryErrorMapper.insert(stockHistoryError);
                }
            }
        }
    }*/

    /**
     * 日期格式為：yyyy/MM/dd
     */
    @Override
    @Transactional
    public void fetchHistory(String no) throws Exception {
        // 处理日期，而不是时间,所以用LocalDate
        LocalDate endDate = LocalDate.now();
        StockHistory lastStockHistory = stockHistoryMapper.selectLastStockHistory(no);
        // 一开始默认抓取最近5年的数据
        LocalDate startDate = endDate.plusYears(-5L);
        if(lastStockHistory != null) {
            // 开始日期从最近数据库中的最近日期+1
            startDate = MyDateUtils.date2LoalDate(DateUtils.addDays(lastStockHistory.getDate(), 1));
        }
        // 结束日期 >= 开始日期才fetch
        if(endDate.isAfter(startDate) || endDate.isEqual(startDate)) {
            try {
                this.fetchHistory(no, MyDateUtils.localDate2Date(startDate), MyDateUtils.localDate2Date(endDate));
            } catch(Exception e) {
                logger.error("股号: {}, 开始日期: {}, 结束日期: {}, 抓取异常: ", no, startDate, endDate, e);
                StockHistoryError stockHistoryError = new StockHistoryError();
                stockHistoryError.setId(IdUtils.genLongId());
                stockHistoryError.setCreateDate(new Date());
                stockHistoryError.setNo(no);
                stockHistoryError.setStartDate(MyDateUtils.localDate2Date(startDate));
                stockHistoryError.setEndDate(MyDateUtils.localDate2Date(endDate));
                stockHistoryError.setStatus(0); //抓取状态 1: 重试后成功 0: 错误
                stockHistoryError.setErrCount(1);
                stockHistoryError.setType(StockHistoryErrorEnum.HISTORY.getType());
                stockHistoryErrorMapper.insert(stockHistoryError);
            }
        }
            
    }

    /**
     * 日期格式為：yyyy/MM/dd
     */
    private void fetchHistory(String no, Date startDate, Date endDate) throws Exception {
        long s = System.currentTimeMillis();
        logger.info("股号: "+no+", 开始日期: "+startDate+", 结束日期: "+endDate+", 抓取开始......");
        List<StockHistory> stockHistory4Inserts = searchHistory(no, startDate, endDate);
        logger.info("股号: "+no+", 开始日期: "+startDate+", 结束日期: "+endDate+", 抓取完成, 耗时: "+((System.currentTimeMillis()-s)/1000)+"s.");
        
        s = System.currentTimeMillis();
        logger.info("股号: "+no+", 开始日期: "+startDate+", 结束日期: "+endDate+", 保存开始......");
        this.saveHistory(stockHistory4Inserts);
        logger.info("股号: "+no+", 开始日期: "+startDate+", 结束日期: "+endDate+", 保存完成, 耗时: "+((System.currentTimeMillis()-s)/1000)+"s.");
        
    }

    /**
     * 只抓取网站的数据，不保存数据库
     * 日期格式為：yyyy/MM/dd
     */
    @Override
    @Transactional
    public void fetchCurrentHistoryDaily(String no) throws Exception {
        // https://www.cnyes.com/twstock/quote/2881.htm
        List<StockHistoryError> stockHistoryErrors = stockHistoryErrorMapper.selectAllByStatusByNo(no, 1, StockHistoryErrorEnum.DAILY.getType());
        if(stockHistoryErrors!=null && stockHistoryErrors.stream()//.map(StockHistoryError::getNo)
            .anyMatch(p -> no.equals(p.getNo()) && p.getErrCount() > MAX_ERR_COUNT)) {
                logger.info("fetchCurrentHistoryDaily: 股号: "+no+"错误次数太多，不太抓取。");
                return;
        }
        WebClientUtils.process(webClient -> {
            List<StockHistoryDaily> stockHistoryDaily4Inserts = Lists.newArrayList();
            StockData stockData = stockDataMapper.selectByNo(no);
            if(stockData == null) {
                throw new BusinessException("Not found stock data by no: " + no);
            }
            long stockId = stockData.getId();
            // 
            String url = "https://www.cnyes.com/twstock/ps_pv_time/"+stockData.getNo()+".htm";
            HtmlPage page = processGuceOathCom(webClient.getPage(url));
//            final String pageAsXml = page.asXml();
//            System.out.println(pageAsXml);
            List<Object> trNodes = page.getByXPath("//div[@class=\"scroll\"]//tr");
            if(trNodes!=null && !trNodes.isEmpty()) {
                for(int i = 0;i<trNodes.size();i++) {
                    HtmlElement node = (HtmlElement)trNodes.get(i);
                    DomNodeList<DomNode> tdNodes = node.getChildNodes();
                    if(tdNodes != null && !tdNodes.isEmpty()) {
                        // 時間	買價	賣價	成交價	漲跌	單量	總量
                        String dateStr = tdNodes.get(0).asText();
                        // 把01替换为13点
                        String datetimeStr = DatesUtils.YYMMDD2.toString()+" "+dateStr.trim()
                            .replaceAll("^1:", "13:").replaceAll("^01:", "13:")
                            .replaceAll("^2:", "14:").replaceAll("^02:", "14:");
                        Date date = DatesUtils.YYMMDDHHMMSS2.toDate(datetimeStr);
						boolean isExisted = stockHistoryDaily4Inserts.stream().map(StockHistoryDaily::getDate)
								.anyMatch(p -> date.getTime() == p.getTime());
                    	if(!isExisted) {
                            String buy = tdNodes.get(1).asText();
                            String sell = tdNodes.get(2).asText();
                            String vol = tdNodes.get(3).asText();
                            String upsDowns = tdNodes.get(4).asText();
                            String pratyaksam = tdNodes.get(5).asText();
                            String totalAmount = tdNodes.get(6).asText();
//                            System.out.println(datetimeStr+" "+buy+" "+sell+" "+vol+" "+upsDowns+" "+pratyaksam);
                            StockHistoryDaily stockHistoryDaily = new StockHistoryDaily();
//                            stockHistoryDaily.setId(IdUtils.genLongId());
//                          stockHistoryDaily.setCreateDate(new Date());
                            stockHistoryDaily.setStockId(stockId);
                            stockHistoryDaily.setDate(date);
                            stockHistoryDaily.setBuy(toBigDecimal(buy));
                            stockHistoryDaily.setSell(toBigDecimal(sell));
                            stockHistoryDaily.setVol(toBigDecimal(vol));
                            stockHistoryDaily.setUpsDowns(toBigDecimal(upsDowns));
                            stockHistoryDaily.setTotalAmount(toBigDecimal(totalAmount));
                            stockHistoryDaily.setPratyaksam(toBigDecimal(pratyaksam));
//                            stockHistoryDailyMapper.insert(stockHistoryDaily);
                            stockHistoryDaily4Inserts.add(stockHistoryDaily);
                    	}
                    }
                }
            }
        
            StockHistoryDaily lastStockHistoryDaily = stockHistoryDailyMapper.selectLastStockHistoryDaily(no);
            Date lastDate = null;
            if(lastStockHistoryDaily!=null) {
                lastDate = lastStockHistoryDaily.getDate();
            }
            if(stockHistoryDaily4Inserts!=null && !stockHistoryDaily4Inserts.isEmpty()) {
                stockHistoryDaily4Inserts = Lists.reverse(stockHistoryDaily4Inserts);
                // 升序insert
                for (StockHistoryDaily stockHistoryDaily4Insert : stockHistoryDaily4Inserts) {
                    if(lastDate == null || stockHistoryDaily4Insert.getDate().getTime() > lastDate.getTime()) {
                        stockHistoryDaily4Insert.setId(IdUtils.genLongId());
                        stockHistoryDaily4Insert.setCreateDate(new Date());
                        stockHistoryDailyMapper.insert(stockHistoryDaily4Insert);
                    }
                    
                }
            }
            return null;
        });
    }

    /**
     * 只抓取网站的数据，不保存数据库
     * 日期格式為：yyyy/MM/dd
     */
    private List<StockHistory> searchHistory(String no, Date startDate, Date endDate) throws Exception {
        List<StockHistoryError> stockHistoryErrors = stockHistoryErrorMapper.selectAllByStatusByNo(no, 1, StockHistoryErrorEnum.HISTORY.getType());
        if(stockHistoryErrors!=null && stockHistoryErrors.stream()//.map(StockHistoryError::getNo)
            .anyMatch(p -> no.equals(p.getNo()) && p.getErrCount() > MAX_ERR_COUNT)) {
                logger.info("searchHistory: 股号: "+no+"错误次数太多，不太抓取。");
                return Lists.newArrayList();
        }
        return WebClientUtils.process(webClient -> {
            List<StockHistory> stockHistory4Inserts = Lists.newArrayList();
            StockData stockData = stockDataMapper.selectByNo(no);
            if(stockData == null) {
                throw new BusinessException("Not found stock data by no: " + no);
            }
            long stockId = stockData.getId();
            String url = "https://www.cnyes.com/twstock/ps_historyprice.aspx?code="+stockData.getNo();
            HtmlPage page = processGuceOathCom(webClient.getPage(url));
            page.getElementById("ctl00_ContentPlaceHolder1_startText").setAttribute("value", DatesUtils.YYMMDD2.toString(startDate));  
            page.getElementById("ctl00_ContentPlaceHolder1_endText").setAttribute("value", DatesUtils.YYMMDD2.toString(endDate));  
            HtmlForm form = page.getHtmlElementById("aspnetForm");
            HtmlPage page2 = form.getOneHtmlElementByAttribute("input", "id", "ctl00_ContentPlaceHolder1_submitBut").click();
            HtmlForm form2 = page2.getHtmlElementById("aspnetForm");
            HtmlElement element = form2.getOneHtmlElementByAttribute("table", "enableviewstate", "false");
    //            logger.info("1===>"+element.asXml());
            List<HtmlElement> trs = element.getElementsByTagName("tr");
    //            logger.info("2===>"+trs);
            if(trs!=null && !trs.isEmpty()) {
                stockHistoryMapper.deleteByDate(stockId, DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
                for(HtmlElement tr : trs) {
    //                    logger.info("3===>"+tr.asXml());
                    List<HtmlElement> tds = tr.getElementsByTagName("td");
                    if(tds!=null && !tds.isEmpty()) {
                        int i=0;
                        StockHistory stockHistory = new StockHistory();
                        for(HtmlElement td : tds) {
    //                            logger.info("===>"+td.asXml());
                            switch(i) {
                                case 0:
                                  // 日期
                                    stockHistory.setDate(DatesUtils.YYMMDD2.toDate(td.asText()));
                                break;
                                case 1:
                                  // 開盤
                                    stockHistory.setOpening(toBigDecimal(td.asText()));
                                  break;
                                case 2:
                                    // 最高    
                                    stockHistory.setHighest(toBigDecimal(td.asText()));
                                    break;
                                case 3:
                                    // 最低    
                                    stockHistory.setLowest(toBigDecimal(td.asText()));
                                    break;
                                case 4:
                                    // 收盤    
                                    stockHistory.setClosing(toBigDecimal(td.asText()));
                                    break;
                                case 5:
                                    // 漲跌    
                                    stockHistory.setUpsDowns(toBigDecimal(td.asText()));
                                    break;
                                case 6:
                                    // 漲%    
                                    stockHistory.setRiseRate(td.asText());
                                    break;
                                case 7:
                                    // 成交量    
                                    stockHistory.setVol(toBigDecimal(td.asText().replace(",", "")));
                                    break;
                                case 8:
                                    // 成交金額    
                                    stockHistory.setAmount(toBigDecimal(td.asText().replace(",", "")));
                                    break;
                                case 9:
                                    // 本益比
                                    stockHistory.setPer(td.asText());
                                    break;
                            }
                            i++;
                        }
    //                        logger.info("===>"+stockHistory);
                        stockHistory.setType(StockHistoryEnum.DAY.getType());
                        stockHistory.setStockId(stockId);
                        stockHistory4Inserts.add(stockHistory);
                    }
                }
            }
            return stockHistory4Inserts;
        });
    }
    
    /**
     * 保存数据到数据库
     */
    @Transactional
    private void saveHistory(List<StockHistory> stockHistory4Inserts) {
        if(stockHistory4Inserts!=null && !stockHistory4Inserts.isEmpty()) {
            stockHistory4Inserts = Lists.reverse(stockHistory4Inserts);
            // 升序insert
            for (StockHistory stockHistory4Insert : stockHistory4Inserts) {
                int existCount = stockHistoryMapper.selectUniqueHistoryCount(stockHistory4Insert.getStockId(), stockHistory4Insert.getDate(), stockHistory4Insert.getType());
                if(existCount == 0) {
                    stockHistory4Insert.setId(IdUtils.genLongId());
                    stockHistory4Insert.setCreateDate(new Date());
                    stockHistoryMapper.insert(stockHistory4Insert);
                }
                // 日：可以在页面中进行计算
                /*StockHistory averageOfDay = stockHistoryMapper.averageClosing(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.DAY.getType());
                StockHistory averageVolOfDay = stockHistoryMapper.averageVol(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.DAY.getType());
                if(averageOfDay!=null && averageVolOfDay!=null) {
                    averageOfDay.setId(stockHistory4Insert.getId());
                    averageOfDay.setUpdateDate(new Date());
                    // 设置5日成立量均线
                    averageOfDay.setAveragevol5(averageVolOfDay.getAveragevol5());
                    stockHistoryMapper.updateAverage(averageOfDay);
                }*/

            }
            // 需要上面处理完才能往下处理
            // 在前端页面中处理
//            for (StockHistory stockHistory4Insert : stockHistory4Inserts) {
//                long stockId = stockHistory4Insert.getStockId();
//                LocalDate localDate = MyDateUtils.date2LoalDate(stockHistory4Insert.getDate());
//                // 周
//                LocalDate firstDateOfWeek = MyDateUtils.getFirstDayOfWeek(localDate);
//                Date startDateOfWeek = MyDateUtils.localDate2Date(firstDateOfWeek);
//                
////                    LocalDate lastDateOfWeek = localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), 7);
////                    Date endDateOfWeek = Date.from(lastDateOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                // 结束日期应该是当时那条记录的日期
//                Date endDateOfWeek = stockHistory4Insert.getDate();
//                
//                StockHistory history4Week = stockHistoryMapper.selectWeekOrMonthStockHistory(stockId, startDateOfWeek, endDateOfWeek, StockHistoryEnum.DAY.getType());
//                if(history4Week != null) {
//                    // 以startDateOfWeek(（取下一个自然工作日）)作为周的唯一判断,一周只能有一和记录
//                    Date uniqeDate = MyDateUtils.getNextNatureWorkDay(startDateOfWeek);
//                    stockHistoryMapper.deleteByWeekOrMonth(stockId, uniqeDate, StockHistoryEnum.WEEK.getType());
//                    history4Week.setId(IdUtils.genLongId());
//                    history4Week.setStockId(stockId);
//                    history4Week.setDate(uniqeDate);
//                    history4Week.setType(StockHistoryEnum.WEEK.getType());
//                    history4Week.setCreateDate(new Date());
//                    stockHistoryMapper.insert(history4Week);
//                    /*StockHistory averageOfWeek = stockHistoryMapper.averageClosing(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.WEEK.getType());
//                    StockHistory averageVolOfWeek = stockHistoryMapper.averageVol(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.WEEK.getType());
//                    if(averageOfWeek != null && averageVolOfWeek != null) {
//                        averageOfWeek.setId(history4Week.getId());
//                        averageOfWeek.setUpdateDate(new Date());
//                        // 设置5周成立量均线
//                        averageOfWeek.setAveragevol5(averageVolOfWeek.getAveragevol5());
//                        stockHistoryMapper.updateAverage(averageOfWeek);
//                    }*/
//                }
//                
//                // 月
//                LocalDate firstDateOfMonth = MyDateUtils.getFirstDayOfMonth(localDate);
//                Date startDateOfMonth = MyDateUtils.localDate2Date(firstDateOfMonth);
//                
////                    LocalDate lastDateOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
////                    Date endDateOfMonth = Date.from(lastDateOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                // 结束日期应该是当时那条记录的日期
//                Date endDateOfMonth = stockHistory4Insert.getDate();
//                
//                StockHistory history4Month = stockHistoryMapper.selectWeekOrMonthStockHistory(stockId, startDateOfMonth, endDateOfMonth, StockHistoryEnum.DAY.getType());
//                if(history4Month != null) {
//                    // 以startDateOfMonth作为月（取下一个自然工作日）的唯一判断,一月只能有一和记录
//                    Date uniqeDate = MyDateUtils.getNextNatureWorkDay(startDateOfMonth);
//                    stockHistoryMapper.deleteByWeekOrMonth(stockId, uniqeDate, StockHistoryEnum.MONTH.getType());
//                    history4Month.setId(IdUtils.genLongId());
//                    history4Month.setStockId(stockId);
//                    history4Month.setDate(uniqeDate);
//                    history4Month.setType(StockHistoryEnum.MONTH.getType());
//                    history4Month.setCreateDate(new Date());
//                    stockHistoryMapper.insert(history4Month);
//                    /*StockHistory averageOfMonth = stockHistoryMapper.averageClosing(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.MONTH.getType());
//                    StockHistory averageVolOfMonth = stockHistoryMapper.averageVol(stockId, stockHistory4Insert.getDate(), StockHistoryEnum.MONTH.getType());
//                    if(averageOfMonth!=null && averageVolOfMonth!=null) {
//                        averageOfMonth.setId(history4Month.getId());
//                        averageOfMonth.setUpdateDate(new Date());
//                        // 设置5月成立量均线
//                        averageOfMonth.setAveragevol5(averageVolOfMonth.getAveragevol5());
//                        stockHistoryMapper.updateAverage(averageOfMonth);
//                    }*/
//                }
//            }
        }
    }

    /*@Override
    public List<StockHistory> select(Date startDate, Date endDate) {
        return stockHistoryMapper.select(startDate, endDate);
    }*/

    @Override
    @Transactional
    public void importBydailyTransactions(String excelFile) throws IOException {
        String fileMd5 = FileMd5Utils.getMD5(new File(excelFile));
        StockDailyTransactionsHistory sth = stockDailyTransactionsHistoryMapper.selectByMd5(fileMd5);
        if(sth != null) {
//            throw new BusinessException("Excel imported already. excelFile: " + excelFile);
            throw new BusinessException("該文件已經導入過，無需重複導入！");
        }
        Date date = new Date();
        Table<Integer, String, Object> table = ExcelUtils.readExcel2table(excelFile, 1, 1, 1);
//        logger.info("all=>"+table);
        Set<Integer> rowKeys = table.rowKeySet();
        logger.info("rowKeys size=>"+rowKeys.size());
//        List<StockDailyTransactions> dailyTxs = Lists.newArrayList();
        for(Integer rowKey : rowKeys) {
            Map<String, Object> rows = table.row(rowKey);
//            String columnKey = rowKey.getColumnKey();
//            String value = set.getValue();
//            table.get(rowKey, columnKey);
//            logger.info(rows);
            
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
            
            StockMyStore stockMyStore = stockMyStoreMapper.select(tx.getStockId(), tx.getTxDate(), tx.getTxKind(), tx.getTxPrice());
            if(stockMyStore == null) {
                stockMyStore = new StockMyStore();
                stockMyStore.setId(IdUtils.genLongId());
                stockMyStore.setQuantity(tx.getQuantity());
                stockMyStore.setStatus(true);
                stockMyStore.setStockId(tx.getStockId());
                stockMyStore.setTxDate(tx.getTxDate());
                stockMyStore.setTxKind(tx.getTxKind());
                stockMyStore.setCreateDate(date);
                stockMyStore.setTxPrice(tx.getTxPrice());
                stockMyStoreMapper.insert(stockMyStore);
            } else {
                int quantity = stockMyStore.getQuantity();
                if(quantity != tx.getQuantity().intValue()) {
                    if(tx.getTxKind() == BuyTypeEnum.BUY.getType()) {
                        // 买 +
                        stockMyStoreMapper.updateByQuantity(stockMyStore.getId(), quantity);
                    } else if(tx.getTxKind() == BuyTypeEnum.SELL.getType()) {
                        // 卖 -
                        stockMyStoreMapper.updateByQuantity(stockMyStore.getId(), -quantity);
                    }
                }
            }
        }
        sth = new StockDailyTransactionsHistory();
        sth.setId(IdUtils.genLongId());
        sth.setFileMd5(fileMd5);
        sth.setCreateDate(date);
        stockDailyTransactionsHistoryMapper.insert(sth);
    }
    
    @Override
    @Transactional
    public void importStockMyDatas(String excelFile) throws IOException {
        Table<Integer, String, Object> table = ExcelUtils.readExcel2table(excelFile, 0, 0, 1);
        Map<Integer, Map<String, Object>> map = table.rowMap();
        Collection<Map<String,Object>> values = map.values();
        Iterator<Map<String, Object>> iterator = values.iterator();
        while (iterator.hasNext()) {
             Map<String, Object> rows = iterator.next();
             Date date = new Date();
             // {=1.0, 概念股=台積電/半導體設備, 產品=再生晶圓, 股名=中砂, 股號=1560.0}
             String no = rows.get("股號").toString().replaceAll("\\.0", "");
             StockData stockData = stockDataMapper.selectByNo(no);
             if(stockData == null) {
                 logger.warn("Not found stock data by no: " + no);
                 /*String  company = rows.get("股名").toString();
                 stockData = new StockData();
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
                 stockDataMapper.insert(stockData);*/
             } else {
                 
                 StockMyData stockMyData = stockMyDataMapper.selectByStockId(stockData.getId());
                 if(stockMyData == null) {
                     String kinds = rows.get("概念股").toString();
                     if(StringUtils.isNotBlank(kinds)) {
                         kinds = StringUtils.substringAfter(kinds.toString(), "/");
                     }
                     
                     String industry = rows.get("產品").toString();
                     
                     stockMyData = new StockMyData();
                     stockMyData.setId(IdUtils.genLongId());
                     stockMyData.setStockId(stockData.getId());
                    stockMyData.setKinds(kinds);
                    stockMyData.setIndustry(industry);
                     stockMyData.setStatus(true);
                     stockMyData.setCreateDate(date);
                     stockMyDataMapper.insert(stockMyData);
                 } else {
                     logger.error("{}已經存在，無需導入！", no); 
                 }
             }
        }
    }
    
    private BigDecimal toBigDecimal(String value) {
        try {
            value = value.replace(",", "");
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
    
    /*private boolean checkNewsSubject(String subject) {
        List<StockNewsExcludeKey> stockNewsExcludeKeys = stockNewsExcludeKeyMapper.selectAll(true);
        return stockNewsExcludeKeys.stream()
                .map(StockNewsExcludeKey::getKey)
//                .peek(System.out::println)
                .anyMatch(p -> subject.indexOf(p) != -1);
    }*/
    
}
