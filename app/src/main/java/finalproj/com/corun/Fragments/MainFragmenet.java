package finalproj.com.corun.Fragments;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import finalproj.com.corun.Activities.MainActivity;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.Class.Runner;
import finalproj.com.corun.R;

public class MainFragmenet extends Fragment {

    public MainFragmenet(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView title = (TextView) rootView.findViewById(R.id.home_page_title);

        ((Button)(rootView.findViewById(R.id.btn_time))).
                setText(Manager.getAppManger().getCurrent_run().getRun_time().timeToString());


        ((Button)(rootView.findViewById(R.id.btn_date))).
                setText(Manager.getAppManger().getCurrent_run().getRun_time().dateToString());

        return rootView;
    }
}
