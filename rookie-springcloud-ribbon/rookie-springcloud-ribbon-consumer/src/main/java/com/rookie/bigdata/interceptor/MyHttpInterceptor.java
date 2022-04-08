package com.rookie.bigdata.interceptor;

import com.rookie.bigdata.request.MyHttpRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Classname MyHttpInterceptor
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/19 10:02
 * @Version 1.0
 */
public class MyHttpInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        MyHttpRequest newRequest = new MyHttpRequest(request);

        return execution.execute(newRequest, body);
    }
}
