package com.wnd.imd;

import android.app.Application;

import com.wnd.imd.di.ApplicationComponent;
import com.wnd.imd.di.ApplicationModule;
import com.wnd.imd.di.DaggerApplicationComponent;
import com.wnd.imd.di.NetworkingModule;

/**
 * Created by Wandy on 9/13/17.
 */

public class IMDApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkingModule(new NetworkingModule())
                .build();
    }
}
