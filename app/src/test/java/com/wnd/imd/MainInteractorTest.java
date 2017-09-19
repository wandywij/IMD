package com.wnd.imd;

import com.wnd.imd.networking.NetworkService;
import com.wnd.imd.networking.NetworkServiceImpl;
import com.wnd.imd.networking.response.PSIResponseModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by Wandy on 9/14/17.
 */

public class MainInteractorTest {

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
    public void getPSIShouldReturnObservable() {
        networkService.getPSIIndex()
                .subscribe(new Consumer<PSIResponseModel>() {
                    @Override
                    public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                        Assert.assertNotNull(psiResponseModel);
                    }
                });
    }


}
