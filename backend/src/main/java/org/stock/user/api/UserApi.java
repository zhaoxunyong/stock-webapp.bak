package org.stock.user.api;

import org.stock.user.api.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Stock api", description="Stock Api", protocols="http", produces = "application/json")
public interface UserApi {
    
    @ApiOperation(value="login", notes="login")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginName", value = "loginName", required = true, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "string", paramType = "query"),
    })
    public UserDto login(String loginName, String password);
}
