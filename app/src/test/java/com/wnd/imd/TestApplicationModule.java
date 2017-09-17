package com.wnd.imd;

import android.content.Context;

import com.wnd.imd.di.ApplicationModule;

/**
 * Created by Wandy on 9/17/17.
 */

public class TestApplicationModule extends ApplicationModule {

    private Context context;

    public TestApplicationModule(Context context) {
        super(context);
        this.context = context;
    }
}
