package com.rookie.bigdata.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.util.Random;

/**
 * @Classname MyHttpRequest
 * @Description TODO
 * @Author rookie
 * @Date 2021/10/19 9:47
 * @Version 1.0
 */
public class MyHttpRequest implements HttpRequest {



    String [] uriStr=new String[]{"http://127.0.0.1:9091/producer/hello","http://127.0.0.1:9092/producer/hello","http://127.0.0.1:9093/producer/hello"};



    private HttpRequest httpRequest;


    public MyHttpRequest(HttpRequest httpRequest){
        this.httpRequest=httpRequest;
    }

    @Override
    public String getMethodValue() {
        return this.httpRequest.getMethodValue();
    }

    @Override
    public URI getURI() {
        int max=3,min=0;
        int ran2 = (int) (Math.random()*(max-min)+min);


        try {
            URI newUri = new URI(uriStr[ran2]);
            return newUri;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.httpRequest.getURI();
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpRequest.getHeaders();
    }
}
