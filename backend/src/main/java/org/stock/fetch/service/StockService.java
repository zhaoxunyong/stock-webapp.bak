package org.stock.fetch.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.stock.fetch.constant.StockNewsKeyTypeEnum;
import org.stock.fetch.model.ChangeStockMySelectedType;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockHistoryDaily;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockNewsKey;

public interface StockService {
	StockMyData StockMyData(Long stockId);
	
	List<StockMyData> getStockMyDatas();
	
	List<StockMyData> getStockMyDatasByType(Long type);
	
	List<StockMyStore> getStockMyDatasByStore();
	
	StockData getStockData(Long id);

    List<StockNews> getNewsExcludeBystockId4All(Long stockId, int startNo, int pageSize);

    List<StockNews> getNewsIncludeBystockId4All(Long stockId, int startNo, int pageSize);

    int getNewsExcludeCountBystockId4All(Long stockId);

    int getNewsIncludeCountBystockId4All(Long stockId);
	
	List<StockNews> getNewsExcludeBystockId(Long stockId, Long selectedType, int curPage, int pageSize);
	
	List<StockNews> getNewsIncludeBystockId(Long stockId, Long selectedType,  int curPage, int pageSize);
	
	int getNewsExcludeCountBystockId(Long stockId, Long selectedType);
	
	int getNewsIncludeCountBystockId(Long stockId, Long selectedType);
	
	List<StockImportantNews> getImportantNewsExclude(int curPage, int pageSize);
	
	List<StockImportantNews> getImportantNewsInclude(int curPage, int pageSize);
	
	int getImportantNewsExcludeCount();
	
	int getImportantNewsIncludeCount();
	
	List<StockMySelectedType> getStockMySelectedTypes();
	
	List<StockMySelectedType> getMySelectedTypesByStockId(Long stockId);
	
	void saveStockMySelectedType(StockMySelectedType stockMySelectedType);
	
	void changeStockMySelected(List<Long> stockIds, Long selectedType);
    
    void changeStockMySelectedType(List<ChangeStockMySelectedType> changeStockMySelectedTypes);
	
	void saveAllStockMySelected(List<Long> stockIds, Long selectedType);
	
	void renameStockMydataName(Long selectedType, String name) ;
	
	void removeOneStockMySelected(Long stockId, Long selectedType);
	
	void removeStockMySelected(Long selectedType);
	
	List<StockDailyTransactions> getStockDailyTransactions(Date startDate, Date endDate);
	
	List<StockData> search4StockData(String value);
    
    List<StockData> search4StockMyData(String value);
    
    List<StockNewsKey> getStockNewsKeyByInclude();
    
    List<StockNewsKey> getStockNewsKeyByexclude();
    
    void saveStockNewsKeys(List<StockNewsKey> stockNewsKey);
    
    int deleteByType(StockNewsKeyTypeEnum stockNewsKeyTypeEnum);

    void saveCompanyStatus(Long stockId, String companyStatus);
    
    List<StockHistory> selectHistory(long stockId, Date startDate, Date endDate, int type);
    
    List<StockHistoryDaily> selectCurrentStockHistoryDailys(Long stockId);
    
    StockHistory selectWeekOrMonthStockHistory(long stockId, Date startDate, Date endDate, int type);
    
    StockHistory averageClosing(@Param("stockId") Long stockId, @Param("date") Date date, int type);
    
    StockHistory averageVol(@Param("stockId") Long stockId, @Param("date") Date date, int type);
}
