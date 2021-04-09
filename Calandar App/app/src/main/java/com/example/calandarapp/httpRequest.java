package com.example.calandarapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class httpRequest extends Thread
{

    JSONObject jsonObjFromRequest = null;

    JSONArray jsonArrayFromRequest = new JSONArray();

    JSONObject jsonObjectPostResult = null;

    protected Boolean requestSuccess = false;

    RequestQueue queue;

    String urlGetYear = "http://142.11.236.52:8080/userDayData?userID=04dd4132-6341-4394-a9bb-1dbfe4d24906";

    String UUIDForUser1 = "04dd4132-6341-4394-a9bb-1dbfe4d24906";

    String urlPostReq = "http://142.11.236.52:8080/user?username=butts";

    int funcToRunInThread;

    MainActivity ourMainAct;

    public httpRequest(Context mainActivityContext, int function)
    {
        queue = Volley.newRequestQueue( mainActivityContext );

        ourMainAct = (MainActivity) mainActivityContext;

        funcToRunInThread = function;
    }

    public void run()
    {
        if(funcToRunInThread == 1)
        {
            requestSuccess = false;
            trySynchHTTPGetRequestForJSONArray( urlGetYear );
        }
        else if(funcToRunInThread == 2)
        {
            trySynchHTTPPostRequestForJSONArray(urlPostReq);
        }

    }
    /*
    public void run()
    {
        requestSuccess = false;
        tryHTTPGetRequestForJSONArray(urlGetYear);
    }
    */

    public void tryHTTPGetRequest(String url)
    {
        Log.d("Entered", "MAKING REQ");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //textView.setText("Response: " + response.toString());
                        Log.d("success OBJ","REQUEST SUCCESS");
                        jsonObjFromRequest = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("No worky ");
                        Log.d("anError OBJ","REQUEST FAILURE");

                        if (error instanceof AuthFailureError) {
                            Log.d("anError OBJ", "auth");
                        } else if (error instanceof ServerError) {
                            Log.d("anError OBJ", "server");
                        } else if (error instanceof NetworkError) {
                            Log.d("anError OBJ", "network");
                        } else if (error instanceof ParseError) {
                            Log.d("anError OBJ", "parse");
                        }
                        Log.d("MESSAGE anError OBJ", "Error MESSAGE: " + error.getMessage());

                    }
                });

        //textView.setText("Text changed, method called ");

        queue.add(jsonObjectRequest);
    }

    public void tryHTTPPutRequest(String url, JSONObject putObject)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, putObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("successOBJPUT","REQUEST SUCCESS");

                        ourMainAct.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ourMainAct.tryGetRequestThreadAndLoadCal();
                            }
                        });

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("anErrorOBJPUT","REQUEST FAILURE");

                        if (error instanceof AuthFailureError) {
                            Log.d("anError OBJ PUT", "auth");
                        } else if (error instanceof ServerError) {
                            Log.d("anError OBJ PUT", "server");
                        } else if (error instanceof NetworkError) {
                            Log.d("anError OBJ PUT", "network");
                        } else if (error instanceof ParseError) {
                            Log.d("anError OBJ PUT", "parse");
                        }
                        Log.d("MESSAGE anError OBJ PUT", "Error MESSAGE: " + error.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void tryHTTPGetRequestForJSONArray(String url)
    {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("THREADTESTgetarray","REQUEST SUCCESS");
                        jsonArrayFromRequest =  response;
                        requestSuccess = true;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("anError Array","REQUEST FAILURE");

                        if (error instanceof AuthFailureError) {
                            Log.d("anError Array", "auth");
                        } else if (error instanceof ServerError) {
                            Log.d("anError Array", "server");
                        } else if (error instanceof NetworkError) {
                            Log.d("anError Array", "network");
                        } else if (error instanceof ParseError) {
                            Log.d("anError Array", "parse");
                        }
                        Log.d("MESSAGE anError Array", "Error MESSAGE: " + error.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void trySynchHTTPPutRequest(String url, JSONObject putObject)
    {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, putObject, future, future);
        queue.add( jsonObjectRequest );


            Log.d("THREADTEST", "waiting for PUT future.get");

            future.isDone();


        ourMainAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ourMainAct.tryGetRequestThreadAndLoadCal();
            }
        });// this will block

            Log.d("THREADTEST", "future.get PUT finished");

            /*
        } catch (InterruptedException e) {
            Log.d("THREADTEST", "future interruption");
            // exception handling
        } catch (
                ExecutionException e) {
            // exception handling
            Log.d("THREADTEST", "execution exception");
        }

             */

    }

    public void trySynchHTTPGetRequestForJSONArray(String url)
    {
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, future, future);
        queue.add(request);

        Log.d("THREADTEST","request added");

        try {
            Log.d("THREADTEST", "waiting for future.get");
            jsonArrayFromRequest = future.get(); // this will block


            ourMainAct.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ourMainAct.getJSONArrayResponse(jsonArrayFromRequest);
                }
            });

            Log.d("THREADTEST", "future.get finished");
        } catch (InterruptedException e) {
            Log.d("THREADTEST", "future interruption");
            // exception handling
        } catch (
                ExecutionException e) {
            // exception handling
            Log.d("THREADTEST", "execution exception");
        }
    }

    public void trySynchHTTPPostRequestForJSONArray(String url)
    {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, future, future);

        queue.add(request);

        Log.d("THREADTEST","POST: request added");

        try {
            Log.d("THREADTEST", "POST: waiting for future.get");
            jsonObjectPostResult = future.get(); // this will block


            ourMainAct.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ourMainAct.getJSONObjectPostResponse(jsonObjectPostResult);
                }
            });

            Log.d("THREADTEST", "future.POST finished");
        }
        catch ( InterruptedException e )
        {
            Log.d("THREADTEST", "POST future interruption");
            // exception handling
        }
        catch ( ExecutionException e )
        {
            // exception handling
            Log.d("THREADTEST", "POST execution exception");
        }
    }
}
