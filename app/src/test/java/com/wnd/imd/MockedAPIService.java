package com.wnd.imd;

import com.google.gson.Gson;
import com.wnd.imd.networking.APIService;
import com.wnd.imd.networking.response.PSIResponseModel;

import java.io.IOException;
import io.reactivex.Observable;

/**
 * Created by Wandy on 9/17/17.
 */

public class MockedAPIService implements APIService {

    private Boolean isError = false;
    public static final String isNoConnection = "isNoConnection";
    public static final String HEALTHY = "healthy";

    @Override
    public Observable<PSIResponseModel> getPSI() {
        if(!isError) {
            final String json = "{\n" +
                    "    \"region_metadata\": [\n" +
                    "        {\n" +
                    "            \"name\": \"west\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 1.35735,\n" +
                    "                \"longitude\": 103.7\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"national\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 0,\n" +
                    "                \"longitude\": 0\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"east\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 1.35735,\n" +
                    "                \"longitude\": 103.94\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"central\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 1.35735,\n" +
                    "                \"longitude\": 103.82\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"south\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 1.29587,\n" +
                    "                \"longitude\": 103.82\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"north\",\n" +
                    "            \"label_location\": {\n" +
                    "                \"latitude\": 1.41803,\n" +
                    "                \"longitude\": 103.82\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"items\": [\n" +
                    "        {\n" +
                    "            \"timestamp\": \"2017-09-16T15:00:00+08:00\",\n" +
                    "            \"update_timestamp\": \"2017-09-16T15:06:18+08:00\",\n" +
                    "            \"readings\": {\n" +
                    "                \"o3_sub_index\": {\n" +
                    "                    \"west\": 15,\n" +
                    "                    \"national\": 15,\n" +
                    "                    \"east\": 15,\n" +
                    "                    \"central\": 13,\n" +
                    "                    \"south\": 9,\n" +
                    "                    \"north\": 12\n" +
                    "                },\n" +
                    "                \"pm10_twenty_four_hourly\": {\n" +
                    "                    \"west\": 22,\n" +
                    "                    \"national\": 31,\n" +
                    "                    \"east\": 28,\n" +
                    "                    \"central\": 24,\n" +
                    "                    \"south\": 30,\n" +
                    "                    \"north\": 31\n" +
                    "                },\n" +
                    "                \"pm10_sub_index\": {\n" +
                    "                    \"west\": 22,\n" +
                    "                    \"national\": 31,\n" +
                    "                    \"east\": 28,\n" +
                    "                    \"central\": 24,\n" +
                    "                    \"south\": 30,\n" +
                    "                    \"north\": 31\n" +
                    "                },\n" +
                    "                \"co_sub_index\": {\n" +
                    "                    \"west\": 4,\n" +
                    "                    \"national\": 6,\n" +
                    "                    \"east\": 6,\n" +
                    "                    \"central\": 5,\n" +
                    "                    \"south\": 5,\n" +
                    "                    \"north\": 5\n" +
                    "                },\n" +
                    "                \"pm25_twenty_four_hourly\": {\n" +
                    "                    \"west\": 12,\n" +
                    "                    \"national\": 19,\n" +
                    "                    \"east\": 18,\n" +
                    "                    \"central\": 17,\n" +
                    "                    \"south\": 18,\n" +
                    "                    \"north\": 19\n" +
                    "                },\n" +
                    "                \"so2_sub_index\": {\n" +
                    "                    \"west\": 6,\n" +
                    "                    \"national\": 16,\n" +
                    "                    \"east\": 10,\n" +
                    "                    \"central\": 13,\n" +
                    "                    \"south\": 16,\n" +
                    "                    \"north\": 5\n" +
                    "                },\n" +
                    "                \"co_eight_hour_max\": {\n" +
                    "                    \"west\": 0.45,\n" +
                    "                    \"national\": 0.58,\n" +
                    "                    \"east\": 0.58,\n" +
                    "                    \"central\": 0.46,\n" +
                    "                    \"south\": 0.49,\n" +
                    "                    \"north\": 0.48\n" +
                    "                },\n" +
                    "                \"no2_one_hour_max\": {\n" +
                    "                    \"west\": 7,\n" +
                    "                    \"national\": 44,\n" +
                    "                    \"east\": 44,\n" +
                    "                    \"central\": 22,\n" +
                    "                    \"south\": 18,\n" +
                    "                    \"north\": 10\n" +
                    "                },\n" +
                    "                \"so2_twenty_four_hourly\": {\n" +
                    "                    \"west\": 9,\n" +
                    "                    \"national\": 25,\n" +
                    "                    \"east\": 16,\n" +
                    "                    \"central\": 21,\n" +
                    "                    \"south\": 25,\n" +
                    "                    \"north\": 8\n" +
                    "                },\n" +
                    "                \"pm25_sub_index\": {\n" +
                    "                    \"west\": 49,\n" +
                    "                    \"national\": 59,\n" +
                    "                    \"east\": 57,\n" +
                    "                    \"central\": 56,\n" +
                    "                    \"south\": 57,\n" +
                    "                    \"north\": 59\n" +
                    "                },\n" +
                    "                \"psi_twenty_four_hourly\": {\n" +
                    "                    \"west\": 49,\n" +
                    "                    \"national\": 59,\n" +
                    "                    \"east\": 57,\n" +
                    "                    \"central\": 56,\n" +
                    "                    \"south\": 57,\n" +
                    "                    \"north\": 59\n" +
                    "                },\n" +
                    "                \"o3_eight_hour_max\": {\n" +
                    "                    \"west\": 35,\n" +
                    "                    \"national\": 35,\n" +
                    "                    \"east\": 35,\n" +
                    "                    \"central\": 31,\n" +
                    "                    \"south\": 22,\n" +
                    "                    \"north\": 29\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"api_info\": {\n" +
                    "        \"status\": \"healthy\"\n" +
                    "    }\n" +
                    "}";
            PSIResponseModel psiResponseModel = new Gson().fromJson(json, PSIResponseModel.class);
            return Observable.just(psiResponseModel);
        } else {
            return Observable.error(new IOException(isNoConnection));
        }
    }

    public void throwError(boolean throwError) {
        isError = throwError;
    }
}
