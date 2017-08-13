package com.demo29.jsonserver;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Update_item extends AppCompatActivity {
    EditText edtname, edtprice, edtdetails;
    int idItem;
    Button btnsave, btncancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        edtname = (EditText) findViewById(R.id.editTextUname);
        edtprice = (EditText) findViewById(R.id.editTextUprice);
        edtdetails = (EditText) findViewById(R.id.editTextUdetails);
        btnsave = (Button) findViewById(R.id.buttonUpdate);
        btncancle = (Button) findViewById(R.id.buttonCancle);
        Intent myItent = getIntent();
        SanPham sanPham = (SanPham) myItent.getSerializableExtra("Uname");
        idItem = sanPham.ID;
        edtname.setText(sanPham.Ten);
        edtprice.setText(sanPham.Gia + "");
        edtdetails.setText(sanPham.MoTa);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edtname.getText().toString().trim();
                String giasp = edtprice.getText().toString().trim();
                String mota = edtdetails.getText().toString().trim();
                if (tensp.isEmpty() || giasp.isEmpty() || mota.isEmpty()) {
                    Toast.makeText(Update_item.this, "The information is valid!", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateByVolly();
                }
            }
        });
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void UpdateByVolly() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://hosttestbest.pe.hu/Update.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("222")) {
                            Toast.makeText(Update_item.this, "Update success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Update_item.this, MainActivity.class));
                            Log.d("AAAA", response);
                        } else {
                            Toast.makeText(Update_item.this, "Update error!", Toast.LENGTH_SHORT).show();
                            Log.d("AAAA", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Update_item.this, "App failed!!! please check it agian...", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // map <key = String, data type "value"= String>
                Map<String, String> map = new HashMap<>();
                map.put("ProductId", String.valueOf(idItem));
                map.put("ProductName", edtname.getText().toString());
                map.put("ProductPrice", edtprice.getText().toString());
                map.put("Details", edtdetails.getText().toString());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

}
