package org.stock.fetch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stock.fetch.dao.StockHistoryMapper;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.aeasycredit.commons.lang.utils.DatesUtils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * https://www.cnyes.com/twstock/ps_historyprice/2881.htm
 */
@Service
public class FetchServiceImpl implements FetchService {
    
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    
    /**
     * 日期格式為：yyyy/MM/dd
     */
    @Override
    public void fetch(String startDate, String endDate) throws Exception {
        String stockId = "2881";
        HtmlPage page = webClient.getPage("https://www.cnyes.com/twstock/ps_historyprice.aspx?code="+stockId);
        page.getElementById("ctl00_ContentPlaceHolder1_startText").setAttribute("value", startDate);  
        page.getElementById("ctl00_ContentPlaceHolder1_endText").setAttribute("value", endDate);  
        HtmlForm form = page.getHtmlElementById("aspnetForm");
        HtmlPage page2 = form.getOneHtmlElementByAttribute("input", "id", "ctl00_ContentPlaceHolder1_submitBut").click();
        HtmlForm form2 = page2.getHtmlElementById("aspnetForm");
        HtmlElement element = form2.getOneHtmlElementByAttribute("table", "enableviewstate", "false");
//        System.out.println("1===>"+element.asXml());
        List<HtmlElement> trs = element.getElementsByTagName("tr");
        System.out.println("2===>"+trs);
        if(trs!=null && !trs.isEmpty()) {
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
                    stockHistoryMapper.insert(stockHistory);
                }
            }
        }
    }

    @Override
    public List<StockHistory> select(Date startDate, Date endDate) {
        return stockHistoryMapper.select(startDate, endDate);
    }
    
}
