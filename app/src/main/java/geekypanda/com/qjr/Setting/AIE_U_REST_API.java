package geekypanda.com.qjr.Setting;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/*
*
 * Created by prasath on 04-02-2017.*/



public class AIE_U_REST_API extends Activity{

    private String TAG = "VOLLEY";
    // Tag used to cancel the request
    private String tag_json_obj = "json_obj_req";

    public void post(String url, final Map<String, String> params, final OnRestCallback callback, final Request.Priority priority) {

        JSONObject paramsJsonObject = null;
        if (params != null) {
            paramsJsonObject = new JSONObject(params);
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, paramsJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, response.toString());
                callback.onRestResponse(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onRestResponse(false, null);
            }
        }) {



            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue

        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);

    }

    public void post(String url, JSONObject params, final OnRestCallback callback, final Request.Priority priority) {

        JSONObject paramsJsonObject = params;


         JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, paramsJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, response.toString());
                callback.onRestResponse(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onRestResponse(false, null);
            }
        }) {



            @Override
            public Priority getPriority() {
                return priority;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);

    }

    public void get(String url, final Map<String, String> params, final OnRestCallback callback, final Request.Priority priority) {
        JSONObject object = new JSONObject();
        if (params == null) {
            object = null;
        } else {
            object = new JSONObject(params);
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, response.toString());
                callback.onRestResponse(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onRestResponse(false, null);
            }
        }) {



            @Override
            public Priority getPriority() {
                return priority;
            }



        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void get(String url, final OnRestCallback callback, final Request.Priority priority) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, response.toString());
                callback.onRestResponse(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onRestResponse(false, null);
            }
        }) {



            @Override
            public Priority getPriority() {
                return priority;
            }




        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void post(String url, final JSONArray params, final OnRestCallback callback, final Request.Priority priority) {

        JSONArray paramsJsonObject = null;
        if (params != null) {
            paramsJsonObject = params;

        }

        JsonPostArrayRequest jsonObjReq = new JsonPostArrayRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, response.toString());


                callback.onRestResponse(true, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onRestResponse(false, null);
            }
        }, paramsJsonObject);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void put(String url, final Map<String, String> params, final OnRestCallback callback, final Request.Priority priority) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, response.toString());
                        callback.onRestResponse(true, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        callback.onRestResponse(false, null);
                    }
                }) {

            @Override
            public Priority getPriority() {
                return priority;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);

    }

    public void put(String url, JSONObject params, final OnRestCallback callback, final Request.Priority priority) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, response.toString());
                        callback.onRestResponse(true, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        callback.onRestResponse(false, null);
                    }
                }) {

            @Override
            public Priority getPriority() {
                return priority;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);

    }

    public void delete(String url, final Map<String, String> params, final OnRestCallback callback, final Request.Priority priority) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.DELETE,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(TAG, response.toString());
                        callback.onRestResponse(true, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        callback.onRestResponse(false, null);
                    }
                }) {

            @Override
            public Priority getPriority() {
                return priority;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Get a RequestQueue
        // Get a RequestQueue
        RequestQueue queue = AIE_U_Singleton.getInstance().getRequestQueue();
        // Adding request to request queue
        AIE_U_Singleton.getInstance().addToRequestQueue(jsonObjReq);
    }

    public interface OnRestCallback {
        public void onRestResponse(boolean success, JSONObject jsonObject);
    }

}

