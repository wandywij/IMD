package com.wnd.imd.psi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wnd.imd.IMDApplication;
import com.wnd.imd.R;
import com.wnd.imd.di.psi.DaggerMainComponent;
import com.wnd.imd.di.psi.MainComponent;
import com.wnd.imd.di.psi.MainModule;
import com.wnd.imd.networking.response.PSIResponseModel;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Inject
    MainPresenter presenter;

    private MainComponent mainComponent;
    private Disposable disposable;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(((IMDApplication) getApplication()).getApplicationComponent())
                .mainModule(new MainModule())
                .build();
        mainComponent.inject(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = presenter.onResume(new Consumer<PSIResponseModel>() {
            @Override
            public void accept(@NonNull PSIResponseModel psiResponseModel) throws Exception {
                if (mMap != null) {
                    updateMap(psiResponseModel);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if (throwable instanceof IOException) {
                    showNoConnectionDialog();
                } else {
                    showErrorDialog();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void updateMap(PSIResponseModel responseModel) {
        LatLngBounds.Builder boundBuilder = new LatLngBounds.Builder();
        for (PSIResponseModel.Region_metadata region : responseModel.getRegion_metadata()) {
            // Add a marker
            if (region.getLabel_location().getLatitude() == 0 && region.getLabel_location().getLongitude() == 0) {
                continue;
            }
            LatLng regionCoordinate = new LatLng(region.getLabel_location().getLatitude(), region.getLabel_location().getLongitude());
            mMap.addMarker(new MarkerOptions().position(regionCoordinate).title(region.getName()));

            boundBuilder.include(regionCoordinate);
        }

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (getResources().getDisplayMetrics().widthPixels * 0.10);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundBuilder.build(), width, height, padding));
    }

    private void showNoConnectionDialog() {
        new android.app.AlertDialog.Builder(this)
                .setMessage(R.string.no_connection_error_message)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onResume();
                    }
                })
                .show();
    }

    private void showErrorDialog() {
        new android.app.AlertDialog.Builder(this)
                .setMessage(R.string.something_wrong)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onResume();
                    }
                })
                .show();
    }
}

