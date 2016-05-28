package finalproj.com.corun.Fragments;


import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.R;

public class SettingsFragment extends Fragment{

    public SettingsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container,false);
        return rootView;


    }
}
