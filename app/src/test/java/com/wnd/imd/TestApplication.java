package com.wnd.imd;

import com.wnd.imd.di.ApplicationComponent;
import com.wnd.imd.di.DaggerApplicationComponent;

/**
 * Created by Wandy on 9/17/17.
 */

public class TestApplication extends IMDApplication {

    @Override
    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new TestApplicationModule(this))
                .networkingModule(new NetworkingModuleTest())
                .build();
    }
}
