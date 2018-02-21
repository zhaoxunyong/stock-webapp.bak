package org.stock.fetch.api;

import java.util.List;

import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockNews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="合同相關的接口", protocols="https", produces = "application/json")
public interface StockApi {
	
	@ApiOperation(value="getStockDatas", notes="getStockDatas")
	public List<StockData> getStockDatas();
	
	@ApiOperation(value="getStockData", notes="getStockData")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path"),
      })
	public StockData getStockData(Long id);
	
	@ApiOperation(value="getNewsBystockId", notes="getNewsBystockId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "long", paramType = "path"),
	})
	public List<StockNews> getNewsBystockId(Long stockId);
}
