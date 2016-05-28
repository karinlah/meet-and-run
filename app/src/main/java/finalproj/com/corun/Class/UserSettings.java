package finalproj.com.corun.Class;

import android.location.Location;
import android.os.Build;
import android.util.Range;

/**
 * Created by Karini on 3/17/2016.
 */
public class UserSettings {

    private Pace pace;
    public float km_search_around;
    public Range<Integer> distanc_range = new Range<>(5,10);

    public UserSettings(){
        this.km_search_around = 5;
        this.pace = new Pace(5,30);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            distanc_range = new Range<>(5,10);
        }
    }

    public void setPace(int m, int s) {
        this.pace = new Pace(m,s);
    }

    public Pace getPace() {
        return pace;
    }

    public Range<Integer> getDistanc_range() {
        return distanc_range;
    }
}

