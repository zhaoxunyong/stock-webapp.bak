package org.stock.fetch.api.impl;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.stock.config.StockProperties;
import org.stock.fetch.api.StockApi;
import org.stock.fetch.api.dto.PageDto;
import org.stock.fetch.api.dto.StockDailyTransactionsDto;
import org.stock.fetch.api.dto.StockDataDto;
import org.stock.fetch.api.dto.StockImportantNewsDto;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.api.dto.StockMySelectedTypeDto;
import org.stock.fetch.api.dto.StockNewsDto;
import org.stock.fetch.model.StockDailyTransactions;
import org.stock.fetch.model.StockData;
import org.stock.fetch.model.StockImportantNews;
import org.stock.fetch.model.StockMyData;
import org.stock.fetch.model.StockMySelectedType;
import org.stock.fetch.model.StockNews;
import org.stock.fetch.service.FetchService;
import org.stock.fetch.service.StockService;

import com.aeasycredit.commons.lang.idgenerator.IdUtils;

@RestController
@RequestMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
@EnableConfigurationProperties(StockProperties.class)
public class StockApiImpl implements StockApi {
	
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

	@Override
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
	}

	@Override
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
	public void changeStockMySelected(String stockId, String selectedType) {
//		throw new BusinessException("中华人民共和国");
		stockService.changeStockMySelected(Long.parseLong(stockId), Long.parseLong(selectedType));
	}

	@Override
    @RequestMapping(value = "/removeStockMySelected", method = POST)
	public void removeStockMySelected(String stockId, String selectedType) {
		stockService.removeStockMySelected(Long.parseLong(stockId), Long.parseLong(selectedType));
	}
	
	@PostMapping("/uploadStockDailyTransactions")  
    public void uploadStockDailyTransactions(@RequestParam("file") MultipartFile file) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = file.getInputStream();
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
	public List<StockDailyTransactionsDto> getStockDailyTransactions() {
		List<StockDailyTransactions> stockDailyTransactionses = stockService.getStockDailyTransactions();
		List<StockDailyTransactionsDto> dtoList = stockDailyTransactionses.stream().map(model -> {
			return modelMapper.map(model, StockDailyTransactionsDto.class);
		}).collect(Collectors.toList());
		return dtoList;
	}

}
