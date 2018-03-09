/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： 2018年1月5日
 * 项目： wenchun-webapp
 */
package org.stock.fetch.constant;

/**
 * StockNewsKeyTypeEnum
 * 
 * @author Dave.zhao
 * @version [版本号, 2018年1月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum StockNewsKeyTypeEnum {
    INCLUDE(0, "包含"),
    EXCLUDE(1, "排除");
    
    int type;
    String name;
    
    StockNewsKeyTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
}
