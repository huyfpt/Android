package com.a1.demo19.sqlite_get_image_from_anywhere;

import android.Manifest;
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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class InsertItems extends AppCompatActivity {
    ImageButton imageCamera, imageFile;
    ImageView imageView;
    Button btnInsertItem, btnUpdateItems;
    EditText edtName, edtPrice;
    int idstores = 0;
    private static final int PICK_FROM_CAMERA = 1234;
    private static final int PICK_FROM_GALLERY = 12345;
    public static SQLite database;
    StoreAdapter adapter = null;
    ArrayList<Store> arrayListstores = new ArrayList<Store>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_items);
        edtName = (EditText) findViewById(R.id.EditItemName);
        edtPrice = (EditText) findViewById(R.id.editText);
        imageCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        imageFile = (ImageButton) findViewById(R.id.imageButtonFile);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnInsertItem = (Button) findViewById(R.id.buttonSaveInsert);
        btnUpdateItems = (Button) findViewById(R.id.buttonUpdate);
        database = new SQLite(this, "Store.sqlite", null, 1);
        final ListView mylview = (ListView) findViewById(R.id.listView1);
        adapter = new StoreAdapter(
                InsertItems.this,
                R.layout.customer_view,
                arrayListstores
        );
        mylview.setAdapter(adapter);
        //update
        mylview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtName.setText(arrayListstores.get(position).name);
                edtPrice.setText(arrayListstores.get(position).price + "");
                Bitmap bitmap = BitmapFactory.decodeByteArray(arrayListstores.get(position).image, 0, arrayListstores.get(position).image.length);
                imageView.setImageBitmap(bitmap);
                idstores = arrayListstores.get(position).id;
            }
        });
        btnUpdateItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idstores > 0) {
                    String Newname = edtName.getText().toString().trim();
                    float NewPrice = Float.parseFloat(edtPrice.getText().toString().trim());
                    byte[] newimage = ImageView_To_Byte(imageView);
                    database.UpdateProduct(Newname, NewPrice, newimage, idstores);
                    Log.d("AAA", "" + newimage);
                }
                loadData();
                idstores = 0;
            }
        });
        loadData();
        btnInsertItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.InsertProduct(
                        edtName.getText().toString().trim(),
                        Integer.parseInt(edtPrice.getText().toString().trim()),
                        ImageView_To_Byte(imageView)
                );
                Toast.makeText(InsertItems.this, "Insert succes", Toast.LENGTH_SHORT).show();
                edtName.setText("");
                edtPrice.setText("");
                imageView.setImageResource(R.drawable.noimageavailable);
            }
        });
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, PICK_FROM_CAMERA);
                //dành cho andriod 6.0 trở lên
                ActivityCompat.requestPermissions(
                        InsertItems.this,
                        new String[]{Manifest.permission.CAMERA},
                        PICK_FROM_CAMERA
                );
            }
        });
        imageFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, PICK_FROM_GALLERY);
                ActivityCompat.requestPermissions(
                        InsertItems.this,
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
                    Toast.makeText(InsertItems.this, "you not provide admitation for camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case PICK_FROM_GALLERY: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_FROM_GALLERY);
                } else {
                    Toast.makeText(InsertItems.this, "you not provide adminatation for Gallery", Toast.LENGTH_SHORT).show();
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
        Cursor cursorPd = MainActivity.database.GetData("Select * from product");
        arrayListstores.clear();
        adapter.notifyDataSetChanged();
        while (cursorPd.moveToNext()) { //moveToNext duyệt ng đầu tiên -> cuối cùng
            int id = cursorPd.getInt(0);
            String name = cursorPd.getString(1);
            float price = cursorPd.getFloat(2);
            byte[] image = cursorPd.getBlob(3);
            arrayListstores.add(new Store(id, name, price, image));
        }
    }
}




