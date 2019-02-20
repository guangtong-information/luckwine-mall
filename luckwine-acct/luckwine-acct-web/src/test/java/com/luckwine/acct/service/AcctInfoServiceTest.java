package com.luckwine.acct.service;

import com.luckwine.acct.AcctApplication;
import com.luckwine.acct.model.base.AcctInfo;
import com.luckwine.acct.model.request.AcctInfoPageRequest;
import com.luckwine.acct.service.info.AcctInfoService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
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
@SpringBootTest(classes = AcctApplication.class)
@WebAppConfiguration
public class AcctInfoServiceTest {

    @Resource
    private AcctInfoService acctInfoService;

    @Test
    public void testAcctInfo() {

        CommonQueryPageRequest<AcctInfoPageRequest> request = new CommonQueryPageRequest<AcctInfoPageRequest>();

        request.setPageSize(10);
        request.setPageNo(1);
        AcctInfoPageRequest acctInfoPageRequest = new AcctInfoPageRequest();
        acctInfoPageRequest.setCreateTimeStart("2018-09-04 00:00:00");
        acctInfoPageRequest.setCreateTimeEnd("2018-09-04 23:59:59");
        request.setRequest(acctInfoPageRequest);

        CommonQueryPageResponse<List<AcctInfo>> response = acctInfoService.querylist(request);

        log.info("账户列表：{}", response.toString());

    }

}
