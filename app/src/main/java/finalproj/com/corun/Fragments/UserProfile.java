package finalproj.com.corun.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import finalproj.com.corun.Activities.SignInActivity;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.Class.Runner;
import finalproj.com.corun.DB.Volley;
import finalproj.com.corun.R;

public class UserProfile extends Fragment{

    private static final int LOG_OUT = 3;

    public UserProfile(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.user_profile_fragment, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.user_profile_pic);
        Button disconnect = (Button) rootView.findViewById(R.id.disconnect_button);
        Runner curr_runner  = Manager.getAppManger().getCurrentRunner();

//        imageView.setImageBitmap(Volley.getVolleyIns().LoadImageFromUrl(String.valueOf(curr_runner.getUser().pic_url)));

        Picasso.with(getActivity()).load(curr_runner.getUser().pic_url).into(imageView);

        TextView mStatusTextView = (TextView) rootView.findViewById(R.id.status);
        mStatusTextView.setText("Currently loged as: " + curr_runner.getUser().full_name);

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SignInActivity.class);
                i.putExtra("LOGOUT", "logout");
                startActivityForResult(i, LOG_OUT);
            }
        });

        return rootView;
    }
}
