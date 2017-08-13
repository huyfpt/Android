package com.a3.demo23.read_rss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMyView;
    ArrayList<String> reader = new ArrayList<>();
    ArrayList<String> arrlink;
    ArrayAdapter<String> adapter= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMyView = (ListView) findViewById(R.id.listViewMyview);
        arrlink = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reader);
        lvMyView.setAdapter(adapter);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadXML().execute("http://vnexpress.net/rss/kinh-doanh.rss");
            }
        });
        lvMyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrlink.size()==0){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, NewReaderActivity.class);
                    intent.putExtra("link", arrlink.get(position));
                    startActivity(intent);
                }
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

    private class Read_Rss extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //  Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            //Analysis RSS
        }
    }

    private class ReadXML extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String titles = "";
            String links = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                titles += parser.getValue(element, "title") + "\n";
                links += parser.getValue(element, "link");
                reader.add(titles);
                arrlink.add(links);
            }
            adapter.notifyDataSetChanged();
            //Toast.makeText(MainActivity.this, "tin" + nodeList.getLength(), Toast.LENGTH_LONG).show();
        }

    }
}
