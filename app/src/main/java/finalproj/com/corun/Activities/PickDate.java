package finalproj.com.corun.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import finalproj.com.corun.Class.DateTime;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.R;

public class PickDate extends AppCompatActivity {

    private DatePicker dp;
    private Button set_date_btn;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        calendar = Calendar.getInstance();
        dp = (DatePicker) findViewById(R.id.datePicker);

        Calendar c = Manager.getAppManger().getCurrent_run().getRun_time().getCal();
        dp.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        set_date_btn = (Button) findViewById(R.id.btn_ok_date);

        set_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set the calender
                Log.d("DATE", "DATE: " + dp.getYear()+ (dp.getMonth() + 1) +  dp.getDayOfMonth());
                calendar.set(dp.getYear(), dp.getMonth()+1, dp.getDayOfMonth());

                Manager.getAppManger().getCurrent_run().setRun_date(calendar);

                //Send the result intent
//                Intent res_i = new Intent();
//                res_i.putExtra("DATE", date.toString());
                setResult(MainActivity.RESULT_OK);
                finish();
            }
        });
    }
}
