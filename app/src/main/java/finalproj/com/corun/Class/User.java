package finalproj.com.corun.Class;

import android.location.Location;
import android.net.Uri;
import android.os.Parcel;

import com.google.android.gms.maps.model.LatLng;

import finalproj.com.corun.DB.DB;

/**
 * Created by Karini on 4/1/2016.
 */
public class User {

    private final static String BASIC_PIC_URI = "http://images.mentalfloss.com/sites/default/files/styles/insert_main_wide_image/public/einstein1_7.jpg";
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    private LatLng latLng;
    private static Location location;
    public String mail;
    public String full_name;
    public String pic_url = BASIC_PIC_URI;

    public User(String mail, String full_name, String pic_url) {
        this.mail = mail;
        this.full_name = full_name;
        this.pic_url = pic_url;
    }

    public User(String mail, String full_name) {
        this.mail = mail;
        this.full_name = full_name;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setLocation(Location location) {
        this.location = location;
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
    }

    public Location getLocation()
    {
        return location;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
