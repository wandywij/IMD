package com.wnd.imd;

import com.google.gson.Gson;
import com.wnd.imd.interactor.PSIInteractor;
import com.wnd.imd.networking.APIService;
import com.wnd.imd.networking.response.PSIResponseModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.when;

/**
 * Created by Wandy on 9/18/17.
 */

public class MainPresenterTest {

    @Mock
    PSIInteractor psiInteractor;

    private final String HEALTHY = "healthy";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertResultNotNull() {
        PSIResponseModel psiResponseModel = new Gson().fromJson(MockedAPIService.dummyJSON, PSIResponseModel.class);
        Assert.assertNotNull(psiResponseModel);
        when(psiInteractor.getPSI()).thenReturn(Observable.just(psiResponseModel));
        psiInteractor.getPSI()
                .subscribe(new Consumer<PSIResponseModel>() {
                    @Override
                    public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                        Assert.assertEquals(psiResponseModel.api_info.status, HEALTHY);
                    }
                });
    }

    @Test
    public void networkShouldReturnException() {
        PSIResponseModel psiResponseModel = new Gson().fromJson(MockedAPIService.dummyJSON, PSIResponseModel.class);
        when(psiInteractor.getPSI()).thenReturn(Observable.create(new ObservableOnSubscribe<PSIResponseModel>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<PSIResponseModel> e) throws Exception {
                e.onError(new IOException(Constant.IS_NO_INTERNET_CONNECTION));
            }
        }));
        psiInteractor.getPSI()
                .subscribe(new Consumer<PSIResponseModel>() {
                    @Override
                    public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Assert.assertTrue(throwable instanceof IOException);
                        Assert.assertEquals(throwable.getMessage(), Constant.IS_NO_INTERNET_CONNECTION);
                    }
                });
    }
}
