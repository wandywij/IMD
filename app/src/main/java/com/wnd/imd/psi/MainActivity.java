package com.wnd.imd.psi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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

/**
 * The first activity to be displayed and will show map to user to display all PSI locations.
 * Implement callback to determine whenever map is ready to be displayed.
 *
 * */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Inject
    MainPresenter presenter;

    private MainComponent mainComponent;
    public Disposable disposable;
    public GoogleMap mMap;

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

    /**
     * Everytime activity is resumed, we want to show user the updated information.
     * Hence, we will observe any changes on response and update map afterwards.
     *
     * */
    @Override
    protected void onResume() {
        super.onResume();

        /**
         * modified VIPER is used in this program, relies on RXJava for functional programming
         */
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

    /**
     * Add marker on map for every region on PSI.
     * Boundary will be set so map will displayed with all markers shown.
     *
     * @param responseModel
     * */
    public void updateMap(final PSIResponseModel responseModel) {
        final LatLngBounds.Builder boundBuilder = new LatLngBounds.Builder();
        for (final PSIResponseModel.Region_metadata region : responseModel.getRegion_metadata()) {
            // Add a marker
            if (region.getLabel_location().getLatitude() == 0 && region.getLabel_location().getLongitude() == 0) {
                continue;
            }
            LatLng regionCoordinate = new LatLng(region.getLabel_location().getLatitude(), region.getLabel_location().getLongitude());
            mMap.addMarker(new MarkerOptions().position(regionCoordinate).title(region.getName()));
            boundBuilder.include(regionCoordinate);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                moveCamera(boundBuilder);
                showDialog(responseModel, marker.getTitle());
                return false;
            }
        });
        moveCamera(boundBuilder);

    }

    private void moveCamera(LatLngBounds.Builder boundBuilder) {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (getResources().getDisplayMetrics().widthPixels * 0.10);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundBuilder.build(), width, height, padding));
    }

    public class MyDialog extends Dialog {

        public MyDialog(@android.support.annotation.NonNull Context context, @StyleRes int themeResId) {
            super(context, themeResId);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setContentView(R.layout.dialog_reading);
            this.setCancelable(true);

            setupDialog();
        }
        //Dialog dialog = new Dialog(this, android.R.style.Theme_Dialog);

        public TextView lblTitle1, lblDescription1, lblTitle2, lblDescription2, lblTitle3, lblDescription3,
                lblTitle4, lblDescription4, lblTitle5, lblDescription5, lblTitle6, lblDescription6,
                lblTitle7, lblDescription7, lblTitle8, lblDescription8, lblTitle9, lblDescription9,
                lblTitle10, lblDescription10,
                lblTitle11, lblDescription11, lblTitle12, lblDescription12;

        public void setupDialog() {
            lblTitle1 = (TextView) this.findViewById(R.id.lblTitle1);
            lblDescription1 = (TextView) this.findViewById(R.id.lblDescription1);

            lblTitle2 = (TextView) this.findViewById(R.id.lblTitle2);
            lblDescription2 = (TextView) this.findViewById(R.id.lblDescription2);

            lblTitle3 = (TextView) this.findViewById(R.id.lblTitle3);
            lblDescription3 = (TextView) this.findViewById(R.id.lblDescription3);

            lblTitle4 = (TextView) this.findViewById(R.id.lblTitle4);
            lblDescription4 = (TextView) this.findViewById(R.id.lblDescription4);

            lblTitle5 = (TextView) this.findViewById(R.id.lblTitle5);
            lblDescription5 = (TextView) this.findViewById(R.id.lblDescription5);

            lblTitle6 = (TextView) this.findViewById(R.id.lblTitle6);
            lblDescription6 = (TextView) this.findViewById(R.id.lblDescription6);

            lblTitle7 = (TextView) this.findViewById(R.id.lblTitle7);
            lblDescription7 = (TextView) this.findViewById(R.id.lblDescription7);

            lblTitle8 = (TextView) this.findViewById(R.id.lblTitle8);
            lblDescription8 = (TextView) this.findViewById(R.id.lblDescription8);

            lblTitle9 = (TextView) this.findViewById(R.id.lblTitle9);
            lblDescription9 = (TextView) this.findViewById(R.id.lblDescription9);

            lblTitle10 = (TextView) this.findViewById(R.id.lblTitle10);
            lblDescription10 = (TextView) this.findViewById(R.id.lblDescription10);

            lblTitle11 = (TextView) this.findViewById(R.id.lblTitle11);
            lblDescription11 = (TextView) this.findViewById(R.id.lblDescription11);

            lblTitle12 = (TextView) this.findViewById(R.id.lblTitle12);
            lblDescription12 = (TextView) this.findViewById(R.id.lblDescription12);
        }


    }



    public void showDialog(PSIResponseModel psiResponseModel, final String region) {
        MyDialog dialog = new MyDialog(this, android.R.style.Theme_Dialog);
        dialog.setupDialog();

        final String NORTH = "north";
        final String WEST = "west";
        final String EAST = "east";
        final String SOUTH = "south";
        final String CENTRAL = "central";

        if (region.equalsIgnoreCase(WEST)) {
            dialog.lblTitle1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.getClass().getSimpleName());
            dialog.lblDescription1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.west + "");

            dialog.lblTitle2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.west + "");

            dialog.lblTitle3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.getClass().getSimpleName());
            dialog.lblDescription3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.west + "");

            dialog.lblTitle4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.getClass().getSimpleName());
            dialog.lblDescription4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.west + "");

            dialog.lblTitle5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.west + "");

            dialog.lblTitle6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.getClass().getSimpleName());
            dialog.lblDescription6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.west + "");

            dialog.lblTitle7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.west + "");

            dialog.lblTitle8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.getClass().getSimpleName());
            dialog.lblDescription8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.west + "");

            dialog.lblTitle9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.west + "");

            dialog.lblTitle10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.getClass().getSimpleName());
            dialog.lblDescription10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.west + "");

            dialog.lblTitle11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.west + "");

            dialog.lblTitle12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.west + "");
        } else if (region.equalsIgnoreCase(EAST)) {
            dialog.lblTitle1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.getClass().getSimpleName());
            dialog.lblDescription1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.east + "");

            dialog.lblTitle2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.east + "");

            dialog.lblTitle3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.getClass().getSimpleName());
            dialog.lblDescription3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.east + "");

            dialog.lblTitle4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.getClass().getSimpleName());
            dialog.lblDescription4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.east + "");

            dialog.lblTitle5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.east + "");

            dialog.lblTitle6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.getClass().getSimpleName());
            dialog.lblDescription6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.east + "");

            dialog.lblTitle7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.east + "");

            dialog.lblTitle8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.getClass().getSimpleName());
            dialog.lblDescription8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.east + "");

            dialog.lblTitle9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.east + "");

            dialog.lblTitle10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.getClass().getSimpleName());
            dialog.lblDescription10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.east + "");

            dialog.lblTitle11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.east + "");

            dialog.lblTitle12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.east + "");
        } else if (region.equalsIgnoreCase(NORTH)) {
            dialog.lblTitle1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.getClass().getSimpleName());
            dialog.lblDescription1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.north + "");

            dialog.lblTitle2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.north + "");

            dialog.lblTitle3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.getClass().getSimpleName());
            dialog.lblDescription3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.north + "");

            dialog.lblTitle4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.getClass().getSimpleName());
            dialog.lblDescription4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.north + "");

            dialog.lblTitle5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.north + "");

            dialog.lblTitle6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.getClass().getSimpleName());
            dialog.lblDescription6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.north + "");

            dialog.lblTitle7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.north + "");

            dialog.lblTitle8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.getClass().getSimpleName());
            dialog.lblDescription8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.north + "");

            dialog.lblTitle9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.north + "");

            dialog.lblTitle10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.getClass().getSimpleName());
            dialog.lblDescription10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.north + "");

            dialog.lblTitle11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.north + "");

            dialog.lblTitle12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.north + "");
        } else if (region.equalsIgnoreCase(SOUTH)) {
            dialog.lblTitle1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.getClass().getSimpleName());
            dialog.lblDescription1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.south + "");

            dialog.lblTitle2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.south + "");

            dialog.lblTitle3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.getClass().getSimpleName());
            dialog.lblDescription3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.south + "");

            dialog.lblTitle4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.getClass().getSimpleName());
            dialog.lblDescription4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.south + "");

            dialog.lblTitle5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.south + "");

            dialog.lblTitle6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.getClass().getSimpleName());
            dialog.lblDescription6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.south + "");

            dialog.lblTitle7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.south + "");

            dialog.lblTitle8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.getClass().getSimpleName());
            dialog.lblDescription8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.south + "");

            dialog.lblTitle9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.south + "");

            dialog.lblTitle10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.getClass().getSimpleName());
            dialog.lblDescription10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.south + "");

            dialog.lblTitle11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.south + "");

            dialog.lblTitle12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.south + "");
        } else if (region.equalsIgnoreCase(CENTRAL)) {
            dialog.lblTitle1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.getClass().getSimpleName());
            dialog.lblDescription1.setText(psiResponseModel.getItems().get(0).readings.o3_sub_index.central + "");

            dialog.lblTitle2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription2.setText(psiResponseModel.getItems().get(0).readings.pm10_twenty_four_hourly.central + "");

            dialog.lblTitle3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.getClass().getSimpleName());
            dialog.lblDescription3.setText(psiResponseModel.getItems().get(0).readings.pm10_sub_index.central + "");

            dialog.lblTitle4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.getClass().getSimpleName());
            dialog.lblDescription4.setText(psiResponseModel.getItems().get(0).readings.co_sub_index.central + "");

            dialog.lblTitle5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription5.setText(psiResponseModel.getItems().get(0).readings.pm25_twenty_four_hourly.central + "");

            dialog.lblTitle6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.getClass().getSimpleName());
            dialog.lblDescription6.setText(psiResponseModel.getItems().get(0).readings.so2_sub_index.central + "");

            dialog.lblTitle7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription7.setText(psiResponseModel.getItems().get(0).readings.co_eight_hour_max.central + "");

            dialog.lblTitle8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.getClass().getSimpleName());
            dialog.lblDescription8.setText(psiResponseModel.getItems().get(0).readings.no2_one_hour_max.central + "");

            dialog.lblTitle9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription9.setText(psiResponseModel.getItems().get(0).readings.so2_twenty_four_hourly.central + "");

            dialog.lblTitle10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.getClass().getSimpleName());
            dialog.lblDescription10.setText(psiResponseModel.getItems().get(0).readings.pm25_sub_index.central + "");

            dialog.lblTitle11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.getClass().getSimpleName());
            dialog.lblDescription11.setText(psiResponseModel.getItems().get(0).readings.psi_twenty_four_hourly.central + "");

            dialog.lblTitle12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.getClass().getSimpleName());
            dialog.lblDescription12.setText(psiResponseModel.getItems().get(0).readings.o3_eight_hour_max.central + "");
        }
        dialog.show();
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

    /**
     * To display a dialog that will tell a user there's something wrong and they can choose
     * whether to ignore it or try again.
     *
     * @param messageResId String resource ID to be displayed as dialog message
     *
     * */
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

