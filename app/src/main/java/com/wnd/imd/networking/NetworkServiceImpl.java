package com.wnd.imd.networking;

import com.wnd.imd.networking.response.PSIResponseModel;

import io.reactivex.Observable;

/**
 * Created by Wandy on 9/14/17.
 */

public class NetworkServiceImpl implements NetworkService {

    private APIService apiService;

    public NetworkServiceImpl(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<PSIResponseModel> getPSIIndex() {
        return apiService.getPSI();
    }
}
