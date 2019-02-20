package com.luckwine.parent.filter;

import com.luckwine.parent.util.RequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {

    private static final String BEFORE_LOG_MSG = "Calling http[{}].Sent request:{}.";
    private static final String AFTER_LOG_MSG = "Called http[{}].[{}]. elapsed: [{} Seconds] ";

    private boolean includeResponsePayload = true;
    private int maxPayloadLength = 1000;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestLogUtils.initLog();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //获取uri路径
        StringBuilder url = new StringBuilder();
        url.append('[');
        url.append(request.getMethod());
        url.append(']');
        url.append(request.getRequestURL());
        if (null != request.getQueryString()) {
            url.append('?').append(request.getQueryString());
        }


        //注意:这里不能直接读request里面的流,那样会导致真正的服务读取不到
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        //执行真正的请求
        filterChain.doFilter(wrappedRequest, wrappedResponse);

        //获取请求体
        String requestBody = this.getContentAsString(wrappedRequest.getContentAsByteArray(), this.maxPayloadLength, request.getCharacterEncoding());

        log.info(BEFORE_LOG_MSG, url , requestBody);

        if (includeResponsePayload) {
            byte[] buf = wrappedResponse.getContentAsByteArray();
            String responseBody = getContentAsString(buf, buf.length, response.getCharacterEncoding());
            //响应数据打印日志
            stopWatch.stop();
            log.info(AFTER_LOG_MSG, url , responseBody , stopWatch.getTotalTimeSeconds());
        }
        //这里一定要把content拷贝回真正的response, copyBodyToResponse需要spring 4.2以上.
        wrappedResponse.copyBodyToResponse();

        RequestLogUtils.logClear();
    }

    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, maxLength);
        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }


}
