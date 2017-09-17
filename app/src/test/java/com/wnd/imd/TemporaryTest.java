package com.wnd.imd;

import android.util.Log;

import com.wnd.imd.networking.APIService;
import com.wnd.imd.networking.NetworkService;
import com.wnd.imd.networking.NetworkServiceImpl;
import com.wnd.imd.networking.response.PSIResponseModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * Created by Wandy on 9/14/17.
 */

public class TemporaryTest {

    NetworkService networkService;
    MockedAPIService apiService;

    @Before
    public void before() {
        apiService = new MockedAPIService();
        networkService = new NetworkServiceImpl(apiService);
    }

    @Test
    public void networkingServiceShouldBeNotNull() {
        Assert.assertNotNull(networkService);
    }

    @Test
    public void assertResultNotNull() {
        Observable<PSIResponseModel> psiResponseModelObservable = networkService.getPSIIndex();
        psiResponseModelObservable.subscribe(new Consumer<PSIResponseModel>() {
            @Override
            public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                Assert.assertEquals(psiResponseModel.api_info.status, apiService.HEALTHY);
            }
        });
    }

    @Test
    public void networkShouldReturnException() {
        apiService.throwError(true);
        networkService.getPSIIndex()
                .subscribe(new Consumer<PSIResponseModel>() {
                    @Override
                    public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Assert.assertTrue(throwable instanceof IOException);
                        Assert.assertEquals(throwable.getMessage(), apiService.isNoConnection);
                    }
                });
    }

}
