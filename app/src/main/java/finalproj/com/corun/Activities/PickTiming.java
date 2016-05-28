package finalproj.com.corun.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import finalproj.com.corun.Class.DateTime;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.R;

public class PickTiming extends AppCompatActivity implements View.OnClickListener {

    private TimePicker tp = null;
    Button btn_ok = null;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_timing);
        i = getIntent();

        ((ImageButton) findViewById(R.id.btn_e_mor)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_mor)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_e_after)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_after)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_eve)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_m_night)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.btn_night)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        String extra = "";

        switch (v.getId()) {
            case R.id.btn_e_mor:
                extra = String.valueOf(DateTime.DAY_TIMES.EARLY_MORNING);
                break;

            case R.id.btn_mor:
                extra = String.valueOf(DateTime.DAY_TIMES.MORNING);
                break;

            case R.id.btn_e_after:
                extra = String.valueOf(DateTime.DAY_TIMES.E_AFTERNOON);
                break;

            case R.id.btn_after:
                extra = String.valueOf(DateTime.DAY_TIMES.AFTERNOON);
                break;

            case R.id.btn_eve:
                extra = String.valueOf(DateTime.DAY_TIMES.EVENING);
                break;

            case R.id.btn_night:
                extra = String.valueOf(DateTime.DAY_TIMES.NIGHT);
                break;

            case R.id.btn_m_night:
                extra = String.valueOf(DateTime.DAY_TIMES.M_NIGHT);
                break;
        }

        intent.putExtra("TIME",extra);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }
}
