package com.demo29.jsonserver;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Insert_items extends AppCompatActivity {
    Button btnThem, btnHuy;
    EditText edtTen, edtGia, edtMoTa;
    String linkInsert = "http://192.168.149.2/android/Insertdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_items);
        AnhXa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = edtTen.getText().toString().trim();
                String giasp = edtGia.getText().toString().trim();
                String mota = edtMoTa.getText().toString().trim();
                if (tensp.isEmpty() || giasp.isEmpty() || mota.isEmpty()) {
                    Toast.makeText(Insert_items.this, "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    //1. Insert by AcsynTask
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new ThemSanPhamAsync().execute(linkInsert);
//                        }
//                    });
                    //2. Insert by Volley
                    InsertByVolley();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InsertByVolley() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://hosttestbest.pe.hu/Insertdata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            Toast.makeText(Insert_items.this, "Insert success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Insert_items.this, MainActivity.class));
                            Log.d("AAA", response);
                        } else {
                            Toast.makeText(Insert_items.this, "Insert error!", Toast.LENGTH_SHORT).show();
                            Log.d("AAA", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Insert_items.this, "App failed!!! please check it agian...", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // map <key = String, data type "value"= String>
                Map<String, String> map = new HashMap<>();
                map.put("ProductName", edtTen.getText().toString());
                map.put("ProductPrice", edtGia.getText().toString());
                map.put("Details", edtMoTa.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private class ThemSanPhamAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return postData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("1")) {// equals("1") because in Insertdata.php
                // if(mysqli_query($connection, $cautruyvan)){ echo 1}
                Toast.makeText(Insert_items.this, "Insert success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Insert_items.this, MainActivity.class));
            } else {
                Toast.makeText(Insert_items.this, "Insert error!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String postData(String link) {
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection
            connect = (HttpURLConnection) url.openConnection();
            connect.setReadTimeout(10000);
            connect.setConnectTimeout(15000);
            connect.setRequestMethod("POST");

            // Gán tham số vào URL
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("ProductName", edtTen.getText().toString())
                    .appendQueryParameter("ProductPrice", edtGia.getText().toString())
                    .appendQueryParameter("Details", edtMoTa.getText().toString());
            String query = builder.build().getEncodedQuery();

            // Mở kết nối gửi dữ liệu
            OutputStream os = connect.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connect.connect();

        } catch (IOException e1) {
            e1.printStackTrace();
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStream input = connect.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return "Error!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }

    private void AnhXa() {
        btnHuy = (Button) findViewById(R.id.buttonHuy);
        btnThem = (Button) findViewById(R.id.buttonThem);
        edtGia = (EditText) findViewById(R.id.editTextGiaSP);
        edtMoTa = (EditText) findViewById(R.id.editTextMoTa);
        edtTen = (EditText) findViewById(R.id.editTextTenSP);
    }
}