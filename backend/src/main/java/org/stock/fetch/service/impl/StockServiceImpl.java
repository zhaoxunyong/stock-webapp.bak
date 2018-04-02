package org.stock.fetch.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import org.stock.fetch.model.ChangeStockMySelectedType;
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
import com.aeasycredit.commons.lang.utils.CollectionsUtils;

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
    public org.stock.fetch.model.StockMyData StockMyData(Long stockId) {
        return stockMyDataMapper.selectByStockId(stockId);
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
	public List<StockNews> getNewsExcludeBystockId(Long stockId, int startNo, int pageSize) {
	    List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectExcludeByStockId(stockId, excludeKeys, startNo, pageSize);
	}

    @Override
    public List<StockNews> getNewsIncludeBystockId(Long stockId, int startNo, int pageSize) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectIncludeByStockId(stockId, excludeKeys, includeKeys, startNo, pageSize);
    }

    @Override
    public int getNewsExcludeCountBystockId(Long stockId) {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.excludeCount(stockId, excludeKeys);
    }

    @Override
    public int getNewsIncludeCountBystockId(Long stockId) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.includeCount(stockId, excludeKeys, includeKeys);
    }

	@Override
	public List<StockImportantNews> getImportantNewsExclude(int startNo, int pageSize) {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockImportantNewsMapper.selectExclude(excludeKeys, startNo, pageSize);
	}

    @Override
    public List<StockImportantNews> getImportantNewsInclude(int startNo, int pageSize) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockImportantNewsMapper.selectInclude(excludeKeys, includeKeys, startNo, pageSize);
    }


    @Override
    public int getImportantNewsExcludeCount() {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockImportantNewsMapper.excludeCount(excludeKeys);
    }

    @Override
    public int getImportantNewsIncludeCount() {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockImportantNewsMapper.includeCount(excludeKeys, includeKeys);
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
	public void renameStockMydataName(Long selectedType, String name)  {
        stockMySelectedTypeMapper.renameStockMydataName(selectedType, name);
	}
    
    @Override
    @Transactional
    public void changeStockMySelected(List<Long> stockIds, Long selectedType) {
        for(Long stockId : stockIds) {
            stockMySelectedMapper.deleteByStockId(stockId, selectedType);
            // 是否在個股中，沒有的話，需要添加
            StockMyData stockMyData = stockMyDataMapper.selectByStockId(stockId);
            if(stockMyData == null) {
                stockMyData = new StockMyData();
                stockMyData.setId(IdUtils.genLongId());
                stockMyData.setStockId(stockId);
//              stockMyData.setKinds(kinds);
//              stockMyData.setIndustry(industry);
                stockMyData.setStatus(true);
                stockMyData.setCreateDate(new Date());
                stockMyDataMapper.insert(stockMyData);
            }
            
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
    public void changeStockMySelectedType(List<ChangeStockMySelectedType> changeStockMySelectedTypes) {
        if(changeStockMySelectedTypes != null && !changeStockMySelectedTypes.isEmpty()) {
            stockMySelectedTypeMapper.deleteAll();
            for(ChangeStockMySelectedType changeStockMySelectedType : changeStockMySelectedTypes) {
                StockMySelectedType record = new StockMySelectedType();
                record.setId(IdUtils.genLongId());
                record.setName(changeStockMySelectedType.getName());
                record.setType(changeStockMySelectedType.getType());
                record.setCreateDate(new Date());
                stockMySelectedTypeMapper.insert(record);
            }
        }
    }
	
	@Override
	@Transactional
	public void saveAllStockMySelected(List<Long> stockIds, Long selectedType) {
        stockMySelectedMapper.delete(selectedType);
	    for(Long stockId : stockIds) {
            // 是否在個股中，沒有的話，需要添加
            StockMyData stockMyData = stockMyDataMapper.selectByStockId(stockId);
            if(stockMyData == null) {
                stockMyData = new StockMyData();
                stockMyData.setId(IdUtils.genLongId());
                stockMyData.setStockId(stockId);
//              stockMyData.setKinds(kinds);
//              stockMyData.setIndustry(industry);
                stockMyData.setStatus(true);
                stockMyData.setCreateDate(new Date());
                stockMyDataMapper.insert(stockMyData);
            }
            
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
    public void removeOneStockMySelected(Long stockId, Long selectedType) {
        stockMySelectedMapper.removeOneStockMySelected(stockId, selectedType);
    }

	@Override
	@Transactional
	public void removeStockMySelected(Long selectedType) {
		stockMySelectedMapper.delete(selectedType);
		stockMySelectedTypeMapper.deleteByType(selectedType);
	}

	@Override
	public List<StockDailyTransactions> getStockDailyTransactions(Date startDate, Date endDate) {
		return stockDailyTransactionsMapper.selectByDate(startDate, endDate);
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
    @Transactional
    public void saveStockNewsKeys(List<StockNewsKey> stockNewsKeys) {
        stockNewsKeys.forEach(stockNewsKey -> {
            stockNewsKeyMapper.insert(stockNewsKey);
        });
    }

    @Override
    public int deleteByType(StockNewsKeyTypeEnum stockNewsKeyTypeEnum) {
        return stockNewsKeyMapper.deleteByType(stockNewsKeyTypeEnum.getType());
    }

    @Override
    @Transactional
    public void saveCompanyStatus(Long stockId, String companyStatus) {
        stockDataMapper.saveCompanyStatus(stockId, companyStatus);
    }

}
