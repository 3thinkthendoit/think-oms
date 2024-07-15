package com.think.oms.infrastructure.core.http;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class HttpClient implements InitializingBean, DisposableBean {

    private OkHttpClient client  = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        client = new OkHttpClient();
    }

    @Override
    public void destroy() throws Exception {
        client = null;
        //
    }

    public String get(String url){

        return "";
    }

    public String post(String url, Map<String,Object> params){

        return "";
    }

}
