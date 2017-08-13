package com.demo25.json_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnobject, btnArray, btnVn, btnEn, btnobob, btnPHP, btnVphp;
    TextView txtobject;
    ListView mylv;
    ArrayList<cus> cust;
    Listview_customer adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        cust = new ArrayList<>();
        adapter = new Listview_customer(
                MainActivity.this,
                R.layout.activity_listview_customer,
                cust
        );
        mylv.setAdapter(adapter);
        btnobject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new read_JSON().execute("http://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
                    }
                });
            }
        });
        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Read_json_aray().execute("http://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
                    }
                });
            }
        });
        btnVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Read_Object2().execute("http://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
                    }
                });
            }
        });
        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Read_JSon_En().execute("http://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
                    }
                });
            }
        });
        btnobob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new demo4().execute("http://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
                    }
                });
            }
        });
        btnPHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new demoPHP().execute("http://192.168.161.2//android/demo2.php");
                    }
                });
            }
        });
        btnVphp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoleyProductDemo();
            }
        });
    }

    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    //json ọbject
    private class read_JSON extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject object = null;
            try {
                object = new JSONObject(s);
                String nameSupject = object.getString("monhoc");
                String where = object.getString("noihoc");
                String webpage = object.getString("website");
                String fanpage = object.getString("fanpage");
                String logo = object.getString("logo");
                Toast.makeText(MainActivity.this, nameSupject + "\n" + where + "\n" + webpage + "\n" + fanpage + "\n" + logo, Toast.LENGTH_LONG).show();
                txtobject.setText(nameSupject + "\n" + where + "\n" + webpage + "\n" + fanpage + "\n" + logo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class Read_json_aray extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
//                {--object
//                      "danhsach": [--array
//                    {"khoahoc"  :  "Lap trinh iOS co ban"   },--object
//                    {"khoahoc"  :  "Lap trinh iOS nang cao" },--object
//                    {"khoahoc"  :  "Lap trinh PHP&MySQL"    },--object
//                    {"khoahoc"  :  "Lap trinh Phonegap"     },--object
//                    {"khoahoc"  :  "Lap trinh Cocos2dx"     },--object
//                    {"khoahoc"  :  "Lap trinh Android"      }--object
//                    ]-array
//                  }--object
                JSONObject object = new JSONObject(s);
                JSONArray arrayDS = object.getJSONArray("danhsach");//đọc bắt đầu từ object còn name "danhsach"
                for (int i = 0; i < arrayDS.length(); i++) {
                    JSONObject objectDS = arrayDS.getJSONObject(i);//đọc từ array lấy từng phần tử object--truyền vào vì mảng chạy từ 0 mà i=0
                    String couse = objectDS.getString("khoahoc");
                    Toast.makeText(MainActivity.this, couse, Toast.LENGTH_LONG).show();
                    txtobject.setText(couse);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class Read_Object2 extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONObject objectLG = object.getJSONObject("language");
                JSONObject objectVn = objectLG.getJSONObject("vn");
                String name = objectVn.getString("name");
                String adress = objectVn.getString("address");
                String course1 = objectVn.getString("course1");
                String course2 = objectVn.getString("course2");
                String course3 = objectVn.getString("course3");
                txtobject.setText(name + "\n" + adress + "\n" + course1 + "\n" + course2 + "\n" + course3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class Read_JSon_En extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONObject objectLG = object.getJSONObject("language");
                JSONObject objectEn = objectLG.getJSONObject("en");
                String name = objectEn.getString("name");
                String adress = objectEn.getString("address");
                String course1 = objectEn.getString("course1");
                String course2 = objectEn.getString("course2");
                String course3 = objectEn.getString("course3");
                txtobject.setText(name + "\n" + adress + "\n" + course1 + "\n" + course2 + "\n" + course3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class demo4 extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    String kh = object1.getString("khoahoc");
                    String hp = object1.getString("hocphi");
                    cust.add(new cus(kh, hp));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class demoPHP extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String dob = jsonObject.getString("DayOfBirth");
                    Toast.makeText(MainActivity.this, name + "\n" + dob, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void VoleyProductDemo() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.161.2/android/productDemo.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String id = object.getString("id");
                                String name = object.getString("name");
                                String price = object.getString("cost");
                                String des = object.getString("describe");
                                cust.add(new cus(id, name, price, des));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(arrayRequest);
    }

    public void anhxa() {
        btnobject = (Button) findViewById(R.id.buttonJObject);
        btnArray = (Button) findViewById(R.id.buttonJArray);
        btnVn = (Button) findViewById(R.id.buttonVn);
        btnEn = (Button) findViewById(R.id.buttonEn);
        btnobob = (Button) findViewById(R.id.buttonobob);
        btnPHP = (Button) findViewById(R.id.buttonphp);
        btnVphp = (Button) findViewById(R.id.buttonVphp);
        txtobject = (TextView) findViewById(R.id.textViewObject);
        mylv = (ListView) findViewById(R.id.listView);

    }
}
