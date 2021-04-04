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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class httpRequest extends Thread
{

    JSONObject jsonObjFromRequest = null;

    JSONArray jsonArrayFromRequest = new JSONArray();

    Boolean requestSuccess = true;

    RequestQueue queue;

    String urlGetYear = "http://142.11.236.52:8080/dayData";

    public httpRequest(Context mainActivityContext)
    {
        queue = Volley.newRequestQueue( mainActivityContext );
    }


    public void run()
    {
        tryHTTPGetRequestForJSONArray(urlGetYear);
    }


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
                        requestSuccess = true;
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
                        Log.d("success array","REQUEST SUCCESS");
                        jsonArrayFromRequest =  response;
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

}
