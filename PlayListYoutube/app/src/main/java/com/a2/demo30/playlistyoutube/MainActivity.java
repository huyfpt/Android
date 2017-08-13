package com.a2.demo30.playlistyoutube;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ListView mylv;
    ArrayList<VideoYoutuBe> arraySanPham ;
    Listview_Adapter adapter = null;
    // Start create project in Google console
    //1.key YouTube Data API    ------- "enable"
    //2. Google+ API            ------- "enable"
    //3.Contacts API            ------- "enable"
    //Credentials key: AIzaSyBTT69fxdispEN2_Odnlby9YJxCmGhS0Es
    //Youtube's playlist : https://www.youtube.com/playlist?list=PLjlhS9oxJD1oL2q4xDKYRF4qsrTMwCgZB
    //------------------------------------------
    // get json youtube api v3  "http://stackoverflow.com/questions/22613903/youtube-api-v3-get-list-of-users-videos"
    //1.The channels#list method will return a JSON
    // with some information about the channel, including the playlist ID for the "uploads" playlist:
    //https://www.googleapis.com/youtube/v3/channels?part=contentDetails&forUsername=OneDirectionVEVO&key={YOUR_API_KEY}
    //2.With the playlist ID you can get the videos with the playlistItems#list method:
    //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="......................"&key={YOUR_API_KEY}
    //-----------------------------------------
    String Idkey = "AIzaSyBTT69fxdispEN2_Odnlby9YJxCmGhS0Es";
    String Yplaylist = "PLjlhS9oxJD1oL2q4xDKYRF4qsrTMwCgZB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetJsonYoutube();
        arraySanPham = new ArrayList<>();
        mylv = (ListView) findViewById(R.id.listView);

    }

    private void GetJsonYoutube() {
         RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + Yplaylist + "&key=" + Idkey+"&maxResults=50";
         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            JSONArray  arrayItems = response.getJSONArray("items");
                            for (int i = 0; i < response.length(); i++) {
                                String title ="",  urlM ="" , videoId="";
                                JSONObject objectItems = arrayItems.getJSONObject(i);
                                JSONObject objectSippet = objectItems.getJSONObject("snippet");
                                 title = objectSippet.getString("title");
                                String changelTitle = objectSippet.getString("channelTitle");
                                JSONObject objectThumbnails = objectSippet.getJSONObject("thumbnails");
                                JSONObject objectDefaul = objectThumbnails.getJSONObject("default");
                                String urlD = objectDefaul.getString("url");
                                JSONObject objectMedium = objectThumbnails.getJSONObject("medium");
                                 urlM = objectMedium.getString("url");
                                JSONObject objectResoure = objectSippet.getJSONObject("resourceId");
                                //String kind = objectResoure.getString("kind");
                                 videoId = objectResoure.getString("videoId");
                                //Toast.makeText(MainActivity.this, title +"========"+ videoID , Toast.LENGTH_LONG).show();
                                arraySanPham.add(new VideoYoutuBe(title, urlM,videoId));
                            }
                            adapter = new Listview_Adapter(MainActivity.this, R.layout.viewlayoutcustomer, arraySanPham);
                            mylv.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // adapter.notifyDataSetChanged();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
