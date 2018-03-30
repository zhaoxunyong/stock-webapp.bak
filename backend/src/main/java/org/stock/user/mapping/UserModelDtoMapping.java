/*
 * 描述： 合同Model轉Dto類
 * 修改人： Weng.weng
 * 修改时间： Jun 14, 2017
 * 项目： los-core
 */
package org.stock.user.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.user.api.dto.UserDto;
import org.stock.user.model.User;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * 合同Model轉Dto類<br>
 * 
 * @author Weng.weng
 * @version [版本号, Jun 14, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class UserModelDtoMapping extends PropertyMapConfigurerSupport<User, UserDto> {

    @Override
    public PropertyMap<User, UserDto> mapping() {
        return new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {

                map().setId(String.valueOf(source.getId()));
            }
        };
    }
    
    
    
}
