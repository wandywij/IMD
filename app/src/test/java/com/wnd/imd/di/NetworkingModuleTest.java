package com.wnd.imd.di;

import android.util.Log;

import com.wnd.imd.MockedAPIService;
import com.wnd.imd.di.NetworkingModule;
import com.wnd.imd.networking.APIService;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Wandy on 9/17/17.
 */
public class NetworkingModuleTest extends NetworkingModule {

    @Provides @Singleton
    @Override
    public APIService provideApiService(Retrofit retrofit) {
        return retrofit.create(MockedAPIService.class);
    }

}
