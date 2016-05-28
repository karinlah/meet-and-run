package finalproj.com.corun.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import finalproj.com.corun.Class.DateTime;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.R;

public class PickSpecificTime extends AppCompatActivity {

    private TimePicker tp = null;
    Button btn_ok = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pick_spec_time);
//
//        tp = (TimePicker) findViewById(R.id.timePmicker);
//        btn_ok = (Button) findViewById(R.id.btn_ok_time);
//
//        Calendar calendar = Manager.getAppManger().getCurrent_run().getRun_time().getCal();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            tp.setHour(calendar.get(Calendar.HOUR_OF_DAY));
//            tp.setMinute(calendar.get(Calendar.MINUTE));
//        }
//        else {
//
//            tp.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//            tp.setCurrentMinute(calendar.get(Calendar.MINUTE));
//        }
//
//        btn_ok.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Calendar calendar = Calendar.getInstance();
//
//                //Set the calender
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    calendar.set(Calendar.HOUR, tp.getHour());
//
//                    if(tp.getHour() > 12)
//                    {
//                        calendar.set(Calendar.AM_PM, Calendar.AM);
//                    }
//                    else {
//                        calendar.set(Calendar.AM_PM, Calendar.PM);
//                    }
//
//                    calendar.set(Calendar.MINUTE, tp.getMinute());
//                    calendar.set(Calendar.SECOND, 0);
//                }
//                else {
//                    calendar.set(Calendar.HOUR, tp.getCurrentHour());
//                    calendar.set(Calendar.MINUTE, tp.getCurrentMinute());
//                }
//
//                Manager.getAppManger().getCurrent_run().setRun_time(calendar,DateTime.TIME);
//                setResult(MainActivity.RESULT_OK);
//                finish();
//            }
//        });

    }
}
