package finalproj.com.corun.DB;

import android.location.Location;
import android.net.Uri;

import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.games.event.EventRef;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

import finalproj.com.corun.Class.DateTime;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.Class.RunEvent;
import finalproj.com.corun.Class.Runner;
import finalproj.com.corun.Class.User;

/**
 * Created by Karini on 3/18/2016.
 */
public final class DB {

    private final double DISTANCE = 0.01;
    private final static finalproj.com.corun.DB.DB DB = new DB();
    private ArrayList<Runner> runners = new ArrayList<>();

    private DB() {
    }

    public static DB getDB() {
        return DB;
    }

    private void sql (String query)
    {

    }

    public void getUser(String mail, String userid, String pic) {

        boolean suc = true;
        String url = CONST_DB.BASE_URL + CONST_DB.GET_USER_PATH + mail;
        Volley.getVolleyIns().HandleJSONRequest(url, Volley.OP_GET_USER);

        //  New User
        Manager.getAppManger().setNew_user(true);
        User u = new User(mail, userid, pic);

        // Save the user in DB
//        AddNewUser(u);
        Manager.setUserLogedin(u);
        Manager.getAppManger().setCurrentRunner(new Runner(u));
    }


    public void HandleUser() {

        JSONObject j = Manager.getAppManger().getJson_result();

        // Create new user
        if (Manager.getAppManger().getJson_result().length() == 0) {
            this.AddNewUser(Manager.getAppManger().getUserLogedin());

        } else {

            try {

                User user = new User(j.getString(CONST_DB.USER_MAIL),
                                        j.getString(CONST_DB.USER_NAME),
                                        j.getString(CONST_DB.USER_PIC));

//                LatLng l = new LatLng(j.getDouble(CONST_DB.RUNNER_LAT), j.getDouble(CONST_DB.RUNNER_LNG));

                Runner runner = new Runner(user,
                        j.getInt(CONST_DB.RUNNER_GENDER),
                        j.getInt(CONST_DB.RUNNER_AGE));

                runner.setRunner_id(j.getInt(CONST_DB.RUNNER_ID));
                Manager.setUserLogedin(user);
                Manager.getAppManger().setCurrentRunner(runner);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        Manager.getAppManger().setJson_result(new JSONObject());
//        getRunsBook(Manager.getAppManger().getCurrentRunner().getRunner_id());
        getUserDate();
    }

    public void AddNewUser(User user) {
        // Add user to DB
        AddNewRunner(user);

        // Set to be current
    }

    public ArrayList<RunEvent> getEventsAround(Location currUserLoc){

        ArrayList<RunEvent> events = new ArrayList<>();

        //TODO - gets from DB the events happens around - if there is nothing , put fake
        if(events.size() == 0)
        {

            if(runners.size() == 0)
            {
                this.getEventParticipate();
            }

            Random r = new Random();
            int e = r.nextInt(5)+1;
            for(int i = 0; i < e ; i++) {

                //Event time
                int t = r.nextInt(7);
                DateTime dateTime = new DateTime();
//                dateTime.chageTime(DateTime.DAY_TIMES.values()[t]);
                dateTime.chageTime(Manager.getAppManger().getCurrent_run().getRun_time().gettime());


                // Put event participates
                ArrayList<Runner> par = new ArrayList<>();

                int p = r.nextInt(runners.size()) + 1;

                Collections.shuffle(runners);
                for (int j = 0; j < p; j++) {
                    par.add(runners.get(j));
                }

                float d_lat = (float) (r.nextFloat() * 0.03);
                float d_lng = (float) (r.nextFloat() * 0.03);

                LatLng latLng = new LatLng(currUserLoc.getLatitude()+d_lat,
                                            currUserLoc.getLongitude()+d_lng);

                int dis = r.nextInt(10) + 4;
                RunEvent event = new RunEvent(dateTime,
                                             par.get(0).getUserSettings().getPace(),
                                             dis,
                                             latLng);
                event.setParticipates(par);
                events.add(event);
            }
        }

        return events;
    }


    //TODO
    // Get the participate from DB
    private void getEventParticipate() {

        // Get all the runners that participate in the event
        runners =  new ArrayList<>();
        Runner r;
        User user;

//        LatLng latLng1 = new LatLng(currUserLoc.getLatitude()+DISTANCE, currUserLoc.getLongitude());
        user = new User("r@gmail.com","Reut");
        r = new Runner(user,Runner.FEMALE, 27);
        r.getUserSettings().setPace(7, 0);
        runners.add(r);

//        LatLng latLng2 = new LatLng(currUserLoc.getLatitude()+DISTANCE, currUserLoc.getLongitude()+DISTANCE);
        user = new User("Mor@gmail.com","Mor");
        r = new Runner(user,Runner.FEMALE, 27);
        r.getUserSettings().setPace(6, 30);
        runners.add(r);

//        LatLng latLng3 = new LatLng(currUserLoc.getLatitude(), currUserLoc.getLongitude()+DISTANCE);
        user = new User("k@gmail.com","Katia");
        r = new Runner(user,Runner.FEMALE, 25);
        r.getUserSettings().setPace(4, 45);
        runners.add(r);

//        LatLng latLng4 = new LatLng(currUserLoc.getLatitude()+0.002, currUserLoc.getLongitude()+0.002);
        user = new User("yaron@gmail.com","Yaron");
        r = new Runner(user,Runner.MALE,28);
        r.getUserSettings().setPace(4, 50);
        runners.add(r);

//        LatLng latLng5 = new LatLng(currUserLoc.getLatitude(), currUserLoc.getLongitude()+0.003);
        user = new User("dor@gmail.com","Dor");
        r = new Runner(user,Runner.MALE,28);
        r.getUserSettings().setPace(5, 0);
        runners.add(r);
    }

    public void UpdateUserLocation(Location location) {

        // Upadate in DB
    }

    public void AddNewRunner(User user) {

        Runner runner = new Runner(user);

        //set ruuner id
        Manager.getAppManger().setCurrentRunner(runner);

        //TODO
        // Add runner to DB
    }

    public Runner getRunnerByUser(User user) {

        // Search the runner in DB by the user mail
        return new Runner(user);
    }

    public void getUserDate(){

        // GET RUN BOOK
        // GET USER SETTING
    }

    public void getRunsBook (int runner_id) {

        if(runner_id == 0)
        {
            return;
        }

        String url = CONST_DB.BASE_URL + CONST_DB.GET_RUNBOOK_PATH + runner_id;
        Volley.getVolleyIns().HandleJsonArrayRequest(url, Volley.OP_GET_RUN_BOOK);
    }

    public void HandleRunBook(){

        JSONArray j = Manager.getAppManger().getJson_arr_result();

        if(j.length() == 0)
        {
            return;
        }

        RunEvent event;
        for(int i=1 ; i <j.length() ; i++){

            try {
                JSONObject curr_j= j.getJSONObject(i);
                event = new RunEvent();
                Calendar cal = Calendar.getInstance();
//                cal.set();
                String date = curr_j.getString(CONST_DB.EVENT_DATE);
                event.setRun_time(DateTime.DAY_TIMES.valueOf(
                                    String.valueOf(curr_j.getInt(CONST_DB.EVENT_TIME))));
                event.setEventID(curr_j.getInt(CONST_DB.EVENT_ID));

                Manager.getAppManger().getMyEventBook().add(event);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Manager.getAppManger().setJson_arr_result(new JSONArray());
    }


//    public ArrayList<Runner> getRunnersForEvent(RunEvent runEvent){
//
//        // TODO: SELECT FROM DB ALL THE EVENTS THAT HAPPEN IN THE SELECTED TIME
//        // If found events in that time ---> get the runners in that events
//        if(true) {
//
//            // Add the runners of the event we found to the runners table
////            Manager.getAppManger().runEvents = new ArrayList<>();
//
//            //TODO
//            //Get event from the db my the time and data of imput parameter
//            RunEvent event = new RunEvent(runEvent.getRun_time());
//            this.runnersTable(event);
//            event.setParticipates(this.t_runners);
//
////            Manager.getAppManger().runEvents.add(event);
//        }
//
//        return t_runners;
//    }

    public void addNewParticipate(Runner par, RunEvent event)
    {
        //TODO
        event.getParticipates().add(par);

        // Add paticipate to the DB if the run doesn't exist - create it
    }

    public void addNewRun(RunEvent current_run, Runner currentRunner) {
        // Add to DB - Create new run

        this.addNewParticipate(currentRunner, current_run);
    }

    public void addToMyRunBook(RunEvent event)
    {
        // TODO
        // Add it to the DB
    }
}

