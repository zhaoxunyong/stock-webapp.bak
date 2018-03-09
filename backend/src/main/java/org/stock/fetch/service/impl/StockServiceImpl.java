package org.stock.fetch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stock.fetch.constant.StockNewsKeyTypeEnum;
import org.stock.fetch.dao.StockDailyTransactionsMapper;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockImportantNewsMapper;
import org.stock.fetch.dao.StockMyDataMapper;
import org.stock.fetch.dao.StockMySelectedMapper;
import org.stock.fetch.dao.StockMySelectedTypeMapper;
import org.stock.fetch.dao.StockMyStoreMapper;
import org.stock.fetch.dao.StockNewsKeyMapper;
import org.stock.fetch.dao.StockNewsMapper;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelected;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockNewsKey;
import org.stock.fetch.service.StockService;

import com.aeasycredit.commons.lang.idgenerator.IdUtils;

@Service
public class StockServiceImpl implements StockService {
	
    @Autowired
    private StockMyDataMapper stockMyDataMapper;
	
    @Autowired
    private StockDataMapper stockDataMapper;
	
    @Autowired
    private StockNewsMapper stockNewsMapper;
	
    @Autowired
    private StockImportantNewsMapper stockImportantNewsMapper;
    
    @Autowired
    private StockMySelectedTypeMapper stockMySelectedTypeMapper;
    
    @Autowired
    private StockDailyTransactionsMapper stockDailyTransactionsMapper;
    
    @Autowired
    private StockMySelectedMapper stockMySelectedMapper;
    
    @Autowired
    private StockMyStoreMapper stockMyStoreMapper;
    
    @Autowired
    private StockNewsKeyMapper stockNewsKeyMapper;

	@Override
	public List<StockMyData> getStockMyDatas() {
		return stockMyDataMapper.selectAll();
	}

    @Override
	public List<StockMyStore> getStockMyDatasByStore() {
        return stockMyStoreMapper.getStockMyDatasByStore();
	}
	
	@Override
	public List<StockMyData> getStockMyDatasByType(Long type) {
		return stockMyDataMapper.getStockMyDatasByType(type);
	}

	@Override
	public StockData getStockData(Long id) {
		return stockDataMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StockNews> getNewsBystockId(Long stockId, int startNo, int pageSize) {
		return stockNewsMapper.selectByStockId(stockId, startNo, pageSize);
	}

	@Override
	public int getNewsCountBystockId(Long stockId) {
		return stockNewsMapper.count(stockId);
	}

	@Override
	public List<StockImportantNews> getImportantNews(int startNo, int pageSize) {
		return stockImportantNewsMapper.selectAll(startNo, pageSize);
	}

	@Override
	public int getImportantNewsCount() {
		return stockImportantNewsMapper.count();
	}

	@Override
	public List<StockMySelectedType> getStockMySelectedTypes() {
		return stockMySelectedTypeMapper.selectAll();
	}

	@Override
	public List<StockMySelectedType> getMySelectedTypesByStockId(Long stockId) {
		return stockMySelectedTypeMapper.selectByStockId(stockId);
	}

	@Override
	@Transactional
	public void saveStockMySelectedType(StockMySelectedType stockMySelectedType) {
		stockMySelectedTypeMapper.insert(stockMySelectedType);
	}
	
	@Override
	@Transactional
	public void changeStockMySelected(List<Long> stockIds, Long selectedType) {
	    stockMySelectedMapper.delete(selectedType);
	    for(Long stockId : stockIds) {
	        StockMySelected stockMySelected = stockMySelectedMapper.select(stockId, selectedType);
	        if(stockMySelected == null) {
	            // insert
	            stockMySelected = new StockMySelected();
	            stockMySelected.setId(IdUtils.genLongId());
	            stockMySelected.setSelectedType(selectedType);
	            stockMySelected.setStockId(stockId);
	            stockMySelected.setStatus(true);
	            stockMySelected.setCreateDate(new Date());
	            stockMySelectedMapper.insert(stockMySelected);
	        } else {
	            // update
	            stockMySelectedMapper.update(stockId, selectedType);
	        }
	    }
	}

	@Override
	@Transactional
	public void removeStockMySelected(Long selectedType) {
		stockMySelectedMapper.delete(selectedType);
		stockMySelectedTypeMapper.deleteByType(selectedType);
	}

	@Override
	public List<StockDailyTransactions> getStockDailyTransactions() {
		return stockDailyTransactionsMapper.selectAll();
	}

    @Override
    public List<StockData> search4StockData(String value) {
        return stockDataMapper.search4StockData(value);
    }

    @Override
    public List<StockData> search4StockMyData(String value) {
        return stockDataMapper.search4StockMyData(value);
    }

    @Override
    public List<StockNewsKey> getStockNewsKeyByInclude() {
        return stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
    }

    @Override
    public List<StockNewsKey> getStockNewsKeyByexclude() {
        return stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
    }

    @Override
    public void saveStockNewsKeys(List<StockNewsKey> stockNewsKeys) {
        stockNewsKeys.forEach(stockNewsKey -> {
            stockNewsKeyMapper.insert(stockNewsKey);
        });
    }

    @Override
    public int deleteByType(StockNewsKeyTypeEnum stockNewsKeyTypeEnum) {
        return stockNewsKeyMapper.deleteByType(stockNewsKeyTypeEnum.getType());
    }

}
