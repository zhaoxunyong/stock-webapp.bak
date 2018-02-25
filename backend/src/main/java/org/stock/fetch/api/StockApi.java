package org.stock.fetch.api;

import java.util.List;

import org.stock.fetch.api.dto.PageDto;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.api.dto.StockMySelectedTypeDto;
import org.stock.fetch.api.dto.StockNewsDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="合同相關的接口", protocols="http", produces = "application/json")
public interface StockApi {
	
	@ApiOperation(value="getStockMyDatas", notes="getStockMyDatas")
	public List<StockMyDataDto> getStockMyDatas();
	
	@ApiOperation(value="getStockMyDatasByType", notes="getStockMyDatasByType")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "string", paramType = "path"),
      })
	public List<StockMyDataDto> getStockMyDatasByType(String type);
	
	@ApiOperation(value="getStockData", notes="getStockData")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
      })
	public StockDataDto getStockData(String id);
	
	@ApiOperation(value="getNewsBystockId", notes="getNewsBystockId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
	})
	public PageDto<StockNewsDto> getNewsBystockId(String stockId, int curPage, int pageSize);
	
	@ApiOperation(value="getImportantNews", notes="getImportantNews")
	public PageDto<StockImportantNewsDto> getImportantNews(int curPage, int pageSize);
	
	@ApiOperation(value="getStockMySelectedTypes", notes="getStockMySelectedTypes")
	public List<StockMySelectedTypeDto> getStockMySelectedTypes();
	
	@ApiOperation(value="getMySelectedTypesByStockId", notes="getMySelectedTypesByStockId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path")
	})
	public List<StockMySelectedTypeDto> getMySelectedTypesByStockId(String stockId);
	
	@ApiOperation(value="saveStockMySelectedType", notes="saveStockMySelectedType")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
	})
	public void saveStockMySelectedType(String name);
	
	@ApiOperation(value="changeStockMySelected", notes="changeStockMySelected")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "query"),
	})
	public void changeStockMySelected(String stockId, String  selectedType);
	
	@ApiOperation(value="removeStockMySelected", notes="removeStockMySelected")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "query"),
	})
	public void removeStockMySelected(String stockId, String  selectedType);
}
