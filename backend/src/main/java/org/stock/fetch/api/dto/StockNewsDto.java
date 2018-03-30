package org.stock.fetch.api.dto;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.aeasycredit.commons.lang.base.BaseDto;
import com.aeasycredit.commons.lang.base.BaseModel;
import com.aeasycredit.commons.lang.utils.DatesUtils;

public class StockNewsDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = -3032843623033610822L;

    private String id;

    private Long stockId;

    private String froms;

    private String subject;

    private String url;

    private Date newsDate;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms == null ? null : froms.trim();
    }

    public String getSubject() {
        Date now = new Date();
        
        if(DatesUtils.calculateDay(now, this.getNewsDate()) == 0) {
            return "<font color=\"red\">"+subject+"</font>";
        } else if(DatesUtils.calculateDay(now, this.getNewsDate())  <= 3) {
            return subject;
        } else {
            return "<font color=\"black\">"+subject+"</font>";
        }
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}