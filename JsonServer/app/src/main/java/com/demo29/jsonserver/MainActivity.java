package com.demo29.jsonserver;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView mylv;
    Button btnThem;
    ArrayList<SanPham> arraySanPham;
    SanPhamAdapter adapter = null;
    int rage = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        // sign in ContextMenu
        registerForContextMenu(mylv);
        adapter = new SanPhamAdapter(this, R.layout.product_cus_view, arraySanPham);
        mylv.setAdapter(adapter);
        mylv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rage = position;
                //PopupMenuEditDelete();
                return false;
            }
        });
        GetData("http://hosttestbest.pe.hu/productDemo.php");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Insert_items.class);
                startActivity(intent);

            }
        });


    }

//    private void PopupMenuEditDelete() {
//        PopupMenu popupMenu = new PopupMenu(this, mylv);
//        popupMenu.getMenuInflater().inflate(R.menu.edit_delect, popupMenu.getMenu());
//        popupMenu.show();
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.edit_delect, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEdit:
                // Toast.makeText(MainActivity.this, "Update Successfully"+ arraySanPham.get(rage).Ten, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Update_item.class);
                intent.putExtra("Uname", arraySanPham.get(rage));
                startActivity(intent);

                break;
            case R.id.menuDelete:
//                Toast.makeText(MainActivity.this, "Deleted" + arraySanPham.get(rage).Ten, Toast.LENGTH_LONG).show();
                Confirm();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void AnhXa() {
        btnThem = (Button) findViewById(R.id.buttonThemSanPham);
        mylv = (ListView) findViewById(R.id.listviewSanPham);
        arraySanPham = new ArrayList<>();
    }

    private void GetData(String urls) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, urls, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null || response.toString() != "") {
                            arraySanPham.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                    arraySanPham.add(new SanPham(
                                            object.getInt("idpd"),
                                            object.getString("name"),
                                            object.getInt("cost"),
                                            object.getString("details")
                                    ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonReq);
    }

    private void deleteItem() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://hosttestbest.pe.hu/delete.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("211")) {
                            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            GetData("http://hosttestbest.pe.hu/productDemo.php");//refresh page index "productDemo.php"
                            Log.d("AAAA", response);
                        } else {
                            Toast.makeText(MainActivity.this, "Delete error!", Toast.LENGTH_SHORT).show();
                            Log.d("AAAA", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "App failed!!! please check it agian...", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                String idItem = String.valueOf(arraySanPham.get(rage).ID);
                map.put("ProductId",idItem);
                return map;
            }
        };
        queue.add(stringRequest);
    }

    private void Confirm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Inotification");
        dialog.setMessage("Must you destroy it" + arraySanPham.get(rage).Ten);
        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }
}
