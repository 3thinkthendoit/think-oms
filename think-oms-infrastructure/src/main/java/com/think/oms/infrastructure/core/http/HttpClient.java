package com.think.oms.infrastructure.core.http;

import com.squareup.okhttp.OkHttpClient;
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
        client.setConnectTimeout(30L, TimeUnit.SECONDS);
        client.setReadTimeout(30L, TimeUnit.SECONDS);
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
