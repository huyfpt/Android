package com.demo23.async_task_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSove;
    TextView txtSolve;
    ProgressBar bgSolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSove = (Button) findViewById(R.id.buttonSolve);
        txtSolve = (TextView) findViewById(R.id.textViewSolve);
        bgSolve = (ProgressBar)findViewById(R.id.progressBarSolve);
        bgSolve.setVisibility(View.INVISIBLE);// ẩn-> nhấn vào hiện lại
        btnSove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new xulycongviec().execute();
                    }
                });
            }
        });
    }

    private class xulycongviec extends AsyncTask<Void, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtSolve.setText("onPreExecute....Start");
            bgSolve.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {//(...) is array
            for (int i = 1; i <= 5; i++) {
                publishProgress("xong viec" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Complete...";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtSolve.append("\n " + s);// xuất hiện thêm k xóa cái củ (setText)
            bgSolve.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtSolve.append("\n " + values[0]);
        }
    }
}
