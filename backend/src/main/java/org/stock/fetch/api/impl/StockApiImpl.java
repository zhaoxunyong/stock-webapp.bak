package org.stock.fetch.api.impl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.stock.config.StockProperties;
import org.stock.fetch.api.StockApi;
import org.stock.fetch.api.dto.ChangeStockMySelectedDto;
import org.stock.fetch.api.dto.ChangeStockMySelectedTypeDto;
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
import org.stock.fetch.constant.StockNewsKeyTypeEnum;
import org.stock.fetch.model.ChangeStockMySelectedType;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockHistory;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockMyStore;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.model.StockNewsKey;
import org.stock.fetch.service.FetchService;
import org.stock.fetch.service.StockService;
import org.stock.fetch.tasks.ScheduledTasks;

import com.aeasycredit.commons.lang.exception.BusinessException;
import com.aeasycredit.commons.lang.idgenerator.IdUtils;
import com.aeasycredit.commons.lang.utils.CollectionsUtils;
import com.aeasycredit.commons.lang.utils.DatesUtils;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
@EnableConfigurationProperties(StockProperties.class)
public class StockApiImpl implements StockApi {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private FetchService fetchService;
	
	@Autowired
	private StockProperties stockProperties;

	@Override
    @RequestMapping(value = "/getStockMyDatas", method = GET)
	public List<StockMyDataDto> getStockMyDatas() {
		List<StockMyData> stockDatas = stockService.getStockMyDatas();
		List<StockMyDataDto> dtoList = stockDatas.stream().map(model -> {
			return modelMapper.map(model, StockMyDataDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

    @Override
    @RequestMapping(value = "/getStockMyDatasByStore", method = GET)
	public List<StockMyStoreDto> getStockMyDatasByStore() {
        List<StockMyStore> stockMyStores = stockService.getStockMyDatasByStore();
        List<StockMyStoreDto> dtoList = stockMyStores.stream().map(model -> {
            return modelMapper.map(model, StockMyStoreDto.class);
        }).collect(Collectors.toList());
        return dtoList;
	}
    
    /**
     * 用于左侧股票列表时降序显示，因为在自行股列表中是升序，两个用的是同一个方法
    
    @Override
    @RequestMapping(value = "/getStockMyDatasByTypeReverse/{type}", method = GET)
    public List<StockMyDataDto> getStockMyDatasByTypeReverse(@PathVariable String type) {
        List<StockMyData> stockDatas = stockService.getStockMyDatasByType(Long.parseLong(type));
        Collections.sort(stockDatas, new Comparator<StockMyData>() {

            @Override
            public int compare(StockMyData o1, StockMyData o2) {
                if(o2.getId() > o1.getId()) {
                    return 1;
                } else  if(o2.getId() < o1.getId()) {
                    return 1;
                }
                return 0;
            }
            
        });
        List<StockMyDataDto> dtoList = stockDatas.stream().map(model -> {
            return modelMapper.map(model, StockMyDataDto.class);
        })
//        .sorted(Collections.reverseOrder())
        .collect(Collectors.toList());
        return dtoList;
    } */

	@Override
    @RequestMapping(value = "/getStockMyDatasByType/{type}", method = GET)
	public List<StockMyDataDto> getStockMyDatasByType(@PathVariable String type) {
		List<StockMyData> stockDatas = stockService.getStockMyDatasByType(Long.parseLong(type));
		List<StockMyDataDto> dtoList = stockDatas.stream().map(model -> {
			return modelMapper.map(model, StockMyDataDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
    @RequestMapping(value = "/getStockData/{id}", method = GET)
	public StockDataDto getStockData(@PathVariable String id) {
		StockData stockData = stockService.getStockData(Long.parseLong(id));
		StockDataDto dto = modelMapper.map(stockData, StockDataDto.class);
		return dto;
	}

	/*@Override
    @RequestMapping(value = "/getNewsBystockId/{stockId}/{curPage}/{pageSize}", method = GET)
	public PageDto<StockNewsDto> getNewsBystockId(@PathVariable String stockId, @PathVariable int curPage, @PathVariable int pageSize) {
		PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
		
		List<StockNews> stockNewses = stockService.getNewsBystockId(Long.parseLong(stockId), pageDto.getStart(), pageSize);
		List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
			return modelMapper.map(model, StockNewsDto.class);
		}).collect(Collectors.toList());
		
		// 查詢列表記錄總數
        int total = stockService.getNewsCountBystockId(Long.parseLong(stockId));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
	}*/

    @Override
    @RequestMapping(value = "/getNewsExcludeBystockId4All/{stockId}/{curPage}/{pageSize}", method = GET)
    public PageDto<StockNewsDto> getNewsExcludeBystockId4All(@PathVariable String stockId, @PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
        
        List<StockNews> stockNewses = stockService.getNewsExcludeBystockId4All(Long.parseLong(stockId), pageDto.getStart(), pageSize);
        List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
            return modelMapper.map(model, StockNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getNewsExcludeCountBystockId4All(Long.parseLong(stockId));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }

    @Override
    @RequestMapping(value = "/getNewsIncludeBystockId4All/{stockId}/{curPage}/{pageSize}", method = GET)
    public PageDto<StockNewsDto> getNewsIncludeBystockId4All(@PathVariable String stockId, @PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
        
        List<StockNews> stockNewses = stockService.getNewsIncludeBystockId4All(Long.parseLong(stockId), pageDto.getStart(), pageSize);
        List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
            return modelMapper.map(model, StockNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getNewsIncludeCountBystockId4All(Long.parseLong(stockId));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }
	
    @Override
    @RequestMapping(value = "/getNewsExcludeBystockId/{stockId}/{selectedType}/{curPage}/{pageSize}", method = GET)
    public PageDto<StockNewsDto> getNewsExcludeBystockId(@PathVariable String stockId, @PathVariable String selectedType, @PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
        
        List<StockNews> stockNewses = stockService.getNewsExcludeBystockId(Long.parseLong(stockId), Long.parseLong(selectedType), pageDto.getStart(), pageSize);
        List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
            return modelMapper.map(model, StockNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getNewsExcludeCountBystockId(Long.parseLong(stockId), Long.parseLong(selectedType));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }

    @Override
    @RequestMapping(value = "/getNewsIncludeBystockId/{stockId}/{selectedType}/{curPage}/{pageSize}", method = GET)
    public PageDto<StockNewsDto> getNewsIncludeBystockId(@PathVariable String stockId, @PathVariable String selectedType, @PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockNewsDto> pageDto = new PageDto<StockNewsDto>(curPage, pageSize);
        
        List<StockNews> stockNewses = stockService.getNewsIncludeBystockId(Long.parseLong(stockId), Long.parseLong(selectedType), pageDto.getStart(), pageSize);
        List<StockNewsDto> dtoList = stockNewses.stream().map(model -> {
            return modelMapper.map(model, StockNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getNewsIncludeCountBystockId(Long.parseLong(stockId), Long.parseLong(selectedType));
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }

	/*@Override
    @RequestMapping(value = "/getImportantNews/{curPage}/{pageSize}", method = GET)
	public PageDto<StockImportantNewsDto> getImportantNews(@PathVariable int curPage, @PathVariable int pageSize) {
		PageDto<StockImportantNewsDto> pageDto = new PageDto<StockImportantNewsDto>(curPage, pageSize);
		
		List<StockImportantNews> stockImportantNewses = stockService.getImportantNews(pageDto.getStart(), pageSize);
		List<StockImportantNewsDto> dtoList = stockImportantNewses.stream().map(model -> {
			return modelMapper.map(model, StockImportantNewsDto.class);
		}).collect(Collectors.toList());
		
		// 查詢列表記錄總數
        int total = stockService.getImportantNewsCount();
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
	}*/
    
    @Override
    @RequestMapping(value = "/getImportantNewsExclude/{curPage}/{pageSize}", method = GET)
    public PageDto<StockImportantNewsDto> getImportantNewsExclude(@PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockImportantNewsDto> pageDto = new PageDto<StockImportantNewsDto>(curPage, pageSize);
        
        List<StockImportantNews> stockImportantNewses = stockService.getImportantNewsExclude(pageDto.getStart(), pageSize);
        List<StockImportantNewsDto> dtoList = stockImportantNewses.stream().map(model -> {
            return modelMapper.map(model, StockImportantNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getImportantNewsExcludeCount();
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }

    @Override
    @RequestMapping(value = "/getImportantNewsInclude/{curPage}/{pageSize}", method = GET)
    public PageDto<StockImportantNewsDto> getImportantNewsInclude(@PathVariable int curPage, @PathVariable int pageSize) {
        PageDto<StockImportantNewsDto> pageDto = new PageDto<StockImportantNewsDto>(curPage, pageSize);
        
        List<StockImportantNews> stockImportantNewses = stockService.getImportantNewsInclude(pageDto.getStart(), pageSize);
        List<StockImportantNewsDto> dtoList = stockImportantNewses.stream().map(model -> {
            return modelMapper.map(model, StockImportantNewsDto.class);
        }).collect(Collectors.toList());
        
        // 查詢列表記錄總數
        int total = stockService.getImportantNewsIncludeCount();
        
        // 設置分頁信息
        pageDto.setRows(dtoList);
        pageDto.setTotal(total);
        return pageDto;
    }

	@Override
    @RequestMapping(value = "/getStockMySelectedTypes", method = GET)
	public List<StockMySelectedTypeDto> getStockMySelectedTypes() {
		List<StockMySelectedType> stockMySelectedTypes = stockService.getStockMySelectedTypes();
		List<StockMySelectedTypeDto> dtoList = stockMySelectedTypes.stream().map(model -> {
			return modelMapper.map(model, StockMySelectedTypeDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
    @RequestMapping(value = "/getMySelectedTypesByStockId/{stockId}", method = GET)
	public List<StockMySelectedTypeDto> getMySelectedTypesByStockId(@PathVariable String stockId) {
		List<StockMySelectedType> stockMySelectedTypes = stockService.getMySelectedTypesByStockId(Long.parseLong(stockId));
		List<StockMySelectedTypeDto> dtoList = stockMySelectedTypes.stream().map(model -> {
			return modelMapper.map(model, StockMySelectedTypeDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
    @RequestMapping(value = "/saveStockMySelectedType", method = POST)
	public void saveStockMySelectedType(String name) {
		StockMySelectedType stockMySelectedType = new StockMySelectedType();
		stockMySelectedType.setId(IdUtils.genLongId());
		stockMySelectedType.setType(IdUtils.genLongId());
		stockMySelectedType.setName(name);
		stockMySelectedType.setCreateDate(new Date());
		stockService.saveStockMySelectedType(stockMySelectedType);
	}

    @Override
    @RequestMapping(value = "/changeStockMySelected", method = POST)
    public void changeStockMySelected(@RequestBody ChangeStockMySelectedDto changeStockMySelectedDto) {
//      throw new BusinessException("中华人民共和国");
        List<Long> stokIdLongs = changeStockMySelectedDto.getStockIds().stream().map(stockId -> Long.parseLong(stockId)).collect(Collectors.toList());
        stockService.changeStockMySelected(stokIdLongs, Long.parseLong(changeStockMySelectedDto.getSelectedType()));
    }

    @Override
    @RequestMapping(value = "/changeStockMySelectedType", method = POST)
    public void changeStockMySelectedType(@RequestBody ChangeStockMySelectedTypeParams changeStockMySelectedTypeParams) {
        List<ChangeStockMySelectedTypeDto> changeStockMySelectedTypeDtos = changeStockMySelectedTypeParams.getChangeStockMySelectedTypeDtos();
        List<ChangeStockMySelectedType> changeStockMySelectedTypes = changeStockMySelectedTypeDtos.stream().map(model -> {
            return modelMapper.map(model, ChangeStockMySelectedType.class);
        }).collect(Collectors.toList());
        stockService.changeStockMySelectedType(changeStockMySelectedTypes);
    }

	@Override
    @RequestMapping(value = "/saveAllStockMySelected", method = POST)
	public void saveAllStockMySelected(@RequestBody ChangeStockMySelectedDto changeStockMySelectedDto) {
//		throw new BusinessException("中华人民共和国");
	    List<Long> stokIdLongs = changeStockMySelectedDto.getStockIds().stream().map(stockId -> Long.parseLong(stockId)).collect(Collectors.toList());
		stockService.saveAllStockMySelected(stokIdLongs, Long.parseLong(changeStockMySelectedDto.getSelectedType()));
	}

    @Override
    @RequestMapping(value = "/renameStockMydataName", method = POST)
    public void renameStockMydataName(String selectedType, String name) {
        stockService.renameStockMydataName(Long.parseLong(selectedType), name);
    }
	
    @Override
    @RequestMapping(value = "/removeOneStockMySelected", method = POST)
    public void removeOneStockMySelected(String stockId, String selectedType) {
        stockService.removeOneStockMySelected(Long.parseLong(stockId), Long.parseLong(selectedType));
    }

	@Override
    @RequestMapping(value = "/removeStockMySelected", method = POST)
	public void removeStockMySelected(String selectedType) {
		stockService.removeStockMySelected(Long.parseLong(selectedType));
	}
	
	@PostMapping("/uploadStockDailyTransactions")  
    public void uploadStockDailyTransactions(@RequestParam("file") MultipartFile file) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = file.getInputStream();
			if(StringUtils.isBlank(stockProperties.getUploadTempFolder()) || !new File(stockProperties.getUploadTempFolder()).isDirectory()) {
			    throw new BusinessException("UploadTempFolder is empty or is not folder!");
			}
			if(!new File(stockProperties.getUploadTempFolder()).exists()) {
			    new File(stockProperties.getUploadTempFolder()).mkdirs();
			}
			String excelFile = stockProperties.getUploadTempFolder()+"/"+IdUtils.genLongId()+"_"+file.getOriginalFilename();
			output = new FileOutputStream(excelFile);
			IOUtils.copy(input, output);
			fetchService.importBydailyTransactions(excelFile);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
		
//        try {  
//              
//            byte[] bytes = file.getBytes();  
//            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());  
//            Files.write(path, bytes);  
//            redirectAttributes.addFlashAttribute("message", "已经将 '" + file.getOriginalFilename() + "' 的文件上传成功");  
//              
//        } catch (IOException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
          
//        return "redirect:/uploadStatus";  
    }

	@Override
	@GetMapping("/getStockDailyTransactions")
	public List<StockDailyTransactionsDto> getStockDailyTransactions(String startDate, String endDate) {
	    if(StringUtils.isBlank(endDate)) {
	        endDate = DatesUtils.YYMMDD2.toString();
	    }
		List<StockDailyTransactions> stockDailyTransactionses = stockService.getStockDailyTransactions(DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate));
		List<StockDailyTransactionsDto> dtoList = stockDailyTransactionses.stream().map(model -> {
			return modelMapper.map(model, StockDailyTransactionsDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

    @Override
    @GetMapping("/search4StockData")
    public List<StockDataDto> search4StockData(String query) {
        List<StockData> stockDatas = stockService.search4StockData(query);
        List<StockDataDto> dtoList = stockDatas.stream().map(model -> {
            return modelMapper.map(model, StockDataDto.class);
        }).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @GetMapping("/search4StockMyData")
    public List<StockDataDto> search4StockMyData(String query) {
        List<StockData> stockDatas = stockService.search4StockMyData(query);
        List<StockDataDto> dtoList = stockDatas.stream().map(model -> {
            return modelMapper.map(model, StockDataDto.class);
        }).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @GetMapping("/getStockNewsKeyByInclude")
    public List<StockNewsKeyDto> getStockNewsKeyByInclude() {
        List<StockNewsKey> stockNewsKeys = stockService.getStockNewsKeyByInclude();
        List<StockNewsKeyDto> dtoList = stockNewsKeys.stream().map(model -> {
            return modelMapper.map(model, StockNewsKeyDto.class);
        }).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @GetMapping("/getStockNewsKeyByExclude")
    public List<StockNewsKeyDto> getStockNewsKeyByExclude() {
        List<StockNewsKey> stockNewsKeys = stockService.getStockNewsKeyByexclude();
        List<StockNewsKeyDto> dtoList = stockNewsKeys.stream().map(model -> {
            return modelMapper.map(model, StockNewsKeyDto.class);
        }).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @PostMapping("/saveStockNewsKeyByInclude")  
    public void saveStockNewsKeyByInclude(@RequestParam("keys[]") List<String> keys) {
        stockService.deleteByType(StockNewsKeyTypeEnum.INCLUDE);
        List<StockNewsKey> stockNewsKeys = Lists.newArrayList();
        keys.forEach(key -> {
            if(StringUtils.isNotBlank(key)) {
                StockNewsKey stockNewsKey = new StockNewsKey();
                stockNewsKey.setId(IdUtils.genLongId());
                stockNewsKey.setCreateDate(new Date());
                stockNewsKey.setKey(key);
                stockNewsKey.setStatus(true);
                stockNewsKey.setType(StockNewsKeyTypeEnum.INCLUDE.getType());
                stockNewsKeys.add(stockNewsKey);
            }
        });
        if(CollectionsUtils.isNotEmpty(stockNewsKeys)) {
            stockService.saveStockNewsKeys(stockNewsKeys);
        }
    }

    @Override
    @PostMapping("/saveStockNewsKeyByExclude")  
    public void saveStockNewsKeyByExclude(@RequestParam("keys[]") List<String> keys) {
        stockService.deleteByType(StockNewsKeyTypeEnum.EXCLUDE);
        List<StockNewsKey> stockNewsKeys = Lists.newArrayList();
        keys.forEach(key -> {
            if(StringUtils.isNotBlank(key)) {
                StockNewsKey stockNewsKey = new StockNewsKey();
                stockNewsKey.setId(IdUtils.genLongId());
                stockNewsKey.setCreateDate(new Date());
                stockNewsKey.setKey(key);
                stockNewsKey.setStatus(true);
                stockNewsKey.setType(StockNewsKeyTypeEnum.EXCLUDE.getType());
                stockNewsKeys.add(stockNewsKey);
            }
        });
        if(CollectionsUtils.isNotEmpty(stockNewsKeys)) {
            stockService.saveStockNewsKeys(stockNewsKeys);
        }
    }

    @Override
    @RequestMapping(value = "/saveCompanyStatus", method = POST)
    public void saveCompanyStatus(String stockId, String companyStatus) {
        stockService.saveCompanyStatus(Long.parseLong(stockId), companyStatus);
    }

    @Override
    @RequestMapping(value = "/fetchLatestNews", method = POST)
    public void fetchLatestNews(String stockId) {
        if(!ScheduledTasks.IS_FETCH_NEW) {
            ScheduledTasks.IS_FETCH_NEW = true;
            try {
                fetchService.fetchLatestNews(stockService.getStockData(Long.parseLong(stockId)));
            } catch (Exception e) {
                throw new BusinessException(e);
            } finally {
                ScheduledTasks.IS_FETCH_NEW = false;
            }
        } else {
            logger.warn("fetchLatestNews: 後臺已經在抓取數據中...");
        }
    }

    @Override
    @RequestMapping(value = "/fetchNews", method = POST)
    public void fetchNews(String stockId, int fetchPage) {
        if(!ScheduledTasks.IS_FETCH_NEW) {
            ScheduledTasks.IS_FETCH_NEW = true;
            try {
                fetchService.fetchNews(stockService.getStockData(Long.parseLong(stockId)), fetchPage);
            } catch (Exception e) {
                throw new BusinessException(e);
            } finally {
                ScheduledTasks.IS_FETCH_NEW = false;
            }
        } else {
            logger.warn("fetchNews: 後臺已經在抓取數據中...");
        }
    }

    @Override
    @RequestMapping(value = "/fetchImportantLatestNews", method = POST)
    public void fetchImportantLatestNews() {
        if(!ScheduledTasks.IS_FETCH_IMPORTANT_NEW) {
            ScheduledTasks.IS_FETCH_IMPORTANT_NEW = true;
            try {
                fetchService.fetchImportantLatestNews();
            } catch (Exception e) {
                throw new BusinessException(e);
            } finally {
                ScheduledTasks.IS_FETCH_IMPORTANT_NEW = false;
            }
        } else {
            logger.warn("fetchImportantLatestNews: 後臺已經在抓取數據中...");
        }
    }

    @Override
    @RequestMapping(value = "/fetchImportantNews", method = POST)
    public void fetchImportantNews(int fetchPage) {
        if(!ScheduledTasks.IS_FETCH_IMPORTANT_NEW) {
            ScheduledTasks.IS_FETCH_IMPORTANT_NEW = true;
            try {
                fetchService.fetchImportantNews(fetchPage);
            } catch (Exception e) {
                throw new BusinessException(e);
            } finally {
                ScheduledTasks.IS_FETCH_IMPORTANT_NEW = false;
            }
        } else {
            logger.warn("fetchImportantNews: 後臺已經在抓取數據中...");
        }
    }

    @Override
    @GetMapping(value = "/selectHistory")
    public List<StockHistoryDto> selectHistory(String stockId, String startDate, String endDate, int type) {
        List<StockHistory> stockHistorys = stockService.selectHistory(Long.parseLong(stockId), DatesUtils.YYMMDD2.toDate(startDate), DatesUtils.YYMMDD2.toDate(endDate), type);
        
        List<StockHistoryDto> dtoList = stockHistorys.stream().map(model -> {
            return modelMapper.map(model, StockHistoryDto.class);
        }).collect(Collectors.toList());
        return Lists.reverse(dtoList);
    }

    /* (non-Javadoc)
     * @see org.stock.fetch.api.StockApi#fetchAllHistory()
     */
    @Override
    @PostMapping(value = "/fetchAllHistory")
    public String fetchAllHistory() {
        new Thread() {
            public void run() {
                try {
                    fetchService.fetchAllHistory();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }.start();
        return "ok";
    }

    /*@Override
    @GetMapping(value = "/data")
    public String data(String stockId, String startDate, String endDate) {
        List<StockHistoryDto> dtoList = selectHistory(stockId, startDate, endDate);
        StringBuilder str = new StringBuilder();
        str.append("Date,Open,High,Low,Close,Volume").append("\n");
//        Date,Open,High,Low,Close,Volume
//        9-Jun-14,62.40,63.34,61.79,62.88,37617413
        if(dtoList != null && !dtoList.isEmpty()) {
            for(StockHistoryDto history : dtoList) {
                str.append(history.getDate()).append(",")
                   .append(history.getOpening()).append(",")
                   .append(history.getHighest()).append(",")
                   .append(history.getLowest()).append(",")
                   .append(history.getClosing()).append(",")
                   .append(history.getVol().replace(",", "")).append("\n");
            }
        }
        return str.toString();
    }*/

}
