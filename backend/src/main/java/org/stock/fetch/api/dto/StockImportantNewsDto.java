package org.stock.fetch.api.dto;

import java.util.Date;

import com.aeasycredit.commons.lang.utils.DatesUtils;

public class StockImportantNewsDto {
    private String id;

    private String froms;

    private String subject;

    private Date newsDate;

    private Date createDate;

    private Date updateDate;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFroms() {
        Date now = new Date();
        
        if(DatesUtils.calculateDay(now, this.getNewsDate()) == 0) {
            return "<font color=\"red\">"+froms+"</font>";
        } else if(DatesUtils.calculateDay(now, this.getNewsDate())  <= 3) {
            return froms;
        } else {
            return "<font color=\"black\">"+froms+"</font>";
        }
    }

    public void setFroms(String froms) {
        this.froms = froms == null ? null : froms.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}