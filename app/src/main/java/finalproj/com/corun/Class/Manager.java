package finalproj.com.corun.Class;


/**
 * Created by Karini on 3/17/2016.
 */
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import finalproj.com.corun.DB.DB;
import finalproj.com.corun.DB.Volley;

public final class Manager {

    private HashMap<Marker,RunEvent> markersHashMap = null;
    private ArrayList<RunEvent> events_around_me = null;
    private ArrayList<RunEvent> myEventBook = new ArrayList<>();

    private JSONObject json_result = new JSONObject();
    private JSONArray json_arr_result = new JSONArray();
    private static DB AppDB = DB.getDB();
    private static Runner currentRunner = null;
    private static User     UserLogedin = null;
    private static Manager AppManger = new Manager();
    private static RunEvent current_run = null;
    private Boolean new_user = false;
    private Boolean server_opr_done = false;

//    private boolean user_sign_in = true;

    private Manager(){
        markersHashMap = new HashMap<>();
    }

    public static Manager getAppManger(){
        return AppManger;
    }

    public void addNewRun(RunEvent event){

        current_run.setRun_creator(currentRunner);
        getAppDB().addNewRun(event, currentRunner);

        myEventBook.add(event);
        getAppDB().addToMyRunBook(event);

    }
    public Boolean getServer_opr_done() {
        return server_opr_done;
    }

    public void setJson_arr_result(JSONArray json_arr_result) {
        this.json_arr_result = json_arr_result;
    }

    public JSONArray getJson_arr_result() {
        return json_arr_result;
    }

    public void setServer_opr_done(Boolean server_opr_done) {
        this.server_opr_done = server_opr_done;
    }

    public Boolean getNew_user() {
        return new_user;
    }

    public void setNew_user(Boolean new_user) {
        this.new_user = new_user;
    }

    public void clearAllMarkers(){
        this.markersHashMap = new HashMap<>();
    }

    public ArrayList<RunEvent> getMyEventBook() {
        return myEventBook;
    }

    public void setUserLogedin(String mail, String userid, String pic) {

        // Get runner by the connected user , if exists from DB
        // if the runner doesn't exist - create one
        getAppDB().getUser(mail,userid,pic);

    }

    public static void setUserLogedin(User userLogedin) {
        UserLogedin = userLogedin;
    }

    public User getUserLogedin() {
        return UserLogedin;
    }

    public void userLogedOut() {
        UserLogedin = null;
    }

    public RunEvent getEventByMarker(Marker marker) {
        return markersHashMap.get(marker);
    }

    public void addMarkerToHashMap(Marker marker, RunEvent event) {
        this.markersHashMap.put(marker, event);
    }

//    public void setCurrentUser(Runner loginUser) {currentUser = loginUser; }
//    public Runner getCurrentUser() { return currentUser;}

    public Runner getCurrentRunner() {
        return currentRunner;
    }


    public void atachRunnerToEvent(RunEvent event){
        getAppDB().addNewParticipate(getCurrentRunner(),event);
        getAppDB().addToMyRunBook(event);
    }

    public void setCurrentRunner(Runner currentRunner) {
        Manager.currentRunner = currentRunner;
    }

    public DB getAppDB() { return AppDB; }

    public RunEvent getCurrent_run() {
        return current_run;
    }

    public void setCurrent_run(RunEvent run) {
        current_run = run;
    }

    public JSONObject getJson_result(){
        return this.json_result;
    }

    public void setJson_result(JSONObject json_result) {
        this.json_result = json_result;
    }

    public ArrayList<RunEvent> getEvents_around_me() {
        events_around_me = getAppDB().getEventsAround(Manager.getAppManger().getUserLogedin().getLocation());
        return events_around_me;
    }
}
