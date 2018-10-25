package org.stock.fetch.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stock.fetch.constant.StockHistoryEnum;
import org.stock.fetch.constant.StockNewsKeyTypeEnum;
import org.stock.fetch.dao.StockDailyTransactionsMapper;
import org.stock.fetch.dao.StockDataMapper;
import org.stock.fetch.dao.StockHistoryDailyMapper;
import org.stock.fetch.dao.StockHistoryMapper;
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
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockHistoryDaily;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelected;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockNewsKey;
import org.stock.fetch.service.StockService;
import org.stock.utils.MyDateUtils;

import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.google.common.collect.Lists;

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
    
    @Autowired
    private StockHistoryMapper stockHistoryMapper;
    
    @Autowired
    private StockHistoryDailyMapper stockHistoryDailyMapper;

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
    public List<StockNews> getNewsExcludeBystockId4All(Long stockId, int startNo, int pageSize) {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectExcludeByStockId4All(stockId, excludeKeys, startNo, pageSize);
    }

    @Override
    public List<StockNews> getNewsIncludeBystockId4All(Long stockId, int startNo, int pageSize) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectIncludeByStockId4All(stockId, excludeKeys, includeKeys, startNo, pageSize);
    }

    @Override
    public int getNewsExcludeCountBystockId4All(Long stockId) {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.excludeCount4All(stockId, excludeKeys);
    }

    @Override
    public int getNewsIncludeCountBystockId4All(Long stockId) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.includeCount4All(stockId, excludeKeys, includeKeys);
    }

	@Override
	public List<StockNews> getNewsExcludeBystockId(Long stockId, Long selectedType, int startNo, int pageSize) {
	    List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectExcludeByStockId(stockId, selectedType, excludeKeys, startNo, pageSize);
	}

    @Override
    public List<StockNews> getNewsIncludeBystockId(Long stockId, Long selectedType, int startNo, int pageSize) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.selectIncludeByStockId(stockId, selectedType, excludeKeys, includeKeys, startNo, pageSize);
    }

    @Override
    public int getNewsExcludeCountBystockId(Long stockId, Long selectedType) {
        List<StockNewsKey> stockNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        String excludeKeys = stockNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.excludeCount(stockId, selectedType, excludeKeys);
    }

    @Override
    public int getNewsIncludeCountBystockId(Long stockId, Long selectedType) {
        List<StockNewsKey> stockExcludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.EXCLUDE.getType());
        List<StockNewsKey> stockIncludeNewsKeys = stockNewsKeyMapper.selectByType(StockNewsKeyTypeEnum.INCLUDE.getType());
        String excludeKeys = stockExcludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        String includeKeys = stockIncludeNewsKeys.stream()
                .map(StockNewsKey::getKey)
                .collect(Collectors.joining(","));
        return stockNewsMapper.includeCount(stockId, selectedType, excludeKeys, includeKeys);
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
        long id = IdUtils.genLongId();
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
	            stockMySelected.setId(id--);
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

    @Override
    public List<StockHistory> selectHistory(long stockId, Date startDate, Date endDate, int type) {
        List<StockHistory> actuallyStockHistorys = Lists.newArrayList();
        // 始终通过日来计算,第一条记录是最新的
        List<StockHistory> stockHistorys = stockHistoryMapper.selectStockHistory(stockId, startDate, endDate, StockHistoryEnum.DAY.getType());
        // 是否有当日的数据？
        if(!stockHistorys.isEmpty()) {
            Date lastDate = stockHistorys.get(0).getDate();
            if(!MyDateUtils.date2LoalDate(lastDate).isEqual(LocalDate.now())) {
                // 获取当天的数据,最后一条记录是最新的
                List<StockHistoryDaily> stockHistoryDailys = this.selectCurrentStockHistoryDailys(stockId);
                if(stockHistoryDailys!=null && !stockHistoryDailys.isEmpty()) {
                    // 時間   買價  賣價  成交價 漲跌  單量  總量
                    BigDecimal opening = stockHistoryDailys.get(0).getVol();
                    BigDecimal closing = stockHistoryDailys.get(stockHistoryDailys.size()-1).getVol();
                    BigDecimal lowest = stockHistoryDailys.stream().map(StockHistoryDaily::getVol).reduce(BigDecimal::min).get();
                    BigDecimal highest = stockHistoryDailys.stream().map(StockHistoryDaily::getVol).reduce(BigDecimal::max).get();
                    BigDecimal vol = stockHistoryDailys.stream().map(StockHistoryDaily::getVol).reduce(new BigDecimal(0), (a, b) -> a.add(b));
                    StockHistory currentStockHistory = new StockHistory();
                    currentStockHistory.setId(IdUtils.genLongId());
                    currentStockHistory.setStockId(stockId);
                    currentStockHistory.setType(StockHistoryEnum.DAY.getType());
                    
                    currentStockHistory.setOpening(opening);
                    currentStockHistory.setClosing(closing);
                    currentStockHistory.setLowest(lowest);
                    currentStockHistory.setHighest(highest);
                    currentStockHistory.setVol(vol);
                    currentStockHistory.setDate(new Date());
                    stockHistorys.add(0, currentStockHistory);
                }
            }
        }
        if (type == StockHistoryEnum.DAY.getType()) {
            actuallyStockHistorys.addAll(stockHistorys);
        } else if (type == StockHistoryEnum.WEEK.getType()) {
            List<BigDecimal> openings = Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> closings =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> lowests =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> highests =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> vols =Lists.newArrayList(); //从近到远的顺序添加
            Date lastStartDateOfWeek = null;
            for (StockHistory stockHistory : stockHistorys) {
                // 结束日期应该是当时那条记录的日期
                Date endDateOfWeek = stockHistory.getDate();
                LocalDate localDate = MyDateUtils.date2LoalDate(endDateOfWeek);
                // 超过日期时，先计算出上次的结果
                if (lastStartDateOfWeek != null && endDateOfWeek.getTime() < lastStartDateOfWeek.getTime()) {
                    // 以startDateOfWeek(（取下一个自然工作日）)作为周的唯一判断,一周只能有一和记录
                    Date date = MyDateUtils.getNextNatureWorkDay(lastStartDateOfWeek);
                    StockHistory tempStockHistory = new StockHistory();
                    tempStockHistory.setId(IdUtils.genLongId());
                    tempStockHistory.setStockId(stockId);
                    tempStockHistory.setOpening(openings.get(openings.size()-1));  // 第一天的开盘
                    tempStockHistory.setClosing(closings.get(0));  // 最后一天的收盘
                    tempStockHistory.setLowest(lowests.stream().reduce(BigDecimal::min).get());    // 这周中最低的
                    tempStockHistory.setHighest(highests.stream().reduce(BigDecimal::max).get());  // 这周中最高的
                    tempStockHistory.setVol(vols.stream().reduce(new BigDecimal(0), (a, b) -> a.add(b))); // 总和
                    tempStockHistory.setDate(date);
                    actuallyStockHistorys.add(tempStockHistory);
                    // 重置数据
                    openings = Lists.newArrayList();
                    closings = Lists.newArrayList();
                    lowests = Lists.newArrayList();
                    highests = Lists.newArrayList();
                    vols = Lists.newArrayList();
                }
                
                // 一周的第一天
                LocalDate firstDateOfWeek = MyDateUtils.getFirstDayOfWeek(localDate);
                Date startDateOfWeek = MyDateUtils.localDate2Date(firstDateOfWeek);
                if (endDateOfWeek.getTime() >= startDateOfWeek.getTime()) {
                    // 计算
                    openings.add(stockHistory.getOpening());
                    closings.add(stockHistory.getClosing());
                    lowests.add(stockHistory.getLowest());
                    highests.add(stockHistory.getHighest());
                    vols.add(stockHistory.getVol());
                }
                // 当为最后一条或者第一条记录时，直接计算
                if (stockHistorys.get(stockHistorys.size()-1).getId().equals(stockHistory.getId())) {
                    // 以startDateOfWeek(（取下一个自然工作日）)作为周的唯一判断,一周只能有一和记录
                    Date date = MyDateUtils.getNextNatureWorkDay(startDateOfWeek);
                    StockHistory tempStockHistory = new StockHistory();
                    tempStockHistory.setId(IdUtils.genLongId());
                    tempStockHistory.setStockId(stockId);
                    tempStockHistory.setOpening(openings.get(openings.size()-1));  // 第一天的开盘
                    tempStockHistory.setClosing(closings.get(0));  // 最后一天的收盘
                    tempStockHistory.setLowest(lowests.stream().reduce(BigDecimal::min).get());    // 这周中最低的
                    tempStockHistory.setHighest(highests.stream().reduce(BigDecimal::max).get());  // 这周中最高的
                    tempStockHistory.setVol(vols.stream().reduce(new BigDecimal(0), (a, b) -> a.add(b))); // 总和
                    tempStockHistory.setDate(date);
                    actuallyStockHistorys.add(tempStockHistory);
                    // 重置数据
                    openings = Lists.newArrayList();
                    closings = Lists.newArrayList();
                    lowests = Lists.newArrayList();
                    highests = Lists.newArrayList();
                    vols = Lists.newArrayList();
                }
                lastStartDateOfWeek = startDateOfWeek;
                
            }
            
        } else if (type == StockHistoryEnum.MONTH.getType()) {
            List<BigDecimal> openings = Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> closings =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> lowests =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> highests =Lists.newArrayList(); //从近到远的顺序添加
            List<BigDecimal> vols =Lists.newArrayList(); //从近到远的顺序添加
            Date lastStartDateOfMonth = null;
            for (StockHistory stockHistory : stockHistorys) {
                // 结束日期应该是当时那条记录的日期
                Date endDateOfMonth = stockHistory.getDate();
                LocalDate localDate = MyDateUtils.date2LoalDate(endDateOfMonth);
                // 超过日期时，先计算出上次的结果
                if (lastStartDateOfMonth != null && endDateOfMonth.getTime() < lastStartDateOfMonth.getTime()) {
                    // 以startDateOfWeek(（取下一个自然工作日）)作为周的唯一判断,一周只能有一和记录
                    Date date = MyDateUtils.getNextNatureWorkDay(lastStartDateOfMonth);
                    StockHistory tempStockHistory = new StockHistory();
                    tempStockHistory.setId(IdUtils.genLongId());
                    tempStockHistory.setStockId(stockId);
                    tempStockHistory.setOpening(openings.get(openings.size()-1));  // 第一天的开盘
                    tempStockHistory.setClosing(closings.get(0));  // 最后一天的收盘
                    tempStockHistory.setLowest(lowests.stream().reduce(BigDecimal::min).get());    // 这周中最低的
                    tempStockHistory.setHighest(highests.stream().reduce(BigDecimal::max).get());  // 这周中最高的
                    tempStockHistory.setVol(vols.stream().reduce(new BigDecimal(0), (a, b) -> a.add(b))); // 总和
                    tempStockHistory.setDate(date);
                    actuallyStockHistorys.add(tempStockHistory);
                    // 重置数据
                    openings = Lists.newArrayList();
                    closings = Lists.newArrayList();
                    lowests = Lists.newArrayList();
                    highests = Lists.newArrayList();
                    vols = Lists.newArrayList();
                }
                
                // 月
                LocalDate firstDateOfMonth = MyDateUtils.getFirstDayOfMonth(localDate);
                Date startDateOfMonth = MyDateUtils.localDate2Date(firstDateOfMonth);
                if (endDateOfMonth.getTime() >= startDateOfMonth.getTime()) {
                    // 计算
                    openings.add(stockHistory.getOpening());
                    closings.add(stockHistory.getClosing());
                    lowests.add(stockHistory.getLowest());
                    highests.add(stockHistory.getHighest());
                    vols.add(stockHistory.getVol());
                }
                // 当为最后一条或者第一条记录时，直接计算
                if (stockHistorys.get(stockHistorys.size()-1).getId().equals(stockHistory.getId())) {
                    // 以startDateOfMonth作为月（取下一个自然工作日）的唯一判断,一月只能有一和记录
                    Date date = MyDateUtils.getNextNatureWorkDay(startDateOfMonth);
                    StockHistory tempStockHistory = new StockHistory();
                    tempStockHistory.setId(IdUtils.genLongId());
                    tempStockHistory.setStockId(stockId);
                    tempStockHistory.setOpening(openings.get(openings.size()-1));  // 第一天的开盘
                    tempStockHistory.setClosing(closings.get(0));  // 最后一天的收盘
                    tempStockHistory.setLowest(lowests.stream().reduce(BigDecimal::min).get());    // 这周中最低的
                    tempStockHistory.setHighest(highests.stream().reduce(BigDecimal::max).get());  // 这周中最高的
                    tempStockHistory.setVol(vols.stream().reduce(new BigDecimal(0), (a, b) -> a.add(b))); // 总和
                    tempStockHistory.setDate(date);
                    actuallyStockHistorys.add(tempStockHistory);
                    // 重置数据
                    openings = Lists.newArrayList();
                    closings = Lists.newArrayList();
                    lowests = Lists.newArrayList();
                    highests = Lists.newArrayList();
                    vols = Lists.newArrayList();
                }
                lastStartDateOfMonth = startDateOfMonth;
                
            }
        }
        return actuallyStockHistorys;
    }
    
    @Override
    public StockHistory selectWeekOrMonthStockHistory(long stockId, Date startDate, Date endDate, int type) {
        return stockHistoryMapper.selectWeekOrMonthStockHistory(stockId, startDate, endDate, type);
    }

    @Override
    public StockHistory averageClosing(Long stockId, Date date, int type) {
         return stockHistoryMapper.averageClosing(stockId, date, type);
    }

    @Override
    public StockHistory averageVol(Long stockId, Date date, int type) {
         return stockHistoryMapper.averageVol(stockId, date, type);
    }

    @Override
    public List<StockHistoryDaily> selectCurrentStockHistoryDailys(Long stockId) {
        Date startDate = MyDateUtils.localDate2Date(LocalDate.now());
        Date endDate = new Date();
        return stockHistoryDailyMapper.selectStockHistoryDailys(stockId, startDate, endDate);
    }

}
