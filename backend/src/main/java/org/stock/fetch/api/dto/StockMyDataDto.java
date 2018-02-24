package org.stock.fetch.api.dto;

import java.util.Date;

import org.springframework.data.annotation.Transient;

public class StockMyDataDto {
    private String id;

    private String stockId;
    
    @Transient
    private String no;
    
    @Transient
    private String company;

    @Transient
    private String[] selectedTypes;

    private String kinds;

    private String industry;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds == null ? null : kinds.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String[] getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(String[] selectedTypes) {
		this.selectedTypes = selectedTypes;
	}
    
}