package com.wnd.imd.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wnd.imd.Constant;
import com.wnd.imd.networking.APIService;
import com.wnd.imd.networking.NetworkService;
import com.wnd.imd.networking.NetworkServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wandy on 9/14/17.
 */

@Module
public class NetworkingModule {

    @Provides
    @Singleton
    public NetworkService provideNetworkService(APIService apiService) {
        return new NetworkServiceImpl(apiService);
    }

    @Provides
    @Singleton
    public APIService provideApiService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        //.header("User-Agent", "Your-App-Name")
                        .header("api-key", Constant.API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }

}
