package org.stock.fetch.api;

import java.util.List;

import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockNewsDto;
import org.stock.fetch.model.StockNews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="合同相關的接口", protocols="http", produces = "application/json")
public interface StockApi {
	
	@ApiOperation(value="getStockDatas", notes="getStockDatas")
	public List<StockDataDto> getStockDatas();
	
	@ApiOperation(value="getStockData", notes="getStockData")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
      })
	public StockDataDto getStockData(String id);
	
	@ApiOperation(value="getNewsBystockId", notes="getNewsBystockId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
	})
	public List<StockNewsDto> getNewsBystockId(String stockId);
}
