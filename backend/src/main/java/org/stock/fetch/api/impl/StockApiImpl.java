package org.stock.fetch.api.impl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stock.fetch.api.StockApi;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockNewsDto;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockNewsMapper;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockNews;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockApiImpl implements StockApi {
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private StockDataMapper stockDataMapper;
	
    @Autowired
    private StockNewsMapper stockNewsMapper;

	@Override
    @RequestMapping(value = "/getStockDatas", method = GET)
	public List<StockDataDto> getStockDatas() {
		List<StockData> stockDatas = stockDataMapper.selectAll();
		List<StockDataDto> dtoList = stockDatas.stream().map(model -> {
			return modelMapper.map(model, StockDataDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
    @RequestMapping(value = "/getStockData/{id}", method = GET)
	public StockDataDto getStockData(@PathVariable String id) {
		StockData stockData = stockDataMapper.selectByPrimaryKey(Long.parseLong(id));
		StockDataDto dto = modelMapper.map(stockData, StockDataDto.class);
		return dto;
	}

	@Override
    @RequestMapping(value = "/getNewsBystockId/{stockId}", method = GET)
	public List<StockNewsDto> getNewsBystockId(@PathVariable String stockId) {
		List<StockNews> stockNewses = stockNewsMapper.selectByStockId(Long.parseLong(stockId));
		List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
			return modelMapper.map(model, StockNewsDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

}
