package com.wnd.imd.psi;

import com.wnd.imd.interactor.PSIInteractor;
import com.wnd.imd.networking.response.PSIResponseModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Wandy on 9/16/17.
 */

public class MainPresenter {

    private PSIInteractor interactor;
    private BehaviorSubject<PSIResponseModel> behaviorSubject = BehaviorSubject.create();

    public MainPresenter(PSIInteractor interactor) {
        this.interactor = interactor;
    }

    public void getIPS() {
        interactor.getPSI()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<PSIResponseModel>() {
                    @Override
                    public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                        behaviorSubject.onNext(psiResponseModel);
                        behaviorSubject.onComplete();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    public Disposable onResume(Consumer<PSIResponseModel> getPSIResponseModel) {
        getIPS();
        return behaviorSubject.
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPSIResponseModel);
    }
}
