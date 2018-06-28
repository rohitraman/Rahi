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

//Format of response
//['route':{'depart': '20.20', 'zone': 'SR', 'platform': '11', 'lat': '13.0825454795418', 'lng': '80.2754473686218', 'halt': '0', 'arrival': 'First', 'day': '1', 'division': 'MAS', 'index': '1', 'code': 'MAS', 'distance_from_source': '0', 'name': 'Chennai Central'}, {'depart': '20.50', 'zone': 'SR', 'platform': '2', 'lat': '13.1160523081398', 'lng': '79.9129199981689', 'halt': '1', 'arrival': '20.49', 'day': '1', 'division': 'MAS', 'index': '2', 'code': 'TRL', 'distance_from_source': '42', 'name': 'Tiruvallur'}, {'depart': '21.10', 'zone': 'SR', 'platform': '1', 'lat': '13.0818505290382', 'lng': '79.6680799126625', 'halt': '2', 'arrival': '21.08', 'day': '1', 'division': 'MAS', 'index': '3', 'code': 'AJJ', 'distance_from_source': '69', 'name': 'Arakkonam'}, {'depart': '21.40', 'zone': 'SR', 'platform': '2', 'lat': '12.9708946633531', 'lng': '79.3605807423592', 'halt': '1', 'arrival': '21.39', 'day': '1', 'division': 'MAS', 'index': '4', 'code': 'WJR', 'distance_from_source': '105', 'name': 'Walajah Road'}, {'depart': '22.00', 'zone': 'SR', 'platform': '1', 'lat': '12.9719610793637', 'lng': '79.1384696960449', 'halt': '2', 'arrival': '21.58', 'day': '1', 'division': 'MAS', 'index': '5', 'code': 'KPD', 'distance_from_source': '130', 'name': 'Katpadi Jn'}, {'depart': '23.25', 'zone': 'SR', 'platform': '1', 'lat': '12.562846987401', 'lng': '78.5773864388466', 'halt': '2', 'arrival': '23.23', 'day': '1', 'division': 'MAS', 'index': '6', 'code': 'JTJ', 'distance_from_source': '214', 'name': 'Jolarpettai'}, {'depart': '00.55', 'zone': 'SR', 'platform': '2', 'lat': '11.6702880993225', 'lng': '78.1134045124054', 'halt': '3', 'arrival': '00.52', 'day': '2', 'division': 'SA', 'index': '7', 'code': 'SA', 'distance_from_source': '334', 'name': 'Salem Jn'}, {'depart': '01.55', 'zone': 'SR', 'platform': '2', 'lat': '11.3278223330679', 'lng': '77.7263617515564', 'halt': '3', 'arrival': '01.52', 'day': '2', 'division': 'SA', 'index': '8', 'code': 'ED', 'distance_from_source': '396', 'name': 'Erode Jn'}, {'depart': '02.40', 'zone': 'SR', 'platform': '1', 'lat': '11.1087002941292', 'lng': '77.3397427797318', 'halt': '2', 'arrival': '02.38', 'day': '2', 'division': 'SA', 'index': '9', 'code': 'TUP', 'distance_from_source': '446', 'name': 'Tiruppur'}, {'depart': '03.30', 'zone': 'SR', 'platform': '3', 'lat': '10.9649024568577', 'lng': '76.9895750284195', 'halt': '2', 'arrival': '03.28', 'day': '2', 'division': 'SA', 'index': '10', 'code': 'PTJ', 'distance_from_source': '490', 'name': 'Podanur Jn'}, {'depart': '04.35', 'zone': 'SR', 'platform': '1,2', 'lat': '10.7996469084651', 'lng': '76.6374170780182', 'halt': '5', 'arrival': '04.30', 'day': '2', 'division': 'PGT', 'index': '11', 'code': 'PGT', 'distance_from_source': '538', 'name': 'Palakkad'}, {'depart': '05.00', 'zone': 'SR', 'platform': '2', 'lat': '10.7696414155378', 'lng': '76.3778007030487', 'halt': '2', 'arrival': '04.58', 'day': '2', 'division': 'PGT', 'index': '12', 'code': 'OTP', 'distance_from_source': '570', 'name': 'Ottappalam'}, {'depart': '05.40', 'zone': 'SR', 'platform': '6', 'lat': '10.75923839156', 'lng': '76.2715530395508', 'halt': '10', 'arrival': '05.30', 'day': '2', 'division': 'PGT', 'index': '13', 'code': 'SRR', 'distance_from_source': '583', 'name': 'Shoranur Jn'}, {'depart': '06.00', 'zone': 'SR', 'platform': '1', 'lat': '10.8021129801769', 'lng': '76.180824637413', 'halt': '2', 'arrival': '05.58', 'day': '2', 'division': 'PGT', 'index': '14', 'code': 'PTB', 'distance_from_source': '594', 'name': 'Pattambi'}, {'depart': '06.25', 'zone': 'SR', 'platform': '1', 'lat': '10.845866573195', 'lng': '76.0334587097168', 'halt': '2', 'arrival': '06.23', 'day': '2', 'division': 'PGT', 'index': '15', 'code': 'KTU', 'distance_from_source': '613', 'name': 'Kuttippuram'}, {'depart': '06.50', 'zone': 'SR', 'platform': '1', 'lat': '10.9174160251273', 'lng': '75.9222972393036', 'halt': '2', 'arrival': '06.48', 'day': '2', 'division': 'PGT', 'index': '16', 'code': 'TIR', 'distance_from_source': '628', 'name': 'Tirur'}, {'depart': '07.00', 'zone': 'SR', 'platform': '1', 'lat': '10.9783237881336', 'lng': '75.8815464377403', 'halt': '2', 'arrival': '06.58', 'day': '2', 'division': 'PGT', 'index': '17', 'code': 'TA', 'distance_from_source': '635', 'name': 'Tanur'}, {'depart': '07.10', 'zone': 'SR', 'platform': '1', 'lat': '11.0471587424533', 'lng': '75.8602952957153', 'halt': '1', 'arrival': '07.09', 'day': '2', 'division': 'PGT', 'index': '18', 'code': 'PGI', 'distance_from_source': '643', 'name': 'Parpanangadi'}, {'depart': '07.35', 'zone': 'SR', 'platform': '2', 'lat': '11.1750127099322', 'lng': '75.8301821351051', 'halt': '1', 'arrival': '07.34', 'day': '2', 'division': 'PGT', 'index': '19', 'code': 'FK', 'distance_from_source': '659', 'name': 'Ferok'}, {'depart': '08.00', 'zone': 'SR', 'platform': '4', 'lat': '11.2464241009831', 'lng': '75.7809555530548', 'halt': '5', 'arrival': '07.55', 'day': '2', 'division': 'PGT', 'index': '20', 'code': 'CLT', 'distance_from_source': '668', 'name': 'Kozhikkode (Calicut)'}, {'depart': '08.25', 'zone': 'SR', 'platform': '1', 'lat': '11.4460407204908', 'lng': '75.693826675415', 'halt': '1', 'arrival': '08.24', 'day': '2', 'division': 'PGT', 'index': '21', 'code': 'QLD', 'distance_from_source': '693', 'name': 'Quilandi'}, {'depart': '08.40', 'zone': 'SR', 'platform': '2,3', 'lat': '11.593365868954', 'lng': '75.5870908498764', 'halt': '2', 'arrival': '08.38', 'day': '2', 'division': 'PGT', 'index': '22', 'code': 'BDJ', 'distance_from_source': '715', 'name': 'Vadakara (Badagara)'}, {'depart': '08.50', 'zone': 'SR', 'platform': '1', 'lat': '11.699002373566', 'lng': '75.5468067526817', 'halt': '1', 'arrival': '08.49', 'day': '2', 'division': 'PGT', 'index': '23', 'code': 'MAHE', 'distance_from_source': '728', 'name': 'Mahe'}, {'depart': '09.05', 'zone': 'SR', 'platform': '1', 'lat': '11.7534283848375', 'lng': '75.4929828643799', 'halt': '2', 'arrival': '09.03', 'day': '2', 'division': 'PGT', 'index': '24', 'code': 'TLY', 'distance_from_source': '737', 'name': 'Thalassery (Tellicherry)'}, {'depart': '09.35', 'zone': 'SR', 'platform': '1', 'lat': '11.8715034405642', 'lng': '75.3683459758759', 'halt': '5', 'arrival': '09.30', 'day': '2', 'division': 'PGT', 'index': '25', 'code': 'CAN', 'distance_from_source': '758', 'name': 'Kannur (Cannanore)'}, {'depart': '09.55', 'zone': 'SR', 'platform': '1', 'lat': '12.0218336509432', 'lng': '75.2601778507233', 'halt': '2', 'arrival': '09.53', 'day': '2', 'division': 'PGT', 'index': '26', 'code': 'PAZ', 'distance_from_source': '779', 'name': 'Payangadi'}, {'depart': '10.05', 'zone': 'SR', 'platform': '1', 'lat': '12.0914020183883', 'lng': '75.1956117153168', 'halt': '2', 'arrival': '10.03', 'day': '2', 'division': 'PGT', 'index': '27', 'code': 'PAY', 'distance_from_source': '791', 'name': 'Payyanur'}, {'depart': '10.20', 'zone': 'SR', 'platform': '2', 'lat': '12.2145042618864', 'lng': '75.1546651124954', 'halt': '2', 'arrival': '10.18', 'day': '2', 'division': 'PGT', 'index': '28', 'code': 'CHV', 'distance_from_source': '806', 'name': 'Charvattur'}, {'depart': '10.40', 'zone': 'SR', 'platform': '', 'lat': '12.3199667064408', 'lng': '75.0848150253296', 'halt': '2', 'arrival': '10.38', 'day': '2', 'division': 'PGT', 'index': '29', 'code': 'KZE', 'distance_from_source': '820', 'name': 'Kanhangad'}, {'depart': '11.00', 'zone': 'SR', 'platform': '2', 'lat': '12.4914012534406', 'lng': '74.9876037240028', 'halt': '2', 'arrival': '10.58', 'day': '2', 'division': 'PGT', 'index': '30', 'code': 'KGQ', 'distance_from_source': '843', 'name': 'Kasaragod'}, {'depart': 'Last', 'zone': 'SR', 'platform': '2,3', 'lat': '12.8633252805552', 'lng': '74.8434323072433', 'halt': '0', 'arrival': '12.25', 'day': '2', 'division': 'PGT', 'index': '31', 'code': 'MAQ', 'distance_from_source': '889', 'name': 'Mangalore Central'}]


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
