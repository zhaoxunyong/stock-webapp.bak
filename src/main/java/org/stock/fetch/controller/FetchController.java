package org.stock.fetch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView fetch(String startDate, String endDate) {
        List<StockHistory> historys = fetchService.select(DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
        ModelAndView view = new ModelAndView("fetch");
        view.addObject("historys", historys);
        return view;
    }
}
