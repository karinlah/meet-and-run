package finalproj.com.corun.Class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import finalproj.com.corun.DB.CONST_DB;
import finalproj.com.corun.R;

/**
 * Created by Karini on 4/6/2016.
 */
public class PendingListAdapter extends ArrayAdapter<RunEvent>{

    private final Context context;
    private final ArrayList<RunEvent> values;

    public PendingListAdapter(Context context, ArrayList<RunEvent> events) {
        super(context, 0, events);
        this.context = context;
        this.values = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RunEvent event = Manager.getAppManger().getMyEventBook().get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_runsbooks, parent, false);
        }

        TextView tv_runtime = (TextView) convertView.findViewById(R.id.row_run_time);
        TextView tv_rundate = (TextView) convertView.findViewById(R.id.row_run_data);
        ImageView im_image = (ImageView) convertView.findViewById(R.id.row_image);
        ImageButton ib_map = (ImageButton)convertView.findViewById(R.id.ev_location);
        ib_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(CONST_DB.GET_MAP_ROUTH+
                                    Manager.getAppManger().getUserLogedin().getLocation().getLatitude()+","+Manager.getAppManger().getUserLogedin().getLocation().getLongitude()));
                getContext().startActivity(intent);
            }
        });


        tv_rundate.setText(event.getRun_time().dateToString());
        tv_runtime.setText(event.getRun_time().timeToString());

//        StringBuilder builder = new StringBuilder();
//
//        for (Runner par : event.getParticipates()){
//            builder.append(par.getUser().full_name);
//            builder.append(" , ");
//        }
//        builder.setCharAt(builder.length()-2,' ');
//        tv_par.setText(builder);

//        Bitmap bitmap;

        switch (event.getRun_time().gettime()){

            case EARLY_MORNING:
                  im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.e_mor));
                   break;
            case MORNING:
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.morning));

                break;
            case E_AFTERNOON :
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.e_afternoon));

                break;
            case AFTERNOON :
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.afternoon));

                break;
               case EVENING :
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.evening));

                break;
            case NIGHT :
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.night));

                break;
            case M_NIGHT:
                im_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.m_night));

                break;
        }

        return convertView;
   }
}
