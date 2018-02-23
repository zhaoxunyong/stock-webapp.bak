package org.stock.fetch.api;

import java.util.List;

import org.stock.fetch.api.dto.PageDto;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.api.dto.StockNewsDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="合同相關的接口", protocols="http", produces = "application/json")
public interface StockApi {
	
	@ApiOperation(value="getStockMyDatas", notes="getStockMyDatas")
	public List<StockMyDataDto> getStockMyDatas();
	
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
}
