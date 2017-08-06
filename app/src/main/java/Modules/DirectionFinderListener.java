package Modules;

import java.util.List;

/**
 * Created by aquib on 7/8/17.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
