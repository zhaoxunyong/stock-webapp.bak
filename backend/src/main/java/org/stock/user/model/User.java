package org.stock.user.model;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class User extends BaseModel {
    
    private static final long serialVersionUID = -2871228322737341033L;
    
    // 主鍵
    private Long id;
    
    // 登錄用戶名
    private String loginName;
    
    // 用戶姓名
    private String name;
    
    // 密碼
    private String password;
    
    // 電子郵箱
    private String email;
    
    // 手機號碼
    private String mobile;
    
    // RTX號碼
    private String rtx;
    
    // 性別:[ 1-男 2-女 0-未知 ]
    private Integer gender;
    
    // 是否鎖定：[ 0-未鎖定 1-鎖定]
    private Integer locked;
    
    // 創建時間
    private Date createDate;
    
    // 修改時間
    private Date updateDate;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
    
    public String getRtx() {
        return rtx;
    }
    
    public void setRtx(String rtx) {
        this.rtx = rtx == null ? null : rtx.trim();
    }
    
    public Integer getGender() {
        return gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    
    public Integer getLocked() {
        return locked;
    }
    
    public void setLocked(Integer locked) {
        this.locked = locked;
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