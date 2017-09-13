package com.wnd.imd.networking;

import com.wnd.imd.networking.response.PSIResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wandy on 9/14/17.
 */

public interface APIService {

    @GET("environment/psi")
    Observable<PSIResponseModel> getPSI();
}
