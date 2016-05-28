package finalproj.com.corun.DB;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;

import finalproj.com.corun.Class.AppController;
import finalproj.com.corun.Class.Manager;

/**
 * Created by Karini on 4/1/2016.
 */
public final class Volley {

    private static final Volley volley_ins = new Volley();
    private static final String TAG = "VOLLEY:";
    public static final int OP_GET_USER = 1;
    public static final int OP_GET_RUN_BOOK = 2;

    public static Volley getVolleyIns() {
        return volley_ins;
    }

    public void HandleJSONRequest(String url, final int op ) {

        final String TAG = "CONNECT JSON";
        final JSONObject res_json = new JSONObject();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Manager.getAppManger().setJson_result(response);

                        switch (op)
                        {
                            case(OP_GET_USER):
                                {
                                    DB.getDB().HandleUser();
                                }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "onErrorResponse from volley");
                Manager.getAppManger().setJson_result(null);

            }
        });

        jsonObjReq.setRetryPolicy(
                new DefaultRetryPolicy(0, 0, 0));

//      Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    public void HandleJsonArrayRequest(String url, final int op) {

        Log.d("Volley", "Array request");

        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                Manager.getAppManger().setJson_arr_result(response);
                switch (op)
                {
                    case OP_GET_RUN_BOOK:
                        DB.getDB().HandleRunBook();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, "ARRAY JSON");
    }


    public void HandleStringRequest(String url) {

        Log.d("Volley", "string request");

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);


    }

}
