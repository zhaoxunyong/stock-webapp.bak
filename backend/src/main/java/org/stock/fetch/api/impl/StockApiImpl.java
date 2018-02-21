package org.stock.fetch.api.impl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stock.fetch.api.StockApi;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockNewsMapper;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockNews;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockApiImpl implements StockApi {
	
    @Autowired
    private StockDataMapper stockDataMapper;
	
    @Autowired
    private StockNewsMapper stockNewsMapper;

	@Override
    @RequestMapping(value = "/getStockDatas", method = GET)
	public List<StockData> getStockDatas() {
		return stockDataMapper.selectAll();
	}

	@Override
    @RequestMapping(value = "/getStockData/{id}", method = GET)
	public StockData getStockData(@PathVariable Long id) {
		StockData stockData = stockDataMapper.selectByPrimaryKey(id);
		return stockData;
	}

	@Override
    @RequestMapping(value = "/getNewsBystockId/{stockId}", method = GET)
	public List<StockNews> getNewsBystockId(@PathVariable Long stockId) {
		return stockNewsMapper.selectByStockId(stockId);
	}

}
