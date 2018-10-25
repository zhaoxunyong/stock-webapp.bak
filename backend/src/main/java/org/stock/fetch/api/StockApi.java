package org.stock.fetch.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.stock.fetch.api.dto.ChangeStockMySelectedDto;
import org.stock.fetch.api.dto.ChangeStockMySelectedTypeParams;
import org.stock.fetch.api.dto.PageDto;
import org.stock.fetch.api.dto.StockDailyTransactionsDto;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockHistoryDto;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.api.dto.StockMySelectedTypeDto;
import org.stock.fetch.api.dto.StockMyStoreDto;
import org.stock.fetch.api.dto.StockNewsDto;
import org.stock.fetch.api.dto.StockNewsKeyDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="Stock Api", protocols="http", produces = "application/json")
public interface StockApi {
	
	@ApiOperation(value="getStockMyDatas", notes="getStockMyDatas")
	public List<StockMyDataDto> getStockMyDatas();
    
    @ApiOperation(value="getStockMyDatasByStore", notes="getStockMyDatasByStore")
    public List<StockMyStoreDto> getStockMyDatasByStore();
	
	@ApiOperation(value="getStockMyDatasByType", notes="getStockMyDatasByType")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "string", paramType = "path"),
      })
	public List<StockMyDataDto> getStockMyDatasByType(String type);
    
//    @ApiOperation(value="getStockMyDatasByTypeReverse", notes="getStockMyDatasByTypeReversal")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "string", paramType = "path"),
//      })
//    public List<StockMyDataDto> getStockMyDatasByTypeReverse(String type);
	
	@ApiOperation(value="getStockData", notes="getStockData")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
      })
	public StockDataDto getStockData(String id);
    
    @ApiOperation(value="getNewsExcludeBystockId4All", notes="getNewsExcludeBystockId4All")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
        @ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
        @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
    })
    public PageDto<StockNewsDto> getNewsExcludeBystockId4All(String stockId, int curPage, int pageSize);
    
    @ApiOperation(value="getNewsIncludeBystockId4All", notes="getNewsIncludeBystockId4All")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
        @ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
        @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
    })
    public PageDto<StockNewsDto> getNewsIncludeBystockId4All(String stockId, int curPage, int pageSize);
	
	@ApiOperation(value="getNewsExcludeBystockId", notes="getNewsExcludeBystockId")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
		@ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
	})
	public PageDto<StockNewsDto> getNewsExcludeBystockId(String stockId, String selectedType, int curPage, int pageSize);
    
    @ApiOperation(value="getNewsIncludeBystockId", notes="getNewsIncludeBystockId")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "path"),
        @ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "path"),
        @ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
        @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
    })
    public PageDto<StockNewsDto> getNewsIncludeBystockId(String stockId, String selectedType, int curPage, int pageSize);
	
	@ApiOperation(value="getImportantNewsExclude", notes="getImportantNewsExclude")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
        @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
    })
	public PageDto<StockImportantNewsDto> getImportantNewsExclude(int curPage, int pageSize);
	
	@ApiOperation(value="getImportantNewsInclude", notes="getImportantNewsInclude")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "curPage", value = "curPage", required = true, dataType = "int", paramType = "path"),
        @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path"),
    })
	public PageDto<StockImportantNewsDto> getImportantNewsInclude(int curPage, int pageSize);
	
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
	public void changeStockMySelected(ChangeStockMySelectedDto changeStockMySelectedDto);
	
	@ApiOperation(value="changeStockMySelectedType", notes="changeStockMySelectedType")
	public void changeStockMySelectedType(ChangeStockMySelectedTypeParams changeStockMySelectedTypeParams);
    
    @ApiOperation(value="saveAllStockMySelected", notes="saveAllStockMySelected")
    public void saveAllStockMySelected(ChangeStockMySelectedDto changeStockMySelectedDto);
	
	@ApiOperation(value="renameStockMydataName", notes="renameStockMydataName")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "string", paramType = "query"),
    })
	public void renameStockMydataName(String selectedType, String  name);
	
    
    @ApiOperation(value="removeOneStockMySelected", notes="removeOneStockMySelected")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "query"),
    })
    public void removeOneStockMySelected(String stockId, String selectedType);
	
	@ApiOperation(value="removeStockMySelected", notes="removeStockMySelected")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "selectedType", value = "selectedType", required = true, dataType = "string", paramType = "query"),
	})
	public void removeStockMySelected(String selectedType);
	
	@ApiOperation(value="uploadStockDailyTransactions", notes="uploadStockDailyTransactions")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "form")
	})
	public void uploadStockDailyTransactions(MultipartFile file) throws IOException;
	
	@ApiOperation(value="getStockDailyTransactions", notes="getStockDailyTransactions")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "startDate", value = "startDate", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "endDate", required = true, dataType = "string", paramType = "query")
    })
	public List<StockDailyTransactionsDto> getStockDailyTransactions(String startDate, String endDate);

    @ApiOperation(value="search4StockData", notes="search4StockData")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "query", value = "query", required = true, dataType = "string", paramType = "query")
    })
	public List<StockDataDto> search4StockData(String query);

    @ApiOperation(value="search4StockMyData", notes="search4StockMyData")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "query", value = "query", required = true, dataType = "string", paramType = "query")
    })
    public List<StockDataDto> search4StockMyData(String query);
    
    @ApiOperation(value="getStockNewsKeyByInclude", notes="getStockNewsKeyByInclude")
    public List<StockNewsKeyDto> getStockNewsKeyByInclude();
    
    @ApiOperation(value="getStockNewsKeyByExclude", notes="getStockNewsKeyByExclude")
    public List<StockNewsKeyDto> getStockNewsKeyByExclude();
    
    @ApiOperation(value="saveStockNewsKeyByInclude", notes="saveStockNewsKeyByInclude")
    public void saveStockNewsKeyByInclude(List<String> keys);
    
    @ApiOperation(value="saveStockNewsKeyByExclude", notes="saveStockNewsKeyByExclude")
    public void saveStockNewsKeyByExclude(List<String> keys);

    @ApiOperation(value="saveCompanyStatus", notes="saveCompanyStatus")
    public void saveCompanyStatus(String stockId, String companyStatus);
    
    @ApiOperation(value="fetchLatestNews", notes="fetchLatestNews")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
    })
    public void fetchLatestNews(String stockId);
    
    @ApiOperation(value="fetchNews", notes="fetchNews")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "fetchPage", value = "fetchPage", required = true, dataType = "string", paramType = "query")
    })
    public void fetchNews(String stockId, int fetchPage);
    
    @ApiOperation(value="fetchImportantLatestNews", notes="fetchImportantLatestNews")
    public void fetchImportantLatestNews();
    
    @ApiOperation(value="fetchImportantNews", notes="fetchImportantNews")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "fetchPage", value = "fetchPage", required = true, dataType = "string", paramType = "query")
    })
    public void fetchImportantNews(int fetchPage);
    
    @ApiOperation(value="selectHistory", notes="selectHistory")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "startDate", value = "startDate", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "endDate", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "integer", paramType = "query")
    })
    public List<StockHistoryDto> selectHistory(String stockId, String startDate, String endDate, int type);
    
    @ApiOperation(value="selectLastDayHistory", notes="selectLastDayHistory")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
    })
    public List<StockHistoryDto> selectLastDayHistory(String stockId);
    
    @ApiOperation(value="selectLastWeekHistory", notes="selectLastWeekHistory")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
    })
    public List<StockHistoryDto> selectLastWeekHistory(String stockId);
    
    @ApiOperation(value="selectLastMonthHistory", notes="selectLastMonthHistory")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
    })
    public List<StockHistoryDto> selectLastMonthHistory(String stockId);
    
    // test
    @ApiOperation(value="fetchAllHistory", notes="fetchAllHistory")
    public String fetchAllHistory();
    
    // test
    @ApiOperation(value="refetchAllHistory", notes="refetchAllHistory")
    public String refetchAllHistory();
    
    // test
    @ApiOperation(value="fetchHistory", notes="fetchHistory")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "no", value = "no", required = true, dataType = "string", paramType = "query")
    })
    public String fetchHistory(String no);
    
    @ApiOperation(value="fetchCurrentHistoryDaily", notes="fetchCurrentHistoryDaily")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query")
    })
    public void fetchCurrentHistoryDaily(String stockId);
    /*@ApiOperation(value="data", notes="data")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stockId", value = "stockId", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "startDate", value = "startDate", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "endDate", required = true, dataType = "string", paramType = "query")
    })
    public String data(String stockId, String startDate, String endDate);*/
}
