package com.wnd.imd.interactor;

import com.wnd.imd.networking.NetworkService;
import com.wnd.imd.networking.response.PSIResponseModel;

import io.reactivex.Observable;

/**
 * Created by Wandy on 9/16/17.
 */

public class PSIInteractor {

    NetworkService networkService;

    public PSIInteractor(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Observable<PSIResponseModel> getPSI() {
        return networkService.getPSIIndex();
    }
}
