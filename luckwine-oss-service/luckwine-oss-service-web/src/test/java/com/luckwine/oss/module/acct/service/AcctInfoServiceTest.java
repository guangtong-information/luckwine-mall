package com.luckwine.oss.module.acct.service;


import com.luckwine.oss.OSSApplication;
import com.luckwine.oss.acct.model.request.AcctInfoPageOssRequest;
import com.luckwine.oss.acct.model.response.AcctInfoOssResponse;
import com.luckwine.oss.acct.service.info.AcctInfoOssService;
import com.luckwine.parent.entitybase.request.CommonQueryPageRequest;
import com.luckwine.parent.entitybase.response.CommonQueryPageResponse;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
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
@SpringBootTest(classes = OSSApplication.class)
@WebAppConfiguration
public class AcctInfoServiceTest {

    @Resource
    private AcctInfoOssService acctInfoOssService;

    @Test
    public void testAcctInfo() {

        CommonQueryPageRequest<AcctInfoPageOssRequest> request = new CommonQueryPageRequest<>();
        request.setTraceId(SequenceUtil.genSequence(SequenceCode.TRACEID, "1"));
        request.setPageSize(10);
        request.setPageNo(1);
        AcctInfoPageOssRequest acctInfoPageRequest = new AcctInfoPageOssRequest();
        acctInfoPageRequest.setCreateTimeStart("2018-09-04 00:00:00");
        acctInfoPageRequest.setCreateTimeEnd("2018-09-04 23:59:59");
        request.setRequest(acctInfoPageRequest);

        CommonQueryPageResponse<List<AcctInfoOssResponse>> response = acctInfoOssService.query(request);

        System.out.println("账户列表：" + response);

    }

}
