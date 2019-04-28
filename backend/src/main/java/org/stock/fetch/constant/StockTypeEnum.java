/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： 2018年1月5日
 * 项目： wenchun-webapp
 */
package org.stock.fetch.constant;

/**
 * StockTypeEnum
 * 
 * @author Dave.zhao
 * @version [版本号, 2018年1月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum StockTypeEnum {
    MARKET(0, "上市"),
    COUNTER(1, "上櫃"),
    ELECTRONIC(2, "電子產業"),
    CONCEPT(3, "概念股"),
    GROUP(4, "集團股");
    
    int type;
    String name;
    
    StockTypeEnum(int type, String name) {
        this.type = type;
        this.name= name;
    }

    public int getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
}
