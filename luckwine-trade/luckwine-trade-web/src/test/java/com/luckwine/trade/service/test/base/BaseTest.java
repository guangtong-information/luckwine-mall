package com.luckwine.trade.service.test.base;


import com.luckwine.parent.entitybase.enums.AppName;
import com.luckwine.parent.entitybase.enums.ChannelCode;
import com.luckwine.parent.entitybase.enums.OperLevel;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
import com.luckwine.trade.TradeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * 单元测试基类
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TradeApplication.class)
@ContextConfiguration(locations = {"classpath:test-profile.xml"})
@WebAppConfiguration
@ActiveProfiles("mock")
public abstract class BaseTest {

    @Resource
    protected TestHelper testHelper;

    protected static String PROFILE;

    public static CommonRequest request = new CommonRequest();

    /**
     * 执行之前
     */
    @Before
    public void setUp() {

        PROFILE = testHelper.getCurrentProfile();

        //通用请求参数赋值
        initRequest();

        //之后mock
        if (isMock()) {
            MockitoAnnotations.initMocks(this);
            fullMock();
        }
    }

    /**
     * 业务相关的Mock
     */
    public abstract void fullMockInner();

    protected boolean isMock() {
        if (Profile.MOCK.getValue().equals(PROFILE)) {
            return true;
        }
        return false;
    }

    public abstract void initRequestInner();
    /**
     * 基本请求
     */
    public void initRequest() {
        request.setAppName(AppName.TRADE.getCode());
        request.setChannelCode(ChannelCode.PORTALWEB.getCode());
        request.setOperLevel(OperLevel.DEFAULT.getCode());
        request.setTraceId(SequenceUtil.genSequence(SequenceCode.TRACEID, "1"));
        initRequestInner();
    }

    private void fullMock() {
        //公共mock
        //ToDo

        //子类mock
        fullMockInner();
    }

}
