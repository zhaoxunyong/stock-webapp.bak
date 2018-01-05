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
    CONCEPT(1),
    GROUP(2);
    
    int type;
    
    StockTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    
}
