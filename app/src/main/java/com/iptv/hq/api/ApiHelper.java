package com.iptv.hq.api;
import com.iptv.hq.common.AppOtt;
import com.iptv.hq.common.Properties;
import com.iptv.hq.utils.HttpParams;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by HQ on 2017/11/3.
 */
public class ApiHelper {

    public static Api getApi() {
        Headers headers = new Headers.Builder().add("md5", "GYWmhK2MfuQtDc9Cj8Fbw9hGoJwQ+f3WtViaAM7An3mUR3TVi/fh1g==").build();
        return getApi(Properties.HTTP.HTTP_URL, headers);
    }

    public static Api getApi(final Headers headers) {
        return getApi(Properties.HTTP.HTTP_URL, headers);
    }

    public static Api getApi(String url, final Headers headers) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                                .addInterceptor(
                                        new Interceptor() {
                                            @Override
                                            public Response intercept(Chain chain) throws IOException {
                                                Request request = chain.request().newBuilder().headers(headers).tag("hhh").build();
                                                return chain.proceed(request);
                                            }
                                        }
                                )
                               .cache(AppOtt.getCache()).build()
                ).build();

        return retrofit.create(Api.class);
    }

    public static Api getNoHeaderApi(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        new OkHttpClient.Builder()
                                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                                .build()
                ).build();
        return retrofit.create(Api.class);
    }

    public static RequestBody createBody(String content) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), content);
    }

    public static RequestBody createBody(HttpParams params) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), params.toJson());
    }

    public static RequestBody createBody(Map map) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new HttpParams().toJson(map));
    }
}
