package com.tienminh.dmt.youtubeapiplaylist2609;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
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

    ListView lvVideo;
    ArrayList<VideoYouTube> arrayVideo;
    VideoAdapter adapter;

    String API_KEY = "AIzaSyDXjC5B-g-HphZpwaf-uW0ePH03SlJId7Y";
    String ID_PLAYLIST = "PL9MGyIJfsP2PDuU9NkVDh8_t_frFS3gar";
    String linkGetVideo = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_PLAYLIST +"&key=" + API_KEY + "&maxResults=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AAA", linkGetVideo);
        lvVideo = (ListView) findViewById(R.id.listviewVideo);
        arrayVideo = new ArrayList<>();
        GetJsonYouTube(linkGetVideo);
//        adapter = new VideoAdapter(this, R.layout.row_video, arrayVideo);
//        lvVideo.setAdapter(adapter);
        Toast.makeText(this, removeAccent("Sinh Viên Công Nghệ Thông Tin"), Toast.LENGTH_SHORT).show();


    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
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

    private void GetJsonYouTube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = "", url = "", videoID = "";
                            for (int i = 0; i < jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");
                                JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonThumbMedium = jsonThumbnails.getJSONObject("medium");
                                url = jsonThumbMedium.getString("url");
                                JSONObject jsonResourseId = jsonSnippet.getJSONObject("resourceId");
                                videoID = jsonResourseId.getString("videoId");
                                arrayVideo.add(new VideoYouTube(title, url, videoID));
                            }
//                            adapter.notifyDataSetChanged();
                            adapter = new VideoAdapter(MainActivity.this, R.layout.row_video, arrayVideo);
                            lvVideo.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
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
        requestQueue.add(objectRequest);
    }
}
