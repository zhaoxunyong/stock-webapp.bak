package org.stock.fetch.service;

import java.util.List;

import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockNews;

public interface StockService {
	public List<StockMyData> getStockMyDatas();
	
	public List<StockMyData> getStockMyDatasByType(Long type);
	
	public StockData getStockData(Long id);
	
	public List<StockNews> getNewsBystockId(Long stockId, int curPage, int pageSize);
	
	public int getNewsCountBystockId(Long stockId);
	
	public List<StockImportantNews> getImportantNews(int curPage, int pageSize);
	
	public int getImportantNewsCount();
	
	public List<StockMySelectedType> getStockMySelectedTypes();
	
	public void saveStockMySelectedType(StockMySelectedType stockMySelectedType);
	
	public void changeStockMySelected(Long stockId, Long selectedType);
	
	public void removeStockMySelected(Long stockId, Long selectedType);
}
