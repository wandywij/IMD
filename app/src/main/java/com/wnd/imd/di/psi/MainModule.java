package com.wnd.imd.di.psi;

import com.wnd.imd.di.ActivityScope;
import com.wnd.imd.interactor.PSIInteractor;
import com.wnd.imd.networking.NetworkService;
import com.wnd.imd.psi.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Wandy on 9/16/17.
 */
@Module
public class MainModule {

    @Provides
    @ActivityScope
    public MainPresenter provideMainPresenter(PSIInteractor psiInteractor) {
        return new MainPresenter(psiInteractor);
    }

    @Provides
    @ActivityScope
    public PSIInteractor providePSIInteractor(NetworkService networkService) {
        return new PSIInteractor(networkService);
    }


}
