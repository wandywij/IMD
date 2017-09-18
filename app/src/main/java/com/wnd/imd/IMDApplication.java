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

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createComponent();
    }

    public ApplicationComponent createComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkingModule(new NetworkingModule())
                .build();
        return applicationComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
