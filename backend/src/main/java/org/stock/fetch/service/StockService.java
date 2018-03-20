package org.stock.fetch.service;

import java.util.Date;
import java.util.List;

import org.stock.fetch.constant.StockNewsKeyTypeEnum;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockNewsKey;

public interface StockService {
	public List<StockMyData> getStockMyDatas();
	
	public List<StockMyData> getStockMyDatasByType(Long type);
	
	public List<StockMyStore> getStockMyDatasByStore();
	
	public StockData getStockData(Long id);
	
	public List<StockNews> getNewsExcludeBystockId(Long stockId, int curPage, int pageSize);
	
	public List<StockNews> getNewsIncludeBystockId(Long stockId, int curPage, int pageSize);
	
	public int getNewsExcludeCountBystockId(Long stockId);
	
	public int getNewsIncludeCountBystockId(Long stockId);
	
	public List<StockImportantNews> getImportantNewsExclude(int curPage, int pageSize);
	
	public List<StockImportantNews> getImportantNewsInclude(int curPage, int pageSize);
	
	public int getImportantNewsExcludeCount();
	
	public int getImportantNewsIncludeCount();
	
	public List<StockMySelectedType> getStockMySelectedTypes();
	
	public List<StockMySelectedType> getMySelectedTypesByStockId(Long stockId);
	
	public void saveStockMySelectedType(StockMySelectedType stockMySelectedType);
	
	public void changeStockMySelected(List<Long> stockIds, Long selectedType);
	
	public void removeStockMySelected(Long selectedType);
	
	public List<StockDailyTransactions> getStockDailyTransactions(Date startDate, Date endDate);
	
	public List<StockData> search4StockData(String value);
    
    public List<StockData> search4StockMyData(String value);
    
    public List<StockNewsKey> getStockNewsKeyByInclude();
    
    public List<StockNewsKey> getStockNewsKeyByexclude();
    
    public void saveStockNewsKeys(List<StockNewsKey> stockNewsKey);
    
    public int deleteByType(StockNewsKeyTypeEnum stockNewsKeyTypeEnum);

    public void saveCompanyStatus(Long stockId, String companyStatus) ;
}
