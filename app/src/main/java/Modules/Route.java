package Modules;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by aquib on 7/8/17.
 */

public class Route {
    public Distance distance;
    public DurationClass duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
