package com.demo8.demo_listview;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lwInter;
    ArrayList<String> international;
    EditText edtVAdd;
    Button btnAdd, btnUpdate;
    int range = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lwInter =(ListView)findViewById(R.id.listViewInternational);
        edtVAdd = (EditText) findViewById(R.id.editText);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        international = new ArrayList<String>();
        AddNational();
        final ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                international);
            lwInter.setAdapter(adapter);
//            lwInter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // position : return range when click on lV
////                Toast.makeText(MainActivity.this, position +"", Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this, international.get(range), Toast.LENGTH_LONG).show();
//            }
//        });

    lwInter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                international.remove(lwInter.indexOfChild(view));
                adapter.notifyDataSetChanged();//cập nhật lại lítview
                Toast.makeText(MainActivity.this, "delete success", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameNational = edtVAdd.getText().toString().trim(); //.trim cắt chuổi rổng đầu đuôi
                if(international.contains(nameNational)){// Kiển=m tra tồn tại
                    Toast.makeText(MainActivity.this, "The national has already exist", Toast.LENGTH_LONG).show();
                }else {
                international.add(nameNational);
                adapter.notifyDataSetChanged();//cập nhật lại lítview
                Toast.makeText(MainActivity.this, "insert success", Toast.LENGTH_LONG).show();
                edtVAdd.setText("");
                }
            }
        });


    lwInter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                range = position;
                edtVAdd.setText(international.get(range));
                Toast.makeText(MainActivity.this, international.get(range), Toast.LENGTH_LONG).show();

            }
        });


    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(range < 0){
                Toast.makeText(MainActivity.this, "Please choose any national up to date", Toast.LENGTH_LONG).show();
            }else {
            international.set(range, edtVAdd.getText().toString().trim());
            adapter.notifyDataSetChanged();//cập nhật lại lítview
            Toast.makeText(MainActivity.this, "update success", Toast.LENGTH_LONG).show();
            edtVAdd.setText("");
            range =-1;
            }
        }
    });
    }

    private void AddNational(){
        international.add("USA");
        international.add("Russia");
        international.add("Pasri");
        international.add("Costland");
        international.add("Japan");
        international.add("VietNam");

    }
}
//    public void showInputBox(String oldItem, final int index){
//        final Dialog dialog=new Dialog(MainActivity.this);
//        dialog.setTitle("Input Box");
//        dialog.setContentView(R.layout.input_box);
//        TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmessage);
//        txtMessage.setText("Update item");
//        txtMessage.setTextColor(Color.parseColor("#ff2222"));
//        final EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
//        editText.setText(oldItem);
//
//}
