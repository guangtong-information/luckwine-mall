package com.luckwine.portal.module.portal.controller;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.goods.CartBase;
import com.luckwine.portal.module.portal.service.GetAuthenticationService;
import com.luckwine.portal.module.portal.serviceimpl.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(description = "User接口")
@RequestMapping("/portal/users")
public class UsersController {

    @Autowired
    private GetAuthenticationService getAuthenticationService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cartList", method = RequestMethod.POST)
    @ApiOperation(value = "users cartList")
    public Result<Object> cartList(HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);
        return new ResultUtil<Object>().setData(cartService.findInfoBykey(loginAccount));
    }

    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    @ApiOperation(value = " addCart  ")
    public Result<Object> addCart(@ModelAttribute CommonRequest<CartBase> cartBaseCommonRequest, @ModelAttribute CartBase cartBase, HttpServletRequest request) {
        String loginAccount = getAuthenticationService.getUsernameByToken(request);
        cartService.update(cartBase, loginAccount);
        System.out.println(JSONObject.valueToString(cartService.list()));
        //todo
        return new ResultUtil<Object>().setData(cartService.findInfoBykey(loginAccount));
    }


}
