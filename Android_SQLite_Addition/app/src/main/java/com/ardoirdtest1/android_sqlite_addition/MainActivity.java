package com.ardoirdtest1.android_sqlite_addition;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.ardoirdtest1.android_sqlite_addition.R.id.edtName;

public class MainActivity extends AppCompatActivity {
    EditText edtActivity, edtlocal, edtDate, edttime, edtname;
    Button btnInsert;
    ImageButton imageCamera, imageFile;
    ImageView imageView;
    ListView myView;
    int idevent = 0;
    private static final int PICK_FROM_CAMERA = 1234;
    private static final int PICK_FROM_GALLERY = 12345;
    public static SQLite database;
    IdiscoveryAdapter adapter;
    ArrayList<Idiscovery> arrListIdiscovery = new ArrayList<Idiscovery>();
    Calendar selectCal;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtActivity = (EditText) findViewById(R.id.edtActivity);
        edtlocal = (EditText) findViewById(R.id.edtLocation);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edttime = (EditText) findViewById(R.id.edtTime);
        edtname = (EditText) findViewById(edtName);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        imageCamera = (ImageButton) findViewById(R.id.imageCamera);
        imageFile = (ImageButton) findViewById(R.id.imageButtobFile);
        imageView = (ImageView) findViewById(R.id.imageView);
        database = new SQLite(this, "Idiscovery.sqlite", null, 1);// Idiscovery.sqlite is database name
        //Create table Event
        database.QueryData("Create table if not exists Event" +
                " (Id integer Primary key AutoIncrement, activity varchar, location varchar, date varchar, time varchar, name varchar, photo Blob)");
        //Type Blob luu trử file
        myView = (ListView) findViewById(R.id.listviewCus);
        adapter = new IdiscoveryAdapter(
                MainActivity.this,
                R.layout.customer_view,
                arrListIdiscovery
        );
        myView.setAdapter(adapter);
        loadData();
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_DatePicker();
            }
        });
        edttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_TimePicker();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edtActivity.getText().toString().trim();
                String s2 = edtDate.getText().toString();
                String s4 = edtname.getText().toString();
                if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s4)) {
                    edtActivity.setError("Text file not nul");
                    edtDate.setError("Your Date not nul");
                    edtname.setError("Text file not nul");
                } else {
                    MainActivity.database.InsertEvent(
                            edtActivity.getText().toString().trim(),
                            edtlocal.getText().toString().trim(),
                            edtDate.getText().toString().trim(),
                            edttime.getText().toString().trim(),
                            edtname.getText().toString().trim(),
                            ImageView_To_Byte(imageView)

                    );
                    Toast.makeText(MainActivity.this, "Insert succes", Toast.LENGTH_SHORT).show();
                    edtActivity.setText("");
                    edtlocal.setText("");
                    edtDate.setText("");
                    edttime.setText("");
                    edtname.setText("");
                    imageView.setImageResource(R.drawable.noimageavailable);
                    loadData();
                }

            }
        });
        //delete
        myView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Confirm_Delete(arrListIdiscovery.get(position).activity);
                idevent = arrListIdiscovery.get(position).id;
                return false;
            }
        });


        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK_FROM_CAMERA);
                //dành cho andriod 6.0 trở lên
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        PICK_FROM_CAMERA
                );
            }
        });
        imageFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_FROM_GALLERY);
            }
        });
    }


    //request code for android 6.0
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_FROM_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)// kết quả thành công khi click 0k
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } else {
                    Toast.makeText(MainActivity.this, "you not provide admintation for camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case PICK_FROM_GALLERY: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_FROM_GALLERY);
                } else {
                    Toast.makeText(MainActivity.this, "you not provide adminatation for Gallery", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);//lấy luồn dử liệu nôi dung từ đường dẩn
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ImageView_To_Byte(ImageView imgv) {

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void loadData() {
        Cursor cursorPd = MainActivity.database.GetData("Select * from event");
        arrListIdiscovery.clear();
        adapter.notifyDataSetChanged();
        while (cursorPd.moveToNext()) { //moveToNext duyệt ng đầu tiên -> cuối cùng
            int id = cursorPd.getInt(0);
            String activity = cursorPd.getString(1);
            String location = cursorPd.getString(2);
            String date = cursorPd.getString(3);
            String time = cursorPd.getString(4);
            String name = cursorPd.getString(5);
            byte[] image = cursorPd.getBlob(6);
            arrListIdiscovery.add(new Idiscovery(id, activity, location, date, time, name, image));
        }
    }
    //confirm delete
    private void Confirm_Delete(String activity) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Confirm Remove");
        aBuilder.setMessage("Do you want to delete" +"   "+ activity + "?");
        aBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("Delete From event where Id ='" + idevent + "'");
                Log.d("AAAA","Delete From event where Id ='" + idevent + "'");
                Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        aBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aBuilder.show();
    }
    //search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                loadData();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.filter(newText.trim());
                loadData();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void Get_DatePicker (){
        selectCal = Calendar.getInstance();
        int yearshow = selectCal.get(Calendar.YEAR);
        int monthshow = selectCal.get(Calendar.MONTH);
        int dayshow = selectCal.get(Calendar.DAY_OF_MONTH);
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DatePickerDialog getDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectCal.set(year,month,dayOfMonth);
                edtDate.setText(format.format(selectCal.getTime()));
            }
        },yearshow,monthshow,dayshow);
        getDatePickerDialog.setTitle("Choose Date");
        getDatePickerDialog.show();
    }
    private  void Get_TimePicker(){

        Calendar selectTime = Calendar.getInstance();
        int hour = selectTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectTime.get(Calendar.MINUTE);
        TimePickerDialog getTimePicker;
        getTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int seldHour, int seldMinute) {
                edttime.setText(seldHour + ":" + seldMinute);
            }
        }, hour, minute, true);
        getTimePicker.setTitle("Choose Time");
        getTimePicker.show();

    }
}
