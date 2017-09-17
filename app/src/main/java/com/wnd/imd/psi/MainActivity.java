package com.wnd.imd.psi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wnd.imd.IMDApplication;
import com.wnd.imd.R;
import com.wnd.imd.di.psi.DaggerMainComponent;
import com.wnd.imd.di.psi.MainComponent;
import com.wnd.imd.di.psi.MainModule;
import com.wnd.imd.networking.response.PSIResponseModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter presenter;

    private MainComponent mainComponent;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(((IMDApplication) getApplication()).getApplicationComponent())
                .mainModule(new MainModule())
                .build();
        mainComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = presenter.onResume(new Consumer<PSIResponseModel>() {
            @Override
            public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
            }
        });
    }
}
