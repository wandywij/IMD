package com.wnd.imd;

import android.app.Dialog;
import android.os.Build;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.wnd.imd.networking.response.PSIResponseModel;
import com.wnd.imd.psi.MainActivity;
import com.wnd.imd.psi.MainPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

import static org.mockito.Mockito.when;

/**
 * Created by Wandy on 9/19/17.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Mock
    MainPresenter presenter;

    MainActivity mainActivity;
    BehaviorSubject<PSIResponseModel> behaviorSubject;
    Consumer<PSIResponseModel> psiResponseModelConsumer;
    Consumer<Throwable> throwableConsumer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        behaviorSubject = BehaviorSubject.create();
        psiResponseModelConsumer = new Consumer<PSIResponseModel>() {
            @Override
            public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                Assert.assertNotNull(psiResponseModel);
            }
        };

        throwableConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Assert.assertNotNull(throwable);
            }
        };
    }




    @Test
    public void shouldUpdateMap() {
        //mock the result so it returns correct value
        when(presenter.onResume(
                psiResponseModelConsumer,
                throwableConsumer))
                .thenReturn(behaviorSubject.
                        observeOn(AndroidSchedulers.mainThread())
                        .subscribe(psiResponseModelConsumer, throwableConsumer));
        PSIResponseModel psiResponseModel = new Gson().fromJson(MockedAPIService.dummyJSON, PSIResponseModel.class);
        Assert.assertNotNull(psiResponseModel);
        behaviorSubject.onNext(psiResponseModel);

        //making sure the disposable on mainactivity is not null, because it is used to catch object from presenter
        mainActivity.disposable = presenter.onResume(psiResponseModelConsumer, throwableConsumer);
        Assert.assertNotNull(mainActivity.disposable);

        /* testing west map*/
        mainActivity.showDialog(psiResponseModel, "West");
        MainActivity.MyDialog dialog = (MainActivity.MyDialog) ShadowDialog.getLatestDialog();
        //making sure the dialog is showing
        Assert.assertTrue(dialog.isShowing());
        //making sure the dialog shows
        Assert.assertEquals(dialog.lblDescription1.getText(), psiResponseModel.getItems().get(0).readings.o3_sub_index.west + "");

        dialog.dismiss();
        Assert.assertTrue(!dialog.isShowing());
        /* end of testing west map */

        /* testing east map*/
        mainActivity.showDialog(psiResponseModel, "East");
         dialog = (MainActivity.MyDialog) ShadowDialog.getLatestDialog();
        //making sure the dialog is showing
        Assert.assertTrue(dialog.isShowing());
        //making sure the dialog shows
        Assert.assertEquals(dialog.lblDescription1.getText(), psiResponseModel.getItems().get(0).readings.o3_sub_index.east + "");

        dialog.dismiss();
        Assert.assertTrue(!dialog.isShowing());
        /*end of testing east map */

    }


}
