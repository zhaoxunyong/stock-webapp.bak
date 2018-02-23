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
import org.stock.fetch.api.dto.PageDto;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.api.dto.StockNewsDto;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockImportantNewsMapper;
import org.stock.fetch.dao.StockMyDataMapper;
import org.stock.fetch.dao.StockNewsMapper;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockNews;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockApiImpl implements StockApi {
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private StockMyDataMapper stockMyDataMapper;
	
    @Autowired
    private StockDataMapper stockDataMapper;
	
    @Autowired
    private StockNewsMapper stockNewsMapper;
	
    @Autowired
    private StockImportantNewsMapper stockImportantNewsMapper;

	@Override
    @RequestMapping(value = "/getStockMyDatas", method = GET)
	public List<StockMyDataDto> getStockMyDatas() {
		List<StockMyData> stockDatas = stockMyDataMapper.selectAll();
		List<StockMyDataDto> dtoList = stockDatas.stream().map(model -> {
			return modelMapper.map(model, StockMyDataDto.class);
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
    @RequestMapping(value = "/getNewsBystockId/{stockId}/{curPage}/{pageSize}", method = GET)
	public PageDto<StockNewsDto> getNewsBystockId(@PathVariable String stockId, @PathVariable int curPage, @PathVariable int pageSize) {
		PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
		
		List<StockNews> stockNewses = stockNewsMapper.selectByStockId(Long.parseLong(stockId), pageDto.getStart(), pageSize);
		List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
			return modelMapper.map(model, StockNewsDto.class);
		}).collect(Collectors.toList());
		
		// 查詢列表記錄總數
        int total = stockNewsMapper.count(Long.parseLong(stockId));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
	}

	@Override
    @RequestMapping(value = "/getImportantNews/{curPage}/{pageSize}", method = GET)
	public PageDto<StockImportantNewsDto> getImportantNews(@PathVariable int curPage, @PathVariable int pageSize) {
		PageDto<StockImportantNewsDto> pageDto = new PageDto<StockImportantNewsDto>(curPage, pageSize);
		
		List<StockImportantNews> stockImportantNewses = stockImportantNewsMapper.selectAll(pageDto.getStart(), pageSize);
		List<StockImportantNewsDto> dtoList = stockImportantNewses.stream().map(model -> {
			return modelMapper.map(model, StockImportantNewsDto.class);
		}).collect(Collectors.toList());
		
		// 查詢列表記錄總數
        int total = stockImportantNewsMapper.count();
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
	}

}
