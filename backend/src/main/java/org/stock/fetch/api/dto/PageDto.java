/*
 * 描述： 分頁DTO
 * 修改人： Weng.weng
 * 修改时间： Jun 7, 2017
 * 项目： los-core
 */
package org.stock.fetch.api.dto;

import java.util.List;
import java.util.Map;

import com.aeasycredit.commons.lang.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 分頁DTO<br>
 * 
 * @author Weng.weng
 * @version [版本号, Jun 7, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ApiModel(value = "分頁信息")
public class PageDto<T> extends BaseDto {
    
    private static final long serialVersionUID = -8014975339158526001L;
    
    private String val = "";
    
    //	private Map map;
    
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int curPage = 1;
    
    /**
     * 起始位置
     */
    @ApiModelProperty(value = "起始位置")
    private int start;
    
    /**
     * 结束位置
     */
    @ApiModelProperty(value = "结束位置")
    private int end;
    
    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小")
    private int pageSize = 15;
    
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private long pageTotal;
    
    /**
     * 总记录
     */
    @ApiModelProperty(value = "总记录")
    private long total;
    
    /**
     * 返回集合
     */
    @ApiModelProperty(value = "列表信息")
    private List<T> rows;
    
    /**
     * 返回对象
     */
    @ApiModelProperty(value = "返回对象信息")
    private T obj;
    
    public T getObj() {
        return obj;
    }
    
    public void setObj(T obj) {
        this.obj = obj;
    }
    
    public PageDto() {
        super();
    }
    
    public PageDto(int pageSize) {
        super();
        this.pageSize = pageSize;
    }
    
    public PageDto(int curPage, int pageSize) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
    }
    
    public PageDto(Map map) {
        super();
        
        this.curPage = Integer.parseInt(map.get("curPage").toString());
        this.pageSize = Integer.parseInt(map.get("pageSize").toString());
        
        //        map.put("start", this.getStart());
        //        map.put("end", this.getEnd());
        
        //        this.map = map;
    }
    
    public PageDto(int curPage, int pageSize, Map map) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        //        this.map = map;
    }
    
    public PageDto(int curPage, int pageSize, long total) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.total = total;
    }
    
    /** 前一页 */
    public int getPreviousPage() {
        return getCurPage() <= 1 ? 1 : getCurPage() - 1;
    }
    
    /** 下一页 */
    public long getNextPage() {
        return curPage >= getPageTotal() ? getPageTotal() : curPage + 1;
    }
    
    public int getCurPage() {
        return curPage;
    }
    
    public void setCurPage(int curPage) {
        this.curPage = curPage < 1 ? 1 : curPage;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public long getPageTotal() {
        
        if (pageSize != 0) {
            pageTotal = (total % pageSize == 0) ? total / pageSize : total
                    / pageSize + 1;
        }
        return pageTotal;
    }
    
    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public String getVal() {
        return val;
    }
    
    public void setVal(String val) {
        this.val = val;
    }
    
    public int getStart() {
        start = ((curPage - 1) * pageSize);
        return start;
    }
    
    public void setStart(int start) {
        this.start = start < 1 ? pageSize : start;
    }
    
    public List<T> getRows() {
        return rows;
    }
    
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    public int getEnd() {
        end = curPage * pageSize;
        return end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
    
    //	public Map getMap() {
    //		return map;
    //	}
    //
    //	public void setMap(Map map) {
    //		this.map = map;
    //	}
    
}
