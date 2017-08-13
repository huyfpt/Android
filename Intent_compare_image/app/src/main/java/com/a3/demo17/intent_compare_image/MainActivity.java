package com.a3.demo17.intent_compare_image;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// qua AndroidMainifest chỉnh dialod Intent của page 2
// <activity android:name=".Choose_Image"
//android:theme="@style/Base.V21.Theme.AppCompat.Dialog">
//  </activity>
public class MainActivity extends AppCompatActivity {
    ImageView imgView1, imgView2;
    TextView txtmath;
    public static ArrayList<String> arrayList; // public static cho các màn hình khác gọi
    int Request_Code = 123;
    int idImage;
    int score ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView1 = (ImageView) findViewById(R.id.imageView);
        imgView2 = (ImageView) findViewById(R.id.imageView2);
        txtmath = (TextView) findViewById(R.id.txtScore);
        //khai baos Reference---------------------
        sharedPreferences = getSharedPreferences("MyScore", MODE_PRIVATE);
        score = sharedPreferences.getInt("score1", 0);
        if (score == 0) {
            score = 100;
        }
        txtmath.setText("" + score);
        //----------------------------------------

        String arrayName[] = getResources().getStringArray(R.array.ImageList);
        arrayList = new ArrayList<>(Arrays.asList(arrayName));
        Collections.shuffle(arrayList);// trộn get("phần tử") trong arayList
        idImage = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());
        imgView1.setImageResource(idImage);
        // click chọn image
        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkScore() == true) {
                    Intent intent = new Intent(MainActivity.this, Choose_Image.class);
                    startActivityForResult(intent, Request_Code); //int Request_Code =123;
                } else {

                }
            }
        });
    }


    // gọi hàm trong file XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reload, menu);// gọi file xml menu
        return super.onCreateOptionsMenu(menu);
    }

    //gọi hàm trong file xml (bắt sự kiện)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuOption:
                Toast.makeText(this, "Choose Option", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuShare:
                Toast.makeText(this, "Choose Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuRefesh:
                Collections.shuffle(arrayList);
                idImage = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());
                imgView1.setImageResource(idImage);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //trả kết quả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Request_Code && resultCode == RESULT_OK && data != null) {
            String ImageName = data.getStringExtra("ChooseImage");
            int idImageChoose = getResources().getIdentifier(ImageName, "drawable", getPackageName());
            imgView2.setImageResource(idImageChoose);
            // kiem tra 2 hinh
            if (idImageChoose == idImage) {
                Toast.makeText(this, "Correct and next image", Toast.LENGTH_SHORT).show();
                score += 10;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("score1", score);
                editor.commit();
                //khi đúng và đả tính điểm thỳ hinh sẻ tự động load hình mới
                Collections.shuffle(arrayList);
                idImage = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());
                imgView1.setImageResource(idImage);
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                score -= 5;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("score1", score);
                editor.commit();

            }
            txtmath.setText("" + score);
        }
        //bắt chơi ăn gian
        if (requestCode == Request_Code && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You choose image yet", Toast.LENGTH_SHORT).show();
            score -= 30;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("score1", score);
            editor.commit();
        }
        txtmath.setText("" + score);
        super.onActivityResult(requestCode, resultCode, data);
    }

    // kiểm tra thua < 0
    private boolean checkScore() {
        if (score <= 0) {
            confirmDialog();
            return false;
        } else {
            return true;
        }
    }

    //confirm dialog
    private void confirmDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Notification");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("thua thì cút chơi ngu như chó");
        //nut 1
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                score = 100;
                txtmath.setText("" + score);
            }
        });
        //nut 2
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.show();
    }
}

