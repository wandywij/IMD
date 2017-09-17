package com.wnd.imd.networking.response;

import java.util.ArrayList;

/**
 * Created by Wandy on 9/14/17.
 */

public class PSIResponseModel {
    public final Region_metadata region_metadata[];
    public final ArrayList<Item> items;
    public final Api_info api_info;

    public PSIResponseModel(Region_metadata[] region_metadata, ArrayList<Item> items, Api_info api_info) {
        this.region_metadata = region_metadata;
        this.items = items;
        this.api_info = api_info;
    }

    public static final class Region_metadata {
        public final String name;
        public final Label_location label_location;

        public Region_metadata(String name, Label_location label_location) {
            this.name = name;
            this.label_location = label_location;
        }

        public static final class Label_location {
            public final double latitude;
            public final double longitude;

            public Label_location(double latitude, double longitude) {
                this.latitude = latitude;
                this.longitude = longitude;
            }
        }
    }

    public static final class Item {
        public final String timestamp;
        public final String update_timestamp;
        public final Readings readings;

        public Item(String timestamp, String update_timestamp, Readings readings) {
            this.timestamp = timestamp;
            this.update_timestamp = update_timestamp;
            this.readings = readings;
        }

        public static final class Readings {
            public final O3_sub_index o3_sub_index;
            public final Pm10_twenty_four_hourly pm10_twenty_four_hourly;
            public final Pm10_sub_index pm10_sub_index;
            public final Co_sub_index co_sub_index;
            public final Pm25_twenty_four_hourly pm25_twenty_four_hourly;
            public final So2_sub_index so2_sub_index;
            public final Co_eight_hour_max co_eight_hour_max;
            public final No2_one_hour_max no2_one_hour_max;
            public final So2_twenty_four_hourly so2_twenty_four_hourly;
            public final Pm25_sub_index pm25_sub_index;
            public final Psi_twenty_four_hourly psi_twenty_four_hourly;
            public final O3_eight_hour_max o3_eight_hour_max;

            public Readings(O3_sub_index o3_sub_index, Pm10_twenty_four_hourly pm10_twenty_four_hourly, Pm10_sub_index pm10_sub_index, Co_sub_index co_sub_index, Pm25_twenty_four_hourly pm25_twenty_four_hourly, So2_sub_index so2_sub_index, Co_eight_hour_max co_eight_hour_max, No2_one_hour_max no2_one_hour_max, So2_twenty_four_hourly so2_twenty_four_hourly, Pm25_sub_index pm25_sub_index, Psi_twenty_four_hourly psi_twenty_four_hourly, O3_eight_hour_max o3_eight_hour_max) {
                this.o3_sub_index = o3_sub_index;
                this.pm10_twenty_four_hourly = pm10_twenty_four_hourly;
                this.pm10_sub_index = pm10_sub_index;
                this.co_sub_index = co_sub_index;
                this.pm25_twenty_four_hourly = pm25_twenty_four_hourly;
                this.so2_sub_index = so2_sub_index;
                this.co_eight_hour_max = co_eight_hour_max;
                this.no2_one_hour_max = no2_one_hour_max;
                this.so2_twenty_four_hourly = so2_twenty_four_hourly;
                this.pm25_sub_index = pm25_sub_index;
                this.psi_twenty_four_hourly = psi_twenty_four_hourly;
                this.o3_eight_hour_max = o3_eight_hour_max;
            }

            public static final class O3_sub_index {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public O3_sub_index(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Pm10_twenty_four_hourly {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Pm10_twenty_four_hourly(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Pm10_sub_index {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Pm10_sub_index(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Co_sub_index {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Co_sub_index(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Pm25_twenty_four_hourly {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Pm25_twenty_four_hourly(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class So2_sub_index {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public So2_sub_index(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Co_eight_hour_max {
                public final double west;
                public final double national;
                public final double east;
                public final double central;
                public final double south;
                public final double north;

                public Co_eight_hour_max(double west, double national, double east, double central, double south, double north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class No2_one_hour_max {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public No2_one_hour_max(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class So2_twenty_four_hourly {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public So2_twenty_four_hourly(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Pm25_sub_index {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Pm25_sub_index(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class Psi_twenty_four_hourly {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public Psi_twenty_four_hourly(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }

            public static final class O3_eight_hour_max {
                public final long west;
                public final long national;
                public final long east;
                public final long central;
                public final long south;
                public final long north;

                public O3_eight_hour_max(long west, long national, long east, long central, long south, long north) {
                    this.west = west;
                    this.national = national;
                    this.east = east;
                    this.central = central;
                    this.south = south;
                    this.north = north;
                }
            }
        }
    }

    public static final class Api_info {
        public final String status;

        public Api_info(String status) {
            this.status = status;
        }
    }
}