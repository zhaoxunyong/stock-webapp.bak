package org.stock.fetch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.service.FetchService;

import com.aeasycredit.commons.lang.utils.DatesUtils;

@Controller
@RequestMapping("/fetch")
public class FetchController {
    
    @Autowired
    private FetchService fetchService;
    
    @RequestMapping("/index")
    public ModelAndView index(String startDate, String endDate) {
        List<StockHistory> historys = fetchService.select(DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
        ModelAndView view = new ModelAndView("fetch");
        view.addObject("historys", historys);
        return view;
    }
    
    @RequestMapping("/index2")
    public ModelAndView index2(String startDate, String endDate) {
        List<StockHistory> historys = fetchService.select(DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
        ModelAndView view = new ModelAndView("fetch2");
        view.addObject("historys", historys);
        return view;
    }
    
    @RequestMapping("/data")
    @ResponseBody
    public String data(String startDate, String endDate) {
        List<StockHistory> historys = fetchService.select(DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
        StringBuilder str = new StringBuilder();
        str.append("Date,Open,High,Low,Close,Volume").append("\n");
//        Date,Open,High,Low,Close,Volume
//        9-Jun-14,62.40,63.34,61.79,62.88,37617413
        if(historys != null && !historys.isEmpty()) {
            for(StockHistory history : historys) {
                str.append(DatesUtils.YYMMDD.toString(history.getDate())).append(",")
                   .append(history.getOpening()).append(",")
                   .append(history.getHighest()).append(",")
                   .append(history.getLowest()).append(",")
                   .append(history.getClosing()).append(",")
                   .append(history.getVol().replace(",", "")).append("\n");
            }
        }
        return str.toString();
    }
}
