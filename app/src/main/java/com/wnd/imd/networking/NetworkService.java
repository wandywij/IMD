package com.wnd.imd.networking;

import com.wnd.imd.networking.response.PSIResponseModel;

import io.reactivex.Observable;

/**
 * Created by Wandy on 9/13/17.
 */

public interface NetworkService {
    Observable<PSIResponseModel> psiResponse();
}
