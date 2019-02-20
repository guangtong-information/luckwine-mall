package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.synthesize.SynthesizeApplication;
import com.luckwine.synthesize.model.base.AdModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SynthesizeApplication.class)
@WebAppConfiguration
public class AdModuleServiceTest {

    @Resource
    private AdModuleService adModuleService;

    @Test
    public void testQueryAdModulePage() {

        CommonQueryPageRequest<AdModule> request = new CommonQueryPageRequest<>();
        AdModule adModule = new AdModule();
        request.setRequest(adModule);
        request.setPageNo(1);
        request.setPageSize(10);

        CommonQueryPageResponse<List<AdModule>> response = adModuleService.queryAdModulePage(request);
        System.out.println(response);
    }

}
