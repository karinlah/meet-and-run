package finalproj.com.corun.Class;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import finalproj.com.corun.R;


/**
 * Created by Karini on 3/28/2016.
 */
public class DateTime implements Serializable{

    public static enum DAY_TIMES {
        EARLY_MORNING,MORNING, E_AFTERNOON ,AFTERNOON ,EVENING ,NIGHT ,M_NIGHT }

    public static final int TIME = 1;
    public static final int DATE = 2;

    private Date date;
    private DAY_TIMES time;
    Calendar cal = Calendar.getInstance();

    public DateTime(){

        //Handle date
        date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("MMM d");
        String.format(f.format(date));
        cal.setTime(date);

        // set time
        this.time = DAY_TIMES.MORNING;
    }

    public Calendar getCal() {
        return cal;
    }


    public DAY_TIMES gettime(){
        return this.time;
    }

    public String timeToString() {

        switch (this.time){
            case EARLY_MORNING:
                return "Early morning (5-7 AM)";
            case MORNING:
                return "Morning(8-11 AM)";
            case E_AFTERNOON :
                return "Early afternoon (11-1 AM)";
            case AFTERNOON :
                return "Afternoon(2-4 PM)";
            case EVENING :
                return "Evening(5-8 PM)";
            case NIGHT :
                return "Night(9-11 PM)";
            case M_NIGHT:
                return "Middle of the night(12-4 AM)";
        }
        return null;
    }

    public void chageDate(Calendar c)
    {
        cal.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
        cal.set(Calendar.YEAR, c.get(Calendar.YEAR));
    }

    public void chageTime(DAY_TIMES time){
        this.time = time;
    }

    public String dateToString(){
        SimpleDateFormat format = new SimpleDateFormat("EE, MMM d");
        return (String.format(format.format(this.cal.getTime())));

    }



//    public void setNewTime(Calendar c, int TIME_DATE_CODE, DAY_TIMES time){
//
//        switch (TIME_DATE_CODE){
//            case TIME:
////                cal.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR));
////                cal.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
//                this.time = time;
//                break;
//            case DATE:
//                cal.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
//                cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
//                cal.set(Calendar.YEAR, c.get(Calendar.YEAR));
//                break;
//        }
//    }


//    public String dateToString(){
//
//        return (
//                cal.get(Calendar.DAY_OF_MONTH) + "-" +
//                        cal.get(Calendar.MONTH) + "-" +
//                        cal.get(Calendar.YEAR));
//    }

//    public Calendar fromString(String str_cal){
//        Calendar c = Calendar.getInstance();
//
//        String[] date;
//        String[] time;
//
//        String[] datetime = str_cal.split(":");
//        date = datetime[0].split("-");
//        time = datetime[1].split("-");
//
//        c.add(Calendar.HOUR, Integer.parseInt(time[0]));
//        c.add(Calendar.MINUTE,Integer.parseInt(time[1]));
//
//        c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
//        c.add(Calendar.MONTH,Integer.parseInt(date[1]));
//        c.add(Calendar.YEAR, Integer.parseInt(date[2]));
//
//        return c;
//    }
//
//    public String timeToString(){
//
//        SimpleDateFormat format = new SimpleDateFormat("h:m a");
//        return (String.format(format.format(this.cal.getTime())));
//    }
}
