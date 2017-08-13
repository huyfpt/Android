package com.demo26.request_volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnVolley, btnArray, btnImag;
    TextView txtText;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://developer.android.com/training/volley/simple.html
        anhxa();
        btnVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volley_Json();
            }
        });
        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volley_JsArray();
            }
        });
        btnImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volley_Image();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void Volley_Json() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String subject = response.getString("monhoc");
                            txtText.setText(subject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtText.setText("" + error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void Volley_JsArray() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object = null;
                            try {
                                object = response.getJSONObject(i);
                                String name = object.getString("khoahoc");
                                String cost = object.getString("hocphi");
                                txtText.setText(name + "\n" + cost);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtText.setText("" + error);
            }
        });
        queue.add(jsonArrayRequest);
    }

    public void Volley_Image() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://sinhthanh.xtgem.com/download/hinh-anh/hot-girl/sinhthanh.xtgem.com-hinh-girl-xinh-113.jpg";
        final ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imgView.setImageBitmap(response);
                    }
                },
                0, 0, null, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    imgView.setImageResource(R.mipmap.ic_launcher);
                        txtText.setText("Eror image link");
                    }
                });
        queue.add(imageRequest);
    }

    public void anhxa() {
        btnVolley = (Button) findViewById(R.id.buttonVolleyJs);
        btnArray = (Button) findViewById(R.id.buttonAr);
        btnImag = (Button) findViewById(R.id.buttonImg);
        imgView = (ImageView) findViewById(R.id.imageView);
        txtText = (TextView) findViewById(R.id.textViewText);
    }
}
