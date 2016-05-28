package finalproj.com.corun.Class;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.firstparty.InitializeBuyFlowRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import finalproj.com.corun.Class.DateTime.DAY_TIMES;
import finalproj.com.corun.DB.CONST_DB;
import finalproj.com.corun.Fragments.RunBookFragment;
import finalproj.com.corun.R;

/**
 * Created by Karini on 4/6/2016.
 */
public class RunListAdapter extends ArrayAdapter<RunEvent>{

    private final Context context;
    private final ArrayList<RunEvent> values;

    public RunListAdapter(Context context, ArrayList<RunEvent> events) {
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

        return convertView;
   }
}
