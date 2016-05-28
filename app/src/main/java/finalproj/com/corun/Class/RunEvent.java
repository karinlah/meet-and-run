package finalproj.com.corun.Class;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Range;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Karini on 4/2/2016.
 */
public class RunEvent{

    int eventID = 0;
    DateTime run_time = null;
    Pace pace;
    int distance;
    private Location location = null;
    private LatLng latLng = null;

    Runner run_creator;
    ArrayList<Runner> participates = new ArrayList<>();


    public RunEvent(){
        //TODO - DELETE
        run_time = new DateTime();
        this.pace = new Pace(5,30);
        this.distance = 10;

        if (Manager.getAppManger().getUserLogedin().getLocation() !=null)
        {
            location = Manager.getAppManger().getUserLogedin().getLocation();
            latLng = new LatLng(location.getLatitude(),location.getLongitude());
        }
    }

    public RunEvent(DateTime t, Pace p, int d, LatLng l){
        run_time = t;
        pace = p;
        distance = d;
        latLng = l;
        location = new Location("event location");
        location.setLongitude(l.longitude);
        location.setLatitude(l.latitude);
    }

    public RunEvent(DateTime time) {
        new RunEvent();
        run_time = time;
    }

    public void setRun_time_date(Calendar cal, DateTime.DAY_TIMES time ) {
        this.run_time.chageDate(cal);
        this.run_time.chageTime(time);
    }

    public Location getLocation() {
        return location;
    }

    public void setRun_time(DateTime.DAY_TIMES time ) {
        this.run_time.chageTime(time);
    }

    public void setRun_date(Calendar calendar) {
        this.run_time.chageDate(calendar);
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public DateTime getRun_time() {
        return run_time;
    }


    public ArrayList<Runner> getParticipates() {
        return participates;
    }

    public void setParticipates(ArrayList<Runner> participates) {
        run_creator = participates.get(0);
        this.participates = participates;
    }

    public void setRun_creator(Runner run_creator) {
        this.run_creator = run_creator;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Pace getPace() {
        return pace;
    }

    public int getDistance() {
        return distance;
    }


////    Parcelable part
////    *********************
//    public RunEvent(Parcel in) {
//
//        //  mail,full name, pic, dis-mis, dis-max ,pace m, pace s, age
////        String[] data = new String[8];
////        in.readStringArray(data);
////
////        User user = new User(data[0], data[1], data[2]);
////        this.user = user;
////
////        Range<Integer> range = new Range<Integer>(Integer.parseInt(data[3]),
////                Integer.parseInt(data[4]));
////
////        this.userSettings = new UserSettings();
////        this.userSettings.distanc_range = range;
////        this.userSettings.setPace(Integer.parseInt(data[5]), Integer.parseInt(data[6]));
////        this.age = Integer.parseInt(data[7]);
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        //  mail,full name, pic, dis-mis, dis-max ,pace min, pace sec
////        dest.writeStringArray(new String[]{
////                this.getUser().mail,
////                this.getUser().full_name,
////                this.getUser().pic_url,
////                String.valueOf(this.userSettings.distanc_range.getLower()),
////                String.valueOf(this.userSettings.distanc_range.getUpper()),
////                String.valueOf(this.userSettings.getPace().min),
////                String.valueOf(this.userSettings.getPace().sec),
////                String.valueOf(this.age)});
//    }
//
//    public static final Parcelable.Creator<RunEvent> CREATOR = new Parcelable.Creator<RunEvent>() {
//
//        @Override
//        public RunEvent createFromParcel(Parcel source) {
//            //  mail,full name, pic, dis-mis, dis-max ,pace m, pace s
//            return new RunEvent(source);
//        }
//
//        @Override
//        public RunEvent[] newArray(int size) {
//            return new RunEvent[size];
//        }
//    };

}
