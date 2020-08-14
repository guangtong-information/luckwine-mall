package com.luckwine.portal.module.portal.controller.common;



import com.luckwine.portal.common.limit.RedisRaterLimiter;
import com.luckwine.portal.common.utils.ResultUtil;
import com.luckwine.portal.common.vo.Result;
import com.luckwine.portal.module.portal.serviceimpl.OSSFileUtilsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author HowellYang
 */
@Slf4j
@RestController
@Api(description = "文件上传接口")
@RequestMapping("/portal/upload")
public class UploadController {

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private OSSFileUtilsServiceImpl ossFileUtilsService;

    @Value("${portal.ossUrlPath}")
    private String ossUrlPath;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) {

        // IP限流 在线Demo所需 5分钟限1个请求
//        String token1 = redisRaterLimiter.acquireTokenFromBucket("upload:"+ IpInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token1)) {
//            throw new OSSException("上传那么多干嘛，等等再传吧");
//        }

        String filename = request.getParameter("filename"), imagePath = "";
        if(StringUtils.isEmpty(filename)){
            filename = file.getOriginalFilename();
        }

        try {
            InputStream inputStream = file.getInputStream();
            //todo 上传阿里云OSS服务器 , 保存:据请调用 OSSFileUtilsServiceImpl().save()
            imagePath = ossFileUtilsService.upload(inputStream, filename);
        } catch (Exception e) {
            log.error(e.toString());
        }
        String url = ossUrlPath + imagePath;
        return new ResultUtil<String>().setData(url);
    }

}
