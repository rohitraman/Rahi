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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


//Format of the response:
//{"train":{"coach_arrangement":[{"1":{"coach_id":"","tag":"","type":"En"}},{"2":{"coach_id":"","tag":"","type":"VP"}},{"3":{"coach_id":"","tag":"","type":"GS"}},{"4":{"coach_id":"","tag":"","type":"UR"}},{"5":{"coach_id":"","tag":"","type":"UR"}},{"6":{"coach_id":"A1","tag":"A","type":"2A"}},{"7":{"coach_id":"B1","tag":"B","type":"3A"}},{"8":{"coach_id":"B2","tag":"B","type":"3A"}},{"9":{"coach_id":"B3","tag":"B","type":"3A"}},{"10":{"coach_id":"B4","tag":"B","type":"3A"}},{"11":{"coach_id":"S1","tag":"S","type":"SL"}},{"12":{"coach_id":"S2","tag":"S","type":"SL"}},{"13":{"coach_id":"S3","tag":"S","type":"SL"}},{"14":{"coach_id":"S4","tag":"S","type":"SL"}},{"15":{"coach_id":"S5","tag":"S","type":"SL"}},{"16":{"coach_id":"S6","tag":"S","type":"SL"}},{"17":{"coach_id":"S7","tag":"S","type":"SL"}},{"18":{"coach_id":"S8","tag":"S","type":"SL"}},{"19":{"coach_id":"S9","tag":"S","type":"SL"}},{"20":{"coach_id":"S10","tag":"S","type":"SL"}},{"21":{"coach_id":"S11","tag":"S","type":"SL"}},{"22":{"coach_id":"","tag":"","type":"UR"}},{"23":{"coach_id":"","tag":"","type":"UR"}},{"24":{"coach_id":"","tag":"","type":"GS"}}],"coach_types":{"1A":"0","2A":"1","2S":"0","3A":"1","CC":"0","GN":"1","SL":"1"},"rake_share":["12601","22638"],"region":"SR","train_base":{"average_speed":"55","distance_from_to":"889","dstn_reach":"12.25","dstn_stn_code":"MAQ","dstn_stn_name":"Mangalore Central","from_stn_code":"MAS","from_stn_name":"Chennai Central","from_time":"20.20","notif_coach":"MAS-CLT-143 berth in SL class. ","running_days":"1111111","source_depart":"20.20","source_stn_code":"MAS","source_stn_name":"Chennai Central","to_stn_code":"MAQ","to_stn_name":"Mangalore Central","to_time":"12.25","train_id":"2206","train_name":"MANGALORE MAIL","train_no":"12601","travel_time":"16.05","type":"SUPERFAST"},"train_type":"Super Fast"}}

public class GetTrainActivity extends AppCompatActivity {
    EditText etTrain;
    Button btnGetTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_train);
        etTrain = (EditText) findViewById(R.id.et_train_no);
        btnGetTrain = (Button) findViewById(R.id.btn_trains);

        btnGetTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {
                    object.put("train_no",etTrain.getText().toString());
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105:8080/getTrain", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject object1 = response.getJSONObject("train");
                                JSONArray array = object1.getJSONArray("coach_arrangement");
                                String type = object1.getString("train_type");
                                JSONObject trainBase = object1.getJSONObject("train_base");

                                for (int i=0;i<array.length();i++)
                                {
                                    JSONObject object2 = array.getJSONObject(i);
                                    JSONObject coachNo = object2.getJSONObject(String.valueOf(i+1));
                                    String coach_id = coachNo.getString("coach_id");
                                    String coach_type = coachNo.getString("type");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Errrrooorrr",error.getMessage());
                            NetworkResponse response = error.networkResponse;
                            if (error instanceof ServerError && response!=null)
                            {
                                try {
                                    String string = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                                    Log.i("Yaay",string);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    RequestQueue queue = Volley.newRequestQueue(GetTrainActivity.this);
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
