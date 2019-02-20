package com.luckwine.oss.module.mock.base;

import com.luckwine.oss.OSSApplication;
import com.luckwine.parent.entitybase.enums.AppName;
import com.luckwine.parent.entitybase.enums.ChannelCode;
import com.luckwine.parent.entitybase.enums.OperLevel;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.util.SequenceUtil;
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
@SpringBootTest(classes = OSSApplication.class)
@WebAppConfiguration
@ActiveProfiles("mock")
@ContextConfiguration(locations = {"classpath:mock-profile.xml"})
public abstract class BaseMockTest {

    @Resource
    protected MockHelper mockHelper;

    protected static String PROFILE;

    public static CommonRequest request = new CommonRequest();

    /**
     * 执行之前
     */
    @Before
    public void setUp() {

        PROFILE = mockHelper.getCurrentProfile();

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
        if (MockProfile.MOCK.getValue().equals(PROFILE)) {
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
