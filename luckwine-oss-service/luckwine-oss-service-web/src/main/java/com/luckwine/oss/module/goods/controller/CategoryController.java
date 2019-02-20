package com.luckwine.oss.module.goods.controller;

import com.luckwine.goods.model.request.category.CategoryPageRequest;
import com.luckwine.goods.model.request.category.CategorySaveRequest;
import com.luckwine.oss.common.utils.ResultUtil;
import com.luckwine.oss.common.vo.Result;
import com.luckwine.oss.module.goods.remote.category.CategoryPageRemoteService;
import com.luckwine.oss.module.goods.remote.category.CategorySaveRemoteService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.request.CommonRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "商品-分类管理")
@RequestMapping("/oss/goods/category")
public class CategoryController {

    @Autowired
    private CategoryPageRemoteService categoryPageRemoteService;

    @Autowired
    private CategorySaveRemoteService categorySaveRemoteService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation(value = "新增分类")
    public Result<Object>  categorySave(@RequestBody CommonRequest<CategorySaveRequest> request){
        return new ResultUtil().setData(categorySaveRemoteService.call(request));
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value = "分页查询分类")
    public Result<Object>  categoryPage(@RequestBody CommonQueryPageRequest<CategoryPageRequest> request){
        return new ResultUtil().setData(categoryPageRemoteService.call(request));
    }


}
