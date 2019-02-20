package com.luckwine.synthesize.service;

import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.synthesize.SynthesizeApplication;
import com.luckwine.synthesize.model.request.AdDictionaryRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SynthesizeApplication.class)
@WebAppConfiguration
public class AdDictionaryListServiceTest {

    @Resource
    private AdDictionaryService adDictionaryService;

    @Test
    public void testAdDictionary()
    {
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("======================================");
        String systemId = "1";
        CommonRequest<String> request = new CommonRequest<>();
        request.setRequest(systemId);
        adDictionaryService.queryPagesBySystemId(request).getResponse().forEach(x->System.out.println(x.getName()));
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("======================================");
    }
}
