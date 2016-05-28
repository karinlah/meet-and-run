package finalproj.com.corun.Fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.telerik.widget.list.ListViewDataSourceAdapter;
import com.telerik.widget.list.RadListView;

import java.util.ArrayList;

import finalproj.com.corun.Class.RunEvent;
import finalproj.com.corun.Class.RunListAdapter;
import finalproj.com.corun.R;
import finalproj.com.corun.Class.Manager;

public class RunBookFragment extends Fragment {

//    ArrayList<RunEvent> runEvents = null;

    public void RunBookFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_runsbooks_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.run_list);
        listView.setAdapter(new RunListAdapter(
                getActivity(),
                Manager.getAppManger().getMyEventBook()));

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                ArrayList<RunEvent> runEvents = Manager.getAppManger().getMyEventBook();
////                Toast.makeText(getActivity(),
////                                runEvents.get(position).getParticipates().get(0).getUser().full_name,
////                                Toast.LENGTH_LONG).show();
//            }
//        });

        return view;

    }

}
