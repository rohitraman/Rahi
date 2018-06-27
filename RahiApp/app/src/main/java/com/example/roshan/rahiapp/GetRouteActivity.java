package com.example.roshan.rahiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class GetRouteActivity extends AppCompatActivity {

    EditText etTrainNo;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_route);
        etTrainNo = (EditText) findViewById(R.id.et_trainNo);
        button = (Button) findViewById(R.id.btn_route);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {
                    Log.i("Blll","Bliiii");
                    object.put("train_no",etTrainNo.getText().toString());
                    Log.i("Yaay",object.toString());
                    final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105:8080/getRoute", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.i("array",response.toString());
                                JSONArray array = response.getJSONArray("route");
                                for (int i = 0; i< array.length(); i++)
                                {
                                    JSONObject object2 = array.getJSONObject(i);
                                    Log.i("hELLO",object2.toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("jaaaa",error.getLocalizedMessage());
                            NetworkResponse response = error.networkResponse;
                            if(error instanceof ServerError && response!=null)
                            {
                                String response1 = null;
                                try {
                                    response1 = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                Log.i("Erroorr",response1);
                            }
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, DefaultRetryPolicy.DEFAULT_MAX_RETRIES*48, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue queue = Volley.newRequestQueue(GetRouteActivity.this);
                    queue.add(request);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Blah","Blah");
                }

            }
        });
    }
}
