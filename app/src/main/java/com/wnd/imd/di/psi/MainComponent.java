package com.wnd.imd.di.psi;

import com.wnd.imd.di.ActivityScope;
import com.wnd.imd.di.ApplicationComponent;
import com.wnd.imd.psi.MainActivity;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by Wandy on 9/16/17.
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class},modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
