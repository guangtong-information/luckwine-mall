package com.luckwine.portal.module.portal.controller.manage;

import com.luckwine.portal.common.utils.PageUtil;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.PageVo;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.module.portal.entity.Log;
import com.luckwine.portal.module.portal.entity.elasticsearch.EsLog;
import com.luckwine.portal.module.portal.service.LogService;
import com.luckwine.portal.module.portal.service.elasticsearch.EsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * 拥有ROLE_ADMIN角色的用户可以访问
 * @author HowellYang
 */
@Slf4j
@RestController
@Api(description = "日志管理接口")
@RequestMapping("/oss/log")
public class LogController{

    @Value("${portal.logRecord.es}")
    private Boolean esRecord;

    @Autowired
    private EsLogService esLogService;


    @RequestMapping(value = "/getAllByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<Object> getAllByPage(@ModelAttribute PageVo pageVo){
        if(esRecord){
            Page<EsLog> es = esLogService.getLogList(PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(es);
        }
        return null;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索")
    public Result<Object> search(@RequestParam String key, @ModelAttribute PageVo pageVo){

        if(esRecord){
            Page<EsLog> es = esLogService.searchLog(key, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(es);
        }
        return null;
    }

    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除")
    public Result<Object> delByIds(@RequestParam String[] ids){

        for(String id : ids){
            if(esRecord){
                esLogService.deleteLog(id);
            }
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }

    @RequestMapping(value = "/delAll",method = RequestMethod.DELETE)
    @ApiOperation(value = "全部删除")
    public Result<Object> delAll(){

        if(esRecord){
            esLogService.deleteAll();
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
