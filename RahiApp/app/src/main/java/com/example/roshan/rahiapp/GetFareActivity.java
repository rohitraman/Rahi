package com.example.roshan.rahiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class GetFareActivity extends AppCompatActivity {

    EditText etGetFrom, etGetTo, etGetTrainNo;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_fare);
        etGetFrom = (EditText) findViewById(R.id.et_from_1);
        etGetTo = (EditText) findViewById(R.id.et_to_1);
        etGetTrainNo = (EditText) findViewById(R.id.et_train_no_1);
        button = (Button) findViewById(R.id.btn_get_fare);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {
                    object.put("from", etGetFrom.getText().toString().toUpperCase());
                    object.put("to",etGetTo.getText().toString().toUpperCase());
                    object.put("train_no",etGetTrainNo.getText().toString());
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105:8080/getFare", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Response",response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Error",error.getMessage());
                            NetworkResponse response = error.networkResponse;
                            if (error instanceof ServerError && response!=null)
                            {
                                try {
                                    String string = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                                    Log.i("Finally",string);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(GetFareActivity.this);
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
