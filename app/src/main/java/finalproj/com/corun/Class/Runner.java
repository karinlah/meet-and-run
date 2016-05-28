package finalproj.com.corun.Class;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Range;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import finalproj.com.corun.DB.DB;

/**
 * Created by Karini on 3/17/2016.
 */
public class Runner implements Parcelable {

    public static final int MALE = 1;
    public static final int FEMALE = 2;

    private User user;
    private int Runner_id = 0;
    private int gender;
    private int age = 0;
//    private Location location = null;
//    private LatLng latLng = null;

    private UserSettings userSettings = null;
//    ArrayList<RunEvent> userActiviesLogBook = null;
    private RunEvent runEvent = null;

    public int getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public LatLng getLatLng() {
//        return latLng;
//    }

//    public Location getLocation() {
//        return location;
//    }

    public Runner(User user) {
        this.user = user;
        this.gender = MALE;
        this.age = 0;
        this.runEvent = new RunEvent();
//        this.location = new Location("Israel");
//        location.setLatitude(32);
//        location.setLongitude(35);
        userSettings = new UserSettings();
    }

//    public Runner(User user, int gender) {
//        this.user = user;
//        this.gender = gender;
//        userSettings = new UserSettings();
//    }


    public void setRunner_id(int runner_id) {
        Runner_id = runner_id;
    }

    public int getRunner_id() {
        return Runner_id;
    }

    public Runner(User user, int gender, int age) {
        this.user = user;
//        this.runEvent = event;
        this.gender = gender;
//        this.latLng = latLng;
        this.age = age;
//        this.location = new Location(user.full_name + " location");
//        location.setLongitude(latLng.longitude);
//        location.setLatitude(latLng.latitude);
        userSettings = new UserSettings();
    }

    public User getUser() {
        return user;
    }

    public int getAge() {
        return age;
    }


    public RunEvent getRunEvent() {
        return runEvent;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    // Parcelable part
//    *********************
    public Runner(Parcel in) {

        //  mail,full name, pic, dis-mis, dis-max ,pace m, pace s, age
        String[] data = new String[8];
        in.readStringArray(data);

        User user = new User(data[0], data[1], data[2]);
        this.user = user;

        Range<Integer> range = new Range<Integer>(Integer.parseInt(data[3]),
                                    Integer.parseInt(data[4]));

        this.userSettings = new UserSettings();
        this.userSettings.distanc_range = range;
        this.userSettings.setPace(Integer.parseInt(data[5]), Integer.parseInt(data[6]));
        this.age = Integer.parseInt(data[7]);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //  mail,full name, pic, dis-mis, dis-max ,pace min, pace sec
        dest.writeStringArray(new String[]{
                this.getUser().mail,
                this.getUser().full_name,
                this.getUser().pic_url,
                String.valueOf(this.userSettings.distanc_range.getLower()),
                String.valueOf(this.userSettings.distanc_range.getUpper()),
                String.valueOf(this.userSettings.getPace().min),
                String.valueOf(this.userSettings.getPace().sec),
                String.valueOf(this.age)});
    }

    public static final Parcelable.Creator<Runner> CREATOR = new Parcelable.Creator<Runner>() {

        @Override
        public Runner createFromParcel(Parcel source) {
            //  mail,full name, pic, dis-mis, dis-max ,pace m, pace s
            return new Runner(source);
        }

        @Override
        public Runner[] newArray(int size) {
            return new Runner[size];
        }
    };
}
