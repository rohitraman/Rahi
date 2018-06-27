package com.example.roshan.rahiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class GetPNRActivity extends AppCompatActivity {
    EditText etPNR;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pnr);
        etPNR = (EditText) findViewById(R.id.editText6);
        button = (Button) findViewById(R.id.btn_pnr);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pnr = etPNR.getText().toString();
                JSONObject object = new JSONObject();
                try {
                    object.put("pnr",pnr);
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105/getPNR", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Response",response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
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
                    RequestQueue queue = Volley.newRequestQueue(GetPNRActivity.this);
                    queue.add(request);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
