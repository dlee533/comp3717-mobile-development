package com.bcit.dongmin_lab6;

import com.google.gson.Gson;

public class Place {

    String xid;
    String name;
    double dist;
    int rate;
    String osm;
    String kinds;
    Point point;

    public double getLon() {
        return point.lon;
    }

    public double getLat() {
        return point.lat;
    }

    public String getName() {
        return name;
    }

}

class Point {
    double lon;
    double lat;
}
/**
 * [
 *   {
 *     "name": "Oakland City Hall",
 *     "osm": "relation/4682064",
 *     "xid": "R4682064",
 *     "wikidata": "Q932794",
 *     "kind": "architecture,other_buildings_and_structures,historic_architecture,interesting_places",
 *     "point": {
 *       "lon": -122.272705,
 *       "lat": 37.80513
 *     }
 *   }
 * ]
 */
