package com.a2.demo18.time_dialog_demo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView txtTime;
    EditText edt1, edt2;
    Button btnCal;
    int day, month, year;
    Calendar calendar, calendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTime = (TextView) findViewById(R.id.textViewShowTime);
        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);
        btnCal = (Button) findViewById(R.id.button);
//        txtTime.setText("" + calendar.getTime());
//        txtTime.setText(calendar.get(Calendar.DATE) + "");
//        txtTime.setText("" + calendar.get(Calendar.MONTH) + "");// tháng luôn bị -1
//        SimpleDateFormat HourFormat = new SimpleDateFormat("hh:mm:ss a");//a = AM or PM
//        txtTime.setText(HourFormat.format(calendar.getTime())); //định dang gio
        month = month + 1;
        edt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowDateDialog();
            }
        });
        edt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDateDialog2();
            }
        });
btnCal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        calendar.getTimeInMillis();
        calendar2.getTimeInMillis();
    }
});
    }

    private void ShowDateDialog() {
        calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth, 0, 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//  txtTime.setText(simpleDateFormat.format(calendar.getTime())); //định dang ngày-tháng-năm
                //set calemdar = time user choose
                edt1.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, mDay, mMonth, mYear);
        datePickerDialog.show();
    }

    private void ShowDateDialog2() {
        int mYear = calendar2.get(Calendar.YEAR);
        int mMonth = calendar2.get(Calendar.MONTH);
        int mDay = calendar2.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar2 = Calendar.getInstance();
                calendar2.set(year, month, dayOfMonth, 0, 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //set calemdar = time user choose
                edt2.setText(simpleDateFormat.format(calendar2.getTime()));
            }
        }, mDay, mMonth, mYear);
        datePickerDialog.show();
    }

}
