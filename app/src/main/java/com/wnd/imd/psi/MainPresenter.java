package com.wnd.imd.psi;

import com.wnd.imd.Constant;
import com.wnd.imd.interactor.PSIInteractor;
import com.wnd.imd.networking.response.PSIResponseModel;

import java.io.IOException;

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
    private BehaviorSubject<PSIResponseModel> behaviorSubject;

    public MainPresenter(PSIInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Get PSI data from API and send signal whenever the data is collected succesfully or whenever
     * it throws any errors.
     * */
    public void getPSI() {
        behaviorSubject = BehaviorSubject.create();
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
                        if(throwable instanceof IOException) {
                            behaviorSubject.onError(getIOException());
                        } else {
                            behaviorSubject.onError(getException());
                        }
                        behaviorSubject.onComplete();
                    }
                });
    }

    /**
     * Will be called whenever activity is resuming.
     * This method will get PSI data and observe any signal receive to update map.
     *
     * @param getPSIResponseModel
     * @param throwableConsumer
     *
     * @return Disposable that observe changes on data an update map afterwards
     * */
    public Disposable onResume(Consumer<PSIResponseModel> getPSIResponseModel, Consumer<Throwable> throwableConsumer) {
        getPSI();
        return behaviorSubject.
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPSIResponseModel, throwableConsumer);
    }

    public IOException getIOException() {
        return new IOException(Constant.IS_NO_INTERNET_CONNECTION);
    }

    /**
     * Create a new general Exception to be thrown
     *
     * @return New general Exception
     * */
    public Exception getException() {
        return new Exception();
    }



}
