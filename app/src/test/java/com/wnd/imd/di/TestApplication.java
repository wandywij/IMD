package com.wnd.imd.di;

import com.wnd.imd.IMDApplication;

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
