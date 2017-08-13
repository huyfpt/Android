package com.a3.demo17.intent_compare_image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class Choose_Image extends AppCompatActivity {
TableLayout tlbLayout;
    int range = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__image);
        tlbLayout = (TableLayout)findViewById(R.id.MyTableImage);
        int numColum = 3;
        int numRow  = 6;


        Collections.shuffle(MainActivity.arrayList);
        for(int i=1; i <=numRow; i++){
            TableRow tableRow = new TableRow(this);
            for (int j = 1; j <=numColum; j++){
                final ImageView imageView = new ImageView(this);
                final int range = numColum*(i-1)+(j-1); // range++ arrayList tự tăng
                final int idImage = getResources().getIdentifier(
                        MainActivity.arrayList.get(range),
                        "drawable",
                        getPackageName()
                );//range -1 vì int j bắt đầu từ 1
                imageView.setImageResource(idImage);
                // chỉnh lại form ảnh
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(240,240); // height =240 , with =240
                imageView.setLayoutParams(layoutParams);
                //add hình vào
                tableRow.addView(imageView);//hình nằm trong dòng
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("ChooseImage", MainActivity.arrayList.get(range));
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                });
            }
            tlbLayout.addView(tableRow);//dòng add vào bảng
        }

    }
}
