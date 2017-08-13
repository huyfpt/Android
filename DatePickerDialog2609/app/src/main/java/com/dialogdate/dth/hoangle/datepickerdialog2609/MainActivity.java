package com.dialogdate.dth.hoangle.datepickerdialog2609;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtDate, edtDate2;
    Button btnTinh;
    TextView tvKetQua;
    Calendar myCalendar, myCalendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDate = (EditText)findViewById(R.id.editTextDate);
        edtDate2 = (EditText)findViewById(R.id.editTextDate2);
        btnTinh = (Button)findViewById(R.id.buttonTinh);
        tvKetQua = (TextView)findViewById(R.id.TextViewKetqua);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePickerThuNhat();
            }
        });

        edtDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePickerThuHai();
            }
        });

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ý tưởng sẽ lấy ra mili giây rồi sau đó đổi ra giờ phút giây
//                myCalendar.getTimeInMillis();
//                myCalendar2.getTimeInMillis();

                //ép kiểu về int , sau đó chia cho số để trả tgian về ngày
                int soNgay = (int) (myCalendar2.getTimeInMillis()-myCalendar.getTimeInMillis()) / (24*60*60*1000);
                if(soNgay<0){
                    tvKetQua.setText("Ngay thu 2 ko duoc truoc ngay thu 1");
                }else {
                    tvKetQua.setText("Khoảng cách là : "+soNgay+" ngày"); // kết quả thời gian ra milisecond
                }
            }
        });
    }

    private void ShowDatePickerThuNhat (){
        myCalendar = Calendar.getInstance();
        int year = myCalendar.get(Calendar.YEAR);
        // chú ý tháng bị +1 nếu gọi riêng , gọi chung thì bị -1, gọi từng cái rồi đổ vào là đúng nhất
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(year,month,dayOfMonth);
                edtDate.setText(dinhDangNgay.format(myCalendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private void ShowDatePickerThuHai (){
        myCalendar2 = Calendar.getInstance();
        int year = myCalendar2.get(Calendar.YEAR);
        // chú ý tháng bị +1 nếu gọi riêng , gọi chung thì bị -1, gọi từng cái rồi đổ vào là đúng nhất
        int month = myCalendar2.get(Calendar.MONTH);
        int day = myCalendar2.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar2.set(year,month,dayOfMonth);
                edtDate2.setText(dinhDangNgay.format(myCalendar2.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
