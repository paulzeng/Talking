package com.drugdu.http;

import android.util.SparseArray;

import com.drugdu.APP;
import com.drugdu.C;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laucherish on 16/3/15.
 */
public class RetrofitManager {

    public static final String BASE_ZHIHU_URL = "http://news-at.zhihu.com/api/4/";

    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private static OkHttpClient mOkHttpClient;
    public final ApiManagerService apiService;

    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> mInstanceManager = new SparseArray<>(
            HostType.TYPE_COUNT);


    public static RetrofitManager builder(int hostType) {
        RetrofitManager instance = mInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            mInstanceManager.put(hostType, instance);
            return instance;
        } else {
            return instance;
        }
    }


    private RetrofitManager(@HostType.HostTypeChecker int hostType) {
        initOkHttpClient();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getHost(hostType))
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiManagerService.class);
    }


    private void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            //.addNetworkInterceptor(interceptor)
                            .addInterceptor(interceptor)
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build();
                }

            }
        }
    }


    // Define the interceptor, add authentication headers
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (!APP.isLogin()) {
                return chain.proceed(originalRequest);
            } else {
                String token = APP.getUserToken();
                Request authorised = originalRequest.newBuilder()
                        .header("token", token)
                        .build();
                return chain.proceed(authorised);
            }
        }
    };

 
    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    private String getHost(int hostType) {
        switch (hostType) {
            case HostType.DEV_HOST:
                return C.DEV_HOST_URL;
            case HostType.GAMMA_HOST:
                return C.GAMMA_HOST_URL;
            case HostType.BETA_HOST:
                return C.BETA_HOST_URL;
            case HostType.PRODUCT_HOST:
                return C.PRODUCT_HOST_URL;
        }
        return "";
    }

}
