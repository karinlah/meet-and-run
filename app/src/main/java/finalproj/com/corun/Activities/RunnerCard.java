package finalproj.com.corun.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.Class.Runner;
import finalproj.com.corun.Class.User;
import finalproj.com.corun.DB.DB;
import finalproj.com.corun.DB.Volley;
import finalproj.com.corun.R;

public class RunnerCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_card);


        // Getting the runner represented by the clicked marker
        Intent intent = getIntent();
        final Runner curr_runner = intent.getParcelableExtra("Runner");

        if(curr_runner == null)
        {
            finish();
        }

        TextView title = (TextView) findViewById(R.id.runner_title);
        title.setText(curr_runner.getUser().full_name);

        ImageView runner_pic = (ImageView) findViewById(R.id.runner_pic);
        Picasso.with(this).load(curr_runner.getUser().pic_url).into(runner_pic);

        TextView age = (TextView) findViewById(R.id.tv_age);
        age.setText(String.valueOf(curr_runner.getAge()));

        ((TextView)findViewById(R.id.tv_pace)).setText(curr_runner.getUserSettings().getPace().toString());
        ((TextView)findViewById(R.id.tv_dis)).setText(String.valueOf(curr_runner.getUserSettings().distanc_range.getLower()) + " - " +
                                                      String.valueOf(curr_runner.getUserSettings().distanc_range.getUpper()));


        FloatingActionButton btn_join_to_run = (FloatingActionButton) findViewById(R.id.FAB_join_to_run);
        btn_join_to_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RunnerCard.this,
                        "Join request sent to " + curr_runner.getUser().full_name,
                        Toast.LENGTH_LONG ).show();

                // Append me to the current event
                Manager.getAppManger().atachRunnerToEvent(Manager.getAppManger().getCurrent_run());
                finish();

            }
        });
    }
}
