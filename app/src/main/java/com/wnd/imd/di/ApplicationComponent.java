package com.wnd.imd.di;

import com.wnd.imd.networking.NetworkService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Wandy on 9/14/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    NetworkService networkService();
}
